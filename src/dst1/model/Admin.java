package dst1.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Admin extends Person {

	@OneToMany(mappedBy="admin")
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

	public void addCluster(Cluster cluster) {
		if(clusterList == null)
			clusterList = new ArrayList<Cluster>();
		if(!clusterList.contains(cluster)) {
			cluster.setAdmin(this);
			clusterList.add(cluster);
		}
	}
}
