package dst1.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Environment {
	@Id
	@GeneratedValue
	private Long id;
	private String workflow;
	@ElementCollection
	private List<String> params;
	
	public Environment(String workflow) {
		this.workflow = workflow;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWorkflow() {
		return workflow;
	}

	public void setWorkflow(String workflow) {
		this.workflow = workflow;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}
	
	public void addParam(String param) {
		if(params == null)
			params = new ArrayList<String>();
		params.add(param);
	}
}
