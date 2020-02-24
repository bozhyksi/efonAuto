package pages.queuesPage;

import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;

public class QueuesBasePage extends BasePage {
    private String tabConfigureQueuesXpath = "//a[@href=\"/portal/call-queues/overview\"]";
    private String tabStatusQueuesXpath = "//a[@href=\"/portal/call-queues/status\"]";
    private String tabRecordingsXpath = "//a[@href=\"/portal/call-queues/records\"]";
    private String tabReportXpath = "//a[@href=\"/portal/call-queues/reports\"]";

    public SelenideElement getTabConfigureQueues() {
        return field(tabConfigureQueuesXpath);
    }

    public SelenideElement getTabStatusQueues() {
        return field(tabStatusQueuesXpath);
    }

    public SelenideElement getTabReport() {
        return field(tabReportXpath);
    }

    public SelenideElement getTabRecordings() {
        return field(tabRecordingsXpath);
    }
}
