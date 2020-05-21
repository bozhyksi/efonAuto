package pages.provisioningPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.provisioningPage.provisioningPopups.provisioningSettingsPopup.ConfigurationTabProvisioningSettingPopup;

public class ProvisioningEndDevicesPage extends ProvisioningBasePage {

    //<editor-fold desc="locators">
    private final String buttonEditEndDeviceByNameXpath = "//td[contains(text(),\"%s\")]/..//i[contains(@class,\"fa-cog\")]/..";
    private final String fieldDisplayNameByTextXpath = "//td/div[contains(text(),\"%s)]";
    private final String fieldByTextXpath = "//table//td[contains(text(),\"%s\")]";
    private final String buttonPlusByTextXpath = "//table//td[contains(text(),\"%s\")]/..//a[@xpath=\"1\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">


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
    public ConfigurationTabProvisioningSettingPopup clickEditButton(String name){
        getButtonEditEndDeviceByName(name).click();
        waitUntilAlertDisappear();
        return new ConfigurationTabProvisioningSettingPopup();
    }

}
