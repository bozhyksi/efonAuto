package core.configuration.preparations;

import core.dbWorker.DataBaseWorker;
import core.fields.Fields;
import pages.UserPage.UserPage;
import pages.basePage.BasePage;
import pages.loginPage.LoginPage;

public class eFonApp extends PreparationsForRun{

    public Fields fields = new Fields();
    public BasePage basePage = new BasePage();
    public DataBaseWorker dataBaseWorker = new DataBaseWorker();

    public LoginPage loginPage = new LoginPage();
    public UserPage userPage = new UserPage();


}
