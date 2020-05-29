package pages.provisioningPage.provisioningPopups.provisioningSettingsPopup;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import tests.provisioningPageTests.provisioningTestData.PhoneModelTestData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;

public class ProvisioningSettingTab extends ProvisioningSettingsPopup {

    //<editor-fold desc="locators">
    private final String dropdownLanguageXpath = "//select[@formcontrolname=\"languageCode\"]";
    private final String dropdownWebLanguageXpath = "//select[@formcontrolname=\"webLanguageCode\"]";
    private final String inputConfigureWebAuthXpath = "//input[@formcontrolname=\"isWebAuthCustom\"]";
    private final String inputWebUserXpath = "//input[@formcontrolname=\"webUsername\"]";
    private final String inputWebPasswordXpath = "//input[@formcontrolname=\"webPassword\"]";
    private final String dropdownFunctionByNumberXpath = "//td[contains(text(),\" %s\")]/..//select[@formcontrolname=\"type\"]";
    private final String inputDestinationNumberByNumberXpath = "//td[contains(text(),\" %s\")]/..//input[@formcontrolname=\"destinationNumber\"]";
    private final String inputDisplayNameByNumberXpath = "//td[contains(text(),\" %s\")]/..//input[@formcontrolname=\"displayName\"]";
    private final String checkboxIsFixedByText = "//td[contains(text(),\" %s\")]/..//input[@formcontrolname=\"isFixed\"]";
    //</editor-fold>


    //<editor-fold desc="get\set">


    public SelenideElement getCheckboxIsFixedByText(String text) {
        return field(String.format(checkboxIsFixedByText,text));
    }

    public SelenideElement getDropdownLanguage() {
        return field(dropdownLanguageXpath);
    }

    public SelenideElement getDropdownWebLanguage() {
        return field(dropdownWebLanguageXpath);
    }

    public SelenideElement getInputConfigureWebAuth() {
        return field(inputConfigureWebAuthXpath);
    }

    public SelenideElement getInputWebUser() {
        return field(inputWebUserXpath);
    }

    public SelenideElement getInputWebPassword() {
        return field(inputWebPasswordXpath);
    }

    public SelenideElement getDropdownFunctionByNumber(String text) {
        return field(String.format(dropdownFunctionByNumberXpath, text));
    }

    public SelenideElement getInputDestinationNumberByNumber(String text) {
        return field(String.format(inputDestinationNumberByNumberXpath, text));
    }

    public SelenideElement getInputDisplayNameByNumber(String text) {
        return field(String.format(inputDisplayNameByNumberXpath,text));
    }
    //</editor-fold>


    @Step("Select language")
    public ProvisioningSettingTab selectLanguage(String value){
        getDropdownLanguage().selectOptionByValue(value);
        return this;
    }

    @Step("Select Web language")
    public ProvisioningSettingTab selectWebLanguage(String value){
        getDropdownWebLanguage().selectOptionByValue(value);
        return this;
    }

    @Step("Configure WEB authentication")
    public ProvisioningSettingTab configureWebAuthentication(String user, String password){
        if (!getInputConfigureWebAuth().isSelected())getInputConfigureWebAuth().click();
        waitUntilAlertDisappear();
        if(getInputWebUser().exists())getInputWebUser().setValue(user);
        getInputWebPassword().setValue(password);
        return this;
    }

    @Step("Configure function")
    public ProvisioningSettingTab configureFunction(String destNum, String dispName){
        configFunc(1,destNum,dispName);
        configFunc(2,destNum,dispName);
        configFunc(3,destNum,dispName);
        return this;
    }

    private ProvisioningSettingTab configFunc(int num, String destNum, String dispName){
        getDropdownFunctionByNumber(String.valueOf(num)).selectOption(getRandomNumber(1,3));
        getInputDestinationNumberByNumber(String.valueOf(num)).setValue(destNum);
        getInputDisplayNameByNumber(String.valueOf(num)).setValue(dispName);
        return this;
    }

    @Step("Verify configured functions")
    public ProvisioningSettingTab verifyConfiguredFunctions(String destNum, String dispName){
        verifyFunc(1,destNum,dispName);
        verifyFunc(2,destNum,dispName);
        verifyFunc(3,destNum,dispName);
        return this;
    }

    private void verifyFunc(int num, String destNum, String dispName){
        getInputDestinationNumberByNumber(String.valueOf(num)).shouldHave(value(destNum));
        getInputDisplayNameByNumber(String.valueOf(num)).shouldHave(value(dispName));
    }

    @Step("Make phone function fixed")
    public ProvisioningSettingTab makeFixed(String number){
        if (!getCheckboxIsFixedByText(number).isSelected())
            getCheckboxIsFixedByText(number).click();
        return this;
    }

    @Step("Configure fixed function")
    public ProvisioningSettingTab configureFixedFunctions(PhoneModelTestData phoneModel){
        configFunc(11, phoneModel.getDestinNumber(),phoneModel.getDisplName()).makeFixed("11");
        waitUntilAlertDisappear();
        configFunc(12, phoneModel.getDestinNumber(),phoneModel.getDisplName()).makeFixed("12");
        waitUntilAlertDisappear();
        configFunc(13, phoneModel.getDestinNumber(),phoneModel.getDisplName()).makeFixed("13");
        return this;
    }

    @Step("Delete configured function")
    public ProvisioningSettingTab unConfigureFixedFunctions(){
        unfixFunc(11).getDropdownFunctionByNumber(String.valueOf(11)).selectOptionContainingText("Not selected");
        waitUntilAlertDisappear();
        unfixFunc(12).getDropdownFunctionByNumber(String.valueOf(12)).selectOptionContainingText("Not selected");
        waitUntilAlertDisappear();
        unfixFunc(13).getDropdownFunctionByNumber(String.valueOf(13)).selectOptionContainingText("Not selected");
        return this;
    }

    @Step("Unfix function")
    public ProvisioningSettingTab unfixFunc(int funcNum){
        if (getCheckboxIsFixedByText(String.valueOf(funcNum)).isSelected())
            getCheckboxIsFixedByText(String.valueOf(funcNum)).click();
        return this;
    }

    @Step("Verify Fixed functions")
    public ProvisioningSettingTab verifyFixedFunctions(PhoneModelTestData phoneModel){
        verifyFunc(11,phoneModel.getDestinNumber(),phoneModel.getDisplName());
        verifyFunc(12,phoneModel.getDestinNumber(),phoneModel.getDisplName());
        verifyFunc(13,phoneModel.getDestinNumber(),phoneModel.getDisplName());
        return this;
    }

}
