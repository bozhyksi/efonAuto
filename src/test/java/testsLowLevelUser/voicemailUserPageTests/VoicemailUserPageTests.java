package testsLowLevelUser.voicemailUserPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import testsLowLevelUser.voicemailUserPageTests.voicemailTestData.VoicemailTestData;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.exist;
import static io.qameta.allure.Allure.step;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.MenuTabsLowLevelUser.VOICEMAIL;
import static lowLevelUserPages.voicemailLowLevelUserpage.VoicemailBaseUserPage.VoicemailTabs.ANNOUNCEMENTS;
import static lowLevelUserPages.voicemailLowLevelUserpage.VoicemailBaseUserPage.VoicemailTabs.VOICEMAIL_SETTING;

@Listeners(CustomListeners.class)

public class VoicemailUserPageTests extends BaseTestMethods {
    ArrayList<FileManagementTestData> announcementList = new ArrayList<>();

    @Description("Check if low-level user can configure Voicemail retrieval/delivery")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "voicemailUserPageTests"})
    public void CheckIfLowlevelUserCanConfigureVoicemailRetrievalDelivery(){
        step("Prepare test data");
        VoicemailTestData voicemailAccess = new VoicemailTestData();

        step("Login as low-level user");
        loginAsLowLevelUser();

        step("Goto Voicemail");
        basePageLowLevelUser.goToMenuTab(VOICEMAIL);
        voicemailBaseUserPage.goToMenuTab(VOICEMAIL_SETTING);

        step("Configure Voicemail retrieval/delivery");
        voicemailSettingUserPage.fillInVoicemailRetrievalDelivery(voicemailAccess);
        refreshPage();

        step("Validate if Voicemail retrieval/delivery configuration saved");
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
        basePageLowLevelUser.goToMenuTab(VOICEMAIL);
        voicemailBaseUserPage.goToMenuTab(ANNOUNCEMENTS);

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
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "voicemailUserPageTests"}, invocationCount = 3)
    public void CheckIfLowLevelUserCanConfigureVoicemailAnnouncementSettings(){
        step("Prepare test data");
        FileManagementTestData announcement = new FileManagementTestData();
        announcementList.add(announcement);

        step("Login as low-level user");
        loginAsLowLevelUser();

        step("Upload Announcements file");
        lowLevelUserUploadAnnouncement(announcement);

        step("Goto Voicemail Settings");
        voicemailBaseUserPage.goToMenuTab(VOICEMAIL_SETTING);

        step("Configure Voicemail announcement settings");
        voicemailSettingUserPage.configureVoicemailAnnouncementSettings(announcement);

        step("Validate made changes");
        refreshPage();
        voicemailSettingUserPage.validateVoicemailAnnouncementSettings(announcement);


        step("Cleat test data - delete uploaded file");
        deleteAnnouncementLowLevelUser(announcement);
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        startBrowser();
        loginAsLowLevelUser();
        lowLevelUserAnnouncementCleanUp(announcementList);
        closeBrowser();

    }


}
