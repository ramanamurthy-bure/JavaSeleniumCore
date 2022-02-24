package practice.streams.webtable;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class TableSearchWithStream {

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
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		driver.manage().window().maximize();

		driver.findElement(By.id("search-field")).sendKeys("Rice");

		List<WebElement> veggies = driver.findElements(By.xpath("//tr/td[1]"));

		// 1 results
		List<WebElement> filteredList = veggies.stream().filter(veggie -> veggie.getText().contains("Rice"))
				.collect(Collectors.toList());

		// 1 result
		Assert.assertEquals(veggies.size(), filteredList.size());
	}
}