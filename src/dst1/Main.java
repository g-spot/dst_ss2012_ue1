package dst1;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
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



import dst1.logging.MyConsoleFormatter;
import dst1.model.*;

public class Main {
	
	private static Logger logger;
	private static EntityManager em;

	private Main() {
		super();
	}

	public static void main(String[] args) {
		initLogging();
		
		logger.info("Creating entity manager");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("grid");
		em = emf.createEntityManager();
		logger.info("Done.");
		
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
		//dst03();
		dst04a();
		dst04b();
		dst04c();
		dst04d();
		dst05a();
		dst05b();
		dst05c();
	}

	public static void dst01() {
		logger.info("Starting dst01()");
		
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
		Membership membership4 = new Membership(grid1, user4, new Date(), 0.4);
		
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
		cluster1.addComputer(computer1);
		cluster2.addComputer(computer3);
		cluster2.addComputer(computer2);
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
		job1.setEnvironment(environment1);
		job2.setEnvironment(environment2);
		job3.setEnvironment(environment3);
		job4.setEnvironment(environment4);
		user1.addJob(job1);
		user2.addJob(job2);
		user3.addJob(job3);
		user4.addJob(job4);
		
		// create executions
		Execution execution1 = new Execution(new Date(), null, JobStatus.RUNNING);
		Execution execution2 = new Execution(new Date(), null, JobStatus.SCHEDULED);
		Execution execution3 = new Execution(new Date(), new Date(), JobStatus.FAILED);
		Execution execution4 = new Execution(new Date(), new Date(), JobStatus.FINISHED);
		execution1.setJob(job1);
		execution2.setJob(job2);
		execution3.setJob(job3);
		execution4.setJob(job4);
		execution1.addComputer(computer1);
		execution2.addComputer(computer2);
		execution3.addComputer(computer3);
		execution4.addComputer(computer4);
		
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
			logger.info("Done.");
			
			logger.info("Persisting jobs...");
			em.persist(job1);
			em.persist(job2);
			em.persist(job3);
			em.persist(job4);
			logger.info("Done.");
			
			logger.info("Persisting executions...");
			em.persist(execution1);
			em.persist(execution2);
			em.persist(execution3);
			em.persist(execution4);
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
			
			
			
			/*Admin admin = em.find(Admin.class, 1l);
			if(admin != null)
				logger.severe("<" + admin.getId() + "><" + admin.getFirstName() + "><" + admin.getLastName() + ">");
			else
				logger.info("Admin not found");
			
			Query query = em.createQuery("SELECT a FROM Admin a");
			for(Object result : query.getResultList()) {
				admin = (Admin)result;
				logger.warning("<" + admin.getId() + "><" + admin.getFirstName() + "><" + admin.getLastName() + ">");
			}*/
			
			logger.info("Committing transaction...");
			em.getTransaction().commit();
			logger.info("Done.");
		} catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			logger.severe(e.getMessage());
		}

		logger.info("Finished dst01()");
	}

	public static void dst02a() {

	}

	public static void dst02b() {

	}

	public static void dst02c() {

	}

	public static void dst03() {
		logger.info("Starting dst03()");
		
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
		
		logger.info("Finished dst03()");
	}

	public static void dst04a() {

	}

	public static void dst04b() {
		logger.info("Starting dst04b()");
		
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
		
		logger.info("Finished dst04b()");
	}

	public static void dst04c() {

	}

	public static void dst04d() {

	}

        public static void dst05a() {

        }

        public static void dst05b() {

        }

        public static void dst05c() {

        }
        
    private static void initLogging() {
		logger = Logger.getLogger("dst_ss2012_ue1");
		logger.setUseParentHandlers(false);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new MyConsoleFormatter());
		logger.addHandler(handler);
    }
}
