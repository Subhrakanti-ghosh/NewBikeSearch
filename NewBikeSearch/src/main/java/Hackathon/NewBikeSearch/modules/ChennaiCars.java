package Hackathon.NewBikeSearch.modules; // Import statements for required modules and utilities

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By; // Import necessary Selenium classes
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Hackathon.NewBikeSearch.base.DriverSetup; // Import base setup class
import Hackathon.NewBikeSearch.utils.ExtentReport; // Import utility classes
import Hackathon.NewBikeSearch.utils.Highlighter;
import Hackathon.NewBikeSearch.utils.ScreenShots;
import Hackathon.NewBikeSearch.utils.WriteExcelData;

public class ChennaiCars extends DriverSetup { // Define a class named "ChennaiCars" that extends "DriverSetup"

	// Locators for elements on the webpage
	By usedCarsPath = By.xpath("//a[normalize-space()='Used Cars']");
	By chennaiPath = By.xpath("//span[normalize-space()='Chennai']");
	By popularModelListPath = By.xpath("//ul[@class='zw-sr-secLev usedCarMakeModelList popularModels ml-20 mt-10']");
	By modelListPath = By.tagName("li");

	Highlighter hl = new Highlighter(); // Create an instance of the Highlighter class
	ExtentReport rep = new ExtentReport(); // Create an instance of the ExtentReport class
	ScreenShots sss = new ScreenShots(); // Create an instance of the ScreenShots class

	public void UsedCarsInChennai() throws Exception {
		logger = extent.createTest("Used Cars in Chennai"); // Log the test case in the extent report
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver; // Create a JavaScriptExecutor instance
			js.executeScript("window.scrollTo(0,0);"); // Scroll to the top of the page
			Thread.sleep(2000); // Pause for 2 seconds
			Actions ac = new Actions(driver); // Create an instance of the Actions class
			WebElement usedCars = driver.findElement(usedCarsPath); // Find the "Used Cars" element
			hl.Highlight(driver, usedCars); // Highlight the element
			ac.moveToElement(usedCars).perform(); // Hover over the "Used Cars" element
			Thread.sleep(1000); // Pause for 1 second
			WebElement chennai = driver.findElement(chennaiPath); // Find the "Chennai" element
			hl.Highlight(driver, chennai); // Highlight the element
			js.executeScript("arguments[0].click()", chennai); // Click on the "Chennai" element using JavaScript
			rep.TestPassed("<-- Used Cars in Chennai is Selected -->"); // Log a test passed message
		} catch (Exception e) {
			String ImgName = "usedCars"; // Define a screenshot name
			sss.ScreenShotTake(driver, ImgName); // Take a screenshot
			rep.TestFailed("<-- Used Cars in Chennai is not Selected -->", ImgName); // Log a test failed message
		}
	}

	public void PopularModels() throws Exception {
		logger = extent.createTest("Popular Models in Chennai"); // Log the test case in the extent report
		try {
			WebElement popular = driver.findElement(popularModelListPath); // Find the "Popular Models" element
			JavascriptExecutor js = (JavascriptExecutor) driver; // Create a JavaScriptExecutor instance
			js.executeScript("arguments[0].scrollIntoView(true);", popular); // Scroll to the "Popular Models" element
			Thread.sleep(1000); // Pause for 1 second
			List<WebElement> models = popular.findElements(modelListPath); // Find all "li" elements under "Popular
																			// Models"
			List<String> popularModel = new ArrayList<String>(); // Create a list to store popular model names
			System.out.println("\nPopular Models:"); // Print a header
			WriteExcelData.writeExcelTopCellCars(); // Write headers to an Excel sheet

			// Loop through the first 10 models, print their text, and add them to the list
			for (int i = 0; i < 10; i++) {
				System.out.println(models.get(i).getText());
				popularModel.add(models.get(i).getText());
			}
			WriteExcelData.writeExcelData(popularModel, "Cars"); // Write the popular model data to the Excel sheet
			rep.TestPassed("<-- Popular Models fetched -->"); // Log a test passed message
		} catch (Exception e) {
			String ImgName = "popularModels"; // Define a screenshot name
			sss.ScreenShotTake(driver, ImgName); // Take a screenshot
			rep.TestFailed("<-- Popular Models not fetched -->", ImgName); // Log a test failed message
		}
	}
}
