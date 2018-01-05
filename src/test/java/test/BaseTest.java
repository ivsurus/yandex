package test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import java.net.URL;  

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;


import component.ToolbarComponent;
import page.LoginPage;

public abstract class BaseTest {
	
	private final static String URL = "http://yandex.com";
	
	protected WebDriver driver;
	
	@BeforeMethod
	protected void setUp(){
		ChromeOptions options = new ChromeOptions();
		try {
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
		} catch (MalformedURLException e) {
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(11, TimeUnit.SECONDS);
	}
	
	protected LoginPage doLogIn(){
		driver.get(URL);
		return new LoginPage(driver);
	}
	
	@AfterMethod
	protected void doLogOut(){
		new ToolbarComponent(driver).logOut();
		driver.close();
	}
	
	@AfterSuite
	protected void tearDown(){
		driver.quit();
	}

}
