package lowLevelUserPages.lastCallsLowLevelUserPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import testsLowLevelUser.lastCallsUserPageTests.lastCallsUserPageTestData.LastCallsUserPageTestData;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.*;

public abstract class LastCallsUserPage extends BasePageLowLevelUser {

    //<editor-fold desc="locators">
    private final String dropdownSelectNumberXpath = "//select";
    private final String checkboxAllNumbersXpath = "//span[text()=\"all numbers\"]/../input";
    private final String inputDateFromXpath = "//input[@formcontrolname=\"dateFrom\"]";
    private final String inputDateUntilXpath = "//input[@formcontrolname=\"dateUntil\"]";
    private final String buttonSearchXpath = "//button[text()=\"Search\"]";
    private final String buttonExportXpath = "//button[text()=\"Export\"]";
    private final String fieldByTextXpath= "//td[contains(text(),\"%s\")]";
    //</editor-fold>

    //<editor-fold desc="get/set">
    public SelenideElement getFieldNoItems() {
        return field(String.format(fieldByTextXpath,"No Items"));
    }

    public SelenideElement getDropdownSelectNumber() {
        return field(dropdownSelectNumberXpath);
    }

    public SelenideElement getCheckboxAllNumbers() {
        return field(checkboxAllNumbersXpath);
    }

    public SelenideElement getInputDateFrom() {
        return field(inputDateFromXpath);
    }

    public SelenideElement getInputDateUntil() {
        return field(inputDateUntilXpath);
    }

    public SelenideElement getButtonSearch() {
        return field(buttonSearchXpath);
    }

    public SelenideElement getButtonExport() {
        return field(buttonExportXpath);
    }
    //</editor-fold>

    @Step("Select phone number")
    public LastCallsUserPage selectPhoneNumber(String phoneNumber){
        getDropdownSelectNumber().selectOptionContainingText(phoneNumber);
        return this;
    }

    @Step("Set date FROM")
    public LastCallsUserPage setDateFrom(String dateFrom){
        getInputDateFrom().setValue(dateFrom).pressTab();
        return this;
    }

    @Step("Set date UNTIL")
    public LastCallsUserPage setDateUntil(String dateUntil){
        getInputDateUntil().setValue(dateUntil)
                .pressTab();
        return this;
    }

    @Step("Click Search")
    public LastCallsUserPage clickSearch(){
        getButtonSearch().click();
        waitUntilAlertDisappear();
        getAlertDialog().shouldNot(appear,exist,visible);
        getAlertErrorMsg().shouldNot(appear,exist,visible);
        return this;
    }

    @Step("Validate results")
    public LastCallsUserPage validateResults(){
        getFieldNoItems().shouldBe(visible,exist);
        return this;
    }

    @Step("Check if \"Phones\" dropdown contains only user's phone")
    public LastCallsUserPage validatePhoneDropDownItems(){
        getDropdownSelectNumber().click();
        waitUntilAlertDisappear();
        ArrayList<String> phones = new ArrayList<>();
        Select select = new Select(getDropdownSelectNumber());
        int size = select.getOptions().size();
        for (WebElement elem:select.getOptions()) {
            phones.add(elem.getText());
        }
        Assert.assertEquals(size, 1,"Numbers list contains not user related phones: \n" + phones);
        return this;
    }

    @Step("Activate all numbers checkbox")
    public LastCallsUserPage activateAllNumbersCheckBox(){
        getCheckboxAllNumbers().click();
        return this;
    }





}
