package pages.callForwardingPage;

import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;

public class CallForwardingPage extends BasePage {
    //<editor-fold desc="Locators">
    private String dropdownMyNumbersXpath = "//h3[text()=\"My numbers\"]//following-sibling::select";

    private String checkboxAfterXpath = "//span[contains(text(),\"After\")]//preceding-sibling::input";
    private String inputDelayXpath = "//input[@formcontrolname=\"delay\"]";
    private String dropdownAfterForwardToXpath = "//span[text()=\"After\"]//ancestor::section//select[@formcontrolname=\"forwardTo\"]";
    private String inputAfterPhoneXpath = "//span[text()=\"After\"]//ancestor::section//input[@formcontrolname=\"number\"]";

    private String checkboxIfbusyXpath = "//span[text()=\"If busy\"]//preceding-sibling::input";
    private String dropdownIfbusyForwardToXpath = "//span[text()=\"If busy\"]//ancestor::section//select[@formcontrolname=\"forwardTo\"]";
    private String inputIfbusyPhoneXpath = "//span[text()=\"If busy\"]//ancestor::section//input[@formcontrolname=\"number\"]";

    private String checkboxDeviceUnavailableXpath = "//span[contains(text(),\"device unavailable\")]//preceding-sibling::input";
    private String dropdownDeviceForwardToXpath = "//span[contains(text(),\"device unavailable\")]//ancestor::section//select[@formcontrolname=\"forwardTo\"]";
    private String inputDevicePhoneXpath = "//span[contains(text(),\"device unavailable\")]//ancestor::section//input[@formcontrolname=\"number\"]";

    private String checkboxSuppressedNumbersXpath = "//label[text()=\"Calls with suppressed numbers\"]/input";
    private String dropdownSupprNumForwardToXpath = "//label[text()=\"Calls with suppressed numbers\"]//ancestor::section//select[@formcontrolname=\"forwardTo\"]";

    private String checkboxManualStatusXpath = "//p[text()=\"Manual status\"]//ancestor::section//input[@formcontrolname=\"isEnabled\"]";
    private String inputAbsentXpath = "//p[text()=\"Manual status\"]//ancestor::section//input[@formcontrolname=\"subject\"]";
    private String checkboxPrivateXpath = "//span[text()=\"Private\"]//following-sibling::input";
    private String dropdownManualStatusForwardToXpath = "//span[text()=\"Private\"]//ancestor::section//select[@formcontrolname=\"forwardTo\"]";
    private String inputManualStatusPhoneXpath = "//span[text()=\"Private\"]//ancestor::section//input[@formcontrolname=\"number\"]";
    private String inputDateFromXpath = "//input[@formcontrolname=\"dateFrom\"]";
    private String inputDateUntilXpath = "//input[@formcontrolname=\"dateUntil\"]";

    private String buttonSaveXpath =  "//button[text()=\"Save\"]";
    private String buttonCancelXpath =  "//button[text()=\"Cancel\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getButtonCancel() {
        return field(buttonCancelXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getDropdownMyNumbers() {
        return field(dropdownMyNumbersXpath);
    }

    public SelenideElement getCheckboxAfter() {
        return field(checkboxAfterXpath);
    }

    public SelenideElement getInputDelay() {
        return field(inputDelayXpath);
    }

    public SelenideElement getDropdownAfterForwardTo() {
        return field(dropdownAfterForwardToXpath);
    }

    public SelenideElement getInputAfterPhone() {
        return field(inputAfterPhoneXpath);
    }

    public SelenideElement getCheckboxIfbusy() {
        return field(checkboxIfbusyXpath);
    }

    public SelenideElement getDropdownIfbusyForwardTo() {
        return field(dropdownIfbusyForwardToXpath);
    }

    public SelenideElement getInputIfbusyPhone() {
        return field(inputIfbusyPhoneXpath);
    }

    public SelenideElement getCheckboxDeviceUnavailable() {
        return field(checkboxDeviceUnavailableXpath);
    }

    public SelenideElement getDropdownDeviceForwardTo() {
        return field(dropdownDeviceForwardToXpath);
    }

    public SelenideElement getInputDevicePhone() {
        return field(inputDevicePhoneXpath);
    }

    public SelenideElement getCheckboxSuppressedNumbers() {
        return field(checkboxSuppressedNumbersXpath);
    }

    public SelenideElement getDropdownSupprNumForwardTo() {
        return field(dropdownSupprNumForwardToXpath);
    }

    public SelenideElement getCheckboxManualStatus() {
        return field(checkboxManualStatusXpath);
    }

    public SelenideElement getInputAbsent() {
        return field(inputAbsentXpath);
    }

    public SelenideElement getCheckboxPrivate() {
        return field(checkboxPrivateXpath);
    }

    public SelenideElement getDropdownManualStatusForwardTo() {
        return field(dropdownManualStatusForwardToXpath);
    }

    public SelenideElement getInputManualStatusPhone() {
        return field(inputManualStatusPhoneXpath);
    }

    public SelenideElement getInputDateFrom() {
        return field(inputDateFromXpath);
    }

    public SelenideElement getInputDateUntil() {
        return field(inputDateUntilXpath);
    }
    //</editor-fold>

}
