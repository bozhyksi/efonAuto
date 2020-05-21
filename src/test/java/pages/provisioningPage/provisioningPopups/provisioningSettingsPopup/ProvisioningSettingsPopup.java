package pages.provisioningPage.provisioningPopups.provisioningSettingsPopup;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.basePage.basePopup.BasePopup;

public class ProvisioningSettingsPopup extends BasePopup {
    public enum ProvisioningSettingsPopupTabs{
        PROVISIONING_CONFIGURATION,
        PROVISIONING_SETTINGS;
    }

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


    private void gotoMenuTab(ProvisioningSettingsPopupTabs tabName){
            getMenuTab(tabName).click();
            waitUntilAlertDisappear();
    }

    private SelenideElement getMenuTab(ProvisioningSettingsPopupTabs tab){
        switch (tab) {
            case PROVISIONING_SETTINGS:
                return getTabProvisioningSettings();
            case PROVISIONING_CONFIGURATION:
                return getTabProvisioningConfiguration();
            default:
                return getTabProvisioningConfiguration();
        }
    }

    @Step("Goto provisioning settings")
    public ProvisioningSettingTab gotoProvisioningSettingsTab(){
        gotoMenuTab(ProvisioningSettingsPopupTabs.PROVISIONING_SETTINGS);
        return new ProvisioningSettingTab();
    }

    @Step("Goto provisioning configurations")
    public ProvisioningConfigurationTab gotoProvisioningConfigurationTab(){
        gotoMenuTab(ProvisioningSettingsPopupTabs.PROVISIONING_CONFIGURATION);
        return new ProvisioningConfigurationTab();
    }
}
