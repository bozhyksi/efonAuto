package lowLevelUserPages.voicemailLowLevelUserpage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import testsLowLevelUser.voicemailUserPageTests.voicemailTestData.VoicemailTestData;

import static com.codeborne.selenide.Condition.*;

public class VoicemailSettingUserPage extends VoicemailBaseUserPage {
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

    public void fillInVoicemailRetrievalDelivery(VoicemailTestData voicemailTestData){
        getButtonVoicemailEdit().click();
        waitUntilAlertDisappear();
        getInputPinCode().setValue(voicemailTestData.getVoicemailPinCode());
        getInputEmailForVoice().setValue(voicemailTestData.getVoicemailEmail());
        getInputSalutation().setValue(voicemailTestData.getVoicemailSalutation());
        getCheckboxYes().click();
        getButtonSave().click();
        waitUntilAlertDisappear();
    }

    public void validateVoicemailRetrievalDeliveryData(VoicemailTestData voicemailTestData){
        getButtonVoicemailEdit().click();
        waitUntilAlertDisappear();
        getInputPinCode().shouldHave(value(voicemailTestData.getVoicemailPinCode()));
        getInputEmailForVoice().shouldHave(value(voicemailTestData.getVoicemailEmail()));
        getInputSalutation().shouldHave(value(voicemailTestData.getVoicemailSalutation()));
        getCheckboxYes().shouldBe(selected);
        getButtonCancel().click();
    }

    public void configureVoicemailAnnouncementSettings(FileManagementTestData file){
        getButtonAnnouncementEdit().click();
        waitUntilAlertDisappear();
        getDropdownTemporaryAnnouncement().selectOptionContainingText(file.getFileName());
        getDropdownVoicemailBusy().selectOptionContainingText(file.getFileName());
        getButtonSave().click();
    }

    public void validateVoicemailAnnouncementSettings(FileManagementTestData file){
        getButtonAnnouncementEdit().click();

        getDropdownTemporaryAnnouncement().getSelectedText().contains(file.getFileName());
        getDropdownTemporaryAnnouncement().selectOptionByValue("null");

        getDropdownTemporaryAnnouncement().getSelectedText().contains(file.getFileName());
        getDropdownVoicemailBusy().selectOptionByValue("null");

        getButtonSave().click();
    }

    @Step("Check if Select number dropdown contains only user number")
    public VoicemailSettingUserPage verifySelectNumberDropdownItems(){
        super.validateDropDownItems(getDropdownSelectNumber());
        return this;
    }

}