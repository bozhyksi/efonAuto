package pages.abbreviatedDialling;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;

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
    //</editor-fold>

    //<editor-fold desc="//-- AbbreviatedNumbers get\set methods --//">
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

    public void checkIfAbbrevNumberExistsInList(String shortNum){
        getDropdownItemsPerPage().selectOptionContainingText("All");
        getListNo().filterBy(Condition.text(shortNum)).shouldHave(CollectionCondition.size(1));
    }

    public void checkIfAbbrevNumberDoesNotExistInList(String shortNum){
        getDropdownItemsPerPage().selectOptionContainingText("All");
        getListNo().filterBy(Condition.text(shortNum)).shouldHave(CollectionCondition.size(0));
    }

    public void deleteSingleAbbrevNumber(String shortNum){
        getButtonDeleteByNum(shortNum).click();
    }

    public void checkIfAbbrevNumberRangeCreated(AbbreviatedDialling obj){
        getDropdownItemsPerPage().selectOptionContainingText("All");
        waitUntilAlertDisappear();
        for (String elem : obj.getShortNumbersArray()) {
            getFieldNumberByText(elem).should().exists();
        };
    }

    public void editSingleAbbrevNumber(String shortNum){
        getButtonEditByNum(shortNum).click();
    }

    public void checkIfExternalUserInfoIsDisplayedInTheAbbreviatedDiallingGrid(AbbreviatedDialling dat){
        getFieldFirstNameByText(dat.getFirstName()).should().exists();
        getFieldLastNameByText(dat.getLastName()).should().exists();
    }
}
