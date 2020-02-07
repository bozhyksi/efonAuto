package pages.abbreviatedDialling;

import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;

public class AbbreviatedDiallingBasePage extends BasePage {
    private String tabAbbreviatedNumbersXpath = "//a[@href=\"/portal/internal-numbers/overview\"]";
    private String tabManageAbbreviatedNumbersXpath = "//a[@href=\"/portal/internal-numbers/manage\"]";


    public SelenideElement getTabAbbreviatedNumbers() {
        return field(tabAbbreviatedNumbersXpath);
    }

    public SelenideElement getTabManageAbbreviatedNumbers() {
        return field(tabManageAbbreviatedNumbersXpath);
    }
}
