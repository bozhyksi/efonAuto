package pages.provisioningPage;

import com.codeborne.selenide.SelenideElement;

public class ProvisioningManagerPage extends ProvisioningBasePage {

    //<editor-fold desc="locators">
    private final String buttonEnableXpath = "//button[text()=\"Enable\"]";
    private final String fieldProvisioningModeXpath = "//label[text()=\"Provisioning mode\"]/following-sibling::div/span";
    private final String inputPinXpath = "//input[@formcontrolname=\"pin\"]";
    private final String dropdownProvisioningMinutesXpath = "//select[@formcontrolname=\"activeTimeout\"]";
    private final String checkboxProvisionAllNonProvisionedEndDevicesXpath = "//input[@formcontrolname=\"mustSelectNotProvisionedAccount\"]";
    private final String checkboxAllowUsersToRequestFullProvisioningListXpath = "//input[@formcontrolname=\"allowToRequestFullList\"]";
    private final String inputIpAddressXpath = "//input[@formcontrolname=\"ipAddressMasks\"]";
    private final String buttonActivateXpath = "//button[text()=\"Activate\"]";
    //</editor-fold>


    //<editor-fold desc="get\set">
    public SelenideElement getButtonActivate() {
        return field(buttonActivateXpath);
    }

    public SelenideElement getInputIpAddress() {
        return field(inputIpAddressXpath);
    }

    public SelenideElement getCheckboxAllowUsersToRequestFullProvisioningList() {
        return field(checkboxAllowUsersToRequestFullProvisioningListXpath);
    }

    public SelenideElement getCheckboxProvisionAllNonProvisionedEndDevices() {
        return field(checkboxProvisionAllNonProvisionedEndDevicesXpath);
    }

    public SelenideElement getDropdownProvisioningMinutes() {
        return field(dropdownProvisioningMinutesXpath);
    }

    public SelenideElement getInputPin() {
        return field(inputPinXpath);
    }

    public SelenideElement getButtonEnable() {
        return field(buttonEnableXpath);
    }

    public SelenideElement getFieldProvisioningMode() {
        return field(fieldProvisioningModeXpath);
    }
    //</editor-fold>

}
