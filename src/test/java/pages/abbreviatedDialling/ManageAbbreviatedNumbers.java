package pages.abbreviatedDialling;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class ManageAbbreviatedNumbers extends AbbreviatedDiallingBasePage {
    //<editor-fold desc="//-- ManageAbbreviatedNumbers Locators --//">
    private String inputAddXpath = "//input[@formcontrolname=\"internalNumber\"]";
    private String inputAddAreaFromXpath = "//input[@formcontrolname=\"numberFrom\"]";
    private String inputAddAreaUntilXpath = "//input[@formcontrolname=\"numberUntil\"]";
    private String buttonAddXpath = "(//button[text()='Add'])[1]";
    private String buttonAddAreaXpath = "(//button[text()='Add'])[2]";
    //</editor-fold>

    //<editor-fold desc="//-- ManageAbbreviatedNumbers get\set methods --//">
    public SelenideElement getInputAdd() {
        return field(inputAddXpath);
    }

    public SelenideElement getInputAddAreaFrom() {
        return field(inputAddAreaFromXpath);
    }

    public SelenideElement getInputAddAreaUntil() {
        return field(inputAddAreaUntilXpath);
    }

    public SelenideElement getButtonAdd() {
        return field(buttonAddXpath);
    }

    public SelenideElement getButtonAddArea() {
        return field(buttonAddAreaXpath);
    }
    //</editor-fold>

    public void addSingleAbbrevNumber(String shortNumber){
        getInputAdd().setValue(shortNumber);
        getButtonAdd().click();
        Selenide.sleep(500);
    }

    public void addRangeAbbrevNumber(String startRangeNum, String endRangeNum){
        getInputAddAreaFrom().setValue(startRangeNum);
        getInputAddAreaUntil().setValue(endRangeNum);
        getButtonAddArea().click();
    }

}
