package pages.IVRpage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import flow.PublicEnums;
import io.qameta.allure.Step;
import pages.IVRpage.IVRpagePopup.CreateEditIvrPopup;
import pages.basePage.BasePage;
import tests.IVRpageTests.IVRtestData.IVRtestData;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.queuesPageTest.queueTestData.Queue;
import tests.userPageTests.userPageTestData.User;

import static com.codeborne.selenide.Condition.exist;
import static core.configuration.preparations.eFonApp.confirmationPopup;
import static pages.basePage.BasePage.MenuTabsBasePage.IVRs;

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
    private final String fieldByText = "//td[contains(text(), \"%s\")]";
    private final String fieldCallRecordings = "//td[contains(text(), \"%s\")]//p[contains(text(),\"calls recording\")]";
    //</editor-fold>

    //<editor-fold desc="get\set">


    public SelenideElement getFieldCallRecordings(String nameIvr) {
        return field(String.format(fieldCallRecordings,nameIvr));
    }

    public SelenideElement getFieldNameByText(String text) {
        return field(String.format(fieldByText,text));
    }


    public SelenideElement getFieldPhoneByText(String text) {
        return field(String.format(fieldByText,text));
    }

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

    @Step("Click New IVR button")
    public CreateEditIvrPopup clickNewIvr(){
        getButtonNewIvr().click();
        waitUntilAlertDisappear();
        return new CreateEditIvrPopup();
    }

    @Step("Verify if IVR exists")
    public IVRpage verifyIfIvrExists(String ivrName){
        getFieldNameByText(ivrName).should(exist);
        return this;
    }

    @Step("Verify if IVR does not exists")
    public IVRpage verifyIfIvrDoesNotExist(String ivrName){
        getFieldNameByText(ivrName).shouldNot(exist);
        return this;
    }

    @Step("Delete IVR")
    public IVRpage deleteIvr(IVRtestData ... ivrs){
        goToMenuTab(MenuTabsBasePage.IVRs);
        for (IVRtestData ivr : ivrs) {
            getButtonDeleteIvrByName(ivr.getIvrName()).click();
            waitUntilAlertDisappear();
            confirmationPopup.getYesButton().click();
            waitUntilAlertDisappear();
            verifyIfIvrDoesNotExist(ivr.getIvrName());
        }
        return this;
    }

    @Step("Create IVR")
    public IVRpage createIvr(IVRtestData ivr){
        goToMenuTab(IVRs);
        clickNewIvr()
                .enterIvrName(ivr.getIvrName())
                .enterDisplayName(ivr.getIvrDisplName())
                .selectLanguage(ivr.getIvrLanguage())
                .selectNumber(ivr.getIvrNumber())
                .selectAnnouncement(ivr.getAnnouncement().getFileName())
                .saveChanges()
                .verifyIfIvrExists(ivr.getIvrName());
        return this;
    }

    @Step("Create IVR")
    public IVRpage createIvr(IVRtestData ivr, FileManagementTestData announcement){
        goToMenuTab(IVRs);
        clickNewIvr()
                .enterIvrName(ivr.getIvrName())
                .enterDisplayName(ivr.getIvrDisplName())
                .selectLanguage(ivr.getIvrLanguage())
                .selectNumber(ivr.getIvrNumber())
                .selectAnnouncement(announcement.getFileName())
                .saveChanges()
                .verifyIfIvrExists(ivr.getIvrName());
        return this;
    }

    @Step("Click edit IVR button")
    public CreateEditIvrPopup clickEditIvr(IVRtestData ivr){
        getButtonEditIvrByName(ivr.getIvrName()).click();
        waitUntilAlertDisappear();
        return new  CreateEditIvrPopup();
    }

    @Step("Verify if call recordings activated")
    public IVRpage verifyCallRecording(IVRtestData ivr, PublicEnums.State state){
        switch (state){
            case ACTIVATED:
                getFieldCallRecordings(ivr.getIvrName()).should(exist);
                break;
            case DEACTIVATED:
                getFieldCallRecordings(ivr.getIvrName()).shouldNot(exist);
                break;
        }
        return this;
    }


}
