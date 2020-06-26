package pages.queuesPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import tests.queuesPageTest.queueTestData.Queue;
import tests.userPageTests.userPageTestData.User;

import static com.codeborne.selenide.Condition.*;
import static pages.queuesPage.ReportsQueueTab.ReportBy.Day;

public class ReportsQueueTab extends QueuesBasePage {
    public enum ReportBy {
        Day,
        Month,
        Period
    }

    //<editor-fold desc="locators">
    private String tabDayXpath = "//a[contains(@href,\"call-queues/reports/day\")]";
    private String tabMonthXpath = "//a[contains(@href,\"call-queues/reports/month\")]";
    private String tabPeriodXpath = "//a[contains(@href,\"call-queues/reports/range\")]";

    private String dropdownReportXpath = "//label[text()=\"Report\"]/..//select";
    private String dropdownQueueXpath = "//label[text()=\"Queue\"]/..//select";
    private String dropdownAgentXpath = "//label[text()=\"Agent\"]/..//select";
    private String inputDayXpath = "//label[text()=\"Day\"]/..//input";
    private String inputMonthXpath = "//label[text()=\"Month\"]/..//input";
    private String inputFromXpath = "//label[text()=\"From\"]/..//input";
    private String inputToXpath = "//label[text()=\"To\"]/..//input";
    private String inputInactiveQueuesXpath = "//label[text()=\"Include inactive queues\"]/input";
    private String inputInactiveMembersXpath = "//label[text()=\"Include inactive members\"]/input";
    private String buttonSearchXpath = "//button[text()=\"Search\"]";
    private String fieldByTextXpath = "//td[contains(text(),\"%s\")]";


    //</editor-fold>

    //<editor-fold desc="get\set">


    public SelenideElement getFieldByText(String text) {
        return field(String.format(fieldByTextXpath,text));
    }

    public SelenideElement getButtonSearch() {
        return field(buttonSearchXpath);
    }

    public SelenideElement getInputInactiveMembers() {
        return field(inputInactiveMembersXpath);
    }

    public SelenideElement getInputInactiveQueues() {
        return field(inputInactiveQueuesXpath);
    }

    public SelenideElement getInputTo() {
        return field(inputToXpath);
    }

    public SelenideElement getInputFrom() {
        return field(inputFromXpath);
    }

    public SelenideElement getInputMonth() {
        return field(inputMonthXpath);
    }

    public SelenideElement getInputDay() {
        return field(inputDayXpath);
    }

    public SelenideElement getDropdownAgent() {
        return field(dropdownAgentXpath);
    }

    public SelenideElement getDropdownQueue() {
        return field(dropdownQueueXpath);
    }

    public SelenideElement getDropdownReport() {
        return field(dropdownReportXpath);
    }

    public SelenideElement getTabDay() {
        return field(tabDayXpath);
    }

    public SelenideElement getTabMonth() {
        return field(tabMonthXpath);
    }

    public SelenideElement getTabPeriod() {
        return field(tabPeriodXpath);
    }
    //</editor-fold>

    @Step("Goto report")
    public void goToReport(ReportBy tabName){
        getMenuTab(tabName).click();
        waitUntilAlertDisappear();
    }

    private SelenideElement getMenuTab(ReportBy tabName){
        switch (tabName){
            case Day: return getTabDay();
            case Month: return getTabMonth();
            case Period: return getTabPeriod();
            default:return getTabDay();
        }
    }

    @Step("Select report type")
    public void selectReportType(Queue.Report reportType){
        getDropdownReport().selectOptionContainingText(reportType.getReportType());
    }

    @Step("Create reports for every type")
    public void createReportForEveryType(ReportBy report, Queue queue, User user){
        goToReport(report);
        for (Queue.Report reportType : Queue.Report.values()) {
            selectReportType(reportType);
            getDropdownQueue().selectOptionContainingText(queue.getName());
            getDropdownAgent().selectOptionContainingText(user.getLastName());
            switch (report){
                case Day:
                    getInputDay().setValue(queue.getDay());
                    break;
                case Month:
                    getInputMonth().setValue(queue.getMonth());
                    break;
                case Period:
                    getInputFrom().setValue(queue.getFromDate()).pressTab();
                    getInputTo().setValue(queue.getToDate()).pressTab();
                    break;
            }
            getButtonSearch().click();
            waitUntilAlertDisappear();
            getFieldByText("No Items").should(exist);
        }
    }

    @Step("Create reports for every type")
    public void createReportForEveryType(ReportBy report, Queue queue, String agentName){
        goToReport(report);
        for (Queue.Report reportType : Queue.Report.values()) {
            selectReportType(reportType);
            getDropdownQueue().selectOptionContainingText(queue.getName());
            getDropdownAgent().selectOptionContainingText(agentName);
            switch (report){
                case Day:
                    getInputDay().setValue(queue.getDay());
                    break;
                case Month:
                    getInputMonth().setValue(queue.getMonth());
                    break;
                case Period:
                    getInputFrom().setValue(queue.getFromDate()).pressTab();
                    getInputTo().setValue(queue.getToDate()).pressTab();
                    break;
            }
            getButtonSearch().click();
            waitUntilAlertDisappear();
            getFieldByText("No Items").should(exist);
        }
    }
}
