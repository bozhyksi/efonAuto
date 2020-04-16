package lowLevelUserPages.sendSmsPageLowLevelUser;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import core.workers.dbWorker.DataBaseWorker;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.codeborne.selenide.Condition.exist;

public class ManageSenderNumbersAndNamesUserPage extends SendSmsBaseUserPage {
    //<editor-fold desc="locators">
    private String buttonAddXpath = "//a[text()=\"Add\"]";
    private String buttonCreateNewCodeByTextXpath = "//td[contains(text(),\"%s\")]/..//a[@id=\"generateNewCode\"]";
    private String buttonEditByTextXpath = "//td[contains(text(),\"%s\")]/..//i[contains(@class,\"fa-cog\")]";
    private String buttonDeleteByTextXpath = "//td[contains(text(),\"%s\")]/..//i[contains(@class,\"fa-trash\")]";
    private String filedMobileNumberByTextXpath = "//td[contains(text(),\"%s\")]";
    private String fieldAuthorizedByTextXpath = "//td[contains(text(),\"%s\")]/..//i[contains(@class,\"fa-check\")]";
    private String fieldNonAuthorizedByTextXpath = "//td[contains(text(),\"%s\")]/..//i[contains(@class,\"fa-times\")]";
    //</editor-fold>

    //<editor-fold desc="get\set">


    public SelenideElement getFieldNonAuthorizedByText(String text) {
        return field(String.format(fieldNonAuthorizedByTextXpath, text));
    }

    public SelenideElement getFieldAuthorizedByText(String text) {
        return field(String.format(fieldAuthorizedByTextXpath, text));
    }

    public SelenideElement getButtonAdd() {
        return field(buttonAddXpath);
    }

    public SelenideElement getButtonCreateNewCodeByText(String text) {
        return field(String.format(buttonCreateNewCodeByTextXpath, text));
    }

    public SelenideElement getButtonEditByText(String text) {
        return field(String.format(buttonEditByTextXpath,text));
    }

    public SelenideElement getButtonDeleteByText(String text) {
        return field(String.format(buttonDeleteByTextXpath, text));
    }

    public SelenideElement getFiledMobileNumberByText(String text) {
        return field(String.format(filedMobileNumberByTextXpath, text));
    }
    //</editor-fold>

    public void verifyIfNumberAddedAsNonAuthorized(String number){
        getFiledMobileNumberByText(number).should(exist);
        getFieldNonAuthorizedByText(number).should(exist);
    }

    public void verifyIfNumberAddedAsAuthorized(String number){
        getFiledMobileNumberByText(number).should(exist);
        getFieldAuthorizedByText(number).should(exist);
    }

}
