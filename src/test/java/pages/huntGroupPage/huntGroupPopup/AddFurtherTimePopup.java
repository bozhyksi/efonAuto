package pages.huntGroupPage.huntGroupPopup;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.huntGroupPage.huntGroupPopup.createHuntGropuPopup.CreateHuntGroupPopup;
import pages.huntGroupPage.huntGroupPopup.createHuntGropuPopup.TimersSection;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;

public class AddFurtherTimePopup extends CreateHuntGroupPopup {
    //<editor-fold desc="locators">
    private String inputTimeNameXpath = "//new-timer-queue//input[@formcontrolname=\"timerQueueName\"]";
    private String inputMondayXpath = "//new-timer-queue//label[text()=\"Monday\"]/following-sibling::div/input";
    private String inputTuesdayXpath = "//new-timer-queue//label[text()=\"Tuesday\"]/following-sibling::div/input";
    private String inputWednesdayXpath = "//new-timer-queue//label[text()=\"Wednesday\"]/following-sibling::div/input";
    private String inputThursdayXpath = "//new-timer-queue//label[text()=\"Thursday\"]/following-sibling::div/input";
    private String inputFridayXpath = "//new-timer-queue//label[text()=\"Friday\"]/following-sibling::div/input";
    private String inputSaturdayXpath = "//new-timer-queue//label[text()=\"Saturday\"]/following-sibling::div/input";
    private String inputSundayXpath = "//new-timer-queue//label[text()=\"Sunday\"]/following-sibling::div/input";
    private String buttonAddNewStepXpath = "//new-timer-queue//button[text()=\"+ Add new step\"]";
    private String buttonSaveXpath = "//*[@id=\"systemModal\"]//button[text()=\"Save\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getInputTimeName() {
        return field(inputTimeNameXpath);
    }

    public SelenideElement getInputMonday() {
        return field(inputMondayXpath);
    }

    public SelenideElement getInputTuesday() {
        return field(inputTuesdayXpath);
    }

    public SelenideElement getInputWednesday() {
        return field(inputWednesdayXpath);
    }

    public SelenideElement getInputThursday() {
        return field(inputThursdayXpath);
    }

    public SelenideElement getInputFriday() {
        return field(inputFridayXpath);
    }

    public SelenideElement getInputSaturday() {
        return field(inputSaturdayXpath);
    }

    public SelenideElement getInputSunday() {
        return field(inputSundayXpath);
    }

    public SelenideElement getButtonAddNewStep() {
        return field(buttonAddNewStepXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }
    //</editor-fold>

    @Step("Enter time Name")
    public AddFurtherTimePopup enterTimeName(String name){
        getInputTimeName().setValue(name);
        return this;
    }

    @Step("Enter timers")
    public AddFurtherTimePopup enterTimers(HuntGroup huntGroup){
        getInputTimeName().setValue(huntGroup.getFurtherTimeName());
        getInputMonday().setValue(huntGroup.getFurtherTimeMonday());
        getInputTuesday().setValue(huntGroup.getFurtherTimeTuesday());
        getInputWednesday().setValue(huntGroup.getFurtherTimeWednesday());
        getInputThursday().setValue(huntGroup.getFurtherTimeThursday());
        getInputFriday().setValue(huntGroup.getFurtherTimeFriday());
        getInputSaturday().setValue(huntGroup.getFurtherTimeSaturday());
        getInputSunday().setValue(huntGroup.getFurtherTimeSunday());
        return this;
    }

    @Step("Configure steps")
    public AddFurtherTimePopup configureSteps(HuntGroup huntGroup){
        new TimersSection().configureStandartTimer(huntGroup);
        return this;
    }

    @Step("Save")
    public CreateHuntGroupPopup saveTimers(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return new CreateHuntGroupPopup();
    }
}
