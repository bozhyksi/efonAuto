package pages.provisioningPage;

import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;

public class ProvisioningBasePage extends BasePage{

    //<editor-fold desc="locators">
    private final String tabProvisioningEndDevicesXpath = "//a[contains(@href,\"/provisioning/end-devices\")]";
    private final String tabProvisioningPhoneModelsXpath = "//a[contains(@href,\"/provisioning/phone-models\")]";
    private final String tabProvisioningManagerXpath = "//a[contains(@href,\"/provisioning-manager\")]";
    private final String inputSearchXpath = "//h3[text()=\"Search\"]/../input";
    //</editor-fold>

    //<editor-fold desc="get\set">

    public SelenideElement getInputSearch() {
        return field(inputSearchXpath);
    }

    public SelenideElement getTabProvisioningEndDevices() {
        return field(tabProvisioningEndDevicesXpath);
    }

    public SelenideElement getTabProvisioningPhoneModels() {
        return field(tabProvisioningPhoneModelsXpath);
    }

    public SelenideElement getTabProvisioningManager() {
        return field(tabProvisioningManagerXpath);
    }
    //</editor-fold>
}
