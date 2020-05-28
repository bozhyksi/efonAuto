package pages.blockListSection;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.basePage.BasePage;

import java.util.ArrayList;
import java.util.List;

public class BlockListSection extends BasePage {

    //<editor-fold desc="locators">
    private final String dropdownNumbersXpath = "//h3[contains(text(),\"numbers\")]/..//select";
    private final String checkboxBlockIncomCallsXpath = "//input[@formcontrolname=\"isEnabled\"]";
    private final String checkboxCallsSuppressedNumbersXpath = "//input[@formcontrolname=\"useSuppressedNumbers\"]";
    private final String checkboxUseBlockListXpath = "//input[@formcontrolname=\"useBlockList\"]";
    private final String dropdownForwardToXpath = "//select[@formcontrolname=\"forwardTo\"]";
    private final String dropdownBlocklistTypeXpath = "//select[@formcontrolname=\"blockListType\"]";
    //</editor-fold>


    //<editor-fold desc="get\set">
    public SelenideElement getDropdownNumbers() {
        return field(dropdownNumbersXpath);
    }

    public SelenideElement getCheckboxBlockIncomCalls() {
        return field(checkboxBlockIncomCallsXpath);
    }

    public SelenideElement getCheckboxCallsSuppressedNumbers() {
        return field(checkboxCallsSuppressedNumbersXpath);
    }

    public SelenideElement getCheckboxUseBlockList() {
        return field(checkboxUseBlockListXpath);
    }

    public SelenideElement getDropdownForwardTo() {
        return field(dropdownForwardToXpath);
    }

    public SelenideElement getDropdownBlocklistType() {
        return field(dropdownBlocklistTypeXpath);
    }
    //</editor-fold>

    @Step("Check if number exists in Blocklist dropdown")
    public BlockListSection dropdownContainsNumber(String item){
        field(dropdownNumbersXpath).click();
        waitUntilAlertDisappear();
        List<WebElement> list = new Select(field(dropdownNumbersXpath)).getOptions();
        waitUntilAlertDisappear();
        for (WebElement elem : list) {
            if(elem.getText().contains(item)) {
                return this;
            }
        }
        Assert.assertTrue(false, item+" does NOT exists in the dropdown");
        return this;
    }

    @Step("Check if number NOT exist in Blocklist dropdown")
    public BlockListSection dropdownNotContainsNumber(String item){
        field(dropdownNumbersXpath).click();
        waitUntilAlertDisappear();
        List<WebElement> list = new Select(field(dropdownNumbersXpath)).getOptions();
        for (WebElement elem : list) {
            if(elem.getText().contains(item)) {
                Assert.assertTrue(false, item+"exists in the dropdown");
                return this;
            }
        }
        return this;
    }



}
