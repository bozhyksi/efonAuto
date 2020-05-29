package pages.provisioningPage.provisioningPopups.provisioningSettingsPopup;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.Select;
import pages.basePage.basePopup.BasePopup;
import pages.provisioningPage.ProvisioningEndDevicesPage;
import pages.provisioningPage.ProvisioningPhoneModelsPage;

import static com.codeborne.selenide.Condition.text;

public class ProvisioningSettingsPopup extends BasePopup {

    public enum ProvisioningSettingsPopupTabs{
        PROVISIONING_CONFIGURATION,
        PROVISIONING_SETTINGS;
    }

    //<editor-fold desc="locators">
    private final String tabProvisioningConfigurationXpath = "//a[contains(text(),\"Provisioning configuration\")]";
    private final String tabProvisioningSettingsXpath = "//a[contains(text(),\"Provisioning settings\")]";
    private final String buttonClose = "//button[@class=\"close\"]";

    private final String fieldEndDeviceXpath = "//label[text()=\"End device\"]/../div";
    private final String fieldPhoneModelXpath = "//label[text()=\"Phone model\"]/../div";
    //</editor-fold>

    //<editor-fold desc="get\set">


    public SelenideElement getButtonClose() {
        return field(buttonClose);
    }

    public SelenideElement getFieldPhoneModel() {
        return field(fieldPhoneModelXpath);
    }

    public SelenideElement getfieldEndDevice() {
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

    @Step("Save changes")
    public ProvisioningEndDevicesPage saveChanges(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return new ProvisioningEndDevicesPage();
    }

    @Step("Save Phone Model changes")
    public ProvisioningPhoneModelsPage savePhoneModelChanges(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return new ProvisioningPhoneModelsPage();
    }

    @Step("Check end device and user name")
    public ProvisioningSettingsPopup checkEndDeviceAndUserName(String endDevice, String userName){
        getfieldEndDevice().getText().contains(endDevice);
        getfieldEndDevice().getText().contains(userName);
        return this;
    }

    @Step("Verify header - phone model name")
    public ProvisioningSettingsPopup verifyPhoneModelName(String name){
        getFieldPhoneModel().shouldHave(text(name));
        return this;
    }



}
