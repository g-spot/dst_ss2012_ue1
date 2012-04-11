package dst1.query;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import dst1.model.Execution;
import dst1.model.Job;
import dst1.model.JobStatus;

public class JobQueries {
	
	public static List<Job> findJobsByUserAndWorkflow(EntityManager em, String username, String workflow) {
		Session session = (Session)em.getDelegate();
		Criteria criteria = session.createCriteria(Job.class);
		
		if(username != null) {
			Criteria userCriteria = criteria.createCriteria("user");
			userCriteria.add(Restrictions.eq("username", username));
		}
		
		if(workflow != null) {
			Criteria workflowCriteria = criteria.createCriteria("environment");
			workflowCriteria.add(Restrictions.eq("workflow", workflow));
		}
		
		return criteria.list();
	}
	
	public static List<Job> findFinishedJobsByStartAndEndDate(EntityManager em, Date startDate, Date endDate) {
		Session session = (Session)em.getDelegate();
		Criteria criteria = session.createCriteria(Job.class);
		
		Execution executionPattern = new Execution(startDate, endDate, JobStatus.FINISHED);
		Example executionExample = Example.create(executionPattern);
		executionExample.excludeProperty("computerList");
		
		Job jobPattern = new Job(true);
		executionPattern.setJob(jobPattern);
		Example jobExample = Example.create(jobPattern);
		jobExample.excludeProperty("isPaid");
		jobExample.excludeProperty("environment");
		jobExample.excludeProperty("user");
		
		criteria.add(jobExample);
		
		Criteria executionCriteria = criteria.createCriteria("execution");
		executionCriteria.add(executionExample);
		
		return criteria.list();
	}
}
