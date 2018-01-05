package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import component.ToolbarComponent;

public abstract class AbstractPage {
		
		public ToolbarComponent toolbarComponent;
		
		protected WebDriver driver;
	    
	    public AbstractPage(WebDriver driver) {
	        this.driver = driver;	     
	        PageFactory.initElements(this.driver, this);  
	       	toolbarComponent = new ToolbarComponent(driver);  
	    }

}

