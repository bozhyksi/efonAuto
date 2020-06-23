package tests.queuesPageTest;

import api.baseApiMethods.FileManagementApi;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.queuesPageTest.queueTestData.Queue;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static api.baseApiMethods.AbbreviatedNumbersApi.createAbbreviatedNumberApi;
import static api.baseApiMethods.AbbreviatedNumbersApi.deleteAbbreviatedNumberApi;
import static api.baseApiMethods.FileManagementApi.*;
import static api.baseApiMethods.QueueApi.*;
import static api.baseApiMethods.UserApi.*;
import static com.codeborne.selenide.Condition.exist;
import static io.qameta.allure.Allure.addStreamAttachmentAsync;
import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.*;
import static pages.queuesPage.ReportsQueueTab.ReportBy.*;
import static pages.queuesPage.StatusQueueTab.ChangeState.*;
import static tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling.Type.SINGLE;

@Listeners(CustomListeners.class)

public class QueuesPageTests extends BaseTestMethods {
    ArrayList<Queue> queuesList = new ArrayList<>();
    ArrayList<User> usersList = new ArrayList<>();
    ArrayList<AbbreviatedDialling> abbrevNumsList = new ArrayList<>();
    ArrayList<FileManagementTestData> musicList = new ArrayList<>();

    @Description("Verify if user can create/delete new Queue")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "queuePageTest"})
    public void createDeleteQueueTest() {

        Queue queue = new Queue();
        FileManagementTestData music = new FileManagementTestData();
        User user1 = new User();
        User user2 = new User();

        queuesList.add(queue);
        usersList.add(user1);
        usersList.add(user2);
        musicList.add(music);

        uploadMohApi(music);
        createUsersApi(user1, user2);

        login()
                .goToMenuTab(QUEUES)
                .goToMenuTab(CONFIGURE_QUEUES);
        configureQueueTab
                .clickCreateNewQueue()
                .setQueueName(queue.getName())
                .setSubscription()
                .selectMaxWaitTime(queue.getMaxWaitTime())
                .selectQueueManager(user1.getFullName())
                .selectQueueReporter(user2.getFullName())
                .selectPriority(queue.getPriority())
                .selectWaitingMusic(music.getFileName())
                .selectAnnoucFrequency(queue.getAnnouncementFrequency())
                .selectRuleForAgent(queue.getRuleForFindingAgent())
                .selectTimeout(queue.getTimeoutForCalling())
                .selectRetryTime(queue.getWaitingTimeBeforeNextAttempt())
                .selectWaitTimeBeforeNextCall(queue.getWaitingTimeBeforeNextCall())
                .selectRecordCall(queue.getRecordCalls())
                .saveChanges()
                .verifyIfQueueExistsInTheList(queue.getName())
                .deleteQueue(queue)
                .verifyIfQueueNotExistInList(queue.getName());

        deleteUsersApi(user1, user2);
        deleteMohApi(music);
    }

    @Description("Verify if user can edit Queue")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "queuePageTest"})
    public void editQueueTest() {
        User manager = new User();
        User reporter = new User();
        AbbreviatedDialling shortNum = new AbbreviatedDialling(SINGLE);
        Queue queue = new Queue();

        queuesList.add(queue);
        usersList.add(manager);
        usersList.add(reporter);
        abbrevNumsList.add(shortNum);

        createUsersApi(manager, reporter);
        createQueueApi(queue);
        createAbbreviatedNumberApi(shortNum);

        login()
                .goToMenuTab(QUEUES)
                .goToMenuTab(CONFIGURE_QUEUES);
        configureQueueTab
                .clickEditQueueButton(queue)
                .selectQueueManager(manager.getFirstName())
                .selectQueueReporter(reporter.getFirstName())
                .selectLoginShortNum(shortNum)
                .selectMaxWaitTime(queue.getMaxWaitTime())
                .selectPriority(queue.getPriority())
                .selectRuleForAgent(queue.getRuleForFindingAgent())
                .saveChanges()
                .verifyQueueManagerReporterExist(manager.getFullName())
                .verifyQueueManagerReporterExist(reporter.getFullName());
        configureQueueTab
                .clickEditQueueButton(queue)
                .verifyMaxWaitTime(queue.getMaxWaitTime())
                .verifyPriority(queue.getPriority())
                .verifyRuleForAgents(queue.getRuleForFindingAgent())
                .verifyShortNum(shortNum.getSingleShortNum());

        deleteQueueApi(queue);
        deleteUsersApi(manager, reporter);
        deleteAbbreviatedNumberApi(shortNum);
    }

    @Description("Check if user can configure Queue for agents")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "queuePageTest"})
    public void configureQueueForAgentsTest() {
        Queue queue = new Queue();
        User agent1 = new User();
        User agent2 = new User();
        queuesList.add(queue);
        usersList.add(agent1);
        usersList.add(agent2);

        createUsersApi(agent1, agent2);
        createQueueApi(queue);
        login()
                .goToMenuTab(QUEUES)
                .goToMenuTab(CONFIGURE_QUEUES);
        configureQueueTab
                .openQueueAgentPopup(queue)
                .addAgentToQueue(agent1, agent2);
        queueForAgentsPopup
                .validateAddedAgents(queue, agent1, agent2);
        deleteQueueApi(queue);
        deleteUsersApi(agent1, agent2);
    }

    @Description("Check if user can change the Agent status Login/Logout/Wait on Status tab")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "queuePageTest"})
    public void changeAgentStatusTest() {
        Queue queue = new Queue(new User());

        queuesList.add(queue);
        usersList.add(queue.getAgent());

        createQueueApi(queue);
        createUsersApi(queue.getAgent());
        addQueueAgentApi(queue);

        login()
                .goToMenuTab(QUEUES)
                .goToMenuTab(STATUS_QUEUES);
        statusQueuePage
                .selectQueue(queue)
                .changeStatus(queue.getAgent().getFirstName(), Login)
                .changeStatus(queue.getAgent().getFirstName(), Pause)
                .changeStatus(queue.getAgent().getFirstName(), End_Pause)
                .changeStatus(queue.getAgent().getFirstName(), Wait)
                .changeStatus(queue.getAgent().getFirstName(), End_Wait)
                .changeStatus(queue.getAgent().getFirstName(), Logout);
        deleteQueueApi(queue);
        deleteUsersApi(queue.getAgent());
    }

    @Description("Check if admin can change the Agent penalty on Status tab")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "queuePageTest"})
    public void changeAgentPenaltyTest() {
        Queue queue = new Queue(new User());

        queuesList.add(queue);
        usersList.add(queue.getAgent());

        createQueueApi(queue);
        createUsersApi(queue.getAgent());
        addQueueAgentApi(queue);

        login()
                .goToMenuTab(QUEUES)
                .goToMenuTab(STATUS_QUEUES);
        statusQueuePage
                .selectQueue(queue)
                .clickEditPenalty(queue.getAgent())
                .enterPenaltyForAgent(queue.getAgent().getPenalty())
                .saveChanges()
                .verifyPenalty(queue.getAgent().getPenalty());

        deleteQueueApi(queue);
        deleteUsersApi(queue.getAgent());
    }

    @Description("Check if admin can search Queue for recordings")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "queuePageTest"})
    public void searchQueuesForRecordingsTest() {
        Queue queue = new Queue(new User());
        queuesList.add(queue);
        usersList.add(queue.getAgent());

        createUsersApi(queue.getAgent());
        createQueueApi(queue);
        addQueueAgentApi(queue);

        login()
                .goToMenuTab(QUEUES)
                .goToMenuTab(RECORDINGS_QUEUES);
        recordingsQueuePage
                .enterDate(queue.getFromDate(),queue.getToDate())
                .selectQueue(queue)
                .selectAgent(queue.getAgent())
                .clickSearch()
                .verifySearchResults();
        deleteQueueApi(queue);
        deleteUsersApi(queue.getAgent());
    }

    @Description("Check if VPBX admin can create Report by Day")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "queuePageTest"})
    public void createReportByDayTest() {
        Queue queue = new Queue(new User());

        queuesList.add(queue);
        usersList.add(queue.getAgent());

        createUsersApi(queue.getAgent());
        createQueueApi(queue);
        addQueueAgentApi(queue);

        login()
                .goToMenuTab(QUEUES)
                .goToMenuTab(REPORT_QUEUES);
        reportsQueuePage
                .createReportForEveryType(Day, queue, queue.getAgent());
        deleteQueueApi(queue);
        deleteUsersApi(queue.getAgent());
    }

    @Description("Check if VPBX admin can create Report by Month")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "queuePageTest"})
    public void createReportByMonthTest() {
        Queue queue = new Queue(new User());

        queuesList.add(queue);
        usersList.add(queue.getAgent());

        createUsersApi(queue.getAgent());
        createQueueApi(queue);
        addQueueAgentApi(queue);

        login()
                .goToMenuTab(QUEUES)
                .goToMenuTab(REPORT_QUEUES);
        reportsQueuePage
                .createReportForEveryType(Month, queue, queue.getAgent());

        deleteQueueApi(queue);
        deleteUsersApi(queue.getAgent());
    }

    @Description("Check if VPBX admin can create Report by Period")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "queuePageTest"})
    public void CheckIfVpbxAdminCanCreateReportByPeriod() {
        Queue queue = new Queue(new User());

        queuesList.add(queue);
        usersList.add(queue.getAgent());

        createUsersApi(queue.getAgent());
        createQueueApi(queue);
        addQueueAgentApi(queue);

        login()
                .goToMenuTab(QUEUES)
                .goToMenuTab(REPORT_QUEUES);
        reportsQueuePage
                .createReportForEveryType(Period, queue, queue.getAgent());

        deleteQueueApi(queue);
        deleteUsersApi(queue.getAgent());
    }

    @Description("Check if Queue changed name shown in the grid")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "queuePageTest"})
    public void queueChangedNameDisplayingTest() {

        Queue queue = new Queue();
        queuesList.add(queue);

        login();
        configureQueueTab
                .createQueue(queue)
                .clickEditQueueButton(queue)
                .setQueueName(queue.changeName())
                .saveChanges()
                .verifyIfQueueExistsInTheList(queue.getName())
                .deleteQueue(queue);
    }

    @Description("Check if Queue changed Manager shown in the grid")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "queuePageTest"})
    public void queueChangedManagerDisplayingTest() {
        User manager = new User();
        Queue queue = new Queue();
        queuesList.add(queue);
        usersList.add(manager);

        createUsersApi(manager);
        createQueueApi(queue);

        login()
                .goToMenuTab(QUEUES)
                .goToMenuTab(CONFIGURE_QUEUES);
        configureQueueTab
                .clickEditQueueButton(queue)
                .selectQueueManager(manager.getFirstName())
                .saveChanges()
                .verifyQueueManagerReporterExist(manager.getFullName());

        deleteUsersApi(manager);
        deleteQueueApi(queue);
    }

    @Description("Check if Queue changed Reporter shown in the grid")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "queuePageTest"})
    public void queueChangedReporterDisplayingTest() {
        User reporter = new User();
        Queue queue = new Queue();
        queuesList.add(queue);
        usersList.add(reporter);

        createUsersApi(reporter);
        createQueueApi(queue);

        login()
                .goToMenuTab(QUEUES)
                .goToMenuTab(CONFIGURE_QUEUES);
        configureQueueTab
                .clickEditQueueButton(queue)
                .selectQueueReporter(reporter.getFirstName())
                .saveChanges()
                .verifyQueueManagerReporterExist(reporter.getFullName());

        deleteQueueApi(queue);
        deleteUsersApi(reporter);
    }


    @AfterClass(alwaysRun = true)
    private void cleanUp() {
        queueCleanUp(queuesList);
        userCleanUp(usersList);
        abbrevNumsCleanUp(abbrevNumsList);
    }
}
