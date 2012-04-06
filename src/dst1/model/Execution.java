package dst1.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
	@OneToOne(optional=false, cascade={CascadeType.REMOVE})
	private Job job;
	@ManyToMany(mappedBy="executionList", cascade={CascadeType.REMOVE})
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
	
	public void removeComputer(Computer computer) {
		if(computerList != null) {
			computer.removeExecution(this);
			computerList.remove(computer);
		}
	}
	
	public void removeAllComputers() {
		for(Computer computer:computerList) {
			computer.removeExecution(this);
		}
		if(computerList != null)
			computerList.clear();
	}
	
	@Override
	public String toString() {
		String value = "[Execution id=" + id + ", " +
					"start=" + start + ", " +
					"end=" + end + ", " + 
					"status=" + status + ", " + 
					"job=" + (job != null ? job.getId() : null) + ", " +
					"computerList={";
		for(Computer computer:computerList) {
			value += computer.getId() + ",";
		}
		value = value.substring(0, value.length() - 1);
		value += "}]";
		return value;
	}
}
