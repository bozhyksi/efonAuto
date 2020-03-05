package pages.queuesPage.queuePagePopups;

import com.codeborne.selenide.SelenideElement;
import pages.basePage.basePopup.BasePopup;

public class CreateNewQueuePopup extends BasePopup {
    //<editor-fold desc="Locators">
    private String fieldNameXpath = "//label[text()=\"Name:\"]/following-sibling::div";
    private String inputQueueNameXpath = "//input[@formcontrolname=\"queueName\"]";
    private String dropdownMaxWaintingTimeXpath = "//select[@formcontrolname=\"maxHoldTime\"]";
    private String dropdownSubscriptionXpath = "//select[@formcontrolname=\"subscriptionId\"]";
    private String dropdownManagerXpath = "//label[text()=\"Manager:\"]/following-sibling::div/select";
    private String dropdownReporterXpath = "//label[text()=\"Reporter:\"]/following-sibling::div/select";
    private String dropdownLoginLogoutXpath = "//select[@formcontrolname=\"loginLogout\"]";
    private String dropdownPriorityXpath = "//select[@formcontrolname=\"priority\"]";
    private String dropdownWaitingMusicXpath = "//select[@formcontrolname=\"mohId\"]";
    private String dropdownFileNameAnnouncXpath = "//select[@formcontrolname=\"announcementFilename\"]";
    private String dropdownAnnounFreqXpath = "//select[@formcontrolname=\"announcementFrequency\"]";
    private String dropdownRulesForFindAgentXpath = "//select[@formcontrolname=\"strategy\"]";
    private String dropdownTimeOutForCallXpath = "//select[@formcontrolname=\"timeout\"]";
    private String dropdownRetryXpath = "//select[@formcontrolname=\"retry\"]";
    private String dropdownWrapUpTimeXpath ="//select[@formcontrolname=\"wrapUpTime\"]";
    private String dropdownRecordCallsXpath = "//select[@formcontrolname=\"recordCalls\"]";
    private String buttonSaveXpath = "//button[text()=\"Save\"]";
    private String buttonCancelXpath = "//button[text()=\"Cancel\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getFieldName() {
        return field(fieldNameXpath);
    }

    public SelenideElement getInputQueueName() {
        return field(inputQueueNameXpath);
    }

    public SelenideElement getDropdownMaxWaintingTime() {
        return field(dropdownMaxWaintingTimeXpath);
    }

    public SelenideElement getDropdownSubscription() {
        return field(dropdownSubscriptionXpath);
    }

    public SelenideElement getDropdownManager() {
        return field(dropdownManagerXpath);
    }

    public SelenideElement getDropdownReporter() {
        return field(dropdownReporterXpath);
    }

    public SelenideElement getDropdownLoginLogout() {
        return field(dropdownLoginLogoutXpath);
    }

    public SelenideElement getDropdownPriority() {
        return field(dropdownPriorityXpath);
    }

    public SelenideElement getDropdownWaitingMusic() {
        return field(dropdownWaitingMusicXpath);
    }

    public SelenideElement getDropdownFileNameAnnounc() {
        return field(dropdownFileNameAnnouncXpath);
    }

    public SelenideElement getDropdownAnnounFreq() {
        return field(dropdownAnnounFreqXpath);
    }

    public SelenideElement getDropdownRulesForFindAgent() {
        return field(dropdownRulesForFindAgentXpath);
    }

    public SelenideElement getDropdownTimeOutForCall() {
        return field(dropdownTimeOutForCallXpath);
    }

    public SelenideElement getDropdownRetry() {
        return field(dropdownRetryXpath);
    }

    public SelenideElement getDropdownWrapUpTime() {
        return field(dropdownWrapUpTimeXpath);
    }

    public SelenideElement getDropdownRecordCalls() {
        return field(dropdownRecordCallsXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getButtonCancel() {
        return field(buttonCancelXpath);
    }
    //</editor-fold>

    public void selectRandomSubscriptionForQueue(){
        getRandomDropDownOption(getDropdownSubscription());
    }

    public void selectRandomWaitingMusic(){
        getRandomDropDownOption(getDropdownWaitingMusic());
    }

}
