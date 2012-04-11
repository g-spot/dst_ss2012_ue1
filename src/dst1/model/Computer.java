package dst1.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityListeners;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import dst1.validator.CPUs;

@EntityListeners(dst1.listener.ComputerListener.class)
@NamedQuery(name="findComputersInVienna",
			query="SELECT OBJECT(c) " + 
				  "  FROM Computer c LEFT JOIN FETCH c.executionList " + 
				  " WHERE c.location LIKE 'AUT-VIE%' " +
				  " GROUP BY c")
public class Computer {

	private Long id;
	@Size(min=5,max=25)
	private String name;
	@CPUs(min=4,max=8)
	private int cpus;
	@Pattern(regexp="[A-Z]{3}[-][A-Z]{3}[@][0-9]{4}")
	private String location;
	@Past
	private Date creation;
	@Past
	private Date lastUpdate;
	private List<Execution> executionList;
	private Cluster cluster;
	
	public Computer() {
		this.executionList = new ArrayList<Execution>();
	}
	
	public Computer(String name, int cpus, String location, Date creation, Date lastUpdate) {
		this.name = name;
		this.cpus = cpus;
		this.location = location;
		this.creation = creation;
		this.lastUpdate = lastUpdate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCpus() {
		return cpus;
	}

	public void setCpus(int cpus) {
		this.cpus = cpus;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<Execution> getExecutionList() {
		return executionList;
	}
	
	public void setExecutionList(List<Execution> executionList) {
		this.executionList = executionList;
	}

	public void addExecution(Execution execution) {
		if(executionList == null)
			executionList = new ArrayList<Execution>();
		executionList.add(execution);
	}
	
	public void removeExecution(Execution execution) {
		if(executionList != null)
			executionList.remove(execution);
	}
	
	public void removeAllExecutions() {
		for(Execution execution:executionList) {
			if(execution.getComputerList() != null)
				execution.getComputerList().remove(this);
		}
		executionList.clear();
	}

	public Cluster getCluster() {
		return cluster;
	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}
	
	public void removeCluster(Cluster cluster) {
		if(cluster != null)
			cluster.removeComputer(this);
	}
	
	@Override
	public String toString() {
		String value = "[Computer id=" + id + ", " +
					"name=" + name + ", " +
					"cpus=" + cpus + ", " + 
					"location=" + location + ", " + 
					"creation=" + creation + ", " +
					"lastUpdate=" + lastUpdate + ", " + 
					"cluster=" + (cluster != null ? cluster.getId() : null) + ", " +
					"executionList={";
		if(executionList != null && !executionList.isEmpty()) {
			for(Execution execution:executionList) {
				value += execution.getId() + ",";
			}
			value = value.substring(0, value.length() - 1);
		}
		value += "}]";
		return value;
	}
}
