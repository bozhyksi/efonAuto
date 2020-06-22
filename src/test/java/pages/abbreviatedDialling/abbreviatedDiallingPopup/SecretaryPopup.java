package pages.abbreviatedDialling.abbreviatedDiallingPopup;

import pages.abbreviatedDialling.AbbreviatedNumbers;
import pages.queuesPage.queuePagePopups.QueueForAgentsPopup;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;
import tests.queuesPageTest.queueTestData.Queue;
import tests.userPageTests.userPageTestData.User;

import static com.codeborne.selenide.Condition.exist;
import static core.workers.javaScriptExecutor.JavaScriptExecutor.executeDragDropScript;

public class SecretaryPopup extends AbbreviatedNumbers {
    //SecretaryPopup === QueueForAgentsPopup;
    QueueForAgentsPopup queueForAgentsPopup = new QueueForAgentsPopup();

    public AbbreviatedNumbers addSecretaryToShortNumber(User user){
        executeDragDropScript(queueForAgentsPopup.getFieldNotSelectedAgentByName(user.getFirstName()),
                queueForAgentsPopup.getSectionSelected());
        getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();
        return new AbbreviatedNumbers();
    }

    public void validateAddedSecretaries(User ... users){
        for (User user: users) {
            queueForAgentsPopup.getFieldSelectedAgentByName(user.getFirstName()).should(exist);
            waitUntilAlertDisappear();
        }
        queueForAgentsPopup.getButtonCancel().click();
    }
}
