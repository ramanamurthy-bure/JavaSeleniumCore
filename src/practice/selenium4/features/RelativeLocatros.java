package practice.selenium4.features;
// Static packages need to add manually
import static org.openqa.selenium.support.locators.RelativeLocator.with;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RelativeLocatros {

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
		
		
		// Relative Locators | Friendly Locators
		// This is available in the static package, so we need to import it by typing manually. 
		// Relative locators does not support flex webelements
		// Relative locators help to identify elements with the help of tagname
		WebElement elmNameEdit=driver.findElement(By.xpath("(//input[@name='name'])[1]"));
	
		//above
		WebElement lblName=driver.findElement(with(By.tagName("label")).above(elmNameEdit));
		System.out.println(lblName.getText());	
		
		//below
		WebElement elmDOB=driver.findElement(By.xpath("//label[text()='Date of Birth']"));
		WebElement btnSub=driver.findElement(with(By.tagName("input")).below(elmDOB));
		btnSub.click();
				
		//toLeftOf
		WebElement elmIceCreamChekBox=driver.findElement(By.xpath("//label[normalize-space()='Check me out if you Love IceCreams!']"));
		WebElement elmCheckBox=driver.findElement(with(By.tagName("input")).toLeftOf(elmIceCreamChekBox));
		elmCheckBox.click();
		
		//toRightOf
		WebElement elmFrstRadioBtn=driver.findElement(By.xpath("//input[@id='inlineRadio1']"));
		WebElement radiolabel=driver.findElement(with(By.tagName("label")).toRightOf(elmFrstRadioBtn));
		System.out.println(radiolabel.getText());
		
	}
}