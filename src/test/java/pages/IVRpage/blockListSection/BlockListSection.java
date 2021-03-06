package pages.IVRpage.blockListSection;

import com.codeborne.selenide.SelenideElement;
import pages.IVRpage.IVRpage;
import pages.basePage.BasePage;

public class BlockListSection extends IVRpage {
    //<editor-fold desc="locators">
    private String dropdownNumbersXpath = "//h3[contains(text(),\"numbers\")]/../select";
    private String checkboxBlockIncomingCallsXpath = "//input[@formcontrolname=\"isEnabled\"]";
    private String dropdownForwardToXpath = "//select[@formcontrolname=\"forwardTo\"]";
    private String checkboxUseBlocklistXpath = "//input[@formcontrolname=\"useBlockList\"]";
    private String dropdownBlocklistTypeXpath = "//select[@formcontrolname=\"blockListType\"]";
    private String checkboxCallsSuppressedNumbersXpath = "//input[@formcontrolname=\"useSuppressedNumbers\"]";
    private String buttonEditBlocklistXpath = "//select[@formcontrolname=\"blockListType\"]/../..//i[contains(@class,\"fa-cog\")]/..";
    private String fieldBlockedNumberByTextXpath = "//span[contains(text(),\"The following incoming numbers are blocked\")]/..//span[contains(text(),\"%s\")]";
    //</editor-fold>


    //<editor-fold desc="get\set">

    public SelenideElement getFieldBlockedNumberByText(String text) {
        return field(String.format(fieldBlockedNumberByTextXpath, text));
    }

    public SelenideElement getCheckboxCallsSuppressedNumbers() {
        return field(checkboxCallsSuppressedNumbersXpath);
    }

    public SelenideElement getDropdownNumbers() {
        return field(dropdownNumbersXpath);
    }

    public SelenideElement getCheckboxBlockIncomingCalls() {
        return field(checkboxBlockIncomingCallsXpath);
    }

    public SelenideElement getDropdownForwardTo() {
        return field(dropdownForwardToXpath);
    }

    public SelenideElement getCheckboxUseBlocklist() {
        return field(checkboxUseBlocklistXpath);
    }

    public SelenideElement getDropdownBlocklistType() {
        return field(dropdownBlocklistTypeXpath);
    }

    public SelenideElement getButtonEditBlocklist() {
        return field(buttonEditBlocklistXpath);
    }

    @Override
    public SelenideElement getButtonSave() {
        return super.getButtonSave();
    }
    //</editor-fold>


}
