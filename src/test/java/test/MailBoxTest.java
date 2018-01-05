package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;
import page.DraftEmailPage;
import page.DraftsListPage;
import page.InboxListPage;
import page.SentListPage;
import util.Util;

public class MailBoxTest extends BaseTest {
	
	
	private final static String USER_NAME = "module5testmailbox";
	private final static String PASSWORD = "qwerty12345";
	private final static String EMAIL_ADRESS = "qwerty12345@yandex.com";
	private final static String EMAIL_SUBJECT = Util.getRandomEmailSubject();
	private final static String EMAIL_BODY = "Hello! I'm a test email";
	private final static String DRAFTS = "Drafts";
	private final static String SENT = "Sent";
	private final static int NUMBER_OF_EMAILS = 3;

	/**Login to the mail box -> assert, that the login is successful -> create a new mail (fill addressee, subject and body fields) ->
	   save the mail as a draft -> verify, that the mail presents in ‘Drafts’ folder -> verify the draft content 
	   (addressee, subject and body – should be the same as in 3) -> send the mail -> verify, that the mail disappeared from ‘Drafts’ folder ->
		verify, that the mail is in ‘Sent’ folder -> Log off.*/

	@Test
	public void draftsFolderTest() {
		InboxListPage inboxListPage = doLogIn().fillInUser(USER_NAME).fillInPassword(PASSWORD).clickLoginButton();
		assertEquals(inboxListPage.toolbarComponent.getUserName(), USER_NAME);
		int numberEmailsInDrafts = inboxListPage.toolbarComponent.openDraftsFolder().toolbarComponent.
				getNumberOfEmailsInFolder(DRAFTS);
		int numberEmailsInSent = inboxListPage.toolbarComponent.openSentFolder().toolbarComponent.
				getNumberOfEmailsInFolder(SENT);
		DraftsListPage draftsListPage = inboxListPage.toolbarComponent.createNewEmail().
				fillInEmailAdress(EMAIL_ADRESS).fillInEmailSubject(EMAIL_SUBJECT).
				fillInEmailBody(EMAIL_BODY).closeEmailAndSaveToDraft().toolbarComponent.openDraftsFolder();
		draftsListPage.toolbarComponent.waitForChangeOfNumberOfEmailsInFolder(numberEmailsInDrafts+1);
		assertTrue(draftsListPage.isEmailPresentInDraftsList(EMAIL_SUBJECT));
		DraftEmailPage draftEmailPage = draftsListPage.openEmailBySubject(EMAIL_SUBJECT);
		assertEquals(draftEmailPage.getEmailAdress(), EMAIL_ADRESS);
		assertEquals(draftEmailPage.getSubject(), EMAIL_SUBJECT);
		assertEquals(draftEmailPage.getBody(), EMAIL_BODY);
		draftEmailPage.sendDraftEmail().toolbarComponent.openDraftsFolder().toolbarComponent.
		waitForChangeOfNumberOfEmailsInFolder(numberEmailsInDrafts);
		assertFalse(draftsListPage.isEmailPresentInDraftsList(EMAIL_SUBJECT));
		SentListPage sentListPage = draftsListPage.toolbarComponent.openSentFolder();
		sentListPage.toolbarComponent.waitForChangeOfNumberOfEmailsInFolder(numberEmailsInSent+1);
		assertTrue(sentListPage.isEmailPresentInSentList(EMAIL_SUBJECT));
	}
	
	/** Create 3 new  emails and send them -> go to 'Sent' folder -> multi-select ALL emails (shift + LM)
	 -> drag and drop to trash -> highlight empty folder message and make screenshot -> check 'Sent' folder is empty 
	 -> Log off.**/
 	@Test
 	public void advanceActionsTest(){
		InboxListPage inboxListPage = doLogIn().fillInUser(USER_NAME).fillInPassword(PASSWORD).clickLoginButton();
		for (int i=0; i<NUMBER_OF_EMAILS; i++){
			String emailSubject = Util.getRandomEmailSubject();
			inboxListPage.toolbarComponent.createNewEmail().fillInEmailAdress(EMAIL_ADRESS).fillInEmailSubject(emailSubject).
			fillInEmailBody(EMAIL_BODY).sendEmail();	
		}
		SentListPage sentListPage = inboxListPage.toolbarComponent.openSentFolder().selectAllEmailsByAdvanceActions().
		moveEmailsIntoTrashByDragAndDrop().highlightElementeAndTakeScreenshot();
		assertTrue(sentListPage.isSentFolderEmpty());
	}
	
}
