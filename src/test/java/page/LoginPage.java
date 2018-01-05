package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {
	
	@FindBy(name ="login")
	private WebElement loginField;
    
    @FindBy(name ="passwd")
	private WebElement passwordField;
    
    @FindBy(className ="auth__button")
    private WebElement loginButton;
	
    public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public LoginPage fillInUser(String login){
		loginField.sendKeys(login);
		return this;
	}
	
	public LoginPage fillInPassword(String password){
		passwordField.sendKeys(password);
		return this;
	}
	
	public InboxListPage clickLoginButton(){
		loginButton.click();
		return new InboxListPage(driver);
	}

}
