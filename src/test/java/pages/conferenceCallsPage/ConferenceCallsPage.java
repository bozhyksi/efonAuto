package pages.conferenceCallsPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.basePage.BasePage;
import pages.conferenceCallsPage.conferenceCallsPagePopup.CreateNewConferenceCallPopup;
import tests.сonferenceCallsPageTests.ConferenceCallTestData.ConferenceCallTestData;

import static com.codeborne.selenide.Condition.exist;
import static core.configuration.preparations.eFonApp.confirmationPopup;

public class ConferenceCallsPage  extends BasePage {

    //<editor-fold desc="Locators">
    private String buttonNewConferenceCallXpath = "//a[@type=\"button\" and text()=\"New conference call\"]";
    private String fieldByTextXpath = "//td[contains(text(),\"%s\")]";
    private String buttonActiveByNameXpath = "//td[contains(text(),\"%s\")]/..//a[@id=\"conferenceCallStatus\"]";
    private String buttonEditByName = "//td[contains(text(),\"%s\")]/..//a[@id=\"editConferenceCall\"]";
    private String buttonDeleteByName = "//td[contains(text(),\"%s\")]/..//a[@id=\"deleteConferenceCall\"]";;

    private String dropdownConferenceCallNumbersXpath = "//h3[text()=\"ConferenceCallTestData calls numbers\"]//following-sibling::select";
    private String checkboxCallsSuppressedNumXpath = "//label[text()=\"Calls with suppressed numbers\"]/input";
    private String dropdownForwardToXpath = "//select[@formcontrolname=\"forwardTo\"]";
    private String buttonSaveXpath = "//button[text()=\"Save\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getFieldByText(String text) {
        return field(String.format(fieldByTextXpath,text));
    }

    public SelenideElement getFieldLanguageByText(String text) {
        return field(String.format(fieldByTextXpath,text));
    }

    public SelenideElement getFieldPinByText(String text) {
        return field(String.format(fieldByTextXpath,text));
    }

    public SelenideElement getFieldNumberByText(String text) {
        return field(String.format(fieldByTextXpath,text));
    }

    public SelenideElement getFieldNameByText(String text) {
        return field(String.format(fieldByTextXpath,text));
    }

    public SelenideElement getButtonNewConferenceCall() {
        return field(buttonNewConferenceCallXpath);
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


    @Step("Click Create New ConferenceCallTestData Call")
    public CreateNewConferenceCallPopup clickNewConfCall(){
        getButtonNewConferenceCall().click();
        waitUntilAlertDisappear();
        return new CreateNewConferenceCallPopup();
    }

    @Step("Create ConferenceCallTestData Call")
    public ConferenceCallsPage createConfCall(ConferenceCallTestData confCall){
        goToMenuTab(MenuTabsBasePage.CONFERENCE_CALLS);
        clickNewConfCall()
                .enterName(confCall.getName())
                .selectNumber(confCall.getConferenceNumber())
                .enterPIN(confCall.getPin())
                .selectLanguage(confCall.getLanguage())
                .saveChanges()
                .verifyConfCallExists(confCall.getName());
        return this;
    }

    @Step("Verify if Conference call exists in the list")
    public ConferenceCallsPage verifyConfCallExists(String name){
        getFieldByText(name).should(exist);
        return this;
    }

    @Step("Verify if Conference call does NOT exist in the list")
    public ConferenceCallsPage verifyConfCallNotExist(String name){
        getFieldByText(name).shouldNot(exist);
        return this;
    }

    @Step("Delete Conference Call")
    public ConferenceCallsPage deleteConfCall(ConferenceCallTestData ... confCalls){
        for (ConferenceCallTestData entry : confCalls) {
            getButtonDeleteByName(entry.getName()).click();
            waitUntilAlertDisappear();
            confirmationPopup.getYesButton().click();
            waitUntilAlertDisappear();
            verifyConfCallNotExist(entry.getName());
            waitUntilAlertDisappear();
        }
        return this;
    }

    @Step("Click Edit conference call button")
    public CreateNewConferenceCallPopup clickEdit(ConferenceCallTestData confCall){
        getButtonEditByName(confCall.getName()).click();
        waitUntilAlertDisappear();
        return new CreateNewConferenceCallPopup();
    }


}
