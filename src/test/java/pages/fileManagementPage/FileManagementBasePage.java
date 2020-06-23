package pages.fileManagementPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.basePage.BasePage;

import java.io.File;

import static core.configuration.preparations.eFonApp.confirmationPopup;

public class FileManagementBasePage extends BasePage {
    //<editor-fold desc="Locators">
    private String inputNameXpath = "//input[@formcontrolname=\"displayName\"]";
    private String tabAnnouncementDisplayXpath = "//a[contains(@href,\"file-management/announcement-display\")]";
    private String tabMusicOnHoldXpath = "//a[contains(@href,\"/file-management/music-on-hold\")]";
    private String inputFileUploadXpath = "//input[@id=\"fileUploadInput\"]";
    private String buttonSaveXpath = "//button[text()=\"Save\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getInputName() {
        return field(inputNameXpath);
    }

    public SelenideElement getInputFileUpload() {
        return field(inputFileUploadXpath);
    }

    public SelenideElement getTabAnnouncementDisplay() {
        return field(tabAnnouncementDisplayXpath);
    }

    public SelenideElement getTabMusicOnHold() {
        return field(tabMusicOnHoldXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }
    //</editor-fold>

    @Step("Upload file")
    public FileManagementBasePage uploadFile(String filePath){
        getInputFileUpload().uploadFile(new File(filePath));
        return this;
    }

    @Step("Enter file name")
    public FileManagementBasePage enterFileName(String name){
        getInputName().setValue(name);
        return this;
    }

    @Step("Save changes")
    public <T extends FileManagementBasePage> T save(T obj){
        getButtonSave().click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        return (T) this;
    }
}
