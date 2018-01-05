package component;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import page.DraftsListPage;
import page.LoginPage;
import page.NewEmailPage;
import page.SentListPage;

public class ToolbarComponent {
	
	private final static String TITLE = "title";
	private final static String DRAFTS = "Drafts";
	private final static String SENT = "Sent";
	private final static String NO_MESSAGES = "There are no messages in “%s”.";
	
	@FindBy(css ="div.mail-User-Name")
	private WebElement userNameField;
	
	@FindBy(linkText ="Compose")
	private WebElement composeButton;
	
	@FindBy(xpath ="//span[contains(text(),'Drafts')]")
	private WebElement draftsLink;
	
	@FindBy(xpath ="//span[contains(text(),'Sent')]")
	private WebElement sentLink;
	
	@FindBy(className ="mail-User-Name")
	private WebElement userMenuDropdown;
	
	@FindBy(linkText ="Log out")
	private WebElement logOutLink;
	
	@FindBy(css =".ns-view-messages-list")
	private WebElement emailsContainer;
	
	@FindBy(css =".ns-view-messages-empty")
	private WebElement emptyEmailsContainer;
	
 	@FindBy(className = "ns-view-messages-item-wrap")
	private List<WebElement> allEmailsList;
	
	private By selectedFolderBy = By.className("mail-NestedList-Item_current");
	
	private By emailsContainerBy = By.className("ns-view-messages-item-wrap");
	
	
	private WebDriver driver;
	
	public ToolbarComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public String getUserName(){
		return userNameField.getText();
	}
	
	public NewEmailPage createNewEmail(){
		composeButton.click();
		return new NewEmailPage(driver);
	}
	
	public DraftsListPage openDraftsFolder(){
		draftsLink.click();
		waitForFolderIsOpened(DRAFTS);
		return new DraftsListPage(driver);
	}
	
	public SentListPage openSentFolder(){
		sentLink.click();
		waitForFolderIsOpened(SENT);
		return new SentListPage(driver);
	}
	
	public LoginPage logOut(){
		userMenuDropdown.click();
		logOutLink.click();
		return new LoginPage(driver);
	}
	
	public int getNumberOfEmailsInFolder(String folderName){
		if (emailsContainer.getText().contains(String.format(NO_MESSAGES, folderName)) 
				&& emptyEmailsContainer.isDisplayed()) { 
			return 0;
		}
		return allEmailsList.size();
	}
	
	public void waitForChangeOfNumberOfEmailsInFolder(int expectedValue){
		new WebDriverWait(driver, 30).until(ExpectedConditions.numberOfElementsToBe(emailsContainerBy, expectedValue));
	}
	
	private void waitForFolderIsOpened(String folderName){
		new WebDriverWait(driver, 10).until(ExpectedConditions.attributeContains(selectedFolderBy, TITLE, folderName));
	}


}

