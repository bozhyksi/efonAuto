package tests.recordedCallsPageTests;

import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.recordedCallsPageTests.recordedCallsTestData.RecordedCalls;

import static io.qameta.allure.Allure.step;

@Listeners(CustomListeners.class)

public class RecordedCallsPageTests extends BaseTestMethods {

    @Description("Verify if user can configure FTP connection on Recorded Calls")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "recordedCallsPageTests"}, enabled = false)//need valid FTP configurations for test
    public void VerifyIfUserCanConfigureFtpConnectionOnRecordedCalls(){
        step("Prepare test data");
        RecordedCalls recordedCall = new RecordedCalls();

        step("Login as navigate to Recorded Calls");
        login();
        basePage.getTabRecordedCalls().click();
        recordedCallsPage.getTabRecCallsConfigurations().click();

        step("Activate and configure calls upload");
        recordedCallConfigPage.getCheckboxUpload().click();
        recordedCallConfigPage.getInputHost().setValue(recordedCall.getHost());
        recordedCallConfigPage.getInputPort().setValue(recordedCall.getPort());
        recordedCallConfigPage.getCheckboxUseFtp().click();
        recordedCallConfigPage.getInputUser().setValue(recordedCall.getUser());
        recordedCallConfigPage.getInputPassword().setValue(recordedCall.getPass());
        recordedCallConfigPage.getInputPath().setValue(recordedCall.getPath());
        recordedCallConfigPage.getButtonSave().click();
        refreshPage();
        waitUntilAlertDisappear();

        step("Check if all data were saved");
        basePage.getTabRecordedCalls().click();
        recordedCallsPage.getTabRecCallsConfigurations().click();

        recordedCallConfigPage.getCheckboxUpload().shouldBe(Condition.selected);
        recordedCallConfigPage.getInputHost().shouldHave(Condition.value(recordedCall.getHost()));
        recordedCallConfigPage.getInputPort().shouldHave(Condition.value(recordedCall.getPort()));
        recordedCallConfigPage.getCheckboxUseFtp().shouldBe(Condition.selected);
        recordedCallConfigPage.getInputPath().shouldHave(Condition.value(recordedCall.getPath()));
        recordedCallConfigPage.getInputUser().shouldHave(Condition.value(recordedCall.getUser()));
        recordedCallConfigPage.getInputPassword().shouldHave(Condition.value(recordedCall.getPass()));
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
