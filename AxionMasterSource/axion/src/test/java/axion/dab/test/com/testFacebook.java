package axion.dab.test.com;

import org.testng.annotations.Test;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class testFacebook {

	private String appPackage;
	private String appActivity;
	private String userName;
	private String passWord;
	private AndroidDriver wd;
	private String screenDumpdestDir;
	WebDriverWait wait;

	
	@Parameters({ "screenDumpdestDir","appPackage", "appActivity", "userName", "passWord"})		
	@BeforeClass
	public void beforeClass(String param1 , String param2 , String param3 , String param4, String param5) {
	
		appiumStopServer();
		appiumStartServer();
		screenDumpdestDir = param1;
		appPackage = param2;
		appActivity = param3;
		userName = param4;
		passWord = param5;

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appium-version", "1.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "5.0.1");
		capabilities.setCapability("deviceName", "04157df41cb1253b");
		capabilities.setCapability("appPackage", appPackage);
		capabilities.setCapability("appActivity", appActivity);

		try {
			wd = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(enabled=true)
	public void testFacebookLogin() throws MalformedURLException {
     
		ATUReports.add("PASS", "Facebook Login Start ", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		ATUReports.add("<a href="  + takeScreenShot() + ">view screen dump</a>", true);
		
		// First click
		wait = new WebDriverWait(wd, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.EditText[1]")));
		
	   wd.findElement(By
				.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.EditText[1]"))
				.click();
	
		// Clear old data
		wd.findElement(By
				.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.EditText[1]"))
				.clear();

		// Enter new data Username
		wd.findElement(By
				.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.EditText[1]"))
				.sendKeys(userName);

		// First click
		wd.findElement(By
				.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.RelativeLayout[1]/android.widget.EditText[1]"))
				.click();

		// Clear old data

		wd.findElement(By
				.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.RelativeLayout[1]/android.widget.EditText[1]"))
				.clear();

		// Enter new data Password
		wd.findElement(By
				.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.RelativeLayout[1]/android.widget.EditText[1]"))
				.sendKeys(passWord);

		// Click login button
		wd.findElement(By
				.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.Button[1]"))
				.click();

		ATUReports.add("<a href="  + takeScreenShot() + ">view screen dump</a>", true);
		
		// Wait for Deny Location for facebook 
		wait = new WebDriverWait(wd, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.Button[1]")));
		
		ATUReports.add("<a href="  + takeScreenShot() + ">view screen dump</a>", true);
		// Deny location
		wd.findElement(By
				.xpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.Button[1]"))
				.click();

		ATUReports.add("PASS", "Facebook Login complete ", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		// do some clicks inside the face book app
		ATUReports.add("PASS", "Facebook News Feed ", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		ATUReports.add("<a href="  + takeScreenShot() + ">view screen dump</a>", true);
		wd.findElement(By.name("News Feed")).click();
		ATUReports.add("<a href="  + takeScreenShot() + ">view screen dump</a>", true);
		ATUReports.add("PASS", "Facebook Friends Request ", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

		wd.findElement(By.name("Friend Requests")).click();
		ATUReports.add("<a href="  + takeScreenShot() + ">view screen dump</a>", true);
		logoutFacebook();
	}

	@AfterClass
	public void afterClass() {

		wd.quit();

		appiumStopServer();

	}

	
	public void logoutFacebook()
	{
		
		wait = new WebDriverWait(wd, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("More")));
		
		wd.findElement(By.name("More")).click();

		// Now scroll down to search Log Out button 
		scrollToExactElement("Log");
		ATUReports.add("PASS", "Facebook Log Out ", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		wait = new WebDriverWait(wd, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("Log Out")));
		ATUReports.add("<a href="  + takeScreenShot() + ">view screen dump</a>", true);
		wd.findElement(By.name("Log Out")).click();

		ATUReports.add("PASS", "Facebook Log Out ", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		ATUReports.add("<a href="  + takeScreenShot() + ">view screen dump</a>", true);
		wd.findElement(By
				.xpath("//android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.Button[2]"))
				.click();

		ATUReports.add("PASS", "Facebook Log Complete ", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		ATUReports.add("<a href="  + takeScreenShot() + ">view screen dump</a>", true);
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
		//	e.printStackTrace();
		}

		System.out.println("Appium server stop");
	}

	public void scrollToExactElement(String str) {
		((AndroidDriver<MobileElement>) wd).findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().descriptionStartsWith(\""
						+ str + "\").instance(0))");
	}

	
	public String takeScreenShot() {
		
		  // Capture screenshot.
		  File scrFile = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
		  // Set date format to set It as screenshot file name.
		  SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		  // Create folder under project with name "screenshots" provided to destDir.
		  new File(screenDumpdestDir).mkdirs();
		  // Set file name using current date time.
		  String destFile = dateFormat.format(new Date()) + ".png";

		  try {
		   // Copy paste file at destination folder location
		   FileUtils.copyFile(scrFile, new File(screenDumpdestDir + "\\" + destFile));
		   String filename = "file:///"+screenDumpdestDir + "\\" + destFile;
		   return filename;
		  } catch (IOException e) {
		   return "null";
		  }
		
		 }

}
