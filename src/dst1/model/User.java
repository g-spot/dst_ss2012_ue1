package dst1.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	private byte[] password;
	@Embedded
	private Address address;
	private List<Job> jobList;
	private List<Membership> membershipList;
	
	public User() {
		this.membershipList = new ArrayList<Membership>();
	}
}
