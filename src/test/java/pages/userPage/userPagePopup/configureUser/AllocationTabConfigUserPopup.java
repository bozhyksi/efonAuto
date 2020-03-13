package pages.userPage.userPagePopup.configureUser;

import com.codeborne.selenide.SelenideElement;

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
    //</editor-fold>

    //<editor-fold desc="get\set">
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
}
