package lowLevelUserPages.basePageLowLevelUser;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;

import static com.codeborne.selenide.Condition.*;

public class BasePageLowLevelUser extends BasePage {
    public enum MenuTabsLowLevelUser{
        SEND_SMS,
        VOICEMAIL,
        DASHBOARD;
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
        CONTACT_SETTINGS("Contact settings");

        private String title;

        PageTitles(String title){
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    //<editor-fold desc="locators">
    private String tabVoiceMailXpath = "//a[@id=\"menu-22\"]";
    private String tabSendSmsXpath  = "//a[@id=\"menu-23\"]";
    private String tabDashboardXpath = "//a[@id=\"menu-10\"]";
    private String pageTitleXpath = "//h1[text()=\"%s\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
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

    @Override
    public SelenideElement getTabFax() {
        return super.getTabFax();
    }
    //</editor-fold>

    @Override
    public void goToMenuTab(MenuTabsBasePage tabName) {
        super.goToMenuTab(tabName);
    }

    public void goToMenuTab(MenuTabsLowLevelUser tabName) {
        getTab(tabName).click();
        waitUntilAlertDisappear();
    }

    private SelenideElement getTab(MenuTabsLowLevelUser tab){
        switch (tab){
            case VOICEMAIL: return getTabVoiceMail();
            case SEND_SMS: return getTabSendSms();
            case DASHBOARD: return getTabDashboard();
            default: return getTabSendSms();
        }
    }

    public void validatePageTitle(PageTitles title){
        getPageTitleXpath(title).should(exist).shouldBe(visible);
    }
}
