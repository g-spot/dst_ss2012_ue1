package dst1.interceptor;

import org.hibernate.EmptyInterceptor;

public class SQLInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 3894614218727237142L;
	private static int selectCount = 0;

	public String onPrepareStatement(String sql) {
		if(sql.startsWith("select") && (sql.contains("Computer") || sql.contains("Execution")))
			selectCount++;

		return sql;
	}
	
	public static void resetCounter() {
		selectCount = 0;
	}

	public static String getStatistics() {
		return "Counted select statements for Computers and Executes: " + selectCount;
	}
}
