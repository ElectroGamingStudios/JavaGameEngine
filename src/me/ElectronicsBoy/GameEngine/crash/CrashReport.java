package me.ElectronicsBoy.GameEngine.crash;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;

public class CrashReport {
	
	Calendar cal = Calendar.getInstance();
	File dir = new File("crashReports");
	File textFile = new File("crashReports", "CrashReport_" + cal.get(Calendar.DATE) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.YEAR) + " " + cal.get(Calendar.HOUR_OF_DAY) + "-" + cal.get(Calendar.MINUTE) + "-" + cal.get(Calendar.SECOND) + ".txt");
	
	public void createCrashReport(Throwable error, Thread thread) {
		StringWriter stackTrace = new StringWriter();
		PrintWriter pw = new PrintWriter(stackTrace);
		error.printStackTrace(pw);
		
		String systemSection =   "OS              : " + System.getProperty("os.name") 
		                       + "\n"
		                       + "OS Version      : " + System.getProperty("os.version")
							   + "\n"
							   + "OS Architecture : " + System.getProperty("os.arch")
							   + "\n"
							   + "Current Dir     : " + System.getProperty("user.dir");
		
		String head = "Game Crash Report\n"
				+     "-----------------\n";
		String body = "All known info is shown below\n"
				+     "-----------------------------\n"
				+     "StackTrace:\n"
				+     stackTrace + "\n"
				+     "Caused by:\n"
				+     error.getCause() + "\n\n"
			    +     "Message:\n"
				+     error.getMessage()
				+     "\n"
				+     "Current Thread:\n"
				+     thread.getName()
				+     "\n\n"
				+ 	  "System Details: \n"
				+	  systemSection
				+     "\n\n"
				+	  "#@!Game has crashed! Crash Report saved to " + textFile.getAbsolutePath() + "!@#";
		
		System.out.println(head + body);
		this.writeToFile(head, body);
	}
	
	private void writeToFile(String head, String body) {
		try {
			if(!dir.exists())
				dir.mkdir();
			textFile.createNewFile();
			
			PrintWriter printWriter = new PrintWriter(textFile);
			printWriter.println(head);
			printWriter.println(body);
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
