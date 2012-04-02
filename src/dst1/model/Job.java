package dst1.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Job {
	@Id
	private Long id;
	@Transient
	private int numCpus;
	@Transient
	private int executionTime;
	private boolean isPaid;
	@OneToOne
	private Environment environment;
	@ManyToOne
	private User user;
	@OneToOne
	private Execution execution;
	
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
}
