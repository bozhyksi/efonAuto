package core.configuration.preparations;

import core.fields.Fields;
import core.workers.dbWorker.DataBaseWorker;
import core.workers.excelWorker.ExcelFileWorker;
import pages.abbreviatedDialling.AbbreviatedDiallingBasePage;
import pages.abbreviatedDialling.AbbreviatedNumbers;
import pages.abbreviatedDialling.ManageAbbreviatedNumbers;
import pages.abbreviatedDialling.abbreviatedDiallingPopup.AssignAbbreviatedDialling;
import pages.basePage.BasePage;
import pages.basePage.basePopup.AlertPopup;
import pages.basePage.basePopup.ConfirmationPopup;
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
}
