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
import pages.basePage.BasePage;
import pages.userPage.userPagePopup.configureUser.ConfigureUserBasePopup;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.userPageTests.userPageTestData.EndDevice;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static api.baseApiMethods.FileManagementApi.*;
import static api.baseApiMethods.NumbersApi.getCustomerNumbersApi;
import static api.baseApiMethods.UserApi.*;
import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.*;
import static pages.userPage.userPagePopup.configureUser.ConfigureUserBasePopup.Tabs.*;
import static pages.userPage.userPagePopup.configureUser.ConfigureUserBasePopup.Tabs.FAX;

@Listeners({CustomListeners.class})

public class UserPageTests extends BaseTestMethods {
    ArrayList<User> usersList = new ArrayList<>();
    ArrayList<FileManagementTestData> filesList = new ArrayList<>();

    @Description("Check if VPBX admin is able to create users")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "userPageTests"})
    public void createDeleteUserTest(){

        User user = new User();
        usersList.add(user);

        login()
                .goToMenuTab(USER);
        userPage
                .createUser(user)
                .verifyIfUserExists(user.getFirstName())
                .deleteUser(user);
    }

    @Description("Check if VPBX admin is able to DELETE users")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "userPageTests"})
    public void deleteUserTest(){
        User user = new User();
        usersList.add(user);

        createUsersApi(user);
        login()
                .goToMenuTab(USER);
        userPage
                .deleteUser(user)
                .verifyIfUserDoesNotExist(user.getFirstName());
    }

    @Description("Check if the system shows correct user's data on edit popup - \"NAME\" tab")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"})
    public void verifyUserInfoOnEditPopupNameTab(){
        User user = new User();
        usersList.add(user);

        login()
                .goToMenuTab(USER);
        userPage
                .createUser(user)
                .clickEditUser(user)
                .goToTab(NAME);
        nameTabConfigUserPopup
                .validateTitle(user.getTitle())
                .validateFirstName(user.getFirstName())
                .validateLastName(user.getLastName())
                .validateLoginEmail(user.getLoginEmail())
                .closeEditUserPopup();

        deleteUsersApi(user);
    }

    //905
    @Description("Check if the system shows correct user's data on edit popup - \"ALLOCATIONS\" tab")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"},enabled = false)
    public void validateUserDataOnEditPopupALLOCATIONSTab(){
        User user = new User();
        usersList.add(user);

        createUsersApi(user);

        login()
                .goToMenuTab(USER);
        userPage
                .clickEditUser(user)
                .goToTab(ALLOCATIONS);
        allocationTabConfigUserPopup
                .validateVoicemailEmail(user.getVoicemailEmail())
                .validateNumber(user.getPhoneNumber())
                .validatePermittedDestinationNumbers(user.getPermittedDestinationNumbers())
                .validateBusyOnBusy()
                .validateCallsRecordingDirection(user.getCallsRecordingDirection())
                .closeEditUserPopup();

        deleteUsersApi(user);
    }

    @Description("Check if the system allows to configure data on FORWARDING tab")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests", "callForwardingPage"})
    public void configureDataOnForwardingTabTest(){
        User user = new User();
        usersList.add(user);

        createUsersApi(user);
        login()
                .goToMenuTab(USER);
        userPage
                .clickEditUser(user)
                .goToTab(FORWARDING);
        forwardingTabConfigUserPopup
                .selectNumber(user.getPhoneNumber())
                .configAfterSection(user.getAfterDelay(),user.getForwardToPhone())
                .configManualStatus(user)
                .configIfBusySection(user.getForwardToPhone())
                .configEndDevSection(user.getForwardToPhone())
                .save()
                .closeEditUserPopup();
        userPage
                .clickEditUser(user)
                .goToTab(FORWARDING);
        forwardingTabConfigUserPopup
                .selectNumber(user.getPhoneNumber())
                .verifyAfterSection(user.getAfterDelay(),user.getForwardToPhone())
                .verifyIfBusy(user.getForwardToPhone())
                .verifyManualStatusSection(user);
        deleteUsersApi(user);
    }

    @Description("Check if the configured data on FORWARDING tab(edit user popup) appears on Call Forwarding")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests", "callForwardingPage"})
    public void configuredDataOnForwardingTabAndCheckOnCallForwardingTest(){
        User user = new User();
        usersList.add(user);

        createUsersApi(user);
        login()
                .goToMenuTab(USER);
        userPage
                .clickEditUser(user)
                .goToTab(FORWARDING);
        forwardingTabConfigUserPopup
                .selectNumber(user.getPhoneNumber())
                .configAfterSection(user.getAfterDelay(),user.getForwardToPhone())
                .configEndDevSection(user.getForwardToPhone())
                .configIfBusySection(user.getForwardToPhone())
                .configManualStatus(user)
                .save()
                .closeEditUserPopup()
                .goToMenuTab(CALL_FORWARDING);
        callForwardingPage
                .selectNumber(user.getPhoneNumber())
                .verifyAfterSection(user)
                .verifyEndDevSection(user)
                .verifyManualStatus(user)
                .verifyIfBusySection(user);
        deleteUsersApi(user);
    }

    @Description("Check if the configured data on VOICEMAIL tab(edit user popup)")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"})
    public void configureVoicemailTest(){
        User user = new User();
        usersList.add(user);

        createUsersApi(user);
        login()
                .goToMenuTab(USER);
        userPage
                .clickEditUser(user)
                .goToTab(VOICEMAIL);
        voicemailTabConfigUserPopup
                .selectNumber(user.getPhoneNumber())
                .clickEdit()
                .enterPIN(user.getVoicemailPinCode())
                .enterEmail(user.getVoicemailEmail())
                .enterSalutation(user.getVoicemailSalutation())
                .save()
                .closeEditUserPopup()
                .clickEditUser(user)
                .goToTab(VOICEMAIL);
        voicemailTabConfigUserPopup
                .verifyVoicemailConfigs(user);
        deleteUsersApi(user);
    }

    @Description("Check if user can upload voicemail announcement files on ANNOUNCEMENTS tab(edit user popup)")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"})
    public void uploadVoicemailAnnouncementTest() {
        User user = new User();
        FileManagementTestData announcement = new FileManagementTestData();
        usersList.add(user);

        createUsersApi(user);
        login()
                .goToMenuTab(USER);
        userPage
                .clickEditUser(user)
                .goToTab(ANNOUNCEMENTS);
        announcementsTabConfigUserPopup
                .uploadAnnouncementFile(announcement)
                .closeEditUserPopup()
                .clickEditUser(user)
                .goToTab(ANNOUNCEMENTS);
        announcementsTabConfigUserPopup
                .verifyAnnouncementExist(announcement);
        deleteUsersApi(user);
    }

    @Description("Check if user can configure Fax tab(edit user popup)")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"})
    public void configureFaxTest(){
        User user = new User();
        usersList.add(user);

        createUsersApi(user);
        login()
                .goToMenuTab(USER);
        userPage
                .clickEditUser(user)
                .goToTab(FAX);
        faxTabConfigUserPopup
                .selectNumber(user.getPhoneNumber())
                .clickEdit()
                .enterEmail(user.getFaxEmail())
                .selectFaxReceiveFormat(user.getFaxReceiveFormat())
                .save()
                .closeEditUserPopup()
                .clickEditUser(user)
                .goToTab(FAX);
        faxTabConfigUserPopup
                .verifyFaxSettings(user);
        deleteUsersApi(user);
    }

    @Description("Check if fax configuration made on edit user popup is shown on Fax tab in main menu")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"})
    public void checkFaxSettingsOnFaxTab(){
        User user = new User();
        usersList.add(user);

        createUsersApi(user);
        login()
                .goToMenuTab(USER);
        userPage
                .clickEditUser(user)
                .goToTab(FAX);
        faxTabConfigUserPopup
                .selectNumber(user.getPhoneNumber())
                .clickEdit()
                .selectFaxReceiveFormat(user.getFaxReceiveFormat())
                .enterEmail(user.getFaxEmail())
                .save()
                .closeEditUserPopup()
                .goToMenuTab(BasePage.MenuTabsBasePage.FAX);
        faxPage
                .selectNumber(user.getPhoneNumber())
                .clickEditFax2Email()
                .validateFaxSettings(user);
        deleteUsersApi(user);
    }

    @Description("Check if user can make configurations on END-DEVICE tab(edit user popup)")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"})
    public void configureEndDeviceTest(){
        User user = new User();
        EndDevice endDevice = new EndDevice();
        usersList.add(user);

        createUsersApi(user);
        login()
                .goToMenuTab(USER);
        userPage
                .clickEditUser(user)
                .goToTab(ENDDEVICE);
        endDeviceTabConfigUserPopup
                .selectEndDevice(user.getEndDevices())
                .enterName(endDevice.getEndDevName())
                .enterUserID(endDevice.getEndDevUserId())
                .enterPassword(endDevice.getEndDevPass())
                .selectCodec(endDevice.getEndDevCodec())
                .selectLanguage(endDevice.getEndDevPhoneLanguage())
                .enterDisplayName(endDevice.getEndDevDisplayName())
                .selectOutGoingNum(endDevice.getEndDevOutgoingNumber())
                .enterLocation(endDevice.getEndDevLocation())
                .save()
                .refreshPage();
        userPage
                .clickEditUser(user)
                .goToTab(ENDDEVICE);
        endDeviceTabConfigUserPopup
                .verifyEndDeviceSettings(endDevice);
        deleteUsersApi(user);
    }

    @Description("Check if user can select his own number as outgoing on END-DEVICE tab(edit user popup)")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"})
    public void selectOwnNumberAsOutgoingTest(){
        User user = new User();
        usersList.add(user);

        createUsersApi(user);
        login()
                .goToMenuTab(USER);
        userPage
                .clickEditUser(user)
                .goToTab(ENDDEVICE);
        endDeviceTabConfigUserPopup
                .selectEndDevice(user.getEndDevices())
                .selectOutGoingNum(user.getPhoneNumber())
                .enterLocation("8909")
                .save()
                .refreshPage();
        userPage
                .clickEditUser(user)
                .goToTab(ENDDEVICE);
        endDeviceTabConfigUserPopup
                .getDropdownOutgoingNumEndDev().getSelectedValue().contains(user.getPhoneNumber());
        deleteUsersApi(user);
    }

    @Description("Check if all customers numbers are available as outgoing on END-DEVICE tab(edit user popup)")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"})
    public void outgoingNumbersListTest(){
        User user = new User();
        usersList.add(user);

        ArrayList<String> customerNumbersList = getCustomerNumbersApi();
        ArrayList<String> outgoingNumbersList;

        createUsersApi(user);
        login()
                .goToMenuTab(USER);
        userPage
                .clickEditUser(user)
                .goToTab(ENDDEVICE);
        outgoingNumbersList = endDeviceTabConfigUserPopup.getOutgoingDropdownItems();
        endDeviceTabConfigUserPopup
                .verifyIfAllCustomerNumbersAreAvailableAsOutgoing(customerNumbersList,outgoingNumbersList);
        deleteUsersApi(user);
    }

    @Description("Check if vpbx admin can edit/delete uploaded files on ANNOUNCEMENTS tab(edit user popup) ")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"})
    public void editAnnouncementTest(){
        User user = new User();
        FileManagementTestData announcement = new FileManagementTestData();
        usersList.add(user);

        createUsersApi(user);
        uploadAnnouncementForUserApi(user,announcement);
        login()
                .goToMenuTab(USER);
        userPage
                .clickEditUser(user)
                .goToTab(ANNOUNCEMENTS);
        announcementsTabConfigUserPopup
                .changeAnnouncementName(announcement)
                .closeEditUserPopup()
                .clickEditUser(user)
                .goToTab(ANNOUNCEMENTS);
        announcementsTabConfigUserPopup
                .verifyAnnouncementName(announcement);
        deleteUsersApi(user);
    }

    @Description("Check if marked as Ringback announcement appears on File management")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"})
    public void markAsRingbackAnnouncementAppearsOnFileManagementTest(){
        User user = new User();
        FileManagementTestData announcFile = new FileManagementTestData();
        usersList.add(user);
        filesList.add(announcFile);

        createUsersApi(user);
        uploadAnnouncementForUserApi(user,announcFile);
        login()
                .goToMenuTab(USER);
        userPage
                .clickEditUser(user)
                .goToTab(ANNOUNCEMENTS);
        announcementsTabConfigUserPopup
                .activateRingbackOption(announcFile)
                .closeEditUserPopup()
                .goToMenuTab(FILE_MANAGEMENT)
                .goToMenuTab(ANNOUNCEMENT_DISPLAY);
        announcementDisplayPage
                .verifyIfAnnouncementExists(announcFile);
        deleteUsersApi(user);
        deleteAnnouncementApi(announcFile);
    }

    //EPRO-1126
    @Description("Check if user can Record voicemail announcement by phone")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "userPageTests"},enabled = false)
    public void recordVoicemailAnnouncementByPhoneTest(){
        String user = "AutoTestUser";
        FileManagementTestData announcement = new FileManagementTestData();
        filesList.add(announcement);
        SSHFileTransfer.uploadFile(announcement.getSourcePath(),announcement.getDestinationPath());

        login()
            .goToMenuTab(USER);
        userPage
                .editUser(user)
                .goToTab(ANNOUNCEMENTS);
        announcementsTabConfigUserPopup
                .getRecordedVoicemailAnnouncementByPhone(announcement)
                .verifyAnnouncementExist(announcement);
        deleteAnnouncementApi(announcement);
    }

    @AfterClass(alwaysRun = true)
    private void сleanUp(){
        userCleanUp(usersList);
        announcementCleanUp(filesList);
    }
}
