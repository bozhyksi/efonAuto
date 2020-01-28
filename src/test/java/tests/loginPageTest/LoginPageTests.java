package tests.loginPageTest;

import com.codeborne.selenide.Condition;
import core.configuration.customListeners.CustomListeners;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.Allure.step;

@Listeners(CustomListeners.class)

public class LoginPageTests extends BaseTestMethods {

    @Description("Verify if user can Login/LogOut with correct credentials")
    @Test(testName = "Verify if user can Login/LogOut with correct credentials",groups = {"regression", "smoke", "loginPageTests"})
    public void loginTest(){
        step("Fill in Login");
        loginPage.fillInLogin(getLogin());

        step("Fill in Pass");
        loginPage.fillInPassword(getPassword());

        step("Click Login button");
        loginPage.clickLoginButton();

        step("Verify if user was logged in");
        basePage.getButtonLogout().shouldBe(visible);
        basePage.getMainMenu().shouldBe(visible);

        step("Click Logout");
        basePage.clickButtonLogout();

        step("Verify if user was logged out");
        loginPage.getButtonLogin().shouldBe(visible);
        loginPage.getInputLoginField().shouldBe(visible);
        loginPage.getInputPasswordField().shouldBe(visible);
    }
}
