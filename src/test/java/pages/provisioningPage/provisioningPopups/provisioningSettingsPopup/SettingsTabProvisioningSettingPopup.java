package pages.provisioningPage.provisioningPopups.provisioningSettingsPopup;

import com.codeborne.selenide.SelenideElement;

public class SettingsTabProvisioningSettingPopup extends ProvisioningSettingsPopupBaseTab {

    //<editor-fold desc="locators">
    private final String dropdownLanguageXpath = "//select[@formcontrolname=\"languageCode\"]";
    private final String dropdownWebLanguageXpath = "//select[@formcontrolname=\"webLanguageCode\"]";
    private final String inputConfigureWebAuthXpath = "//input[@formcontrolname=\"isWebAuthCustom\"]";
    private final String inputWebUserXpath = "//input[@formcontrolname=\"webUsername\"]";
    private final String inputWebPasswordXpath = "//input[@formcontrolname=\"webPassword\"]";
    private final String dropdownFunctionByNumberXpath = "//td[contains(text(),\" %s\")]/..//select[@formcontrolname=\"type\"]";
    private final String inputDestinationNumberByNumberXpath = "//td[contains(text(),\" %s\")]/..//input[@formcontrolname=\"destinationNumber\"]";
    private final String inputDisplayNameByNumberXpath = "//td[contains(text(),\" %s\")]/..//input[@formcontrolname=\"displayName\"]";
    //</editor-fold>


     //<editor-fold desc="get\set">
    public SelenideElement getDropdownLanguage() {
        return field(dropdownLanguageXpath);
    }

    public SelenideElement getDropdownWebLanguage() {
        return field(dropdownWebLanguageXpath);
    }

    public SelenideElement getInputConfigureWebAuth() {
        return field(inputConfigureWebAuthXpath);
    }

    public SelenideElement getInputWebUser() {
        return field(inputWebUserXpath);
    }

    public SelenideElement getInputWebPassword() {
        return field(inputWebPasswordXpath);
    }

    public SelenideElement getDropdownFunctionByNumber() {
        return field(dropdownFunctionByNumberXpath);
    }

    public SelenideElement getInputDestinationNumberByNumber() {
        return field(inputDestinationNumberByNumberXpath);
    }

    public SelenideElement getInputDisplayNameByNumber() {
        return field(inputDisplayNameByNumberXpath);
    }
    //</editor-fold>
}
