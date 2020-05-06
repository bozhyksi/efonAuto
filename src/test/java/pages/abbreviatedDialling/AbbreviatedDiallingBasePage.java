package pages.abbreviatedDialling;

import com.codeborne.selenide.SelenideElement;
import core.workers.menuNavigator.IMenuNavigator;
import core.workers.menuNavigator.MenuNavigator;
import pages.basePage.BasePage;

public class AbbreviatedDiallingBasePage extends BasePage{
    //<editor-fold desc="locators">
    private final String tabAbbreviatedNumbersXpath = "//a[contains(@href,\"/internal-numbers/overview\")]";
    private final String tabManageAbbreviatedNumbersXpath = "//a[contains(@href,\"/internal-numbers/manage\")]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getTabAbbreviatedNumbers() {
        return field(tabAbbreviatedNumbersXpath);
    }

    public SelenideElement getTabManageAbbreviatedNumbers() {
        return field(tabManageAbbreviatedNumbersXpath);
    }
    //</editor-fold>


}
