package pages.huntGroupPage.huntGroupPopup.createHuntGropuPopup;

import flow.PublicEnums;
import io.qameta.allure.Step;
import pages.huntGroupPage.huntGroupPopup.AddFullDaysPopup;
import pages.huntGroupPage.huntGroupPopup.AddFurtherTimePopup;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.queuesPageTest.queueTestData.Queue;

import static com.codeborne.selenide.Condition.text;

public class TimersSection extends CreateHuntGroupPopup{
    private int level = 1;

    //<editor-fold desc="locators">
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

    private void configureLevel(String delay, PublicEnums.HuntGroupStepActions actionValue, String number) {
        String levelNumber = String.valueOf(level);
        getButtonAddNewStep().click();
        if (getInputDelayByLabel(levelNumber).exists()) getInputDelayByLabel(levelNumber).setValue(delay);
        getDropdownActionTypeByLabel(levelNumber).selectOptionByValue(actionValue.getValue());
        getDropDownEndDevicesByLabel(levelNumber).selectOption(1);
        getInputNumberByLabel(levelNumber).setValue(number);
        level++;
    }

    private void configureLevel(String delay, PublicEnums.HuntGroupStepActions actionValue) {
        String levelNumber = String.valueOf(level);
        getButtonAddNewStep().click();
        getDropdownActionTypeByLabel(levelNumber).selectOptionByValue(actionValue.getValue());
        //if (level > 1) getInputDelayByLabel(levelNumber).setValue(delay);
        if (getInputDelayByLabel(levelNumber).exists()) getInputDelayByLabel(levelNumber).setValue(delay);
        level++;
    }

    private void configureLevel(String delay, PublicEnums.HuntGroupStepActions actionValue, FileManagementTestData file) {
        String levelNumber = String.valueOf(level);
        getButtonAddNewStep().click();
        getDropdownActionTypeByLabel(levelNumber).selectOptionByValue(actionValue.getValue());
        getDropdownAnnouncementIdByLabel(levelNumber).selectOptionContainingText(file.getFileName());
        //if (level > 1) getInputDelayByLabel(levelNumber).setValue(delay);
        if (getInputDelayByLabel(levelNumber).exists()) getInputDelayByLabel(levelNumber).setValue(delay);
        level++;
    }

    private void configureLevel(String delay, PublicEnums.HuntGroupStepActions actionValue, Queue queue) {
        String levelNumber = String.valueOf(level);
        getButtonAddNewStep().click();
        getDropdownActionTypeByLabel(levelNumber).selectOptionByValue(actionValue.getValue());
        getDropdownQueueIdByLabel(levelNumber).selectOptionContainingText(queue.getName());
        //if (level > 1) getInputDelayByLabel(levelNumber).setValue(delay);
        if (getInputDelayByLabel(levelNumber).exists()) getInputDelayByLabel(levelNumber).setValue(delay);
        level++;
    }

    @Step("Configure Timers")
    public TimersSection configureStandartTimer(HuntGroup huntGroup){
        configureLevel("10", PublicEnums.HuntGroupStepActions.VoicemailUnavailable);
        configureLevel("12", PublicEnums.HuntGroupStepActions.Announcements,huntGroup.getAnnouncement());
        configureLevel("22", PublicEnums.HuntGroupStepActions.Queue,huntGroup.getQueue());
        configureLevel("25", PublicEnums.HuntGroupStepActions.NumberEndDevice,huntGroup.getQueue().getRandomPhone("04",8));
        configureLevel("28", PublicEnums.HuntGroupStepActions.VoicemailNoAnnouncement);
        configureLevel("30", PublicEnums.HuntGroupStepActions.VoicemailBusy);
        return this;
    }

    @Step("Verify Timers")
    public TimersSection verifyStandartTimersConfiguration(){
        getDropdownActionTypeByLabel("1").getSelectedOption().shouldHave(text("Voicemail: unavailable"));
        getDropdownActionTypeByLabel("2").getSelectedOption().shouldHave(text("Announcements"));
        getDropdownActionTypeByLabel("3").getSelectedOption().shouldHave(text("Queue"));
        getDropdownActionTypeByLabel("4").getSelectedOption().shouldHave(text("Number/end device"));
        getDropdownActionTypeByLabel("5").getSelectedOption().shouldHave(text("Voicemail: no announcement"));
        getDropdownActionTypeByLabel("6").getSelectedOption().shouldHave(text("Voicemail: busy"));
        return this;
    }

    @Step("Submit configured timers")
    public CreateHuntGroupPopup submit(){
        field(buttonSubmitTimerXpath).click();
        waitUntilAlertDisappear();
        return this;
    }
}
