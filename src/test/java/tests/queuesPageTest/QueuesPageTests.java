package tests.queuesPageTest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.basePage.BasePage;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;
import tests.queuesPageTest.queueTestData.Queue;
import tests.userPageTests.userPageTestData.User;

import javax.swing.*;
import java.util.ArrayList;

import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.QUEUES;

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

    @Description("Verify if user can edit Queue")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "queuePageTest"})
    public void VerifyIfUserCanEditQueue(){
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

        step("Create new Queue");
        createQueue(queue);

        step("Open edit popup, click edit button");
        configureQueueTab.getButtonEditQueueByName(queue.getName()).click();
        waitUntilAlertDisappear();

        step("Select manager");
        queue.setManager(user1.getFullName());
        createNewQueuePopup.getDropdownManager().selectOptionContainingText(queue.getManager());

        step("Select reporter");
        queue.setReporter(user2.getFullName());
        createNewQueuePopup.getDropdownReporter().selectOptionContainingText(queue.getReporter());

        step("Change \"Max. waiting time for customer\" field value");
        queue.setMaxWaitTime(Queue.MaxWaitTime.getRandomVal().getWaitTime());
        createNewQueuePopup.getDropdownMaxWaintingTime().setValue(queue.getMaxWaitTime());

        step("Change \"Priority\" drop down value");
        queue.setPriority(Queue.Priority.getRandomVal().getPrior());
        createNewQueuePopup.getDropdownPriority().selectOptionContainingText(queue.getPriority());

        step("Change \"Set Rule For Finding Agent\" drop down value");
        queue.setRuleForFindingAgent(Queue.RuleForFindingAgent.getRandomVal().getRule());
        createNewQueuePopup.getDropdownRulesForFindAgent().selectOptionContainingText(queue.getRuleForFindingAgent());

        step("Save made changes");
        createNewQueuePopup.getButtonSave().click();
        waitUntilAlertDisappear();

        step("Verify if Queue exists in grid");
        configureQueueTab.getFieldQueueNameByText(queue.getName()).should(Condition.exist);

        step("Open edit popup, click edit button");
        configureQueueTab.getButtonEditQueueByName(queue.getName()).click();
        waitUntilAlertDisappear();

        step("Verify if all made changes were saved");
        createNewQueuePopup.getDropdownMaxWaintingTime().getSelectedText().contains(queue.getMaxWaitTime());
        createNewQueuePopup.getDropdownPriority().getSelectedText().contains(queue.getPriority());
        createNewQueuePopup.getDropdownRulesForFindAgent().getSelectedText().contains(queue.getRuleForFindingAgent());
        createNewQueuePopup.getButtonCancel().click();

        step("Clear test data");
        deleteQueue(queue.getName());
        deleteUser(user1);
        deleteUser(user2);
    }

    @Description("Check if user can configure Queue for agents")
    @Test(/*retryAnalyzer = RetryAnalyzer.class, */groups = {"regression", "smoke", "queuePageTest"} ,enabled = false)
    public void CheckIfUserCanConfigureQueueForAgents(){
        step("Prepare test data - users, queue instances");
        Queue queue = new Queue();
        User user1 = new User();
        User user2 = new User();

        queuesList.add(queue);
        usersList.add(user1);
        usersList.add(user2);

        step("Log in the system");
        login();

        step("Open Queue for agents popup");
        basePage.goToMenuTab(QUEUES);
        configureQueueTab.getButtonEditAgentsQueueByText("asd").click();

        step("Move agent");
        String script = "(function( $ ) {" +
                "    $.fn.simulateDragDrop = function(options) {" +
                "        return this.each(function() {" +
                "            new $.simulateDragDrop(this, options);" +
                "        });" +
                "    };" +
                "    $.simulateDragDrop = function(elem, options) {" +
                "        this.options = options;" +
                "        this.simulateEvent(elem, options);" +
                "    };" +
                "    $.extend($.simulateDragDrop.prototype, {" +
                "        simulateEvent: function(elem, options) {" +
                "            /*Simulating drag start*/" +
                "            var type = 'dragstart';" +
                "            var event = this.createEvent(type);" +
                "            this.dispatchEvent(elem, type, event);" +
                "            /*Simulating drop*/" +
                "            type = 'drop';" +
                "            var dropEvent = this.createEvent(type, {});" +
                "            dropEvent.dataTransfer = event.dataTransfer;" +
                "            this.dispatchEvent($(options.dropTarget)[0], type, dropEvent);" +
                "            /*Simulating drag end*/" +
                "            type = 'dragend';" +
                "            var dragEndEvent = this.createEvent(type, {});" +
                "            dragEndEvent.dataTransfer = event.dataTransfer;" +
                "            this.dispatchEvent(elem, type, dragEndEvent);" +
                "        }," +
                "        createEvent: function(type) {" +
                "            var event = document.createEvent(\"CustomEvent\");" +
                "            event.initCustomEvent(type, true, true, null);" +
                "            event.dataTransfer = {" +
                "                data: {" +
                "                }," +
                "                setData: function(type, val){" +
                "                    this.data[type] = val;" +
                "                }," +
                "                getData: function(type){" +
                "                    return this.data[type];" +
                "                }" +
                "            };" +
                "            return event;" +
                "        }," +
                "        dispatchEvent: function(elem, type, event) {" +
                "            if(elem.dispatchEvent) {" +
                "                elem.dispatchEvent(event);" +
                "            }else if( elem.fireEvent ) {" +
                "                elem.fireEvent(\"on\"+type, event);" +
                "            }" +
                "        }" +
                "    });" +
                "})(jQuery);";

        ((JavascriptExecutor)driver).executeScript(script+"$('#column-a').simulateDragDrop({ dropTarget: '#column-b'});" );
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
