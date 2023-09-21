package Hackathon.NewBikeSearch.utils; // Import necessary modules

import org.openqa.selenium.JavascriptExecutor; // Import JavascriptExecutor class
import org.openqa.selenium.WebDriver; // Import WebDriver class
import org.openqa.selenium.WebElement; // Import WebElement class

public class Highlighter { // Define a class named "Highlighter"

	// Method to highlight a WebElement using JavaScriptExecutor
	public void Highlight(WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver; // Create a JavaScriptExecutor instance
		// Execute JavaScript to add a red border and yellow background to the element
		js.executeScript("arguments[0].setAttribute('style','border:2px solid red; background:yellow');", ele);
	}
}
