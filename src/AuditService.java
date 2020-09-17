import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class AuditService {
	 private static AuditService single_instance = null; 
	 private FileWriter myWriter;
	 
	 private AuditService() {
		 try {
			 File auditFile = new File("audit.csv");
			 myWriter = new FileWriter("audit.csv", true);
			 if (auditFile.createNewFile()) {
				 myWriter.write("action_name,timestamp\n");
			 }
		 } catch (IOException e) {
			 System.out.println("An error occurred with the audit file.");
			 e.printStackTrace();
		 }
	 }
	  
	 public static AuditService getInstance() { 
		 if (single_instance == null) 
			 single_instance = new AuditService(); 
		 return single_instance;
	 }
	 
	 public void logAction(String action) {
		 try {
			myWriter.write(action + "," + (new Timestamp(System.currentTimeMillis()).toInstant()) + '\n');
		} catch (IOException e) {
			System.out.println("An error occurred with the audit process.");
			e.printStackTrace();
		}
	 }
	 
	 public void closeAudit() {
		 try {
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred with the clossing the audit process.");
			e.printStackTrace();
		}
	 }
}
