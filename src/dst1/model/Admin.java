package dst1.model;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {
	@Id
	private Long id;
	private String firstName;
	private String lastName;
	private List<Cluster> clusterList;
	@Embedded
	private Address address;
}
