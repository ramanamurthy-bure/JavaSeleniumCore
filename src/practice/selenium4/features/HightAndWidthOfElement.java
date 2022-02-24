package practice.selenium4.features;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HightAndWidthOfElement {

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
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		driver.manage().window().maximize();
		
		WebElement elmName=driver.findElement(By.xpath("(//input[@name='name'])[1]"));
		
		int height=elmName.getRect().getHeight();
		int width=elmName.getRect().getWidth();
		
		System.out.println("Height of the 'Name' edit field: "+height);
		System.out.println("Width of the 'Name' edit field: "+width);
	
	}
}