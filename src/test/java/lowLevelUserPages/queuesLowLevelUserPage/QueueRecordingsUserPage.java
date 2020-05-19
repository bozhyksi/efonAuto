package lowLevelUserPages.queuesLowLevelUserPage;

import io.qameta.allure.Step;

import static core.configuration.preparations.eFonApp.recordingsQueuePage;

public class QueueRecordingsUserPage extends QueuesBaseUserPage {

    @Step("Verify if \"Queue display name\" dropdown does not contain queue")
    public QueueRecordingsUserPage verifyIfQueueDisplayNameDropdownDoesNotContainQueue(String queueName){
        super.verifyIfDropDownDoesNotContainQueue(recordingsQueuePage.getDropdownQueueDisplayName(),queueName);
        return this;
    }

}
