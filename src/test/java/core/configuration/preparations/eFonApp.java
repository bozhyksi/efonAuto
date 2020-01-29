package core.configuration.preparations;

import core.workers.DataBaseWorker;
import core.fields.Fields;
import pages.basePage.basePopup.ConfirmationPopup;
import pages.userPage.UserPage;
import pages.basePage.BasePage;
import pages.loginPage.LoginPage;
import pages.userPage.userPagePopup.CreateUserPopup;

public class eFonApp extends PreparationsForRun{

    public Fields fields = new Fields();
    public BasePage basePage = new BasePage();
    public DataBaseWorker dataBaseWorker = new DataBaseWorker();

    public ConfirmationPopup confirmationPopup = new ConfirmationPopup();
    public LoginPage loginPage = new LoginPage();
    public UserPage userPage = new UserPage();
    public CreateUserPopup createUserPopup = new CreateUserPopup();

}
