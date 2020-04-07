package tests.loginPageTest;

import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import core.customListeners.CustomListeners;

import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;

@Listeners(CustomListeners.class)

public class LoginPageTests extends BaseTestMethods {

    @Description("Verify if user can Login/LogOut with correct credentials")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "loginPageTests"})
    public void loginTest(){
        step("Fill in Login");
        loginPage.fillInLogin(getLogin());

        step("Fill in Pass");
        loginPage.fillInPassword(getPassword());

        step("Click Login button");
        //loginPage.getButtonLogin().click();

        step("Verify if user was logged in");
        basePage.getButtonLogout().shouldBe(visible);
        basePage.formAdministration().shouldBe(visible);

        step("Click Logout");
        basePage.getButtonLogout().click();

        step("Verify if user was logged out");
        loginPage.getButtonLogin().shouldBe(visible);
        loginPage.getInputLoginField().shouldBe(visible);
        loginPage.getInputPasswordField().shouldBe(visible);
    }

    @Description("Verify if user can NOT login with incorrect credentials")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "loginPageTests"})
    public void VerifyIfUserCanNotLoginWithIncorrectCredentials(){
        step("Fill in Login with incorrect data");
        loginPage.fillInLogin(getRandomEmail());

        step("Fill in Pass with incorrect data");
        loginPage.fillInPassword(getRandomPassword());

        step("Click Login button");
        loginPage.getButtonLogin().click();
        loginPage.getButtonLogin().click(); // workaround, need to be investigated. If leave only one click - it does not work!

        step("Verify if user was NOT logged");
        loginPage.getButtonLogin().shouldBe(visible);
        loginPage.getInputLoginField().shouldBe(visible);
        loginPage.getInputPasswordField().shouldBe(visible);
        loginPage.getMessageLoginError().shouldBe(visible);
    }
}