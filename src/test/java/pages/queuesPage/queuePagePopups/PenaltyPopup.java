package pages.queuesPage.queuePagePopups;

import com.codeborne.selenide.SelenideElement;
import pages.basePage.basePopup.BasePopup;

public class PenaltyPopup extends BasePopup {
    private String fieldQueueNameXpath = "//label[text()=\"Queue:\"]/following-sibling::div";
    private String fieldAgentNameXpath = "//label[text()=\"Agent:\"]/following-sibling::div";
    private String inputPenaltyXpath = "//form//input[@formcontrolname=\"penaltyValue\"]";

    @Override
    public SelenideElement getButtonSave() {
        return super.getButtonSave();
    }

    @Override
    public SelenideElement getButtonCancel() {
        return super.getButtonCancel();
    }

    public SelenideElement getFieldQueueName() {
        return field(fieldQueueNameXpath);
    }

    public SelenideElement getFieldAgentName() {
        return field(fieldAgentNameXpath);
    }

    public SelenideElement getInputPenalty() {
        return field(inputPenaltyXpath);
    }
}
