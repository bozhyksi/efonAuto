package tests.numbersPageTests;

import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import tests.IVRpageTests.IVRtestData.IVRtestData;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.NUMBERS;

public class NumbersPageTests extends BaseTestMethods {
    ArrayList<User> userList = new ArrayList<>();
    ArrayList<IVRtestData> ivrList = new ArrayList<>();
    ArrayList<FileManagementTestData> announcementList = new ArrayList<>();
    ArrayList<HuntGroup> huntGroupList = new ArrayList<>();


    @Description("Verify if user data is shown on numbers page")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "numbersPageTests"})
    public void VerifyIfUserDataIsShownInNumbersPage(){
        step("Prepare test data");
        User user = new User();
        userList.add(user);

        step("Log in the system");
        login();

        step("Create user");
        createUser(user);

        step("Goto Numbers page and check if user details is shown for proper number");
        basePage.goToMenuTab(NUMBERS);
        numbersPage.verifyIfNumberInfoShowed(user);

        step("Delete test data");
        deleteUser(user);
    }

    //test disabled because of the bug
    @Description("Verify if IVR data is shown on numbers page")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "numbersPageTests"}, enabled = false)
    public void VerifyIfIvrDataIsShownInNumbersPage(){
        step("Prepare test data");
        IVRtestData ivr = new IVRtestData();
        FileManagementTestData announcement = new FileManagementTestData();
        ivrList.add(ivr);

        step("Log in the system");
        login();

        step("Upload announcement");
        uploadAnnouncementFile(announcement);

        step("Create IVR");
        createIVR(ivr,announcement);

        step("Goto Numbers page and check if user details is shown for proper number");
        basePage.goToMenuTab(NUMBERS);
        numbersPage.verifyIfNumberInfoShowed(ivr);

        step("Delete test data");
        deleteIVR(ivr.getIvrName());
        deleteAnnouncementFile(announcement.getFileName());
    }

    //test disabled because of the bug
    @Description("Verify if Hunt Group data is shown on numbers page")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "numbersPageTests"},enabled = false)
    public void VerifyIfHuntGroupDataIsShownInNumbersPage(){
        step("Prepare test data");
        HuntGroup huntGroup = new HuntGroup();
        huntGroupList.add(huntGroup);

        step("Log in the system");
        login();

        step("Create hunt group");
        createHuntGroup(huntGroup);

        step("Goto Numbers page and check if user details is shown for proper number");
        basePage.goToMenuTab(NUMBERS);
        numbersPage.verifyIfNumberInfoShowed(huntGroup);

        step("Delete test data");
        deleteHuntGroup(huntGroup.getHuntGroupName());
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        startBrowser();
        login();
        huntGroupCleanUp(huntGroupList);
        ivrCleanUp(ivrList);
        announcementCleanUp(announcementList);
        userCleanUp(userList);
        closeBrowser();
    }

}
