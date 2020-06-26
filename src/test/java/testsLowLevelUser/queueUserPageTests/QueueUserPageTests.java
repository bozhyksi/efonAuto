package testsLowLevelUser.queueUserPageTests;

import com.codeborne.selenide.Condition;
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
import static api.baseApiMethods.UserApi.*;
import static com.codeborne.selenide.Condition.value;
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
        Queue queue = new Queue(new User());
        queuesList.add(queue);
        usersList.add(queue.getAgent());

        createUsersApi(queue.getAgent());
        createQueueWithMangerReporterApi(queue,autotestUserContactID,autotestUserContactID);

        loginAsLowLevelUser()
                .goToMenuTab(QUEUES)
                .goToMenuTab(CONFIGURE_QUEUES);
        configureQueueTab
                .openQueueAgentPopup(queue)
                .addAgentToQueue(queue.getAgent())
                .openQueueAgentPopup(queue)
                .validateAddedAgents(queue.getAgent().getFirstName());

        deleteQueueApi(queue);
        deleteUsersApi(queue.getAgent());
    }

    @Description("Check if low-level user can change penalty for Queue agent")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "queueUserPageTest"})
    public void changePenaltyTest(){
        Queue queue = new Queue(new User());
        usersList.add(queue.getAgent());
        queuesList.add(queue);

        createQueueWithMangerReporterApi(queue,autotestUserContactID, autotestUserContactID);
        createUsersApi(queue.getAgent());
        addQueueAgentApi(queue);

        loginAsLowLevelUser()
                .goToMenuTab(QUEUES)
                .goToMenuTab(STATUS_QUEUES);
        statusQueuePage
                .selectQueue(queue)
                .clickEditPenalty(queue.getAgent())
                .enterPenaltyForAgent(queue.getAgent().getPenalty())
                .saveChanges()
                .selectQueue(queue)
                .clickEditPenalty(queue.getAgent())
                .getInputPenalty().shouldHave(value(queue.getAgent().getPenalty()));

        deleteQueueApi(queue);
        deleteUsersApi(queue.getAgent());
    }

    @Description("Check if low-level user can create Queues Report by DAY")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","queueUserPageTest"})
    public void createReportByDayTest(){
        Queue queue = new Queue();
        queuesList.add(queue);

        createQueueWithMangerReporterApi(queue,autotestUserContactID,autotestUserContactID);
        addQueueAgentApi(queue,autotestUserAccountID);

        loginAsLowLevelUser()
                .goToMenuTab(QUEUES)
                .goToMenuTab(REPORT_QUEUES);
        reportsQueuePage
                .createReportForEveryType(Day, queue,autotestUserFirstName);

        deleteQueueApi(queue);
    }

    @Description("Check if low-level user can create Queues Report by MONTH")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","queueUserPageTest"})
    public void createReportByMonthTest(){
        Queue queue = new Queue();
        queuesList.add(queue);

        createQueueWithMangerReporterApi(queue,autotestUserContactID,autotestUserContactID);
        addQueueAgentApi(queue,autotestUserAccountID);

        loginAsLowLevelUser()
                .goToMenuTab(QUEUES)
                .goToMenuTab(REPORT_QUEUES);
        reportsQueuePage
                .createReportForEveryType(Month, queue,autotestUserFirstName);

        deleteQueueApi(queue);
    }

    @Description("Check if low-level user can create Queues Report by PERIOD")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","queueUserPageTest"})
    public void createReportByPeriodTest(){
        Queue queue = new Queue();
        queuesList.add(queue);

        createQueueWithMangerReporterApi(queue,autotestUserContactID,autotestUserContactID);
        addQueueAgentApi(queue,autotestUserAccountID);

        loginAsLowLevelUser()
                .goToMenuTab(QUEUES)
                .goToMenuTab(REPORT_QUEUES);
        reportsQueuePage
                .createReportForEveryType(Period,queue,autotestUserFirstName);

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
    public void unAssignedManagerAccessQueue(){
        Queue queue = new Queue();
        queuesList.add(queue);

        createQueueWithMangerReporterApi(queue,autotestUserContactID,autotestUserContactID);

        login()
                .goToMenuTab(QUEUES)
                .goToMenuTab(CONFIGURE_QUEUES);
        configureQueueTab
                .clickEditQueueButton(queue.getName())
                .unassignQueueManager(autotestUserFullName)
                .unassignQueueReporter(autotestUserFullName)
                .saveChanges()
                .refreshPage();
        logOut();

        loginAsLowLevelUser()
                .goToMenuTab(QUEUES)
                .goToMenuTab(CONFIGURE_QUEUES);
        configureQueueTab
                .verifyIfQueueNotExistInList(queue.getName());

        deleteQueueApi(queue);
    }

    @Description("Verify if Queues where user is NOT a manager are not available in Search dropdown on Queue status tab")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","queueUserPageTest"})
    public void verifyIfQueuesWhereUserIsNotAManagerAreNotAvailableInSearchDropdownOnQueueStatusTab(){
        Queue queue = new Queue();
        Queue queue2 = new Queue();
        queuesList.add(queue);
        queuesList.add(queue2);

        createQueueWithMangerReporterApi(queue,autotestUserContactID,autotestUserContactID);
        createQueueApi(queue2);

        loginAsLowLevelUser()
                .goToMenuTab(QUEUES)
                .goToMenuTab(STATUS_QUEUES);
        statusQueuePage
                .verifyIfSearchDropDownDoesNotContainQueue(queue.getName());

        deleteQueueApi(queue, queue2);
    }

    @Description("Verify if Queues where user is NOT a manager are not available in \"Queue display name\" dropdown on Queue RECORDINGS tab")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","queueUserPageTest"})
    public void verifyIfQueuesWhereUserIsNotAManagerAreNotAvailableInQueueDisplayNameDropdownOnQueueRecordingsTab(){
        Queue queue = new Queue();
        Queue queue2 = new Queue();
        queuesList.add(queue);
        queuesList.add(queue2);

        createQueueWithMangerReporterApi(queue,autotestUserContactID,autotestUserContactID);
        createQueueApi(queue2);

        loginAsLowLevelUser()
                .goToMenuTab(QUEUES)
                .goToMenuTab(RECORDINGS_QUEUES);
        recordingsQueuePage
                .verifyIfDisplayNameDropDownDoesNotContainQueue(queue.getName());
        logOut();

        deleteQueueApi(queue,queue2);
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        userCleanUp(usersList);
        queueCleanUp(queuesList);
    }
}
