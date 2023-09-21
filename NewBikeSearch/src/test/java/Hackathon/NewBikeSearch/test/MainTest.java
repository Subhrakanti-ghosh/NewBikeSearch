package Hackathon.NewBikeSearch.test; // Import necessary modules

import java.util.Scanner; // Import Scanner class

import org.testng.annotations.AfterTest; // Import AfterTest annotation
import org.testng.annotations.BeforeTest; // Import BeforeTest annotation
import org.testng.annotations.Test; // Import Test annotation

import Hackathon.NewBikeSearch.base.DriverSetup; // Import base setup class
import Hackathon.NewBikeSearch.modules.ChennaiCars; // Import module class ChennaiCars
import Hackathon.NewBikeSearch.modules.HondaBikes; // Import module class HondaBikes
import Hackathon.NewBikeSearch.modules.Login; // Import module class Login

public class MainTest extends DriverSetup { // Define a class named "MainTest" that extends "DriverSetup"

	// Create instances of necessary classes
	DriverSetup dr = new DriverSetup();
	HondaBikes lc = new HondaBikes();
	ChennaiCars cc = new ChennaiCars();
	Login lg = new Login();

	// Method to run before tests start
	@BeforeTest(alwaysRun = true)
	public void beforeRun() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter 1 for Chrome...\nEnter 2 for Edge...");
		int ch = sc.nextInt(); // Read user input for browser choice
		dr.setupDriver(ch); // Setup driver based on user choice
		dr.getURL(); // Get the URL for the web page
		sc.close(); // Close the scanner
	}

	// Test method for bike-related tests
	@Test(groups = "SmokeTest", priority = 1)
	public void TestBike() throws Exception {
		lc.SearchUpcomingBikes(); // Perform search for upcoming bikes
		lc.SearchForHondaBikes(); // Search for Honda bikes
		lc.SearchHondaUnder4lakh(); // Search for Honda bikes under 4 lakh
	}

	// Test method for car-related tests
	@Test(groups = "Regression", priority = 2)
	public void TestCar() throws Exception {
		cc.UsedCarsInChennai(); // Search for used cars in Chennai
		cc.PopularModels(); // Get popular car models
	}

	// Test method for login-related tests
	@Test(priority = 3)
	public void TestLogin() throws Exception {
		lg.NavigateToMainPage(); // Navigate to the main page
		lg.LogIn(); // Perform login
		lg.GoogleLogIn(); // Perform Google login
		lg.InvalidDetailsError(); // Test invalid login details
	}

	// Method to run after all tests are completed
	@AfterTest(alwaysRun = true)
	public void closeTest() {
		dr.quitDriver(); // Quit the driver
	}
}
