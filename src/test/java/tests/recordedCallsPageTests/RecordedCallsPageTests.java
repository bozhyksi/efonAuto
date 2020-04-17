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
import static pages.basePage.BasePage.MenuTabsBasePage.RECORDED_CALLs;
import static pages.basePage.BasePage.MenuTabsBasePage.RECORDED_CALLs_CONFIGURATIONS;

@Listeners(CustomListeners.class)

public class RecordedCallsPageTests extends BaseTestMethods {

    @Description("Verify if user can configure FTP connection on Recorded Calls")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "recordedCallsPageTests"})
    public void VerifyIfUserCanConfigureFtpConnectionOnRecordedCalls(){
        step("Prepare test data");
        RecordedCalls recordedCall = new RecordedCalls();

        step("Login as navigate to Recorded Calls");
        login();
        basePage.goToMenuTab(RECORDED_CALLs).goToMenuTab(RECORDED_CALLs_CONFIGURATIONS);

        step("Activate and configure calls upload");
        recordedCallConfigPage.configureRecordedCallsFtpUpload(recordedCall);

        step("Check if all data were saved");
        basePage.goToMenuTab(RECORDED_CALLs).goToMenuTab(RECORDED_CALLs_CONFIGURATIONS);
        recordedCallConfigPage.verifyRecordedCallsFtpUploadConfiguration(recordedCall);
    }

    @Description("Verify if user can search recorded calls on Recorded calls overview")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "recordedCallsPageTests"})
    public void VerifyIfUserCanSearchRecordedCallsOnRecordedCallsOverview(){
        step("Prepare test data");
        RecordedCalls recordedCall = new RecordedCalls();

        step("Login as navigate to Recorded Calls -> Overview");
        login();
        basePage.getTabRecordedCalls().click();
        recordedCallsPage.getTabRecCallsOverview().click();

        step("Fill in Search field");
        recordedCallOverviewPage.getInputFromDate().setValue(recordedCall.getFromDate()).pressTab();
        recordedCallOverviewPage.getInputUntilDate().setValue(recordedCall.getUntilDate()).pressTab();
        recordedCallOverviewPage.getButtonSearch().click();
        waitUntilAlertDisappear();

        step("Verify search results");
        recordedCallOverviewPage.getFieldsFoundElements().shouldHaveSize(2);
        recordedCallOverviewPage.getFieldSourceByNumber(recordedCall.getSourceNumber1()).should(Condition.exist);
        recordedCallOverviewPage.getFieldSourceByNumber(recordedCall.getSourceNumber2()).should(Condition.exist);
    }
}
