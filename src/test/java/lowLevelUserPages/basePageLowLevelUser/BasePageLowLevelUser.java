package lowLevelUserPages.basePageLowLevelUser;

import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;

public class BasePageLowLevelUser extends BasePage {
    public enum MenuTabsLowLevelUser{
        SEND_SMS,
        VOICEMAIL;
    }

    private String tabVoiceMailXpath = "//a[@id=\"menu-22\"]";
    private String tabSendSmsXpath  = "//a[@id=\"menu-23\"]";


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
            default: return getTabSendSms();
        }
    }
}
