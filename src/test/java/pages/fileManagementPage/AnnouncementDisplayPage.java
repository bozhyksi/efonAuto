package pages.fileManagementPage;

import com.codeborne.selenide.SelenideElement;

public class AnnouncementDisplayPage extends FileManagementPage {
    private String buttonUploadFileXpath = "//a[@title=\"Upload WAV files\"]";
    private String inputNameXpath = "//input[@formcontrolname=\"displayName\"]";
    private String inputFileUploadXpath = "//input[@formcontrolname=\"multipartFile\"]";
    private String buttonDownloadByNameXpath = "//table//td[2]/div[contains(text(),\"%s\")]/ancestor::tr//a[@title=\":::Download\"]";
    private String buttonTestConfigByNameXpath = "//table//td[2]/div[contains(text(),\"%s\")]/ancestor::tr//a[@title=\":::Create test configuration\"]";
    private String buttonEditByNameXpath = "//table//td[2]/div[contains(text(),\"%s\")]/ancestor::tr//a[@title=\":::Edit\"]";
    private String buttonDeleteByNameXpath = "//table//td[2]/div[contains(text(),\"%s\")]/ancestor::tr//a[@title=\":::Delete\"]";

    public SelenideElement getButtonUploadFile() {
        return field(buttonUploadFileXpath);
    }

    public SelenideElement getInputName() {
        return field(inputNameXpath);
    }

    public SelenideElement getInputFileUpload() {
        return field(inputFileUploadXpath);
    }

    public SelenideElement getButtonDownloadByName(String name) {
        return field(String.format(buttonDownloadByNameXpath,name));
    }

    public SelenideElement getButtonTestConfigByName(String name) {
        return field(String.format(buttonTestConfigByNameXpath, name));
    }

    public SelenideElement getButtonEditByName(String name) {
        return field(String.format(buttonEditByNameXpath,name));
    }

    public SelenideElement getButtonDeleteByName(String name) {
        return field(String.format(buttonDeleteByNameXpath,name));
    }
}
