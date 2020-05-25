package pages.IVRpage.IVRpagePopup;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.IVRpage.IVRpage;

public class CreateEditIvrPopup extends IVRpage {

    //<editor-fold desc="Locators">
    private String inputNameXpath = "//input[@formcontrolname=\"logicalName\"]";
    private String inputDisplayNameXpath = "//input[@formcontrolname=\"displayName\"]";
    private String dropdownLanguageXpath = "//select[@formcontrolname=\"language\"]";
    private String dropdownSelectIvrNumberXpath = "//select[@formcontrolname=\"number\"]";
    private String dropdownSelectAnnouncXpath = "//select[@formcontrolname=\"announcement\"]";
    private String buttonSaveXpath = "//div[@class=\"modal-content\"]//button[text()=\"Save\"]";
    private String buttonCancelXpath = "//div[@class=\"modal-content\"]//button[text()=\"Cancel\"]";
    private String buttonCloseXpath = "//button[@class=\"close\"]";

    private final String dropdownIvrActionByEventXpath = "//td[text()=\"%s\"]/..//select[@formcontrolname=\"ivrActionType\"]";
    private final String dropdownParameterByEventXpath = "//td[text()=\"%s\"]/..//*[@formcontrolname=\"id\"]";
    private final String inputParameterByEventXpath = "//td[text()=\"%s\"]/..//*[@formcontrolname=\"id\"]";
    private final String checkboxActiveByEventXpath = "//td[text()=\"%s\"]/..//*[@formcontrolname=\"active\"]";
    private final String inputMaxThroughputsXpath = "//input[@formcontrolname=\"maxThroughputs\"]";
    private final String inputMaxWaitTimeXpath = "//input[@formcontrolname=\"maxWaitTime\"]";

    private final String inputPinXpath= "//input[@formcontrolname=\"pin\"]";
    private final String voiceMailEmail = "//input[@formcontrolname=\"voiceMailEmail\"]";
    private final String voiceMailSalutation = "//input[@formcontrolname=\"voiceMailTitle\"]";
    private final String deleteVoiceMail = "//input[@formcontrolname=\"deleteVoiceMail\"]";
    private final String activateCallRecording = "//input[@formcontrolname=\"activateCallRecording\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">


    public SelenideElement getActivateCallRecording() {
        return field(activateCallRecording);
    }

    public SelenideElement getDeleteVoiceMail() {
        return field(deleteVoiceMail);
    }

    public SelenideElement getVoiceMailSalutation() {
        return field(voiceMailSalutation);
    }

    public SelenideElement getVoiceMailEmail() {
        return field(voiceMailEmail);
    }

    public SelenideElement getInputPin() {
        return field(inputPinXpath);
    }

    public SelenideElement getInputMaxWaitTime() {
        return field(inputMaxWaitTimeXpath);
    }

    public SelenideElement getInputMaxThroughputs() {
        return field(inputMaxThroughputsXpath);
    }

    public SelenideElement getCheckboxActiveByEvent(String text) {
        return field(String.format(checkboxActiveByEventXpath,text));
    }

    public SelenideElement getInputParameterByEvent(String text) {
        return field(String.format(inputParameterByEventXpath,text));
    }

    public SelenideElement getDropdownParameterByEvent(String text) {
        return field(String.format(dropdownParameterByEventXpath,text));
    }

    public SelenideElement getDropdownIvrActionByEvent(String text) {
        return field(String.format(dropdownIvrActionByEventXpath,text));
    }

    public SelenideElement getButtonClose() {
        return field(buttonCloseXpath);
    }

    public SelenideElement getInputName() {
        return field(inputNameXpath);
    }

    public SelenideElement getInputDisplayName() {
        return field(inputDisplayNameXpath);
    }

    public SelenideElement getDropdownLanguage() {
        return field(dropdownLanguageXpath);
    }

    public SelenideElement getDropdownSelectIvrNumber() {
        return field(dropdownSelectIvrNumberXpath);
    }

    public SelenideElement getDropdownSelectAnnounc() {
        return field(dropdownSelectAnnouncXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getButtonCancel() {
        return field(buttonCancelXpath);
    }
    //</editor-fold>

    public void selectIvrNumber(){
        getDropdownSelectIvrNumber().selectOption(1);
    }

    @Step("Enter IVR name")
    public CreateEditIvrPopup enterIvrName(String name){
        getInputName().setValue(name);
        return this;
    }

    @Step("Enter IVR display name")
    public CreateEditIvrPopup enterDisplayName(String dispName){
        getInputDisplayName().setValue(dispName);
        return this;
    }

    @Step("Select IVR language")
    public CreateEditIvrPopup selectLanguage(String languageVal){
        getDropdownLanguage().selectOptionByValue(languageVal);
        return this;
    }

    @Step("Select IVR number")
    public CreateEditIvrPopup selectNumber(String num){
        getDropdownSelectIvrNumber().selectOptionContainingText(num);
        return this;
    }

    @Step("Select IVR announcement")
    public CreateEditIvrPopup selectAnnouncement(String announcement){
        getDropdownSelectAnnounc().selectOptionContainingText(announcement);
        return this;
    }

    @Step("Select Action for proper event")
    public CreateEditIvrPopup selectAction(String event, String action){
        getDropdownIvrActionByEvent(event).selectOptionByValue(action);
        return this;
    }

    @Step("Select Parameter from dropdown for proper event")
    public CreateEditIvrPopup selectParameter(String event, String parameter){
        getDropdownParameterByEvent(event).selectOptionContainingText(parameter);
        return this;
    }

    @Step("Enter Parameter into field for proper event")
    public CreateEditIvrPopup enterParameter(String event, String parameter){
        getInputParameterByEvent(event).setValue(parameter);
        return this;
    }

    @Step("Activate/deactivate event")
    public CreateEditIvrPopup activateEvent(String event){
        getCheckboxActiveByEvent(event).click();
        return this;
    }

    @Step("Enter Max. throughputs of menu")
    public CreateEditIvrPopup enterMaxThroughputs(String num){
        getInputMaxThroughputs().setValue(num);
        return this;
    }

    @Step("Enter Max. waiting time on entry (seconds)")
    public CreateEditIvrPopup enterMaxWaitTime(String num){
        getInputMaxWaitTime().setValue(num);
        return this;
    }

    @Step("Enter PIN")
    public CreateEditIvrPopup enterPIN(String pin){
        getInputPin().setValue(pin);
        return this;
    }

    @Step("Enter Voice Mail Email")
    public CreateEditIvrPopup enterVoiceMailEmail(String email){
        getVoiceMailEmail().setValue(email);
        return this;
    }

    @Step("Enter salutation")
    public CreateEditIvrPopup enterSalutation(String salutation){
        getVoiceMailSalutation().setValue(salutation);
        return this;
    }

    @Step("Activate Delete Voice Mail option")
    public CreateEditIvrPopup activateDeleteVoiceMailOption(){
        getDeleteVoiceMail().click();
        return this;
    }

    @Step("Activate call recordings")
    public CreateEditIvrPopup activateCallRecordings(){
        getActivateCallRecording().click();
        return this;
    }

    @Step("Save changes")
    public IVRpage saveChanges(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return new IVRpage();
    }

    //@Step("Configure action")

}
