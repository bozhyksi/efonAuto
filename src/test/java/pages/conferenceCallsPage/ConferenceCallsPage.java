package pages.conferenceCallsPage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;

public class ConferenceCallsPage  extends BasePage {
    //<editor-fold desc="Locators">
    private String buttonNewConferenceCallXpath = "//a[@type=\"button\" and text()=\"New conference call\"]";
    private String listNamesXpath = "//table//td[1]";
    private String listNumbersXpath = "//table//td[2]";
    private String listPinXpath = "//table//td[3]";
    private String listLanguageXpath = "//table//td[4]";
    private String listSubscriptLimitXpath = "//table//td[5]";
    private String buttonActiveByNameXpath = "//table//td[text()=\"%s\"]//following-sibling::td//a[@id=\"conferenceCallStatus\"]";
    private String buttonEditByName = "//table//td[text()=\"%s\"]//following-sibling::td//a[@id=\"editConferenceCall\"]";
    private String buttonDeleteByName = "//table//td[text()=\"%s\"]//following-sibling::td//a[@id=\"deleteConferenceCall\"]";

    private String dropdownConferenceCallNumbersXpath = "//h3[text()=\"Conference calls numbers\"]//following-sibling::select";
    private String checkboxCallsSuppressedNumXpath = "//label[text()=\"Calls with suppressed numbers\"]/input";
    private String dropdownForwardToXpath = "//select[@formcontrolname=\"forwardTo\"]";
    private String buttonSaveXpath = "//button[text()=\"Save\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getButtonNewConferenceCall() {
        return field(buttonNewConferenceCallXpath);
    }

    public ElementsCollection getListNames() {
        return fields(listNamesXpath);
    }

    public ElementsCollection getListNumbers() {
        return fields(listNumbersXpath);
    }

    public ElementsCollection getListPin() {
        return fields(listPinXpath);
    }

    public ElementsCollection getListLanguage() {
        return fields(listLanguageXpath);
    }

    public ElementsCollection getListSubscriptLimit() {
        return fields(listSubscriptLimitXpath);
    }

    public ElementsCollection getButtonActiveByName() {
        return fields(buttonActiveByNameXpath);
    }

    public SelenideElement getButtonEditByName(String confrName) {
        return field(String.format(buttonEditByName,confrName));
    }

    public SelenideElement getButtonDeleteByName(String confrName) {
        return field(String.format(buttonDeleteByName,confrName));
    }

    public SelenideElement getDropdownConferenceCallNumbers() {
        return field(dropdownConferenceCallNumbersXpath);
    }

    public SelenideElement getCheckboxCallsSuppressedNum() {
        return field(checkboxCallsSuppressedNumXpath);
    }

    public SelenideElement getDropdownForwardTo() {
        return field(dropdownForwardToXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }
    //</editor-fold>
}
