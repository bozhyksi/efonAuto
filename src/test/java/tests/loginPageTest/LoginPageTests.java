package tests.loginPageTest;

import core.configuration.customListeners.CustomListeners;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;

@Listeners(CustomListeners.class)

public class LoginPageTests extends BaseTestMethods {

    @Description("Verify if user can Login with correct credentials")
    @Test(groups = {"regression", "smoke", "loginPageTests"})
    public void loginTest(){
        step("Fill in Login");
        loginPage.fillInLogin(getLogin());

        step("Fill in Pass");
        loginPage.fillInPassword(getPassword());

        step("Click Login button");
        loginPage.clickLoginButton();


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
