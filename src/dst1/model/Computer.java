package dst1.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import dst1.validator.CPUs;

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

	public Cluster getCluster() {
		return cluster;
	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}
}
