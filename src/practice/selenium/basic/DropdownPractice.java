package practice.selenium.basic;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DropdownPractice {

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

		WebElement elmCCy = driver.findElement(By.xpath("//select[@id='ctl00_mainContent_DropDownListCurrency']"));
		Select sel = new Select(elmCCy);
		ArrayList<String> allOptionText = new ArrayList<String>();
		List<WebElement> alOptions = sel.getOptions();
		for (WebElement elm : alOptions) {
			allOptionText.add(elm.getText().trim());
		}

		System.out.println(sel.getFirstSelectedOption().getText());
		System.out.println(allOptionText);

		WebElement elmDepartureCity = driver.findElement(By.xpath("//input[@value='Departure City']"));
		elmDepartureCity.click();

		WebElement elmDeptCity = driver.findElement(By.xpath("//a[contains(@text,'MAA')]"));
		elmDeptCity.click();

		WebElement elmArvCity = driver.findElement(By.xpath("//a[contains(@text,'GOI')]"));
		elmArvCity.click();

		WebElement emDeptDate = driver.findElement(By.cssSelector("a.ui-state-default.ui-state-highlight"));
		emDeptDate.click();

		WebElement elmRoundTrip = driver.findElement(By.xpath("//input[@id=\"ctl00_mainContent_rbtnl_Trip_1\"]"));
		elmRoundTrip.click();

		WebElement elmRetunDateBtn = driver
				.findElement(By.xpath("//label[contains(text(),'Return date')]/following::button"));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
		wait.until(ExpectedConditions.elementToBeClickable(elmRetunDateBtn));
		elmRetunDateBtn.click();

		WebElement emArrDate = driver.findElement(By.cssSelector("a.ui-state-default.ui-state-highlight"));
		emArrDate.click();

		elmRetunDateBtn.click();
		List<WebElement> allDates = driver.findElements(By.xpath("//span[text()='March']/following::td"));
		String elmDateToSelect = "23";

		for (WebElement elmDate : allDates) {
			if (elmDate.getText().equalsIgnoreCase(elmDateToSelect)) {
				elmDate.click();
				break;
			}
		}

		WebElement elmTypeToSel = driver
				.findElement(By.xpath("//input[@id='autosuggest' and @placeholder='Type to Select']"));
		elmTypeToSel.clear();
		elmTypeToSel.sendKeys("Ind");
		WebElement elmInd = driver.findElement(By.xpath("//a[text()='India']"));
		elmInd.click();

		WebElement elmCheckIn = driver.findElement(By.xpath("//span[text()='Check-In']"));
		Actions act = new Actions(driver);
		act.moveToElement(elmCheckIn);

		StringBuilder sb = new StringBuilder();
		List<WebElement> allToolTextPara = driver.findElements(By.xpath("//span[text()='Check-In']/following::div/p"));
		for (WebElement emlmPara : allToolTextPara) {
			sb.append(emlmPara.getText() + "\n");
		}

		System.out.println(sb);

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

		driver.quit();

	}
}