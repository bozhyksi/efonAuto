package pages.abbreviatedDialling;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
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
    private String buttonEditXPath = "//table[@role=\"grid\"]//a[@id=\"editShortDial\"]";
    private String buttonDeleteXpath = "//table[@role=\"grid\"]//a[@id=\"deleteShortDial\"]";
    private String buttonDeleteByNumberXpath = "//table[@role=\"grid\"]//td[1][text()=\"%s\"]//..//a[@id=\"deleteShortDial\"]";
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

    public SelenideElement getButtonDeleteByNum(String Num){
        String xPath = String.format(buttonDeleteByNumberXpath,Num);
        return field(xPath);
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

    public void checkIfAbbrevNumberRangeCreated(AbbreviatedDiallingTestData obj){
        getDropdownItemsPerPage().selectOptionContainingText("All");
        getListNo().shouldHave(CollectionCondition.size(obj.getShortNumbersArray().size()));
    }

    public void editSingleAbbrevNumber(String shortNum){
        getChildByParentName(getListNo(),getListButtonEdit(), shortNum).click();
    }

    public void checkIfExternalUserInfoIsDisplayedInTheAbbreviatedDiallingGrid(HashMap<String,String> dat){
        getListLastName().filterBy(Condition.text(dat.get("lastName"))).shouldHaveSize(1);
        getListFirstName().filterBy(Condition.text(dat.get("firstName"))).shouldHaveSize(1);
        getListCompany().filterBy(Condition.text(dat.get("company"))).shouldHaveSize(1);
    }
}
