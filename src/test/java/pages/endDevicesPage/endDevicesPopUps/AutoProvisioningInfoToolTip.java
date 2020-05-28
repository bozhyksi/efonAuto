package pages.endDevicesPage.endDevicesPopUps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.endDevicesPage.EndDevicesPage;
import tests.endDevicesPageTests.endDevicesTestData.EndDevicesTestData;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;

public class AutoProvisioningInfoToolTip extends EndDevicesPage {
    //<editor-fold desc="locators">
    private final String toolTipXpath = "//*[@role=\"tooltip\"]";
    private final String fieldPhoneModelXpath= "//*[@role=\"tooltip\"]//div[contains(text(),\"Phone model\")]/following-sibling::div";
    private final String fieldFirmwareVersionXpath= "//*[@role=\"tooltip\"]//div[contains(text(),\"Firmware version\")]/following-sibling::div";
    private final String fieldMACXpath= "//*[@role=\"tooltip\"]//div[contains(text(),\"MAC\")]/following-sibling::div";
    private final String fieldIpAddressXpath= "//*[@role=\"tooltip\"]//div[contains(text(),\"IP adress\")]/following-sibling::div";
    private final String fieldDateFirstProvisioningXpath= "//*[@role=\"tooltip\"]//div[contains(text(),\"Date of first provisioning\")]/following-sibling::div";
    private final String fieldLastBootIPXpath= "//*[@role=\"tooltip\"]//div[contains(text(),\"Last boot IP\")]/following-sibling::div";
    private final String fieldDateLastProvisioningXpath= "//*[@role=\"tooltip\"]//div[contains(text(),\"Date of last provisioning\")]/following-sibling::div";
    //</editor-fold>


    //<editor-fold desc="get\set">
    public SelenideElement getToolTip() {
        return field(toolTipXpath);
    }

    public SelenideElement getFieldPhoneModel() {
        return field(fieldPhoneModelXpath);
    }

    public SelenideElement getFieldFirmwareVersion() {
        return field(fieldFirmwareVersionXpath);
    }

    public SelenideElement getFieldMAC() {
        return field(fieldMACXpath);
    }

    public SelenideElement getFieldIpAddress() {
        return field(fieldIpAddressXpath);
    }

    public SelenideElement getFieldDateFirstProvisioning() {
        return field(fieldDateFirstProvisioningXpath);
    }

    public SelenideElement getFieldLastBootIP() {
        return field(fieldLastBootIPXpath);
    }

    public SelenideElement getFieldDateLastProvisioning() {
        return field(fieldDateLastProvisioningXpath);
    }
    //</editor-fold>

    public AutoProvisioningInfoToolTip verifyAutoProvisioningToolTipInfo(EndDevicesTestData.AutoProvisionedInfo obj){
        getFieldPhoneModel().shouldHave(text(obj.getPhoneModel()));
        getFieldFirmwareVersion().shouldHave(text(obj.getFirmwareVersion()));
        getFieldMAC().should(exist);
        getFieldLastBootIP().shouldHave(text(obj.getLastBootIp()));
        getFieldDateFirstProvisioning().shouldHave(text(obj.getDateOfFirstProvisioning()));
        getFieldDateLastProvisioning().shouldHave(text(obj.getDateOfLastProvisioning()));
        return new AutoProvisioningInfoToolTip();
    }

}
