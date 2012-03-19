package dst1.model;

import javax.persistence.Embeddable;

@Embeddable
public class MembershipId {
	private User user;
	private Grid grid;
}
