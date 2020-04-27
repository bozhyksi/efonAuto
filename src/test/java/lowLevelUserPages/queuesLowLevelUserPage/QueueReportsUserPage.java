package lowLevelUserPages.queuesLowLevelUserPage;

import pages.queuesPage.ReportsQueueTab;
import tests.queuesPageTest.queueTestData.Queue;

import static com.codeborne.selenide.Condition.exist;

public class QueueReportsUserPage extends QueuesBaseUserPage {
    ReportsQueueTab reportsQueueTab = new ReportsQueueTab();

    public void createDayReports(ReportsQueueTab.ReportBy report, Queue queue){
        reportsQueueTab.goToReport(report);
        for (Queue.Report reportType : Queue.Report.values()) {
            reportsQueueTab.selectReportType(reportType);
            reportsQueueTab.getDropdownQueue().selectOptionContainingText(queue.getName());
            reportsQueueTab.getDropdownAgent().selectOptionContainingText("All agents");
            switch (report){
                case Day:
                    reportsQueueTab.getInputDay().setValue(queue.getDay());
                    break;
                case Month:
                    reportsQueueTab.getInputMonth().setValue(queue.getMonth());
                    break;
                case Period:
                    reportsQueueTab.getInputFrom().setValue(queue.getFromDate()).pressTab();
                    reportsQueueTab.getInputTo().setValue(queue.getToDate()).pressTab();
                    break;
            }
            reportsQueueTab.getButtonSearch().click();
            waitUntilAlertDisappear();
            reportsQueueTab.getFieldByText("No Items").should(exist);
        }
    }
}
