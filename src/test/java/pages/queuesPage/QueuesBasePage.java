package pages.queuesPage;

import com.codeborne.selenide.SelenideElement;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.basePage.BasePage;

import static testsLowLevelUser.testData.AutotestUserData.autotestUserFullName;

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

    protected QueuesBasePage verifyIfDropDownDoesNotContainQueue(SelenideElement dropdown, String queueName){
        dropdown.click();
        Select select = new Select(dropdown);
        waitUntilAlertDisappear();
        Assert.assertEquals(select.getOptions().contains(queueName), false,
                "\nSearch dropdown should not contain queue: \""+queueName+"\"\n" +
                        "User \""+ autotestUserFullName +"\" is not a manager of queue: \""+queueName+"\"\n");
        return this;
    }
}
