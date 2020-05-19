package lowLevelUserPages.queuesLowLevelUserPage;

import io.qameta.allure.Step;
import pages.queuesPage.ReportsQueueTab;
import tests.queuesPageTest.queueTestData.Queue;

import static com.codeborne.selenide.Condition.exist;
import static core.configuration.preparations.eFonApp.recordingsQueuePage;
import static core.configuration.preparations.eFonApp.reportsQueuePage;

public class QueueReportsUserPage extends QueuesBaseUserPage {

    public void createDayReports(ReportsQueueTab.ReportBy report, Queue queue){
        reportsQueuePage.goToReport(report);
        for (Queue.Report reportType : Queue.Report.values()) {
            reportsQueuePage.selectReportType(reportType);
            reportsQueuePage.getDropdownQueue().selectOptionContainingText(queue.getName());
            reportsQueuePage.getDropdownAgent().selectOptionContainingText("All agents");
            switch (report){
                case Day:
                    reportsQueuePage.getInputDay().setValue(queue.getDay());
                    break;
                case Month:
                    reportsQueuePage.getInputMonth().setValue(queue.getMonth());
                    break;
                case Period:
                    reportsQueuePage.getInputFrom().setValue(queue.getFromDate()).pressTab();
                    reportsQueuePage.getInputTo().setValue(queue.getToDate()).pressTab();
                    break;
            }
            reportsQueuePage.getButtonSearch().click();
            waitUntilAlertDisappear();
            reportsQueuePage.getFieldByText("No Items").should(exist);
        }
    }

}
