import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Command {
	
	private String IP;
	
	public Command(String IP) {
		
		this.IP = IP;
	}
	
	public void stress (String IP) {
		
		for(int i = 0; i < 1; i++) {
			
			ThreadingStressthem ripStressthem = new ThreadingStressthem(IP);
			
			ripStressthem.start();
			
			ThreadingHardStresser ripHardStresser = new ThreadingHardStresser(IP);
			
			ripHardStresser.start();
		}
	}
	
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
		 * We need this many threads because each thread will be in a state of
		 * "start()" when we call the start method. The thread cannot be used until the process has completed.
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
		
	}
}
