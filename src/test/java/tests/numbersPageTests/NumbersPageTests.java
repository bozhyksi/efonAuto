package tests.numbersPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.IVRpageTests.IVRtestData.IVRtestData;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static api.baseApiMethods.HuntGroupApi.createHuntGroupApi;
import static api.baseApiMethods.HuntGroupApi.deleteHuntGroupApi;
import static api.baseApiMethods.IVRApi.createIvrApi;
import static api.baseApiMethods.IVRApi.deleteIvrApi;
import static api.baseApiMethods.UserApi.createUserApi;
import static api.baseApiMethods.UserApi.deleteUserApi;
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

        User user = new User();
        userList.add(user);

        createUserApi(user.getJson());

        login()
            .goToMenuTab(NUMBERS);
        numbersPage
                .verifyIfNumberInfoShown(user);

        deleteUserApi(user.getId());
    }

    @Description("Verify if IVR data is shown on numbers page")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "numbersPageTests"})
    public void VerifyIfIvrDataIsShownInNumbersPage(){
        IVRtestData ivr = new IVRtestData(new FileManagementTestData());
        ivrList.add(ivr);
        announcementList.add(ivr.getAnnouncement());

        createIvrApi(ivr);

        login()
                .goToMenuTab(NUMBERS);
        numbersPage
                .verifyIfNumberInfoShown(ivr);

        deleteIvrApi(ivr);

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
                .verifyIfNumberInfoShown(huntGroup);
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
