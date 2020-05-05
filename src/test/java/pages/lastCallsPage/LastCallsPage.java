package pages.lastCallsPage;

import com.codeborne.selenide.SelenideElement;
import core.workers.menuNavigator.IMenuNavigator;
import core.workers.menuNavigator.MenuNavigator;
import pages.basePage.BasePage;

public class LastCallsPage extends BasePage implements IMenuNavigator {
    //<editor-fold desc="locators">
    private String tabMissedXpath = "//a[contains(@href,\"/last-calls/missed\")][text()=\"Missed\"]";
    private String tabIncomingXapth = "//a[contains(@href,\"last-calls/incoming\")]";
    private String tabOutgoingXapth = "//a[contains(@href,\"last-calls/outgoing\")]";
    private String tabOutgoingByMonthXapth = "//a[contains(@href,\"/last-calls/outgoing-by-month\")]";
    private String buttonSearchXpath = "//button[text()=\"Search\"]";
    private String checkboxAllNumbersXpath = "//span[text()=\"all numbers\"]/preceding-sibling::input";
    private String dropdownPhoneNumbersXpath = "//input[@type=\"checkbox\"]//ancestor::div//preceding-sibling::select";
    private String inputFromDateXpath = "//label[text()=\"From\"]//following-sibling::div/input";
    private String inputToDateXpath = "//label[text()=\"To\"]//following-sibling::div/input";
    private String inputMonthXpath = "//label[text()=\"Month\"]//following-sibling::div/input";
    private String fieldDestinationNumberXpath = "//tbody//td[contains(text(),\"%s\")]";
    //</editor-fold>

    //<editor-fold desc="get\set">

    public SelenideElement getFieldDestinationNumber(String name) {
        return field(String.format(fieldDestinationNumberXpath, name));
    }

    public SelenideElement getInputMonth() {
        return field(inputMonthXpath);
    }

    public SelenideElement getInputFromDate() {
        return field(inputFromDateXpath);
    }

    public SelenideElement getInputToDate() {
        return field(inputToDateXpath);
    }

    public SelenideElement getDropdownPhoneNumbers() {
        return field(dropdownPhoneNumbersXpath);
    }

    public SelenideElement getCheckboxAllNumbers() {
        return field(checkboxAllNumbersXpath);
    }

    public SelenideElement getButtonSearch() {
        return field(buttonSearchXpath);
    }

    public SelenideElement getTabIncoming() {
        return field(tabIncomingXapth);
    }

    public SelenideElement getTabMissed() {
        return field(tabMissedXpath);
    }

    public SelenideElement getTabOutgoing() {
        return field(tabOutgoingXapth);
    }

    //</editor-fold>

    public void gotoSubMenuTab(MenuNavigator.LastCallsSubTabs tabName){
        switch (tabName){
            case MISSED_CALLS: {
                getTabMissed().click();
                waitUntilAlertDisappear();
                break;
            }
            case INCOMING_CALLS: {
                getTabIncoming().click();
                waitUntilAlertDisappear();
                break;
            }
            case OUTGOING_CALLS: {
                getTabOutgoing().click();
                waitUntilAlertDisappear();
                break;
            }
        }
    }
}
