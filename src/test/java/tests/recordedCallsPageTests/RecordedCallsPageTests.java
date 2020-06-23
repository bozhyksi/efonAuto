package tests.recordedCallsPageTests;

import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.basePage.BasePage;
import tests.recordedCallsPageTests.recordedCallsTestData.RecordedCalls;

import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.*;

@Listeners(CustomListeners.class)

public class RecordedCallsPageTests extends BaseTestMethods {

    @Description("Verify if user can configure FTP connection on Recorded Calls")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "recordedCallsPageTests"})
    public void configureFtpConnectionTest(){

        RecordedCalls recordedCall = new RecordedCalls();

        login()
                .goToMenuTab(RECORDED_CALLs)
                .goToMenuTab(RECORDED_CALLs_CONFIGURATIONS);
        recordedCallConfigPage
                .configureRecordedCallsFtpUpload(recordedCall)
                .verifyRecordedCallsFtpUploadConfiguration(recordedCall);
    }

    @Description("Verify if user can search recorded calls on Recorded calls overview")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "recordedCallsPageTests"})
    public void searchRecordedCallsTest(){

        RecordedCalls recordedCall = new RecordedCalls();

        login()
                .goToMenuTab(RECORDED_CALLs)
                .goToMenuTab(RECORDED_CALLs_OVERVIEW);
        recordedCallOverviewPage
                .enterFromDate(recordedCall.getFromDate())
                .enterToDate(recordedCall.getUntilDate())
                .clickSearch()
                .verifyResults(recordedCall);
    }
}
