package pages.provisioningPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.provisioningPage.provisioningPopups.provisioningSettingsPopup.ProvisioningSettingsPopup;
import tests.provisioningPageTests.provisioningTestData.PhoneModelTestData;

import static pages.basePage.BasePage.MenuTabsBasePage.PROVISIONING_PHONE_MODELS;

public class ProvisioningPhoneModelsPage extends ProvisioningBasePage {
    //<editor-fold desc="locators">
    private final String fieldbyTextXpath = "//table//td[contains(text(),\"%s\")]";
    private final String buttonEditByTextXpath = "//table//td[contains(text(),\"%s\")]/..//a";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getFieldPhoneModelByText(String text) {
        return field(String.format(fieldbyTextXpath,text));
    }

    public SelenideElement getFieldFirmwareByText(String text) {
        return field(String.format(fieldbyTextXpath,text));
    }

    public SelenideElement getFieldConfigurationTemplateByText(String text) {
        return field(String.format(fieldbyTextXpath,text));
    }

    public SelenideElement getButtonEditByTextXpath(String text) {
        return field(String.format(buttonEditByTextXpath, text));
    }
    //</editor-fold>

    @Step("Edit phone model. Open Provisioning settings popup")
    public ProvisioningSettingsPopup clickEditPhoneModel(String phoneModel){
        getButtonEditByTextXpath(phoneModel).click();
        waitUntilAlertDisappear();
        return new ProvisioningSettingsPopup();
    }

    @Step("Edit phone model. Open Provisioning settings popup")
    public ProvisioningSettingsPopup clickEditPhoneModel(PhoneModelTestData phoneModel){
        getButtonEditByTextXpath(phoneModel.getPhoneModel()).click();
        waitUntilAlertDisappear();
        return new ProvisioningSettingsPopup();
    }

}
