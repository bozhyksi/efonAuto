package pages.queuesPage.queuePagePopups;

import com.codeborne.selenide.SelenideElement;
import pages.queuesPage.ConfigureQueueTab;

public class QueueForAgentsPopup extends ConfigureQueueTab {
    //<editor-fold desc="Locations">
    private String fieldNotSelectedAgentByNameXpath = "//h2[text()=\"Not selected\"]//parent::div//section/div[text()[contains(.,\"%s\")]]";
    private String fieldSelectedAgentByNameXpath = "//h2[text()=\"Selected\"]//parent::div//section/div[text()[contains(.,\"%s\")]]";
    private String buttonSaveXpath = "//button[text()=\"Save\"]";
    private String buttonCancelXpath = "//button[text()=\"Cancel\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getFieldNotSelectedAgentByName() {
        return field(fieldNotSelectedAgentByNameXpath);
    }

    public SelenideElement getFieldSelectedAgentByName() {
        return field(fieldSelectedAgentByNameXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getButtonCancel() {
        return field(buttonCancelXpath);
    }
    //</editor-fold>
}
