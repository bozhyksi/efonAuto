package pages.recordedCallPage;

import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;

public class RecordedCallsPage extends BasePage {
    private String tabRecCallsOverviewXpath = "//a[contains(@href,\"recorded-calls/overview\")]";
    private String tabRecCallsConfigurationsXpath = "//a[contains(@href,\"recorded-calls/configuration\")]";

    public SelenideElement getTabRecCallsOverview() {
        return field(tabRecCallsOverviewXpath);
    }

    public SelenideElement getTabRecCallsConfigurations() {
        return field(tabRecCallsConfigurationsXpath);
    }
}
