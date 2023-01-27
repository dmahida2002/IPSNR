import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class contains the behaviors for interacting with a NordVPN website in order to retrieve 
 * information about a specific IP address.
 * It uses Selenium WebDriver to navigate and interact with the website. 
 * The class contains methods for setting up the WebDriver, entering an IP address,
 * clicking the 'Check' button on the website, and extracting the details such as ISP, 
 * country, state and inner region from the website.
 * The extracted details are stored in an array and can be accessed through the 'finish' method.
 * @author Deeshan M
 * @version 1.0
*/

public class BehaviorsForNordVPN {
	
	/**
	* Declare a WebDriver object for interacting with the website
	*/
	
	private WebDriver driver;
	
	/**
	* Declare a Resources object for accessing the Resources class
	*/
	
	private Resources resources = new Resources();
	
	/**
	* Declare a ChromeOptions object for setting options for the ChromeDriver
	*/
	  
	private ChromeOptions options = new ChromeOptions();
	
	/**
	* Declare an array of strings to hold the details of the IP
	*/
	
	private String details[] = new String[4];
	
	/**
	* This method sets up the WebDriver and navigates to the specified website.
	* @param website the website to navigate to
	*/
	
	public void setup(String website) {
		
		options.addArguments("--headless", "--disable-dev-shm-usage");
			
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\dmahi\\Desktop\\chromedriver.exe");
			
		driver = new ChromeDriver(options);
		options.setCapability("acceptSslCerts", true);
		options.setCapability("acceptInsecureCerts", true);
		
		String currentWebsite = website;
		
		driver.navigate().to(currentWebsite);
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	/**
	 * This method is used to enter the IP Address in the input field of the website.
	 * @param IP - The IP Address that needs to be entered in the input field.
	*/
	
	public void enterIP(String IP) {
		
		String IPXPath = "/html/body/div[3]/div[1]/div/div/div/div/div/div/div[1]/div/div/div[1]/div/div/div[1]/div/div[2]/div[1]/input";
		
		driver.findElement(By.xpath(IPXPath)).clear();
		driver.findElement(By.xpath(IPXPath)).sendKeys(IP);
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}
	
	/**
	 * This method is used to click on the 'Check' button on the website after entering the IP Address.
	*/
	
	public void clickCheck() {
		
		String checkXPath = "/html/body/div[3]/div[1]/div/div/div/div/div/div/div[1]/div/div/div[1]/div/div/div[1]/div/div[2]/div[2]/button";
		
		driver.findElement(By.xpath(checkXPath)).click();
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(checkXPath)));
		
		element.click();
	}
	
	/**
	 * This method is used to extract the details such as ISP, country, 
	 * state and inner region from the website after the 'Check' button is clicked.
	 * The extracted details are stored in an array named 'details'.
	*/
	
	public void getDetails() {
		
		String ISPXPath = "/html/body/div[3]/div[1]/div/div/div/div/div/div/div[2]/div/div[2]/div/div[1]/p[2]";
		String countryXPath = "/html/body/div[3]/div[1]/div/div/div/div/div/div/div[2]/div/div[2]/div/div[1]/p[4]";
		String stateXPath = "/html/body/div[3]/div[1]/div/div/div/div/div/div/div[2]/div/div[2]/div/div[2]/p[4]";
		String innerRegionXPath = "/html/body/div[3]/div[1]/div/div/div/div/div/div/div[2]/div/div[2]/div/div[1]/p[6]";
		String pulledCont = driver.findElement(By.xpath(countryXPath)).getText();
		
		details[0] = driver.findElement(By.xpath(ISPXPath)).getText();
		details[1] = resources.nordContAbr(pulledCont);
		details[2] = driver.findElement(By.xpath(stateXPath)).getText();
		details[3] = driver.findElement(By.xpath(innerRegionXPath)).getText();
	}
	
	/**
	 * This method returns the array 'details' which contains the details of the IP address that was searched for.
	 * The details include the ISP, country, state and inner region of the IP address.
	 * @return the array populated with the IP details.
	 */
	
	public String[] finish() {
		
		return details;
	}
}
