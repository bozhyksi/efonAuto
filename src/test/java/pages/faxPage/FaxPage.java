package pages.faxPage;

import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;

public class FaxPage extends BasePage {
    //<editor-fold desc="//-- Locators --//">
    private String dropdownSelectNumberXpath = "//h3[text()=\"Select number\"]//following-sibling::select";
    private String editButtonXpath = "//a[@name=\"editLink\"]|//div[text()=\"Fax2Email is not configured for this phone number\"]//following-sibling::div/a[@class=\"icon-link\"]";
    private String inputEmail = "//input[@formcontrolname=\"fax2emailEmail\"]";
    private String radioTiffAndPdfXpath = "//span[text()=\"TIFF and PDF\"]//preceding-sibling::input[@formcontrolname=\"fax2emailFormat\"][1]";
    private String radioPdfOnlyXpath = "//span[text()=\"PDF only\"]//preceding-sibling::input[@formcontrolname=\"fax2emailFormat\"][1]";
    private String radioTiffOnlyXpath = "//span[text()=\"TIFF only\"]//preceding-sibling::input[@formcontrolname=\"fax2emailFormat\"][1]";
    private String buttonSaveXpath= "//button[text()=\"Save\"]";
    private String buttonCancelXpath = "//button[text()=\"Cancel\"]";
    private String checkboxTiffAndPDFXpath = "//span[text()=\"TIFF and PDF\"]//preceding-sibling::input[@formcontrolname=\"fax2emailFormat\"]";
    //</editor-fold>

    //<editor-fold desc="//-- get\set --//">

    public SelenideElement getCheckboxTiffAndPDF() {
        return field(checkboxTiffAndPDFXpath);
    }

    public SelenideElement getDropdownSelectNumber() {
        return field(dropdownSelectNumberXpath);
    }

    public SelenideElement getEditButton() {
        return field(editButtonXpath);
    }

    public SelenideElement getInputEmail() {
        return field(inputEmail);
    }

    public SelenideElement getRadioTiffAndPdf() {
        return field(radioTiffAndPdfXpath);
    }

    public SelenideElement getRadioPdfOnly() {
        return field(radioPdfOnlyXpath);
    }

    public SelenideElement getRadioTiffOnly() {
        return field(radioTiffOnlyXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getButtonCancel() {
        return field(buttonCancelXpath);
    }
    //</editor-fold>
}
