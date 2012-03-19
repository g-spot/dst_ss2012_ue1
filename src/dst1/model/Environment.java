package dst1.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Environment {
	@Id
	private Long id;
	private String workflow;
	private List<String> params;
	
	public Environment() {
		this.params = new ArrayList<String>();
	}
}
