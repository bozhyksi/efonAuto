package lowLevelUserPages.faxPageLowLevelUser;

import com.codeborne.selenide.SelenideElement;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;

public class FaxesBaseUserPage extends BasePageLowLevelUser {
    public enum FaxesBaseUserPageTabs{
        FAX_ARRIVED,
        FAX_SETTINGS,
        SEND_FAX;
    }

    private String tabFaxArrivedXpath = "//a[contains(@href,\"/fax/received-faxes\")]";
    private String tabFaxSettingsXpath = "//a[contains(@href,\"/fax/fax-settings\")]";
    private String tabFaxSendXpath = "//a[contains(@href,\"/fax/fax-send\")]";

    public SelenideElement getTabFaxArrived() {
        return field(tabFaxArrivedXpath);
    }

    public SelenideElement getTabFaxSettings() {
        return field(tabFaxSettingsXpath);
    }

    public SelenideElement getTabFaxSend() {
        return field(tabFaxSendXpath);
    }

    @Override
    public SelenideElement getDropdownItemsPerPage() {
        return super.getDropdownItemsPerPage();
    }

    public void goToMenuTab(FaxesBaseUserPageTabs tabName) {
        waitUntilAlertDisappear();
        getTab(tabName).click();
    }

    private SelenideElement getTab(FaxesBaseUserPageTabs tab){
        switch (tab){
            case SEND_FAX: return getTabFaxSend();
            case FAX_ARRIVED: return getTabFaxArrived();
            default: return getTabFaxSettings();
        }
    }
}
