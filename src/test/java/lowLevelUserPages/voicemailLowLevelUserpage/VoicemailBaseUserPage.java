package lowLevelUserPages.voicemailLowLevelUserpage;

import com.codeborne.selenide.SelenideElement;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;

public abstract class VoicemailBaseUserPage extends BasePageLowLevelUser {

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

}
