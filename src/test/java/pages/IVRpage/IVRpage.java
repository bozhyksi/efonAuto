package pages.IVRpage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;

public class IVRpage extends BasePage {
    //<editor-fold desc="Locators">
    private String buttonNewIvrXpath = "//a[text()=\"New IVR\"]";
    private String listNameXpath = "//table//td[1]";
    private String listDisplayNameXpath = "//table//td[2]";
    private String listNumberXpath = "//table//td[3]";
    private String buttonDeleteIvrByName = "//td[1][contains(text(),\"%s\")]//ancestor::tr//a[@id=\"deleteIvr\"]";
    private String buttonEditIvrByName = "//td[1][contains(text(),\"%s\")]//ancestor::tr//a[@id=\"editIvr\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
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
}
