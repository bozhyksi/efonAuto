package lowLevelUserPages.voicemailLowLevelUserpage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lowLevelUserPages.voicemailLowLevelUserpage.voicemailUserPagePopups.EditAnnouncementPopupUserPage;
import pages.userPage.userPagePopup.configureUser.AnnouncementsTabConfigUserPopup;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;

import java.io.File;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.exist;
import static core.configuration.preparations.eFonApp.confirmationPopup;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.MenuTabsLowLevelUser.VOICEMAIL;

public class AnnouncementsUserPage extends VoicemailBaseUserPage {

    //<editor-fold desc="Locators">
    private String inputFileNameXpath = "//input[@formcontrolname=\"displayName\"]";
    private String buttonUploadFileXpath = "//a[@title=\"Upload WAV files\"]";
    private String buttonDownloadByNameXpath = "//table//td[2]/div[contains(text(),\"%s\")]/ancestor::tr//a[@title=\":::Download\"]";
    private String buttonTestConfigByNameXpath = "//table//td[2]/div[contains(text(),\"%s\")]/ancestor::tr//a[@title=\":::Create test configuration\"]";
    private String buttonEditByNameXpath = "//table//td/div[contains(text(),\"%s\")]/ancestor::tr//i[contains(@class,\"fa-cog\")]/..";
    private String buttonDeleteByNameXpath = "//table//td/div[contains(text(),\"%s\")]/ancestor::tr//i[contains(@class,\"fa-trash\")]/..";
    private String fieldNameByTextXpath = "//table//td//div[text()[contains(.,\"%s\")]]";
    private String inputFileUploadXpath = "//input[@id=\"fileUploadInput\"]";
    private String buttonSaveXpath = "//button[text()=\"Save\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">


    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getInputFileName() {
        return field(inputFileNameXpath);
    }

    public SelenideElement getInputFileUpload() {
        return field(inputFileUploadXpath);
    }

    public SelenideElement getButtonUploadFile() {
        return field(buttonUploadFileXpath);
    }

    public SelenideElement getButtonDownloadByName(String name) {
        return field(String.format(buttonDownloadByNameXpath,name));
    }

    public SelenideElement getButtonTestConfigByName(String name) {
        return field(String.format(buttonTestConfigByNameXpath, name));
    }

    public SelenideElement getButtonEditByName(String name) {
        return field(String.format(buttonEditByNameXpath,name));
    }

    public SelenideElement getButtonDeleteByName(String name) {
        return field(String.format(buttonDeleteByNameXpath,name));
    }

    public SelenideElement getFieldNameByText(String name) {
        return field(String.format(fieldNameByTextXpath,name));
    }
    //</editor-fold>

    @Step("Upload announcement file")
    public AnnouncementsUserPage uploadAnnouncementFile(FileManagementTestData file){
        getButtonUploadFile().click();
        getInputFileUpload().uploadFile(new File(file.getFilePath()));
        getInputFileName().setValue(file.getFileName());
        getButtonSave().click();
        confirmationPopup.getYesButton().click();
        return this;
    }

    @Step("Verify if announcement file exists")
    public AnnouncementsUserPage validateIfAnnouncementExcists(FileManagementTestData file){
        getFieldNameByText(file.getFileName()).should(exist);
        return this;
    }

    public void editAnnouncementName(FileManagementTestData file){
        EditAnnouncementPopupUserPage editPopup = new EditAnnouncementPopupUserPage();
        getButtonEditByName(file.getFileName()).click();
        waitUntilAlertDisappear();
        editPopup.getInputName().setValue(file.rename());
        getButtonSave().click();
        waitUntilAlertDisappear();
    }

    @Step("Click edit")
    public EditAnnouncementPopupUserPage clickEdit(FileManagementTestData file){
        getButtonEditByName(file.getFileName()).click();
        waitUntilAlertDisappear();
        return new EditAnnouncementPopupUserPage();
    }

    public void getRecordedAnnouncementByPhone(FileManagementTestData file){
        new AnnouncementsTabConfigUserPopup().getRecordedVoicemailAnnouncementByPhone(file)
        .deleteAnnouncement(file);
    }


}

