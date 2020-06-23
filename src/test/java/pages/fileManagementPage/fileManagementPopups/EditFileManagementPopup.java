package pages.fileManagementPage.fileManagementPopups;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.basePage.basePopup.BasePopup;
import pages.fileManagementPage.MusicOnHoldPage;

public class EditFileManagementPopup extends BasePopup {

    //<editor-fold desc="locators">
    private String inputDisplayNameXpath = "//music-on-hold-detail//input[@formcontrolname=\"displayName\"]";
    private String buttonSaveXpath = "//div[@class=\"modal-content\"]//button[text()=\"Save\"]";
    private String buttonCancelXpath = "//div[@class=\"modal-content\"]//button[text()=\"Cancel\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getInputDisplayName() {
        return field(inputDisplayNameXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getButtonCancel() {
        return field(buttonCancelXpath);
    }
    //</editor-fold>

    @Step("Enter File name")
    public EditFileManagementPopup enterName(String name){
        getInputDisplayName().setValue(name);
        return this;
    }

    @Step("Save changes")
    public MusicOnHoldPage save(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return new MusicOnHoldPage();
    }
}
