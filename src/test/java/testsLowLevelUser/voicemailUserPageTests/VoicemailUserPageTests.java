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

import static api.baseLowLevelUserApi.FileManagementApi.deleteAnnouncementLowLevelUserApi;
import static api.baseLowLevelUserApi.FileManagementApi.uploadAnnouncementLowLevelUserApi;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.value;
import static io.qameta.allure.Allure.step;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.MenuTabsLowLevelUser.*;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.MenuTabsLowLevelUser.VOICEMAIL_SETTING;
import static testsLowLevelUser.testData.AutotestUserData.autotestUserPhone;


@Listeners(CustomListeners.class)

public class VoicemailUserPageTests extends BaseTestMethods {
    ArrayList<FileManagementTestData> announcementList = new ArrayList<>();

    @Description("Check if low-level user can configure Voicemail retrieval/delivery")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "voicemailUserPageTests"})
    public void configureVoicemailRetrievalDeliveryTest(){
        VoicemailTestData voicemailData = new VoicemailTestData();

        loginAsLowLevelUser()
                .goToMenuTab(VOICEMAIL)
                .goToMenuTab(VOICEMAIL_SETTING);
        voicemailSettingUserPage
                .selectNumber(autotestUserPhone)
                .clickEditVoicemailRetrievalDelivery()
                    .enterPin(voicemailData.getVoicemailPinCode())
                    .enterEmail(voicemailData.getVoicemailEmail())
                    .enterSalutation(voicemailData.getVoicemailSalutation())
                    .save()
                .clickEditVoicemailRetrievalDelivery()
                .verifyVoicemailRetrievalDeliverySettings(voicemailData);
    }

    @Description("Check if low-level user can Upload Announcements")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "voicemailUserPageTests"})
    public void uploadAnnouncementsTest(){
        FileManagementTestData announcement = new FileManagementTestData();
        announcementList.add(announcement);

        loginAsLowLevelUser()
                .goToMenuTab(VOICEMAIL)
                .goToMenuTab(ANNOUNCEMENTS);
        announcementsUserPage
                .uploadAnnouncementFile(announcement)
                .validateIfAnnouncementExcists(announcement);
        deleteAnnouncementLowLevelUserApi(announcement);
    }

    @Description("Check if low-level user can EDIT Announcements")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "voicemailUserPageTests"})
    public void editAnnouncementsTest(){
        FileManagementTestData announcement = new FileManagementTestData();
        announcementList.add(announcement);

        uploadAnnouncementLowLevelUserApi(announcement);
        loginAsLowLevelUser()
                .goToMenuTab(VOICEMAIL)
                .goToMenuTab(ANNOUNCEMENTS);
        announcementsUserPage
                .clickEdit(announcement)
                    .enterName(announcement.rename())
                    .save()
                .validateIfAnnouncementExcists(announcement)
                .clickEdit(announcement)
                    .getInputName().shouldHave(value(announcement.getFileName()));
        deleteAnnouncementLowLevelUserApi(announcement);
    }

    @Description("Check if low-level user can configure Voicemail announcement settings")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "voicemailUserPageTests"})
    public void configureVoicemailAnnouncementSettingsTest(){
        FileManagementTestData announcement = new FileManagementTestData();
        announcementList.add(announcement);

        uploadAnnouncementLowLevelUserApi(announcement);
        loginAsLowLevelUser()
                .goToMenuTab(VOICEMAIL)
                .goToMenuTab(VOICEMAIL_SETTING);
        voicemailSettingUserPage
                .clickEditVoicemailAnnouncementSettings()
                    .selectTemporaryAnnouncement(announcement.getFileName())
                    .selectVoicemailBusy(announcement.getFileName())
                    .selectVoicemailUnavailable(announcement.getFileName())
                    .save()
                .clickEditVoicemailAnnouncementSettings()
                    .verifyVoicemailAnnouncementSettings(announcement)
                    .selectTemporaryAnnouncement("Not set")
                    .selectVoicemailBusy("Not set")
                    .selectVoicemailUnavailable("Not set")
                    .save();
        deleteAnnouncementLowLevelUserApi(announcement);
    }

    @Description("Check if Select Number drop-down contains only user number on VOICEMAIL tab")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "voicemailUserPageTests"})
    public void verifySelectNumberDropDownItemsOnVoicemailTest(){
        loginAsLowLevelUser()
                .goToMenuTab(VOICEMAIL);
        voicemailUserPage
                .verifySelectNumberDropdownItems();
    }

    //BUG 1043
    @Description("Check if Select Number drop-down contains only user number on VOICEMAIL_SETTINGS tab - BUG 1043")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "voicemailUserPageTests"},enabled = false)
    public void verifySelectNumberDropDownItemsOnSettingsTest(){
        loginAsLowLevelUser()
                .goToMenuTab(VOICEMAIL)
                .goToMenuTab(VOICEMAIL_SETTING);
        voicemailSettingUserPage
                .verifySelectNumberDropdownItems();
    }


    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        lowLevelUserAnnouncementCleanUp(announcementList);
    }
}
