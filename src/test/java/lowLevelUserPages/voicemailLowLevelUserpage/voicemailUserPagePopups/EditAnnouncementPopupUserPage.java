package lowLevelUserPages.voicemailLowLevelUserpage.voicemailUserPagePopups;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lowLevelUserPages.voicemailLowLevelUserpage.AnnouncementsUserPage;

public class EditAnnouncementPopupUserPage extends AnnouncementsUserPage {
    //<editor-fold desc="locators">
    private String titleXpath = "//announcement-edit-name//p";
    private String inputNameXpath = "//announcement-edit-name//input[@formcontrolname=\"displayName\"]";
    private String checkboxVoicemailXpath = "//announcement-edit-name//input[@formcontrolname=\"asVoicemail\"]";
    private String checkboxRingbackXpath = "//announcement-edit-name//input[@formcontrolname=\"asRingback\"]";
    private String saveButton = "//*[@role=\"dialog\"]//button[text()=\"Save\"]";
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

    @Step("Enter Name")
    public EditAnnouncementPopupUserPage enterName(String name){
        getInputName().setValue(name);
        return this;
    }

    @Step("Save")
    public AnnouncementsUserPage save(){
        field(saveButton).click();
        waitUntilAlertDisappear();
        return new AnnouncementsUserPage();
    }

}
