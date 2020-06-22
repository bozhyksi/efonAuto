package pages.blockListSection;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.basePage.BasePage;
import pages.blockListSection.blockListSectionPopup.BlockedNumbersPopup;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.selected;

public class BlockListSection extends BasePage {

    //<editor-fold desc="locators">
    private final String dropdownNumbersXpath = "//h3[contains(text(),\"numbers\")]/..//select";
    private final String checkboxBlockIncomCallsXpath = "//input[@formcontrolname=\"isEnabled\"]";
    private final String checkboxCallsSuppressedNumbersXpath = "//input[@formcontrolname=\"useSuppressedNumbers\"]";
    private final String checkboxUseBlockListXpath = "//input[@formcontrolname=\"useBlockList\"]";
    private final String dropdownForwardToXpath = "//select[@formcontrolname=\"forwardTo\"]";
    private final String dropdownBlocklistTypeXpath = "//select[@formcontrolname=\"blockListType\"]";
    private final String buttonEdit = "//select[@formcontrolname=\"blockListType\"]/../..//i[contains(@class, \"fa-cog\")]/..";
    private final String fieldByText = "//*[@id=\"systemModal\"]//td[contains(text(),\"%s\")]";
    //</editor-fold >


    //<editor-fold desc="get\set">
    public SelenideElement getFieldCommentByText(String text) {
        return field(String.format(fieldByText,text));
    }

    public SelenideElement getFieldNumberByText(String text) {
        return field(String.format(fieldByText,text));
    }

    public SelenideElement getButtonEdit() {
        return field(buttonEdit);
    }

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
    public BlockListSection checkIfDropdownContainsNumber(String item){
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
    public BlockListSection checkIfDropdownNotContainsNumber(String item){
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

    @Step("Click edit blocklist button. Open \"Enter blocked numbers\" popup")
    public BlockedNumbersPopup clickEdit(){
        getButtonEdit().click();
        waitUntilAlertDisappear();
        return new BlockedNumbersPopup();
    }

    @Step("Select number from drop-down")
    public BlockListSection selectNumber(String num){
        getDropdownNumbers().selectOptionContainingText(num);
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Activate Calls with suppressed numbers")
    public BlockListSection clickCallsWithSuppressedNumbers(){
        getCheckboxCallsSuppressedNumbers().click();
        return this;
    }

    @Step("Activate Block incoming calls")
    public BlockListSection clickBlockIncomingCalls(){
        getCheckboxBlockIncomCalls().click();
        return this;
    }

    @Step("Select ForwardTo")
    public BlockListSection selectForwardTo(String val){
        getDropdownForwardTo().selectOptionContainingText(val);
        return this;
    }

    @Step("Save changes")
    public BlockListSection saveChanges(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Verify Block incoming calls comfiguration")
    public BlockListSection verifyBlockIncomingCallsConfig(){
        getCheckboxBlockIncomCalls().shouldBe(selected);
        getDropdownForwardTo().getSelectedOption().getText().contains("Voicemail");
        return this;
    }

    @Step("Verify Calls with suppressed numbers comfiguration")
    public BlockListSection verifyCallsWithSuppressedNumbers(){
        getCheckboxCallsSuppressedNumbers().shouldBe(selected);
        return this;
    }

    @Step("Activate Use blocklist")
    public BlockListSection activateUseBlocklist(){
        if (!getCheckboxBlockIncomCalls().isSelected()) {
            clickBlockIncomingCalls()
                    .selectForwardTo("Voicemail");
        }
        if (!getCheckboxUseBlockList().isSelected()){
            getCheckboxUseBlockList().click();
            getDropdownBlocklistType().selectOptionContainingText("Blocked numbers");
        }
        return this;
    }

    @Step("Verify Use blocklist comfiguration")
    public BlockListSection verifyUseBlocklistConfigs(){
        getCheckboxUseBlockList().shouldBe(selected);
        getDropdownBlocklistType().getSelectedOption().getText().contains("Blocked numbers");
        return this;
    }


}
