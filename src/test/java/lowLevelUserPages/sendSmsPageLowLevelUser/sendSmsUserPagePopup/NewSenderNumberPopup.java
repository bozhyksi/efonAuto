package lowLevelUserPages.sendSmsPageLowLevelUser.sendSmsUserPagePopup;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lowLevelUserPages.sendSmsPageLowLevelUser.ManageSenderNumbersAndNamesUserPage;

public class NewSenderNumberPopup extends ManageSenderNumbersAndNamesUserPage {
    //<editor-fold desc="locators">
    private String inputMobileNumberXpath = "//input[@formcontrolname=\"mobileNumber\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getInputMobileNumber() {
        return field(inputMobileNumberXpath);
    }

    @Override
    public SelenideElement getButtonSave() {
        return super.getButtonSave();
    }
    //</editor-fold>

    @Step("Enter mobile number")
    public NewSenderNumberPopup enterMobileNumber(String num){
        getInputMobileNumber().setValue(num);
        return this;
    }

    @Step("Save")
    public ManageSenderNumbersAndNamesUserPage save(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return new ManageSenderNumbersAndNamesUserPage();
    }

}
