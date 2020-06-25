package testsLowLevelUser.queueUserPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.queuesPageTest.queueTestData.Queue;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static api.baseApiMethods.QueueApi.*;
import static io.qameta.allure.Allure.step;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.*;
import static pages.basePage.BasePage.MenuTabsBasePage.*;
import static pages.queuesPage.ReportsQueueTab.ReportBy.*;
import static testsLowLevelUser.testData.AutotestUserData.*;

@Listeners(CustomListeners.class)

public class QueueUserPageTests extends BaseTestMethods {
    ArrayList<User> usersList = new ArrayList<>();
    ArrayList<Queue> queuesList = new ArrayList<>();

    @Description("Check if low-level user can select/deselect agents to Queue")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "queueUserPageTest"})
    public void addAgentsToQueueTest(){
        Queue queue = new Queue();
        queuesList.add(queue);

        createQueueWithMangerReporterApi(queue,autotestUserContactID,autotestUserContactID);

        loginAsLowLevelUser()
                .goToMenuTab(QUEUES)
                .goToMenuTab(CONFIGURE_QUEUES);
        configureQueueTab
                .openQueueAgentPopup(queue)
                .addAgentToQueue(autotestUserFirstName)
                .openQueueAgentPopup(queue)
                .validateAddedAgents(autotestUserFirstName);

        deleteQueueApi(queue);
    }

    // test fails, bug created EPRO-1011
    @Description("Check if low-level user can change penalty for Queue agent ==== test fails, bug created EPRO-1011")
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
        //addAgentToQueue("AutoTestQueue",user);

        step("Goto Status tab and change penalty for created user");
        basePageLowLevelUser.goToMenuTab(QUEUES).goToMenuTab(STATUS_QUEUES);
        queueStatusUserPage.changePenaltyForAgent("AutoTestQueue",user);
    }

    @Description("Check if low-level user can create Queues Report by DAY")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","queueUserPageTest"})
    public void createReportByDayTest(){
        Queue queue = new Queue();
        queuesList.add(queue);

        createQueueWithMangerReporterApi(queue,autotestUserContactID,autotestUserContactID);

        loginAsLowLevelUser()
                .goToMenuTab(QUEUES)
                .goToMenuTab(REPORT_QUEUES);
        queueReportsUserPage
                .createDayReports(Day,queue);

        deleteQueueApi(queue);
    }

    @Description("Check if low-level user can create Queues Report by MONTH")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","queueUserPageTest"})
    public void createReportByMonthTest(){
        Queue queue = new Queue();
        queuesList.add(queue);

        createQueueWithMangerReporterApi(queue,autotestUserContactID,autotestUserContactID);

        loginAsLowLevelUser()
                .goToMenuTab(QUEUES)
                .goToMenuTab(REPORT_QUEUES);
        queueReportsUserPage
                .createDayReports(Month,queue);

        deleteQueueApi(queue);
    }

    @Description("Check if low-level user can create Queues Report by PERIOD")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","queueUserPageTest"})
    public void createReportByPeriodTest(){
        Queue queue = new Queue();
        queuesList.add(queue);

        createQueueWithMangerReporterApi(queue,autotestUserContactID,autotestUserContactID);

        loginAsLowLevelUser()
                .goToMenuTab(QUEUES)
                .goToMenuTab(REPORT_QUEUES);
        queueReportsUserPage
                .createDayReports(Period,queue);

        deleteQueueApi(queue);
    }

    @Description("Check if low-level user can see Queues where he is assigned as a manager")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","queueUserPageTest"})
    public void assignedQueuesAccessTest(){
        Queue queue = new Queue();
        queuesList.add(queue);

        createQueueWithMangerReporterApi(queue,autotestUserContactID,autotestUserContactID);

        loginAsLowLevelUser()
                .goToMenuTab(QUEUES)
                .goToMenuTab(CONFIGURE_QUEUES);
        configureQueueTab
                .verifyIfQueueExistsInTheList(queue.getName());

        deleteQueueApi(queue);
    }

    @Description("Check if low-level user can NOT see Queues where he is not assigned as a manager")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","queueUserPageTest"})
    public void notAssignedQueuesAccessTest(){
        Queue queue = new Queue();
        queuesList.add(queue);

        createQueueApi(queue);

        loginAsLowLevelUser()
                .goToMenuTab(QUEUES)
                .goToMenuTab(CONFIGURE_QUEUES);
        configureQueueTab
                .verifyIfQueueNotExistInList(queue.getName());

        deleteQueueApi(queue);
    }

    @Description("Check if low-level user can NOT see Queues where he was unassigned as a manager")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","queueUserPageTest"})
    public void CheckIfUserCanNotSeeQueuesWhereHeWasUnAssignedAsAManager(){

        Queue queue = new Queue();
        queuesList.add(queue);

        login();
        basePage
                .goToMenuTab(QUEUES)
                .goToMenuTab(CONFIGURE_QUEUES);
        configureQueueTab
                .clickCreateNewQueue()
                .setQueueName(queue.getName())
                .setSubscription()
                .selectQueueManager(autotestUserFullName)
                .saveChanges()
                .refreshPage();
        configureQueueTab
                .clickEditQueueButton(queue.getName())
                .unassignQueueManager(autotestUserFullName)
                .saveChanges()
                .refreshPage();
        logOut();

        loginAsLowLevelUser();
        basePageLowLevelUser
                .goToMenuTab(QUEUES)
                .goToMenuTab(CONFIGURE_QUEUES);
        configureQueueTab
                .verifyIfQueueNotExistInList(queue.getName());
        logOut();

        login();
        deleteQueue(queue.getName());
    }

    @Description("Verify if Queues where user is NOT a manager are not available in Search dropdown on Queue status tab")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","queueUserPageTest"})
    public void VerifyIfQueuesWhereUserIsNotAManagerAreNotAvailableInSearchDropdownOnQueueStatusTab(){
        Queue queue = new Queue();
        queuesList.add(queue);

        login();
        basePage
                .goToMenuTab(QUEUES)
                .goToMenuTab(CONFIGURE_QUEUES);
        configureQueueTab
                .clickCreateNewQueue()
                .setQueueName(queue.getName())
                .setSubscription()
                .saveChanges()
                .refreshPage();
        logOut();

        loginAsLowLevelUser();
        basePageLowLevelUser
                .goToMenuTab(QUEUES)
                .goToMenuTab(STATUS_QUEUES);
        queueStatusUserPage.
                verifyIfSearchDropDownDoesNotContainQueue(queue.getName());
        logOut();

        login();
        deleteQueue(queue.getName());
    }

    @Description("Verify if Queues where user is NOT a manager are not available in \"Queue display name\" dropdown on Queue RECORDINGS tab")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","queueUserPageTest"})
    public void VerifyIfQueuesWhereUserIsNotAManagerAreNotAvailableInQueueDisplayNameDropdownOnQueueRecordingsTab(){
        Queue queue = new Queue();
        queuesList.add(queue);

        login();
        basePage
                .goToMenuTab(QUEUES)
                .goToMenuTab(CONFIGURE_QUEUES);
        configureQueueTab
                .clickCreateNewQueue()
                .setQueueName(queue.getName())
                .setSubscription()
                .saveChanges()
                .refreshPage();
        logOut();

        loginAsLowLevelUser();
        basePageLowLevelUser
                .goToMenuTab(QUEUES)
                .goToMenuTab(RECORDINGS_QUEUES);
        queueRecordingsUserPage.
                verifyIfQueueDisplayNameDropdownDoesNotContainQueue(queue.getName());
        logOut();

        login();
        deleteQueue(queue.getName());
    }


    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        userCleanUp(usersList);
        queueCleanUp(queuesList);
    }


}
