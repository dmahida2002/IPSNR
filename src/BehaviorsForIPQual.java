import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.EnumMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.net.URL;

/**
 * Enum representing the states in the United States in a abbreviated form.
*/

enum State {
	
    AL, AK, AZ, AR, 
    CA, CO, CT, 
    DE, 
    FL, 
    GA, 
    HI, ID, IL, IN, IA, 
    KS, KY, 
    LA, 
    ME, MD, MA, MI, MN, MS, MO, MT, 
    NE, NV, NH, NJ, NM, NY, NC, ND, 
    OH, OK, OR, 
    PA, 
    RI, 
    SC, SD, 
    TN, TX, 
    UT, 
    VT, VA, 
    WA, WV, WI, WY
}

/**
* This class represents the behavior of the application when it is used to gather information 
* about an IP address. It uses Selenium's WebDriver to navigate to a website that provides 
* information about an IP address and extracts the necessary information. It also utilizes 
* an EnumMap to map state abbreviations to their full names.
*
* @author Deeshan M
* @version 1.0
*/

public class BehaviorsForIPQual {
	
	/**
	 * This variable is an EnumMap that holds the state abbreviation as the key and the full state name as the value.
	 * The EnumMap is parameterized with the Enum class 'State' as the key and 'String' as the value.
	 * The EnumMap is instantiated using the Enum class 'State' as the argument for the constructor.
	 * This map will be used to map the state abbreviation from the web page to the full state name.
	*/
	
	private EnumMap<State, String> stateAbbreviations = new EnumMap<>(State.class);
	
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
	 * The ISPXPath variable is a String that contains the XPath expression used to locate a specific element on the web page.
	 */
	
	private String ISPXPath = "/html/body/section[1]/div[2]/div/div[4]/div[1]/div[1]/table/tbody/tr[11]/td[2]";
	
	/**
	* Declare an array of strings to hold the details of the IP
	*/
	
	private String details[] = new String[4];
	
	/**
	* This method sets up the WebDriver and navigates to the specified website.
	* @param website the website to navigate to
	*/
	
	public void setup(String website, String IP) {
		
		stateAbbreviations();
		
		options.addArguments("--headless", "--disable-dev-shm-usage");
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\dmahi\\Desktop\\chromedriver.exe");
			
		driver = new ChromeDriver(options);
		options.setCapability("acceptSslCerts", true);
		options.setCapability("acceptInsecureCerts", true);
		
		String currentWebsite = website + IP;
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		driver.navigate().to(currentWebsite);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ISPXPath)));
	}
	
	/**
	 * This method maps the state abbreviations to their full names by adding them to the stateAbbreviations EnumMap.
	 * The EnumMap is initialized using the State enumeration as the key type.
	 * The put() method is used to add each state abbreviation as the key and its corresponding full name as the value.
	*/
	
	private void stateAbbreviations() {
		
		stateAbbreviations.put(State.AL, "Alabama");
        stateAbbreviations.put(State.AK, "Alaska");
        stateAbbreviations.put(State.AZ, "Arizona");
        stateAbbreviations.put(State.AR, "Arkansas");
        stateAbbreviations.put(State.CA, "California");
        stateAbbreviations.put(State.CO, "Colorado");
        stateAbbreviations.put(State.CT, "Connecticut");
        stateAbbreviations.put(State.DE, "Delaware");
        stateAbbreviations.put(State.FL, "Florida");
        stateAbbreviations.put(State.GA, "Georgia");
        stateAbbreviations.put(State.HI, "Hawaii");
        stateAbbreviations.put(State.ID, "Idaho");
        stateAbbreviations.put(State.IL, "Illinois");
        stateAbbreviations.put(State.IN, "Indiana");
        stateAbbreviations.put(State.IA, "Iowa");
        stateAbbreviations.put(State.KS, "Kansas");
        stateAbbreviations.put(State.KY, "Kentucky");
        stateAbbreviations.put(State.LA, "Louisiana");
        stateAbbreviations.put(State.ME, "Maine");
        stateAbbreviations.put(State.MD, "Maryland");
        stateAbbreviations.put(State.MA, "Massachusetts");
        stateAbbreviations.put(State.MI, "Michigan");
        stateAbbreviations.put(State.MN, "Minnesota");
        stateAbbreviations.put(State.MS, "Mississippi");
        stateAbbreviations.put(State.MO, "Missouri");
        stateAbbreviations.put(State.MT, "Montana");
        stateAbbreviations.put(State.NE, "Nebraska");
        stateAbbreviations.put(State.NV, "Nevada");
        stateAbbreviations.put(State.NH, "New Hampshire");
        stateAbbreviations.put(State.NJ, "New Jersey");
        stateAbbreviations.put(State.NM, "New Mexico");
        stateAbbreviations.put(State.NY, "New York");
        stateAbbreviations.put(State.NY, "New York");
        stateAbbreviations.put(State.NC, "North Carolina");
        stateAbbreviations.put(State.ND, "North Dakota");
        stateAbbreviations.put(State.OH, "Ohio");
        stateAbbreviations.put(State.OK, "Oklahoma");
        stateAbbreviations.put(State.OR, "Oregon");
        stateAbbreviations.put(State.PA, "Pennsylvania");
        stateAbbreviations.put(State.RI, "Rhode Island");
        stateAbbreviations.put(State.SC, "South Carolina");
        stateAbbreviations.put(State.SD, "South Dakota");
        stateAbbreviations.put(State.TN, "Tennessee");
        stateAbbreviations.put(State.TX, "Texas");
        stateAbbreviations.put(State.UT, "Utah");
        stateAbbreviations.put(State.VT, "Vermont");
        stateAbbreviations.put(State.VA, "Virginia");
        stateAbbreviations.put(State.WA, "Washington");
        stateAbbreviations.put(State.WV, "West Virginia");
        stateAbbreviations.put(State.WI, "Wisconsin");
        stateAbbreviations.put(State.WY, "Wyoming");
	}
	
	/**
	 * This method is used to extract the details such as ISP, country, 
	 * state and inner region from the website after the 'Check' button is clicked.
	 * The extracted details are stored in an array named 'details'.
	*/

	public void getDetails() {
		
		String countryXPath = "/html/body/section[1]/div[2]/div/div[4]/div[1]/div[1]/table/tbody/tr[2]/td[2]/span";
		String stateXPath = "/html/body/section[1]/div[2]/div/div[4]/div[1]/div[1]/table/tbody/tr[9]/td[2]";
		String innerRegionXPath = "/html/body/section[1]/div[2]/div/div[4]/div[1]/div[1]/table/tbody/tr[8]/td[2]";
		String scoreXPath = "/html/body/section[1]/div[2]/div/div[4]/div[1]/div[1]/table/tbody/tr[3]/td[2]/span";
		
		details[0] = driver.findElement(By.xpath(ISPXPath)).getText();
		details[1] = resources.getFullCountryName(driver.findElement(By.xpath(countryXPath)).getText());
		details[2] = stateAbbreviations.get(State.valueOf(driver.findElement(By.xpath(stateXPath)).getText()));
		details[3] = driver.findElement(By.xpath(innerRegionXPath)).getText();
		details[4] = driver.findElement(By.xpath(scoreXPath)).getText();
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
