package Hackathon.NewBikeSearch.modules; // Import necessary modules

import java.time.Duration; // Import necessary Duration class

import org.openqa.selenium.By; // Import necessary Selenium classes
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import Hackathon.NewBikeSearch.base.DriverSetup; // Import base setup class
import Hackathon.NewBikeSearch.utils.ExtentReport; // Import utility classes
import Hackathon.NewBikeSearch.utils.Highlighter;
import Hackathon.NewBikeSearch.utils.ScreenShots;

public class Login extends DriverSetup { // Define a class named "Login" that extends "DriverSetup"

	// Create instances of various utility classes and locators for elements on the
	// webpage
	Highlighter hl = new Highlighter();
	ExtentReport rep = new ExtentReport();
	ScreenShots sss = new ScreenShots();
	By zigWheelsPath = By.xpath("//a[@class='zw i-b mt-10 pt-2 zw-srch-logo']/img");
	By loginPath = By.id("des_lIcon");
	By googlePath = By.xpath("//div[@class='lgn-sc c-p txt-l pl-30 pr-30 googleSignIn']");
	By emailPath = By.xpath("//input[@type='email']");
	By submitPath = By.xpath("//span[normalize-space()='Next']");
	By errorPath = By.xpath("//div[@class='o6cuMc Jj6Lae']");

	public void NavigateToMainPage() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver; // Create a JavaScriptExecutor instance
		js.executeScript("window.scrollTo(0,0);"); // Scroll to the top of the page
		Thread.sleep(1000); // Pause for 1 second
		WebElement zigWheel = driver.findElement(zigWheelsPath); // Find the "ZigWheels" element
		hl.Highlight(driver, zigWheel); // Highlight the element
		Thread.sleep(1000); // Pause for 1 second
		js.executeScript("arguments[0].click();", zigWheel); // Click on the "ZigWheels" element using JavaScript
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); // Set implicit wait timeout
	}

	public void LogIn() throws Exception {
		try {
			logger = extent.createTest("Login/Signup"); // Log the test case in the extent report
			JavascriptExecutor js = (JavascriptExecutor) driver; // Create a JavaScriptExecutor instance
			WebElement logIn = driver.findElement(loginPath); // Find the "Log In" element
			hl.Highlight(driver, logIn); // Highlight the element
			js.executeScript("arguments[0].click();", logIn); // Click on the "Log In" element using JavaScript
			rep.TestPassed("<-- Login/SignUp selected -->"); // Log a test passed message
		} catch (Exception e) {
			String ImgName = "Login"; // Define a screenshot name
			sss.ScreenShotTake(driver, ImgName); // Take a screenshot
			rep.TestFailed("<-- Login/SignUp selection failed -->", ImgName); // Log a test failed message
		}
	}

	public void GoogleLogIn() throws Exception {
		logger = extent.createTest("Login using Google"); // Log the test case in the extent report
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver; // Create a JavaScriptExecutor instance
			String parentWindow = driver.getWindowHandle(); // Get the parent window handle
			WebElement google = driver.findElement(googlePath); // Find the "Google" element
			js.executeScript("arguments[0].click();", google); // Click on the "Google" element using JavaScript

			// Switch to the Google login window
			for (String window : driver.getWindowHandles()) {
				if (!(window.equals(parentWindow))) {
					driver.switchTo().window(window);
					break;
				}
			}

			WebElement email = driver.findElement(emailPath); // Find the email input element
			hl.Highlight(driver, email); // Highlight the element
			js.executeScript("arguments[0].value='sagnik243142@32q4.098';", email); // Fill in the email
			WebElement submit = driver.findElement(submitPath); // Find the "Next" button element
			js.executeScript("arguments[0].click();", submit); // Click on the "Next" button using JavaScript
			rep.TestPassed("<-- Google LogIn option chosen -->"); // Log a test passed message
		} catch (Exception e) {
			String ImgName = "GoogleLogIn"; // Define a screenshot name
			sss.ScreenShotTake(driver, ImgName); // Take a screenshot
			rep.TestFailed("<-- Google LogIn option chosen failed -->", ImgName); // Log a test failed message
		}
	}

	public void InvalidDetailsError() throws Exception {
		logger = extent.createTest("Invalid Login Details"); // Log the test case in the extent report
		try {
			Thread.sleep(2000); // Pause for 2 seconds
			WebElement errorMsg = driver.findElement(errorPath); // Find the error message element
			String msg = errorMsg.getText(); // Get the error message text
			System.err.println(msg); // Print the error message
			String ImgName = "InvalidDetails"; // Define a screenshot name
			sss.ScreenShotTake(driver, ImgName); // Take a screenshot
			rep.TestFailed("<-- Invalid Login Details, Test Case Failed -->", ImgName); // Log a test failed message
		} catch (Exception e) {
			rep.TestPassed("<-- Valid Login Details, Test Case Passed -->"); // Log a test passed message
		}
	}
}
