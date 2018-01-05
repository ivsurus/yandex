package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DraftsListPage extends AbstractPage{
	
	private By allSubjects = By.xpath("//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_subject']/span");
	
	public DraftsListPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean isEmailPresentInDraftsList(String subject){
		List<WebElement> allSubjectsInDrafts = driver.findElements(allSubjects);
		for(WebElement subjectElement: allSubjectsInDrafts){
			if(subjectElement.getText().equals(subject)){
				return true;
			}
		}
		return false;
	}
	
	public DraftEmailPage openEmailBySubject(String subject){
		List<WebElement> allSubjectsInDrafts = driver.findElements(allSubjects);
		for(WebElement subjectElement: allSubjectsInDrafts){
			if(subjectElement.getText().equals(subject)){
				subjectElement.click();
				break;
			}
		}
		return new DraftEmailPage(driver);
	}
}

