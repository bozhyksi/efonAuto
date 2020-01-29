package pages.userPage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;

public class UserPage extends BasePage {

    //<editor-fold desc="//-- UserPage Locators --// ">
    private String pageTitleXpath = "//h1";
    private String inputSearchXpath = "//h3[text()='Search']//following-sibling::input[(contains(@placeholder,'Name, abbreviated'))]";
    private String buttonCreateNewUserXpath = "//a[@role=\"button\"][text()='Create new user']";
    private String listUserNamesXpath = "//*[@role=\"grid\"]//td[1]";
    private String listNumbersXpath = "//*[@role=\"grid\"]//td[2]";
    private String listEndDevicesXpath = "//*[@role=\"grid\"]//td[3]/a";
    private String buttonBlockUserXpath = "//*[@role=\"grid\"]//td[4]/a[1]";
    private String buttonConfigUserXpath = "//*[@role=\"grid\"]//td[4]/a[2]";
    private String buttonDeleteUserXpath = "//*[@role=\"grid\"]//td[4]/a[3]";
    //</editor-fold>

    //<editor-fold desc="//-- UserPage get/set methods --//">
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



}
