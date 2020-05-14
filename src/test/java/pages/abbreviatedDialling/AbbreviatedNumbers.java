package pages.abbreviatedDialling;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.basePage.basePopup.ConfirmationPopup;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.*;

public class AbbreviatedNumbers extends AbbreviatedDiallingBasePage {
    //<editor-fold desc="//--  AbbreviatedNumbers locators --//">
    private String inputSearchXpath = "//h3[text()='Search']//following-sibling::input[(contains(@placeholder,'Company'))]";
    private String listNoXpath = "//table[@role=\"grid\"]//td[1]";
    private String listCompanyXPath = "//table[@role=\"grid\"]//td[2]";
    private String listLastNameXPath = "//table[@role=\"grid\"]//td[3]";
    private String listFirstNameXPath = "//table[@role=\"grid\"]//td[4]";
    private String toggleBlfPickUpXPath = "//table[@role=\"grid\"]//td[5]/button";
    private String buttonEditByTextXPath = "//table//td[text()=\"%s\"]//parent::tr//a[@id=\"editShortDial\"]";
    private String buttonDeleteByTextXpath = "//table//td[text()=\"%s\"]//parent::tr//a[@id=\"deleteShortDial\"]";
    private String fieldNumberByTextXpath = "//table//td[1][text()=\"%s\"]";
    private String fieldCompanyByTextXpath = "//table//td[2][text()=\"%s\"]";
    private String fieldLastNameByTextXPath = "//table//td[3][text()=\"%s\"]";
    private String fieldFirstNameByTextXPath = "//table//td[4][text()=\"%s\"]";
    private String checkboxBlfPickupByTextXpath = "//td[contains(text(),\"%s\")]/..//button[@id=\"toggleBlfPickUpButton\"]";
    private String buttonSecretaryByTextXpath = "//td[contains(text(),\"%s\")]/..//a[@id=\"editShortDialSecretaries\"]";
    private String fieldByTextXpath = "//td[contains(text(),\"%s\")]/..//td[contains(text(),\"%s\")]";

    //</editor-fold>

    //<editor-fold desc="//-- AbbreviatedNumbers get\set methods --//">

    public SelenideElement getLastNameByText(String num, String text) {
        return field(String.format(fieldByTextXpath, num,text));
    }

    public SelenideElement getFirstNameByText(String num,String text) {
        return field(String.format(fieldByTextXpath,num,text));
    }


    public SelenideElement getCompanyByText(String num,String text) {
        return field(String.format(fieldByTextXpath,num,text));
    }

    public SelenideElement getButtonSecretaryByText(String text) {
        return field(String.format(buttonSecretaryByTextXpath, text));
    }

    public SelenideElement getCheckboxBlfPickupByText(String text) {
        return field(String.format(checkboxBlfPickupByTextXpath, text));
    }

    public SelenideElement getFieldFirstNameByText(String name) {
        return field(String.format(fieldFirstNameByTextXPath,name));
    }

    public SelenideElement getFieldLastNameByText(String name) {
        return field(String.format(fieldLastNameByTextXPath,name));
    }

    public SelenideElement getFieldCompanyByText(String name) {
        return field(String.format(fieldCompanyByTextXpath, name));
    }

    public SelenideElement getFieldNumberByText(String num) {
        return field(String.format(fieldNumberByTextXpath,num));
    }

    public SelenideElement getInputSearch() {
        return field(inputSearchXpath);
    }

    public ElementsCollection getListNo() {
        return fields(listNoXpath);
    }

    public ElementsCollection getListCompany() {
        return fields(listCompanyXPath);
    }

    public ElementsCollection getListLastName() {
        return fields(listLastNameXPath);
    }

    public ElementsCollection getListFirstName() {
        return fields(listFirstNameXPath);
    }

    public ElementsCollection getListToggleBlfPickUp() {
        return fields(toggleBlfPickUpXPath);
    }

    public SelenideElement getButtonDeleteByNum(String num){
        return field(String.format(buttonDeleteByTextXpath, num));
    }

    public SelenideElement getButtonEditByNum(String num) {
        return field(String.format(buttonEditByTextXPath,num));
    }

    //</editor-fold>

    public void checkIfSingleAbbrevNumberExistsInList(String shortNum){
        getDropdownItemsPerPage().selectOptionContainingText("All");
        getListNo().filterBy(Condition.text(shortNum)).shouldHave(CollectionCondition.size(1));
    }

    @Step("Check if single short dial exists in the list")
    public AbbreviatedNumbers checkIfSingleAbbrevNumberExistsInList(AbbreviatedDialling shortNum){
        getDropdownItemsPerPage().selectOptionContainingText("All");
        getFieldNumberByText(shortNum.getSingleShortNum()).should(exist);
        return this;
    }

    public void checkIfSingleAbbrevNumberDoesNotExistInList(String shortNum){
        getDropdownItemsPerPage().selectOptionContainingText("All");
        getListNo().filterBy(Condition.text(shortNum)).shouldHave(CollectionCondition.size(0));
    }

    @Step("Verify if single abbreviated number does not exist in the list")
    public AbbreviatedNumbers checkIfSingleAbbrevNumberDoesNotExistInList(AbbreviatedDialling shortNum){
        getDropdownItemsPerPage().selectOptionContainingText("All");
        getFieldNumberByText(shortNum.getSingleShortNum()).shouldNot(exist);
        return this;
    }

    public void deleteSingleAbbrevNumber(String shortNum){
        getButtonDeleteByNum(shortNum).click();
    }

    @Step("Delete single short number")
    public AbbreviatedNumbers deleteSingleAbbrevNumber(AbbreviatedDialling shortNum){
        getButtonDeleteByNum(shortNum.getSingleShortNum()).click();
        waitUntilAlertDisappear();
        new ConfirmationPopup().getYesButton().click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Check if short numbers in range were added")
    public AbbreviatedNumbers checkIfAbbrevNumberRangeCreated(AbbreviatedDialling obj){
        getDropdownItemsPerPage().selectOptionContainingText("All");
        waitUntilAlertDisappear();
        for (String elem : obj.getShortNumbersArray()) {
            getFieldNumberByText(elem).should().exists();
        }
        return this;
    }

    public void editSingleAbbrevNumber(String shortNum){
        getButtonEditByNum(shortNum).click();
    }

    public void checkIfExternalUserInfoIsDisplayedInTheAbbreviatedDiallingGrid(AbbreviatedDialling dat){
        getFieldFirstNameByText(dat.getFirstName()).should().exists();
        getFieldLastNameByText(dat.getLastName()).should().exists();
    }

    public void checkIfAbbrevNumberAssignedToUser(User user, AbbreviatedDialling shortNumber){
        getLastNameByText(shortNumber.getSingleShortNum(),user.getLastName()).should(exist);
    }

    public void activateBLF(AbbreviatedDialling shortNumber){
        getCheckboxBlfPickupByText(shortNumber.getSingleShortNum()).click();
        waitUntilAlertDisappear();
        refreshPage();
        getCheckboxBlfPickupByText(shortNumber.getSingleShortNum()).shouldHave(attribute("aria-pressed","true"));
    }

    private boolean checkAbbrevNumExist(AbbreviatedDialling shortNumber){
        waitUntilAlertDisappear();
        for (SelenideElement entry: getListNo()) {
            if (entry.getText().contains(shortNumber.getSingleShortNum())){
                return true;
            }
        }
        return false;
    }
}
