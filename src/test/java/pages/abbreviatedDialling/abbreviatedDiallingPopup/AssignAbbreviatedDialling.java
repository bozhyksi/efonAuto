package pages.abbreviatedDialling.abbreviatedDiallingPopup;

import com.codeborne.selenide.SelenideElement;
import pages.abbreviatedDialling.AbbreviatedNumbers;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;

public class AssignAbbreviatedDialling extends AbbreviatedNumbers {
    //<editor-fold desc="//-- AssignAbbreviatedDialling Locators --//">
    private String popupTitleXpath = "//h2[@class=\"modal-title\"]";
    private String radioInternalUserXpath = "//b[text()='Internal user']/preceding-sibling::input[@formcontrolname=\"type\"]";
    private String dropdrownSelectUserXpath = "//select[@formcontrolname=\"customerId\"]";
    private String checkboxForwardAsExternalXpath = "//input[@formcontrolname=\"internalRedirect\"]";
    private String radioExternalNumberXpath = "//b[text()='External number']/preceding-sibling::input[@formcontrolname=\"type\"]";
    private String inputExternalNumberXpath = "//input[@formcontrolname=\"extNumber\"]";
    private String inputLastNameXpath = "//input[@formcontrolname=\"extLastName\"]";
    private String inputFirstNameXpath = "//input[@formcontrolname=\"extFirstName\"]";
    private String inputCompanyXpath = "//input[@formcontrolname=\"extCompany\"]";
    private String radioUnusedXpath = "//b[text()='Unused']/preceding-sibling::input[@formcontrolname=\"type\"]";
    private String buttonSaveXpath = "//button[text()='Save']";
    private String buttonCancelXpath = "//button[text()='Cancel']";
    //</editor-fold>

    //<editor-fold desc="//-- AssignAbbreviatedDialling get\set methods --//">
    public SelenideElement getInputCompany() {
        return field(inputCompanyXpath);
    }

    public SelenideElement getInputFirstName() {
        return field(inputFirstNameXpath);
    }

    public SelenideElement getInputLastName() {
        return field(inputLastNameXpath);
    }

    public SelenideElement getInputExternalNumber() {
        return field(inputExternalNumberXpath);
    }

    public SelenideElement getCheckboxForwardAsExternal() {
        return field(checkboxForwardAsExternalXpath);
    }

    public SelenideElement getDropdrownSelectUser() {
        return field(dropdrownSelectUserXpath);
    }

    public SelenideElement getPopupTitle() {
        return field(popupTitleXpath);
    }

    public SelenideElement getRadioInternalUser() {
        return field(radioInternalUserXpath);
    }

    public SelenideElement getRadioExternalNumber() {
        return field(radioExternalNumberXpath);
    }

    public SelenideElement getRadioUnused() {
        return field(radioUnusedXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getButtonCancel() {
        return field(buttonCancelXpath);
    }
    //</editor-fold>


    public AssignAbbreviatedDialling setShortNumberUnused(){
        getRadioUnused().click();
        getButtonSave().click();
        waitUntilAlertDisappear();
        return this;
    }
}
