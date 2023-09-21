package Hackathon.NewBikeSearch.modules; // Import necessary modules

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By; // Import necessary Selenium classes
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import Hackathon.NewBikeSearch.base.DriverSetup; // Import base setup class
import Hackathon.NewBikeSearch.utils.ExtentReport; // Import utility classes
import Hackathon.NewBikeSearch.utils.Highlighter;
import Hackathon.NewBikeSearch.utils.ScreenShots;
import Hackathon.NewBikeSearch.utils.WriteExcelData;

public class HondaBikes extends DriverSetup { // Define a class named "HondaBikes" that extends "DriverSetup"

	// Create instances of various utility classes and locators for elements on the
	// webpage
	Highlighter hl = new Highlighter();
	ExtentReport rep = new ExtentReport();
	ScreenShots sss = new ScreenShots();
	By newBikePath = By.xpath("//a[text() ='New Bikes']");
	By upcommingBikePath = By.xpath("//span[normalize-space()='Upcoming Bikes']");
	By manufacturerPath = By.id("makeId");
	By showMorePath = By.xpath("//span[normalize-space()='View More Bikes']");
	By modelNamePath = By.xpath("//strong[@class='lnk-hvr block of-hid h-height']");
	By priceTextPath = By.xpath("//div[@class='b fnt-15']");
	By launchDatePath = By.xpath("//div[@class='clr-try fnt-14']");

	public void SearchUpcomingBikes() throws Exception {

		logger = extent.createTest("Search for Upcoming Bikes"); // Log the test case in the extent report
		try {
			Actions ac = new Actions(driver); // Create an instance of the Actions class
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // Set implicit wait timeout
			WebElement newBikes = driver.findElement(newBikePath); // Find the "New Bikes" element
			hl.Highlight(driver, newBikes); // Highlight the element
			ac.moveToElement(newBikes).perform(); // Hover over the "New Bikes" element
			WebElement upcomingBikes = driver.findElement(upcommingBikePath); // Find the "Upcoming Bikes" element
			hl.Highlight(driver, upcomingBikes); // Highlight the element
			Thread.sleep(1500); // Pause for 1.5 seconds
			upcomingBikes.click(); // Click on the "Upcoming Bikes" element
			rep.TestPassed("<-- Search for Upcoming Bikes Passed -->"); // Log a test passed message
		} catch (Exception e) {
			String ImgName = "upcommingBike"; // Define a screenshot name
			sss.ScreenShotTake(driver, ImgName); // Take a screenshot
			rep.TestFailed("<-- Search for Upcoming Bikes Failed -->", ImgName); // Log a test failed message
		}
	}

	public void SearchForHondaBikes() throws Exception {

		logger = extent.createTest("Searched for Honda Bikes"); // Log the test case in the extent report
		try {
			WebElement manufacturer = driver.findElement(manufacturerPath); // Find the "Manufacturer" element
			Select selHonda = new Select(manufacturer); // Create a Select object for dropdown selection
			hl.Highlight(driver, manufacturer); // Highlight the element
			selHonda.selectByValue("53"); // Select Honda from the dropdown by value
			Thread.sleep(1500); // Pause for 1.5 seconds
			rep.TestPassed("<-- Search for Honda Bikes Passed -->"); // Log a test passed message
		} catch (Exception e) {
			String ImgName = "hondaBikes"; // Define a screenshot name
			sss.ScreenShotTake(driver, ImgName); // Take a screenshot
			rep.TestFailed("<-- Search for Honda Bikes Failed -->", ImgName); // Log a test failed message
		}
	}

	public void SearchHondaUnder4lakh() throws Exception {

		logger = extent.createTest("Searched Honda Bikes under 4 Lakhs"); // Log the test case in the extent report
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver; // Create a JavaScriptExecutor instance
			WebElement showMore = driver.findElement(showMorePath); // Find the "View More Bikes" element
			js.executeScript("arguments[0].scrollIntoView(true)", showMore); // Scroll to the element
			Thread.sleep(1000); // Pause for 1 second
			js.executeScript("arguments[0].click()", showMore); // Click on the "View More Bikes" element using
																// JavaScript

			// Find elements for model names, price texts, and launch dates
			List<WebElement> modelName = driver.findElements(modelNamePath);
			List<WebElement> priceText = driver.findElements(priceTextPath);
			List<WebElement> launchDate = driver.findElements(launchDatePath);
			List<Float> under4 = new ArrayList<Float>(); // Create a list to store prices

			int countBike = modelName.size(); // Get the count of bikes
			System.out.println("Total Bikes: " + countBike); // Print the total number of bikes

			WriteExcelData.writeExcelTopCellBikes(); // Write headers to an Excel sheet
			int row = 1;
			System.out.println("Bikes under 4 lakh: ");
			int count = 0;

			// Loop through bikes to extract information and write to Excel
			for (int i = 0; i < priceText.size(); i++) {
				// Convert price text to float
				float cost = Float.parseFloat(
						priceText.get(i).getText().replaceAll("Rs. ", "").replaceAll(" Lakh", "").replaceAll(",", ""));
				String model = modelName.get(i).getText(); // Get model name
				String launch = launchDate.get(i).getText(); // Get launch date

				// Special handling for a specific cost value
				if (cost == 79000) {
					cost = (79000.0f / 100000.0f);
				}

				// Check if the cost is under 4 lakh
				if (cost < 4) {
					under4.add(cost);
					System.out.println("[Model: " + model + " Cost: " + cost + " Launch: " + launch + "]");
					String[] arr = new String[3];
					arr[0] = model;
					arr[1] = priceText.get(i).getText();
					arr[2] = launch;
					WriteExcelData.writeExcelData(arr, row++, "Bikes");
					count++;
				}
			}
			System.out.println("Bikes Under 4 Lakh: " + count);
			rep.TestPassed("<-- Honda Bikes under 4 lakhs fetched -->"); // Log a test passed message
		} catch (Exception e) {
			String ImgName = "under4lakhs"; // Define a screenshot name
			sss.ScreenShotTake(driver, ImgName); // Take a screenshot
			rep.TestFailed("<-- Bikes under 4 lakhs has not been fetched -->", ImgName); // Log a test failed message
		}
	}
}
