package tests.numbersPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.IVRpageTests.IVRtestData.IVRtestData;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static api.baseApiClasses.FileManagementApi.deleteAnnouncementApi;
import static api.baseApiClasses.FileManagementApi.uploadAnnouncementApi;
import static api.baseApiClasses.HuntGroupApi.createHuntGroupApi;
import static api.baseApiClasses.HuntGroupApi.deleteHuntGroupApi;
import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.NUMBERS;

@Listeners(CustomListeners.class)

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

    @Description("Verify if IVR data is shown on numbers page")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "numbersPageTests"})
    public void VerifyIfIvrDataIsShownInNumbersPage(){
        IVRtestData ivr = new IVRtestData();
        FileManagementTestData announcement = new FileManagementTestData();
        ivrList.add(ivr);
        announcementList.add(announcement);

        uploadAnnouncementApi(announcement);
        login();
        ivrPage
                .createIvr(ivr,announcement);
        basePage
                .goToMenuTab(NUMBERS);
        numbersPage
                .verifyIfNumberInfoShowed(ivr);
        deleteIVR(ivr.getIvrName());
        deleteAnnouncementApi(announcement.getId());
    }


    @Description("Verify if Hunt Group data is shown on numbers page")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "numbersPageTests"})
    public void VerifyIfHuntGroupDataIsShownInNumbersPage(){

        HuntGroup huntGroup = new HuntGroup();
        createHuntGroupApi(huntGroup.getJson());
        login();
        basePage
                .goToMenuTab(NUMBERS);
        numbersPage
                .verifyIfNumberInfoShowed(huntGroup);
        deleteHuntGroupApi(huntGroup.getId());

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
