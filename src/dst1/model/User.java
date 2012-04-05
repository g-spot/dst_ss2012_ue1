package dst1.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"accountNo","bankCode"}))
public class User extends Person {
	@Column(unique=true,nullable=false)
	private String username;
	@Column(length=32, columnDefinition="BINARY(32)")
	private byte[] password;
	private String accountNo;
	private String bankCode;
	@OneToMany
	private List<Job> jobList;
	@OneToMany
	private List<Membership> membershipList;
	
	public User() {
		this.membershipList = new ArrayList<Membership>();
	}
	
	public User(String firstName, String lastName, Address address) {
		super(firstName, lastName, address);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.reset();
		messageDigest.update(password.getBytes());
		this.password = messageDigest.digest();
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public List<Job> getJobList() {
		return jobList;
	}

	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}

	public List<Membership> getMembershipList() {
		return membershipList;
	}

	public void setMembershipList(List<Membership> membershipList) {
		this.membershipList = membershipList;
	}
	
	public boolean comparePassword(String enteredPassword) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.reset();
		messageDigest.update(enteredPassword.getBytes());
		
		if(this.password.equals(messageDigest.digest()))
			return true;
		return false;
	}
}
