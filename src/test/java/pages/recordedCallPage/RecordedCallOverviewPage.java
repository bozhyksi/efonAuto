package pages.recordedCallPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import tests.recordedCallsPageTests.recordedCallsTestData.RecordedCalls;

public class RecordedCallOverviewPage extends RecordedCallsBasePage {

    //<editor-fold desc="locators">
    private String inputSearchXpath = "//input[@placeholder=\"Source, Destination, Recorded calls account\"]";
    private String inputFromDateXpath = "//label[text()=\"From\"]//following-sibling::div//input";
    private String inputUntilDateXpath = "//label[text()=\"Until\"]//following-sibling::div//input";
    private String buttonSearchXapth = "//button[text()=\"Search\"]";
    private String fieldSourceByNumberXpath = "//td[contains(text(),\"%s\")]";
    private String fieldsFoundElementsXpath = "//table[contains(@class,\"dataTable\")]//tbody//tr";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public ElementsCollection getFieldsFoundElements() {
        return fields(fieldsFoundElementsXpath);
    }

    public SelenideElement getFieldSourceByNumber(String number) {
        return field(String.format(fieldSourceByNumberXpath, number));
    }

    public SelenideElement getInputSearch() {
        return field(inputSearchXpath);
    }

    public SelenideElement getInputFromDate() {
        return field(inputFromDateXpath);
    }

    public SelenideElement getInputUntilDate() {
        return field(inputUntilDateXpath);
    }

    public SelenideElement getButtonSearch() {
        return field(buttonSearchXapth);
    }
    //</editor-fold>

    @Step("Enter Search phrase")
    public RecordedCallOverviewPage enterSearchValue(String val) {
        getInputSearch().setValue(val);
        return this;
    }

    @Step("Enter From date")
    public RecordedCallOverviewPage enterFromDate(String fromDate) {
        getInputFromDate().setValue(fromDate).pressTab();
        return this;
    }

    @Step("Enter TO date")
    public RecordedCallOverviewPage enterToDate(String toDate) {
        getInputUntilDate().setValue(toDate).pressTab();
        return this;
    }

    @Step("Click Search")
    public RecordedCallOverviewPage clickSearch() {
        getButtonSearch().click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Verify search results")
    public RecordedCallOverviewPage verifyResults(RecordedCalls recordedCall) {
        getFieldsFoundElements().shouldHaveSize(2);
        getFieldSourceByNumber(recordedCall.getSourceNumber1()).should(Condition.exist);
        getFieldSourceByNumber(recordedCall.getSourceNumber2()).should(Condition.exist);
        return this;
    }

}
