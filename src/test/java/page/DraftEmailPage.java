package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DraftEmailPage extends AbstractPage {

	@FindBy(css ="span.js-bubble.mail-Bubble")
	private WebElement toField;
	
	@FindBy(xpath ="//input[@name='subj']")
	private WebElement subjectField;
	
	@FindBy(xpath ="//div[@role='textbox']/div")
	private WebElement bodyField;
	
	@FindBy(xpath ="//button[. = 'Send']")
	private WebElement sendButton;
	
	@FindBy(className ="mail-Done")
	private WebElement successMessage;
	
	public DraftEmailPage(WebDriver driver) {
		super(driver);
	}	
	
	public String getEmailAdress(){
		return toField.getAttribute("data-contact-email");
	}
	
	public String getSubject(){
		return subjectField.getAttribute("value");
	}
	
	public String getBody(){
		return bodyField.getText();
	}
	
	public DraftEmailPage sendDraftEmail(){
		sendButton.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(successMessage));
		return this;
	}

}
