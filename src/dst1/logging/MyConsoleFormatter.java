package dst1.logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyConsoleFormatter extends Formatter {

	@Override
	public String format(LogRecord rec) {
		StringBuffer buffer = new StringBuffer(1000);
		String sourceMethodName = (rec.getSourceMethodName().equals("displayQueryResults") ? "" : rec.getSourceMethodName());
		buffer.append("(" + calcDate(rec.getMillis()) + ")");
		buffer.append(" ");
		buffer.append("(" + sourceMethodName + ")" + whitespaces(sourceMethodName, 6));
		buffer.append(" ");
		buffer.append("(" + rec.getLevel().getName() + ")" + whitespaces(rec.getLevel().getName(), 8));
		//buffer.append(" ");
		buffer.append(rec.getMessage() + "\n");
		return buffer.toString();
	}
	
	private String calcDate(long millisecs)
	{
		SimpleDateFormat date_format = new SimpleDateFormat("HH:mm:ss");
		Date resultdate = new Date(millisecs);
		return date_format.format(resultdate);
	}
	
	private String whitespaces(String level, int maxLength) {
		String whitespaces = "";
		for(int i=level.length();i<maxLength;i++) {
			whitespaces += " ";
		}
		return whitespaces;
	}

}
