package pages.recordedCallPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import tests.recordedCallsPageTests.recordedCallsTestData.RecordedCalls;

public class RecordedCallConfigurationPage extends RecordedCallsBasePage {
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

    @Step("Configure FTP connection")
    public RecordedCallConfigurationPage configureRecordedCallsFtpUpload(RecordedCalls recordedCall){
        if (getCheckboxUpload().isSelected()){
            getCheckboxUpload().click();
            getButtonSave().click();
            waitUntilAlertDisappear();
            refreshPage();
        }
        getCheckboxUpload().click();
        getInputHost().setValue(recordedCall.getHost());
        getInputPort().setValue(recordedCall.getPort());
        getCheckboxUseFtp().click();
        getInputUser().setValue(recordedCall.getUser());
        getInputPassword().setValue(recordedCall.getPass());
        getInputPath().setValue(recordedCall.getPath());
        getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();
        return this;
    }

    @Step("Verify FTP connection")
    public RecordedCallConfigurationPage verifyRecordedCallsFtpUploadConfiguration(RecordedCalls recordedCall){
        getCheckboxUpload().shouldBe(Condition.selected);
        getInputHost().shouldHave(Condition.value(recordedCall.getHost()));
        getInputPort().shouldHave(Condition.value(recordedCall.getPort()));
        getCheckboxUseFtp().shouldBe(Condition.selected);
        getInputPath().shouldHave(Condition.value(recordedCall.getPath()));
        getInputUser().shouldHave(Condition.value(recordedCall.getUser()));
        getCheckboxUpload().click();
        getButtonSave().click();
        waitUntilAlertDisappear();
        return this;
    }
}
