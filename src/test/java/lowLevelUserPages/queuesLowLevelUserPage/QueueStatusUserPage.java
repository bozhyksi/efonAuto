package lowLevelUserPages.queuesLowLevelUserPage;

import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.queuesPage.StatusQueueTab;
import pages.queuesPage.queuePagePopups.PenaltyPopup;
import tests.userPageTests.userPageTestData.User;

import static com.codeborne.selenide.Condition.exist;
import static core.configuration.preparations.eFonApp.penaltyPopup;
import static core.configuration.preparations.eFonApp.statusQueuePage;
import static io.qameta.allure.Allure.step;

public class QueueStatusUserPage extends QueuesBaseUserPage {

    public void changePenaltyForAgent(String queue, User user){
        statusQueuePage.getDropdownSearch().selectOptionContainingText(queue);

        step("Verify if selected Queue agents appeared in search results");
        statusQueuePage.getFieldAgentByText(user.getFirstName()).should(exist);

        step("Open Penalty popup");
        statusQueuePage.getButtonEditByName(user.getFirstName()).click();
        waitUntilAlertDisappear();

        step("Set penalty value and validate popup headers");
        penaltyPopup.changePenaltyForAgent(user);
        refreshPage();

        step("Validate if penalty was changed");
        statusQueuePage.getFieldPenaltyByText(user.getPenalty()).should(exist);
    }

    @Step("Verify if Search dropdown does not contain queue")
    public QueueStatusUserPage verifyIfSearchDropDownDoesNotContainQueue(String queueName){
        super.verifyIfDropDownDoesNotContainQueue(statusQueuePage.getDropdownSearch(),queueName);
        return this;
    }
}
