package pages.provisioningPage.provisioningPopups.provisioningSettingsPopup;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.provisioningPage.ProvisioningEndDevicesPage;

import java.io.File;

import static core.configuration.preparations.eFonApp.excelFileWorker;

public class ProvisioningConfigurationTab extends ProvisioningSettingsPopup {

    //<editor-fold desc="locators">
    private final String dropdownFirmwareXpath = "//select[@formcontrolname=\"firmwareId\"]";
    private final String dropdownConfigurationTemplateXpath= "//select[@formcontrolname=\"configurationTemplate\"]";
    private final String buttonDownloadDefaultTemplate = "//button[text()=\"Download default template\"]";
    private final String inputUploadCustomConfigTemplateXpath= "//input[@type=\"file\"]";
    private final String buttonDownloadConfigtemplateXpath = "//button[text()=\"Download\"]";
    //private final String = "";
    //private final String = "";
    //</editor-fold>


    //<editor-fold desc="get\set">
    public SelenideElement getDropdownFirmware() {
        return field(dropdownFirmwareXpath);
    }

    public SelenideElement getDropdownConfigurationTemplate() {
        return field(dropdownConfigurationTemplateXpath);
    }

    public SelenideElement getButtonDownloadDefaultTemplate() {
        return field(buttonDownloadDefaultTemplate);
    }

    public SelenideElement getInputUploadCustomConfigTemplate() {
        return field(inputUploadCustomConfigTemplateXpath);
    }

    public SelenideElement getButtonDownloadConfigtemplate() {
        return field(buttonDownloadConfigtemplateXpath);
    }
    //</editor-fold>

    @Step("Upload custom configuration template")
    public ProvisioningConfigurationTab uploadCustomConfigTemplate(String path){
        getDropdownConfigurationTemplate().selectOptionContainingText("Custom");
        waitUntilAlertDisappear();
        getInputUploadCustomConfigTemplate().uploadFile(new File(path));
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Save changes")
    public ProvisioningEndDevicesPage saveChanges(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return new ProvisioningEndDevicesPage();
    }

    @Step("Download current configuration template")
    public ProvisioningConfigurationTab downloadCurrentConfigurationTemplate(){
        getButtonDownloadConfigtemplate().click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Verify if config file was downloaded")
    public ProvisioningConfigurationTab verifyIfConfigFileWaDownloaded(){
        Assert.assertTrue(excelFileWorker.checkIfFileExists("aastra_37i_V2.0_6867.cfg"), "File not found!");
        return this;
    }

    @Step("Delete downloaded file")
    public ProvisioningConfigurationTab deleteDownloadedFile(){
        excelFileWorker.deleteFile("aastra_37i_V2.0_6867.cfg");
        return this;
    }

    @Step("Select firmware")
    public ProvisioningConfigurationTab selectFirmware(){
        getDropdownFirmware().selectOption(1);
        return this;
    }



}
