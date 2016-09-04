package axion.dab.test.com;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class testNewDefaultSaftey {

	{
		System.setProperty("atu.reporter.config", "atu.properties");
	}

	private String excelFilePath;
	private String excelSheet_UK_Top_Url;
	private String excelSheet_Blacklist_Url;
	private String excelSheet_Iwf_Url;
	private String excelSheet_Over_18_Url;
	private WebDriver driver;
	private String screenDumpPath;
	private int pageLoadTimeOut = 60;
	private int maxClickLinkNo = 3;
	private int maxLinkLoadTimeout = 1;
	private int maxAlertWaitTimeout = 2;
	private String gatewayId = "0A";

	@Parameters({ "excelFilePath", "screenDumpPath", "excelSheet_UK_Top_Url", "excelSheet_Blacklist_Url",
			"excelSheet_Iwf_Url", "excelSheet_Over_18_Url", "gatewayId" })
	@BeforeClass
	public void testMethodStartUpBeforeClass(String param1, String param2, String param3, String param4, String param5,
			String param6, String param7) {

		excelFilePath = param1;
		screenDumpPath = param2;
		excelSheet_UK_Top_Url = param3;
		excelSheet_Blacklist_Url = param4;
		excelSheet_Iwf_Url = param5;
		excelSheet_Over_18_Url = param6;
		gatewayId = param7;

		try {
			ATUReports.add("INFO",
					" Using Excel :" + excelFilePath + " excelSheet_UK_Top_Url :" + excelSheet_UK_Top_Url, true);
			ATUReports.add("INFO",
					" Using Excel :" + excelFilePath + " excelSheet_Blacklist_Url :" + excelSheet_Blacklist_Url, true);
			ATUReports.add("INFO", " Using Excel :" + excelFilePath + " excelSheet_Iwf_Url :" + excelSheet_Iwf_Url,
					true);
			ATUReports.add("INFO",
					" Using Excel :" + excelFilePath + " excelSheet_Over_18_Url :" + excelSheet_Over_18_Url, true);
			ATUReports.add("INFO", " ScreenDumpPath :" + screenDumpPath, true);
			ATUReports.add("INFO", " GatewayID :" + gatewayId, true);

			appiumStopServer();
			appiumStartServer();
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S6");
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME);
			ATUReports.add("INFO", " Using  Platform:" + capabilities.getCapability(MobileCapabilityType.PLATFORM_NAME),
					true);
			ATUReports.add("INFO", " Using Browser :" + capabilities.getBrowserName(), true);
			ATUReports.add("INFO", " Using Device ID :" + capabilities.getCapability(MobileCapabilityType.DEVICE_NAME),
					true);

			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			ATUReports.add("INFO", "Remote Web Driver started", true);
			driver.manage().timeouts().implicitlyWait(pageLoadTimeOut, TimeUnit.SECONDS);
			ATUReports.add("INFO", "Wait Max 60 seconds for page to load", true);
			ATUReports.setWebDriver(driver);
		} catch (Exception e) {
			ATUReports.add("FAIL " + e.getMessage(), true);
		}

	}

	@DataProvider(name = "testInputTopSitesData")
	public Object[][] providertestInputTopSitesData() {

		readExcel Excelobj = new readExcel();
		Object[][] arrayObject = null;

		try {
			arrayObject = Excelobj.getExcelData(excelFilePath, excelSheet_UK_Top_Url);

		} catch (Exception e) {
			System.out.println("Excel read Error " + e.getMessage());
		}

		return arrayObject;
	}

	@DataProvider(name = "testInputBlackListData")
	public Object[][] providerInputBlackListData() {

		readExcel Excelobj = new readExcel();
		Object[][] arrayObject = null;

		try {
			arrayObject = Excelobj.getExcelData(excelFilePath, excelSheet_Blacklist_Url);

		} catch (Exception e) {
			System.out.println("Excel read Error " + e.getMessage());
		}

		return arrayObject;
	}

	@DataProvider(name = "testInputIwfData")
	public Object[][] providerInputIwfData() {

		readExcel Excelobj = new readExcel();
		Object[][] arrayObject = null;

		try {
			arrayObject = Excelobj.getExcelData(excelFilePath, excelSheet_Iwf_Url);

		} catch (Exception e) {
			System.out.println("Excel read Error " + e.getMessage());
		}

		return arrayObject;
	}

	@DataProvider(name = "testInputOver18Data")
	public Object[][] providerInputOver18Data() {

		readExcel Excelobj = new readExcel();
		Object[][] arrayObject = null;

		try {
			arrayObject = Excelobj.getExcelData(excelFilePath, excelSheet_Over_18_Url);

		} catch (Exception e) {
			System.out.println("Excel read Error " + e.getMessage());
		}

		return arrayObject;
	}

	@Test(dataProvider = "testInputBlackListData", enabled = false)
	public void testBlackListedSites(String urlStr) {

		String pageBodyHTML = null;

		ATUReports.add("INFO", urlStr, false);
		// get page first
		try {
			URL url = new URL(urlStr);
			driver.get(url.toString());
			// After Main URL GET wait for page to Load
			checkPageIsReady(urlStr.toString());
			pageBodyHTML = driver.findElement(By.tagName("body")).getText();
			ATUReports.add("INFO", "Page HTML body" + pageBodyHTML, LogAs.INFO,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			waitForAlerts(maxAlertWaitTimeout);

		} catch (Exception e) {
			// not right
			ATUReports.add("INFO", "Ignoring Exception_1 " + e.getMessage(), LogAs.INFO,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		}

		try {
			Assert.assertTrue(pageBodyHTML.contains("This page has been blocked"));
			ATUReports.add("PASS", "BlackList Website Check ", LogAs.PASSED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		} catch (AssertionError e) {
			ATUReports.add("FAIL", "BlackList Website Check : " + pageBodyHTML + "  :" + e.getMessage(), LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		}

	}

	@Test(dataProvider = "testInputOver18Data", enabled = false)
	public void testOver18Sites(String urlStr) {
		String pageBodyHTML = null;

		ATUReports.add("INFO", urlStr, false);
		// get page first
		try {
			URL url = new URL(urlStr);
			driver.get(url.toString());
			// After Main URL GET wait for page to Load
			checkPageIsReady(urlStr.toString());
			pageBodyHTML = driver.findElement(By.tagName("body")).getText();
			ATUReports.add("INFO", "Page HTML body" + pageBodyHTML, LogAs.INFO,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			waitForAlerts(maxAlertWaitTimeout);

		} catch (Exception e) {
			// not right
			ATUReports.add("INFO", "Ignoring Exception_1 " + e.getMessage(), LogAs.INFO,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		}

		try {

			Assert.assertTrue(pageBodyHTML.contains("got to be over 18 for this website"));
			ATUReports.add("PASS", "Over 18 WebSite Check ", LogAs.PASSED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		} catch (AssertionError e) {
			ATUReports.add("FAIL", "Over 18 WebSite Check : " + pageBodyHTML + "  :" + e.getMessage(), LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		}
	}

	@Test(dataProvider = "testInputIwfData", enabled = false)
	public void testIwfSites(String urlStr) {

		String pageBodyHTML = null;

		ATUReports.add("INFO", urlStr, false);
		// get page first
		try {
			URL url = new URL(urlStr);
			driver.get(url.toString());
			// After Main URL GET wait for page to Load
			checkPageIsReady(urlStr.toString());
			pageBodyHTML = driver.findElement(By.tagName("body")).getText();
			ATUReports.add("INFO", "Page HTML body" + pageBodyHTML, LogAs.INFO,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			waitForAlerts(maxAlertWaitTimeout);

		} catch (Exception e) {
			// not right
			ATUReports.add("INFO", "Ignoring Exception_1 " + e.getMessage(), LogAs.INFO,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		}

		try {
			Assert.assertTrue(pageBodyHTML.contains("Access denied"));
			ATUReports.add("PASS", "IWF Access denied Check", LogAs.PASSED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		} catch (AssertionError e) {
			ATUReports.add("FAIL", "IWF Access denied Check : " + pageBodyHTML + "  :" + e.getMessage(), LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		}

	}

	@Test(dataProvider = "testInputTopSitesData", enabled = false)
	public void testNewDefaultSafteyTopUKSites(String urlStr) {

		ATUReports.add("INFO", urlStr, false);

		String[] links = null;
		int linksCount = 0;
		Random r = new Random();
		int linkCount = 1;

		System.out.println("Test URL - " + urlStr);
		// get page first
		try {
			URL url = new URL(urlStr);
			driver.get(url.toString());
			// After Main URL GET wait for page to Load
			checkPageIsReady(urlStr.toString());
			waitForAlerts(maxAlertWaitTimeout);

		} catch (Exception e) {
			// not right
			ATUReports.add("INFO", "Ignoring Exception_1 " + e.getMessage(), LogAs.INFO,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		}

		// find a random link on the page and click
		List<WebElement> anchorTagsList = driver.findElements(By.tagName("a"));
		System.out.println("Total no of links are " + anchorTagsList.size());
		ATUReports.add("INFO", "Total No of links on the page " + anchorTagsList.size(), true);
		// Loop maxClickLinkNo time to click that page and ignore any exception
		linksCount = anchorTagsList.size();
		System.out.println("Total no of links Available: " + linksCount);
		links = new String[linksCount];
		for (int i = 0; i < linksCount; i++) {
			links[i] = anchorTagsList.get(i).getAttribute("href");
		}

		// navigate to each Link on the web page
		for (int i = 1; i < linksCount; i++) {
			String url = links[i];
			try {

				if (!url.contains("javascript") && url != "") {
					if (linkCount > maxClickLinkNo) {
						System.out.println("Max Click Link reached " + maxClickLinkNo);
						ATUReports.add("INFO", "Max Click Link reached" + maxClickLinkNo, true);
						break;
					}
					linkCount++;

				}
			} catch (Exception e) {
				ATUReports.add("INFO ", "Ignoring Exception_1 " + url + " Exception - " + e.getMessage(), LogAs.INFO,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}

			try {
				System.out.println("Click Link " + url);
				ATUReports.add("INFO ", " Click Link " + url, LogAs.PASSED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				driver.navigate().to(url);
				checkPageIsReady(url);
				waitForAlerts(maxAlertWaitTimeout);

			} catch (Exception e) {
				System.out.println(" Navigate URL Ignoring exception");
				ATUReports.add("INFO", "Ignoring Exception_2 " + e.getMessage(), LogAs.INFO,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}

		}

	}

	@Test(enabled = false)
	public void testNewDefaultSafteyHTTPHeader() {

		String urlForHeader = "http://headers.formula4leadership.com";
		String httpReponseText;
		try {
			driver.get(urlForHeader);
			WebElement anchorTagsList = driver.findElement(By.xpath("//*[@id=\"form1\"]/div[3]/p"));
			// After Main URL GET wait for page to Load
			httpReponseText = anchorTagsList.getText();
			System.out.println("HTTP Header Response" + anchorTagsList.getText());
			ATUReports.add("INFO ", " HTTP Header Response" + anchorTagsList.getText(), LogAs.INFO,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			try {
				Assert.assertTrue(httpReponseText.contains("HTTP_O2GW_ID:" + gatewayId));
				ATUReports.add("PASS", "HTTP_O2GW_ID:0A - DABFULL", LogAs.PASSED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			} catch (AssertionError e) {
				ATUReports.add("FAIL", "Assertion Error Gateway ID " + gatewayId + "  :" + e.getMessage(), LogAs.FAILED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			}

		} catch (Exception e) {
			System.out.println(" Navigate URL Ignoring exception");
			ATUReports.add("INFO", "Ignoring Exception_2", LogAs.INFO, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		}

	}

	@AfterClass
	public void testMethodCleanUpAfterClass() {

		driver.quit();
		appiumStopServer();
	}

	public void checkPageIsReady(final String page) {

		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};

		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(driver, pageLoadTimeOut);
			wait.until(expectation);
			ATUReports.add("PASS ", " Page Load Complete " + page, LogAs.PASSED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShot();

		} catch (Exception e) {

			ATUReports.add("FAIL ", " Page Load Timeout " + page, LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			ATUReports.add("FAIL ", e.getMessage(), LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		}

	}

	public void takeScreenShot() {

		// Capture screenshot.
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// Set date format to set It as screenshot file name.
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		// Create folder under project with name "screenshots" provided to
		// destDir.

		new File(screenDumpPath).mkdirs();
		// Set file name using current date time.
		String destFile = dateFormat.format(new Date()) + ".png";

		try {
			// Copy paste file at destination folder location
			FileUtils.copyFile(scrFile, new File(screenDumpPath + "//" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void waitForAlerts(int maxLinkLoadTimeout) {
		WebDriverWait wait = new WebDriverWait(driver, maxLinkLoadTimeout);
		// Wait for Alert to be present
		System.out.println(" waitForAlerts " + maxLinkLoadTimeout);
		Alert myAlert = wait.until(ExpectedConditions.alertIsPresent());
		myAlert.accept();

	}

	public void appiumStartServer() {

		CommandLine command = new CommandLine("C:\\Program Files\\nodejs\\node");
		command.addArgument("C:\\Program Files (x86)\\Appium\\node_modules\\appium\\bin\\appium.js", false);
		command.addArgument("-a", false);
		command.addArgument("127.0.0.1");
		command.addArgument("-p", false);
		command.addArgument("4723");
		command.addArgument("--platform-name", false);
		command.addArgument("Android");
		command.addArgument("--platform-version", false);
		command.addArgument("21");
		command.addArgument("--automation-name", false);
		command.addArgument("Appium ");
		command.addArgument("--log-no-color", false);
		command.addArgument("--session-override", false);
		command.addArgument("--command-timeout", false);
		command.addArgument("120");
		command.addArgument("-lt", false);
		command.addArgument("60");
		command.addArgument("--device-ready-timeout", false);
		command.addArgument("60");

		System.out.println(command.toString());

		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(1);
		try {
			executor.execute(command, resultHandler);
			System.out.println("Appium server started. ");
			Thread.sleep(5000);
			System.out.println("Appium server Loading ... ");
			Thread.sleep(10000);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void appiumStopServer() {

		CommandLine command = new CommandLine("cmd");
		command.addArgument("/c");
		command.addArgument("Taskkill /F /IM node.exe");

		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(1);
		try {
			executor.execute(command, resultHandler);
			Thread.sleep(2000);

		} catch (ExecuteException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Appium server stop");
	}

}
