package lowLevelUserPages.voicemailLowLevelUserpage;

import com.codeborne.selenide.SelenideElement;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;

public class VoicemailBaseUserPage extends BasePageLowLevelUser {
    public enum VoicemailTabs{
        VOICEMAIL_OVERWIEV,
        VOICEMAIL_SETTING,
        ANNOUNCEMENTS;
    }

    //<editor-fold desc="locators">
    private String tabVoicemailXpath = "//a[contains(@href,\"/voicemail/overview\")]";
    private String tabVoicemailSettingXpath = "//a[contains(@href,\"/voicemail/settings\")]";
    private String tabAnnouncementsXpath = "//a[contains(@href,\"/voicemail/announcements\")]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getTabVoicemail() {
        return field(tabVoicemailXpath);
    }

    public SelenideElement getTabVoicemailSetting() {
        return field(tabVoicemailSettingXpath);
    }

    public SelenideElement getTabAnnouncements() {
        return field(tabAnnouncementsXpath);
    }
    //</editor-fold>

    private SelenideElement getTab(VoicemailTabs tab){
        switch (tab){
            case ANNOUNCEMENTS: return getTabAnnouncements();
            case VOICEMAIL_SETTING: return getTabVoicemailSetting();
            default: return getTabVoicemail();
        }
    }

    public void goToMenuTab(VoicemailTabs tab){
        getTab(tab).click();
        waitUntilAlertDisappear();
    }
}
