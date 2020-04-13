package lowLevelUserPages.voicemailLowLevelUserpage.voicemailUserPagePopups;

import com.codeborne.selenide.SelenideElement;
import lowLevelUserPages.voicemailLowLevelUserpage.AnnouncementsUserPage;

public class EditAnnouncementPopupUserPage extends AnnouncementsUserPage {
    //<editor-fold desc="locators">
    private String titleXpath = "//announcement-edit-name//p";
    private String inputNameXpath = "//announcement-edit-name//input[@formcontrolname=\"displayName\"]";
    private String checkboxVoicemailXpath = "//announcement-edit-name//input[@formcontrolname=\"asVoicemail\"]";
    private String checkboxRingbackXpath = "//announcement-edit-name//input[@formcontrolname=\"asRingback\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getTitle() {
        return field(titleXpath);
    }

    public SelenideElement getInputName() {
        return field(inputNameXpath);
    }

    public SelenideElement getCheckboxVoicemail() {
        return field(checkboxVoicemailXpath);
    }

    public SelenideElement getCheckboxRingback() {
        return field(checkboxRingbackXpath);
    }
    //</editor-fold>

}
