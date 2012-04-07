package dst1.listener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import dst1.model.Computer;

public class ComputerListener {
	@PrePersist
	public void onPersist(Computer computer) {
		Date now = new Date();
		computer.setCreation(now);
		computer.setLastUpdate(now);
	}
	
	@PreUpdate
	public void onUpdate(Computer computer) {
		computer.setLastUpdate(new Date());
	}
}
