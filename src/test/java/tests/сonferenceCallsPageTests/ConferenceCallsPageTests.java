package tests.сonferenceCallsPageTests;

import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.сonferenceCallsPageTests.ConferenceCallTestData.ConferenceCallTestData;

import static io.qameta.allure.Allure.step;

@Listeners(CustomListeners.class)

public class ConferenceCallsPageTests extends BaseTestMethods {

    @Description("Verify if user can create new ConferenceCallTestData Call")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "conferenceCallsPage"})
    public void VerifyIfUserCreateNewConferenceCall(){
        step("Preparing test data");
        ConferenceCallTestData conferenceCallTestData = new ConferenceCallTestData();

        step("Log in the system");
        login();

        step("Go to ConferenceCallTestData Call tab");
        basePage.getTabConferenceCalls().click();

        step("Click \"New conferenceCallTestData call\"");
        conferenceCallsPage.getButtonNewConferenceCall().click();
        waitUntilAlertDisappear();

        step("Fill in Name field in \"Configure conferenceCallTestData call\" popup");
        createNewConfCallPopup.getInputName().setValue(conferenceCallTestData.getName());

        step("Select call number in \"Configure conferenceCallTestData call\" popup\"");
        createNewConfCallPopup.getDropdownConferenceCallNum().selectOption(1);

        step("Fill in PIN field in \"Configure conferenceCallTestData call\" popup\"");
        createNewConfCallPopup.getInputPin().setValue(conferenceCallTestData.getPin());

        step("Select call Language in \"Configure conferenceCallTestData call\" popup\"");
        createNewConfCallPopup.getDropdownLanguage().selectOptionByValue(conferenceCallTestData.getLanguage());

        step("Save entered data");
        createNewConfCallPopup.getButtonSave().click();
        waitUntilAlertDisappear();

        step("Delete created ConferenceCallTestData call");
        conferenceCallsPage.getButtonDeleteByName(conferenceCallTestData.getName()).click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();

        step("Verify if ConferenceCallTestData call was deleted");
        conferenceCallsPage.verifyConfCallNotExist(conferenceCallTestData.getName());
    }

    @Description("Verify if user can configure Calls with suppressed numbers")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","conferenceCallsPage"})
    public void VerifyIfUserCanConfigureCallsWithSuppressedNumbers(){
        step("Preparing test data");
        ConferenceCallTestData conferenceCallTestData = new ConferenceCallTestData();
        String forwardTo = "VOICEMAIL";

        step("Log in the system");
        login();

        step("Create new ConferenceCallTestData Call");
        createConferenceCall(conferenceCallTestData);

        step("Select ConferenceCallTestData calls number from drop down");
        conferenceCallsPage.getDropdownConferenceCallNumbers().selectOptionContainingText(conferenceCallTestData.getConferenceNumber());

        step("Enable Calls with suppressed numbers check box");
        conferenceCallsPage.getCheckboxCallsSuppressedNum().click();

        step("Select value from ForwardTo drop down");
        conferenceCallsPage.getDropdownForwardTo().selectOptionByValue(forwardTo);

        step("Click save and Refresh page");
        conferenceCallsPage.getButtonSave().click();
        refreshPage();

        step("Verify if all data was saved");
        conferenceCallsPage.getDropdownConferenceCallNumbers().selectOptionContainingText(conferenceCallTestData.getConferenceNumber());
        conferenceCallsPage.getDropdownForwardTo().getSelectedValue().equals(forwardTo);

        step("Delete ConferenceCallTestData calls");
        conferenceCallsPage.deleteConfCall(conferenceCallTestData);
    }

    //BUG 924
    @Description("Verify if changed conference call name displayed in the grid")
    @Test(/*retryAnalyzer = RetryAnalyzer.class, */groups = {"regression","conferenceCallsPage"},enabled = false)
    public void verifyIfChangedNameDisplayed(){
        ConferenceCallTestData confCall = new ConferenceCallTestData();

        login();
        conferenceCallsPage
                .createConfCall(confCall)
                .clickEdit(confCall)
                .enterName(confCall.changeName())
                .saveChanges()
                .verifyConfCallExists(confCall.getName())
                .deleteConfCall(confCall);
    }

    //BUG 924
    @Description("Verify if changed conference call Number displayed in the grid")
    @Test(/*retryAnalyzer = RetryAnalyzer.class, */groups = {"regression","conferenceCallsPage"},enabled = false)
    public void verifyIfChangedNumberDisplayed(){
        ConferenceCallTestData confCall = new ConferenceCallTestData();

        login();
        conferenceCallsPage
                .createConfCall(confCall)
                .clickEdit(confCall)
                .selectNumber(confCall.changeNumber())
                .saveChanges()
                .verifyConfCallExists(confCall.getConferenceNumber())
                .deleteConfCall(confCall);
    }

    //BUG 924
    @Description("Verify if changed conference call PIN displayed in the grid")
    @Test(/*retryAnalyzer = RetryAnalyzer.class, */groups = {"regression","conferenceCallsPage"},enabled = false)
    public void verifyIfChangedPinDisplayed(){
        ConferenceCallTestData confCall = new ConferenceCallTestData();

        login();
        conferenceCallsPage
                .createConfCall(confCall)
                .clickEdit(confCall)
                .selectNumber(confCall.changePIN())
                .saveChanges()
                .verifyConfCallExists(confCall.getPin())
                .deleteConfCall(confCall);
    }

    //BUG 924
    @Description("Verify if changed conference call Language displayed in the grid")
    @Test(/*retryAnalyzer = RetryAnalyzer.class, */groups = {"regression","conferenceCallsPage"},enabled = false)
    public void verifyIfChangedLanguageDisplayed(){
        ConferenceCallTestData confCall = new ConferenceCallTestData();

        login();
        conferenceCallsPage
                .createConfCall(confCall)
                .clickEdit(confCall)
                .selectNumber(confCall.changeLanguage())
                .saveChanges()
                .verifyConfCallExists(confCall.getPin())
                .deleteConfCall(confCall);
    }
}
