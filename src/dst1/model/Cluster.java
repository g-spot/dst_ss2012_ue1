package dst1.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cluster {
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique=true)
	private String name;
	private Date lastService;
	private Date nextService;
	@OneToMany(mappedBy="cluster")
	private List<Computer> computerList;
	@ManyToMany(mappedBy="parentClusterList")
	private List<Cluster> childClusterList;
	@ManyToMany
	private List<Cluster> parentClusterList;
	@ManyToOne(optional=false)
	private Grid grid;
	@ManyToOne(optional=false)
	private Admin admin;
	
	public Cluster() {
		this.computerList = new ArrayList<Computer>();
		this.childClusterList = new ArrayList<Cluster>();
		this.parentClusterList = new ArrayList<Cluster>();
	}
	
	public Cluster(String name, Date lastService, Date nextService) {
		this.name = name;
		this.lastService = lastService;
		this.nextService = nextService;
		this.computerList = new ArrayList<Computer>();
		this.childClusterList = new ArrayList<Cluster>();
		this.parentClusterList = new ArrayList<Cluster>();
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

	public Date getLastService() {
		return lastService;
	}

	public void setLastService(Date lastService) {
		this.lastService = lastService;
	}

	public Date getNextService() {
		return nextService;
	}

	public void setNextService(Date nextService) {
		this.nextService = nextService;
	}

	public List<Computer> getComputerList() {
		return computerList;
	}

	public void addComputer(Computer computer) {
		if(computerList == null)
			computerList = new ArrayList<Computer>();
		if(!computerList.contains(computer)) {
			computer.setCluster(this);
			computerList.add(computer);
		}
	}

	public List<Cluster> getChildClusterList() {
		return childClusterList;
	}

	public void addChildCluster(Cluster cluster) {
		if(childClusterList == null)
			childClusterList = new ArrayList<Cluster>();
		if(!childClusterList.contains(cluster)) {
			cluster.addParentCluster(this);
			childClusterList.add(cluster);
		}
	}
	
	public List<Cluster> getParentClusterList() {
		return parentClusterList;
	}

	public void addParentCluster(Cluster cluster) {
		if(parentClusterList == null)
			parentClusterList = new ArrayList<Cluster>();
		if(!parentClusterList.contains(cluster)) {
			parentClusterList.add(cluster);
		}
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void setComputerList(List<Computer> computerList) {
		this.computerList = computerList;
	}

	public void setChildClusterList(List<Cluster> childClusterList) {
		this.childClusterList = childClusterList;
	}

	public void setParentClusterList(List<Cluster> parentClusterList) {
		this.parentClusterList = parentClusterList;
	}
}
