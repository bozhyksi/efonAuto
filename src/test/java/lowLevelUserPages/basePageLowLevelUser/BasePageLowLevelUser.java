package lowLevelUserPages.basePageLowLevelUser;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lowLevelUserPages.faxPageLowLevelUser.FaxesBaseUserPage;
import lowLevelUserPages.lastCallsLowLevelUserPage.LastCallsUserPage;
import lowLevelUserPages.sendSmsPageLowLevelUser.SendSmsBaseUserPage;
import lowLevelUserPages.voicemailLowLevelUserpage.VoicemailBaseUserPage;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.basePage.BasePage;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.*;

public class BasePageLowLevelUser extends BasePage {
    public static String autotestUserPhone = "00451245789908";
    public static String autotestUserName = "AutoTestUser AutoTestUser";

    public enum MenuTabsLowLevelUser{
        SEND_SMS,
        DASHBOARD,
        ANNOUNCEMENTS,
        VOICEMAIL,
        VOICEMAIL_SETTING,
        MANAGE_SENDER_NUMBERS_AND_NAMES,
        FAX_ARRIVED,
        FAX_SETTINGS,
        SEND_FAX;
    }
    public enum PageTitles{
        OVERVIEW("Overview"),
        CALLS("Calls"),
        FAXES("Faxes"),
        ABBREVIATED_DIALLING("Abbreviated dialling"),
        FORWARDING("Forwarding"),
        VOICEMAIL("Voicemail"),
        QUEUES("Queues"),
        END_DEVICES("End devices"),
        SEND_SMS("Send SMS"),
        CONTACT_SETTINGS("Contact settings"),
        HUNT_GROUPS("Hunt Groups");

        private String title;

        PageTitles(String title){
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    //<editor-fold desc="locators">
    private String tabVoicemailXpath = "//a[contains(@href,\"/voicemail/overview\")]";
    private String tabVoicemailSettingXpath = "//a[contains(@href,\"/voicemail/settings\")]";
    private String tabAnnouncementsXpath = "//a[contains(@href,\"/voicemail/announcements\")]";
    private String tabVoiceMailXpath = "//a[@id=\"menu-22\"]";
    private String tabSendSmsXpath  = "//a[@id=\"menu-23\"]";
    private String tabDashboardXpath = "//a[@id=\"menu-10\"]";
    private String pageTitleXpath = "//h1[text()=\"%s\"]";
    private String tabManageSenderNumbersXpath = "//a[contains(@href,\"sms/sms-authorization-number\")]";
    private String tabFaxArrivedXpath = "//a[contains(@href,\"/fax/received-faxes\")]";
    private String tabFaxSettingsXpath = "//a[contains(@href,\"/fax/fax-settings\")]";
    private String tabFaxSendXpath = "//a[contains(@href,\"/fax/fax-send\")]";
    //</editor-fold>

    //<editor-fold desc="get\set">

    public SelenideElement getTabFaxArrived() {
        return field(tabFaxArrivedXpath);
    }

    public SelenideElement getTabFaxSettings() {
        return field(tabFaxSettingsXpath);
    }

    public SelenideElement getTabFaxSend() {
        return field(tabFaxSendXpath);
    }

    public SelenideElement getTabManageSenderNumbers() {
        return field(tabManageSenderNumbersXpath);
    }

    public SelenideElement getTabDashboard() {
        return field(tabDashboardXpath);
    }

    public SelenideElement getPageTitleXpath(PageTitles title) {
        return field(String.format(pageTitleXpath,title.getTitle()));
    }

    public SelenideElement getTabVoiceMail() {
        return field(tabVoiceMailXpath);
    }

    public SelenideElement getTabSendSms() {
        return field(tabSendSmsXpath);
    }

    public SelenideElement getTabVoicemail() {
        return field(tabVoicemailXpath);
    }

    public SelenideElement getTabVoicemailSetting() {
        return field(tabVoicemailSettingXpath);
    }

    public SelenideElement getTabAnnouncements() {
        return field(tabAnnouncementsXpath);
    }

    @Override
    public SelenideElement getTabFax() {
        return super.getTabFax();
    }
    //</editor-fold>

    @Override
    public BasePageLowLevelUser goToMenuTab(MenuTabsBasePage tabName) {
        super.goToMenuTab(tabName);
        return this;
    }

    public BasePageLowLevelUser goToMenuTab(MenuTabsLowLevelUser tabName) {
        getTab(tabName).click();
        waitUntilAlertDisappear();
        return this;
    }

    private SelenideElement getTab(MenuTabsLowLevelUser tab){
        switch (tab){
            case VOICEMAIL:return getTabVoiceMail();
            case SEND_SMS:return getTabSendSms();
            case DASHBOARD: return getTabDashboard();
            case ANNOUNCEMENTS: return getTabAnnouncements();
            case VOICEMAIL_SETTING: return getTabVoicemailSetting();
            case MANAGE_SENDER_NUMBERS_AND_NAMES: return getTabManageSenderNumbers();
            case FAX_ARRIVED: return getTabFaxArrived();
            case SEND_FAX: return getTabFaxSend();
            case FAX_SETTINGS: return getTabFaxSettings();
            default: return null;
        }
    }

    public void validatePageTitle(PageTitles title){
        getPageTitleXpath(title).should(exist).shouldBe(visible);
    }

    public BasePageLowLevelUser validateDropDownItems(SelenideElement dropdown){
        dropdown.click();
        waitUntilAlertDisappear();
        ArrayList<String> phones = new ArrayList<>();
        Select select = new Select(dropdown);
        int size = select.getOptions().size();
        for (WebElement elem:select.getOptions()) {
            phones.add(elem.getText());
        }
        Assert.assertEquals(size, 1,"Numbers list contains not user related phones: \n" + phones);
        return this;
    }
}
