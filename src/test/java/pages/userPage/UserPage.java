package pages.userPage;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;
import tests.userPageTests.userPageTestData.User;

import javax.jws.soap.SOAPBinding;

public class UserPage extends BasePage {
    //<editor-fold desc="//-- UserPage Locators --// ">
    private String pageTitleXpath = "//h1";
    private String inputSearchXpath = "//h3[text()='Search']//following-sibling::input[(contains(@placeholder,'Name, abbreviated'))]";
    private String buttonCreateNewUserXpath = "//a[@role=\"button\"][text()='Create new user']";
    private String listUserNamesXpath = "//*[@role=\"grid\"]//td[1]";
    private String listNumbersXpath = "//*[@role=\"grid\"]//td[2]";
    private String listEndDevicesXpath = "//*[@role=\"grid\"]//td[3]/a";
    private String buttonBlockUserXpath = "//*[@role=\"grid\"]//a[@id=\"blockUserButton\"]";
    private String buttonConfigUserXpath = "//*[@role=\"grid\"]//a[@id=\"editUserButton\"]";
    private String buttonDeleteUserXpath = "//*[@role=\"grid\"]//a[@id=\"deleteUserButton\"]";
    private String buttonConfigUserByNameXpath = "//*[@role=\"grid\"]//td[1][contains(text(),\"%s\")]//ancestor::tr//a[@id=\"editUserButton\"]";
    private String buttonDeleteUserByNameXpath = "//*[@role=\"grid\"]//td[1][contains(text(),\"%s\")]//ancestor::tr//a[@id=\"deleteUserButton\"]";
    //</editor-fold>

    //<editor-fold desc="//-- UserPage get/set methods --//">
    public SelenideElement getButtonDeleteUserByName(String name) {
        return field(String.format(buttonDeleteUserByNameXpath, name));
    }

    public SelenideElement getButtonConfigUserByName(String name) {
        return field(String.format(buttonConfigUserByNameXpath, name));
    }

    public SelenideElement getButtonDeleteUser() {
        return field(buttonDeleteUserXpath);
    }

    public SelenideElement getButtonConfigUser() {
        return field(buttonConfigUserXpath);
    }

    public SelenideElement getButtonBlockUser() {
        return field(buttonBlockUserXpath);
    }

    public ElementsCollection getListEndDevices() {
        return fields(listEndDevicesXpath);
    }

    public ElementsCollection getListNumbers() {
        return fields(listNumbersXpath);
    }

    public ElementsCollection getListUserNames() {
        return fields(listUserNamesXpath);
    }

    public ElementsCollection getListButtonDeleteUser(){
        return fields(buttonDeleteUserXpath);
    }

    public SelenideElement getButtonCreateNewUser() {
        return field(buttonCreateNewUserXpath);
    }

    public SelenideElement getInputSearch() {
        return field(inputSearchXpath);
    }

    public SelenideElement getPageTitle() {
        return field(pageTitleXpath);
    }
    //</editor-fold>

    public void checkPageTitle(String val){
        getPageTitle().getText().equals(val);
    }

    public void checkIfUserExistsInTheList(User user){
        getListUserNames().filterBy(Condition.text(user.getFullName())).shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

    public void checkIfUserDeleted(User user){
        getListUserNames().filterBy(Condition.text(user.getFullName())).shouldHave(CollectionCondition.size(0));
    }

    public void deleteUserButtonClick(String userName){
        getChildByParentName(getListUserNames(),getListButtonDeleteUser(),userName).click();
    }

    public void openEditUserPopup(User user){
        getButtonConfigUserByName(user.getFirstName()).click();
        waitUntilAlertDisappear();
    }

}
