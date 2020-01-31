package pages.userPage.userPagePopup.configureUser;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Text;
import pages.userPage.UserPage;

public class ConfigureUserBasePopup extends UserPage {
    //<editor-fold desc="//-- Configure user Locators --//">
    private String popupTitleXpath = "//div[@role=\"dialog\"]//h3";
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
    //</editor-fold>

    //<editor-fold desc="//-- ConfigureUserBasePopup get\set methods --//">
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
        getPopupTitle().shouldHave(Text.value(expected));
    }
}
