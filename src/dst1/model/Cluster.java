package dst1.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cluster {
	@Id
	private Long id;
	@Id
	private String name;
	private Date lastService;
	private Date nextService;
	private List<Computer> computerList;
	private List<Cluster> clusterList;
	private Grid grid;
	private Admin admin;
	
	public Cluster() {
		this.computerList = new ArrayList<Computer>();
		this.clusterList = new ArrayList<Cluster>();
	}
}
