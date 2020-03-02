package pages.fileManagementPage;

import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;

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
}
