package pages.userPage;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.basePage.BasePage;
import pages.userPage.userPagePopup.CreateUserPopup;
import pages.userPage.userPagePopup.configureUser.ConfigureUserBasePopup;
import tests.userPageTests.userPageTestData.User;

import javax.jws.soap.SOAPBinding;

import static com.codeborne.selenide.Condition.exist;
import static core.configuration.preparations.eFonApp.confirmationPopup;
import static pages.basePage.BasePage.MenuTabsBasePage.USER;

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
    private final String getFieldByText = "//table//td[contains(text(),\"%s\")]";
    //</editor-fold>

    //<editor-fold desc="//-- UserPage get/set methods --//">

    public SelenideElement getGetFieldUserNumberByText(String text) {
        return field(String.format(getFieldByText, text));
    }

    public SelenideElement getGetFieldUserNameByText(String text) {
        return field(String.format(getFieldByText, text));
    }

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

    public void checkIfUserExistsInTheList(User user){
        getListUserNames().filterBy(Condition.text(user.getFullName())).shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

    public void checkIfUserDeleted(User user){
        getListUserNames().filterBy(Condition.text(user.getFullName())).shouldHave(CollectionCondition.size(0));
    }

    public void deleteUserButtonClick(String userName){
        getChildByParentName(getListUserNames(),getListButtonDeleteUser(),userName).click();
    }

    @Step("Open Edit user popup")
    public ConfigureUserBasePopup clickEditUser(User user){
        getButtonConfigUserByName(user.getFirstName()).click();
        waitUntilAlertDisappear();
        return new ConfigureUserBasePopup();
    }

    @Step("Open Edit user popup")
    public ConfigureUserBasePopup clickEditUser(String user){
        getButtonConfigUserByName(user).click();
        waitUntilAlertDisappear();
        return new ConfigureUserBasePopup();
    }

    @Step("Open Edit user popup")
    public ConfigureUserBasePopup editUser(String user){
        getButtonConfigUserByName(user).click();
        waitUntilAlertDisappear();
        return new ConfigureUserBasePopup();
    }

    @Step("Click create new user button")
    public CreateUserPopup clickCreateNewUserButton(){
        getButtonCreateNewUser().click();
        waitUntilAlertDisappear();
        return new CreateUserPopup();
    }

    @Step("Create user")
    public UserPage createUser(User ... users){
        for (User user : users) {
            clickCreateNewUserButton()
                    .selectTitle(user.getTitle())
                    .fillFirstName(user.getFirstName())
                    .fillLastName(user.getLastName())
                    .fillLoginEmail(user.getLoginEmail())
                    .selectNumber(user.getPhoneNumber())
                    .selectEndDevices(user.getEndDevices())
                    .saveChanges();
            waitUntilAlertDisappear();
        }
        return this;
    }

    @Step("Delete users")
    public UserPage deleteUser(User ... users){
        for (User user : users) {
            getButtonDeleteUserByName(user.getFirstName()).click();
            waitUntilAlertDisappear();
            confirmationPopup.getYesButton().click();
            waitUntilAlertDisappear();
            verifyIfUserDoesNotExist(user.getFirstName());
        }
        return  this;
    }

    @Step("Verify if user does not exist in the list")
    public UserPage verifyIfUserDoesNotExist(String userName){
        getGetFieldUserNameByText(userName).shouldNot(exist);
        return this;
    }

    @Step("Verify if user exists in the list")
    public UserPage verifyIfUserExists(String userName){
        getGetFieldUserNameByText(userName).should(exist);
        return this;
    }
}
