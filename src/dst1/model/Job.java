package dst1.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Job {
	@Id
	private Long id;
	@Transient
	private int numCpus;
	@Transient
	private int executionTime;
	private boolean isPaid;
	private Environment environment;
	private User user;
}
