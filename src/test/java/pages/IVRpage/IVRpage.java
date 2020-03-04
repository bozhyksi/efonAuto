package pages.IVRpage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;
import tests.IVRpageTests.IVRtestData.IVRtestData;

public class IVRpage extends BasePage {

    //<editor-fold desc="Locators">
    private String buttonNewIvrXpath = "//a[text()=\"New IVR\"]";
    private String listNameXpath = "//table//td[1]";
    private String listDisplayNameXpath = "//table//td[2]";
    private String listNumberXpath = "//table//td[3]";
    private String buttonDeleteIvrByName = "//td[1][contains(text(),\"%s\")]//ancestor::tr//a[@id=\"deleteIvr\"]";
    private String buttonEditIvrByName = "//td[1][contains(text(),\"%s\")]//ancestor::tr//a[@id=\"editIvr\"]";
    private String dropdownParameterByEventNumberXpath = "//table//td[2][text()=\"%s\"]//ancestor::tr//select[@formcontrolname=\"id\"]";
    private String inputParameterByEventNumberXpath = "//table//td[2][text()=\"%s\"]//ancestor::tr//input[@formcontrolname=\"id\"]";
    private String dropdownActionByEventNumberXpath = "//table//td[2][text()=\"%s\"]//ancestor::tr//select[@formcontrolname=\"ivrActionType\"]";
    private String checkboxActiveByEventNumberXpath = "//table//td[2][text()=\"%s\"]//ancestor::tr//input[@formcontrolname=\"active\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getCheckboxActiveByEventNumber(String name) {
        return field(String.format(checkboxActiveByEventNumberXpath, name));
    }

    public SelenideElement getDropdownActionByEventNumber(String name) {
        return field(String.format(dropdownActionByEventNumberXpath, name));
    }

    public SelenideElement getInputParameterByEventNumber(String name) {
        return field(String.format(inputParameterByEventNumberXpath, name));
    }

    public SelenideElement getDropdownParameterByEventNumber(String name) {
        return field(String.format(dropdownParameterByEventNumberXpath, name));
    }

    public SelenideElement getButtonNewIvr() {
        return field(buttonNewIvrXpath);
    }

    public ElementsCollection getListName() {
        return fields(listNameXpath);
    }

    public ElementsCollection getListDisplayName() {
        return fields(listDisplayNameXpath);
    }

    public ElementsCollection getListNumber() {
        return fields(listNumberXpath);
    }

    public SelenideElement getButtonDeleteIvrByName(String name) {
        return field(String.format(buttonDeleteIvrByName, name));
    }

    public SelenideElement getButtonEditIvrByName(String name) {
        return field(String.format(buttonEditIvrByName, name));
    }
    //</editor-fold>

    public void configureAction(IVRtestData ivrObj, IVRtestData.EventNumber eventNumber, IVRtestData.IvrActions ivrAction, boolean active){
        String eventNum = eventNumber.getEvent();
        String action = ivrAction.toString();
        if (active) getCheckboxActiveByEventNumber(eventNum).click();
        getDropdownActionByEventNumber(eventNum).selectOptionByValue(action);
        if (ivrAction == IVRtestData.IvrActions.PHONE_EXTERNAL){
            getInputParameterByEventNumber(eventNum).setValue(ivrObj.getParameterPhoneExternal());
        }else {
            getDropdownParameterByEventNumber(eventNum).selectOption(getRandomDropDownOption(String.format(dropdownParameterByEventNumberXpath, eventNum)));
        }

    }

    public void editIVR(String name){
        getButtonEditIvrByName(name).click();
        waitUntilAlertDisappear();
    }

}
