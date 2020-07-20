package pages.phonebookPage;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.basePage.BasePage;
import tests.phonebookPageTests.phonebookPageTestData.Phonebook;

import java.io.File;

import static core.configuration.preparations.eFonApp.excelFileWorker;
import static io.qameta.allure.Allure.step;

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

    @Step("Verify page title")
    public PhonebookPage validatePageTitle(String excpected){
        getPageTitle().getText().contains(excpected);
        return this;
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

    @Step("Download example file")
    public PhonebookPage downloadExample(){
        getButtonDownloadExample().click();
        Selenide.sleep(3000);
        return this;
    }

    @Step("Verify if file downloaded")
    public PhonebookPage verifyExampleFileDownloaded(){
        Assert.assertTrue(excelFileWorker.checkIfFileExists("excelimport_phonebook_example.xls"),
                "Example file not found");
        return this;
    }

    @Step("Delete downloaded example file")
    public PhonebookPage deleteDownloadedExampleFile(){
        excelFileWorker.deleteFile("excelimport_phonebook_example.xls");
        return this;
    }
}
