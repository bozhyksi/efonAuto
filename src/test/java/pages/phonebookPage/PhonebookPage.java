package pages.phonebookPage;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;

import java.io.File;

public class PhonebookPage extends BasePage {
    //<editor-fold desc="//-- PhonebookPage locators--//">
    private String pageTitleXpath = "//my-app//h1";
    private String buttonDownloadExampleXpath = "//a[text()=\"Download example\"]";
    private String buttonUploadFileXpath = "//input[@name=\"file\"][@type=\"file\"]";
    private String buttonRestoreXpath = "//a[text()=\"Restore\"]";
    private String listNumbersXpath = "//table[@role=\"grid\"]//td[1]";
    private String listNamesPath = "//table[@role=\"grid\"]//td[2]";
    private String listShortDialsPath = "//table[@role=\"grid\"]//td[3]";
    private String buttonDownloadListXpath = "//a[text()=\"Download list\"]";
    private String buttonDeletePhoneBookXpath = "//a[text()=\"Delete phonebook\"]";
    //</editor-fold>

    //<editor-fold desc="//-- PhonebookPage get\set methods --//">
    public SelenideElement getPageTitle() {
        return field(pageTitleXpath);
    }

    public SelenideElement getButtonDownloadExample() {
        return field(buttonDownloadExampleXpath);
    }

    public SelenideElement getButtonUploadFile() {
        return field(buttonUploadFileXpath);
    }

    public SelenideElement getButtonRestore() {
        return field(buttonRestoreXpath);
    }

    public ElementsCollection getListNumbers() {
        return fields(listNumbersXpath);
    }

    public ElementsCollection getListNames() {
        return fields(listNamesPath);
    }

    public ElementsCollection getListShortDials() {
        return fields(listShortDialsPath);
    }

    public SelenideElement getButtonDownloadList() {
        return field(buttonDownloadListXpath);
    }

    public SelenideElement getButtonDeletePhoneBook() {
        return field(buttonDeletePhoneBookXpath);
    }
    //</editor-fold>

    public void validatePageTitle(String excpected){
        getPageTitle().getText().contains(excpected);
    }

    public void uploadFile(String fileName){
        getButtonUploadFile().uploadFile(new File(fileName));
    }

    public void validateUploadedNumbers(int number){
        getListNumbers().shouldHave(CollectionCondition.size(number));
    }

    public void checkIfPhonebookWasDeleted(){
        getListNumbers().shouldHave(CollectionCondition.size(1)).shouldHave(CollectionCondition.texts("No Items"));
    }

    public void downloadPhonebook(){
        getButtonDownloadList().click();
        Selenide.sleep(3000);
    }

    public void downloadExample(){
        getButtonDownloadExample().click();
        Selenide.sleep(3000);
    }
}
