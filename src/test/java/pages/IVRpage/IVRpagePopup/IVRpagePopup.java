package pages.IVRpage.IVRpagePopup;

import com.codeborne.selenide.SelenideElement;
import pages.IVRpage.IVRpage;

public class IVRpagePopup extends IVRpage {

    //<editor-fold desc="Locators">
    private String inputNameXpath = "//input[@formcontrolname=\"logicalName\"]";
    private String inputDisplayNameXpath = "//input[@formcontrolname=\"displayName\"]";
    private String dropdownLanguageXpath = "//select[@formcontrolname=\"language\"]";
    private String dropdownSelectIvrNumberXpath = "//select[@formcontrolname=\"number\"]";
    private String dropdownSelectAnnouncXpath = "//select[@formcontrolname=\"announcement\"]";
    private String buttonSaveXpath = "//div[@class=\"modal-content\"]//button[text()=\"Save\"]";
    private String buttonCancelXpath = "//div[@class=\"modal-content\"]//button[text()=\"Cancel\"]";
    private String buttonCloseXpath = "//button[@class=\"close\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getButtonClose() {
        return field(buttonCloseXpath);
    }

    public SelenideElement getInputName() {
        return field(inputNameXpath);
    }

    public SelenideElement getInputDisplayName() {
        return field(inputDisplayNameXpath);
    }

    public SelenideElement getDropdownLanguage() {
        return field(dropdownLanguageXpath);
    }

    public SelenideElement getDropdownSelectIvrNumber() {
        return field(dropdownSelectIvrNumberXpath);
    }

    public SelenideElement getDropdownSelectAnnounc() {
        return field(dropdownSelectAnnouncXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getButtonCancel() {
        return field(buttonCancelXpath);
    }
    //</editor-fold>

    public void selectIvrNumber(){
        getDropdownSelectIvrNumber().selectOption(getRandomDropDownOption(dropdownSelectIvrNumberXpath));
    }
}
