package tests.userPageTests;

import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import core.workers.sshFileTransfer.SSHFileTransfer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.userPageTests.userPageTestData.EndDevice;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.*;
import static pages.userPage.userPagePopup.configureUser.ConfigureUserBasePopup.Tabs.*;
import static pages.userPage.userPagePopup.configureUser.ConfigureUserBasePopup.Tabs.FAX;

@Listeners({CustomListeners.class})

public class UserPageTests extends BaseTestMethods {
    ArrayList<User> userArrayList = new ArrayList<>();
    ArrayList<FileManagementTestData> filesArrayList = new ArrayList<>();

    @Description("Check if VPBX admin is able to create users")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "userPageTests"})
    public void CheckIfVpbxAdminIsAbleToCreateUsers(){

        User user = new User();
        userArrayList.add(user);

        login();
        userPage
                .createUser(user)
                .verifyIfUserExists(user.getFirstName())
                .deleteUser(user);

    }

    @Description("Check if VPBX admin is able to DELETE users")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "userPageTests"})
    public void CheckIfVpbxAdminCanDeleteUsers(){

        User user = new User();
        userArrayList.add(user);

        login();
        userPage
                .createUser(user)
                .deleteUser(user);

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
        configureUserBasePopup.goToTab(NAME);

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
        configureUserBasePopup.goToTab(ALLOCATIONS);

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
        configureUserBasePopup.goToTab(FORWARDING);

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
        configureUserBasePopup.goToTab(FORWARDING);
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
        configureUserBasePopup.goToTab(FORWARDING);

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
        configureUserBasePopup.goToTab(VOICEMAIL);

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
        configureUserBasePopup.goToTab(VOICEMAIL);
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

    @Description("Check if user can upload voicemail announcement files on ANNOUNCEMENTS tab(edit user popup)")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"})
    public void CheckIfUserCanUploadVoicemailAnnouncementFilesOnAnnouncementsTab(){
        step("Preparing test data, creating new object - User");
        User user = new User();
        FileManagementTestData announcFile = new FileManagementTestData();

        //filesArrayList.add(announcFile);
        userArrayList.add(user);

        step("Login the test environment");
        login();

        step("Create new user");
        createUser(user);

        step("Open user's EDIT mode, and goto ANNOUNCEMENTS tab");
        userPage.getButtonConfigUserByName(user.getFirstName()).click();
        configureUserBasePopup.goToTab(ANNOUNCEMENTS);


        step("Upload new announcement file");
        announcementsTabConfigUserPopup.getButtonUploadFile().click();
        announcementsTabConfigUserPopup.uploadAnnouncementFile(announcFile.getFilePath());
        waitUntilAlertDisappear();
        announcementsTabConfigUserPopup.getInputName().setValue(announcFile.getFileName());
        announcementsTabConfigUserPopup.getButtonSave().click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        announcementsTabConfigUserPopup.getButtonClose().click();

        step("Check if file was uploaded");
        userPage.getButtonConfigUserByName(user.getFirstName()).click();
        configureUserBasePopup.goToTab(ANNOUNCEMENTS);
        announcementsTabConfigUserPopup.getFieldAnnouncementName(announcFile.getFileName()).should(Condition.exist);
        refreshPage();

        step("Delete test user");
        deleteUser(user);
    }

    @Description("Check if user can configure Fax tab(edit user popup)")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"})
    public void CheckIfUserCanConfigureFaxTab(){
        step("Preparing test data, creating new object - User");
        User user = new User();
        userArrayList.add(user);

        step("Login the test environment");
        login();

        step("Create new user");
        createUser(user);

        step("Open user's EDIT mode, and goto FAX tab");
        userPage.getButtonConfigUserByName(user.getFirstName()).click();
        configureUserBasePopup.goToTab(FAX);

        step("Select user number from \"Select number\" drop-down");
        faxTabConfigUserPopup.getDropdownMyNumbers().selectOptionContainingText(user.getPhoneNumber());

        step("Click edit button");
        faxTabConfigUserPopup.getButtonEditFax().click();

        step("Fill in Email input");
        faxTabConfigUserPopup.getInputEmail().setValue(user.getFaxEmail());

        step("Select TIFF and PDF and save changes");
        faxTabConfigUserPopup.getCheckboxTiffAndPdf().click();
        faxTabConfigUserPopup.getButtonSave().click();
        refreshPage();
        waitUntilAlertDisappear();

        step("Check if configuration was saved");
        userPage.getButtonConfigUserByName(user.getFirstName()).click();
        configureUserBasePopup.goToTab(FAX);
        faxTabConfigUserPopup.getDropdownMyNumbers().selectOptionContainingText(user.getPhoneNumber());
        faxTabConfigUserPopup.getButtonEditFax().click();
        faxTabConfigUserPopup.getCheckboxTiffAndPdf().shouldBe(Condition.selected);
        faxTabConfigUserPopup.getInputEmail().shouldHave(Condition.value(user.getFaxEmail()));
        refreshPage();
        waitUntilAlertDisappear();

        step("Delete test user");
        deleteUser(user);
    }

    @Description("Check if fax configuration made on edit user popup is shown on Fax tab in main menu")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"})
    public void CheckIfFaxConfigurationMadeOnEditUserPopupIsShownOnFaxTabInMainMenu(){
        step("Preparing test data, creating new object - User");
        User user = new User();
        userArrayList.add(user);

        step("Login the test environment");
        login();

        step("Create new user");
        createUser(user);

        step("Open user's EDIT mode, and goto FAX tab");
        userPage.getButtonConfigUserByName(user.getFirstName()).click();
        configureUserBasePopup.goToTab(FAX);

        step("Select user number from \"Select number\" drop-down");
        faxTabConfigUserPopup.getDropdownMyNumbers().selectOptionContainingText(user.getPhoneNumber());

        step("Click edit button");
        faxTabConfigUserPopup.getButtonEditFax().click();

        step("Fill in Email input");
        faxTabConfigUserPopup.getInputEmail().setValue(user.getFaxEmail());

        step("Select TIFF and PDF and save changes");
        faxTabConfigUserPopup.getCheckboxTiffAndPdf().click();
        faxTabConfigUserPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();

        step("Goto main ment FAX tab and verify if configured data is shown for appropriate number");
        basePage.getTabFax().click();
        waitUntilAlertDisappear();
        faxPage.getDropdownSelectNumber().selectOptionContainingText(user.getPhoneNumber());
        faxPage.getEditButton().click();
        faxPage.getInputEmail().shouldHave(Condition.value(user.getFaxEmail()));
        faxPage.getCheckboxTiffAndPDF().shouldBe(Condition.selected);
        refreshPage();
        waitUntilAlertDisappear();

        step("Delete test user");
        deleteUser(user);
    }

    @Description("Check if user can make configurations on END-DEVICE tab(edit user popup)")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"})
    public void CheckIfCanMakeConfigurationsOnEnddeviceTab(){
        step("Preparing test data, creating new object - User");
        User user = new User();
        EndDevice endDevice = new EndDevice();
        userArrayList.add(user);

        step("Login the test environment");
        login();

        step("Create new user");
        createUser(user);

        step("Open user's EDIT mode, and goto END DEVICES tab");
        userPage.getButtonConfigUserByName(user.getFirstName()).click();
        configureUserBasePopup.goToTab(ENDDEVICE);

        step("Select end device");
        endDeviceTabConfigUserPopup.getDropdownSelectEndDevice().selectOptionContainingText(user.getEndDevices());

        step("Set name");
        endDeviceTabConfigUserPopup.getInputNameEndDev().setValue(endDevice.getEndDevName());

        step("Set User Id");
        endDeviceTabConfigUserPopup.getInputUserIdEndDev().setValue(endDevice.getEndDevUserId());

        step("Set password");
        endDeviceTabConfigUserPopup.getButtonEditPassEndDev().click();
        endDeviceTabConfigUserPopup.getInputPasswordEndDev().setValue(endDevice.getEndDevPass());
        endDeviceTabConfigUserPopup.getButtonSavePass().click();

        step("Select codec");
        endDeviceTabConfigUserPopup.getDropdownCodecsEndDev().selectOptionByValue(endDevice.getEndDevCodec());

        step("Set language");
        endDeviceTabConfigUserPopup.getDropdownLanguageEndDev().selectOptionByValue(endDevice.getEndDevPhoneLanguage());

        step("Set DisplayName");
        endDeviceTabConfigUserPopup.getinputDispNameEndDev().setValue(endDevice.getEndDevDisplayName());

        step("Select Outgoing Number");
        endDeviceTabConfigUserPopup.getDropdownOutgoingNumEndDev().selectOption(1);
        endDevice.setEndDevOutgoingNumber(endDeviceTabConfigUserPopup.getDropdownOutgoingNumEndDev().getSelectedValue());

        step("Set Location");
        endDeviceTabConfigUserPopup.getInputLocationEndDev().setValue(endDevice.getEndDevLocation());

        step("Save all made changes");
        endDeviceTabConfigUserPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();

        step("Edit created user and goto End Devices tab");
        userPage.getButtonConfigUserByName(user.getFirstName()).click();
        configureUserBasePopup.goToTab(ENDDEVICE);

        step("Check if all made changes were saved");
        endDeviceTabConfigUserPopup.getInputNameEndDev().shouldHave(Condition.value(endDevice.getEndDevName()));
        endDeviceTabConfigUserPopup.getInputUserIdEndDev().shouldHave(Condition.value(endDevice.getEndDevUserId()));
        endDeviceTabConfigUserPopup.getDropdownCodecsEndDev().getSelectedValue().contains(endDevice.getEndDevCodec());
        endDeviceTabConfigUserPopup.getDropdownLanguageEndDev().getSelectedValue().contains(endDevice.getEndDevPhoneLanguage());
        endDeviceTabConfigUserPopup.getinputDispNameEndDev().shouldHave(Condition.value(endDevice.getEndDevDisplayName()));
        endDeviceTabConfigUserPopup.getDropdownOutgoingNumEndDev().getSelectedValue().contains(endDevice.getEndDevOutgoingNumber());
        endDeviceTabConfigUserPopup.getInputLocationEndDev().shouldHave(Condition.value(endDevice.getEndDevLocation()));
        refreshPage();

        step("Delete test data");
        deleteUser(user);
    }

    @Description("Check if user can select his own number as outgoing on END-DEVICE tab(edit user popup)")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"})
    public void CheckIfCanSelectHisOwnNumberAsOutgoingOnEndDevice(){
        step("Preparing test data, creating new object - User");
        User user = new User();
        EndDevice endDevice = new EndDevice();
        userArrayList.add(user);

        step("Login the test environment");
        login();

        step("Create new user");
        createUser(user);

        step("Open user's EDIT mode, and goto END DEVICES tab");
        userPage.openEditUserPopup(user);
        configureUserBasePopup.goToTab(ENDDEVICE);

        step("Select end device");
        endDeviceTabConfigUserPopup.getDropdownSelectEndDevice().selectOptionContainingText(user.getEndDevices());

        step("Select user own number as outgoing on END-DEVICE tab(edit user popup)");
        endDeviceTabConfigUserPopup.getDropdownOutgoingNumEndDev().selectOptionContainingText(user.getPhoneNumber());

        step("Save and verify changes");
        endDeviceTabConfigUserPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();
        userPage.openEditUserPopup(user);
        configureUserBasePopup.goToTab(ENDDEVICE);
        endDeviceTabConfigUserPopup.getDropdownSelectEndDevice().selectOptionContainingText(user.getEndDevices());
        endDeviceTabConfigUserPopup.getDropdownOutgoingNumEndDev().getSelectedValue().contains(user.getPhoneNumber());
        refreshPage();

        step("Clear test data");
        deleteUser(user);
    }

    @Description("Check if all customers numbers are available as outgoing on END-DEVICE tab(edit user popup)")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"})
    public void CheckIfAllCustomersNumbersAreAvailableAsOutgoing(){
        step("Preparing test data, creating new object - User");
        User user = new User();
        userArrayList.add(user);

        ArrayList<String> customerNumbersList;
        ArrayList<String> outgoingNumbersList;

        step("Login the test environment");
        login();

        step("Create new user");
        createUser(user);

        step("Goto Numbers page and get list of all numbers");
        basePage.goToMenuTab(NUMBERS);
        basePage.getDropdownItemsPerPage().selectOptionContainingText("All");
        waitUntilAlertDisappear();
        customerNumbersList = numbersPage.getListOfNumbers();

        step("Open user's EDIT mode, and goto END DEVICES tab");
        basePage.goToMenuTab(USER);
        userPage.openEditUserPopup(user);
        configureUserBasePopup.goToTab(ENDDEVICE);

        step("Get list of outgoing numbers");
        outgoingNumbersList = endDeviceTabConfigUserPopup.getOutgoingDropdownItems();

        step("Verify if all customer numbers are available in outgoing dropdown");
        endDeviceTabConfigUserPopup.verifyIfAllCustomerNumbersAreAvailableAsOutgoing(customerNumbersList,outgoingNumbersList);
        refreshPage();

        step("Clear test data");
        deleteUser(user);
    }

    @Description("Check if vpbx admin can edit/delete uploaded files on ANNOUNCEMENTS tab(edit user popup) ")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"})
    public void CheckIfVpbxAdminCanEditUploadedAnnouncementsOnAnnouncementsTab(){
        step("Preparing test data, creating new object - User");
        User user = new User();
        FileManagementTestData announcFile = new FileManagementTestData();

        userArrayList.add(user);

        step("Login the test environment");
        login();

        step("Create new user");
        createUser(user);

        step("Open user's EDIT mode, and goto ANNOUNCEMENTS tab. Upload announcement.");
        uploadAnnouncementForUserOnEditPopup(user,announcFile);

        step("Edit uploaded ANNOUNCEMENTS");
        userPage.openEditUserPopup(user);
        configureUserBasePopup.goToTab(ANNOUNCEMENTS);
        announcementsTabConfigUserPopup.changeAnnouncementName(announcFile);
        refreshPage();

        step("Verify if changes saved");
        userPage.openEditUserPopup(user);
        configureUserBasePopup.goToTab(ANNOUNCEMENTS);
        announcementsTabConfigUserPopup.verifyAnnouncementName(announcFile);
        refreshPage();

        step("Clear test data");
        deleteUser(user);
    }

    @Description("Check if marked as Ringback announcement appears on File management")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"})
    public void CheckIfMarkedAsRingbackAnnouncementAppearsOnFileManagement(){
        step("Preparing test data, creating new object - User");
        User user = new User();
        FileManagementTestData announcFile = new FileManagementTestData();

        userArrayList.add(user);
        filesArrayList.add(announcFile);

        step("Login the test environment");
        login();

        step("Create new user");
        createUser(user);

        step("Open user's EDIT mode, and goto ANNOUNCEMENTS tab. Upload announcement.");
        uploadAnnouncementForUserOnEditPopup(user,announcFile);

        step("Edit uploaded ANNOUNCEMENTS and mark it as Ringback");
        userPage.openEditUserPopup(user);
        configureUserBasePopup.goToTab(ANNOUNCEMENTS);
        announcementsTabConfigUserPopup.activateRingbackOption(announcFile);
        configureUserBasePopup.getButtonClose().click();


        step("Check if announcement file appeared on File management");
        basePage.goToMenuTab(FILE_MANAGEMENT).goToMenuTab(ANNOUNCEMENT_DISPLAY);
        deleteAnnouncementFile(announcFile.getFileName());

        step("Clear test data");
        deleteUser(user);
    }

    @Description("Check if user can Record voicemail announcement by phone")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"})
    public void CheckIfUserCanRecordVoicemailAnnouncementByPhone(){
        step("Preparing test data, creating new object - User");
        String user = "AutoTestUser";
        FileManagementTestData announcement = new FileManagementTestData();
        filesArrayList.add(announcement);

        step("Upload test recording file to the server");
        SSHFileTransfer.uploadFile(announcement.getSourcePath(),announcement.getDestinationPath());

        step("Login the test environment");
        login();

        step("Edit created user and goto ANNOUNCEMENTS tab.");
        userPage.editUser(user).goToTab(ANNOUNCEMENTS);

        step("Save uploaded ANNOUNCEMENT by phone");
        announcementsTabConfigUserPopup.getRecordedVoicemailAnnouncementByPhone(announcement);

        step("Verify if Recorded announcement was saved");
        userPage.editUser(user).goToTab(ANNOUNCEMENTS);
        announcementsTabConfigUserPopup.deleteAnnouncement(announcement);
    }

    @AfterClass(alwaysRun = true)
    private void сleanUp(){
        startBrowser();
        login();
        userCleanUp(userArrayList);
        announcementCleanUp(filesArrayList);
        closeBrowser();
    }
}
