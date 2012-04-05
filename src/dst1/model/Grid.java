package dst1.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Grid {
	@Id
	@GeneratedValue
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public BigDecimal getCostPerCpuMinute() {
		return costPerCpuMinute;
	}

	public void setCostPerCpuMinute(BigDecimal costPerCpuMinute) {
		this.costPerCpuMinute = costPerCpuMinute;
	}

	public List<Membership> getMembershipList() {
		return membershipList;
	}

	public void setMembershipList(List<Membership> membershipList) {
		this.membershipList = membershipList;
	}

	public List<Cluster> getClusterList() {
		return clusterList;
	}

	public void setClusterList(List<Cluster> clusterList) {
		this.clusterList = clusterList;
	}
}
