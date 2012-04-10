package dst1.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
	@OneToMany(mappedBy="id.grid", cascade={CascadeType.REMOVE})
	private List<Membership> membershipList;
	@OneToMany(mappedBy="grid", cascade={CascadeType.REMOVE})
	private List<Cluster> clusterList;
	
	public Grid() {
		this.membershipList = new ArrayList<Membership>();
		this.clusterList = new ArrayList<Cluster>();
	}
	
	public Grid(String name, String location, BigDecimal costPerCpuMinute) {
		this.name = name;
		this.location = location;
		this.costPerCpuMinute = costPerCpuMinute;
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

	public void addCluster(Cluster cluster) {
		if(clusterList == null)
			clusterList = new ArrayList<Cluster>();
		if(!clusterList.contains(cluster)) {
			cluster.setGrid(this);
			clusterList.add(cluster);
		}
	}
	
	public void addMembership(Membership membership) {
		if(membershipList == null)
			membershipList = new ArrayList<Membership>();
		if(!membershipList.contains(membership))
			membershipList.add(membership);
	}
	
	@Override
	public String toString() {
		String value = "[Grid id=" + id + ", " +
					"name=" + name + ", " +
					"location=" + location + ", " + 
					"costPerCpuMinute=" + costPerCpuMinute + ", " + 
					"clusterList={";
		if(clusterList != null && !clusterList.isEmpty()) {
			for(Cluster cluster:clusterList) {
				value += cluster.getId() + ",";
			}
			value = value.substring(0, value.length() - 1);
		}
		value += "}, ";
		value +=	"membershiplist={";
		if(membershipList != null)
			for(Membership membership:membershipList) {
				//value += "(U" + membership.getId().getUser().getId() + ",G" + membership.getId().getGrid().getId() + "),";
				value += membership.getId().toString() + ",";
			}
		value = value.substring(0, value.length() - 1);
		value += "}]";
		return value;
	}
}
