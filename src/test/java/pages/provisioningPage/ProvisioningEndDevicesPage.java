package pages.provisioningPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.provisioningPage.provisioningPopups.AdditionalEndDevicesPopup;
import pages.provisioningPage.provisioningPopups.ChangeIpAddressPopup;
import pages.provisioningPage.provisioningPopups.SelectForMacProvisioningPopup;
import pages.provisioningPage.provisioningPopups.provisioningSettingsPopup.ProvisioningSettingsPopup;
import tests.userPageTests.userPageTestData.User;

import static com.codeborne.selenide.Condition.exist;
import static core.configuration.preparations.eFonApp.confirmationPopup;
import static core.configuration.preparations.eFonApp.queueForAgentsPopup;

public class ProvisioningEndDevicesPage extends ProvisioningBasePage {

    //<editor-fold desc="locators">

    private final String buttonEditEndDeviceByNameXpath = "//td[contains(text(),\"%s\")]/..//i[contains(@class,\"fa-cog\")]/..";
    private final String fieldDisplayNameByTextXpath = "//td/div[contains(text(),\"%s)]";
    private final String fieldByTextXpath = "//table//td[contains(text(),\"%s\")]";
    private final String buttonPlusByTextXpath = "//table//td[contains(text(),\"%s\")]/..//a[@xpath=\"1\"]";

    private final String buttonActionsMenuByTextXpath = "//td[contains(text(),\"%s\")]/..//a[@id=\"button-custom-html\"]";
    private final String buttonSelectForProvisionXpath = "//*[@id=\"dropdown-custom-html\"]//a[text()=\"Select for provisioning\"]";
    private final String buttonSetManuallProvisionXpath = "//*[@id=\"dropdown-custom-html\"]//a[text()=\"Set as manually provisioned\"]";
    private final String buttonSelectForMacXpath = "//*[@id=\"dropdown-custom-html\"]//a[text()=\"Select for MAC provisioning\"]";
    private final String buttonClearProvisDataXpath = "//*[@id=\"dropdown-custom-html\"]//a[text()=\"Clear provisioning data\"]";
    private final String buttonChangeIpXpath = "//*[@id=\"dropdown-custom-html\"]//a[text()=\"Change IP address\"]";
    private final String buttonDeselectProvisXpath = "//*[@id=\"dropdown-custom-html\"]//a[text()=\"Deselect from provisioning\"]";
    private final String buttonResetManuallyProvisXpath = "//*[@id=\"dropdown-custom-html\"]//a[text()=\"Reset manually provisioned\"]";
    private final String iconFaCheckManualProvByTextXpath= "//td[contains(text(),\"%s\")]/..//td[8]//i[contains(@class, \"fa-check\")]";
    private final String iconFaCheckSelectProvByTextXpath= "//td[contains(text(),\"%s\")]/..//td[7]//i[contains(@class, \"fa-check\")]";
    private final String iconFaCheckAutoProvByTextXpath= "//td[contains(text(),\"%s\")]/..//td[6]//i[contains(@class, \"fa-check\")]";
    private final String fieldMacByTextXpath= "//td/span[text()=\"%s\"]";

    //</editor-fold>

    //<editor-fold desc="get\set">


    public SelenideElement getFieldMacByText(String text) {
        return field(String.format(fieldMacByTextXpath,text));
    }

    public SelenideElement getIconFaCheckAutoProvByText(String text) {
        return field(String.format(iconFaCheckAutoProvByTextXpath,text));
    }

    public SelenideElement getIconFaCheckSelectProvByText(String text) {
        return field(String.format(iconFaCheckSelectProvByTextXpath,text));
    }

    public SelenideElement getIconFaCheckManualProvByText(String text) {
        return field(String.format(iconFaCheckManualProvByTextXpath,text));
    }

    public SelenideElement getButtonResetManuallyProvis() {
        return field(buttonResetManuallyProvisXpath);
    }

    public SelenideElement getButtonDeselectProvis() {
        return field(buttonDeselectProvisXpath);
    }

    public SelenideElement getButtonChangeIp() {
        return field(buttonChangeIpXpath);
    }

    public SelenideElement getButtonClearProvisData() {
        return field(buttonClearProvisDataXpath);
    }

    public SelenideElement getButtonSelectForMac() {
        return field(buttonSelectForMacXpath);
    }

    public SelenideElement getButtonSetManuallProvision() {
        return field(buttonSetManuallProvisionXpath);
    }

    public SelenideElement getButtonSelectForProvision() {
        return field(buttonSelectForProvisionXpath);
    }

    public SelenideElement getButtonActionsMenuByText(String text) {
        return field(String.format(buttonActionsMenuByTextXpath,text));
    }

    public SelenideElement getButtonEditEndDeviceByName(String text) {
        return field(String.format(buttonEditEndDeviceByNameXpath,text));
    }

    public SelenideElement getButtonPlusByText(String text) {
        return field(String.format(buttonPlusByTextXpath,text));
    }

    public SelenideElement getFieldFirstNameByText(String text) {
        return field(String.format(fieldByTextXpath,text));
    }

    public SelenideElement getFieldLastNameByText(String text) {
        return field(String.format(fieldByTextXpath,text));
    }

    public SelenideElement getFieldDisplayNameByTextXpath(String text) {
        return field(String.format(fieldDisplayNameByTextXpath,text));
    }
    //</editor-fold>

    @Step("Click edit button and open Provisioning settings popup")
    public ProvisioningSettingsPopup clickEditButton(String name){
        getButtonEditEndDeviceByName(name).click();
        waitUntilAlertDisappear();
        return new ProvisioningSettingsPopup();
    }

    private void openMenu(String name){
        getButtonActionsMenuByText(name).click();
        waitUntilAlertDisappear();
    }

    @Step("End-device: Select for provisioning")
    public ProvisioningEndDevicesPage selectForProvisioning(String name){
        openMenu(name);
        getButtonSelectForProvision().click();
        waitUntilAlertDisappear();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        verifyIfSelectedForProvisioning(name);
        return this;
    }

    @Step("End-device: DEselect for provisioning")
    public ProvisioningEndDevicesPage deSelectForProvisioning(String name){
        openMenu(name);
        getButtonDeselectProvis().click();
        waitUntilAlertDisappear();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        verifyIfNotSelectedForProvisioning(name);
        return this;
    }

    @Step("End-device: Set as manually provisioned")
    public ProvisioningEndDevicesPage setManuallyProvisioned(String name){
        openMenu(name);
        getButtonSetManuallProvision().click();
        waitUntilAlertDisappear();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        verifyIfManuallyProvisioned(name);
        return this;
    }

    @Step("End-device: Reset manually provisioned")
    public ProvisioningEndDevicesPage resetManuallyProvisioned(String name){
        openMenu(name);
        getButtonResetManuallyProvis().click();
        waitUntilAlertDisappear();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        getIconFaCheckManualProvByText(name).shouldNot(exist);
        return this;
    }

    @Step("End-device: Select for MAC provisioning")
    public ProvisioningEndDevicesPage selectForMacProvisioning(String name, String macAddress){
        openMenu(name);
        clickSelectForMac()
                .enterMacAddress(macAddress)
                .saveMac();
        waitUntilAlertDisappear();
        getFieldMacByText(macAddress).should(exist);
        return this;
    }

    @Step("End-device: Change IP address")
    public ProvisioningEndDevicesPage changeIpAddress(String name, String ipAddress){
        openMenu(name);
        clickChangeIP()
                .enterIpAddress(ipAddress)
                .saveIpAddress();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Open MAC address popup")
    private SelectForMacProvisioningPopup clickSelectForMac(){
        getButtonSelectForMac().click();
        return new SelectForMacProvisioningPopup();
    }

    @Step("Open change IP address popup")
    private ChangeIpAddressPopup clickChangeIP(){
        getButtonChangeIp().click();
        waitUntilAlertDisappear();
        return new ChangeIpAddressPopup();
    }

    @Step("Verify if end-device is selected for provisioning")
    public ProvisioningEndDevicesPage verifyIfSelectedForProvisioning(String name){
        getIconFaCheckSelectProvByText(name).should(exist);
        return this;
    }

    @Step("Verify if end-device is DEselected for provisioning")
    public ProvisioningEndDevicesPage verifyIfNotSelectedForProvisioning(String name){
        getIconFaCheckSelectProvByText(name).shouldNot(exist);
        return this;
    }

    @Step("Verify if end-device is Manually provisioned")
    public ProvisioningEndDevicesPage verifyIfManuallyProvisioned(String name){
        getIconFaCheckManualProvByText(name).should(exist);
        return this;
    }

    @Step("Click plus button")
    public AdditionalEndDevicesPopup clickPlus(String text){
        getButtonPlusByText(text).click();
        waitUntilAlertDisappear();
        return new AdditionalEndDevicesPopup();
    }

}
