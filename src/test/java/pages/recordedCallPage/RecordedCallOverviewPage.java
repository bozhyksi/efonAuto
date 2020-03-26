package pages.recordedCallPage;

import com.codeborne.selenide.SelenideElement;

public class RecordedCallOverviewPage extends RecordedCallsPage {
    private String inputSearchXpath = "//input[@placeholder=\"Source, Destination, Recorded calls account\"]";
    private String inputFromDateXpath = "//label[text()=\"From\"]//following-sibling::div//input";
    private String inputUntilDateXpath = "//label[text()=\"Until\"]//following-sibling::div//input";
    private String buttonSearchXapth = "//button[text()=\"Search\"]";

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
