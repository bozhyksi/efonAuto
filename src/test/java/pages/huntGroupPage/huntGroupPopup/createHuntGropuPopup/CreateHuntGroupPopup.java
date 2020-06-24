package pages.huntGroupPage.huntGroupPopup.createHuntGropuPopup;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import flow.PublicEnums;
import io.qameta.allure.Step;
import pages.huntGroupPage.HuntGroupPage;
import pages.huntGroupPage.huntGroupPopup.AddFullDaysPopup;
import pages.huntGroupPage.huntGroupPopup.AddFurtherTimePopup;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.queuesPageTest.queueTestData.Queue;
import tests.userPageTests.userPageTestData.User;

import static com.codeborne.selenide.Condition.*;

public class CreateHuntGroupPopup extends HuntGroupPage {

    //<editor-fold desc="Locators">

    //Edit hunt group section
    private String buttonSubmitEditHuntGroupXpath = "//h2[text()=\"Edit hunt group\"]/a";
    private String buttonEditHuntGroupXpath = "//h2[text()=\"Edit hunt group\"]//i[contains(@class,\"fa-cog\")]/..";
    private String dropdownNumberXpath = "//select[@formcontrolname=\"huntGroupNumber\"]";
    private String inputNameXpath = "//input[@formcontrolname=\"huntGroupName\"]";
    private String dropdownLanguageXpath = "//select[@formcontrolname=\"huntGroupLanguage\"]";
    private String inputDisplNameXpath = "//input[@formcontrolname=\"huntGroupDisplayName\"]";
    private String dropdownAuthUsersXpath = "//label[text()=\"Authorised users\"]/following-sibling::div/select";
    private String selectedAuthorizedUser = "//new-selected-value//span[text()[contains(.,'%s')]]";
    private final String buttonDeleteAuthorizedUserXpath = "//new-selected-value//span[text()[contains(.,\"%s\")]]/button";

    //Voicemail settings section
    private String inputPinXpath = "//input[@formcontrolname=\"pin\"]";
    private String inputEmailXpath = "//input[@formcontrolname=\"email\"]";
    private String inputSalutationXpath = "//input[@formcontrolname=\"salutation\"]";
    private String checkboxOnlySendByEmailXpath = "//input[@formcontrolname=\"onlySendByEmail\"]";
    private String buttonSubmitVoicemailXpath = "//h2[text()=\"Voicemail settings\"]/a";

    //If end devices not available (not registered)section
    private String dropdownRelevantAccountXpath = "//select[@formcontrolname=\"backUpAccount\"]";
    private String dropdownBackUpTypeXpath = "//select[@formcontrolname=\"backUpType\"]";
    private String inputBackUpNumberXpath = "//input[@formcontrolname=\"backUpNumber\"]";
    private String buttonSubmitRelevantAccountXpath = "//h2[text()=\"If end devices not available (not registered)\"]/a";

    //Timers section
    private String buttonSubmitTimerXpath = "//span[text()=\"None of the above days or times are applicable.\"]/following-sibling::a";
    private String buttonAddNewStepXpath = "//button[text()=\"+ Add new step\"]";
    private String dropdownListActionTypeXpath = "//select[@formcontrolname=\"queueActionType\"]";
    private String inputListDelayXpath = "//input[@formcontrolname=\"delay\"]";
    private String inputListNumbersXpath = "//input[@formcontrolname=\"numbers\"]";
    private String dropdownListAnnouncmentIdXpath = "//select[@formcontrolname=\"announcementId\"]";
    private String dropdownListQueueIdXpath = "//select[@formcontrolname=\"callCenterQueueName\"]";
    private String dropdownTimerGroupXpath = "//select[@id=\"timerGroupSelect\"]";
    private String buttonAddXpath = "//button[text()=\"Add\"]";
    private String checkboxCallRecordingXpath = "//label[text()=\"Calls recording\"]/following-sibling::div//input";
    private String buttonSaveXpath = "//*[@class=\"modal-footer\"]//button[text()=\"Save\"]";
    private String buttonCancelXpath = "//*[@class=\"modal-footer\"]//button[text()=\"Cancel\"]";
    private String buttonCloseXpath = "//button[@aria-label=\"Close\"]";
    private String dropdownAccountXpath = "//select[@id=\"accountSelect\"]";
    private String inputNumberXpath = "//input[@id=\"numberSelection\"]";

    private String dropdownActionTypeByLabelXpath = "//label[contains(text(),\"%s\")]//parent::div//following-sibling::div/select[@formcontrolname=\"queueActionType\"]";
    private String dropDownEndDevicesByLabelXpath = "//label[contains(text(),\"%s\")]//ancestor::hunt-group-timer-queue-action//select[@id=\"accountSelect\"]";
    private String inputNumberByLabelXpath = "//label[contains(text(),\"%s\")]//ancestor::hunt-group-timer-queue-action//input[@id=\"numberSelection\"]";
    private String inputDelayByLabelXpath = "//label[contains(text(),\"%s\")]//ancestor::hunt-group-timer-queue-action//input[@formcontrolname=\"delay\"]";
    private String dropdownAnnouncementIdByLabelXpath = "//label[contains(text(),\"%s\")]//ancestor::hunt-group-timer-queue-action//select[@formcontrolname=\"announcementId\"]";
    private String dropdownQueueIdByLabelXpath = "//label[contains(text(),\"%s\")]//ancestor::hunt-group-timer-queue-action//select[@formcontrolname=\"callCenterQueueName\"]";
    private String buttonDeleteByLabelXpath = "//label[contains(text(),\"%s\")]//ancestor::hunt-group-timer-queue-action//a[@id=\"deleteQueueAction\"]";

    private String inputFullDayNameXpath = "//input[@formcontrolname=\"timerQueueName\"]";
    private String inputFullDayDateXpath = "//input[@formcontrolname=\"dates\"]";
    private String buttonEditFullDayXpath = "//dt[text()=\"Full days\"]/parent::dl//i[contains(@class,\"fa-cog\")]//parent::a";

    private String inputTimeNameXpath = "//input[@formcontrolname=\"timerQueueName\"]";
    private String inputMondayXpath = "//label[text()=\"Monday\"]/following-sibling::div/input";
    private String inputTuesdayXpath = "//label[text()=\"Tuesday\"]/following-sibling::div/input";
    private String inputWednesdayXpath = "//label[text()=\"Wednesday\"]/following-sibling::div/input";
    private String inputThursdayXpath = "//label[text()=\"Thursday\"]/following-sibling::div/input";
    private String inputFridayXpath = "//label[text()=\"Friday\"]/following-sibling::div/input";
    private String inputSaturdayXpath = "//label[text()=\"Saturday\"]/following-sibling::div/input";
    private String inputSundayXpath = "//label[text()=\"Sunday\"]/following-sibling::div/input";
    private String buttonEditFurtherTimeXpath = "//dt[text()=\"Time\"]/parent::dl//i[contains(@class,\"fa-cog\")]//parent::a";

    //</editor-fold>

    //<editor-fold desc="get\set">


    public SelenideElement getButtonEditHuntGroup() {
        return field(buttonEditHuntGroupXpath);
    }

    public SelenideElement getButtonDeleteAuthorizedUser(String text) {
        return field(String.format(buttonDeleteAuthorizedUserXpath,text));
    }

    public SelenideElement getButtonEditFurtherTime() {
        return field(buttonEditFurtherTimeXpath);
    }

    public SelenideElement getInputSunday() {
        return field(inputSundayXpath);
    }

    public SelenideElement getInputSaturday() {
        return field(inputSaturdayXpath);
    }

    public SelenideElement getInputFriday() {
        return field(inputFridayXpath);
    }

    public SelenideElement getInputThursday() {
        return field(inputThursdayXpath);
    }

    public SelenideElement getInputWednesday() {
        return field(inputWednesdayXpath);
    }

    public SelenideElement getInputTuesday() {
        return field(inputTuesdayXpath);
    }

    public SelenideElement getInputMonday() {
        return field(inputMondayXpath);
    }

    public SelenideElement getInputTimeName() {
        return field(inputTimeNameXpath);
    }

    public SelenideElement getButtonEditFullDay() {
        return field(buttonEditFullDayXpath);
    }

    public SelenideElement getInputFullDayDate() {
        return field(inputFullDayDateXpath);
    }

    public SelenideElement getInputFullDayName() {
        return field(inputFullDayNameXpath);
    }

    public SelenideElement getButtonDeleteByLabel(String level) {
        return field(String.format(buttonDeleteByLabelXpath, level));
    }

    public SelenideElement getDropdownQueueIdByLabel(String level) {
        return field(String.format(dropdownQueueIdByLabelXpath, level));
    }

    public SelenideElement getDropdownAnnouncementIdByLabel(String level) {
        return field(String.format(dropdownAnnouncementIdByLabelXpath, level));
    }

    public SelenideElement getInputDelayByLabel(String level) {
        return field(String.format(inputDelayByLabelXpath, level));
    }

    public SelenideElement getInputNumberByLabel(String level) {
        return field(String.format(inputNumberByLabelXpath, level));
    }

    public SelenideElement getDropDownEndDevicesByLabel(String level) {
        return field(String.format(dropDownEndDevicesByLabelXpath, level));
    }

    public SelenideElement getDropdownActionTypeByLabel(String level) {
        return field(String.format(dropdownActionTypeByLabelXpath, level));
    }

    public SelenideElement getInputNumber() {
        return field(inputNumberXpath);
    }

    public SelenideElement getDropdownAccount() {
        return field(dropdownAccountXpath);
    }

    public SelenideElement getButtonSubmitEditHuntGroup() {
        return field(buttonSubmitEditHuntGroupXpath);
    }

    public SelenideElement getDropdownNumber() {
        return field(dropdownNumberXpath);
    }

    public SelenideElement getInputName() {
        return field(inputNameXpath);
    }

    public SelenideElement getDropdownLanguage() {
        return field(dropdownLanguageXpath);
    }

    public SelenideElement getInputDisplName() {
        return field(inputDisplNameXpath);
    }

    public SelenideElement getDropdownAuthUsers() {
        return field(dropdownAuthUsersXpath);
    }

    public SelenideElement getInputPin() {
        return field(inputPinXpath);
    }

    public SelenideElement getInputEmail() {
        return field(inputEmailXpath);
    }

    public SelenideElement getInputSalutation() {
        return field(inputSalutationXpath);
    }

    public SelenideElement getCheckboxOnlySendByEmail() {
        return field(checkboxOnlySendByEmailXpath);
    }

    public SelenideElement getButtonSubmitVoicemail() {
        return field(buttonSubmitVoicemailXpath);
    }

    public SelenideElement getDropdownRelevantAccount() {
        return field(dropdownRelevantAccountXpath);
    }

    public SelenideElement getDropdownBackUpType() {
        return field(dropdownBackUpTypeXpath);
    }

    public SelenideElement getInputBackUpNumber() {
        return field(inputBackUpNumberXpath);
    }

    public SelenideElement getButtonSubmitRelevantAccount() {
        return field(buttonSubmitRelevantAccountXpath);
    }

    public SelenideElement getButtonSubmitTimer() {
        return field(buttonSubmitTimerXpath);
    }

    public SelenideElement getButtonAddNewStep() {
        return field(buttonAddNewStepXpath);
    }

    public ElementsCollection getDropdownListActionType() {
        return fields(dropdownListActionTypeXpath);
    }

    public ElementsCollection getInputListDelay() {
        return fields(inputListDelayXpath);
    }

    public ElementsCollection getInputListNumbers() {
        return fields(inputListNumbersXpath);
    }

    public ElementsCollection getDropdownListAnnouncmentId() {
        return fields(dropdownListAnnouncmentIdXpath);
    }

    public ElementsCollection getDropdownListQueueId() {
        return fields(dropdownListQueueIdXpath);
    }

    public SelenideElement getDropdownTimerGroup() {
        return field(dropdownTimerGroupXpath);
    }

    public SelenideElement getButtonAdd() {
        return field(buttonAddXpath);
    }

    public SelenideElement getCheckboxCallRecording() {
        return field(checkboxCallRecordingXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getButtonCancel() {
        return field(buttonCancelXpath);
    }

    public SelenideElement getButtonClose() {
        return field(buttonCloseXpath);
    }

    public SelenideElement getSelectedAuthorizedUserByName(String name) {
        return field(String.format(selectedAuthorizedUser, name));
    }
    //</editor-fold>

    public void selectRandomNumber() {
        getDropdownNumber().selectOption(index.getAndIncrement());
    }

    @Step("Activate call recording")
    public CreateHuntGroupPopup activateCallRecordings(){
        getCheckboxCallRecording().click();
        return this;
    }

    public void changeHuntGroupLanguage(String value){
        getButtonSubmitEditHuntGroup().click();
        getDropdownLanguage().selectOptionByValue(value);
        getButtonSubmitEditHuntGroup().click();
    }

    @Step("Select hunt group number")
    public CreateHuntGroupPopup selectNumber(String number){
        getDropdownNumber().selectOptionContainingText(number);
        return this;
    }

    @Step("Set hunt group name")
    public CreateHuntGroupPopup setName(String huntGroupName){
        getInputName().setValue(huntGroupName);
        return this;
    }

    @Step("Select authorized users")
    public CreateHuntGroupPopup selectAuthorizedUser(String ...userNames){
        for (String userName : userNames) {
            getDropdownAuthUsers().selectOptionContainingText(userName);
            waitUntilAlertDisappear();
        }
        return this;
    }

    @Step("Select authorized users")
    public CreateHuntGroupPopup selectAuthorizedUser(User... users){
        if (getButtonEditHuntGroup().exists()) getButtonEditHuntGroup().click();
        for (User user : users) {
            getDropdownAuthUsers().selectOptionContainingText(user.getLastName());
            waitUntilAlertDisappear();
        }
        return this;
    }

    @Step("Unassign authorized users")
    public CreateHuntGroupPopup unassignAuthorizedUser(User... users){
        if (getButtonEditHuntGroup().exists()) getButtonEditHuntGroup().click();
        for (User user: users) {
            getButtonDeleteAuthorizedUser(user.getFirstName()).click();
            waitUntilAlertDisappear();
        }
        return this;
    }

    @Step("Set display name")
    public CreateHuntGroupPopup setDisplayName(String displayName){
        getInputDisplName().setValue(displayName);
        return this;
    }

    @Step("Select language")
    public CreateHuntGroupPopup selectLanguage(String language){
        getDropdownLanguage().selectOptionByValue(language);
        return this;
    }

    @Step("Save changes")
    public HuntGroupPage saveChanges(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return new HuntGroupPage();
    }

    @Step("Click edit button for \"Edit hunt group\" section")
    public CreateHuntGroupPopup clickEditButtonForEditHuntGroupSection(){
        getButtonSubmitEditHuntGroup().click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Validate selected authorized users")
    public CreateHuntGroupPopup validateSelectedAuthorizedUsers(User ... users){
        if (getButtonEditHuntGroup().exists()) getButtonEditHuntGroup().click();
        for (User user : users) {
            getSelectedAuthorizedUserByName(user.getFirstName()).should(exist);
            waitUntilAlertDisappear();
        }
        return this;
    }

    @Step("Click edit Voicemail section")
    public VoicemailSection clickEditVoicemail(){
        getButtonSubmitVoicemail().click();
        return new VoicemailSection();
    }

    @Step("Click edit If end devices not available")
    public EndDevicesNotAvailableSection clickEditEndDevNotAvailable(){
        field(buttonSubmitRelevantAccountXpath).click();
        waitUntilAlertDisappear();
        return new EndDevicesNotAvailableSection();
    }

    @Step("Click edit timers")
    public TimersSection clickEditTimers(){
        field(buttonSubmitTimerXpath).click();
        waitUntilAlertDisappear();
        return new TimersSection();
    }

    @Step("Click Edit full days")
    public CreateHuntGroupPopup clickEditFullDays(){
        getButtonEditFullDay().click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Click Further Timers timers")
    public CreateHuntGroupPopup clickEditFurtherTimers(){
        getButtonEditFurtherTime().click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Verify full days configurations")
    public CreateHuntGroupPopup verifyFullDaysConfig(HuntGroup huntGroup){
        getInputFullDayName().shouldHave(value(huntGroup.getFullDayName()));
        getInputFullDayDate().shouldHave(value(huntGroup.getFullDayDate()));
        return this;
    }

    @Step("Verify timers configurations")
    public CreateHuntGroupPopup verifyTimersConfig(HuntGroup huntGroup){
        getInputTimeName().shouldHave(value(huntGroup.getFurtherTimeName()));
        return this;
    }

    @Step("Select Hunt Group timer group")
    public CreateHuntGroupPopup selectTimerGroups(PublicEnums.HuntGroupTimerGroup type){
        field(dropdownTimerGroupXpath).selectOptionContainingText(type.getType());
        return this;
    }

    @Step("Click Add new Full Days group")
    public AddFullDaysPopup clickAddFullDays(){
        field(buttonAddXpath).click();
        waitUntilAlertDisappear();
        return new AddFullDaysPopup();
    }

    @Step("Click Add new Timers group")
    public AddFurtherTimePopup clickAddTimers(){
        field(buttonAddXpath).click();
        waitUntilAlertDisappear();
        return new AddFurtherTimePopup();
    }
}
