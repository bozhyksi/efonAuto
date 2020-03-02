package pages.fileManagementPage.fileManagementPopups;

import com.codeborne.selenide.SelenideElement;
import pages.basePage.basePopup.BasePopup;

public class EditFileManagementPopup extends BasePopup {
    private String inputDisplayNameXpath = "//music-on-hold-detail//input[@formcontrolname=\"displayName\"]";
    private String buttonSaveXpath = "//div[@class=\"modal-content\"]//button[text()=\"Save\"]";
    private String buttonCancelXpath = "//div[@class=\"modal-content\"]//button[text()=\"Cancel\"]";

    public SelenideElement getInputDisplayName() {
        return field(inputDisplayNameXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getButtonCancel() {
        return field(buttonCancelXpath);
    }
}
