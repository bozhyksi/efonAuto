package lowLevelUserPages.voicemailLowLevelUserpage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import testsLowLevelUser.voicemailUserPageTests.voicemailTestData.VoicemailTestData;

import static com.codeborne.selenide.Condition.*;
import static testsLowLevelUser.testData.AutotestUserData.autotestUserPhone;

public class VoicemailSettingUserPage extends VoicemailBaseUserPage {

    public class VoicemailRetrievalDelivery{
        //<editor-fold desc="locatora">
        private String inputPinCodeXpath = "//voicemail-settings//input[@formcontrolname=\"voicemailPin\"]";
        private String inputEmailForVoiceXpath = "//voicemail-settings//input[@formcontrolname=\"voicemailEmail\"]";
        private String inputSalutationXpath = "//voicemail-settings//input[@formcontrolname=\"voicemailAnrede\"]";
        private String checkboxYesXpath = "//voicemail-settings//span[text()=\"Yes\"]/preceding-sibling::input[@formcontrolname=\"voicemailDelete\"]";
        //</editor-fold>

        //<editor-fold desc="get/set">
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
        //</editor-fold>

        @Step("Enter PIN")
        public VoicemailRetrievalDelivery enterPin(String pin){
            getInputPinCode().setValue(pin);
            return this;
        }

        @Step("Enter email")
        public VoicemailRetrievalDelivery enterEmail (String email){
            getInputEmailForVoice().setValue(email);
            return this;
        }

        @Step("Enter salutation")
        public VoicemailRetrievalDelivery enterSalutation(String salut){
            getInputSalutation().setValue(salut);
            return this;
        }

        @Step("Verify Voicemail retrieval/delivery settings")
        public VoicemailRetrievalDelivery verifyVoicemailRetrievalDeliverySettings(VoicemailTestData voicemailTestData){
            getInputPinCode().shouldHave(value(voicemailTestData.getVoicemailPinCode()));
            getInputEmailForVoice().shouldHave(value(voicemailTestData.getVoicemailEmail()));
            getInputSalutation().shouldHave(value(voicemailTestData.getVoicemailSalutation()));
            return this;
        }

        @Step("Save")
        public VoicemailSettingUserPage save(){
            getButtonSave().click();
            waitUntilAlertDisappear();
            return new VoicemailSettingUserPage();
        }

    }

    public class VoicemailAnnouncementSettings{
        //<editor-fold desc="locators">
        private String dropdownTemporaryAnnouncementXpath = "//announcement-settings//select[@formcontrolname=\"temporaryAnnouncementId\"]";
        private String dropdownVoicemailUnavailableXpath = "//announcement-settings//select[@formcontrolname=\"voicemailUnavailableAnnouncementId\"]";
        private String dropdownVoicemailBusyXpath = "//announcement-settings//select[@formcontrolname=\"voicemailBusyAnnouncementId\"]";
        //</editor-fold>

        //<editor-fold desc="get\set">
        public SelenideElement getDropdownVoicemailBusy() {
            return field(dropdownVoicemailBusyXpath);
        }

        public SelenideElement getDropdownVoicemailUnavailable() {
            return field(dropdownVoicemailUnavailableXpath);
        }

        public SelenideElement getDropdownTemporaryAnnouncement() {
            return field(dropdownTemporaryAnnouncementXpath);
        }
        //</editor-fold>

        @Step("Verify AnnouncementSettings")
        public VoicemailAnnouncementSettings verifyVoicemailAnnouncementSettings(FileManagementTestData file){
            getDropdownTemporaryAnnouncement().getSelectedText().contains(file.getFileName());
            getDropdownVoicemailBusy().getSelectedText().contains(file.getFileName());
            getDropdownVoicemailUnavailable().getSelectedText().contains(file.getFileName());
            return this;
        }

        @Step("Select Temporary announcement")
        public VoicemailAnnouncementSettings selectTemporaryAnnouncement(String fileName){
            getDropdownTemporaryAnnouncement().selectOptionContainingText(fileName);
            return this;
        }

        @Step("Select Voicemail unavailable")
        public VoicemailAnnouncementSettings selectVoicemailUnavailable(String fileName){
            getDropdownVoicemailUnavailable().selectOptionContainingText(fileName);
            return this;
        }

        @Step("Select Voicemail busy")
        public VoicemailAnnouncementSettings selectVoicemailBusy(String fileName){
            getDropdownVoicemailBusy().selectOptionContainingText(fileName);
            return this;
        }

        @Step("Save")
        public VoicemailSettingUserPage save(){
            getButtonSave().click();
            waitUntilAlertDisappear();
            return new VoicemailSettingUserPage();
        }
    }

    //<editor-fold desc="locators">
    private String dropdownSelectNumberXpath = "//voicemail-settings//h3[text()=\"Select number\"]/following-sibling::select";
    private String buttonVoicemailEditXpath = "//voicemail-settings//h2[contains(text(),\"Voicemail retrieval/delivery\")]/following-sibling::div//i[contains(@class,\"fa-cog\")]";



    private String buttonAnnouncementEditXpath = "//announcement-settings//h2[contains(text(),\"Voicemail announcement settings\")]/following-sibling::div//i[contains(@class,\"fa-cog\")]";


    private String buttonSaveXpath = "//voicemail-settings//button[text()=\"Save\"]";
    private String buttonCancelXpath = "//voicemail-settings//button[text()=\"Cancel\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">


    public SelenideElement getButtonAnnouncementEdit() {
        return field(buttonAnnouncementEditXpath);
    }

    public SelenideElement getDropdownSelectNumber() {
        return field(dropdownSelectNumberXpath);
    }

    public SelenideElement getButtonVoicemailEdit() {
        return field(buttonVoicemailEditXpath);
    }



    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getButtonCancel() {
        return field(buttonCancelXpath);
    }
    //</editor-fold>

    @Step("Check if Select number dropdown contains only user number")
    public VoicemailSettingUserPage verifySelectNumberDropdownItems(){
        super.validateDropDownItems(getDropdownSelectNumber());
        return this;
    }

    @Step("Select Number")
    public VoicemailSettingUserPage selectNumber(String num){
        getDropdownSelectNumber().selectOptionContainingText(num);
        return this;
    }

    @Step("Click edit Voicemail retrieval/delivery")
    public VoicemailRetrievalDelivery clickEditVoicemailRetrievalDelivery(){
        getButtonVoicemailEdit().click();
        waitUntilAlertDisappear();
        return new VoicemailRetrievalDelivery();
    }

    @Step("Click edit Voicemail announcement settings")
    public VoicemailAnnouncementSettings clickEditVoicemailAnnouncementSettings(){
        getButtonAnnouncementEdit().click();
        waitUntilAlertDisappear();
        return new VoicemailAnnouncementSettings();
    }

}