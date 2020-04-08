package pages.userPage.userPagePopup.configureUser;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.userPage.UserPage;

import static com.codeborne.selenide.Condition.*;

public class ConfigureUserBasePopup extends UserPage {
    public enum Tabs{
        NAME,
        ALLOCATIONS,
        FAX,
        FORWARDING,
        VOICEMAIL,
        ENDDEVICE,
        SECURITY,
        ANNOUNCEMENTS;
    }

    //<editor-fold desc="//-- Configure user Locators --//">
    private String popupTitleXpath = "//div[@role=\"dialog\"]//h1";
    private String textUserNameXpath = "//div[@role=\"dialog\"]//label[text()='User name']/following-sibling::div";
    private String textNumberXpath = "//div[@role=\"dialog\"]//label[text()='Number']/following-sibling::div";
    private String textEndDevicesXpath = "//div[@role=\"dialog\"]//label[text()='End devices']/following-sibling::div";
    private String tabNameXpath = "//div[@role=\"dialog\"]//a[text()='Name']";
    private String tabAllocationsXpath = "//div[@role=\"dialog\"]//a[text()='Allocations']";
    private String tabForwardingXpath = "//div[@role=\"dialog\"]//a[text()='Forwarding']";
    private String tabVoiceMailXpath = "//div[@role=\"dialog\"]//a[text()='Voicemail']";
    private String tabFaxXpath = "//div[@role=\"dialog\"]//a[text()='Fax']";
    private String tabEndDevicesXpath = "//div[@role=\"dialog\"]//a[text()='End devices']";
    private String tabSecurityXpath = "//div[@role=\"dialog\"]//a[text()='Security']";
    private String tabAnnouncementsXpath = "//div[@role=\"dialog\"]//a[text()='Announcements']";
    private String buttonCloseXpath = "//div[@role=\"dialog\"]//button[text()='Close']";
    private String buttonSaveXpath = "//form//button[text()=\"Save\"]";
    private String buttonCancelXpath = "//form//button[text()=\"Cancel\"]";
    //</editor-fold>

    //<editor-fold desc="//-- ConfigureUserBasePopup get\set methods --//">


    public SelenideElement getTabUserFax() {
        return field(tabFaxXpath);
    }

    public SelenideElement getTabVoiceMail() {
        return field(tabVoiceMailXpath);
    }

    public SelenideElement getButtonCancel() {
        return field(buttonCancelXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getPopupTitle() {
        return field(popupTitleXpath);
    }

    public SelenideElement getTextUserName() {
        return field(textUserNameXpath);
    }

    public SelenideElement getTextNumber() {
        return field(textNumberXpath);
    }

    public SelenideElement getTabEndDevices() {
        return field(tabEndDevicesXpath);
    }

    public SelenideElement getTextEndDevices() {
        return field(textEndDevicesXpath);
    }

    public SelenideElement getTabName() {
        return field(tabNameXpath);
    }

    public SelenideElement getTabAllocations() {
        return field(tabAllocationsXpath);
    }

    public SelenideElement getTabForwarding() {
        return field(tabForwardingXpath);
    }

    public SelenideElement getTabSecurity() {
        return field(tabSecurityXpath);
    }

    public SelenideElement getTabAnnouncements() {
        return field(tabAnnouncementsXpath);
    }

    public SelenideElement getButtonClose() {
        return field(buttonCloseXpath);
    }
    //</editor-fold>

    public void validatePopupTitle(String expected){
        getPopupTitle().shouldHave(Condition.text(expected));
    }

    private SelenideElement getTabElement(Tabs tabName){
        switch (tabName){
            case FAX:
                return getTabUserFax();
            case NAME:
                return getTabName();
            case SECURITY:
                return getTabSecurity();
            case ENDDEVICE:
                return getTabEndDevices();
            case VOICEMAIL:
                return getTabVoiceMail();
            case FORWARDING:
                return getTabForwarding();
            case ALLOCATIONS:
                return getTabAllocations();
            case ANNOUNCEMENTS:
                return getTabAnnouncements();
            default:
                return getTabAnnouncements();
        }
    }

    public void goToTab(Tabs tabName){
        waitUntilAlertDisappear();
        getTabElement(tabName).shouldBe(visible,enabled,appear,appears,exist);
        getTabElement(tabName).waitUntil(visible,500);
        getTabElement(tabName).waitUntil(Condition.enabled,500);
        getTabElement(tabName).waitUntil(Condition.appears,500);
        getTabElement(tabName).waitUntil(Condition.appear,500);
        getTabElement(tabName).click();
        waitUntilAlertDisappear();
    }
}
