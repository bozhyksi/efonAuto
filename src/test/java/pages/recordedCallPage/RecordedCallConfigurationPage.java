package pages.recordedCallPage;

import com.codeborne.selenide.SelenideElement;

public class RecordedCallConfigurationPage extends RecordedCallsPage {
    //<editor-fold desc="locators">
    private String checkboxUploadXpath = "//label[text()=\"Upload:\"]/following-sibling::div/input";
    private String inputHostXpath = "//input[@formcontrolname=\"host\"]";
    private String inputPortXpath = "//input[@formcontrolname=\"port\"]";
    private String checkboxUseFtpXpath = "//input[@formcontrolname=\"sftp\"]";
    private String inputUserXpath = "//input[@formcontrolname=\"user\"]";
    private String inputPasswordXpath = "//input[@formcontrolname=\"password\"]";
    private String inputPathXpath = "//input[@formcontrolname=\"path\"]";
    private String dropdownUploadFrequencyXpath = "//input[@formcontrolname=\"ftpUploadFrequency\"]";
    private String buttonSaveXpath = "//button[text()=\"Save\"]";
    private String buttonCancelXpath = "//button[text()=\"Cancel\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getCheckboxUpload() {
        return field(checkboxUploadXpath);
    }

    public SelenideElement getInputHost() {
        return field(inputHostXpath);
    }

    public SelenideElement getInputPort() {
        return field(inputPortXpath);
    }

    public SelenideElement getCheckboxUseFtp() {
        return field(checkboxUseFtpXpath);
    }

    public SelenideElement getInputUser() {
        return field(inputUserXpath);
    }

    public SelenideElement getInputPassword() {
        return field(inputPasswordXpath);
    }

    public SelenideElement getInputPath() {
        return field(inputPathXpath);
    }

    public SelenideElement getDropdownUploadFrequency() {
        return field(dropdownUploadFrequencyXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }
    //</editor-fold>
}
