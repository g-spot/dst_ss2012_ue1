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
	@OneToMany
	private List<Computer> computerList;
	@ManyToMany
	private List<Cluster> childClusterList;
	@ManyToMany
	private List<Cluster> parentClusterList;
	@ManyToOne(optional=false)
	private Grid grid;
	@ManyToOne
	private Admin admin;
	
	public Cluster() {
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

	public void setComputerList(List<Computer> computerList) {
		this.computerList = computerList;
	}

	public List<Cluster> getChildClusterList() {
		return childClusterList;
	}

	public void setChildClusterList(List<Cluster> childClusterList) {
		this.childClusterList = childClusterList;
	}
	
	public List<Cluster> getParentClusterList() {
		return parentClusterList;
	}

	public void setParentClusterList(List<Cluster> parentClusterList) {
		this.parentClusterList = parentClusterList;
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
}
