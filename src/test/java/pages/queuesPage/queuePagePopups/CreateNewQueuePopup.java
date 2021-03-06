package pages.queuesPage.queuePagePopups;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.basePage.basePopup.BasePopup;
import pages.queuesPage.ConfigureQueueTab;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;

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
    private final String buttonUbAssignManagerReporterByNameXpath = "//new-selected-value//span[contains(.,\"%s\")]/button";
    //</editor-fold>

    //<editor-fold desc="get\set">


    public SelenideElement getButtonUnAssignManagerReporterByName(String text) {
        return field(String.format(buttonUbAssignManagerReporterByNameXpath, text));
    }

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
        waitUntilAlertDisappear();
        getRandomDropDownOption(getDropdownSubscription());
    }

    public void selectRandomWaitingMusic(){
        getRandomDropDownOption(getDropdownWaitingMusic());
    }

    @Step("Select queue manager")
    public CreateNewQueuePopup selectQueueManager(String managerName){
        getDropdownManager().selectOptionContainingText(managerName);
        return this;
    }

    @Step("Select queue reporter")
    public CreateNewQueuePopup selectQueueReporter(String managerName){
        getDropdownReporter().selectOptionContainingText(managerName);
        return this;
    }

    @Step("Save changes")
    public ConfigureQueueTab saveChanges(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return new ConfigureQueueTab();
    }

    @Step("Set queue name")
    public CreateNewQueuePopup setQueueName(String queueName){
        getInputQueueName().setValue(queueName);
        return this;
    }

    @Step("Set queue Subscription")
    public CreateNewQueuePopup setSubscription(){
        getDropdownSubscription().selectOption(1);
        return this;
    }

    @Step ("Deselect queue manager")
    public CreateNewQueuePopup unassignQueueManager(String managerName){
        getButtonUnAssignManagerReporterByName(managerName).click();
        return this;
    }

    @Step ("Deselect queue reporter")
    public CreateNewQueuePopup unassignQueueReporter (String reporterName){
        getButtonUnAssignManagerReporterByName(reporterName).click();
        return this;
    }

    @Step ("Select Login/logout short number")
    public CreateNewQueuePopup selectLoginShortNum(AbbreviatedDialling shortNum){
        getDropdownLoginLogout().selectOptionContainingText(shortNum.getSingleShortNum());
        return this;
    }

    @Step("Select random value from \"Max. waiting time for customer service\" dropdown")
    public CreateNewQueuePopup selectMaxWaitTime(String time){
        getDropdownMaxWaintingTime().selectOptionByValue(time);
        return this;
    }

    @Step("Set priority value")
    public CreateNewQueuePopup selectPriority(String priority){
        getDropdownPriority().selectOptionContainingText(priority);
        return this;
    }

    @Step("Select \"Waiting music\"")
    public CreateNewQueuePopup selectWaitingMusic(String music){
        getDropdownWaitingMusic().selectOptionContainingText(music);
        return this;
    }

    @Step("Select \"Announcement frequency\"")
    public CreateNewQueuePopup selectAnnoucFrequency(String frequency){
        getDropdownAnnounFreq().selectOptionByValue(frequency);
        return this;
    }

    @Step("Select \"Rule for finding agent\"")
    public CreateNewQueuePopup selectRuleForAgent(String rule){
        getDropdownRulesForFindAgent().selectOptionContainingText(rule);
        return this;
    }

    @Step("Select \"Timeout for calling an agent\"")
    public CreateNewQueuePopup selectTimeout(String timeout){
        getDropdownTimeOutForCall().selectOptionByValue(timeout);
        return this;
    }

    @Step("Select \"Waiting time (in sec.) before next attempt\"")
    public CreateNewQueuePopup selectRetryTime (String time){
        getDropdownRetry().selectOptionByValue(time);
        return this;
    }

    @Step("Select \"Waiting time (in sec.) for agents before next call\"")
    public CreateNewQueuePopup selectWaitTimeBeforeNextCall(String time){
        getDropdownWrapUpTime().selectOptionByValue(time);
        return this;
    }

    @Step("Select \"Record calls\"")
    public CreateNewQueuePopup selectRecordCall(String recordCall){
        getDropdownRecordCalls().selectOptionContainingText(recordCall);
        return this;
    }


    @Step("Verify Queue max wait time")
    public CreateNewQueuePopup verifyMaxWaitTime(String time) {
        getDropdownMaxWaintingTime().getSelectedText().contains(time);
        return this;
    }

    @Step("Verify Queue priority")
    public CreateNewQueuePopup verifyPriority(String priority) {
        getDropdownPriority().getSelectedText().contains(priority);
        return this;
    }

    @Step("Verify Queue rule for finding agents")
    public CreateNewQueuePopup verifyRuleForAgents(String rule) {
        getDropdownRulesForFindAgent().getSelectedText().contains(rule);
        return this;
    }

    @Step("Verify Queue Login short number")
    public CreateNewQueuePopup verifyShortNum(String shortNum) {
        getDropdownLoginLogout().getSelectedText().contains(shortNum);
        return this;
    }

}
