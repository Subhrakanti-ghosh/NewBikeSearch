package Hackathon.NewBikeSearch.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Hackathon.NewBikeSearch.utils.ExtentReport;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup {
	public Properties prop;
	public String url;
	public static WebDriver driver;

	public static ExtentReports extent = ExtentReport.getInstance();
	public static ExtentTest logger;

	public WebDriver setupDriver(int ch) {

		try {
			switch (ch) {
			case 1:
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				break;
			case 2:
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				driver.manage().window().maximize();
				break;
			default:
				System.err.println("Please enter a valid option");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

	public void getURL() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream("./resources/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		url = prop.getProperty("url");
		System.out.println(url);
		driver.get(url);
	}

	public void quitDriver() {
		extent.flush();
		driver.quit();
	}
}
