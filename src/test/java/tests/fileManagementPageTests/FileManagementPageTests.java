package tests.fileManagementPageTests;

import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;

import java.util.ArrayList;

import static io.qameta.allure.Allure.step;

@Listeners(CustomListeners.class)

public class FileManagementPageTests extends BaseTestMethods {
    ArrayList<FileManagementTestData> fileArrayList = new ArrayList<>();

    @Description("Verify if user can upload Music on Hold file")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "fileManagementPageTests"})
    public void VerifyIfUserCanUploadMusicOnHoldFile(){
        step("Prepare test data");
        FileManagementTestData testFile = new FileManagementTestData();
        fileArrayList.add(testFile);

        step("Log in the system");
        login();

        step("Goto File Management Page -> Music on Hold");
        basePage.getTabFileManagement().click();
        fileManagementBasePage.getTabMusicOnHold().click();

        step("Upload test .WAV file");
        musicOnHoldPage.uploadFile(testFile.getFilePath());

        step("Fill name for uploaded file");
        musicOnHoldPage.getInputName().setValue(testFile.getFileName());

        step("Save changes");
        musicOnHoldPage.getButtonSave().click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();

        step("Check if file was uploaded");
        musicOnHoldPage.getFieldNameByText(testFile.getFileName()).should(Condition.exist);

        step("Delete uploaded file");
        musicOnHoldPage.getButtonDeleteByName(testFile.getFileName()).click();
        confirmationPopup.getYesButton().click();
        refreshPage();
        waitUntilAlertDisappear();

        step("Verify if file was deleted and is not shown in the grid");
        musicOnHoldPage.getFieldNameByText(testFile.getFileName()).shouldNot(Condition.exist);
    }

    @Description("Verify if user can edit name of music on hold file")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "fileManagementPageTests"})
    public void VerifyIfUserCanEditNameOfMusicOnHoldFile(){
        step("Prepare test data");
        FileManagementTestData file = new FileManagementTestData();
        fileArrayList.add(file);

        step("Log in the system");
        login();

        step("Upload new MOH file");
        uploadMusicOnHoldFile(file);

        step("Click edit button");
        musicOnHoldPage.getButtonEditByName(file.getFileName()).click();

        step("In appeared popup enter new File name");
        file.setFileName(getRandomString(15));
        editFileManagPopup.getInputDisplayName().setValue(file.getFileName());

        step("Save changes");
        editFileManagPopup.getButtonSave().click();
        waitUntilAlertDisappear();

        step("Verify if changed were applied successfully");
        musicOnHoldPage.getFieldNameByText(file.getFileName()).should(Condition.exist);

        step("Delete test data");
        deleteMusicOnHoldFile(file.getFileName());
    }

    @Description("Verify if user can upload announcement file")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "fileManagementPageTests"})
    public void VerifyIfUserCanUploadAnnouncementFile(){
        step("Prepare test data");
        FileManagementTestData file = new FileManagementTestData();
        fileArrayList.add(file);

        step("Log in the system");
        login();

        step("Goto to File management -> Announcement display");
        basePage.getTabFileManagement().click();
        fileManagementBasePage.getTabAnnouncementDisplay().click();

        step("Upload file");
        announcementDisplayPage.getButtonUploadFile().click();
        announcementDisplayPage.uploadFile(file.getFilePath());

        step("Fill name for uploaded file");
        announcementDisplayPage.getInputName().setValue(file.getFileName());

        step("Save changes");
        announcementDisplayPage.getButtonSave().click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        refreshPage(); // temp fix because of the bug

        step("Check if file was uploaded");
        announcementDisplayPage.getFieldNameByText(file.getFileName()).should(Condition.exist);

        step("Delete uploaded file");
        announcementDisplayPage.getButtonDeleteByName(file.getFileName()).click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        refreshPage();

        step("Verify if file was deleted and is not shown in the grid");
        announcementDisplayPage.getFieldNameByText(file.getFileName()).shouldNot(Condition.exist);
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        startBrowser();
        login();
        try {
            announcementCleanUp(fileArrayList);
        } catch (Throwable e){
            mohCleanUp(fileArrayList);
        }finally{
            closeBrowser();
        }
    }
}
