package pages.provisioningPage;

import com.codeborne.selenide.SelenideElement;

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
}
