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

public class ScreenshotOptions {

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
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		driver.manage().window().maximize();

		// Taking page screenshot
		File src1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dst1 = new File("./Screenshots/Page.png");
		FileUtils.copyFile(src1, dst1);

		// Taking screenshot for a WebElement
		WebElement elmImg = driver.findElement(By.cssSelector("div.slider__item a img"));
		File fs = elmImg.getScreenshotAs(OutputType.FILE);
		File dstSave = new File("./Screenshots/WebElement.png");
		FileUtils.copyFile(fs, dstSave);

		if (strBrowserName.equalsIgnoreCase("Firefox")) {
			// Taking full page screenshot - Option available in only Firefox Driver
			File src2 = ((FirefoxDriver) driver).getFullPageScreenshotAs(OutputType.FILE);
			File dst2 = new File("./Screenshots/FullPage.png");
			FileUtils.copyFile(src2, dst2);
		} else if (strBrowserName.equalsIgnoreCase("Chrome")) {

		} else if (strBrowserName.equalsIgnoreCase("Edge")) {

		}

	}
}