package pages.callPickUpPage.callPickUpPopup;

import com.codeborne.selenide.SelenideElement;
import pages.callPickUpPage.CallPickUpPage;

public class GroupCallPickupPopup extends CallPickUpPage {
    //<editor-fold desc="locators">
    private String inputNameXpath = "//input[@formcontrolname=\"name\"]";
    private String dropdownAbbrevDialXpath = "//select[@formcontrolname=\"internalNumberId\"]";
    private String dropdownSelectAccounts = "//label[text()=\"Select accounts for call pick-up\"]/following-sibling::div/select";
    private String buttonSaveXpath = "//button[text()=\"Save\"]";
    private String buttonCancelXpath = "//button[text()=\"Cancel\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getButtonCancel() {
        return field(buttonCancelXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getDropdownAbbrevDial() {
        return field(dropdownAbbrevDialXpath);
    }

    public SelenideElement getInputName() {
        return field(inputNameXpath);
    }

    public SelenideElement getDropdownSelectAccounts() {
        return field(dropdownSelectAccounts);
    }
    //</editor-fold>

    public void selectAbbrenNumber(String abbreNum){
        getDropdownAbbrevDial().selectOptionContainingText(abbreNum);
    }

    public void selectAccount(){
        getDropdownSelectAccounts().selectOption(0);
    }
}
