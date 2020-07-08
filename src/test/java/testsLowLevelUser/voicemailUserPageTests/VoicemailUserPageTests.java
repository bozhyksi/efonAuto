package testsLowLevelUser.voicemailUserPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;
import lowLevelUserPages.voicemailLowLevelUserpage.VoicemailBaseUserPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import testsLowLevelUser.voicemailUserPageTests.voicemailTestData.VoicemailTestData;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.exist;
import static io.qameta.allure.Allure.step;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.MenuTabsLowLevelUser.*;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.MenuTabsLowLevelUser.VOICEMAIL_SETTING;
import static testsLowLevelUser.testData.AutotestUserData.autotestUserPhone;


@Listeners(CustomListeners.class)

public class VoicemailUserPageTests extends BaseTestMethods {
    ArrayList<FileManagementTestData> announcementList = new ArrayList<>();

    @Description("Check if low-level user can configure Voicemail retrieval/delivery")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "voicemailUserPageTests"})
    public void CheckIfLowLevelUserCanConfigureVoicemailRetrievalDelivery(){
        step("Prepare test data");
        VoicemailTestData voicemailAccess = new VoicemailTestData();

        step("Login as low-level user");
        loginAsLowLevelUser();

        step("Goto Voicemail");
        basePageLowLevelUser
                .goToMenuTab(VOICEMAIL)
                .goToMenuTab(VOICEMAIL_SETTING);

        step("Configure Voicemail retrieval/delivery");
        voicemailSettingUserPage.getDropdownSelectNumber().selectOptionContainingText(autotestUserPhone);
        voicemailSettingUserPage.fillInVoicemailRetrievalDelivery(voicemailAccess);
        refreshPage();

        step("Validate if Voicemail retrieval/delivery configuration saved");
        voicemailSettingUserPage.getDropdownSelectNumber().selectOptionContainingText(autotestUserPhone);
        voicemailSettingUserPage.validateVoicemailRetrievalDeliveryData(voicemailAccess);
    }

    @Description("Check if low-level user can Upload Announcements")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "voicemailUserPageTests"})
    public void CheckIfUserCanUploadAnnouncements(){

        step("Prepare test data");
        FileManagementTestData announcement = new FileManagementTestData();
        announcementList.add(announcement);

        step("Login as low-level user");
        loginAsLowLevelUser();

        step("Goto Announcements");
        basePageLowLevelUser.goToMenuTab(VOICEMAIL).goToMenuTab(ANNOUNCEMENTS);
        //voicemailBaseUserPage.goToMenuTab(ANNOUNCEMENTS);

        step("Upload Announcements file");
        announcementsUserPage.uploadAnnouncementFile(announcement);
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        refreshPage();

        step("Check if file was uploaded");
        announcementsUserPage.validateIfAnnouncementExcists(announcement);

        step("Cleat test data - delete uploaded file");
        deleteAnnouncementLowLevelUser(announcement);
    }

    @Description("Check if low-level user can EDIT Announcements")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "voicemailUserPageTests"})
    public void CheckIfLowLevelUserCanEditAnnouncements(){
        step("Prepare test data");
        FileManagementTestData announcement = new FileManagementTestData();
        announcementList.add(announcement);

        step("Login as low-level user");
        loginAsLowLevelUser();

        step("Upload Announcements file");
        lowLevelUserUploadAnnouncement(announcement);

        step("Edit the name of the uploaded announcement");
        announcementsUserPage.editAnnouncementName(announcement);

        step("Validate changes");
        announcementsUserPage.validateIfAnnouncementExcists(announcement);

        step("Cleat test data - delete uploaded file");
        deleteAnnouncementLowLevelUser(announcement);
    }

    @Description("Check if low-level user can configure Voicemail announcement settings")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "voicemailUserPageTests"})
    public void CheckIfLowLevelUserCanConfigureVoicemailAnnouncementSettings(){
        step("Prepare test data");
        FileManagementTestData announcement = new FileManagementTestData();
        announcementList.add(announcement);

        loginAsLowLevelUser();

        step("Upload Announcements file");
        lowLevelUserUploadAnnouncement(announcement);

        step("Goto Voicemail Settings");
        basePageLowLevelUser
                .goToMenuTab(VOICEMAIL)
                .goToMenuTab(VOICEMAIL_SETTING);

        step("Configure Voicemail announcement settings");
        voicemailSettingUserPage.getDropdownSelectNumber().selectOptionContainingText(autotestUserPhone);
        voicemailSettingUserPage.configureVoicemailAnnouncementSettings(announcement);

        step("Validate made changes");
        refreshPage();
        voicemailSettingUserPage.getDropdownSelectNumber().selectOptionContainingText(autotestUserPhone);
        voicemailSettingUserPage.validateVoicemailAnnouncementSettings(announcement);


        step("Cleat test data - delete uploaded file");
        deleteAnnouncementLowLevelUser(announcement);
    }

    @Description("Check if Select Number drop-down contains only user number on VOICEMAIL tab")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "voicemailUserPageTests"})
    public void CheckIfSelectNumberDropDownContainsOnlyUserNumberOnVoiceMail(){

        loginAsLowLevelUser();
        basePageLowLevelUser
                .goToMenuTab(VOICEMAIL);
        voicemailUserPage
                .verifySelectNumberDropdownItems();

    }

    //BUG 1043
    @Description("Check if Select Number drop-down contains only user number on VOICEMAIL_SETTINGS tab - BUG 1043")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "voicemailUserPageTests"},enabled = false)
    public void CheckIfSelectNumberDropDownContainsOnlyUserNumberOnSettings(){

        loginAsLowLevelUser();
        basePageLowLevelUser
                .goToMenuTab(VOICEMAIL)
                .goToMenuTab(VOICEMAIL_SETTING);
        voicemailSettingUserPage
                .verifySelectNumberDropdownItems();

    }


    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        startBrowser();
        loginAsLowLevelUser();
        lowLevelUserAnnouncementCleanUp(announcementList);
        closeBrowser();

    }
}
