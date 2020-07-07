package pages.userPage.userPagePopup.configureUser;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.basePage.basePopup.ConfirmationPopup;
import pages.fileManagementPage.FileManagementBasePage;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static core.configuration.preparations.eFonApp.confirmationPopup;

public class AnnouncementsTabConfigUserPopup extends ConfigureUserBasePopup {

    private class RecordVoicemailAnnouncementByPhone{

        //<editor-fold desc="locators">
        private final String inputNameXpath = "//input[@formcontrolname=\"displayName\"]";
        private final String checkboxVoicemailXpath = "//input[@formcontrolname=\"asVoicemail\"]";
        private final String checkboxRingbackAnnouncementXpath = "//input[@formcontrolname=\"asRingback\"]";
        private final String buttonLookAtMadeByCallAnnouncementXpath = "//button[text()=\"Look at made-by-call announcement\"]";
        private final String buttonSaveXpath = "//button[text()=\"Save\"]";
        //</editor-fold>

        //<editor-fold desc="get\set">
        public SelenideElement getInputName() {
            return field(inputNameXpath);
        }

        public SelenideElement getCheckboxVoicemail() {
            return field(checkboxVoicemailXpath);
        }

        public SelenideElement getCheckboxRingbackAnnouncement() {
            return field(checkboxRingbackAnnouncementXpath);
        }

        public SelenideElement getButtonLookAtMadeByCallAnnouncement() {
            return field(buttonLookAtMadeByCallAnnouncementXpath);
        }

        public SelenideElement getButtonSave() {
            return field(buttonSaveXpath);
        }
        //</editor-fold>
    }
    private class ConfigureAnnouncementPopup {
        private String inputNameXpath = "//announcement-edit-name//input[@formcontrolname=\"displayName\"]";
        private String inputVoicemailXpath = "//announcement-edit-name//input[@formcontrolname=\"asVoicemail\"]";
        private String inputRingbackXpath = "//announcement-edit-name//input[@formcontrolname=\"asRingback\"]";
        private String buttonSaveXpath = "//button[text()=\"Save\"]";
        private String buttonCancelXpath = "//button[text()=\"Cancel\"]";


        SelenideElement getButtonCancel() {
            return field(buttonCancelXpath);
        }

        SelenideElement getButtonSave() {
            return field(buttonSaveXpath);
        }

        SelenideElement getInputName() {
            return field(inputNameXpath);
        }

        SelenideElement getInputVoicemail() {
            return field(inputVoicemailXpath);
        }

        SelenideElement getInputRingback() {
            return field(inputRingbackXpath);
        }
    }
    private ConfigureAnnouncementPopup configureAnnouncementPopup = new ConfigureAnnouncementPopup();
    private RecordVoicemailAnnouncementByPhone recordVoicemailAnnouncementByPhone = new RecordVoicemailAnnouncementByPhone();

    //<editor-fold desc="Locators">
    private String buttonDeleteUserAnnouncementsByNameXpath = "//voicemail-announcements//div[contains(text(),\"%s\")]/ancestor::tr//i[contains(@class,\"fa-trash\")]/parent::a";
    private String buttonEditUserAnnouncementsByNameXpath = "//voicemail-announcements//div[contains(text(),\"%s\")]/ancestor::tr//i[contains(@class,\"fa-cog\")]/parent::a";
    private String buttonUploadFileXpath = "//voicemail-announcements//a[@title=\"Upload WAV files\"]";
    private String inputNameXpath = "//voicemail-announcements//input[@formcontrolname=\"displayName\"]";
    private String buttonSaveXpath = "//voicemail-announcements//button[text()=\"Save\"]";
    private String buttonCancelXpath = "//voicemail-announcements//button[text()=\"Cancel\"]";
    private String fieldAnnouncementNameXpath = "//voicemail-announcements//td[1]/div[contains(text(),\"%s\")]";
    private String inputFileUploadXpath = "//voicemail-announcements//input[@id=\"fileUploadInput\"]";

    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getInputFileUpload() {
        return field(inputFileUploadXpath);
    }

    public SelenideElement getFieldAnnouncementName(String name) {
        return field(String.format(fieldAnnouncementNameXpath,name));
    }

    public SelenideElement getButtonCancel() {
        return field(buttonCancelXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getInputName() {
        return field(inputNameXpath);
    }

    public SelenideElement getButtonUploadFile() {
        return field(buttonUploadFileXpath);
    }

    public SelenideElement getButtonEditUserAnnouncementsByName(String name) {
        return field(String.format(buttonEditUserAnnouncementsByNameXpath,name));
    }

    public SelenideElement getButtonDeleteUserAnnouncementByName(String name) {
        return field(String.format(buttonDeleteUserAnnouncementsByNameXpath, name));
    }
    //</editor-fold>

    @Step("Upload announcement")
    public AnnouncementsTabConfigUserPopup uploadAnnouncementFile (FileManagementTestData announcement){
        getButtonUploadFile().click();
        getInputFileUpload().uploadFile(new File(announcement.getFilePath()));
        waitUntilAlertDisappear();
        getInputName().setValue(announcement.getFileName());
        getButtonSave().click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Verify announcement exists")
    public AnnouncementsTabConfigUserPopup verifyAnnouncementExist(FileManagementTestData announcement){
        getFieldAnnouncementName(announcement.getFileName()).should(Condition.exist);
        return this;
    }

    @Step("Change announcement name")
    public AnnouncementsTabConfigUserPopup changeAnnouncementName (FileManagementTestData announcFile){
        getButtonEditUserAnnouncementsByName(announcFile.getFileName()).click();
        waitUntilAlertDisappear();
        configureAnnouncementPopup.getInputName().setValue(announcFile.rename());
        configureAnnouncementPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Verify announcement name")
    public AnnouncementsTabConfigUserPopup verifyAnnouncementName(FileManagementTestData announcFile){
        getButtonEditUserAnnouncementsByName(announcFile.getFileName()).click();
        waitUntilAlertDisappear();
        configureAnnouncementPopup.getInputName().shouldHave(value(announcFile.getFileName()));
        configureAnnouncementPopup.getButtonCancel().click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Activate Ringback option")
    public AnnouncementsTabConfigUserPopup activateRingbackOption(FileManagementTestData announcFile){
        getButtonEditUserAnnouncementsByName(announcFile.getFileName()).click();
        waitUntilAlertDisappear();
        configureAnnouncementPopup.getInputRingback().click();
        configureAnnouncementPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Get Recorded Voicemail Announcement By Phone")
    public AnnouncementsTabConfigUserPopup getRecordedVoicemailAnnouncementByPhone(FileManagementTestData file){
        recordVoicemailAnnouncementByPhone.getInputName().shouldHave(value("new"));
        recordVoicemailAnnouncementByPhone.getCheckboxVoicemail().shouldBe(selected);
        recordVoicemailAnnouncementByPhone.getInputName().setValue(file.getFileName());
        recordVoicemailAnnouncementByPhone.getButtonSave().click();
        waitUntilAlertDisappear();
        return this;
    }

    public AnnouncementsTabConfigUserPopup deleteAnnouncement(FileManagementTestData file){
        getButtonDeleteUserAnnouncementByName(file.getFileName()).click();
        new ConfirmationPopup().getYesButton().click();
        getFieldAnnouncementName(file.getFileName()).shouldNot(exist);
        refreshPage();
        return this;
    }

    @Step("Save")
    public AnnouncementsTabConfigUserPopup save(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return this;
    }


}


