package pages.queuesPage.queuePagePopups;

import com.codeborne.selenide.SelenideElement;
import pages.basePage.basePopup.BasePopup;
import tests.userPageTests.userPageTestData.User;

import static com.codeborne.selenide.Condition.text;

public class PenaltyPopup extends BasePopup {
    //<editor-fold desc="locators">
    private String fieldQueueNameXpath = "//label[text()=\"Queue:\"]/following-sibling::div";
    private String fieldAgentNameXpath = "//label[text()=\"Agent:\"]/following-sibling::div";
    private String inputPenaltyXpath = "//form//input[@formcontrolname=\"penaltyValue\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
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
    //</editor-fold>


    public void changePenaltyForAgent(User user){
        getFieldAgentName().shouldHave(text(user.getFirstName()));
        getInputPenalty().setValue(user.getPenalty());
        getButtonSave().click();
        waitUntilAlertDisappear();
    }
}
