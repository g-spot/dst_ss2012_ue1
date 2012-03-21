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
	
	public MembershipId getId() {
		return id;
	}
	public void setId(MembershipId id) {
		this.id = id;
	}
	public Date getRegistration() {
		return registration;
	}
	public void setRegistration(Date registration) {
		this.registration = registration;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
}
