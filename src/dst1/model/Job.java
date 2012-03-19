package dst1.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	@OneToOne
	private Environment environment;
	@ManyToOne
	private User user;
	@OneToOne
	private Execution execution;
}
