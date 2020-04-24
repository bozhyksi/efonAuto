package lowLevelUserPages.queuesLowLevelUserPage.queuePopups;

import pages.queuesPage.ConfigureQueueTab;
import pages.queuesPage.queuePagePopups.QueueForAgentsPopup;
import tests.queuesPageTest.queueTestData.Queue;
import tests.userPageTests.userPageTestData.User;

import static com.codeborne.selenide.Condition.exist;
import static core.workers.javaScriptExecutor.JavaScriptExecutor.executeDragDropScript;

public class QueueForAgentsUserPopup extends ConfigureQueueTab {
    private QueueForAgentsPopup queueForAgentsPopup = new QueueForAgentsPopup();

    public void addAgentToQueue(String queueName, String userName){
        queueForAgentsPopup.getButtonEditAgentsQueueByText(queueName).click();
        waitUntilAlertDisappear();
        executeDragDropScript(queueForAgentsPopup.getFieldNotSelectedAgentByName(userName),
                                queueForAgentsPopup.getSectionSelected());

        queueForAgentsPopup.getButtonSave().click();
        waitUntilAlertDisappear();
    }

    public void validateAddedAgents(String queueName, String userName){
        queueForAgentsPopup.getButtonEditAgentsQueueByText(queueName).click();
        waitUntilAlertDisappear();
        queueForAgentsPopup.getFieldSelectedAgentByName(userName).should(exist);
        queueForAgentsPopup.getButtonCancel().click();
    }

    public void deselectAgentFromQueue(String queueName, String userName){
        queueForAgentsPopup.getButtonEditAgentsQueueByText(queueName).click();
        waitUntilAlertDisappear();
        executeDragDropScript(queueForAgentsPopup.getFieldSelectedAgentByName(userName),
                queueForAgentsPopup.getSectionNotSelected());
        queueForAgentsPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();
        queueForAgentsPopup.getButtonEditAgentsQueueByText(queueName).click();
        waitUntilAlertDisappear();
        queueForAgentsPopup.getFieldNotSelectedAgentByName(userName).should(exist);
        queueForAgentsPopup.getButtonCancel().click();
    }
}
