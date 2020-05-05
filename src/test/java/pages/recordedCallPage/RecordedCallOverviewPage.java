package pages.recordedCallPage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class RecordedCallOverviewPage extends RecordedCallsBasePage {
    private String inputSearchXpath = "//input[@placeholder=\"Source, Destination, Recorded calls account\"]";
    private String inputFromDateXpath = "//label[text()=\"From\"]//following-sibling::div//input";
    private String inputUntilDateXpath = "//label[text()=\"Until\"]//following-sibling::div//input";
    private String buttonSearchXapth = "//button[text()=\"Search\"]";
    private String fieldSourceByNumberXpath = "//td[contains(text(),\"%s\")]";
    private String fieldsFoundElementsXpath = "//table[contains(@class,\"dataTable\")]//tbody//tr";


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
}
