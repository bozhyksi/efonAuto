package pages.userPage.userPagePopup;

import com.codeborne.selenide.SelenideElement;
import pages.userPage.UserPage;

public class CreateUserPopup extends UserPage {

    //<editor-fold desc="//-- CreateUserPopup Locators --//">
    private String popupTitleXpath = "//div[@class=\"modal-header\"]//h2";
    private String dropdownTitleXpath = "//create-user//select[@formcontrolname=\"salutation\"]";
    private String inputFirstNameXpath = "//create-user//input[@formcontrolname=\"firstName\"]";
    private String inputLastNameXpath = "//create-user//input[@formcontrolname=\"lastName\"]";
    private String inputLoginEmailXpath = "//create-user//input[@formcontrolname=\"loginEmail\"]";
    private String checkboxUseDiffContactEmailXpath = "//create-user//input[@formcontrolname=\"useDifferentContactEmail\"]";
    private String inputDiffContactEmailXpath = "//create-user//input[@formcontrolname=\"useDifferentContactEmail\"]";
    private String inputUseVoiceEmailEmailXpath = "//create-user//input[@formcontrolname=\"voicemailEmail\"]";
    private String checkboxUseVoiceEmailXpath = "//create-user//input[@formcontrolname=\"useVoicemailEmail\"]";
    private String dropdownNumberXpath = "//create-user//select[@formcontrolname=\"number\"]";
    private String dropdownEndDevicesXpath = "//label[text()='End device(s)']//following-sibling::div/select";
    private String checkboxBusyOnBusyXpath = "//create-user//input[@formcontrolname=\"busyOnBusy\"]";
    private String dropdownAbbreviatedNumbersXpath = "//label[text()='Abbreviated numbers']//following-sibling::div/select";
    private String dropdownPermittedDestNumbersXpath = "//create-user//select[@formcontrolname=\"currentBlockSet\"]";
    private String checkboxSmsEnabledXpath = "//create-user//input[@formcontrolname=\"smsEnabled\"]";
    private String checkboxFaxEnabledXpath = "//create-user//input[@formcontrolname=\"faxEnabled\"]";
    private String checkboxRoleFinanceXpath = "//create-user//input[@formcontrolname=\"roleFinance\"]";
    private String checkboxActivateCallRecordingXpath = "//create-user//input[@formcontrolname=\"activateCallRecording\"]";
    private String dropdownCallRecordingDirectionXpath = "//create-user//select[@formcontrolname=\"callRecordingDirection\"]";
    private String buttonSaveXpath = "//button[text()='Save']";
    private String buttonCancelXpath = "//button[text()='Cancel']";
    private String buttonCloseXpath = "//button[@aria-label=\"Close\"]";
    //</editor-fold>


    //<editor-fold desc="//-- CreateUserPopup get/set methods --//">
    public SelenideElement getPopupTitle() {
        return field(popupTitleXpath);
    }

    public SelenideElement getDropdownTitle() {
        return field(dropdownTitleXpath);
    }

    public SelenideElement getInputFirstName() {
        return field(inputFirstNameXpath);
    }

    public SelenideElement getInputLastName() {
        return field(inputLastNameXpath);
    }

    public SelenideElement getInputLoginEmail() {
        return field(inputLoginEmailXpath);
    }

    public SelenideElement getCheckboxUseDiffContactEmail() {
        return field(checkboxUseDiffContactEmailXpath);
    }

    public SelenideElement getInputDiffContactEmail() {
        return field(inputDiffContactEmailXpath);
    }

    public SelenideElement getCheckboxUseVoiceEmail() {
        return field(checkboxUseVoiceEmailXpath);
    }

    public SelenideElement getInputUseVoiceEmailEmail() {
        return field(inputUseVoiceEmailEmailXpath);
    }

    public SelenideElement getDropdownNumber() {
        return field(dropdownNumberXpath);
    }

    public SelenideElement getDropdownEndDevices() {
        return field(dropdownEndDevicesXpath);
    }

    public SelenideElement getCheckboxBusyOnBusy() {
        return field(checkboxBusyOnBusyXpath);
    }

    public SelenideElement getDropdownAbbreviatedNumbers() {
        return field(dropdownAbbreviatedNumbersXpath);
    }

    public SelenideElement getDropdownPermittedDestNumbers() {
        return field(dropdownPermittedDestNumbersXpath);
    }

    public SelenideElement getCheckboxSmsEnabled() {
        return field(checkboxSmsEnabledXpath);
    }

    public SelenideElement getCheckboxFaxEnabled() {
        return field(checkboxFaxEnabledXpath);
    }

    public SelenideElement getCheckboxRoleFinance() {
        return field(checkboxRoleFinanceXpath);
    }

    public SelenideElement getCheckboxActivateCallRecording() {
        return field(checkboxActivateCallRecordingXpath);
    }

    public SelenideElement getDropdownCallRecordingDirection() {
        return field(dropdownCallRecordingDirectionXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getButtonCancel() {
        return field(buttonCancelXpath);
    }

    public SelenideElement getButtonClose() {
        return field(buttonCloseXpath);
    }
    //</editor-fold>

    public void selectTitle(String value){
        getDropdownTitle().selectOption(value);
    }

    public void fillFirstName(String val){
        getInputFirstName().setValue(val);
    }

    public void fillLastName(String val){
        getInputLastName().setValue(val);
    }

    public void fillLoginEmail(String val){
        getInputLoginEmail().setValue(val);
    }

    public void selectNumber(){
        getDropdownNumber().selectOption(1);
    }

    public void selectEndDevices(){
        getDropdownEndDevices().selectOption(1);
    }

}
