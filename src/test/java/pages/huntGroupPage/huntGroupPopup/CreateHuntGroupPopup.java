package pages.huntGroupPage.huntGroupPopup;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.huntGroupPage.HuntGroupPage;

public class CreateHuntGroupPopup extends HuntGroupPage {

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
    private String dropdownTimerGroupXpath = "//label[text()=\"Huntgroup timer group\"]/following-sibling::div/select";
    private String buttonAddXpath = "//button[text()=\"Add\"]";
    private String checkboxCallRecordingXpath = "//label[text()=\"Calls recording\"]/following-sibling::div//input";
    private String buttonSaveXpath ="//*[@class=\"modal-footer\"]//button[text()=\"Save\"]";
    private String buttonCancelXpath = "//*[@class=\"modal-footer\"]//button[text()=\"Cancel\"]";
    private String buttonCloseXpath = "//button[@aria-label=\"Close\"]";
    private String dropdownAccountXpath = "//select[@id=\"accountSelect\"]";
    private String inputNumberXpath = "//input[@id=\"numberSelection\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
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

    public void selectRandomNumber(){
        getDropdownNumber().selectOption(getRandomDropDownOption(dropdownNumberXpath));
    }
}
