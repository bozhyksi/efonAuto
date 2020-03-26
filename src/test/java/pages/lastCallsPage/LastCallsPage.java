package pages.lastCallsPage;

import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;

public class LastCallsPage extends BasePage {
    private String tabMissedXpath = "//a[contains(@href,\"/last-calls/missed\")][text()=\"Missed\"]";
    private String tabIncomingXapth = "//a[contains(@href,\"last-calls/incoming\")]";
    private String tabOutgoingXapth = "//a[contains(@href,\"last-calls/incoming\")]";
    private String tabOutgoingByMonthXapth = "//a[contains(@href,\"/last-calls/outgoing-by-month\")]";


    

    public SelenideElement getTabIncoming() {
        return field(tabIncomingXapth);
    }

    public SelenideElement getTabMissed() {
        return field(tabMissedXpath);
    }

    public SelenideElement getTabOutgoing() {
        return field(tabOutgoingXapth);
    }

    public SelenideElement getTabOutgoingByMonth() {
        return field(tabOutgoingByMonthXapth);
    }
}
