package pages.endDevicesPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.basePage.BasePage;
import pages.endDevicesPage.endDevicesPopUps.AutoProvisioningInfoToolTip;
import pages.endDevicesPage.endDevicesPopUps.ConfigureEndDevicesPopup;
import tests.endDevicesPageTests.endDevicesTestData.EndDevicesTestData;

import java.util.ArrayList;

public class EndDevicesPage extends BasePage {
    //<editor-fold desc="locators">
    private final String fieldByTextXpath = "//table//td[contains(text(),\"%s\")]";
    private final String fieldOutgoingNumberByTextXpath= "//table//a[contains(@href,\"%s\")]";
    private final String buttonAutoProvisioningToolTipByTextXpath = "//table//td[contains(text(),\"%s\")]/..//img[contains(@src,\"auto-provisioning-active\")]";
    private final String buttonEditXpath= "//table//td[contains(text(),\"%s\")]/..//i[contains(@class,\"fa-cog\")]/..";
    private final String buttonAutoProvTooTipXpath = "//img[contains(@src,\"auto-provisioning-active\")]";
    //private final String = "";
    //private final String = "";
    //private final String = "";
    //private final String = "";
    //private final String = "";
    //</editor-fold>


    //<editor-fold desc="get\set">

    public SelenideElement getButtonAutoProvTooTip() {
        return field(buttonAutoProvTooTipXpath);
    }

    public SelenideElement getButtonEdit(String text) {
        return field(String.format(buttonEditXpath,text));
    }

    public SelenideElement getFieldNameByText(String text) {
        return field(String.format(fieldByTextXpath,text));
    }

    public SelenideElement getFieldUserIdByText(String text) {
        return field(String.format(fieldByTextXpath,text));
    }

    public SelenideElement getFieldOutgoingNumberByText(String text) {
        return field(String.format(fieldOutgoingNumberByTextXpath,text));
    }

    public SelenideElement getButtonAutoProvisioningToolTip(String text) {
        return field(String.format(buttonAutoProvisioningToolTipByTextXpath,text));
    }
    //</editor-fold>

    @Step("Open Auto Provisioning Info Tool Tip")
    public AutoProvisioningInfoToolTip openAutoProvisionedInfoToolTip(EndDevicesTestData.EndDevice endDevice){
        getButtonAutoProvisioningToolTip(endDevice.getAutoProvisionedEndDeviceName()).click();
        waitUntilAlertDisappear();
        return new AutoProvisioningInfoToolTip();
    }

    @Step("Open Auto Provisioning Info Tool Tip")
    public AutoProvisioningInfoToolTip openAutoProvisionedInfoToolTip(){
        getButtonAutoProvTooTip().click();
        waitUntilAlertDisappear();
        return new AutoProvisioningInfoToolTip();
    }

    @Step("Edit end-device, open \"Configure end devices\" popup")
    public ConfigureEndDevicesPopup clickEditEndDevice(String endDeviceName){
        getButtonEdit(endDeviceName).click();
        waitUntilAlertDisappear();
        return new ConfigureEndDevicesPopup();
    }

    @Step("Edit end-device, open \"Configure end devices\" popup")
    public ConfigureEndDevicesPopup clickEditEndDevice(){
        field("//i[contains(@class,\"fa-cog\")]/..").click();
        waitUntilAlertDisappear();
        return new ConfigureEndDevicesPopup();
    }

    @Step("Verify if all customer numbers are available as outgoing")
    public void verifyOutgoingNumbersList(ArrayList<String> customerNumbersList, ArrayList<String> outgoingNumbersList){
        Assert.assertEquals(customerNumbersList.size(),outgoingNumbersList.size(),
                "Size of numbers lists is not equal " +
                "customer numbers: "+customerNumbersList.size()+"; outgoing numbers: "+outgoingNumbersList.size());

        for (String customerNumber: customerNumbersList) {
            Assert.assertTrue(outgoingNumbersList.contains(customerNumber),
                    customerNumber+" - does not exist in outgoing numbers list");
        }
    }

}
