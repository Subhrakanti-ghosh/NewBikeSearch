package Hackathon.NewBikeSearch.utils; // Import necessary modules

import com.aventstack.extentreports.ExtentReports; // Import ExtentReports class
import com.aventstack.extentreports.Status; // Import Status enum
import com.aventstack.extentreports.reporter.ExtentHtmlReporter; // Import ExtentHtmlReporter class

import Hackathon.NewBikeSearch.base.DriverSetup; // Import base setup class

public class ExtentReport extends DriverSetup { // Define a class named "ExtentReport" that extends "DriverSetup"

	private static ExtentReports extent; // Static instance of ExtentReports
	public static int i = 0; // A static variable for counting

	// Method to get an instance of ExtentReports
	public static ExtentReports getInstance() {
		if (extent == null) {
			extent = createInstance("./Reports/htmlExtentReport.html"); // Create instance if not exists
		}
		return extent;
	}

	// Method to create an instance of ExtentReports
	private static ExtentReports createInstance(String fileName) {
		// Create an HTML reporter to generate the Extent report.
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);

		// Create a new instance of ExtentReports and attach the HTML reporter to it.
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		return extent;
	}

	// Method to log test passed status
	public void TestPassed(String msg) {
		logger.log(Status.PASS, msg); // Log a passed status with the provided message
	}

	// Method to log test failed status with a screenshot
	public void TestFailed(String msg, String ImgName) throws Exception {
		logger.log(Status.FAIL, msg); // Log a failed status with the provided message
		logger.addScreenCaptureFromPath("..\\screenshots\\" + ImgName + ".png"); // Add a screenshot to the log
	}
}
