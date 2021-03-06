package pages.abbreviatedDialling;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.abbreviatedDialling.abbreviatedDiallingPopup.AssignAbbreviatedDialling;
import pages.abbreviatedDialling.abbreviatedDiallingPopup.SecretaryPopup;
import pages.basePage.basePopup.ConfirmationPopup;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;
import tests.userPageTests.userPageTestData.User;

import javax.jws.soap.SOAPBinding;
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
        goToMenuTab(MenuTabsBasePage.ABBREVIATED_DIALING)
                .goToMenuTab(MenuTabsBasePage.ABBREVIATED_NUMBERS);
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

    @Step("Make short number unused")
    public AbbreviatedNumbers makeShortNumberUnused(AbbreviatedDialling shortNumber){
        clickEditSingleShortNumber(shortNumber);
        new AssignAbbreviatedDialling()
                .setShortNumberUnused();
        return this;
    }

    @Step("Edit single short number, open edit popup")
    public AssignAbbreviatedDialling clickEditSingleShortNumber(AbbreviatedDialling shortNumber){
        getButtonEditByNum(shortNumber.getSingleShortNum()).click();
        waitUntilAlertDisappear();
        return new AssignAbbreviatedDialling();
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

    @Step("Activate BLF pickup")
    public AbbreviatedNumbers activateBLF(AbbreviatedDialling shortNumber){
        getCheckboxBlfPickupByText(shortNumber.getSingleShortNum()).click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Verify if BLF is active")
    public AbbreviatedNumbers verifyBlfActive(AbbreviatedDialling shortNum){
        getCheckboxBlfPickupByText(shortNum.getSingleShortNum())
                .shouldHave(attribute("aria-pressed","true"));
        return this;
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

    public boolean checkIfDeleteButtonExist(AbbreviatedDialling shortNumber){
        return getButtonDeleteByNum(shortNumber.getSingleShortNum()).exists();
    }

    @Step("Check if short dial was assign to the user and user's info is showed in the grid")
    public AbbreviatedNumbers validateInternalUserShorDial(User user){
        field(String.format("//table//td[text()=\"%s\"]",user.getFullName())).should(exist);
        return this;
    }

    @Step("Check if External user info is displayed in the Abbreviated dialling grid")
    public AbbreviatedNumbers validateExtNumber(AbbreviatedDialling shortNum){
        checkIfExternalUserInfoIsDisplayedInTheAbbreviatedDiallingGrid(shortNum);
        return this;
    }

    @Step("Click edit secretary")
    public SecretaryPopup clickEditSecretary(AbbreviatedDialling shortNum){
        getButtonSecretaryByText(shortNum.getSingleShortNum()).click();
        waitUntilAlertDisappear();
        return new SecretaryPopup();
    }
}
