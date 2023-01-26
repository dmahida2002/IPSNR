import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * The Command class is the main class that contains the main methods that are used to check an IP 
 * address and retrieve the details of the IP address.It contains a private 
 * static variables IP, ISP, country, state, innerRegion, risk which holds the details of the IP address.
 * It also contains constructors, checkIP method, setIPDetails method, getIPDetails 
 * method, and getters for the private variables.
 * The checkIP method is used to check the IP address by instantiating an 
 * object of the ThreadingIPCheck class and creating multiple threads using the Thread class.
 * The setIPDetails method is used to set the values of the private variables with the details of the IP address.
 * The getIPDetails method is used to retrieve the details of the IP address in the form of a String.
 * The getters are used to get the values of the private variables.
 * @author Deeshan M
 * @version 1.0
*/

public class Command {
	
	private static String IP;
	private static String ISP;
	private static String country;
	private static String state;
	private static String innerRegion;
	private static String risk;
	
	/**
	 * Constructor with IP
	 * @param IP the IP address
	 */
	
	public Command(String IP) {
		
		Command.IP = IP;
	}
	
	/**
	 * Empty constructor
	 */
	
	public Command() {
		
		// Empty constructor
	}
	
	/**
	 * The method checkIP() performs multi-threading IP checks on the IP address passed to the class.
	 * It starts by instantiating an initial object of the ThreadingIPCheck class, which is used to 
	 * determine the number of checks needed.
	 * Next, it declares an object array to hold all of the threads that will be created. 
	 * The size of the array will be determined by the numOfChecks() method.
	 * The array is then filled with ThreadingIPCheck objects, and each thread is started. Finally, the 
	 * last thread is joined to wait for it to complete.
	*/
	
	public void checkIP() {
		
		/* 
		 * Instantiate a initial object of the IPChecker thread. This is to get all of the info we need
		 * such as numOfCheck.
		 */
		
		ThreadingIPCheck ripIPChecker = new ThreadingIPCheck(IP);
		
		/* 
		 * Declare a object to hold all of the threads we need. The size of the object
		 * will be determined by numOfCheck() method.
		 */
		
		Object threadHolder[] = new Object[ripIPChecker.numOfChecks()];
		
		/* 
		 * Fill all elements with the ThreadRipperIPCheck class.
		 * We need this many threads because a single thread will be in a state of
		 * "start()" when we call the start method. That thread cannot be used until the process has completed.
		 */
		
		for(int i = 0; i < ripIPChecker.numOfChecks(); i++) {
			
			threadHolder[i] = new ThreadingIPCheck(IP);
		}
		
		/* 
		 * This is where the actual threading begins.
		 */
		
		for(int i = 0; i < ripIPChecker.numOfChecks(); i++) {
			
		    ((ThreadingIPCheck) threadHolder[i]).setChecker(i);
		    
		    ((Thread) threadHolder[i]).start();
		}
		
		try {
			
			((Thread) threadHolder[ripIPChecker.numOfChecks() - 1]).join();
		} catch (InterruptedException e) {
			
		}

	}
	
	/**
	 * This method sets the details of the IP address, such as ISP, country, state, inner region, and risk.
	 * @param details an array of strings containing the details of the IP address. The first element of the array should be the ISP,
	 * second element should be the country, third element should be the state, fourth element should be the inner region and fifth 
	 * element should be the risk.
	*/
	
	public void setIPDetails(String[] details) {
		
		Command.ISP = details[0];
		Command.country = details[1];
		Command.state = details[2];
		Command.innerRegion = details[3];
		Command.risk = details[4];
	}
	
	/**
	 * Retrieves the IP details such as ISP, country, state, city, and risk level.
	 * The details are returned as a string in the format:
	 * ISP: ISP_NAME
	 * Country: COUNTRY_NAME
	 * State: STATE_NAME
	 * City: CITY_NAME
	 * Risk: RISK_LEVEL
	 * @return A string containing the IP details
	*/
	
	public String getIPDetails() {
		
		String details = "ISP: " + ISP;
		
		details += "\nCountry: " + country;
		details += "\nState: " + state;
		details += "\nCity: " + innerRegion;
		details += "\nRisk: " + risk;
		
		return details;
	}
	
	public static String getIP() {
		
	    return IP;
	}

	public static String getISP() {
		
	    return ISP;
	}

	public static String getCountry() {
		
	    return country;
	}

	public static String getState() {
		
	    return state;
	}

	public static String getInnerRegion() {
		
	    return innerRegion;
	}

	public static String getRisk() {
		
	    return risk;
	}
}
