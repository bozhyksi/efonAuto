package lowLevelUserPages.queuesLowLevelUserPage;

import pages.queuesPage.StatusQueueTab;
import pages.queuesPage.queuePagePopups.PenaltyPopup;
import tests.userPageTests.userPageTestData.User;

import static com.codeborne.selenide.Condition.exist;
import static io.qameta.allure.Allure.step;

public class QueueStatusUserPage extends QueuesBaseUserPage {
    StatusQueueTab statusQueuePage = new StatusQueueTab();
    PenaltyPopup penaltyPopup = new PenaltyPopup();

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
}
