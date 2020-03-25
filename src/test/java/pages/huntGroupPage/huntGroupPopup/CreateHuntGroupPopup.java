package pages.huntGroupPage.huntGroupPopup;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.huntGroupPage.HuntGroupPage;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.queuesPageTest.queueTestData.Queue;

public class CreateHuntGroupPopup extends HuntGroupPage {
    private int level = 1;

    public enum QueueActions {
        NumberEndDevice("0"),
        VoicemailUnavailable("1"),
        Announcements("2"),
        VoicemailBusy("3"),
        VoicemailNoAnnouncement("4"),
        Queue("5");

        private String value;

        QueueActions(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    //<editor-fold desc="Locators">
    //Edit hunt group section
    private String buttonSubmitEditHuntGroupXpath = "//h2[text()=\"Edit hunt group\"]/a";
    private String dropdownNumberXpath = "//select[@formcontrolname=\"huntGroupNumber\"]";
    private String inputNameXpath = "//input[@formcontrolname=\"huntGroupName\"]";
    private String dropdownLanguageXpath = "//select[@formcontrolname=\"huntGroupLanguage\"]";
    private String inputDisplNameXpath = "//input[@formcontrolname=\"huntGroupDisplayName\"]";
    private String dropdownAuthUsersXpath = "//label[text()=\"Authorised users\"]/following-sibling::div/select";
    private String selectedAuthorizedUser = "//new-selected-value//span[text()[contains(.,'%s')]]";

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
        //getDropdownNumber().selectOption(getRandomDropDownOption(dropdownNumberXpath));
        getDropdownNumber().selectOption(index.getAndIncrement());
    }

    public void configureLevel(String delay, QueueActions actionValue, String number) {
        String levelNumber = String.valueOf(level);
        getButtonAddNewStep().click();
        if (level > 1) getInputDelayByLabel(levelNumber).setValue(delay);
        getDropdownActionTypeByLabel(levelNumber).selectOptionByValue(actionValue.getValue());
        getDropDownEndDevicesByLabel(levelNumber).selectOption(1);
        getInputNumberByLabel(levelNumber).setValue(number);
        level++;
    }

    public void configureLevel(String delay, QueueActions actionValue) {
        String levelNumber = String.valueOf(level);
        getButtonAddNewStep().click();
        getDropdownActionTypeByLabel(levelNumber).selectOptionByValue(actionValue.getValue());
        if (level > 1) getInputDelayByLabel(levelNumber).setValue(delay);
        level++;
    }

    public void configureLevel(String delay, QueueActions actionValue, FileManagementTestData file) {
        String levelNumber = String.valueOf(level);
        getButtonAddNewStep().click();
        getDropdownActionTypeByLabel(levelNumber).selectOptionByValue(actionValue.getValue());
        getDropdownAnnouncementIdByLabel(levelNumber).selectOptionContainingText(file.getFileName());
        if (level > 1) getInputDelayByLabel(levelNumber).setValue(delay);
        level++;
    }

    public void configureLevel(String delay, QueueActions actionValue, Queue queue) {
        String levelNumber = String.valueOf(level);
        getButtonAddNewStep().click();
        getDropdownActionTypeByLabel(levelNumber).selectOptionByValue(actionValue.getValue());
        getDropdownQueueIdByLabel(levelNumber).selectOptionContainingText(queue.getName());
        if (level > 1) getInputDelayByLabel(levelNumber).setValue(delay);
        level++;
    }

    public void checkIfFurtherTimersSaved(HuntGroup huntGroup){
        getInputTimeName().shouldHave(Condition.value(huntGroup.getFurtherTimeName()));
        getInputMonday().shouldHave(Condition.value(huntGroup.getFurtherTimeMonday()));
        getInputTuesday().shouldHave(Condition.value(huntGroup.getFurtherTimeTuesday()));
        getInputWednesday().shouldHave(Condition.value(huntGroup.getFurtherTimeWednesday()));
        getInputThursday().shouldHave(Condition.value(huntGroup.getFurtherTimeThursday()));
        getInputFriday().shouldHave(Condition.value(huntGroup.getFurtherTimeWednesday()));
        getInputSaturday().shouldHave(Condition.value(huntGroup.getFurtherTimeSaturday()));
        getInputSaturday().shouldHave(Condition.value(huntGroup.getFurtherTimeSunday()));
    }

}
