package pages.provisioningPage.provisioningPopups.provisioningSettingsPopup;

import com.codeborne.selenide.SelenideElement;
import pages.basePage.basePopup.BasePopup;

public abstract class ProvisioningSettingsPopupBaseTab extends BasePopup {

    //<editor-fold desc="locators">
    private final String tabProvisioningConfigurationXpath = "//a[contains(text(),\"Provisioning configuration\")]";
    private final String tabProvisioningSettingsXpath = "//a[contains(text(),\"Provisioning settings\")]";

    private final String fieldEndDeviceXpath = "//label[text()=\"End device\"]/../div";
    private final String fieldPhoneModelXpath = "//label[text()=\"Phone model\"]/../div";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getFieldPhoneModel() {
        return field(fieldPhoneModelXpath);
    }

    public SelenideElement fieldEndDevice() {
        return field(fieldEndDeviceXpath);
    }

    public SelenideElement getTabProvisioningConfiguration() {
        return field(tabProvisioningConfigurationXpath);
    }

    public SelenideElement getTabProvisioningSettings() {
        return field(tabProvisioningSettingsXpath);
    }
    //</editor-fold>

}
