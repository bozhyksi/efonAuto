package pages.fileManagementPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.fileManagementPage.fileManagementPopups.EditFileManagementPopup;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;

import static core.configuration.preparations.eFonApp.confirmationPopup;

public class MusicOnHoldPage extends FileManagementBasePage {
    //<editor-fold desc="Locators">
    private String pageTitleXpath = "//music-on-hold//h1";
    private String fieldNameByTextXpath = "//div[text()[contains(.,\"%s\")]]/..";
    private String buttonEditByNameXpath = "//div[text()[contains(.,\"%s\")]]/../..//a[contains(@id,\"fa-cog\")]";
    private String buttonDeleteByNameXpath = "//div[text()[contains(.,\"%s\")]]/../..//a[contains(@id,\"fa-trash\")]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getPageTitle() {
        return field(pageTitleXpath);
    }

    public SelenideElement getFieldNameByText(String name) {
        return field(String.format(fieldNameByTextXpath,name));
    }

    public SelenideElement getButtonEditByName(String name) {
        return field(String.format(buttonEditByNameXpath,name));
    }

    public SelenideElement getButtonDeleteByName(String name) {
        return field(String.format(buttonDeleteByNameXpath, name));
    }
    //</editor-fold>

    @Step("Check if MOH file was uploaded")
    public MusicOnHoldPage verifyFileUploaded(String fileName){
        getFieldNameByText(fileName).should(Condition.exist);
        return this;
    }

    @Step("Delete uploaded file")
    public MusicOnHoldPage deleteMohFile(String fileName){
        getButtonDeleteByName(fileName).click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        refreshPage();
        return this;
    }

    @Step("Verify if file was deleted and is not shown in the grid")
    public MusicOnHoldPage verifyFileDeleted(String fileName){
        getFieldNameByText(fileName).shouldNot(Condition.exist);
        return this;
    }

    @Step("Click edit button")
    public EditFileManagementPopup clickEdit(FileManagementTestData moh){
        getButtonEditByName(moh.getFileName()).click();
        return new EditFileManagementPopup();
    }

}
