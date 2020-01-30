package core.configuration.preparations;

import core.fields.Fields;
import core.workers.DataBaseWorker;
import core.workers.ExcelFileWorker;
import pages.basePage.BasePage;
import pages.basePage.basePopup.ConfirmationPopup;
import pages.loginPage.LoginPage;
import pages.userPage.UserPage;
import pages.userPage.userPagePopup.CreateUserPopup;

public class eFonApp extends PreparationsForRun{

    public Fields fields = new Fields();
    public BasePage basePage = new BasePage();
    public DataBaseWorker dataBaseWorker = new DataBaseWorker();
    public ExcelFileWorker excelFileWorker = new ExcelFileWorker();

    public ConfirmationPopup confirmationPopup = new ConfirmationPopup();
    public LoginPage loginPage = new LoginPage();
    public UserPage userPage = new UserPage();
    public CreateUserPopup createUserPopup = new CreateUserPopup();

}
