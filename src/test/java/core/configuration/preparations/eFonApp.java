package core.configuration.preparations;

import core.fields.Fields;
import core.workers.dbWorker.DataBaseWorker;
import core.workers.excelWorker.ExcelFileWriter;
import pages.basePage.BasePage;
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
    public ExcelFileWriter excelFileWriter = new ExcelFileWriter();

    public ConfirmationPopup confirmationPopup = new ConfirmationPopup();
    public LoginPage loginPage = new LoginPage();
    public UserPage userPage = new UserPage();
    public CreateUserPopup createUserPopup = new CreateUserPopup();
    public ConfigureUserBasePopup configureUserBasePopup = new ConfigureUserBasePopup();
    public NameTabConfigUserPopup nameTabConfigUserPopup = new NameTabConfigUserPopup();
    public PhonebookPage phonebookPage = new PhonebookPage();

}
