package pages.abbreviatedDialling;

import com.codeborne.selenide.*;
import pages.basePage.basePopup.ConfirmationPopup;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDiallingTestData;

import java.util.HashMap;

public class AbbreviatedNumbers extends AbbreviatedDiallingBasePage {
    //<editor-fold desc="//--  AbbreviatedNumbers locators --//">
    private String inputSearchXpath = "//h3[text()='Search']//following-sibling::input[(contains(@placeholder,'Company'))]";
    private String listNoXpath = "//table[@role=\"grid\"]//td[1]";
    private String listCompanyXPath = "//table[@role=\"grid\"]//td[2]";
    private String listLastNameXPath = "//table[@role=\"grid\"]//td[3]";
    private String listFirstNameXPath = "//table[@role=\"grid\"]//td[4]";
    private String toggleBlfPickUpXPath = "//table[@role=\"grid\"]//td[5]/button";
    private String buttonEditXPath = "(//table[@role=\"grid\"]//td[5]/a)[1]";
    private String buttonDeleteXpath = "(//table[@role=\"grid\"]//td[5]/a)[2]";
    //</editor-fold>

    //<editor-fold desc="//-- AbbreviatedNumbers get\set methods --//">
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

    public ElementsCollection getListButtonEdit() {
        return fields(buttonEditXPath);
    }

    public ElementsCollection getListButtonDelete() {
        return fields(buttonDeleteXpath);
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
        getChildByParentName(getListNo(),getListButtonDelete(), shortNum).click();
    }

    public void checkIfAbbrevNumberRangeCreated(AbbreviatedDiallingTestData obj){
        getDropdownItemsPerPage().selectOptionContainingText("All");
        getListNo().shouldHave(CollectionCondition.size(obj.getShortNumbersArray().size()));
    }

    public void deleteAllShortNumbers(ConfirmationPopup confirmationPopup){
        for (SelenideElement elem: getListButtonDelete()) {
            elem.click();
            confirmationPopup.getYesButton().click();
            Selenide.sleep(500);
        }
    }

    public void editSingleAbbrevNumber(String shortNum){
        getChildByParentName(getListNo(),getListButtonEdit(), shortNum).click();
    }

    public void checkIfExternalUserInfoIsDisplayedInTheAbbreviatedDiallingGrid(HashMap<String,String> dat){
        getListLastName().shouldHave(CollectionCondition.sizeGreaterThan(0)).shouldHave(CollectionCondition.texts(dat.get("lastName")));
        getListFirstName().shouldHave(CollectionCondition.sizeGreaterThan(0)).shouldHave(CollectionCondition.texts(dat.get("firstName")));
        getListCompany().shouldHave(CollectionCondition.sizeGreaterThan(0)).shouldHave(CollectionCondition.texts(dat.get("company")));
    }
}
