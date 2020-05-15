package lowLevelUserPages.faxPageLowLevelUser;

import com.codeborne.selenide.SelenideElement;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class FaxesBaseUserPage extends BasePageLowLevelUser {
    public enum FaxesBaseUserPageTabs{
        FAX_ARRIVED,
        FAX_SETTINGS,
        SEND_FAX;
    }

    //<editor-fold desc="locators">
    private final String tabFaxArrivedXpath = "//a[contains(@href,\"/fax/received-faxes\")]";
    private final String tabFaxSettingsXpath = "//a[contains(@href,\"/fax/fax-settings\")]";
    private final String tabFaxSendXpath = "//a[contains(@href,\"/fax/fax-send\")]";
    private final String dropdownNumberSearchXpath = "//h3[text()=\"Search\"]/..//select";
    //</editor-fold>

    //<editor-fold desc="get\set">

    public SelenideElement getDropdownNumberSearch() {
        return field(dropdownNumberSearchXpath);
    }

    public SelenideElement getTabFaxArrived() {
        return field(tabFaxArrivedXpath);
    }

    public SelenideElement getTabFaxSettings() {
        return field(tabFaxSettingsXpath);
    }

    public SelenideElement getTabFaxSend() {
        return field(tabFaxSendXpath);
    }
    //</editor-fold>

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

    public FaxesBaseUserPage selectNumberFromSearchDropdown(String number){
        getDropdownNumberSearch().selectOptionContainingText(number);
        return this;
    }

    public FaxesBaseUserPage validateNumberSearchDropDownItems(){
        waitUntilAlertDisappear();
        Select select = new Select(getDropdownNumberSearch());
        Assert.assertEquals(select.getOptions().size(), 2,"Numbers list is grater/less than one. ");
        return this;
    }
}
