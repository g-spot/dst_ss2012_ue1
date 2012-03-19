package dst1.model;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Membership {
	@Id
	@Embedded
	private MembershipId id;
	private Date registration;
	private double discount;
}
