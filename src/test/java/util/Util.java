package util;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.commons.io.FileUtils;

public class Util {

	public static String getRandomEmailSubject(){
		int  n = new Random().nextInt(1000001) + 1;
		return "testEmail"+ n;
	}
	
	public static void highlightElementAndMakeScreenshot(WebDriver driver, WebElement element) {
		String backgound = element.getCssValue("backgroundColor");
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + "yellow" + "'", element);
		makeScreenshot(driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + backgound + "'", element);
	}
	
	public static void makeScreenshot(WebDriver driver) {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFileToDirectory(screenshot, new File("src\\test\\resources\\screenshots\\"));
		} catch (IOException e){
		}
	}
	
	
}
