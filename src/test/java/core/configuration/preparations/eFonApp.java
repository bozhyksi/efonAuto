package core.configuration.preparations;

import core.fields.Fields;
import core.workers.dbWorker.DataBaseWorker;
import core.workers.excelWorker.ExcelFileWorker;
import pages.IVRpage.IVRpage;
import pages.IVRpage.IVRpagePopup.IVRpagePopup;
import pages.abbreviatedDialling.AbbreviatedDiallingBasePage;
import pages.abbreviatedDialling.AbbreviatedNumbers;
import pages.abbreviatedDialling.ManageAbbreviatedNumbers;
import pages.abbreviatedDialling.abbreviatedDiallingPopup.AssignAbbreviatedDialling;
import pages.basePage.BasePage;
import pages.basePage.basePopup.AlertPopup;
import pages.basePage.basePopup.ConfirmationPopup;
import pages.callForwardingPage.CallForwardingPage;
import pages.callPickUpPage.CallPickUpPage;
import pages.callPickUpPage.callPickUpPopup.GroupCallPickupPopup;
import pages.conferenceCallsPage.ConferenceCallsPage;
import pages.conferenceCallsPage.conferenceCallsPagePopup.CreateNewConferenceCallPopup;
import pages.faxPage.FaxPage;
import pages.huntGroupPage.HuntGroupPage;
import pages.huntGroupPage.huntGroupPopup.AddFullDaysPopup;
import pages.huntGroupPage.huntGroupPopup.CreateHuntGroupPopup;
import pages.loginPage.LoginPage;
import pages.phonebookPage.PhonebookPage;
import pages.userPage.UserPage;
import pages.userPage.userPagePopup.CreateUserPopup;
import pages.userPage.userPagePopup.configureUser.ConfigureUserBasePopup;
import pages.userPage.userPagePopup.configureUser.NameTabConfigUserPopup;

public class eFonApp extends PreparationsForRun{

    public Fields fields = new Fields();
    public BasePage basePage = new BasePage();
    public DataBaseWorker dataBaseWorker = new DataBaseWorker();
    public ExcelFileWorker excelFileWorker = new ExcelFileWorker();

    public ConfirmationPopup confirmationPopup = new ConfirmationPopup();
    public AlertPopup alertPopup = new AlertPopup();
    public LoginPage loginPage = new LoginPage();
    public UserPage userPage = new UserPage();
    public CreateUserPopup createUserPopup = new CreateUserPopup();
    public ConfigureUserBasePopup configureUserBasePopup = new ConfigureUserBasePopup();
    public NameTabConfigUserPopup nameTabConfigUserPopup = new NameTabConfigUserPopup();
    public PhonebookPage phonebookPage = new PhonebookPage();
    public AbbreviatedDiallingBasePage abbrevDialBasePage = new AbbreviatedDiallingBasePage();
    public AssignAbbreviatedDialling popupAssignAbbrevDial = new AssignAbbreviatedDialling();
    public ManageAbbreviatedNumbers manageAbbrevNumbersPage = new ManageAbbreviatedNumbers();
    public AbbreviatedNumbers abbreviatedNumbers = new AbbreviatedNumbers();
    public FaxPage faxPage = new FaxPage();
    public CallPickUpPage callPickUpPage = new CallPickUpPage();
    public GroupCallPickupPopup groupCallPickupPopup = new GroupCallPickupPopup();
    public CallForwardingPage callForwardingPage = new CallForwardingPage();
    public ConferenceCallsPage conferenceCallsPage = new ConferenceCallsPage();
    public CreateNewConferenceCallPopup createNewConfCallPopup = new CreateNewConferenceCallPopup();
    public IVRpage ivrPage = new IVRpage();
    public IVRpagePopup ivrPagePopup = new IVRpagePopup();
    public HuntGroupPage huntGroupPage = new HuntGroupPage();
    public AddFullDaysPopup addFullDaysPopup = new AddFullDaysPopup();
    public CreateHuntGroupPopup createHuntGroupPopup = new CreateHuntGroupPopup();
}
