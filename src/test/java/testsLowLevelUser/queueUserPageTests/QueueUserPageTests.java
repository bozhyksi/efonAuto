package testsLowLevelUser.queueUserPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.CONFIGURE_QUEUES;
import static pages.basePage.BasePage.MenuTabsBasePage.QUEUES;

@Listeners(CustomListeners.class)

public class QueueUserPageTests extends BaseTestMethods {

    @Description("Check if low-level user can select/deselect agents to Queue")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "sendSmsUserPageTests"})
    public void CheckIfLowLevelUserCanAddAgentsToQueue(){

        step("Log in the system");
        loginAsLowLevelUser();

        step("Open Queue for agents popup");
        basePage.goToMenuTab(QUEUES).goToMenuTab(CONFIGURE_QUEUES);

        step("Add agent");
        queueForAgentsLowLevelUserPopup.addAgentToQueue("AutoTestQueue", "AutoTestUser");
        refreshPage();

        step("Check if agents were added");
        queueForAgentsLowLevelUserPopup.validateAddedAgents("AutoTestQueue", "AutoTestUser");

        step("Deselect added user from agent");
        queueForAgentsLowLevelUserPopup.deselectAgentFromQueue("AutoTestQueue", "AutoTestUser");
    }
}
