package lowLevelUserPages.faxPageLowLevelUser;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;

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

    @Step("Select number from Search dropdown")
    public FaxesBaseUserPage selectNumberFromSearchDropdown(String number){
        getDropdownNumberSearch().selectOptionContainingText(number);
        return this;
    }

    @Step("Check if only user number is available in dropdown ")
    public FaxesBaseUserPage validateNumberSearchDropDownItems(){
        getDropdownNumberSearch().click();
        waitUntilAlertDisappear();
        ArrayList<String> phones = new ArrayList<>();
        Select select = new Select(getDropdownNumberSearch());
        int size = select.getOptions().size();
        for (WebElement elem:select.getOptions()) {
            phones.add(elem.getText());
        }
        Assert.assertEquals(size, 2,"Numbers list contains not user related phones: \n" + phones);
        return this;
    }
}
