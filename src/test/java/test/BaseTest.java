package test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import component.ToolbarComponent;
import page.LoginPage;


/*The main goal of the grid is to distribute the single test sent to it to a single node
that matches the requested capabilities.  It will not ask all connected node that can
execute the test to do so but rather send it to the first it found that can handle
the test.*/


public abstract class BaseTest {
	
	private final static String URL = "http://yandex.com";
	
	protected WebDriver driver;
	
	@Parameters({"browser", "platform", "version"})
	@BeforeMethod(alwaysRun=true)
	protected void setUp(String browser, String platform, String version){
		try{
			URL url = new URL("http://localhost:4444/wd/hub");
		switch (browser){
		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setCapability("platform", Platform.valueOf(platform));
			chromeOptions.setCapability("version", version);
		driver = new RemoteWebDriver(url, chromeOptions);
			break;
		case "edge":
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.setCapability("platform", Platform.valueOf(platform));
			edgeOptions.setCapability("version", version);
		driver = new RemoteWebDriver(url, edgeOptions);
			break;
		}
		}catch (MalformedURLException e) {	
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
