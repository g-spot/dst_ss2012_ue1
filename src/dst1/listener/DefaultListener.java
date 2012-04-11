package dst1.listener;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DefaultListener {
	private static int loadCount = 0;
	private static int updateCount = 0;
	private static int removeCount = 0;
	private static int persistCount = 0;
	private static long persistTime = 0;
	private static Map<Integer,Date> persistedObjectStartTime = new HashMap<Integer,Date>();
	
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

	@PrePersist
	synchronized void prePersist(Object o) {
		persistedObjectStartTime.put(o.hashCode(), new Date());
	}
	
	@PostPersist
	synchronized void postPersist(Object o) {
		persistCount++;
		Date endTime = new Date();
		Date startTime = persistedObjectStartTime.get(o.hashCode());
		if(startTime != null) {
			persistedObjectStartTime.remove(o.hashCode());
			persistTime += (endTime.getTime() - startTime.getTime());
		}
	}
	
	public static String getAccessStatistics() {
		return "Load-Operations: " + loadCount + "\n" +
				"Update-Operations: " + updateCount + "\n" +
				"Remove-Operations: " + removeCount + "\n\n" +
				"Persist-Operations: " + persistCount + "\n" + 
				"Overall time to persist: " + persistTime + " ms\n" + 
				"Average time to persist: " + (persistCount != 0 ? (1.0 * persistTime / persistCount) : 0) + " ms";
	}
	
	public static synchronized int getLoadCount() {
		return loadCount;
	}

	public static synchronized int getUpdateCount() {
		return updateCount;
	}

	public static synchronized int getRemoveCount() {
		return removeCount;
	}

	public static synchronized int getPersistCount() {
		return persistCount;
	}

	public static synchronized long getPersistTime() {
		return persistTime;
	}

	public static synchronized Map<Integer, Date> getPersistedObjectStartTime() {
		return persistedObjectStartTime;
	}
}
