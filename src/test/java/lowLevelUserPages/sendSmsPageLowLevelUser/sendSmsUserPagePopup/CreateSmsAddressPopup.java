package lowLevelUserPages.sendSmsPageLowLevelUser.sendSmsUserPagePopup;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lowLevelUserPages.sendSmsPageLowLevelUser.AddressBookUserPage;
import testsLowLevelUser.sendSmsUserPageTests.sendSmsTestData.AddressBookTestData;

public class CreateSmsAddressPopup extends AddressBookUserPage {
    //<editor-fold desc="locators">
    private String inputMobileNumberXpath = "//input[@formcontrolname=\"mobileNumber\"]";
    private String inputFirstNameXpath = "//input[@formcontrolname=\"firstName\"]";
    private String inputLastNameXpath = "//input[@formcontrolname=\"lastName\"]";
    private String inputCompanyXpath = "//input[@formcontrolname=\"company\"]";
    private String buttonSaveXpath = "//config-modal-template//button[text()=\"Save\"]";
    private String buttonCancelXpath = "//config-modal-template//button[text()=\"Cancel\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getInputMobileNumber() {
        return field(inputMobileNumberXpath);
    }

    public SelenideElement getInputFirstName() {
        return field(inputFirstNameXpath);
    }

    public SelenideElement getInputLastName() {
        return field(inputLastNameXpath);
    }

    public SelenideElement getInputCompany() {
        return field(inputCompanyXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getButtonCancel() {
        return field(buttonCancelXpath);
    }
    //</editor-fold>

    @Step("Enter address book data")
    public CreateSmsAddressPopup enterAddressBookRequiredFields(AddressBookTestData addressBook){
        getInputMobileNumber().setValue(addressBook.getMobileNumber());
        getInputFirstName().setValue(addressBook.getFirstName());
        getInputLastName().setValue(addressBook.getLastName());
        getInputCompany().setValue(addressBook.getCompany());
        return this;
    }

    @Step("Save all data")
    public AddressBookUserPage save(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return new AddressBookUserPage();
    }

    @Step("Clear all fields")
    public CreateSmsAddressPopup clearFields(){
        getInputMobileNumber().clear();
        getInputFirstName().clear();
        getInputLastName().clear();
        getInputCompany().clear();
        return this;
    }
}
