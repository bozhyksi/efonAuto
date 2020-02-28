package tests.queuesPageTest;

import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;
import tests.queuesPageTest.queueTestData.Queue;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static io.qameta.allure.Allure.step;

@Listeners(CustomListeners.class)

public class QueuesPageTests extends BaseTestMethods {
    ArrayList<Queue> queuesList = new ArrayList<>();
    ArrayList<User> usersList = new ArrayList<>();
    ArrayList<AbbreviatedDialling> abbrevNumsList = new ArrayList<>();

    @Description("Verify if user can create new Queue")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "queuePageTest"})
    public void VerifyIfUserCanCreateNewQueue(){
        step("Prepare test data - users, queue instances");
        Queue queue = new Queue();
        User user1 = new User();
        User user2 = new User();

        queuesList.add(queue);
        usersList.add(user1);
        usersList.add(user2);

        step("Log in the system");
        login();

        step("Create test users");
        createUser(user1);
        createUser(user2);

        step("Navigate to the Queue page, Configure tab");
        basePage.getTabQueues().click();
        queuesBasePage.getTabConfigureQueues().click();

        step("Click \"Create new Queue\" button");
        configureQueueTab.getButtonCreateNewQueue().click();

        step("Fill in \"Queue name\" field");
        createNewQueuePopup.getInputQueueName().setValue(queue.getName());

        step("Select \"Subscription\"");
        createNewQueuePopup.getDropdownSubscription().selectOption(1);
        queue.setSubscription(createNewQueuePopup.getDropdownSubscription().getSelectedText());

        step("Select random value from \"Max. waiting time for customer service\" dropdown");
        createNewQueuePopup.getDropdownMaxWaintingTime().selectOptionByValue(queue.getMaxWaitTime());

        step("Select manager");
        queue.setManager(user1.getFullName());
        createNewQueuePopup.getDropdownManager().selectOptionContainingText(queue.getManager());

        step("Select reporter");
        queue.setReporter(user2.getFullName());
        createNewQueuePopup.getDropdownReporter().selectOptionContainingText(queue.getReporter());

        step("Set priority value");
        createNewQueuePopup.getDropdownPriority().selectOptionContainingText(queue.getPriority());

        step("Select \"Waiting music\"");
        createNewQueuePopup.getDropdownWaitingMusic().selectOption(1);
        queue.setWaitingMusic(createNewQueuePopup.getDropdownWaitingMusic().getSelectedText());

        step("Select \"Filename announcement\"");
        createNewQueuePopup.getDropdownFileNameAnnounc().selectOption(1);
        queue.setFilenameAnnouncement(createNewQueuePopup.getDropdownFileNameAnnounc().getSelectedText());

        step("Select \"Announcement frequency\"");
        createNewQueuePopup.getDropdownAnnounFreq().selectOptionByValue(queue.getAnnouncementFrequency());

        step("Select \"Rule for finding agent\"");
        createNewQueuePopup.getDropdownRulesForFindAgent().selectOptionContainingText(queue.getRuleForFindingAgent());

        step("Select \"Timeout for calling an agent\"");
        createNewQueuePopup.getDropdownTimeOutForCall().selectOptionByValue(queue.getTimeoutForCalling());

        step("Select \"Waiting time (in sec.) before next attempt\"");
        createNewQueuePopup.getDropdownRetry().selectOptionByValue(queue.getWaitingTimeBeforeNextAttempt());

        step("Select \"Waiting time (in sec.) for agents before next call\"");
        createNewQueuePopup.getDropdownWrapUpTime().selectOptionByValue(queue.getWaitingTimeBeforeNextCall());

        step("Select \"Record calls\"");
        createNewQueuePopup.getDropdownRecordCalls().selectOptionContainingText(queue.getRecordCalls());

        step("Save Changes");
        createNewQueuePopup.getButtonSave().click();
        refreshPage();
        waitUntilAlertDisappear();

        step("Verify if Queue was created");
        configureQueueTab.getFieldQueueNameByText(queue.getName()).should(Condition.exist);

        step("Delete created Queue");
        configureQueueTab.getButtonDeleteQueueByName(queue.getName()).click();
        confirmationPopup.getYesButton().click();
        refreshPage();
        waitUntilAlertDisappear();

        step("Verify if Queue was created");
        configureQueueTab.getFieldQueueNameByText(queue.getName()).shouldNot(Condition.exist);

        step("Delete created users");
        deleteUser(user1);
        deleteUser(user2);
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        startBrowser();
        login();
        queueCleanUp(queuesList);
        userCleanUp(usersList);
        closeBrowser();
    }
}