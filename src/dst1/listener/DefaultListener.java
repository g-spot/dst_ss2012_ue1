package dst1.listener;

import javax.persistence.PostLoad;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

public class DefaultListener {
	private static int loadCount = 0;
	private static int updateCount = 0;
	private static int removeCount = 0;
	
	@PostLoad
	synchronized void onLoad(Object o) {
		loadCount++;
	}
	
	@PostUpdate
	synchronized void onUpdate(Object o) {
		updateCount++;
	}
	
	@PostRemove
	synchronized void onRemove(Object o) {
		removeCount++;
	}
	
	//TODO count persist-operations and persist time
	
	public static String getAccessStatistics() {
		return "Load-Operations: " + loadCount + "\n" +
				"Update-Operations: " + updateCount + "\n" +
				"Remove-Operations: " + removeCount;
	}
}
