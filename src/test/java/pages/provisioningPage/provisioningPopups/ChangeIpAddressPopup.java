package pages.provisioningPage.provisioningPopups;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.basePage.basePopup.BasePopup;
import pages.provisioningPage.ProvisioningEndDevicesPage;

public class ChangeIpAddressPopup extends BasePopup {
    private final String inputIpAddress = "//input[@formcontrolname=\"ipAddress\"]";

    public SelenideElement getInputIpAddress() {
        return field(inputIpAddress);
    }

    @Step("Enter IP address ")
    public ChangeIpAddressPopup enterIpAddress(String ip){
        getInputIpAddress().setValue(ip);
        return this;
    }

    @Step("Save ip address")
    public ProvisioningEndDevicesPage saveIpAddress(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return new ProvisioningEndDevicesPage();
    }
}
