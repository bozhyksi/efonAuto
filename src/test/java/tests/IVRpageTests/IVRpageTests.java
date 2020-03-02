package tests.IVRpageTests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.IVRpageTests.IVRtestData.IVRtestData;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;

import java.util.ArrayList;

import static io.qameta.allure.Allure.step;

@Listeners(CustomListeners.class)

public class IVRpageTests extends BaseTestMethods {
    private ArrayList<IVRtestData> ivrList = new ArrayList<>();
    private ArrayList<FileManagementTestData> filesList = new ArrayList<>();

    @Description("Verify if user can create new IVR")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "IVRpageTests"})
    public void VerifyIfUserCanCreateNewIvr(){
        step("Prepare test data - create IVR object");
        IVRtestData ivr = new IVRtestData();
        FileManagementTestData file = new FileManagementTestData();
        ivrList.add(ivr);
        filesList.add(file);

        step("Log in the system");
        login();

        step("Upload announcement");
        uploadAnnouncementFile(file);

        step("Goto IVR page");
        basePage.getTabIVRs().click();

        step("Click \"New IVR\" button");
        ivrPage.getButtonNewIvr().click();

        step("Fill in \"Name\" field");
        ivrPagePopup.getInputName().setValue(ivr.getIvrName());

        step("Fill in \"Display Nane\" field");
        ivrPagePopup.getInputDisplayName().setValue(ivr.getIvrDisplName());

        step("Select English language");
        ivrPagePopup.getDropdownLanguage().selectOptionByValue(ivr.getIvrLanguage());

        step("Select number");
        ivrPagePopup.getDropdownSelectIvrNumber().selectOption(1);

        step("Select announcement");
        ivrPagePopup.getDropdownSelectAnnounc().selectOption(1);

        step("Save all changes");
        ivrPagePopup.getButtonSave().click();
        waitUntilAlertDisappear();

        step("Check if IVR is displayed in the grid");
        ivrPage.getListName().filterBy(Condition.text(ivr.getIvrName())).shouldHave(CollectionCondition.sizeGreaterThan(0));

        step("Delete created IVR");
        ivrPage.getButtonDeleteIvrByName(ivr.getIvrName()).click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();

        step("Check if IVR was deleted in the grid");
        ivrPage.getListName().filterBy(Condition.text(ivr.getIvrName())).shouldHave(CollectionCondition.size(0));

        step("Delete announcement file");
        deleteAnnouncementFile(file.getFileName());
    }

    @Description("Verify if user can EDIT new IVR")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "IVRpageTests"}, enabled = false)// test ignored because of the b ug
    public void VerifyIfUserCanEditNewIvr(){
        step("Prepare test data - create IVR object");
        String displName = getRandomString(10);
        IVRtestData ivr = new IVRtestData();
        FileManagementTestData file = new FileManagementTestData();
        ivrList.add(ivr);
        filesList.add(file);

        step("Log in the system");
        login();

        step("Upload announcement");
        uploadAnnouncementFile(file);

        step("Create new IVR");
        createIVR(ivr);

        step("Click edit button");
        ivrPage.getButtonEditIvrByName(ivr.getIvrName()).click();
        waitUntilAlertDisappear();

        step("Display name");
        ivrPagePopup.getInputDisplayName().setValue(displName);

        step("Save changed data");
        ivrPagePopup.getButtonSave().click();
        waitUntilAlertDisappear();
        ivrPagePopup.getButtonClose().click();

        step("Check if saved data are displayed correctly in the grid");
        ivrPage.getListDisplayName().filterBy(Condition.text(displName)).shouldHave(CollectionCondition.sizeGreaterThan(0));

        step("Delete created test data");
        deleteIVR(ivr.getIvrName());

        step("Delete announcement file");
        deleteAnnouncementFile(file.getFileName());
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        startBrowser();
        login();
        ivrCleanUp(ivrList);
        announcementCleanUp(filesList);
        closeBrowser();
    }
}
