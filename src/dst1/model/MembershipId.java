package dst1.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class MembershipId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5394067147892943838L;
	
	@ManyToOne
	private User user;
	@ManyToOne
	private Grid grid;
	
	public MembershipId(Grid grid, User user) {
		this.grid = grid;
		this.user = user;
	}
	
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Grid getGrid() {
		return grid;
	}
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
}
