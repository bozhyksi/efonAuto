package pages.faxPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.basePage.BasePage;
import tests.userPageTests.userPageTestData.User;
import testsLowLevelUser.faxUserPageTests.faxUserPageTestData.Fax2EmailSettingsTestData;

import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.value;

public class FaxPage extends BasePage {

    //<editor-fold desc="//-- Locators --//">
    private String dropdownSelectNumberXpath = "//h3[text()=\"Select number\"]//following-sibling::select";
    private String editButtonXpath = "//a[@name=\"editLink\"]|//div[text()=\"Fax2Email is not configured for this phone number\"]//following-sibling::div/a[@class=\"icon-link\"]";
    private String inputEmail = "//input[@formcontrolname=\"fax2emailEmail\"]";
    private String radioTiffAndPdfXpath = "//input[@formcontrolname=\"fax2emailFormat\"][1]";
    private String radioTiffOnlyXpath = "//input[@formcontrolname=\"fax2emailFormat\"][2]";
    private String radioPdfOnlyXpath = "//input[@formcontrolname=\"fax2emailFormat\"][3]";
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

    @Step("Select user number from \"Select number\" dropdown")
    public FaxPage selectNumber(String number){
        getDropdownSelectNumber().selectOptionContainingText(number);
        return this;
    }

    @Step("Edit Fax2Email section")
    public FaxPage clickEditFax2Email(){
        getEditButton().click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Enter email")
    public FaxPage enterEmail(String email){
        getInputEmail().setValue(email);
        return this;
    }

    @Step("Select fax format")
    public FaxPage selectFaxReceiveFormat(String format){
        switch (format){
            case "TIFF_and_PDF":
                getRadioTiffAndPdf().click();
                break;
            case "PDF":
                getRadioPdfOnly().click();
                break;
            case "TIFF":
                getRadioTiffOnly().click();
                break;
        }
        return this;
    }

    @Step("Save Changes")
    public FaxPage saveChanges(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Validate")
    public FaxPage validateFaxSettings(User user){
        getInputEmail().shouldHave(value(user.getFaxEmail()));
        switch (user.getFaxReceiveFormat()){
            case "TIFF_and_PDF":
                getRadioTiffAndPdf().shouldBe(selected);
                break;
            case "PDF":
                getRadioPdfOnly().shouldBe(selected);
                break;
            case "TIFF":
                getRadioTiffOnly().shouldBe(selected);
                break;
        }
        return this;
    }

    @Step("Validate")
    public FaxPage validateFaxSettings(Fax2EmailSettingsTestData fax2EmailSettings){
        getInputEmail().shouldHave(value(fax2EmailSettings.getEmail()));
        switch (fax2EmailSettings.getFaxMessageFormat()){
            case "TIFF_and_PDF":
                getRadioTiffAndPdf().shouldBe(selected);
                break;
            case "PDF":
                getRadioPdfOnly().shouldBe(selected);
                break;
            case "TIFF":
                getRadioTiffOnly().shouldBe(selected);
                break;
        }
        return this;
    }
}
