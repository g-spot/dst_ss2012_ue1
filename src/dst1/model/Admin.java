package dst1.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Admin extends Person {

	@OneToMany
	private List<Cluster> clusterList;
	
	public Admin(String firstName, String lastName, Address address) {
		super(firstName, lastName, address);
	}

	public List<Cluster> getClusterList() {
		return clusterList;
	}

	public void setClusterList(List<Cluster> clusterList) {
		this.clusterList = clusterList;
	}
}
