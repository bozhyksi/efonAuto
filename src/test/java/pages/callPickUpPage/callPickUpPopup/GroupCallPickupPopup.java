package pages.callPickUpPage.callPickUpPopup;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.callPickUpPage.CallPickUpPage;
import tests.callPickUpPageTests.CallPickUpTestData.CallPickUp;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.value;

public class GroupCallPickupPopup extends CallPickUpPage {
    //<editor-fold desc="locators">
    private final String inputNameXpath = "//input[@formcontrolname=\"name\"]";
    private final String dropdownAbbrevDialXpath = "//select[@formcontrolname=\"internalNumberId\"]";
    private final String dropdownSelectAccounts = "//label[text()=\"Select accounts for call pick-up\"]/following-sibling::div/select";
    private final String fieldSelectedAccountItemByTextXpath = "//new-selected-value//span[text()[contains(.,\"%s\")]]";
    private final String buttonSaveXpath = "//button[text()=\"Save\"]";
    private final String buttonCancelXpath = "//button[text()=\"Cancel\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">


    public SelenideElement getFieldSelectedAccountItemByText(String text) {
        return field(String.format(fieldSelectedAccountItemByTextXpath,text));
    }

    public SelenideElement getButtonCancel() {
        return field(buttonCancelXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getDropdownAbbrevDial() {
        return field(dropdownAbbrevDialXpath);
    }

    public SelenideElement getInputName() {
        return field(inputNameXpath);
    }

    public SelenideElement getDropdownSelectAccounts() {
        return field(dropdownSelectAccounts);
    }
    //</editor-fold>

    @Step("Group for call pick-up set name")
    public GroupCallPickupPopup setName(String name){
        getInputName().setValue(name);
        return this;
    }

    @Step("Group for call pick-up set Abbreviated dialling")
    public GroupCallPickupPopup selectAbbreviateNumber(String shortNumber){
        getDropdownAbbrevDial().selectOptionContainingText(shortNumber);
        return this;
    }

    @Step("Select accounts for call pick-up")
    public GroupCallPickupPopup selectAccountForCallPickup(String account){
        getDropdownSelectAccounts().selectOptionContainingText(account);
        return this;
    }

    @Step("Save changes")
    public GroupCallPickupPopup saveChanges(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Configure group for call pick-up")
    public CallPickUpPage configureGroupForCallPickup(CallPickUp callPickUp){
        setName(callPickUp.getName());
        selectAbbreviateNumber(callPickUp.getAbbreviatedDialling());
        selectAccountForCallPickup(callPickUp.getAccountForCallPickup());
        saveChanges();
        return new CallPickUpPage();
    }

    @Step("Verify Group for call pick-up configuration")
    public CallPickUpPage verifyGroupForCallPickupConfiguration(CallPickUp callPickUp){
        getInputName().shouldHave(value(callPickUp.getName()));
        getDropdownAbbrevDial().getSelectedText().contains(callPickUp.getAbbreviatedDialling());
        getFieldSelectedAccountItemByText(callPickUp.getAccountForCallPickup()).should(exist);
        refreshPage();
        return new CallPickUpPage();
    }


}
