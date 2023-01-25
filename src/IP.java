
/**
* The IP class is the main class of the program, which creates a new instance of the Command class and
* passes in an IP address to be checked. It then calls the checkIP() method on the Command instance,
* and prints the result of the getIPDetails() method.
*
* @author Deeshan M
* @version 1.0
*/

public class IP {
	
	/**
	* The main method of the program, which creates a new instance of the Command class and
	* passes in an IP address to be checked. It then calls the checkIP() method on the Command instance,
	* and prints the result of the getIPDetails() method.
	*
	* @param args command line arguments (unused)
	*/
	
	public static void main(String[] args) {
		
		/*
		 *  + Implement JFrame or better GUi
		 *  + Add IP Checker for VPn, bot, and give it a score (from website).
		 * 	+ Organize data provided from checker
		 * 	+ JavaDoc comments
		 * 	+ Repo description
		 * 	+ README file
		 */
		
		String IP = "8.8.8.8";
		
		Command askTo = new Command(IP);
		
		askTo.checkIP();
		
		System.out.println("\n" + askTo.getIPDetails());
    }
}
