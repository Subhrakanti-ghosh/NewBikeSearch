package Hackathon.NewBikeSearch.utils; // Import necessary modules

import java.io.File; // Import File class
import java.io.IOException; // Import IOException class

import org.apache.commons.io.FileUtils; // Import FileUtils class from Apache Commons IO library
import org.openqa.selenium.OutputType; // Import OutputType enum
import org.openqa.selenium.TakesScreenshot; // Import TakesScreenshot interface
import org.openqa.selenium.WebDriver; // Import WebDriver class

public class ScreenShots { // Define a class named "ScreenShots"

	// Method to take a screenshot of the web page
	public void ScreenShotTake(WebDriver driver, String ImgName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver; // Create a TakesScreenshot instance
		File file = ts.getScreenshotAs(OutputType.FILE); // Capture the screenshot as a file
		try {
			FileUtils.copyFile(file, new File("./screenshots/" + ImgName + ".png")); // Save the screenshot to a file
		} catch (IOException e) {
			e.printStackTrace(); // Print the stack trace in case of an IOException
		}
	}
}
