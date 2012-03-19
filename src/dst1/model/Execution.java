package dst1.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Execution {
	@Id
	private Long id;
	private Date start;
	private Date end;
	private JobStatus status;
	private Job job;
	private List<Computer> computerList;
	
	public Execution() {
		this.computerList = new ArrayList<Computer>();
	}
}
