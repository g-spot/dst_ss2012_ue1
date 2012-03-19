package dst1.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String street;
	private String city;
	private String zipCode;
}
