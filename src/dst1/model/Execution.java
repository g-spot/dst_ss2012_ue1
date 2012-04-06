package dst1.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Execution {
	@Id
	@GeneratedValue
	private Long id;
	private Date start;
	private Date end;
	private JobStatus status;
	@OneToOne(optional=false)
	private Job job;
	@ManyToMany(mappedBy="executionList")
	private List<Computer> computerList;
	
	public Execution(Date start, Date end, JobStatus status) {
		this.start = start;
		this.end = end;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public JobStatus getStatus() {
		return status;
	}

	public void setStatus(JobStatus status) {
		this.status = status;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		job.setExecution(this);
		this.job = job;
	}

	public List<Computer> getComputerList() {
		return computerList;
	}
	
	public void setComputerList(List<Computer> computerList) {
		this.computerList = computerList;
	}

	public void addComputer(Computer computer) {
		if(computerList == null)
			computerList = new ArrayList<Computer>();
		if(!computerList.contains(computer)) {
			computer.addExecution(this);
			computerList.add(computer);
		}
	}
}
