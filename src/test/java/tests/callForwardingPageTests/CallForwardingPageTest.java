package tests.callForwardingPageTests;

import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.userPageTests.userPageTestData.User;

import static io.qameta.allure.Allure.step;

@Listeners(CustomListeners.class)

public class CallForwardingPageTest extends BaseTestMethods {

    @Description("Verify if user can configure After section in call forwarding")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "callForwardingPage"})
    public void VerifyIfUserCanConfigureAfterSectionInCallForwarding(){
        step("Prepare test data");
        String delay = getRandomNumber(2);
        User user = new User();
        String forwardToPhone = getRandomPhone();

        step("Log in the system");
        login();

        step("Create new user");
        createUser(user);

        step("Goto forwarding tab");
        basePage.getTabCallForwarding().click();

        step("Select number");
        callForwardingPage.getDropdownMyNumbers().selectOptionContainingText(user.getPhoneNumber());

        step("Activate \"After\" checkbox");
        callForwardingPage.getCheckboxAfter().click();

        step("Fill in delay");
        callForwardingPage.getInputDelay().setValue(delay);

        step("Select value from \"ForwardTo\" dropdown");
        callForwardingPage.getDropdownAfterForwardTo().selectOptionContainingText("Phone");

        step("Fill in phone field");
        callForwardingPage.getInputAfterPhone().setValue(forwardToPhone);

        step("Save changes");
        callForwardingPage.getButtonSave().click();
        waitUntilAlertDisappear();

        step("Refresh page");
        refreshPage();

        step("Verify if all entered data was saved");
        callForwardingPage.getDropdownMyNumbers().selectOptionContainingText(user.getPhoneNumber());
        callForwardingPage.getInputDelay().shouldHave(Condition.value(delay));
        callForwardingPage.getDropdownAfterForwardTo().getSelectedOption().shouldHave(Condition.text("Phone"));
        callForwardingPage.getInputAfterPhone().shouldHave(Condition.value(forwardToPhone));

        step("Delete test data - delete user");
        deleteUser(user);
    }

    @Description("Verify if user can configure \"If busy\" section in call forwarding")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "callForwardingPage"})
    public void VerifyIfUserCanConfigureIfBusySectionInCallForwarding(){
        step("Prepare test data");
        String delay = getRandomNumber(2);
        User user = new User();
        String forwardToPhone = getRandomPhone();

        step("Log in the system");
        login();

        step("Create new user");
        createUser(user);

        step("Goto forwarding tab");
        basePage.getTabCallForwarding().click();

        step("Select number");
        callForwardingPage.getDropdownMyNumbers().selectOptionContainingText(user.getPhoneNumber());

        step("Activate \"If Busy\" checkbox");
        callForwardingPage.getCheckboxIfbusy().click();

        step("Select value from \"ForwardTo\" dropdown");
        callForwardingPage.getDropdownIfbusyForwardTo().selectOption("Phone");

        step("Fill in phone field");
        callForwardingPage.getInputIfbusyPhone().setValue(forwardToPhone);

        step("Save changes");
        callForwardingPage.getButtonSave().click();
        waitUntilAlertDisappear();

        step("Refresh page");
        refreshPage();

        step("Verify if all entered data was saved");
        callForwardingPage.getDropdownMyNumbers().selectOptionContainingText(user.getPhoneNumber());
        callForwardingPage.getDropdownIfbusyForwardTo().getSelectedOption().shouldHave(Condition.text("Phone"));
        callForwardingPage.getInputIfbusyPhone().shouldHave(Condition.value(forwardToPhone));

        step("Delete test data - delete user");
        deleteUser(user);
    }

    @Description("Verify if user can configure \"If end device unavailable (not registered)\" section in call forwarding")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "callForwardingPage"})
    public void VerifyIfUserCanConfigureIfEndDeviceUnavailableSectionInCallForwarding(){
        step("Prepare test data");
        String delay = getRandomNumber(2);
        User user = new User();
        String forwardToPhone = getRandomPhone();

        step("Log in the system");
        login();

        step("Create new user");
        createUser(user);

        step("Goto forwarding tab");
        basePage.getTabCallForwarding().click();

        step("Select number");
        callForwardingPage.getDropdownMyNumbers().selectOptionContainingText(user.getPhoneNumber());

        step("Activate \"If end device unavailable (not registered)\" checkbox");
        callForwardingPage.getCheckboxDeviceUnavailable().click();

        step("Select value from \"ForwardTo\" dropdown");
        callForwardingPage.getDropdownIfbusyForwardTo().selectOption("Phone");

        step("Fill in phone field");
        callForwardingPage.getInputDevicePhone().setValue(forwardToPhone);

        step("Save changes");
        callForwardingPage.getButtonSave().click();
        waitUntilAlertDisappear();

        step("Refresh page");
        refreshPage();

        step("Verify if all entered data was saved");
        callForwardingPage.getDropdownMyNumbers().selectOptionContainingText(user.getPhoneNumber());
        callForwardingPage.getDropdownDeviceForwardTo().getSelectedOption().shouldHave(Condition.text("Phone"));
        callForwardingPage.getInputDevicePhone().shouldHave(Condition.value(forwardToPhone));

        step("Delete test data - delete user");
        deleteUser(user);
    }

    @Description("Verify if user can configure \"Calls with suppressed numbers\" section in call forwarding")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "callForwardingPage"})
    public void VerifyIfUserCanConfigureCallsWithSuppressedNumbersSectionInCallForwarding(){
        step("Prepare test data");
        String delay = getRandomNumber(2);
        User user = new User();
        String forwardToPhone = getRandomPhone();

        step("Log in the system");
        login();

        step("Create new user");
        createUser(user);

        step("Goto forwarding tab");
        basePage.getTabCallForwarding().click();

        step("Select number");
        callForwardingPage.getDropdownMyNumbers().selectOptionContainingText(user.getPhoneNumber());

        step("Activate \"Calls with suppressed numbers\" checkbox");
        callForwardingPage.getCheckboxSuppressedNumbers().click();

        step("Select value from \"ForwardTo\" dropdown");
        callForwardingPage.getDropdownSupprNumForwardTo().selectOptionByValue("VOICEMAIL");

        step("Save changes");
        callForwardingPage.getButtonSave().click();
        waitUntilAlertDisappear();

        step("Refresh page");
        refreshPage();

        step("Verify if all entered data was saved");
        callForwardingPage.getDropdownMyNumbers().selectOptionContainingText(user.getPhoneNumber());
        callForwardingPage.getDropdownSupprNumForwardTo().getSelectedOption().shouldHave(Condition.value("VOICEMAIL"));

        step("Delete test data - delete user");
        deleteUser(user);
    }
}
