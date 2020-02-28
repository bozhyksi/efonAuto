package pages.fileManagementPage.fileManagementPopups;

import com.codeborne.selenide.SelenideElement;
import pages.basePage.basePopup.BasePopup;

public class AnnouncementEditPopup extends BasePopup {
    private String inputDisplayNameXpath = "//input[@formcontrolname=\"displayName\"]";

    public SelenideElement getInputDisplayName() {
        return field(inputDisplayNameXpath);
    }
}
