package pages.userPage.userPagePopup.configureUser;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import tests.userPageTests.userPageTestData.User;

import static io.qameta.allure.Allure.step;

public class VoicemailTabConfigUserPopup extends ConfigureUserBasePopup {
    //<editor-fold desc="locators">
    private String dropdownSelectNumberXpath = "//voicemail-settings//h3[text()=\"Select number\"]/following-sibling::select";
    private String buttonVoicemailEditXpath = "//voicemail-settings//h2[contains(text(),\"Voicemail retrieval/delivery\")]/following-sibling::div//i[contains(@class,\"fa-cog\")]";
    private String inputPinCodeXpath = "//voicemail-settings//input[@formcontrolname=\"voicemailPin\"]";
    private String inputEmailForVoiceXpath = "//voicemail-settings//input[@formcontrolname=\"voicemailEmail\"]";
    private String inputSalutationXpath = "//voicemail-settings//input[@formcontrolname=\"voicemailAnrede\"]";
    private String checkboxYesXpath = "//voicemail-settings//span[text()=\"Yes\"]/preceding-sibling::input[@formcontrolname=\"voicemailDelete\"]";


    private String buttonAnnouncementEditXpath = "//announcement-settings//h2[contains(text(),\"Voicemail announcement settings\")]/following-sibling::div//i[contains(@class,\"fa-cog\")]";
    private String dropdownTemporaryAnnouncementXpath = "//announcement-settings//select[@formcontrolname=\"temporaryAnnouncementId\"]";
    private String dropdownVoicemailUnavailableXpath = "//announcement-settings//select[@formcontrolname=\"voicemailUnavailableAnnouncementId\"]";
    private String dropdownVoicemailBusyXpath = "//announcement-settings//select[@formcontrolname=\"voicemailBusyAnnouncementId\"]";

    private String buttonSaveXpath = "//voicemail-settings//button[text()=\"Save\"]";
    private String buttonCancelXpath = "//voicemail-settings//button[text()=\"Cancel\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getDropdownVoicemailBusy() {
        return field(dropdownVoicemailBusyXpath);
    }

    public SelenideElement dropdownVoicemailUnavailable() {
        return field(dropdownVoicemailUnavailableXpath);
    }

    public SelenideElement getDropdownTemporaryAnnouncement() {
        return field(dropdownTemporaryAnnouncementXpath);
    }

    public SelenideElement getButtonAnnouncementEdit() {
        return field(buttonAnnouncementEditXpath);
    }

    public SelenideElement getDropdownSelectNumber() {
        return field(dropdownSelectNumberXpath);
    }

    public SelenideElement getButtonVoicemailEdit() {
        return field(buttonVoicemailEditXpath);
    }

    public SelenideElement getInputPinCode() {
        return field(inputPinCodeXpath);
    }

    public SelenideElement getInputEmailForVoice() {
        return field(inputEmailForVoiceXpath);
    }

    public SelenideElement getInputSalutation() {
        return field(inputSalutationXpath);
    }

    public SelenideElement getCheckboxYes() {
        return field(checkboxYesXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getButtonCancel() {
        return field(buttonCancelXpath);
    }
    //</editor-fold>

    @Step("Select number")
    public VoicemailTabConfigUserPopup selectNumber(String num){
        getDropdownSelectNumber().selectOptionContainingText(num);
        return this;
    }

    @Step("Click edit button in \"Voicemail retrieval/delivery\" section")
    public VoicemailTabConfigUserPopup clickEdit(){
        getButtonVoicemailEdit().click();
        return this;
    }

    @Step("Fill in PIN code")
    public VoicemailTabConfigUserPopup enterPIN(String pin){
        getInputPinCode().setValue(pin);
        return this;
    }

    @Step("Fill in E-mail for voice message delivery")
    public VoicemailTabConfigUserPopup enterEmail(String email){
        getInputEmailForVoice().setValue(email);
        return this;
    }

    @Step("Fill in Salutation")
    public VoicemailTabConfigUserPopup enterSalutation(String salut){
        getInputSalutation().setValue(salut);
        return this;
    }

    @Step("Save")
    public VoicemailTabConfigUserPopup save(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Verify voice mail configs")
    public VoicemailTabConfigUserPopup verifyVoicemailConfigs(User user){
        getDropdownSelectNumber().selectOptionContainingText(user.getPhoneNumber());
        getButtonVoicemailEdit().click();
        getInputEmailForVoice().shouldHave(Condition.value(user.getVoicemailEmail()));
        getInputSalutation().shouldHave(Condition.value(user.getVoicemailSalutation()));
        getInputPinCode().shouldHave(Condition.value(user.getVoicemailPinCode()));
        return this;
    }

}
