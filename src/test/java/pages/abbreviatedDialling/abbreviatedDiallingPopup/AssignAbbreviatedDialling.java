package pages.abbreviatedDialling.abbreviatedDiallingPopup;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.abbreviatedDialling.AbbreviatedNumbers;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;
import tests.userPageTests.userPageTestData.User;

import static io.qameta.allure.Allure.step;

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


    @Step ("Set short number unused")
    public AssignAbbreviatedDialling setShortNumberUnused(){
        getRadioUnused().click();
        getButtonSave().click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step ("Set short number to internal user ")
    public AbbreviatedNumbers setShorNumToInternalUser(User user){
        getRadioInternalUser().click();
        getDropdrownSelectUser().selectOptionContainingText(user.getLastName());
        getCheckboxForwardAsExternal().click();
        getButtonSave().click();
        waitUntilAlertDisappear();
        return new AbbreviatedNumbers();
    }

    @Step("Set short number to external user ")
    public AbbreviatedNumbers setShorNumToExternalUser(AbbreviatedDialling shortNum) {
        getRadioExternalNumber().click();
        getInputExternalNumber().setValue(shortNum.getExtPhoneNum());
        getInputLastName().setValue(shortNum.getLastName());
        getInputFirstName().setValue(shortNum.getFirstName());
        getInputCompany().setValue(shortNum.getCompany());
        getButtonSave().click();
        waitUntilAlertDisappear();
        return new AbbreviatedNumbers();
    }
}
