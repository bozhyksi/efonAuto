package pages.provisioningPage.provisioningPopups.provisioningSettingsPopup;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.Select;
import tests.provisioningPageTests.provisioningTestData.PhoneModelTestData;

import static com.codeborne.selenide.Condition.*;

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
    private final String fieldFixedByText = "//td[text()[contains(.,\"%s\")]]";
    //</editor-fold>


    //<editor-fold desc="get\set">
    public SelenideElement getFieldDisplNameByText(String text) {
        return field(String.format(fieldFixedByText,text));
    }

    public SelenideElement getFieldDestNumByText(String text) {
        return field(String.format(fieldFixedByText,text));
    }

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

    @Step("Make phone function fixed")
    public ProvisioningSettingTab makeFixed(String number){
        if (!getCheckboxIsFixedByText(number).isSelected())
            getCheckboxIsFixedByText(number).click();
        return this;
    }

    private int getFunctionDropdownSize(){
        waitUntilAlertDisappear();
        return new Select(getDropdownFunctionByNumber("1")).getOptions().size();
    }

    private int getFunctionSize(){
        waitUntilAlertDisappear();
        return fields("//table[@formarrayname=\"functionKeys\"]//tr").size()-1;
    }

    @Step("Configure all functions")
    public ProvisioningSettingTab configAllFunctions(PhoneModelTestData obj){
        for (int i = 1; i <= getFunctionDropdownSize()-1; i++) {
            String index = String.valueOf(getFunctionSize() - i);
            getDropdownFunctionByNumber(index).selectOption(i);
            getInputDisplayNameByNumber(index).setValue(obj.getDisplName());
            if (getInputDestinationNumberByNumber(index).isEnabled()){
                getInputDestinationNumberByNumber(index).setValue(obj.getDestinNumber());
            }
            makeFixed(index);
        }
        return this;
    }

    @Step("Validate configured functions")
    public ProvisioningSettingTab validateFunctions(PhoneModelTestData obj){
        for (SelenideElement element : fields("//input[@formcontrolname=\"destinationNumber\"]")) {
            if (element.isEnabled()){
                element.shouldHave(value(obj.getDestinNumber()));
            }
        }
        for (SelenideElement element : fields("//input[@formcontrolname=\"displayName\"]")) {
            if (element.isEnabled()){
                element.shouldHave(value(obj.getDisplName()));
            }
        }
        return this;
    }

    @Step("Deactivate functions")
    public ProvisioningSettingTab deactivateAllFunctions(){
        for (int i = 1; i < getFunctionSize(); i++) {
            if (!getDropdownFunctionByNumber(String.valueOf(i)).getSelectedOption().getText().contains("Not selected")){
                getDropdownFunctionByNumber(String.valueOf(i)).selectOption(0);
                waitUntilAlertDisappear();
            }
        }
        return this;
    }

}
