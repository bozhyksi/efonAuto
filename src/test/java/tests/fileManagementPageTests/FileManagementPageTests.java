package tests.fileManagementPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;

import java.util.ArrayList;

import static api.baseApiMethods.FileManagementApi.*;
import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.*;

@Listeners(CustomListeners.class)

public class FileManagementPageTests extends BaseTestMethods {
    ArrayList<FileManagementTestData> fileArrayList = new ArrayList<>();

    @Description("Verify if user can upload and delete Music on Hold file")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "fileManagementPageTests"})
    public void uploadDeleteMohFileTest(){

        FileManagementTestData moh = new FileManagementTestData();
        fileArrayList.add(moh);

        login()
                .goToMenuTab(FILE_MANAGEMENT)
                .goToMenuTab(MUSIC_ON_HOLD);
        musicOnHoldPage
                .uploadFile(moh.getFilePath())
                .enterFileName(moh.getFileName())
                .save(musicOnHoldPage)
                .verifyFileUploaded(moh.getFileName())
                .deleteMohFile(moh.getFileName())
                .verifyFileDeleted(moh.getFileName());
    }

    @Description("Verify if user can edit name of music on hold file")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "fileManagementPageTests"})
    public void editNameMohFileTest(){

        FileManagementTestData moh = new FileManagementTestData();
        fileArrayList.add(moh);

        uploadMohApi(moh);
        login()
                .goToMenuTab(FILE_MANAGEMENT)
                .goToMenuTab(MUSIC_ON_HOLD);
        musicOnHoldPage
                .clickEdit(moh)
                .enterName(moh.rename())
                .saveMoh()
                .verifyFileUploaded(moh.getFileName());
        deleteMohApi(moh);
    }

    @Description("Verify if user can upload announcement file")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "fileManagementPageTests"})
    public void uploadAnnouncementFileTest(){

        FileManagementTestData announcement = new FileManagementTestData();
        fileArrayList.add(announcement);

        login()
                .goToMenuTab(FILE_MANAGEMENT)
                .goToMenuTab(ANNOUNCEMENT_DISPLAY);
        announcementDisplayPage
                .uploadAnnouncement(announcement)
                .verifyIfAnnouncementExists(announcement)
                .deleteAnnouncement(announcement)
                .verifyIfAnnouncementNotExist(announcement);
    }

    @Description("Verify if user can create test configuration")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "fileManagementPageTests"})
    public void createTestConfigurationTest(){
        FileManagementTestData announcement = new FileManagementTestData();
        fileArrayList.add(announcement);

        uploadAnnouncementApi(announcement);
        login()
                .goToMenuTab(FILE_MANAGEMENT)
                .goToMenuTab(ANNOUNCEMENT_DISPLAY);
        announcementDisplayPage
                .clickCreateTestConfig(announcement)
                .verifyTestConfigCreated();
        deleteAnnouncementApi(announcement);
    }

    @Description("Verify if user can edit name of announcement file")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "fileManagementPageTests"})
    public void editAnnouncementNameTest(){
        FileManagementTestData announcement = new FileManagementTestData();
        fileArrayList.add(announcement);

        uploadAnnouncementApi(announcement);
        login()
                .goToMenuTab(FILE_MANAGEMENT)
                .goToMenuTab(ANNOUNCEMENT_DISPLAY);
        announcementDisplayPage
                .clickEdit(announcement)
                .enterName(announcement.rename())
                .saveAnnouncement()
                .verifyIfAnnouncementExists(announcement);
        deleteAnnouncementApi(announcement);
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        announcementCleanUp(fileArrayList);
        mohCleanUp(fileArrayList);
    }
}
