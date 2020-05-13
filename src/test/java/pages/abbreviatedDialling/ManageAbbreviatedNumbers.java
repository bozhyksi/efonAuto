package pages.abbreviatedDialling;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static core.configuration.preparations.eFonApp.dataBaseWorker;

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
        waitUntilAlertDisappear();
        getButtonAdd().click();
    }

    @Step("Create single abbreviated number")
    public void addSingleAbbrevNumber(AbbreviatedDialling shortNumber){
        getInputAdd().setValue(shortNumber.getSingleShortNum());
        waitUntilAlertDisappear();
        getButtonAdd().click();
        waitUntilAlertDisappear();
    }

    public void addRangeAbbrevNumber(String startRangeNum, String endRangeNum){
        getInputAddAreaFrom().setValue(startRangeNum);
        getInputAddAreaUntil().setValue(endRangeNum);
        getButtonAddArea().click();
    }

}
