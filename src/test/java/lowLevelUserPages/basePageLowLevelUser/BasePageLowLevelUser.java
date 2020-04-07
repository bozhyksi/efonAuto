package lowLevelUserPages.basePageLowLevelUser;

import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;

public class BasePageLowLevelUser extends BasePage {
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
}
