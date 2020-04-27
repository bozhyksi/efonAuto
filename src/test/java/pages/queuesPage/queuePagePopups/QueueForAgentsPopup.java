package pages.queuesPage.queuePagePopups;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.basePage.basePopup.BasePopup;
import pages.queuesPage.ConfigureQueueTab;
import tests.queuesPageTest.queueTestData.Queue;
import tests.userPageTests.userPageTestData.User;

import static com.codeborne.selenide.Condition.exist;
import static core.workers.javaScriptExecutor.JavaScriptExecutor.executeDragDropScript;

public class QueueForAgentsPopup extends ConfigureQueueTab {
    //<editor-fold desc="Locations">
    private String popupTitleXpath = "//div[@class=\"modal-content\"]//h3";
    private String fieldNotSelectedAgentByNameXpath = "//h2[text()=\"Not selected\"]//parent::div//section/div[text()[contains(.,\"%s\")]]";
    private String fieldSelectedAgentByNameXpath = "//h2[text()=\"Selected\"]//parent::div//section/div[text()[contains(.,\"%s\")]]";
    private String sectionSelectedXpath = "//h2[text()=\"Selected\"]//following-sibling::div/section";
    private String sectionNotSelectedXpath = "//h2[text()=\"Not selected\"]//following-sibling::div/section";
    private String buttonSaveXpath = "//button[text()=\"Save\"]";
    private String buttonCancelXpath = "//button[text()=\"Cancel\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getPopupTitle() {
        return field(popupTitleXpath);
    }

    public SelenideElement getFieldNotSelectedAgentByName(String name) {
        return field(String.format(fieldNotSelectedAgentByNameXpath, name));
    }

    public SelenideElement getFieldSelectedAgentByName(String name) {
        return field(String.format(fieldSelectedAgentByNameXpath,name));
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getButtonCancel() {
        return field(buttonCancelXpath);
    }

    public SelenideElement getSectionNotSelected() {
        return field(sectionNotSelectedXpath);
    }

    public SelenideElement getSectionSelected() {
        return field(sectionSelectedXpath);
    }

    //</editor-fold>

    public void addAgentToQueue(Queue queue, User ... users){
        getButtonEditAgentsQueueByText(queue.getName()).click();
        waitUntilAlertDisappear();
        for (User user:users) {
            executeDragDropScript(getFieldNotSelectedAgentByName(user.getFirstName()), getSectionSelected());
        }
        getButtonSave().click();
        waitUntilAlertDisappear();
    }

    public void addAgentToQueue(String queue, User ... users){
        getButtonEditAgentsQueueByText(queue).click();
        waitUntilAlertDisappear();
        for (User user:users) {
            executeDragDropScript(getFieldNotSelectedAgentByName(user.getFirstName()), getSectionSelected());
        }
        getButtonSave().click();
        waitUntilAlertDisappear();
    }

    public void validateAddedAgents(Queue queue, User ... users){
        getButtonEditAgentsQueueByText(queue.getName()).click();
        waitUntilAlertDisappear();
        for (User user: users) {
            getFieldSelectedAgentByName(user.getFirstName()).should(exist);
            waitUntilAlertDisappear();
        }
        getButtonCancel().click();
    }

    public void validateAddedAgents(String queue, User ... users){
        getButtonEditAgentsQueueByText(queue).click();
        waitUntilAlertDisappear();
        for (User user: users) {
            getFieldSelectedAgentByName(user.getFirstName()).should(exist);
            waitUntilAlertDisappear();
        }
        getButtonCancel().click();
    }
}
