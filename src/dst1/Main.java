package dst1;

import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand.OutputType;
import com.mongodb.MapReduceOutput;
import com.mongodb.Mongo;
import com.mongodb.MongoException;



import dst1.interceptor.SQLInterceptor;
import dst1.listener.DefaultListener;
import dst1.logging.MyConsoleFormatter;
import dst1.model.*;
import dst1.query.JobQueries;

public class Main {
	
	private static Logger logger;
	private static EntityManager em;
	private static Mongo mongo;
	private static DB mongoDB;

	private Main() {
		super();
	}

	public static void main(String[] args) {
		initLogging();
		
		logger.info("Creating entity manager...");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("grid");
		em = emf.createEntityManager();
		logger.info("Done.");
		
		logger.info("Connecting to MongoDB...");
		try {
			mongo = new Mongo("localhost", 27017);
			mongoDB = mongo.getDB("dst");
			mongoDB.dropDatabase();
			logger.info("Done.");
		} catch (UnknownHostException e) {
			logger.severe("Not able to connect to MongoDB: " + e.getMessage());
		} catch (MongoException e) {
			logger.severe("Not able to connect to MongoDB: " + e.getMessage());
		}
		
		dst01();
		
		/*User user = new User();
		System.out.println("Password before: " + user.getPassword());
		try {
			user.setPassword("gerhard");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Password after: " + user.getPassword());
		
		try {
			if(user.comparePassword("gerhard"))
				System.out.println("gerhard is OK");
			else
				System.out.println("gerhard ist NOT OK");
			if(user.comparePassword("schraml"))
				System.out.println("schraml is OK");
			else
				System.out.println("schraml ist NOT OK");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		dst02a();
		dst02b();
		dst02c();
		dst03();
		dst04a();
		dst04b();
		dst04c();
		dst04d();
		dst05a();
		dst05b();
		dst05c();
	}

	public static void dst01() {
		logger.info("=============== Starting dst01() ===============");
		
		logger.info("Creating domain objects...");
		
		// create administrators
		Admin admin1 = new Admin("Albert", "Auer", new Address("Hauptstra§e 1", "Wien", "1100"));
		Admin admin2 = new Admin("Bertram", "Becker", new Address("Hauptstra§e 2", "Wien", "1100"));
		Admin admin3 = new Admin("Christoph", "Cerny", new Address("Hauptstra§e 3", "Wien", "1100"));
		Admin admin4 = new Admin("Daniel", "DŸsentrieb", new Address("Hauptstra§e 4", "Wien", "1100"));
		Admin admin5 = new Admin("Ignaz", "Ibertsberger", new Address("Jakominiplatz 1", "Graz", "8020"));
		
		// create users
		User user1 = null, user2 = null, user3 = null, user4 = null;
		try {
			user1 = new User("Erwin", "Ehrenreich", "erwin1", "erwin1", "100", "60000", new Address("Hauptstra§e 5", "Wien", "1100"));
			user2 = new User("Fritz", "FŸrchterlich", "fritz1", "fritz1", "200", "60000", new Address("Hauptstra§e 6", "Wien", "1100"));
			user3 = new User("Gerhard", "Gruselglatz", "gerhard1", "gerhard1", "300", "60000", new Address("Hauptstra§e 7", "Wien", "1100"));
			user4 = new User("Harald", "Heidelberger", "harald1", "harald1", "400", "60000", new Address("Hauptstra§e 8", "Wien", "1100"));
		} catch (NoSuchAlgorithmException e1) {
			logger.severe("Missing md5 algorithm");
		}
		
		// create grids
		Grid grid1 = new Grid("Grid Vienna", "Vienna", new BigDecimal(0.5));
		Grid grid2 = new Grid("Grid Munich", "Munich", new BigDecimal(0.7));
		Grid grid3 = new Grid("Grid Graz", "Graz", new BigDecimal(0.3));
		
		Membership membership1 = new Membership(grid1, user1, new Date(), 0.1);
		Membership membership2 = new Membership(grid1, user2, new Date(), 0.2);
		Membership membership3 = new Membership(grid2, user3, new Date(), 0.3);
		Membership membership4 = new Membership(grid2, user4, new Date(), 0.4);
		
		// create clusters
		Cluster cluster1 = new Cluster("Cluster 1", null, null);
		Cluster cluster2 = new Cluster("Cluster 2", null, null);
		Cluster cluster3 = new Cluster("Cluster 3", null, null);
		Cluster cluster4 = new Cluster("Cluster 4", null, null);
		Cluster cluster5 = new Cluster("Cluster 5", null, null);
		Cluster cluster6 = new Cluster("Cluster 6", null, null);
		grid1.addCluster(cluster1);
		grid1.addCluster(cluster3);
		grid1.addCluster(cluster4);
		grid2.addCluster(cluster2);
		grid3.addCluster(cluster5);
		grid3.addCluster(cluster6);
		admin1.addCluster(cluster1);
		admin2.addCluster(cluster2);
		admin3.addCluster(cluster3);
		admin4.addCluster(cluster4);
		admin5.addCluster(cluster5);
		admin5.addCluster(cluster6);
		
		// create computers
		Date date = new Date();
		Computer computer1 = new Computer("Computer Vienna Wieden", 2, "AUT-VIE@1040", date, date);
		Computer computer2 = new Computer("Computer Munich", 4, "GER-MUN@1234", date, date);
		Computer computer3 = new Computer("Computer Vienna Favoriten", 6, "AUT-VIE@1100", date, date);
		Computer computer4 = new Computer("Computer Vienna Margareten", 8, "AUT-VIE@1050", date, date);
		Computer computer5 = new Computer("Computer Graz Andritz", 6, "AUT-GRA@8040", date, date);
		Computer computer6 = new Computer("Computer Graz Jakomini", 8, "AUT-GRA@8020", date, date);
		Computer computer7 = new Computer("Computer Vienna Landstra§e", 4, "AUT-VIE@1030", date, date);
		Computer computer8 = new Computer("Computer Vienna Simmering", 6, "AUT-VIE@1110", date, date);
		cluster1.addComputer(computer1);
		cluster2.addComputer(computer3);
		cluster2.addComputer(computer2);
		cluster2.addComputer(computer7);
		cluster2.addComputer(computer8);
		cluster4.addComputer(computer4);
		cluster5.addComputer(computer5);
		cluster5.addComputer(computer6);
		
		cluster3.addChildCluster(cluster4);
		cluster1.addChildCluster(cluster3);
		
		// create environments
		Environment environment1 = new Environment("Workflow 1");
		Environment environment2 = new Environment("Workflow 2");
		Environment environment3 = new Environment("Workflow 3");
		Environment environment4 = new Environment("Workflow 4");
		Environment environment5 = new Environment("Workflow 5");
		Environment environment6 = new Environment("Workflow 6");
		Environment environment7 = new Environment("Workflow 7");
		Environment environment8 = new Environment("Workflow 8");
		environment1.addParam("param1");
		environment1.addParam("param2");
		environment2.addParam("param3");
		environment2.addParam("param4");
		environment3.addParam("param5");
		environment3.addParam("param6");
		environment4.addParam("param7");
		environment4.addParam("param8");
		
		// create jobs
		Job job1 = new Job(true);
		Job job2 = new Job(false);
		Job job3 = new Job(true);
		Job job4 = new Job(false);
		Job job5 = new Job(false);
		Job job6 = new Job(true);
		Job job7 = new Job(true);
		Job job8 = new Job(false);
		job1.setEnvironment(environment1);
		job2.setEnvironment(environment2);
		job3.setEnvironment(environment3);
		job4.setEnvironment(environment4);
		job5.setEnvironment(environment5);
		job6.setEnvironment(environment6);
		job7.setEnvironment(environment7);
		job8.setEnvironment(environment8);
		user1.addJob(job1);
		user2.addJob(job2);
		user3.addJob(job3);
		user3.addJob(job5);
		user4.addJob(job4);
		user3.addJob(job6);
		user2.addJob(job7);
		user2.addJob(job8);
		
		// create executions
		Execution execution1 = new Execution(new Date(), null, JobStatus.RUNNING);
		Execution execution2 = new Execution(new Date(), null, JobStatus.SCHEDULED);
		Execution execution3 = new Execution(new Date(), new Date(), JobStatus.FAILED);
		Execution execution4 = new Execution(new Date(), new Date(), JobStatus.FINISHED);
		Execution execution5 = new Execution(new Date(), new Date(), JobStatus.FINISHED);
		Execution execution6 = new Execution(new Date(), new Date(), JobStatus.FINISHED);
		Execution execution7 = new Execution(new Date(), new Date(), JobStatus.FINISHED);
		Execution execution8 = new Execution(new Date(), new Date(), JobStatus.FINISHED);
		execution1.setJob(job1);
		execution2.setJob(job2);
		execution3.setJob(job3);
		execution4.setJob(job4);
		execution5.setJob(job5);
		execution6.setJob(job6);
		execution7.setJob(job7);
		execution8.setJob(job8);
		execution1.addComputer(computer1);
		execution2.addComputer(computer2);
		execution3.addComputer(computer3);
		execution4.addComputer(computer4);
		execution5.addComputer(computer4);
		execution1.addComputer(computer7);
		execution2.addComputer(computer7);
		execution3.addComputer(computer8);
		execution4.addComputer(computer8);
		execution6.addComputer(computer4);
		execution7.addComputer(computer4);
		execution8.addComputer(computer4);
		
		logger.info("Done.");
		
		try {
			logger.info("Starting transaction...");
			em.getTransaction().begin();
			logger.info("Done.");
			
			logger.info("Persisting administrators...");
			em.persist(admin1);
			em.persist(admin2);
			em.persist(admin3);
			em.persist(admin4);
			em.persist(admin5);
			logger.info("Done.");
			
			logger.info("Persisting users...");
			em.persist(user1);
			em.persist(user2);
			em.persist(user3);
			em.persist(user4);
			logger.info("Done.");
			
			logger.info("Persisting grids...");
			em.persist(grid1);
			em.persist(grid2);
			em.persist(grid3);
			logger.info("Done.");
			
			logger.info("Persisting clusters...");
			em.persist(cluster1);
			em.persist(cluster2);
			em.persist(cluster3);
			em.persist(cluster4);
			em.persist(cluster5);
			em.persist(cluster6);
			logger.info("Done.");
			
			logger.info("Persisting computers...");
			em.persist(computer1);
			em.persist(computer2);
			em.persist(computer3);
			em.persist(computer4);
			em.persist(computer5);
			em.persist(computer6);
			em.persist(computer7);
			em.persist(computer8);
			logger.info("Done.");
			
			logger.info("Persisting memberships...");
			em.persist(membership1);
			em.persist(membership2);
			em.persist(membership3);
			em.persist(membership4);
			logger.info("Done.");
			
			logger.info("Persisting environments...");
			em.persist(environment1);
			em.persist(environment2);
			em.persist(environment3);
			em.persist(environment4);
			em.persist(environment5);
			em.persist(environment6);
			em.persist(environment7);
			em.persist(environment8);
			logger.info("Done.");
			
			logger.info("Persisting jobs...");
			em.persist(job1);
			em.persist(job2);
			em.persist(job3);
			em.persist(job4);
			em.persist(job5);
			em.persist(job6);
			em.persist(job7);
			em.persist(job8);
			logger.info("Done.");
			
			logger.info("Persisting executions...");
			em.persist(execution1);
			em.persist(execution2);
			em.persist(execution3);
			em.persist(execution4);
			em.persist(execution5);
			em.persist(execution6);
			em.persist(execution7);
			em.persist(execution8);
			logger.info("Done.");
			
			// delete execution1
			// computers first have to be removed from the executions computerList - they should not be deleted
			// deletion of execution1 causes cascading removal of job1, which causes cascading removal of environment1
			logger.info("Removing " + execution1);
			execution1.removeAllComputers();
			em.remove(execution1);
			logger.info("Done.");
			
			// delete computer 2
			// computers are automatically removed from the executions computerLists
			logger.info("Removing " + computer2);
			em.remove(computer2);
			logger.info("Done.");
			
			// delete admin 3
			// deletion of admin3 causes the deletion of all associated clusters and child clusters with all their computers
			logger.info("Removing " + admin3);
			em.remove(admin3);
			logger.info("Done.");
			
			// delete grid 1
			logger.info("Removing " + grid1);
			em.remove(grid1);
			logger.info("Done.");
			
			logger.info("Committing transaction...");
			em.getTransaction().commit();
			logger.info("Done.");
		} catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			logger.severe(e.getMessage());
		}

		logger.info("=============== Finished dst01() ===============");
	}

	public static void dst02a() {
		logger.info("=============== Starting dst02a() ===============");
		
		String gridName = "Grid Munich";
		
		for(long jobCount = 0l; jobCount <= 2; jobCount++) {
			logger.info("Querying users of grid '" + gridName + "' with at least " + jobCount + " job(s) assigned...");
			Query queryUsersOfGridByJobCount = em.createNamedQuery("findUsersOfGridByJobCount");
			queryUsersOfGridByJobCount.setParameter("nameOfGrid", gridName);
			queryUsersOfGridByJobCount.setParameter("jobCount", jobCount);
			displayQueryResults(queryUsersOfGridByJobCount);
		}
		
		logger.info("Querying most active users...");
		Query queryMostActiveUsers = em.createNamedQuery("findMostActiveUsers");
		displayQueryResults(queryMostActiveUsers);
		
		logger.info("=============== Finished dst02a() ===============");
	}

	public static void dst02b() {
		logger.info("=============== Starting dst02b() ===============");
		
		logger.info("Querying computers in vienna...");
		Query queryComputersInVienna = em.createNamedQuery("findComputersInVienna");
		displayQueryResults(queryComputersInVienna);
		List<Computer> computers = queryComputersInVienna.getResultList();
		for(Computer computer : computers) {
			long totalUsage = 0;
			for(Execution execution : computer.getExecutionList()) {
				Date endDate = (execution.getEnd() == null ? new Date() : execution.getEnd());
				if(execution.getStart() != null) {
					totalUsage += (endDate.getTime() - execution.getStart().getTime());
				}
			}
			logger.info("Computer id=" + computer.getId() + " has total usage of " + totalUsage + " ms.");
		}
		
		logger.info("=============== Finished dst02b() ===============");
	}

	public static void dst02c() {
		logger.info("=============== Starting dst02c() ===============");
		
		logger.info("Query jobs of user gerhard1, no restriction for workflow name:");
		List<Job> jobsByUsername = JobQueries.findJobsByUserAndWorkflow(em, "gerhard1", null);
		for(Job job:jobsByUsername) {
			logger.info(job.toString());
		}
		
		logger.info("Query jobs of user 'gerhard1' and workflow name 'Workflow 3':");
		List<Job> jobsByUsernameAndWorkflow = JobQueries.findJobsByUserAndWorkflow(em, "gerhard1", "Workflow 3");
		for(Job job:jobsByUsernameAndWorkflow) {
			logger.info(job.toString());
		}
		
		logger.info("Query all finished jobs:");
		List<Job> allFinishedJobs = JobQueries.findFinishedJobsByStartAndEndDate(em, null, null);
		for(Job job:allFinishedJobs) {
			logger.info(job.toString());
			//logger.info("  " + job.getExecution().toString());
		}
		
		logger.info("=============== Finished dst02c() ===============");
	}

	public static void dst03() {
		logger.info("=============== Starting dst03() ===============");
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		
		Date futureDate = new Date();
		Date pastDate = new Date();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2020, 1, 1);
		futureDate = calendar.getTime();
		calendar.set(2010, 1, 1);
		pastDate = calendar.getTime();
		
		Computer invalidComputer = new Computer();
		invalidComputer.setCpus(10);
		invalidComputer.setLastUpdate(futureDate);
		invalidComputer.setCreation(futureDate);
		invalidComputer.setName("abc");
		invalidComputer.setLocation("AUT-VIE@10401");
		
		Computer validComputer = new Computer();
		validComputer.setCpus(6);
		validComputer.setLastUpdate(pastDate);
		validComputer.setCreation(pastDate);
		validComputer.setName("Computer");
		validComputer.setLocation("AUT-VIE@1040");
		
		// validate invalid computer
		logger.info("Validating invalid computer");
		Set<ConstraintViolation<Computer>> violations = validator.validate(invalidComputer);
		if(violations.size() > 0) {
			logger.warning("Violations: " + violations.size());
			for(ConstraintViolation<Computer> violation : violations) {
				logger.warning(violation.getMessage());
			}
		}
		else
			logger.info("No violations found.");
		
		// validate valid computer
		logger.info("Validating valid computer");
		violations = validator.validate(validComputer);
		if(violations.size() > 0) {
			logger.warning("Violations: " + violations.size());
			for(ConstraintViolation<Computer> violation : violations) {
				logger.warning(violation.getMessage());
			}
		}
		else
			logger.info("No violations found.");
		
		logger.info("=============== Finished dst03() ===============");
	}

	public static void dst04a() {
		logger.info("=============== Starting dst04a() ===============");
		
		// Possible states of the persistence lifecycle:
		//  - NEW
		//  - MANAGED
		//  - REMOVED
		//  - DETACHED
		
		logger.info("Starting transaction...");
		em.getTransaction().begin();
		logger.info("Done.");
		
		// newly created entities job, environment and execution are is in state NEW
		// the entity manager does not know about existence of the entities
		Job job = new Job(true);
		Environment environment = new Environment("Some workflow");
		Execution execution = new Execution(new Date(), null, JobStatus.SCHEDULED);		
		
		// retrieved user is in the state MANAGED
		User user = em.find(User.class, 8l);
		
		// add mandatory associations
		user.addJob(job);
		execution.setJob(job);
		job.setEnvironment(environment);
		
		// after persisting the new entities are in state MANAGED
		em.persist(user);
		em.persist(environment);
		em.persist(job);
		em.persist(execution);
		
		// after the remove operation (and before closing the entity manager or
		// committing the transaciton) all objects are in state REMOVED
		// deleting the execution entity causes cascading deletion of the job and environment entity
		em.remove(execution);
		
		// if the entity manager gets closed, all managed objects are in state DETACHED
		// it is also possible to detach single entities manually by calling em.detach(object);
		// the objects then aren't managed by the entity manager any more
		// em.close();
		
		em.getTransaction().commit();
		
		
		logger.info("=============== Finished dst04a() ===============");
	}

	public static void dst04b() {
		logger.info("=============== Starting dst04b() ===============");
		
		Computer computer = new Computer();
		
		try {
			logger.info("Starting transaction...");
			em.getTransaction().begin();
			logger.info("Done.");
		
			Computer computer5 = em.find(Computer.class, 5l);
			logger.info("Computer 5 lastUpdate before persist(): " + computer5.getLastUpdate());
			
			try {
				logger.info("Sleeping for 3 seconds...");
				Thread.sleep(3000);
				logger.info("Done.");
			} catch(InterruptedException e) {
			}
			
			logger.info("Updating name of computer 5...");
			computer5.setName("New name of computer 5");
			em.persist(computer5);
			logger.info("Done.");
			
			em.getTransaction().commit();
			
			logger.info("Starting transaction...");
			em.getTransaction().begin();
			logger.info("Done.");
			
			Computer updated = em.find(Computer.class, 5l);
			logger.info("Computer 5 lastUpdate after persist(): " + updated.getLastUpdate());
			
			em.getTransaction().commit();
		
			
		} catch(Exception e) {
			em.getTransaction().rollback();
			logger.severe(e.getMessage());
		}
		
		logger.info("=============== Finished dst04b() ===============");
	}

	public static void dst04c() {
		logger.info("=============== Starting dst04c() ===============");
		logger.info(DefaultListener.getAccessStatistics());
		logger.info("=============== Finished dst04c() ===============");
	}

	public static void dst04d() {
		logger.info("=============== Starting dst04d() ===============");
		logger.info("Statistics before counter reset:");
		logger.info(SQLInterceptor.getStatistics());
		SQLInterceptor.resetCounter();
		logger.info("Statistics after counter reset:");
		logger.info(SQLInterceptor.getStatistics());
		dst02b();
		logger.info("Statistics after calling dst02b():");
		logger.info(SQLInterceptor.getStatistics());
		logger.info("=============== Finished dst04d() ===============");
	}

    public static void dst05a() {
    	logger.info("=============== Starting dst05a() ===============");
    	
    	logger.info("Querying finished Jobs...");
		Query queryJobsByStatus = em.createNamedQuery("findJobsByStatus");
		queryJobsByStatus.setParameter("jobStatus", JobStatus.FINISHED);
		List<Job> finishedJobs = queryJobsByStatus.getResultList();
		
		DBCollection collection = mongoDB.getCollection("finishedJobs");
		
		for(Job job:finishedJobs) {
			logger.info(job.toString());
			BasicDBObject document = new BasicDBObject();
			document.put("job_id", job.getId());
			document.put("last_updated", new Date().getTime());
			
			if(job.getId() == 4l) {
				// job 4 --> chromosome_example.json
				logger.info("Preparing chromosome_example.json for insert...");
				BasicDBObject primary = new BasicDBObject();
				primary.put("chromosome", "chr11");
				primary.put("start", 3001012);
				primary.put("end", 3001075);
				BasicDBObject align = new BasicDBObject();
				align.put("chromosome", "chr13");
				align.put("start", 70568380);
				align.put("end", 70568443);
				ArrayList<String> seq = new ArrayList<String>();
				seq.add("TCAGCTCATAAATCACCTCCTGCCACAAGCCTGGCCTGGTCCCAGGAGAGTGTCCAGGCTCAGA");
				seq.add("TCTGTTCATAAACCACCTGCCATGACAAGCCTGGCCTGTTCCCAAGACAATGTCCAGGCTCAGA");
				
				BasicDBObject alignmentBlock = new BasicDBObject();
				alignmentBlock.put("alignment_nr", 0);
				alignmentBlock.put("primary", primary);
				alignmentBlock.put("align", align);
				alignmentBlock.put("blastz", 3500);
				alignmentBlock.put("seq", seq);
				
				document.put("alignment_block", alignmentBlock);
			} else if(job.getId() == 5l) {
				// job 5 --> log_example.json
				logger.info("Preparing log_example.json for insert...");
				ArrayList<String> log_set = new ArrayList<String>();
				log_set.add("Starting");
				log_set.add("Running");
				log_set.add("Still Running");
				log_set.add("Finished");
				
				BasicDBObject logs = new BasicDBObject();
				logs.put("log_set", log_set);
				
				document.put("logs", logs);
			} else if(job.getId() == 6l) {
				// job 6 --> matrix_example.json
				logger.info("Preparing matrix_example.json for insert...");
				int[] row1 = {1, 0, 0, 0};
				int[] row2 = {0, 1, 0, 0};
				int[] row3 = {0, 0, 1, 0};
				int[] row4 = {0, 0, 0, 1};
				int[][] matrix = {row1, row2, row3, row4};
				
				BasicDBObject resultMatrix = new BasicDBObject();
				resultMatrix.put("matrix", matrix);
				document.put("result_matrix", resultMatrix);
				document.put("type", "identity_matrix");
			} else if(job.getId() == 7l) {
				// job 7 --> admin data
				logger.info("Preparing admin data for insert...");
				Query queryAdmins = em.createQuery("SELECT OBJECT(a) FROM Admin a");
				List<Admin> admins = queryAdmins.getResultList();
				ArrayList<BasicDBObject> adminList = new ArrayList<BasicDBObject>();
				for(Admin admin:admins) {
					ArrayList<String> clusterNames = new ArrayList<String>();
					for(Cluster cluster:admin.getClusterList()) {
						clusterNames.add(cluster.getName());
					}
					BasicDBObject adminObject = new BasicDBObject();
					adminObject.put("id", admin.getId());
					BasicDBObject name = new BasicDBObject();
					name.put("first_name", admin.getFirstName());
					name.put("last_name", admin.getLastName());
					adminObject.put("name", name);
					BasicDBObject address = new BasicDBObject();
					address.put("street", admin.getAddress().getStreet());
					address.put("city", admin.getAddress().getCity());
					address.put("zip_code", admin.getAddress().getZipCode());
					adminObject.put("address", address);
					adminObject.put("cluster_list", clusterNames);
					adminList.add(adminObject);
				}
				document.put("type", "some type information");
				document.put("admins", adminList);
			} else if(job.getId() == 8l) {
				// job 8 --> listener results
				logger.info("Preparing listener results for insert...");
				BasicDBObject persistStatistics = new BasicDBObject();
				persistStatistics.put("persist_operations", DefaultListener.getPersistCount());
				persistStatistics.put("overall_persist_time", DefaultListener.getPersistTime());
				if(DefaultListener.getPersistCount() != 0)
					persistStatistics.put("average_persist_time", (1.0 * DefaultListener.getPersistTime() / DefaultListener.getPersistCount()));
				else
					persistStatistics.put("average_persist_time", 0);
				
				BasicDBObject listenerResults = new BasicDBObject();
				listenerResults.put("load_operations", DefaultListener.getLoadCount());
				listenerResults.put("update_operations", DefaultListener.getUpdateCount());
				listenerResults.put("remove_operations", DefaultListener.getRemoveCount());
				listenerResults.put("persist_information", persistStatistics);
				document.put("type", "some other type information");
				document.put("listener_results", listenerResults);
			}
			
			collection.insert(document);
			logger.info("Insert done.");
		}
		logger.info("=============== Finished dst05a() ===============");
    }

    public static void dst05b() {
    	logger.info("=============== Starting dst05b() ===============");
    	
    	logger.info("Querying job with id=5...");
    	DBCollection collection = mongoDB.getCollection("finishedJobs");
    	BasicDBObject pattern = new BasicDBObject();
    	pattern.put("job_id", 5);
    	DBObject job5 = collection.findOne(pattern);
    	
    	if(job5 == null)
    		logger.info("Job not found.");
    	else
    		logger.info(job5.toString());
    	
    	long time = 1325397600;
    	logger.info("Querying all jobs with last_updated > " + time + "...");
    	BasicDBObject greaterThan = new BasicDBObject("$gt", time);
    	pattern = new BasicDBObject();
    	pattern.put("last_updated", greaterThan);
    	
    	BasicDBObject filter = new BasicDBObject();
    	filter.put("_id", 0);
    	
    	DBCursor jobs = collection.find(pattern, filter);
    	
    	while(jobs.hasNext()) {
    		logger.info(jobs.next().toString());
    	}
    	
		logger.info("=============== Finished dst05b() ===============");
    }

    public static void dst05c() {
    	logger.info("=============== Starting dst05c() ===============");
    	
    	DBCollection collection = mongoDB.getCollection("finishedJobs");
    	
    	// emit the number 1 with key "elementName" for each member of this (except job_id, last_updated and _id)
    	String mapFunction = 
    			"function() { " +
    			"  for(element in this) { " +
    			"    if(element != 'job_id' && element != 'last_updated' && element != '_id') " +
    			"      emit(element, 1); " +
    			"  }" +
    			"} ";
    	// just return the length of the found array of values for each key
    	String reduceFunction = 
    			"function(key, values) { " +
    			"  return values.length; " +
    			"} ";
    	MapReduceOutput mapReduceOutput = collection.mapReduce(mapFunction, reduceFunction, null, OutputType.INLINE, null);
    	
    	logger.info("List of all existing top-level payload document properties:");
    	Iterable<DBObject> dbObjects = mapReduceOutput.results();
    	for(DBObject object : dbObjects) {
    		logger.info(object.toString());
    	}
    	
		logger.info("=============== Finished dst05c() ===============");
    }
        
    private static void initLogging() {
		logger = Logger.getLogger("dst_ss2012_ue1");
		logger.setUseParentHandlers(false);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new MyConsoleFormatter());
		logger.addHandler(handler);
    }
    
    private static void displayQueryResults(Query query) {
    	List<Object> resultList = query.getResultList();
    	int i = 0;
    	if(resultList.isEmpty())
    		logger.info("  Result list is empty.");
    	else
			for(Object result : resultList) {
				logger.info("  Row #" + i + " " + result);
				i++;
			}
    }
}
