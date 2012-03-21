package dst1.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Entity
public class Computer {
	//@Id
	private Long id;
	//@Column(unique=true)
	private String name;
	private int cpus;
	private String location;
	private Date creation;
	private Date lastUpdate;
	//@ManyToMany
	private List<Execution> executionList;
	//@ManyToOne
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
