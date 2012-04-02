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
		//TODO md5??????
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		byte[] data = password.getBytes();
		messageDigest.update(data, 0, data.length);
		
		this.password = data;
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
	
	/*public static String MungPass(String pass) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		byte[] data = pass.getBytes(); 
		m.update(data,0,data.length);
		BigInteger i = new BigInteger(1,m.digest());
		return String.format("%1$032X", i);
	}*/
}
