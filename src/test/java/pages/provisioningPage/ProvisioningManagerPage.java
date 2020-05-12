package pages.provisioningPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import tests.provisioningPageTests.provisioningTestData.ProvisioningTestData;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;

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
    private final String buttonReEnableXpath = "//button[text()=\"Re-Enable\"]";
    private final String buttonReActivateXpath = "//button[text()=\"Re-Activate\"]";
    private final String buttonDeActivateXpath = "//button[text()=\"Deactivate\"]";
    private final String buttonDisableXpath = "//button[text()=\"Disable\"]";
    private final String fieldIpAddressXpath = "//label[contains(text(),\"IP\")]/following-sibling::div";
    private final String fieldPinCodeXpath = "//label[contains(text(),\"PIN\")]/following-sibling::div";
    private final String fieldFullProvisioningListXpath = "//i[contains(@class,\"green\")]";
    //</editor-fold>


    //<editor-fold desc="get\set">


    public SelenideElement getButtonDeActivate() {
        return field(buttonDeActivateXpath);
    }

    public SelenideElement getButtonReActivate() {
        return field(buttonReActivateXpath);
    }

    public SelenideElement getButtonReEnable() {
        return field(buttonReEnableXpath);
    }

    public SelenideElement getFieldFullProvisioningList() {
        return field(fieldFullProvisioningListXpath);
    }

    public SelenideElement getFieldPinCode() {
        return field(fieldPinCodeXpath);
    }

    public SelenideElement getFieldIpAddress() {
        return field(fieldIpAddressXpath);
    }

    public SelenideElement getButtonDisable() {
        return field(buttonDisableXpath);
    }

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


    public ProvisioningManagerPage activateProvisioningManager(ProvisioningTestData.ProvisioningManagerData dto){
        if (getFieldProvisioningMode().has(text("enabled"))) deactivateProvisioningManager();
        getButtonEnable().click();
        waitUntilAlertDisappear();
        getInputPin().setValue(dto.getPinCode());
        getDropdownProvisioningMinutes().selectOptionByValue(dto.getSetProvisioningForXMinutes());
        getCheckboxAllowUsersToRequestFullProvisioningList().click();
        getCheckboxProvisionAllNonProvisionedEndDevices().click();
        getInputIpAddress().setValue(dto.getIpAddress());
        getButtonActivate().click();
        waitUntilAlertDisappear();
        refreshPage();
        return this;
    }

    public ProvisioningManagerPage deactivateProvisioningManager(){
        getButtonDisable().click();
        waitUntilAlertDisappear();
        refreshPage();
        getFieldProvisioningMode().shouldHave(text("disabled"));
        getButtonEnable().shouldBe(Condition.enabled);
        return this;
    }

    public ProvisioningManagerPage verifyProvisioningManagerConfiguration(ProvisioningTestData.ProvisioningManagerData dto){
        getFieldProvisioningMode().shouldHave(text("enabled"));
        getFieldIpAddress().shouldHave(text(dto.getIpAddress()));
        getFieldPinCode().shouldHave(text(dto.getPinCode()));
        getFieldFullProvisioningList().should(exist);
        return this;
    }

    public ProvisioningManagerPage reEnableProvisioningManager(ProvisioningTestData.ProvisioningManagerData dto){
        getButtonReEnable().click();
        waitUntilAlertDisappear();
        getInputPin().setValue(dto.getPinCode());
        getDropdownProvisioningMinutes().selectOptionByValue(dto.getSetProvisioningForXMinutes());
        getCheckboxProvisionAllNonProvisionedEndDevices().click();
        getInputIpAddress().setValue(dto.getIpAddress());
        getButtonReActivate().click();
        waitUntilAlertDisappear();
        refreshPage();
        return this;
    }

    public ProvisioningManagerPage reEnableAndDeactivateProvisioningManager(ProvisioningTestData.ProvisioningManagerData dto){
        getButtonReEnable().click();
        getButtonDeActivate().click();
        waitUntilAlertDisappear();
        refreshPage();
        return this;
    }

}
