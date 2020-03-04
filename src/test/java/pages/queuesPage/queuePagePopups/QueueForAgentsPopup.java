package pages.queuesPage.queuePagePopups;

import com.codeborne.selenide.SelenideElement;
import pages.basePage.basePopup.BasePopup;

public class QueueForAgentsPopup extends BasePopup {
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

    public SelenideElement getFieldSelectedAgentByName() {
        return field(fieldSelectedAgentByNameXpath);
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
}
