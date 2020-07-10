package pages.fileManagementPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.fileManagementPage.fileManagementPopups.EditFileManagementPopup;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;

import java.io.File;

import static com.codeborne.selenide.Condition.exist;
import static core.configuration.preparations.eFonApp.confirmationPopup;
import static pages.basePage.BasePage.MenuTabsBasePage.ANNOUNCEMENT_DISPLAY;
import static pages.basePage.BasePage.MenuTabsBasePage.FILE_MANAGEMENT;

public class AnnouncementDisplayPage extends FileManagementBasePage {

    //<editor-fold desc="Locators">
    private String buttonUploadFileXpath = "//a[@title=\"Upload WAV files\"]";
    private String buttonDownloadByNameXpath = "//table//td[2]/div[contains(text(),\"%s\")]/ancestor::tr//a[@title=\"Download\"]";
    private String buttonTestConfigByNameXpath = "//table//td[2]/div[contains(text(),\"%s\")]/ancestor::tr//a[@title=\"Create test configuration\"]";
    private String buttonEditByNameXpath = "//table//td[2]/div[contains(text(),\"%s\")]/ancestor::tr//a[@title=\"Edit\"]";
    private String buttonDeleteByNameXpath = "//table//td[2]/div[contains(text(),\"%s\")]/ancestor::tr//a[@title=\"Delete\"]";
    private String fieldNameByTextXpath = "//table//td[2]//div[text()[contains(.,\"%s\")]]";
    //</editor-fold>

    //<editor-fold desc="get\set">
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
    public AnnouncementDisplayPage uploadAnnouncement(FileManagementTestData file){
        goToMenuTab(MenuTabsBasePage.FILE_MANAGEMENT)
                .goToMenuTab(MenuTabsBasePage.ANNOUNCEMENT_DISPLAY);
        getButtonUploadFile().click();
        waitUntilAlertDisappear();
        super.uploadFile(file.getFilePath());
        getInputName().setValue(file.getFileName());
        getButtonSave().click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        refreshPage(); // temp fix because of the bug
        getFieldNameByText(file.getFileName()).should(exist);
        return this;
    }

    @Step("Delete announcement file")
    public AnnouncementDisplayPage deleteAnnouncement(FileManagementTestData ... files){
        goToMenuTab(FILE_MANAGEMENT)
                .goToMenuTab(ANNOUNCEMENT_DISPLAY);
        for (FileManagementTestData file : files) {
            getButtonDeleteByName(file.getFileName()).click();
            waitUntilAlertDisappear();
            confirmationPopup.getYesButton().click();
            waitUntilAlertDisappear();
            refreshPage();
            verifyIfAnnouncementNotExist(file);
        }
        return this;
    }

    @Step("Verify if announcement exists")
    public AnnouncementDisplayPage verifyIfAnnouncementExists(FileManagementTestData file){
        getFieldNameByText(file.getFileName()).should(exist);
        return this;
    }

    @Step("Verify if announcement does NOT exists")
    public AnnouncementDisplayPage verifyIfAnnouncementNotExist(FileManagementTestData file){
        getFieldNameByText(file.getFileName()).shouldNot(exist);
        return this;
    }

    @Step("Click create test configuration")
    public AnnouncementDisplayPage clickCreateTestConfig(FileManagementTestData file){
        getButtonTestConfigByName(file.getFileName()).click();
        return this;
    }

    @Step("Verify if test configuration was created")
    public AnnouncementDisplayPage verifyTestConfigCreated(){
        field("//*[contains(text(),\"Test configuration created\")]").should(exist);
        return this;
    }

    @Step("Click edit")
    public EditFileManagementPopup clickEdit(FileManagementTestData file){
        getButtonEditByName(file.getFileName()).click();
        waitUntilAlertDisappear();
        return new EditFileManagementPopup();
    }
}
