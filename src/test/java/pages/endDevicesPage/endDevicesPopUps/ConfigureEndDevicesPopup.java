package pages.endDevicesPage.endDevicesPopUps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.endDevicesPage.EndDevicesPage;
import tests.userPageTests.userPageTestData.EndDevice;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;

public class ConfigureEndDevicesPopup extends EndDevicesPage {
    //<editor-fold desc="locators">
    private final String inputNameXpath = "//input[@formcontrolname=\"name\"]";
    private final String inputUserIdXpath= "//input[@formcontrolname=\"userId\"]";
    private final String buttonEditPasswordXpath = "//label[text()=\"Password\"]/..//a";
    private final String inputPasswordXpath = "//input[@formcontrolname=\"password\"]";
    private final String buttonSavePasswordXpath = "//button[@id=\"saveButton\"]";
    private final String dropdownProxyServerXpath = "//select[@formcontrolname=\"proxy\"]";
    private final String dropdownCodecsXpath = "//select[@formcontrolname=\"codec\"]";
    private final String dropdownPhoneLanguageSettingsXpath = "//select[@formcontrolname=\"language\"]";
    private final String inputDisplayNameXpath = "//input[@formcontrolname=\"displayName\"]";
    private final String dropdownOutgoingNumberXpath = "//select[@formcontrolname=\"outgoingNumber\"]";
    private final String dropdownCallPickUpsXpath = "//select[@formcontrolname=\"callInterceptGroup\"]";
    private final String inputLocationXpath = "//input[@formcontrolname=\"emergencyLocation\"]";
    private final String checkboxSuppressedYesXpath = "//input[@formcontrolname=\"suppressed\" and @value=\"true\"]";
    private final String checkboxSuppressedNoXpath = "//input[@formcontrolname=\"suppressed\" and @value=\"false\"]";
    private final String dropdownCountryCode = "//select[@formcontrolname=\"countryCode\"]";
    //</editor-fold>


    //<editor-fold desc="get\set">


    public SelenideElement getDropdownCountryCode() {
        return field("//select[@formcontrolname=\"countryCode\"]");
    }

    public SelenideElement getInputName() {
        return field(inputNameXpath);
    }

    public SelenideElement getInputUserId() {
        return field(inputUserIdXpath);
    }

    public SelenideElement getButtonEditPassword() {
        return field(buttonEditPasswordXpath);
    }

    public SelenideElement getInputPassword() {
        return field(inputPasswordXpath);
    }

    public SelenideElement getButtonSavePassword() {
        return field(buttonSavePasswordXpath);
    }

    public SelenideElement getDropdownProxyServer() {
        return field(dropdownProxyServerXpath);
    }

    public SelenideElement getDropdownCodecs() {
        return field(dropdownCodecsXpath);
    }

    public SelenideElement getDropdownPhoneLanguageSettings() {
        return field(dropdownPhoneLanguageSettingsXpath);
    }

    public SelenideElement getInputDisplayName() {
        return field(inputDisplayNameXpath);
    }

    public SelenideElement getDropdownOutgoingNumber() {
        return field(dropdownOutgoingNumberXpath);
    }

    public SelenideElement getDropdownCallPickUps() {
        return field(dropdownCallPickUpsXpath);
    }

    public SelenideElement getInputLocation() {
        return field(inputLocationXpath);
    }

    public SelenideElement getCheckboxSuppressedYes() {
        return field(checkboxSuppressedYesXpath);
    }

    public SelenideElement getCheckboxSuppressedNo() {
        return field(checkboxSuppressedNoXpath);
    }
    //</editor-fold>

    @Step("Set end-device name")
    public ConfigureEndDevicesPopup setName(String name){
        field(inputNameXpath).setValue(name);
        return this;
    }

    @Step("Set end-device user ID")
    public ConfigureEndDevicesPopup setUserId(String userId){
        field(inputUserIdXpath).setValue(userId);
        return this;
    }

    @Step("Set end-device password")
    public ConfigureEndDevicesPopup setPassword(String password){
        field(buttonEditPasswordXpath).click();
        field(inputPasswordXpath).setValue(password);
        field(buttonSavePasswordXpath).click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Set end-device codec")
    public ConfigureEndDevicesPopup selectCodec(String codec){
        field(dropdownCodecsXpath).selectOptionByValue(codec);
        return this;
    }

    @Step("Set end-device language")
    public ConfigureEndDevicesPopup selectLanguage(String language){
        field(dropdownPhoneLanguageSettingsXpath).selectOptionByValue(language);
        return this;
    }

    @Step("Set end-device display name")
    public ConfigureEndDevicesPopup setDisplayName(String displayName){
        field(inputDisplayNameXpath).setValue(displayName);
        return this;
    }

    @Step("Set end-device proxy server")
    public ConfigureEndDevicesPopup selectProxyServer(String proxy){
        field(dropdownProxyServerXpath).selectOptionByValue(proxy);
        return this;
    }

    @Step("Set end-device outgoing number")
    public ConfigureEndDevicesPopup selectOutgoingNumber(String outgoingNumber){
        field(dropdownOutgoingNumberXpath).selectOptionByValue(outgoingNumber);
        return this;
    }

    @Step("Set end-device call pick-up")
    public ConfigureEndDevicesPopup selectCallsPickUp(String callPickUp){
        field(dropdownCallPickUpsXpath).selectOptionByValue(callPickUp);
        return this;
    }

    @Step("Set end-device location")
    public ConfigureEndDevicesPopup setLocation(String location){
        field(inputLocationXpath).setValue(location);
        return this;
    }

    @Step("Set end-device suppressed option")
    public ConfigureEndDevicesPopup setSuppressed(boolean suppressed){
        if (suppressed) field(checkboxSuppressedYesXpath).click();
        else field(checkboxSuppressedNoXpath).click();
        return this;
    }

    @Step("Save changes, close popup")
    public EndDevicesPage saveChanges(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return new EndDevicesPage();
    }

    @Step("Verify end-device configuration")
    public ConfigureEndDevicesPopup verifyEndDeviceConfiguration(EndDevice endDevice){
        getInputName().shouldHave(value(endDevice.getEndDevName()));
        getInputUserId().shouldHave(value(endDevice.getEndDevUserId()));
        getDropdownProxyServer().getSelectedValue().contains(endDevice.getEndDevProxy());
        getDropdownCodecs().getSelectedValue().contains(endDevice.getEndDevCodec());
        getDropdownPhoneLanguageSettings().getSelectedValue().contains(endDevice.getEndDevPhoneLanguage());
        getInputDisplayName().shouldHave(value(endDevice.getEndDevDisplayName()));
        getDropdownOutgoingNumber().getSelectedValue().contains(endDevice.getEndDevOutgoingNumber());
        //getInputLocation().shouldHave(value(endDevice.getEndDevLocation()));
        if (endDevice.getEndDevSuppressed()) getCheckboxSuppressedYes().shouldBe(selected);
        else getCheckboxSuppressedNo().shouldBe(selected);
        return this;
    }

    @Step("Verify if location field empty")
    public ConfigureEndDevicesPopup verifyLocationFieldEmpty(){
        getInputLocation().shouldHave(value(""));
        return this;
    }

    @Step("Verify location field value")
    public ConfigureEndDevicesPopup verifyLocationFieldValue(String locationExpected){
        String locationActual = getInputLocation().getValue();
        Assert.assertEquals(locationActual,locationExpected);
        return this;
    }

    @Step("Get outgoing drop down items")
    public ArrayList<String> getOutgoingDropdownItems(){
        waitUntilAlertDisappear();
        ArrayList<String> dropdownItemsList = new ArrayList<>();
        Select obj = new Select(getDropdownOutgoingNumber());
        List<WebElement> itemsList = obj.getOptions();
        for (WebElement elem: itemsList) {
            if (!elem.getText().contains("Not Selected"))dropdownItemsList.add(elem.getText().replaceAll("\\s",""));
        }
        return dropdownItemsList;
    }

    @Step("Select Country Code")
    public ConfigureEndDevicesPopup selectCountryCode(String countryCode){
        field("//select[@formcontrolname=\"countryCode\"]").selectOptionByValue(countryCode);
        return this;
    }

    @Step("Verify Country Code")
    public ConfigureEndDevicesPopup verifyCountryCode(String countryCodeExpected){
        String countryCodeActual = getDropdownCountryCode().getSelectedValue();
        Assert.assertEquals(countryCodeActual,countryCodeExpected,
                "\nCountry Code is "+countryCodeActual+" but should be "+countryCodeExpected+"\n");
        return this;
    }
}
