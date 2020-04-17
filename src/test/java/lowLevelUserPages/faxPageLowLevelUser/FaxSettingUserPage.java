package lowLevelUserPages.faxPageLowLevelUser;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import testsLowLevelUser.faxUserPageTests.faxUserPageTestData.Fax2EmailSettingsTestData;

import static com.codeborne.selenide.Condition.*;

public class FaxSettingUserPage extends FaxesBaseUserPage {
    //<editor-fold desc="locators">
    private String inputEmail = "//input[@formcontrolname=\"fax2emailEmail\"]";
    private String dropdownSelectNumberXpath = "//h3[text()=\"Select number\"]//following-sibling::select";
    private String editButtonXpath = "//a[@name=\"editLink\"]|//div[text()=\"Fax2Email is not configured for this phone number\"]//following-sibling::div/a[@class=\"icon-link\"]";
    private String checkboxTiffAndPDFXpath = "//span[text()=\"TIFF and PDF\"]//preceding-sibling::input[@formcontrolname=\"fax2emailFormat\"]";
    private String buttonSaveXpath= "//button[text()=\"Save\"]";
    private String buttonCancelXpath = "//button[text()=\"Cancel\"]";
    private String checkboxFormatByNameXapth = "//span[text()=\"%s\"]//preceding-sibling::input[@formcontrolname=\"fax2emailFormat\"][1]";

    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getCheckboxFormatByName(String name) {
        return field(String.format(checkboxFormatByNameXapth, name));
    }

    public SelenideElement getInputEmail() {
        return field(inputEmail);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getCheckboxTiffAndPDF() {
        return field(checkboxTiffAndPDFXpath);
    }

    public SelenideElement getEditButton() {
        return field(editButtonXpath);
    }

    public SelenideElement getDropdownSelectNumber() {
        return field(dropdownSelectNumberXpath);
    }
    //</editor-fold>

    public void configureFax2Email(Fax2EmailSettingsTestData fax2EmailSettings){
        getDropdownSelectNumber().selectOptionContainingText(fax2EmailSettings.getNumber());
        getEditButton().click();
        waitUntilAlertDisappear();
        getInputEmail().setValue(fax2EmailSettings.getEmail());
        getCheckboxFormatByName(fax2EmailSettings.getFaxMessageFormat()).click();
        getButtonSave().click();
        waitUntilAlertDisappear();
    }

    public void validateFax2EmailSettings(Fax2EmailSettingsTestData fax2EmailSettings){
        getDropdownSelectNumber().selectOptionContainingText(fax2EmailSettings.getNumber());
        getEditButton().click();
        waitUntilAlertDisappear();
        getInputEmail().shouldHave(value(fax2EmailSettings.getEmail()));
        getCheckboxFormatByName(fax2EmailSettings.getFaxMessageFormat()).shouldBe(selected);
    }
}
