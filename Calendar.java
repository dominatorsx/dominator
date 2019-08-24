import java.util.Date;
import java.util.concurrent.TimeUnit;
//Authored by Tharindu

public class Calendar {
	//change the variable
	private static Calendar self;
	//change the variable
	private static java.util.Calendar calendar;
	
	
	private calendar() {
		//change the variable
		calendar = java.util.Calendar.getInstance();
	}
	
	public static Calendar INSTANCE() {
		//change the variable
		if (self == null) {
			//change the variable
			self = new Calendar();
		}
		//change the variable
		return self;
	}
	
	public void incrementDate(int days) {
		//change the variable
		calendar.add(java.util.Calendar.DATE, days);		
	}
	//change the method name
	public synchronized void setDate(Date date) {
		try {
			//change the variable names
			calendar.setTime(date);
	        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        calendar.set(java.util.Calendar.MINUTE, 0);  
	        calendar.set(java.util.Calendar.SECOND, 0);  
	        calendar.set(java.util.Calendar.MILLISECOND, 0);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date Date() {
		try {
			//change the variables
	        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        calendar.set(java.util.Calendar.MINUTE, 0);  
	        calendar.set(java.util.Calendar.SECOND, 0);  
	        calendar.set(java.util.Calendar.MILLISECOND, 0);
			return calendar.getTime();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date Due_Date(int loanPeriod) {
		//change the variable
		Date now = Date();
		//change the variable
		calendar.add(java.util.Calendar.DATE, loanPeriod);
		//change the variable
		Date dueDate = calendar.getTime();
		//change the variable
		calendar.setTime(now);
		//change the variable
		return dueDate;
	}
	
	public synchronized long Get_Days_Difference(Date targetDate) {
		
		long Diff_Millis = Date().getTime() - targetDate.getTime();
	    long Diff_Days = TimeUnit.DAYS.convert(Diff_Millis, TimeUnit.MILLISECONDS);
	    return Diff_Days;
	}

}
