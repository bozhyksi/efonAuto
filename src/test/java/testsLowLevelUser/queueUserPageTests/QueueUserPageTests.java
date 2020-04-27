package testsLowLevelUser.queueUserPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.queuesPage.ReportsQueueTab;
import tests.queuesPageTest.queueTestData.Queue;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.*;
import static pages.queuesPage.ReportsQueueTab.ReportBy.*;

@Listeners(CustomListeners.class)

public class QueueUserPageTests extends BaseTestMethods {
    ArrayList<User> usersList = new ArrayList<>();

    @Description("Check if low-level user can select/deselect agents to Queue")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "sendSmsUserPageTests"})
    public void CheckIfLowLevelUserCanAddAgentsToQueue(){

        step("Log in the system");
        loginAsLowLevelUser();

        step("Open Queue for agents popup");
        basePage.goToMenuTab(QUEUES).goToMenuTab(CONFIGURE_QUEUES);

        step("Add agent");
        queueForAgentsLowLevelUserPopup.addAgentToQueue("AutoTestQueue", "AutoTestUser");
        refreshPage();

        step("Check if agents were added");
        queueForAgentsLowLevelUserPopup.validateAddedAgents("AutoTestQueue", "AutoTestUser");

        step("Deselect added user from agent");
        queueForAgentsLowLevelUserPopup.deselectAgentFromQueue("AutoTestQueue", "AutoTestUser");
    }

    // test fails, bug created EPRO-1011
    @Description("Check if low-level user can change penalty for Queue agent")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "queueUserPageTest"}, enabled = false)
    public void CheckIfLowLevelUserCanChangePenaltyForQueueAgent(){
        step("Prepare test data, create test user");
        User user = new User();
        usersList.add(user);

        login();
        createUser(user);
        logOut();

        step("Log in");
        loginAsLowLevelUser();

        step("Add created user as agent to queue");
        addAgentToQueue("AutoTestQueue",user);

        step("Goto Status tab and change penalty for created user");
        basePageLowLevelUser.goToMenuTab(QUEUES).goToMenuTab(STATUS_QUEUES);
        queueStatusUserPage.changePenaltyForAgent("AutoTestQueue",user);
    }

    @Description("Check if low-level user can create Queues Report by DAY")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","queueUserPageTest"})
    public void CheckIfUserCanCreateReportByDay(){
        step("Prepare test data");
        Queue queue = new Queue("AutoTestQueue");

        step("Log in");
        loginAsLowLevelUser();

        step("Goto Queues Reports by DAY");
        basePageLowLevelUser.goToMenuTab(QUEUES).goToMenuTab(REPORT_QUEUES);

        step("Generate reports by day");
        queueReportsUserPage.createDayReports(Day,queue);

    }

    @Description("Check if low-level user can create Queues Report by MONTH")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","queueUserPageTest"})
    public void CheckIfUserCanCreateReportByMonth(){
        step("Prepare test data");
        Queue queue = new Queue("AutoTestQueue");

        step("Log in");
        loginAsLowLevelUser();

        step("Goto Queues Reports by DAY");
        basePageLowLevelUser.goToMenuTab(QUEUES).goToMenuTab(REPORT_QUEUES);

        step("Generate reports by day");
        queueReportsUserPage.createDayReports(Month,queue);
    }

    @Description("Check if low-level user can create Queues Report by PERIOD")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","queueUserPageTest"})
    public void CheckIfUserCanCreateReportByPeriod(){
        step("Prepare test data");
        Queue queue = new Queue("AutoTestQueue");

        step("Log in");
        loginAsLowLevelUser();

        step("Goto Queues Reports by DAY");
        basePageLowLevelUser.goToMenuTab(QUEUES).goToMenuTab(REPORT_QUEUES);

        step("Generate reports by day");
        queueReportsUserPage.createDayReports(Period,queue);
    }

    @AfterClass(alwaysRun = true, enabled = false)
    private void cleanUp(){
        startBrowser();
        login();
        userCleanUp(usersList);
        closeBrowser();
    }


}
