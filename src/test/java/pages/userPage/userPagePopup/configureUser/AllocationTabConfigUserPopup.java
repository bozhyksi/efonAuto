package pages.userPage.userPagePopup.configureUser;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.userPage.UserPage;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;

public class AllocationTabConfigUserPopup extends ConfigureUserBasePopup {
    //<editor-fold desc="Locators">
    private String checkboxVoicemailEmailXpath = "//form//input[@formcontrolname=\"useVoicemailEmail\"]";
    private String inputVoicemailEmailXpath = "//form//input[@formcontrolname=\"voicemailEmail\"]";
    private String dropdownNumberXpath = "//form//select[@formcontrolname=\"number\"]";
    private String dropdownEndDeviceXpath = "//form//label[text()=\"End device(s)\"]/following-sibling::div/select";
    private String checkboxBusyOnBusyXpath = "//form//input[@formcontrolname=\"busyOnBusy\"]";
    private String dropdownAbbreviatedNumbersXpath = "//form//label[text()=\"Abbreviated numbers\"]/following-sibling::div/select";
    private String dropdownPermittedDestinationXpath = "//form//select[@formcontrolname=\"currentBlockSet\"]";
    private String checkboxActivateSmsXpath = "//form//input[@formcontrolname=\"smsEnabled\"]";
    private String checkboxActivateFaxXpath = "//form//input[@formcontrolname=\"faxEnabled\"]";
    private String checkboxHasFinanceXpath = "//form//input[@formcontrolname=\"roleFinance\"]";
    private String checkboxCallsRecordingXpath = "//form//input[@formcontrolname=\"activateCallRecording\"]";
    private String dropdownCallsRecordingXpath = "//form//select[@formcontrolname=\"callRecordingDirection\"]";
    private String selectedEndDeviceXpath = "//form//span[contains(@class,\"selected-item\")]";
    private final String dropdownFax2EmailAccessXpath = "//select[@formcontrolname=\"faxNumber\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">


    public SelenideElement getDropdownFax2EmailAccess() {
        return field(dropdownFax2EmailAccessXpath);
    }

    public SelenideElement getSelectedEndDevice() {
        return field(selectedEndDeviceXpath);
    }

    public SelenideElement getCheckboxVoicemailEmail() {
        return field(checkboxVoicemailEmailXpath);
    }

    public SelenideElement getInputVoicemailEmail() {
        return field(inputVoicemailEmailXpath);
    }

    public SelenideElement getDropdownNumber() {
        return field(dropdownNumberXpath);
    }

    public SelenideElement getDropdownEndDevice() {
        return field(dropdownEndDeviceXpath);
    }

    public SelenideElement getCheckboxBusyOnBusy() {
        return field(checkboxBusyOnBusyXpath);
    }

    public SelenideElement getDropdownAbbreviatedNumbers() {
        return field(dropdownAbbreviatedNumbersXpath);
    }

    public SelenideElement getDropdownPermittedDestination() {
        return field(dropdownPermittedDestinationXpath);
    }

    public SelenideElement getCheckboxActivateSms() {
        return field(checkboxActivateSmsXpath);
    }

    public SelenideElement getCheckboxActivateFax() {
        return field(checkboxActivateFaxXpath);
    }

    public SelenideElement getCheckboxHasFinance() {
        return field(checkboxHasFinanceXpath);
    }

    public SelenideElement getCheckboxCallsRecording() {
        return field(checkboxCallsRecordingXpath);
    }

    public SelenideElement getDropdownCallsRecording() {
        return field(dropdownCallsRecordingXpath);
    }
    //</editor-fold>

    @Step("Validate VoicemailEmail")
    public AllocationTabConfigUserPopup validateVoicemailEmail(String expR){
        Assert.assertEquals(getInputVoicemailEmail().getValue(), expR, "Voicemail e-mail does not match.");
        return  this;
    }

    @Step("Validate Number")
    public AllocationTabConfigUserPopup validateNumber(String expR){
        getDropdownNumber().getSelectedText().contains(expR);
        return  this;
    }

    @Step("Validate EndDevice")
    public AllocationTabConfigUserPopup validateEndDevice (String expR){
        getSelectedEndDevice().text().contains(expR);
        return  this;
    }

    @Step("Validate BusyOnBusy")
    public AllocationTabConfigUserPopup validateBusyOnBusy (){
        getCheckboxBusyOnBusy().shouldBe(Condition.selected);
        return  this;
    }

    @Step("Validate DestinationNumbers")
    public AllocationTabConfigUserPopup validatePermittedDestinationNumbers (String expR){
        getDropdownPermittedDestination().getSelectedValue().contains(expR);
        return  this;
    }

    @Step("Validate SMS")
    public AllocationTabConfigUserPopup validateActivateSMSservices (){
        getCheckboxActivateSms().shouldBe(Condition.selected);
        return  this;
    }

    @Step("Validate CallsRecording")
    public AllocationTabConfigUserPopup validateCallsRecording (){
        getCheckboxCallsRecording().shouldBe(Condition.selected);
        return  this;
    }

    @Step("Validate CallsRecordingDirection")
    public AllocationTabConfigUserPopup validateCallsRecordingDirection(String expR){
        getDropdownCallsRecording().getSelectedText().contains(expR);
        return  this;
    }

    @Step("Select number in Fax2Email dropdown")
    public AllocationTabConfigUserPopup selectNumberAssignFax2EmailAccess(String number){
        getDropdownFax2EmailAccess().selectOptionByValue(number);
        return this;
    }

    @Step("Save changes")
    public AllocationTabConfigUserPopup saveChanges(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Close edit popup")
    public UserPage closeEditUserPopup(){
        return super.closeEditUserPopup();
    }

    @Step("Select Abbreviated number")
    public AllocationTabConfigUserPopup selectAbbreviatedNumber(AbbreviatedDialling shortNum){
        getDropdownAbbreviatedNumbers().selectOptionContainingText(shortNum.getSingleShortNum());
        return this;
    }


}
