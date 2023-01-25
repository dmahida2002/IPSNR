import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BehaviorsForNordVPN {
	
	private WebDriver driver;
	
	private Resources resources = new Resources();
	  
	private ChromeOptions options = new ChromeOptions();
	
	private String details[] = new String[4];
	
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
	
	public void enterIP(String IP) {
		
		String IPXPath = "/html/body/div[3]/div[1]/div/div/div/div/div/div/div[1]/div/div/div[1]/div/div/div[1]/div/div[2]/div[1]/input";
		
		driver.findElement(By.xpath(IPXPath)).clear();
		driver.findElement(By.xpath(IPXPath)).sendKeys(IP);
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}
	
	public void clickCheck() {
		
		String checkXPath = "/html/body/div[3]/div[1]/div/div/div/div/div/div/div[1]/div/div/div[1]/div/div/div[1]/div/div[2]/div[2]/button";
		
		driver.findElement(By.xpath(checkXPath)).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
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
	
	public String[] finish() {
		
		return details;
	}
}
