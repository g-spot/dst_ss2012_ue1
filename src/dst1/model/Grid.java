package dst1.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Grid {
	@Id
	private Long id;
	@Column(unique=true)
	private String name;
	private String location;
	private BigDecimal costPerCpuMinute;
	@OneToMany
	private List<Membership> membershipList;
	@OneToMany
	private List<Cluster> clusterList;
	
	public Grid() {
		this.membershipList = new ArrayList<Membership>();
		this.clusterList = new ArrayList<Cluster>();
	}
}
