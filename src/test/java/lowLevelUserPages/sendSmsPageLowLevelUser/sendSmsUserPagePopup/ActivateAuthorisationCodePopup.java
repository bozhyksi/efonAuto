package lowLevelUserPages.sendSmsPageLowLevelUser.sendSmsUserPagePopup;

import com.codeborne.selenide.SelenideElement;
import core.workers.dbWorker.DataBaseWorker;
import io.qameta.allure.Step;
import lowLevelUserPages.sendSmsPageLowLevelUser.ManageSenderNumbersAndNamesUserPage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivateAuthorisationCodePopup extends ManageSenderNumbersAndNamesUserPage {
    //<editor-fold desc="locators">
    private String inputAuthorizationCodeXpath = "//input[@formcontrolname=\"code\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    @Override
    public SelenideElement getButtonSave() {
        return super.getButtonSave();
    }

    public SelenideElement getInputAuthorizationCode() {
        return field(inputAuthorizationCodeXpath);
    }
    //</editor-fold>

    @Step("Enter authorization code")
    public ActivateAuthorisationCodePopup enterAuthorizationCode(String authorizationCode){
        getInputAuthorizationCode().setValue(authorizationCode);
        return this;
    }

    @Step("Save")
    public ManageSenderNumbersAndNamesUserPage save(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return this;
    }


}
