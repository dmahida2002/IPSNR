import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BehaviorsForWhatIP {
	
	private WebDriver driver;
	
	private String ISPXPath = "/html/body/div[1]/div/div/div/div/article/div/div/div[1]/div/div[2]/div/div[1]/div/div[2]/div/div/div/div[1]/div[1]/p[4]/span[2]";
	
	private String details[] = new String[5];
	
	public void setup(String website, String IP) {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\dmahi\\Desktop\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		
		driver = new ChromeDriver(options);
		
		options.addArguments("start-maximized");
		options.setCapability("acceptSslCerts", true);
		options.setCapability("acceptInsecureCerts", true);
		
		String currentWebsite = website + IP;
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		driver.navigate().to(currentWebsite);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ISPXPath)));
	}
	
	public void getDetails() {
		
		String countryXPath = "/html/body/div[1]/div/div/div/div/article/div/div/div[1]/div/div[2]/div/div[1]/div/div[2]/div/div/div/div[1]/div[1]/p[7]/span[2]";
		String stateXPath = "/html/body/div[1]/div/div/div/div/article/div/div/div[1]/div/div[2]/div/div[1]/div/div[2]/div/div/div/div[1]/div[1]/p[8]/span[2]";
		String innerRegionXPath = "/html/body/div[1]/div/div/div/div/article/div/div/div[1]/div/div[2]/div/div[1]/div/div[2]/div/div/div/div[1]/div[1]/p[9]/span[2]";
		String typeXPath = "/html/body/div[1]/div/div/div/div/article/div/div/div[1]/div/div[2]/div/div[1]/div/div[2]/div/div/div/div[1]/div[1]/p[6]/span[2]/a";
		
		details[0] = driver.findElement(By.xpath(ISPXPath)).getText();
		details[1] = driver.findElement(By.xpath(countryXPath)).getText();
		details[2] = driver.findElement(By.xpath(stateXPath)).getText();
		details[3] = driver.findElement(By.xpath(innerRegionXPath)).getText();
		details[4] = driver.findElement(By.xpath(typeXPath)).getText();
	}
	
	public String[] finish() {
		
		driver.close();
		
		return details;
	}
}
