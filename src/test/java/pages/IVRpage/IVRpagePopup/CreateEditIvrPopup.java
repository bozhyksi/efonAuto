package pages.IVRpage.IVRpagePopup;

import com.codeborne.selenide.SelenideElement;
import flow.PublicEnums;
import io.qameta.allure.Step;
import pages.IVRpage.IVRpage;
import tests.IVRpageTests.IVRtestData.IVRtestData;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.queuesPageTest.queueTestData.Queue;
import tests.userPageTests.userPageTestData.User;

import static flow.PublicEnums.IvrActions.*;
import static flow.PublicEnums.IvrEvents.*;

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

    @Step("Select Action for proper event")
    public CreateEditIvrPopup selectAction(PublicEnums.IvrEvents event, PublicEnums.IvrActions action){
        getDropdownIvrActionByEvent(event.getVal()).selectOptionByValue(action.toString());
        return this;
    }

    @Step("Select Parameter from dropdown for proper event")
    public CreateEditIvrPopup selectParameter(PublicEnums.IvrEvents event, String parameter){
        getDropdownParameterByEvent(event.getVal()).selectOptionContainingText(parameter);
        return this;
    }

    @Step("Enter Parameter into field for proper event")
    public CreateEditIvrPopup enterParameter(PublicEnums.IvrEvents event, String parameter){
        getInputParameterByEvent(event.getVal()).setValue(parameter);
        return this;
    }

    @Step("Activate/deactivate event")
    public CreateEditIvrPopup activateEvent(PublicEnums.IvrEvents event){
        getCheckboxActiveByEvent(event.getVal()).click();
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

    @Step("Configure action")
    public CreateEditIvrPopup configureAllAction(){
        return this;
    }

    @Step("Configure RINGRUF action")
    public CreateEditIvrPopup configureHuntGroupAction(HuntGroup huntGroup){
        activateEvent(_1);
        selectAction(_1, RINGRUF);
        selectParameter(_1, huntGroup.getHuntGroupName());
        return this;
    }

    @Step("Configure PHONE_DIRECT action")
    public CreateEditIvrPopup configurePhoneDirectAction(User user){
        activateEvent(_2);
        selectAction(_2, PHONE_DIRECT);
        selectParameter(_2, user.getFirstName());
        return this;
    }

    @Step("Configure ABBREVIATED_NUMBER(PHONE_INTERNAL) action")
    public CreateEditIvrPopup configureAbbreviatedNumberAction(AbbreviatedDialling abbrevNumber){
        activateEvent(_3);
        selectAction(_3, PHONE_INTERNAL);
        selectParameter(_3, abbrevNumber.getSingleShortNum());
        return this;
    }

    @Step("Configure PHONE_EXTERNAL action")
    public CreateEditIvrPopup configureExternalDirectAction(String externalNumber){
        activateEvent(_4);
        selectAction(_4, PHONE_EXTERNAL);
        enterParameter(_4, externalNumber);
        return this;
    }

    @Step("Configure IVR action")
    public CreateEditIvrPopup configureIvrNumberAction(IVRtestData ivr){
        activateEvent(_5);
        selectAction(_5, IVR);
        selectParameter(_5, ivr.getIvrName());
        return this;
    }

    @Step("Configure VOICEMAIL_UNAVAILABLE action")
    public CreateEditIvrPopup configureVoiceMailUnavailableAction(){
        activateEvent(_6);
        selectAction(_6, VM_UNAVAILABLE);
        return this;
    }

    @Step("Configure VM_NO_ANNOUNCE action")
    public CreateEditIvrPopup configureVoiceMailNoAnnouncementAction(){
        activateEvent(_7);
        selectAction(_7, VM_NO_ANNOUNCE);
        return this;
    }

    @Step("Configure VM_PERSONAL(Voicemail of subscriber) action")
    public CreateEditIvrPopup configureVoiceMailOfSubscriberAction(User user){
        activateEvent(_8);
        selectAction(_8, VM_PERSONAL);
        selectParameter(_8, user.getFirstName());
        return this;
    }

    @Step("Configure PLAY_HANGUP action")
    public CreateEditIvrPopup configurePlayAndHangUpAction(FileManagementTestData announce){
        activateEvent(_9);
        selectAction(_9, PLAY_HANGUP);
        selectParameter(_9, announce.getFileName());
        return this;
    }

    @Step("Configure PLAY_RESTART action")
    public CreateEditIvrPopup configurePlayAndRestartAction(FileManagementTestData announce){
        activateEvent(_0);
        selectAction(_0, PLAY_RESTART);
        selectParameter(_0, announce.getFileName());
        return this;
    }

    @Step("Configure _star action")
    public CreateEditIvrPopup configureQueueAction(Queue queue){
        activateEvent(_star);
        selectAction(_star, CALL_CENTER_QUEUE);
        selectParameter(_star, queue.getName());
        return this;
    }

    @Step("Configure _dies action")
    public CreateEditIvrPopup configureRestartMenuAction(){
        activateEvent(_dies);
        selectAction(_dies, RESTART);
        return this;
    }

    @Step("Configure INVALID_CHOICE action")
    public CreateEditIvrPopup configureInvalidChoiceAction(){
        selectAction(INVALID_CHOICE, HANGUP);
        return this;
    }

    @Step("Configure TIME_EXPIRED action")
    public CreateEditIvrPopup configureTimeExpiredAction(){
        selectAction(TIME_EXPIRED, RESTART);
        return this;
    }

    @Step("Configure MAX_PASSES_REACHED action")
    public CreateEditIvrPopup configureMaxPassesReachedAction(){
        selectAction(MAX_PASSES_REACHED, HANGUP);
        return this;
    }



}
