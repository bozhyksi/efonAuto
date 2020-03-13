package tests.userPageTests;

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


    }



    @AfterClass(alwaysRun = true, enabled = false)
    private void CleanUp(){
        startBrowser();
        login();
        userCleanUp(userArrayList);
        closeBrowser();
    }
}
