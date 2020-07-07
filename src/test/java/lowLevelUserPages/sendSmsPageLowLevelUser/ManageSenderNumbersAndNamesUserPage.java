package lowLevelUserPages.sendSmsPageLowLevelUser;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import core.workers.dbWorker.DataBaseWorker;
import io.qameta.allure.Step;
import lowLevelUserPages.sendSmsPageLowLevelUser.sendSmsUserPagePopup.ActivateAuthorisationCodePopup;
import lowLevelUserPages.sendSmsPageLowLevelUser.sendSmsUserPagePopup.NewSenderNumberPopup;
import lowLevelUserPages.sendSmsPageLowLevelUser.sendSmsUserPagePopup.SmsConfirmationPopup;
import org.testng.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.codeborne.selenide.Condition.exist;
import static core.configuration.preparations.eFonApp.confirmationPopup;
import static io.qameta.allure.Allure.step;

public class ManageSenderNumbersAndNamesUserPage extends SendSmsBaseUserPage {
    //<editor-fold desc="locators">
    private String buttonAddXpath = "//a[text()=\"Add\"]";
    private String buttonCreateNewCodeByTextXpath = "//td[contains(text(),\"%s\")]/..//a[@id=\"generateNewCode\"]";
    private String buttonEditByTextXpath = "//td[contains(text(),\"%s\")]/..//i[contains(@class,\"fa-cog\")]/..";
    private String buttonDeleteByTextXpath = "//td[contains(text(),\"%s\")]/..//i[contains(@class,\"fa-trash\")]/..";
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

    @Step("Verify non-authorized number exists")
    public ManageSenderNumbersAndNamesUserPage verifyNonAuthorizedNumberExists(String number){
        getFiledMobileNumberByText(number).should(exist);
        getFieldNonAuthorizedByText(number).should(exist);
        return this;
    }

    @Step("Verify Authorized number exists")
    public ManageSenderNumbersAndNamesUserPage verifyAuthorizedNumberExists(String number){
        getFiledMobileNumberByText(number).should(exist);
        getFieldAuthorizedByText(number).should(exist);
        return this;
    }

    @Step("Click add")
    public NewSenderNumberPopup clickAdd(){
        getButtonAdd().click();
        waitUntilAlertDisappear();
        return new NewSenderNumberPopup();
    }

    @Step("Click edit")
    public ActivateAuthorisationCodePopup clickEdit(String senderNumber){
        getButtonEditByText(senderNumber).click();
        waitUntilAlertDisappear();
        return new ActivateAuthorisationCodePopup();
    }

    @Step("Delete test sender number")
    public ManageSenderNumbersAndNamesUserPage deleteSenderNumber(String senderNumber){
        getButtonDeleteByText(senderNumber).click();
        waitUntilAlertDisappear();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        getFiledMobileNumberByText(senderNumber).shouldNot(exist);
        refreshPage();
        getFiledMobileNumberByText(senderNumber).shouldNot(exist);
        return this;
    }

    @Step("Click create new code")
    public ManageSenderNumbersAndNamesUserPage clickCreateNewCode(String senderNumber){
        getButtonCreateNewCodeByText(senderNumber).click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        return this;
    }
}
