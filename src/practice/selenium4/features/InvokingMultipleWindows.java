package practice.selenium4.features;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InvokingMultipleWindows {

	public static void main(String[] args) throws InterruptedException, IOException {

		String strBrowserName = "Chrome";
		WebDriver driver = null;

		if (strBrowserName.equalsIgnoreCase("Chrome")) {
			// Chrome Browser
			System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			// To disable 'Save' password pop-ups
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);
			// To disable 'Chrome is being handled by automated software'
			options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			driver = new ChromeDriver(options);
		} else if (strBrowserName.equalsIgnoreCase("Firefox")) {
			// FirefoxDriver
			System.setProperty("webdriver.gecko.driver", ".\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (strBrowserName.equalsIgnoreCase("Edge")) {
			// EdgeDriver
			System.setProperty("webdriver.edge.driver", ".\\drivers\\msedgedriver.exe");
			EdgeOptions options = new EdgeOptions();
			options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			driver = new EdgeDriver(options);
		} else {
			System.out.println("Invalid Browser");
		}

		// To set the implicit timeout- global
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30L));
		driver.get("https://rahulshettyacademy.com/angularpractice/"); //Step-1
		driver.manage().window().maximize();
		
		// Invoking multiple windows or tabs from selenium using one driver instance
		// When we want to work with different applications at a time
		
		
		//Scenario: 
		//Step-1 : Navigate to https://rahulshettyacademy.com/angularpractice/
		//Step-2 : Navigate to https://rahulshettyacademy.com and get the first course name
		//Step-3 : Now enter the course name in the Name edit box available in the site from Step-1
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> windHands=driver.getWindowHandles();		
		Iterator<String> it=windHands.iterator();
		String parentWindID=it.next();//Parent window will alwasy be first
		String childWindID=it.next(); // Here it will give child window
		
		driver.switchTo().window(childWindID);
		driver.get("https://rahulshettyacademy.com");
		List<WebElement> elmCrscTitle=driver.findElements(By.cssSelector("a[href*='https://courses.rahulshettyacademy.com/p']"));
		String crsName=elmCrscTitle.get(1).getText();
		System.out.println("Course Name Fetched :" +crsName);
		driver.close();
		
		driver.switchTo().window(parentWindID);
		driver.findElement(By.xpath("(//input[@name='name'])[1]")).sendKeys(crsName);		
		
	}
}