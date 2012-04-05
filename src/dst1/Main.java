package dst1;

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
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;



import dst1.logging.MyConsoleFormatter;
import dst1.model.*;

public class Main {
	
	private static Logger logger;

	private Main() {
		super();
	}

	public static void main(String[] args) {
		logger = Logger.getLogger("dst_ss2012_ue1");
		
		logger.setUseParentHandlers(false);
		
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new MyConsoleFormatter());
		logger.addHandler(handler);
		for(Handler h : logger.getHandlers()) {
			System.out.println(h);
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
		
		logger.info("Creating entity manager");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("grid");
		EntityManager em = emf.createEntityManager();
		
		logger.info("Creating test admin");
		Admin admin1 = new Admin("Gerhard", "Schraml", null);
		
		Admin admin2 = new Admin("Max", "Mustermann", new Address("Main Street 1", "Washington D.C.", "1234"));
		
		try {
			em.getTransaction().begin();
			
			em.persist(admin1);
			em.persist(admin2);
			
			Admin admin = em.find(Admin.class, 1l);
			if(admin != null)
				logger.severe("<" + admin.getId() + "><" + admin.getFirstName() + "><" + admin.getLastName() + ">");
			else
				logger.info("Admin not found");
			
			Query query = em.createQuery("SELECT a FROM Admin a");
			for(Object result : query.getResultList()) {
				admin = (Admin)result;
				logger.warning("<" + admin.getId() + "><" + admin.getFirstName() + "><" + admin.getLastName() + ">");
			}
			
			em.getTransaction().commit();
		} catch(Exception e) {
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
}
