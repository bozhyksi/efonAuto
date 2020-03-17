package tests.userPageTests;

import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static io.qameta.allure.Allure.step;

@Listeners({CustomListeners.class})

public class UserPageTests extends BaseTestMethods {
    ArrayList<User> userArrayList = new ArrayList<>();

    @Description("Check if VPBX admin is able to create users")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "userPageTests"})
    public void CheckIfVpbxAdminIsAbleToCreateUsers(){
        step("Preparing test data object - User");
        User user = new User();
        userArrayList.add(user);

        step("Log in the system as VPBX admin and goto Users tab");
        login();
        basePage.getTabUser().click();

        step("Verify if correct page was loaded");
        userPage.getPageTitle().getText().equals("User");

        step("Click \"Create New User\"");
        userPage.getButtonCreateNewUser().click();

        step("Verify if correct popup opened");
        createUserPopup.getPopupTitle().getText().equals("Create user");

        step("Fill in all mandatory fields - Title, First/Last name, Login, Number, End-device");
        createUserPopup.selectTitle(user.getTitle());
        createUserPopup.fillFirstName(user.getFirstName());
        createUserPopup.fillLastName(user.getLastName());
        createUserPopup.fillLoginEmail(user.getLoginEmail());
        user.setPhoneNumber(createUserPopup.selectNumber());
        createUserPopup.selectEndDevices();
        createUserPopup.fillInDiffContactEmail(user.getUseDiffContactEmail());
        createUserPopup.fillInVoiceEmail(user.getVoiceEmail());
        createUserPopup.getCheckboxBusyOnBusy().click();
        user.setPermittedDestinationNumbers(createUserPopup.selectPermittedDestinationNumbers());
        createUserPopup.getCheckboxSmsEnabled().click();
        createUserPopup.getCheckboxRoleFinance().click();
        user.setCallsRecordingDirection(createUserPopup.activateCallRecordings());
        //createUserPopup.activateFaxDispatch(user.getInputLocalHeaderInfo());

        step("Save all made changes");
        createUserPopup.getButtonSave().click();

        step("Verify if user was created with correct data");
        userPage.checkIfUserExistsInTheList(user);

        step("Clear test data, delete created user");
        deleteUser(user);
    }

    @Description("Check if VPBX admin is able to DELETE users")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "userPageTests"})
    public void CheckifVpbxAdminisAbletoDeleteUsers(){
        step("Preparing test data object - User");
        User user = new User();
        userArrayList.add(user);

        step("Log in the system");
        login();

        step("Create new user");
        createUser(user);

        step("Delete created user");
        userPage.deleteUserButtonClick(user.getFullName());

        step("Confirm deleting");
        confirmationPopup.getYesButton().click();

        step("Check if user was deleted");
        userPage.checkIfUserDeleted(user);
    }

    @Description("Check if the system shows correct user's data on edit popup - \"NAME\" tab")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"})
    public void CheckIfTheSystemShowsCorrectUserDataOnEditPopupNameTab(){
        step("Preparing test data, creating new object - User");
        User user = new User();
        userArrayList.add(user);

        step("Login the test environment");
        login();

        step("Create new user");
        createUser(user);

        step("Open user's EDIT mode, and goto Name tab");
        userPage.getButtonConfigUserByName(user.getFirstName()).click();
        configureUserBasePopup.getTabName().click();

        step("Validate popup Title");
        configureUserBasePopup.validatePopupTitle("Configure user");

        step("Validate if Name tab shows correct data of earlier created user");
        nameTabConfigUserPopup.validateTitle(user.getTitle());
        nameTabConfigUserPopup.validateFirstName(user.getFirstName());
        nameTabConfigUserPopup.validateLastName(user.getLastName());
        nameTabConfigUserPopup.validateLoginEmail(user.getLoginEmail());
        nameTabConfigUserPopup.validateDiffContactEmail(user.getUseDiffContactEmail());

        step("Close edit popup, and refresh page");
        nameTabConfigUserPopup.getButtonClose().click();
        refreshPage();
        waitUntilAlertDisappear();

        step("Delete test user");
        deleteUser(user);
    }

    @Description("Check if the system shows correct user's data on edit popup - \"ALLOCATIONS\" tab")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"})
    public void CheckIfTheSystemShowsCorrectUserDataOnEditPopupALLOCATIONSTab(){
        step("Preparing test data, creating new object - User");
        User user = new User();
        userArrayList.add(user);

        step("Login the test environment");
        login();

        step("Create new user");
        createUser(user);

        step("Open user's EDIT mode, and goto Name tab");
        userPage.getButtonConfigUserByName(user.getFirstName()).click();
        configureUserBasePopup.getTabAllocations().click();

        step("Validate if ALLOCATIONS tab shows correct data of earlier created user");
        //allocationTabConfigUserPopup.validateVoicemailEmail(user.getVoiceEmail()); -- looks like a bug in DOM
        allocationTabConfigUserPopup.validateNumber(user.getPhoneNumber());
        allocationTabConfigUserPopup.validateEndDevice(user.getEndDevices());
        //allocationTabConfigUserPopup.validateBusyOnBusy();  -- bug created
        allocationTabConfigUserPopup.validatePermittedDestinationNumbers(user.getPermittedDestinationNumbers());
        allocationTabConfigUserPopup.validateActivateSMSservices();
        allocationTabConfigUserPopup.getCheckboxCallsRecording();
        allocationTabConfigUserPopup.validateCallsRecordingDirection(user.getCallsRecordingDirection());

        step("Close edit popup, and refresh page");
        allocationTabConfigUserPopup.getButtonClose().click();
        refreshPage();
        waitUntilAlertDisappear();

        step("Delete test user");
        deleteUser(user);
    }

    @Description("Check if the system allows to configure data on FORWARDING tab")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests", "callForwardingPage"})
    public void CheckIfTheSystemAllowsToConfigureDataOnForwardingTab(){
        step("Preparing test data, creating new object - User");
        User user = new User();
        userArrayList.add(user);

        step("Login the test environment");
        login();

        step("Create new user");
        createUser(user);

        step("Open user's EDIT mode, and goto FORWARDING tab");
        userPage.getButtonConfigUserByName(user.getFirstName()).click();
        configureUserBasePopup.getTabForwarding().click();

        step("Select number in \"My numbers\" drop-down");
        forwardingTabConfigUserPopup.getDropdownMyNumbers().selectOptionContainingText(user.getPhoneNumber());

        step("Configure \"After\" section");
        forwardingTabConfigUserPopup.getCheckboxAfter().click();
        forwardingTabConfigUserPopup.getInputForwardDelay().setValue(user.getAfterDelay());
        forwardingTabConfigUserPopup.getDropdownForwardTo().selectOptionContainingText("Phone");
        forwardingTabConfigUserPopup.getInputForwardToPhone().setValue(user.getForwardToPhone());

        step("Configure \"If Busy\" section");
        forwardingTabConfigUserPopup.getCheckboxIfBusy().click();
        forwardingTabConfigUserPopup.getDropdownIfBusy().selectOptionContainingText("Phone");
        forwardingTabConfigUserPopup.getInputIfBusyPhone().setValue(user.getForwardToPhone());

        step("Configure \"Manual status\" section");
        forwardingTabConfigUserPopup.getCheckboxManualStatus().click();
        forwardingTabConfigUserPopup.getInputManualSubject().setValue(user.getManualStatusSubj());
        forwardingTabConfigUserPopup.getDropdownManualStatusForwardTo().selectOptionContainingText("Voicemail temporary unavailable");
        forwardingTabConfigUserPopup.getInputDateFrom().setValue(user.getManualStatusDataFrom());
        forwardingTabConfigUserPopup.getInputDateTo().setValue(user.getManualStatusDataTo());

        step("Save all made changes");
        forwardingTabConfigUserPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();

        step("Open the same user for edit and check if data was saved on \"After\" section");
        userPage.getButtonConfigUserByName(user.getFirstName()).click();
        configureUserBasePopup.getTabForwarding().click();
        forwardingTabConfigUserPopup.getDropdownMyNumbers().selectOptionContainingText(user.getPhoneNumber());
        forwardingTabConfigUserPopup.getCheckboxAfter().shouldBe(Condition.selected);
        forwardingTabConfigUserPopup.getInputForwardDelay().shouldHave(Condition.value(user.getAfterDelay()));
        forwardingTabConfigUserPopup.getInputForwardToPhone().shouldHave(Condition.value(user.getForwardToPhone()));

        step("Check if data was saved on \"If busy\" section");
        forwardingTabConfigUserPopup.getCheckboxIfBusy().shouldBe(Condition.selected);
        forwardingTabConfigUserPopup.getInputIfBusyPhone().shouldHave(Condition.value(user.getForwardToPhone()));

        step("Check if data was saved on \"Manual status\" section");
        forwardingTabConfigUserPopup.getCheckboxManualStatus().shouldBe(Condition.selected);
        forwardingTabConfigUserPopup.getInputManualSubject().shouldHave(Condition.value(user.getManualStatusSubj()));
        forwardingTabConfigUserPopup.getInputDateFrom().shouldHave(Condition.value(user.getManualStatusDataFrom()));
        forwardingTabConfigUserPopup.getInputDateTo().shouldHave(Condition.value(user.getManualStatusDataTo()));


        step("Close popup and refresh the page");
        refreshPage();
        waitUntilAlertDisappear();

        step("Delete created test user");
        deleteUser(user);
    }

    @Description("Check if the configured data on FORWARDING tab(edit user popup) appears on Call Forwarding")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests", "callForwardingPage"})
    public void CheckIfTheConfiguredDataOnForwardingTabAppearsOnCallForwarding(){
        step("Preparing test data, creating new object - User");
        User user = new User();
        userArrayList.add(user);

        step("Login the test environment");
        login();

        step("Create new user");
        createUser(user);

        step("Open user's EDIT mode, and goto FORWARDING tab");
        userPage.getButtonConfigUserByName(user.getFirstName()).click();
        configureUserBasePopup.getTabForwarding().click();

        step("Select number in \"My numbers\" drop-down");
        forwardingTabConfigUserPopup.getDropdownMyNumbers().selectOptionContainingText(user.getPhoneNumber());

        step("Configure \"After\" section");
        forwardingTabConfigUserPopup.getCheckboxAfter().click();
        forwardingTabConfigUserPopup.getInputForwardDelay().setValue(user.getAfterDelay());
        forwardingTabConfigUserPopup.getDropdownForwardTo().selectOptionContainingText("Phone");
        forwardingTabConfigUserPopup.getInputForwardToPhone().setValue(user.getForwardToPhone());

        step("Configure \"If Busy\" section");
        forwardingTabConfigUserPopup.getCheckboxIfBusy().click();
        forwardingTabConfigUserPopup.getDropdownIfBusy().selectOptionContainingText("Phone");
        forwardingTabConfigUserPopup.getInputIfBusyPhone().setValue(user.getForwardToPhone());

        step("Configure \"Manual status\" section");
        forwardingTabConfigUserPopup.getCheckboxManualStatus().click();
        forwardingTabConfigUserPopup.getInputManualSubject().setValue(user.getManualStatusSubj());
        forwardingTabConfigUserPopup.getDropdownManualStatusForwardTo().selectOptionContainingText("Voicemail temporary unavailable");
        forwardingTabConfigUserPopup.getInputDateFrom().setValue(user.getManualStatusDataFrom());
        forwardingTabConfigUserPopup.getInputDateTo().setValue(user.getManualStatusDataTo());

        step("Save all made changes");
        forwardingTabConfigUserPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();

        step("Goto Call Forwarding tab");
        basePage.getTabCallForwarding().click();

        step("Select user's phone in \"My numbers\" drop down");
        callForwardingPage.getDropdownMyNumbers().selectOptionContainingText(user.getPhoneNumber());

        step("Verify if data from user popup is shown correctly on Call Forwarding tab");
        callForwardingPage.getInputAfterPhone().shouldHave(Condition.value(user.getForwardToPhone()));
        callForwardingPage.getInputIfbusyPhone().shouldHave(Condition.value(user.getForwardToPhone()));
        callForwardingPage.getInputAbsent().shouldHave(Condition.value(user.getManualStatusSubj()));
        callForwardingPage.getInputDateFrom().shouldHave(Condition.value(user.getManualStatusDataFrom()));
        callForwardingPage.getInputDateUntil().shouldHave(Condition.value(user.getManualStatusDataTo()));

        step("Delete created test user");
        deleteUser(user);
    }

    @Description("Check if the configured data on VOICEMAIL tab(edit user popup)")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"})
    public void CheckIfTheConfiguredDataOnVoicemailTab(){
        step("Preparing test data, creating new object - User");
        User user = new User();
        userArrayList.add(user);

        step("Login the test environment");
        login();

        step("Create new user");
        createUser(user);

        step("Open user's EDIT mode, and goto VOICEMAIL tab");
        userPage.getButtonConfigUserByName(user.getFirstName()).click();
        configureUserBasePopup.getTabVoiceMail().click();

        step("Select user number from \"Select number\" drop-down");
        voicemailTabConfigUserPopup.getDropdownSelectNumber().selectOptionContainingText(user.getPhoneNumber());

        step("Click edit button in \"Voicemail retrieval/delivery\" section");
        voicemailTabConfigUserPopup.getButtonVoicemailEdit().click();

        step("Fill in PIN code");
        voicemailTabConfigUserPopup.getInputPinCode().setValue(user.getVoicemailPinCode());

        step("Fill in E-mail for voice message delivery");
        voicemailTabConfigUserPopup.getInputEmailForVoice().setValue(user.getVoicemailEmail());

        step("Fill in Salutation");
        voicemailTabConfigUserPopup.getInputSalutation().setValue(user.getVoicemailSalutation());
        voicemailTabConfigUserPopup.getCheckboxYes().click();

        step("Save all changes");
        voicemailTabConfigUserPopup.getButtonSave().click();
        refreshPage();
        waitUntilAlertDisappear();

        step("Open created User for edit and verify if entered data was saved");
        userPage.getButtonConfigUserByName(user.getFirstName()).click();
        configureUserBasePopup.getTabVoiceMail().click();
        voicemailTabConfigUserPopup.getDropdownSelectNumber().selectOptionContainingText(user.getPhoneNumber());
        voicemailTabConfigUserPopup.getButtonVoicemailEdit().click();
        voicemailTabConfigUserPopup.getInputEmailForVoice().shouldHave(Condition.value(user.getVoicemailEmail()));
        voicemailTabConfigUserPopup.getInputSalutation().shouldHave(Condition.value(user.getVoicemailSalutation()));
        voicemailTabConfigUserPopup.getInputPinCode().shouldHave(Condition.value(user.getVoicemailPinCode()));
        refreshPage();
        waitUntilAlertDisappear();

        step("Delete created test user");
        deleteUser(user);
    }

    @AfterClass(alwaysRun = true,enabled = false)
    private void сleanUp(){
        startBrowser();
        login();
        userCleanUp(userArrayList);
        closeBrowser();
    }
}
