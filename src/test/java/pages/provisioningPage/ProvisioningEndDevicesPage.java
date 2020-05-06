package pages.provisioningPage;

import com.codeborne.selenide.SelenideElement;

public class ProvisioningEndDevicesPage extends ProvisioningBasePage {

    //<editor-fold desc="locators">
    private final String fieldDisplayNameByTextXpath = "//td/div[contains(text(),\"%s)]";
    private final String fieldByTextXpath = "//table//td[contains(text(),\"%s\")]";
    private final String buttonPlusByTextXpath = "//table//td[contains(text(),\"A\")]/..//a[@xpath=\"1\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">

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
}
