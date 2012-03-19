package dst1.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Computer {
	@Id
	private Long id;
	@Id
	private String name;
	private int cpus;
	private String location;
	private Date creation;
	private Date lastUpdate;
	private List<Execution> executionList;
	private Cluster cluster;
	
	public Computer() {
		this.executionList = new ArrayList<Execution>();
	}
}
