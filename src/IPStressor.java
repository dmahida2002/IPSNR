
public class IPStressor {
	
	public static void main(String[] args) {
		
		/*
		 *  + Implement JFrame or better GUi
		 *  + Add IP Checker for VPn, bot, and give it a score (from website).
		 * 	+ Organize data provided from checker
		 */
		
		String IP = "8.8.8.8";
		
		Command askTo = new Command(IP);
		
		askTo.checkIP();
    }
}