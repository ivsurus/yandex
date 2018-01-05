package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewEmailPage extends AbstractPage {

	@FindBy(name ="to")
	private WebElement toField;
	
	@FindBy(name ="subj")
	private WebElement subjectField;
	
	@FindBy(xpath ="//div[@role='textbox']")
	private WebElement bodyField;
	
	@FindBy(xpath ="//div[@title = 'Close']")
	private WebElement closeSign;
	
	@FindBy(xpath ="//span[contains(text(), 'Save and go')]")
	private WebElement saveAndGoButton;
	
	@FindBy(xpath ="//button[. = 'Send']")
	private WebElement sendButton;
	
	@FindBy(className ="mail-Done")
	private WebElement successMessage;
	
	
	public NewEmailPage(WebDriver driver) {
		super(driver);
	}

	public NewEmailPage fillInEmailAdress(String adress){
		toField.sendKeys(adress);
		return this;
	}
	
	public NewEmailPage fillInEmailSubject(String subject){
		subjectField.sendKeys(subject);
		return this;
	}
	
	public NewEmailPage fillInEmailBody(String body){
		bodyField.sendKeys(body);
		return this;
	}
	
	public String getEmailAdress(){
		return toField.getText();
	}
	public String getSubject(){
		return subjectField.getText();
	}
	public String getBody(){
		return bodyField.getText();
	}
	
	public InboxListPage closeEmailAndSaveToDraft(){
		closeSign.click();
		saveAndGoButton.click();
		return new InboxListPage(driver);
	}
	
	public InboxListPage sendEmail(){
		sendButton.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(successMessage));
		return new InboxListPage(driver);
	}
}
