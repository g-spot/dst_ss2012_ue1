package dst1.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cluster {
	@Id
	private Long id;
	@Column(unique=true)
	private String name;
	private Date lastService;
	private Date nextService;
	@OneToMany
	private List<Computer> computerList;
	@ManyToMany
	private List<Cluster> clusterList;
	@ManyToOne
	private Grid grid;
	@ManyToOne
	private Admin admin;
	
	public Cluster() {
		this.computerList = new ArrayList<Computer>();
		this.clusterList = new ArrayList<Cluster>();
	}
}
