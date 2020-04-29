package pages.huntGroupPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;

import static com.codeborne.selenide.Condition.exist;

public class HuntGroupPage extends BasePage {
    //<editor-fold desc="Locators">
    private String buttonCreateNewHuntGroupXpath = "//a[text()='New hunt group']";
    private String buttonDeleteByNameXpath = "//td[1]/div[contains(text(),\"%s\")]//ancestor::tr//a[@id=\"deleteHuntGroup\"]";
    private String buttonEditByNameXpath = "//td[1]/div[contains(text(),\"%s\")]//ancestor::tr//a[@id=\"editHuntGroup\"]";
    private String fieldNameByTextXpath = "//table[@role=\"grid\"]//td[1]/div[1][text()[contains(.,\"%s\")]]";
    private String fieldDisplayNameByTextXpath = "//table[@role=\"grid\"]//td[2][contains(text(),\"%s\")]";
    private String fieldNumberByTextXpath = "//table[@role=\"grid\"]//td[3][contains(text(),\"%s\")]";
    private String listNumberXpath = "//table[@role=\"grid\"]//td[3]";
    private String dropdownHuntGroupNumberXpath = "//h3[text()=\"Hunt Group numbers\"]//following-sibling::select";
    private String checkboxBlockIncomingCallsXpath = "//label[text()=\"Block incoming calls\"]//input";
    private String dropdownForwardToXpath = "//select[@formcontrolname=\"forwardTo\"]";
    private String checkboxCallWithSupprNumbersXpath = "//label[text()=\"Calls with suppressed numbers\"]//input";
    private String checkboxUseBlockListXpath = "//label[text()=\"Use blocklist\"]//input";
    private String dropdownUseBlockListXpath = "//select[@formcontrolname=\"blockListType\"]";
    private String buttonSaveXpath = "//button[text()='Save']";
    private String fieldCallRecordingsIconByTextXpath = "//td[contains(text(),\"%s\")]/..//a[@id=\"callRecording\"]";
    private String fieldActivatedCallRecordByTextXpath = "//td[contains(text(),\"%s\")]/..//p[text()=\"has activated calls recording\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">


    public SelenideElement getFieldNumberByText(String text) {
        return field(String.format(fieldNumberByTextXpath, text));
    }

    public SelenideElement getFieldDisplayNameByText(String text) {
        return field(String.format(fieldDisplayNameByTextXpath, text));
    }

    public SelenideElement getFieldActivatedCallRecordByText(String text) {
        return field(String.format(fieldActivatedCallRecordByTextXpath,text));
    }

    public SelenideElement getFieldCallRecordingsIconByText(String text) {
        return field(String.format(fieldCallRecordingsIconByTextXpath, text));
    }

    public SelenideElement getButtonCreateNewHuntGroup() {
        return field(buttonCreateNewHuntGroupXpath);
    }

    public SelenideElement getButtonDeleteByName(String name) {
        return field(String.format(buttonDeleteByNameXpath, name));
    }

    public SelenideElement getButtonEditByName(String name) {
        return field(String.format(buttonEditByNameXpath, name));
    }

    public SelenideElement getfieldNameByText(String name) {
        return field(String.format(fieldNameByTextXpath, name));
    }

    public ElementsCollection getListNumber() {
        return fields(listNumberXpath);
    }

    public SelenideElement getDropdownHuntGroupNumber() {
        return field(dropdownHuntGroupNumberXpath);
    }

    public SelenideElement getCheckboxBlockIncomingCalls() {
        return field(checkboxBlockIncomingCallsXpath);
    }

    public SelenideElement getDropdownForwardTo() {
        return field(dropdownForwardToXpath);
    }

    public SelenideElement getCheckboxCallWithSupprNumbers() {
        return field(checkboxCallWithSupprNumbersXpath);
    }

    public SelenideElement getCheckboxUseBlockList() {
        return field(checkboxUseBlockListXpath);
    }

    public SelenideElement getDropdownUseBlockList() {
        return field(dropdownUseBlockListXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }
    //</editor-fold>

    public void openEditPopup(HuntGroup huntGroup){
        getButtonEditByName(huntGroup.getHuntGroupName()).click();
        waitUntilAlertDisappear();
    }

    public void editHuntGroup(HuntGroup huntGroup){
        getButtonEditByName(huntGroup.getHuntGroupName()).click();
        waitUntilAlertDisappear();
    }

    public void editHuntGroup(String huntGroupName){
        getButtonEditByName(huntGroupName).click();
        waitUntilAlertDisappear();
    }

    public void verifyIfCallRecordingWasActivated(HuntGroup huntGroup){
        refreshPage();
        getFieldCallRecordingsIconByText(huntGroup.getHuntGroupNumber()).should(exist);
        getFieldActivatedCallRecordByText(huntGroup.getHuntGroupNumber()).should(exist);
    }
}
