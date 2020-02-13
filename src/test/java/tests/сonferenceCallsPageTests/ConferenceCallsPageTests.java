package tests.сonferenceCallsPageTests;

import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.сonferenceCallsPageTests.ConferenceCallTestData.Conference;

import static io.qameta.allure.Allure.step;

@Listeners(CustomListeners.class)

public class ConferenceCallsPageTests extends BaseTestMethods {

    @Description("Verify if user can create new Conference Call")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "conferenceCallsPage"})
    public void VerifyIfUserCreateNewConferenceCall(){
        step("Preparing test data");
        Conference conference = new Conference();

        step("Log in the system");
        login();

        step("Go to Conference Call tab");
        basePage.getTabConferenceCalls().click();

        step("Click \"New conference call\"");
        conferenceCallsPage.getButtonNewConferenceCall().click();
        waitUntilAlertDisappear();

        step("Fill in Name field in \"Configure conference call\" popup");
        createNewConfCallPopup.getInputName().setValue(conference.getName());

        step("Select call number in \"Configure conference call\" popup\"");
        createNewConfCallPopup.getDropdownConferenceCallNum().selectOption(1);

        step("Fill in PIN field in \"Configure conference call\" popup\"");
        createNewConfCallPopup.getInputPin().setValue(conference.getPin());

        step("Select call Language in \"Configure conference call\" popup\"");
        createNewConfCallPopup.getDropdownLanguage().selectOptionByValue(conference.getLanguage());

        step("Save entered data");
        createNewConfCallPopup.getButtonSave().click();
        waitUntilAlertDisappear();

        step("Delete created Conference call");
        conferenceCallsPage.getButtonDeleteByName(conference.getName()).click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();

        step("Verify if Conference call was deleted");
        conferenceCallsPage.getListNames().filterBy(Condition.text(conference.getName())).shouldHaveSize(0);
    }

    @Description("Verify if user can configure Calls with suppressed numbers")
    @Test(/*retryAnalyzer = RetryAnalyzer.class,*/ groups = {"regression","conferenceCallsPage"},invocationCount = 3)
    public void VerifyIfUserCanConfigureCallsWithSuppressedNumbers(){
        step("Preparing test data");
        Conference conference = new Conference();
        String forwardTo = "VOICEMAIL";

        step("Log in the system");
        login();

        step("Create new Conference Call");
        createConferenceCall(conference);

        step("Select Conference calls number from drop down");
        conferenceCallsPage.getDropdownConferenceCallNumbers().selectOptionContainingText(conference.getConferenceNumber());

        step("Enable Calls with suppressed numbers check box");
        conferenceCallsPage.getCheckboxCallsSuppressedNum().click();

        step("Select value from ForwardTo drop down");
        conferenceCallsPage.getDropdownForwardTo().selectOptionByValue(forwardTo);

        step("Click save and Refresh page");
        conferenceCallsPage.getButtonSave().click();
        refreshPage();

        step("Verify if all data was saved");
        conferenceCallsPage.getDropdownConferenceCallNumbers().selectOptionContainingText(conference.getConferenceNumber());
        conferenceCallsPage.getDropdownForwardTo().getSelectedValue().equals(forwardTo);

        step("Delete Conference calls");
        deleteConferenceCall(conference.getName());
    }
}
