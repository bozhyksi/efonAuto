package pages.provisioningPage.provisioningPopups;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.basePage.basePopup.BasePopup;
import pages.provisioningPage.ProvisioningEndDevicesPage;

public class SelectForMacProvisioningPopup extends BasePopup {
    private final String inputMac = "//input[@formcontrolname=\"mac\"]";

    public SelenideElement getInputMac() {
        return field(inputMac);
    }

    @Step("Enter MAC address")
    public SelectForMacProvisioningPopup enterMacAddress(String mac){
        getInputMac().setValue(mac);
        return this;
    }

    @Step("Save MAC address")
    public ProvisioningEndDevicesPage saveMac(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return new ProvisioningEndDevicesPage();
    }
}
