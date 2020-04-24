package pages.userPage.userPagePopup.configureUser;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;

public class AnnouncementsTabConfigUserPopup extends ConfigureUserBasePopup {
    private ConfigureAnnouncementPopup configureAnnouncementPopup = new ConfigureAnnouncementPopup();

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

    public void uploadAnnouncementFile (String filePath){
        getInputFileUpload().uploadFile(new File(filePath));
    }

    public void changeAnnouncementName (FileManagementTestData announcFile){
        getButtonEditUserAnnouncementsByName(announcFile.getFileName()).click();
        waitUntilAlertDisappear();
        configureAnnouncementPopup.getInputName().setValue(announcFile.rename());
        configureAnnouncementPopup.getButtonSave().click();
        waitUntilAlertDisappear();
    }

    public void verifyAnnouncementName(FileManagementTestData announcFile){
        getButtonEditUserAnnouncementsByName(announcFile.getFileName()).click();
        waitUntilAlertDisappear();
        configureAnnouncementPopup.getInputName().shouldHave(value(announcFile.getFileName()));
        configureAnnouncementPopup.getButtonCancel().click();
        waitUntilAlertDisappear();
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
}
