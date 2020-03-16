package pages.userPage.userPagePopup.configureUser;

import com.codeborne.selenide.SelenideElement;

public class ForwardingTabConfigUserPopup extends ConfigureUserBasePopup {
    //<editor-fold desc="Locators">
    private String dropdownMyNumbersXpath = "//portal-tabs//h3/following-sibling::select";
    private String buttonSaveXpath = "//edit-user//button[text()=\"Save\"]";
    private String buttonCancelXpath = "//edit-user//button[text()=\"Cancel\"]";

    //After
    private String checkboxAfterXpath = "//section[@formgroupname=\"baseCallForwarding\"]//input[@formcontrolname=\"isEnabled\"]";
    private String inputForwardToPhoneXpath = "//section[@formgroupname=\"baseCallForwarding\"]//input[@formcontrolname=\"number\"]";
    private String inputForwardDelayXpath = "//section[@formgroupname=\"baseCallForwarding\"]//input[@formcontrolname=\"delay\"]";
    private String dropdownForwardToXpath = "//section[@formgroupname=\"baseCallForwarding\"]//select[@formcontrolname=\"forwardTo\"]";

    //If busy
    private String checkboxIfBusyXpath = "//section[@formgroupname=\"busy\"]//input[@formcontrolname=\"isEnabled\"]";
    private String inputIfBusyPhoneXpath = "//section[@formgroupname=\"busy\"]//input[@formcontrolname=\"number\"]";
    private String dropdownIfBusyXpath = "//section[@formgroupname=\"busy\"]//select[@formcontrolname=\"forwardTo\"]";

    //If end device unavailable
    private String checkboxIfEndDeviceUnavailableXpath = "//section[@formgroupname=\"backupRouting\"]//input[@formcontrolname=\"isEnabled\"]";
    private String inputIfEndDeviceUnavailablePhoneXpath = "//section[@formgroupname=\"backupRouting\"]//input[@formcontrolname=\"number\"]";
    private String dropdownIfEndDeviceUnavailablePhoneXpath = "//section[@formgroupname=\"backupRouting\"]//select[@formcontrolname=\"forwardTo\"]";

    //Manual status
    private String checkboxManualStatusXpath = "//section[@formgroupname=\"manualStatus\"]//input[@formcontrolname=\"isEnabled\"]";
    private String inputManualSubjectXpath = "//section[@formgroupname=\"manualStatus\"]//input[@formcontrolname=\"subject\"]";
    private String checkboxPrivateXpath = "//section[@formgroupname=\"manualStatus\"]//input[@formcontrolname=\"isPrivate\"]";
    private String dropdownManualStatusForwardToXpath = "//section[@formgroupname=\"manualStatus\"]//select[@formcontrolname=\"forwardTo\"]";
    private String inputManualStatusForwardToXpath = "//section[@formgroupname=\"manualStatus\"]//input[@formcontrolname=\"number\"]";
    private String inputDateFromXpath = "//section[@formgroupname=\"manualStatus\"]//input[@formcontrolname=\"dateFrom\"]";
    private String inputDateToXpath = "//section[@formgroupname=\"manualStatus\"]//input[@formcontrolname=\"dateUntil\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getInputDateTo() {
        return field(inputDateToXpath);
    }

    public SelenideElement getInputDateFrom() {
        return field(inputDateFromXpath);
    }

    public SelenideElement getInputManualStatusForwardTo() {
        return field(inputManualStatusForwardToXpath);
    }

    public SelenideElement getDropdownManualStatusForwardTo() {
        return field(dropdownManualStatusForwardToXpath);
    }

    public SelenideElement getCheckboxPrivate() {
        return field(checkboxPrivateXpath);
    }

    public SelenideElement getInputManualSubject() {
        return field(inputManualSubjectXpath);
    }

    public SelenideElement getCheckboxManualStatus() {
        return field(checkboxManualStatusXpath);
    }

    public SelenideElement getDropdownIfEndDeviceUnavailablePhone() {
        return field(dropdownIfEndDeviceUnavailablePhoneXpath);
    }

    public SelenideElement getInputIfEndDeviceUnavailablePhone() {
        return field(inputIfEndDeviceUnavailablePhoneXpath);
    }

    public SelenideElement getCheckboxIfEndDeviceUnavailable() {
        return field(checkboxIfEndDeviceUnavailableXpath);
    }

    public SelenideElement getDropdownIfBusy() {
        return field(dropdownIfBusyXpath);
    }

    public SelenideElement getInputIfBusyPhone() {
        return field(inputIfBusyPhoneXpath);
    }

    public SelenideElement getCheckboxIfBusy() {
        return field(checkboxIfBusyXpath);
    }

    public SelenideElement getButtonCancel() {
        return field(buttonCancelXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getDropdownForwardTo() {
        return field(dropdownForwardToXpath);
    }

    public SelenideElement getDropdownMyNumbers() {
        return field(dropdownMyNumbersXpath);
    }

    public SelenideElement getInputForwardDelay() {
        return field(inputForwardDelayXpath);
    }

    public SelenideElement getCheckboxAfter() {
        return field(checkboxAfterXpath);
    }

    public SelenideElement getInputForwardToPhone() {
        return field(inputForwardToPhoneXpath);
    }
    //</editor-fold>
}
