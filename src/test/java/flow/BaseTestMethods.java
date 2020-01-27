package flow;

import core.configuration.preparations.eFonApp;

public class BaseTestMethods extends eFonApp {

    public void login(){
        loginPage.fillInLogin(getLogin());
        loginPage.fillInPassword(getPassword());
        loginPage.clickLoginButton();
    }
}
