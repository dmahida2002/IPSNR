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

public class BehaviorsForIPQual {
	
	private EnumMap<State, String> stateAbbreviations = new EnumMap<>(State.class);
	
	private WebDriver driver;
	
	private Resources resources = new Resources();
	
	private ChromeOptions options = new ChromeOptions();
	
	private String ISPXPath = "/html/body/section[1]/div[2]/div/div[4]/div[1]/div[1]/table/tbody/tr[11]/td[2]";
	
	private String details[] = new String[5];
	
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
	
	public String[] finish() {
		
		driver.close();
		
		return details;
	}
}
