package tests.loginPageTest;

import core.configuration.customListeners.CustomListeners;
import flow.BaseTestMethods;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)

public class LoginPageTests extends BaseTestMethods {

    @Test(groups = {"regression", "smoke", "loginPageTests"})
    public void loginTest(){
        loginPage.fillInLogin(getLogin());
        loginPage.fillInPassword(getPassword());
        loginPage.clickLoginButton();


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
