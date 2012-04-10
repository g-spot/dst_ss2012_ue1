package dst1.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@NamedQuery(name="findJobsByStatus",
			query="SELECT OBJECT(j) " +
				  "  FROM Job j " + 
				  " WHERE j.execution.status=:jobStatus")
public class Job {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private boolean isPaid;
	
	@SuppressWarnings("unused")
	@Transient
	private int numCpus;
	@SuppressWarnings("unused")
	@Transient
	private int executionTime;
	
	@OneToOne(optional=false, cascade={CascadeType.REMOVE})
	private Environment environment;
	@ManyToOne(optional=false)
	private User user;
	@OneToOne(optional=false, mappedBy="job")
	private Execution execution;
	
	public Job(boolean isPaid) {
		this.isPaid = isPaid;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNumCpus() {
		int numCpus = 0;
		if(this.execution == null)
			return 0;
		for(Computer computer:this.execution.getComputerList()) {
			numCpus += computer.getCpus();
		}
		return numCpus;
	}
	public int getExecutionTime() {
		if(this.execution == null)
			return 0;
		if(this.execution.getStart() == null || this.execution.getEnd() == null)
			return 0;
		
		return (int)(this.execution.getEnd().getTime() - this.execution.getStart().getTime());
	}
	public boolean isPaid() {
		return isPaid;
	}
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}
	public Environment getEnvironment() {
		return environment;
	}
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Execution getExecution() {
		return execution;
	}
	public void setExecution(Execution execution) {
		this.execution = execution;
	}
	
	@Override
	public String toString() {
		return  "[Job id=" + id + ", " +
				"numCpus=" + getNumCpus() + ", " +
				"executionTime=" + getExecutionTime() + ", " +
				"isPaid=" + isPaid + ", " +
				"user=" + (user != null ? user.getId() : "n.a.") + ", " +
				"execution=" + (execution != null ? execution.getId() : "n.a.") + ", " +
				"environment=" + (environment != null ? environment.getId() : "n.a.") + "]";
	}
}
