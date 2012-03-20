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
import javax.persistence.UniqueConstraint;

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
}
