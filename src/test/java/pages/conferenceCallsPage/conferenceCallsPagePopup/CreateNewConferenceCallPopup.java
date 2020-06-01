package pages.conferenceCallsPage.conferenceCallsPagePopup;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.conferenceCallsPage.ConferenceCallsPage;

public class CreateNewConferenceCallPopup extends ConferenceCallsPage {

    //<editor-fold desc="Locators">
    private String inputNameXpath = "//form//input[@formcontrolname=\"name\"]";
    private String dropdownConferenceCallNumXpath = "//form//select[@formcontrolname=\"numberId\"]";
    private String inputPinXpath = "//form//input[@formcontrolname=\"pin\"]";
    private String dropdownLanguageXpath = "//form//select[@formcontrolname=\"language\"]";
    private String buttonSave = "//div[@class=\"modal-content\"]//button[text()=\"Save\"]";
    private String buttonCancel = "//div[@class=\"modal-content\"]//button[text()=\"Cancel\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getInputName() {
        return field(inputNameXpath);
    }

    public SelenideElement getDropdownConferenceCallNum() {
        return field(dropdownConferenceCallNumXpath);
    }

    public SelenideElement getInputPin() {
        return field(inputPinXpath);
    }

    public SelenideElement getDropdownLanguage() {
        return field(dropdownLanguageXpath);
    }

    @Override
    public SelenideElement getButtonSave() {
        return field(buttonSave);
    }

    public SelenideElement getButtonCancel() {
        return field(buttonCancel);
    }
    //</editor-fold>

    @Step("Enter conference call name")
    public CreateNewConferenceCallPopup enterName(String name){
        getInputName().setValue(name);
        return this;
    }

    @Step("Select conference call number")
    public CreateNewConferenceCallPopup selectNumber(String number){
        getDropdownConferenceCallNum().selectOptionContainingText(number);
        return this;
    }

    @Step("Enter conference call PIN")
    public CreateNewConferenceCallPopup enterPIN(String pin){
        getInputPin().setValue(pin);
        return this;
    }

    @Step("Select conference call language")
    public CreateNewConferenceCallPopup selectLanguage(String lang){
        getDropdownLanguage().selectOptionByValue(lang);
        return this;
    }

    @Step("Save changes")
    public ConferenceCallsPage saveChanges(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return new ConferenceCallsPage();
    }

}
