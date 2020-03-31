package lowLevelUserPages.faxPageLowLevelUser;

import com.codeborne.selenide.SelenideElement;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;

public class FaxesBaseUserPage extends BasePageLowLevelUser {
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
}
