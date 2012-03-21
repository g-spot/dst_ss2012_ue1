package dst1.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Person {
	@Id
	private Long id;
	private String firstName;
	private String lastName;
	@Embedded
	private Address address;
	
	/* InheritanceTypes:
	 * InheritanceType.SINGLE_TABLE
	 *   nur eine Tabelle "Person" mit einem Flag, ob es ein Admin oder ein User ist
	 *   Problem: geht nicht, weil somit auch User eine Relation zu Cluster bzw. Admins eine Relation zu Membership und Job bekommen
	 * InheritanceType.TABLE_PER_CLASS
	 *   2 Tabellen, "Admin" und "User", jede enthält alle geerbten Spalten aus Person sowie ihre spezifischen Spalten
	 *   außerdem sind die Relationen zu anderen Tabellen nur dort abgebildet, wo sie auch wirklich nötig sind
	 *   (Admin - Cluster, User - Membership, User-Job)
	 * InheritanceType.JOINED
	 *   3 Tabellen, "Person", "Admin" und "User", jede Tabelle enthält genau die Spalten seines Objekttyps, die Untertypen
	 *   Admin und User haben einen FK auf den PK von Person
	 *   ginge auch, jedoch nicht nötig, da ich keine Anforderung für eine Abfrage auf alle Personen identifizieren konnte
	 */
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}
