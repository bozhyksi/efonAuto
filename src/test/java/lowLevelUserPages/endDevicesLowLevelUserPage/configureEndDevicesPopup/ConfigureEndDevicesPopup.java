package lowLevelUserPages.endDevicesLowLevelUserPage.configureEndDevicesPopup;

import com.codeborne.selenide.SelenideElement;
import core.fields.Fields;
import io.qameta.allure.Step;
import lowLevelUserPages.endDevicesLowLevelUserPage.EndDevicesUserPage;
import pages.basePage.basePopup.BasePopup;
import pages.userPage.userPagePopup.configureUser.EndDeviceTabConfigUserPopup;
import tests.userPageTests.userPageTestData.EndDevice;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.selected;

public class ConfigureEndDevicesPopup extends BasePopup {

    //<editor-fold desc="locators">
    private final String dropdownLanguageEndDevXpath = "//select[@formcontrolname=\"language\"]";
    private final String dropdownOutgoingNumEndDevXpath = "//select[@formcontrolname=\"outgoingNumber\"]";
    private final String inputLocationEndDevXpath = "//input[@formcontrolname=\"emergencyLocation\"]";
    private final String checkboxSuppressedYesXpath = "//span[text()=\"Yes\"]/..//input[@formcontrolname=\"suppressed\"]";
    private final String checkboxSuppressedNotXpath = "//span[text()=\"No\"]/..//input[@formcontrolname=\"suppressed\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">

    public SelenideElement getDropdownLanguageEndDev() {
        return field(dropdownLanguageEndDevXpath);
    }

    public SelenideElement getDropdownOutgoingNumEndDev() {
        return field(dropdownOutgoingNumEndDevXpath);
    }

    public SelenideElement getInputLocationEndDev() {
        return field(inputLocationEndDevXpath);
    }

    public SelenideElement getCheckboxSuppressedYes() {
        return field(checkboxSuppressedYesXpath);
    }

    public SelenideElement getCheckboxSuppressedNo() {
        return field(checkboxSuppressedNotXpath);
    }

    //</editor-fold>

    @Step("Select Phone language")
    public ConfigureEndDevicesPopup selectPhoneLanguage(String value){
        getDropdownLanguageEndDev().selectOptionByValue(value);
        return this;
    }

    @Step("Select outgoing number")
    public ConfigureEndDevicesPopup selectOutgoingNumber(String outgoingNumber){
        getDropdownOutgoingNumEndDev().selectOptionContainingText(outgoingNumber);
        return this;
    }

    @Step("Select Suppressed option")
    public ConfigureEndDevicesPopup selectSuppressedOption(boolean opt){
        if (opt)
            getCheckboxSuppressedYes().click();
        else
            getCheckboxSuppressedNo().click();
        return this;
    }

    @Step("Enter location")
    public ConfigureEndDevicesPopup enterLocation(String location){
        if (getInputLocationEndDev().is(enabled))
            getInputLocationEndDev().setValue(location);
        return this;
    }

    @Step("Save changes")
    public EndDevicesUserPage saveChanges(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return new EndDevicesUserPage();
    }

    @Step("Validate end device configuration")
    public ConfigureEndDevicesPopup validateEndDeviceConfiguration(EndDevice endDevice){
        getDropdownLanguageEndDev().getSelectedValue().contains(endDevice.getEndDevPhoneLanguage());
        getDropdownOutgoingNumEndDev().getSelectedValue().contains(endDevice.getEndDevOutgoingNumber());
        if (endDevice.getEndDevSuppressed())
            getCheckboxSuppressedYes().shouldBe(selected);
        else
            getCheckboxSuppressedNo().shouldBe(selected);
        return this;
    }

}
