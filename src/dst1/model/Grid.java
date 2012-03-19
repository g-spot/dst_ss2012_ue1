package dst1.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Grid {
	@Id
	private Long id;
	@Id
	private String name;
	private String location;
	private BigDecimal costPerCpuMinute;
	private List<Membership> membershipList;
	private List<Cluster> clusterList;
	
	public Grid() {
		this.membershipList = new ArrayList<Membership>();
		this.clusterList = new ArrayList<Cluster>();
	}
}
