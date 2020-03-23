package pages.userPage.userPagePopup.configureUser;

import com.codeborne.selenide.SelenideElement;

public class EndDeviceTabConfigUserPopup extends ConfigureUserBasePopup {
    //<editor-fold desc="locators">
    private String dropdownSelectEndDeviceXpath = "//edit-user//h3/following-sibling::select";
    private String inputNameEndDevXpath = "//edit-user//end-device-detail//input[@formcontrolname=\"name\"]";
    private String inputUserIdEndDevXpath = "//edit-user//end-device-detail//input[@formcontrolname=\"userId\"]";
    private String buttonEditPassEndDevXpath = "//edit-user//end-device-detail//i[contains(@class,\"fa-cog\")]/parent::a";
    private String inputPasswordEndDevXpath = "//edit-user//end-device-detail//input[@formcontrolname=\"password\"]";
    private String dropdownCodecsEndDevXpath = "//edit-user//end-device-detail//select[@formcontrolname=\"codec\"]";
    private String buttonSavePassXpath = "//edit-user//end-device-detail//button[@id=\"saveButton\"]";
    private String buttonCancelPassXpath = "//edit-user//end-device-detail//button[@id=\"cancelButton\"]";
    private String dropdownLanguageEndDevXpath = "//edit-user//end-device-detail//select[@formcontrolname=\"language\"]";
    private String inputDispNameEndDevXpath = "//edit-user//end-device-detail//input[@formcontrolname=\"displayName\"]";
    private String dropdownOutgoingNumEndDevXpath = "//edit-user//end-device-detail//select[@formcontrolname=\"outgoingNumber\"]";
    private String inputLocationEndDevXpath = "//edit-user//end-device-detail//input[@formcontrolname=\"emergencyLocation\"]";
    private String buttonSaveXpath = "//div[@class=\"modal-body\"]//button[text()=\"Save\"]";
    private String buttonCancelXpath = "//div[@class=\"modal-body\"]//button[text()=\"Cancel\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getDropdownSelectEndDevice() {
        return field(dropdownSelectEndDeviceXpath);
    }

    public SelenideElement getInputNameEndDev() {
        return field(inputNameEndDevXpath);
    }

    public SelenideElement getInputUserIdEndDev() {
        return field(inputUserIdEndDevXpath);
    }

    public SelenideElement getButtonEditPassEndDev() {
        return field(buttonEditPassEndDevXpath);
    }

    public SelenideElement getInputPasswordEndDev() {
        return field(inputPasswordEndDevXpath);
    }

    public SelenideElement getDropdownCodecsEndDev() {
        return field(dropdownCodecsEndDevXpath);
    }

    public SelenideElement getButtonSavePass() {
        return field(buttonSavePassXpath);
    }

    public SelenideElement getButtonCancelPass() {
        return field(buttonCancelPassXpath);
    }

    public SelenideElement getDropdownLanguageEndDev() {
        return field(dropdownLanguageEndDevXpath);
    }

    public SelenideElement getinputDispNameEndDev() {
        return field(inputDispNameEndDevXpath);
    }

    public SelenideElement getDropdownOutgoingNumEndDev() {
        return field(dropdownOutgoingNumEndDevXpath);
    }

    public SelenideElement getInputLocationEndDev() {
        return field(inputLocationEndDevXpath);
    }
    //</editor-fold>
}
