
/**
* The ThreadingIPCheck class is a child of the Thread class, which allows for concurrent execution of IP checking.
* It takes in an IP address and uses it to check against websites specified in websiteList. 
* It implements a method run() that calls webOne() or webTwo() depending on the value of currentWebsite.
* It also implements a method setChecker(int i) that sets the currentWebsite to the passed in value, 
* and a method numOfChecks() that returns the number of websites in websiteList.
*
* @author Deeshan M
* @version 1.0
*/

public class ThreadingIPCheck extends Thread {
	
	/**
	 *  A private String variable that holds the IP address that is going to be checked.
	 */
	
	private String demandedIP;
	
	/**
	 *  A private array of String that holds the URLs of websites that are going to be used for checking IP address.
	 */
	
	private String websiteList[] = 
		{				
				"https://nordvpn.com/ip-lookup/",
				"https://www.ipqualityscore.com/free-ip-lookup-proxy-vpn-test/lookup/"
		};
	
	/**
	 * A private integer variable that holds the index of the current website that is being checked in the websiteList.
	 */
	
	private int currentWebsite;
	
	/**
	 *  The constructor for the ThreadingIPCheck class that takes in an IP address and sets the demandedIP field to it.
	 * 
	 * @param demandedIP  The IP address that is going to be checked.
	 */
	
	public ThreadingIPCheck(String demandedIP) {
		
		this.demandedIP = demandedIP;
	}
	
	/**
	 * The overridden run method of the Thread class. It checks the value of currentWebsite and calls the appropriate method.
	 */
	
	@Override
	public void run() {
		
		if (currentWebsite == 0) webOne();
		
		if (currentWebsite == 1) webTwo();
	}
	
	/**
	 * Method that returns the number of websites in websiteList.
	 * @return The number of websites in websiteList.
	 */
	
	public int numOfChecks() {
		
		return websiteList.length;
	}
	
	/**
	 * Method that sets the currentWebsite field to the passed in value.
	 * @param i The value that currentWebsite is going to be set to.
	 */
	
	public void setChecker(int i) {
		
		currentWebsite = i;
	}
	
	/**
	* A private method that performs the check of the IP address on websiteList[currentWebsite] using a BehaviorsForNordVPN instance.
	* The method starts by creating an instance of the BehaviorsForNordVPN class and calling the setup method on it with the 
	* websiteList[currentWebsite] as argument. Then it calls the enterIP method on the BehaviorsForNordVPN instance and passing 
	* the demandedIP as argument. Next, it performs a loop that calls the clickCheck method on the BehaviorsForNordVPN instance 
	* 10 times, this step is to simulate the user clicking the check button. After the loop it calls the getDetails method on 
	* the BehaviorsForNordVPN instance and finally it calls the finish method on the BehaviorsForNordVPN instance and passes the 
	* result to the toReceiver method of the DetailedCompression class.
	*
	*/
	
	private void webOne() {
		
		System.out.println("Running check at nordvpn...");
		
		BehaviorsForNordVPN performNordVPN = new BehaviorsForNordVPN();
		
		performNordVPN.setup(websiteList[currentWebsite]);
		performNordVPN.enterIP(demandedIP);
		performNordVPN.clickCheck();
		performNordVPN.getDetails();
		
		DetailedCompression.toReceiver(performNordVPN.finish());
	}
	
	/**
	* A private method that performs the check of the IP address on websiteList[currentWebsite] using a BehaviorsForIPQual instance.
	* The method starts by creating an instance of the BehaviorsForIPQual class and calling the setup method on it with the 
	* websiteList[currentWebsite] and demandedIP as arguments. Then it calls the getDetails method on the BehaviorsForIPQual 
	* instance, and finally it calls the finish method on the BehaviorsForIPQual instance and passes the result to the toReceiver
	* method of the DetailedCompression class.
	*/
	
	private void webTwo() {
		
		System.out.println("Running check at ipqualityscore...");
		
		BehaviorsForIPQual performIPQual = new BehaviorsForIPQual();
		
		performIPQual.setup(websiteList[currentWebsite], demandedIP);
		performIPQual.getDetails();
		
		DetailedCompression.toReceiver(performIPQual.finish());
	}
}
