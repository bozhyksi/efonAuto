package tests.lastCallsPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.lastCallsPageTests.lastCallsTestData.LastCallsTestData;

import static io.qameta.allure.Allure.step;

@Listeners(CustomListeners.class)

public class LastCallsPageTests extends BaseTestMethods {

    @Description("Verify if user can get last calls by MISSED numbers")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "lastCallsPageTests"})
    public void VerifyIfUserCanGetLastCallsByMissingNumbers(){
        step("Prepare test data");
        LastCallsTestData lastCalls = new LastCallsTestData();

        step("Login the system");
        login();

        step("Goto Last Calls -> MISSED numbers");
        basePage.getTabLastCalls().click();
        lastCallsPage.getTabMissed().click();

        step("Fill in search parameters");
        lastCallsPage.getCheckboxAllNumbers().click();
        lastCallsPage.getInputFromDate().setValue(lastCalls.getFromDate());
        lastCallsPage.getInputToDate().setValue(lastCalls.getToDate());

        step("Click Search and verify found data");
        lastCallsPage.getButtonSearch().click();
        waitUntilAlertDisappear();

    }

    @Description("Verify if user can get last calls by Incoming numbers")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "lastCallsPageTests"})
    public void VerifyIfUserCanGetLastCallsByIncomingNumbers(){
        step("Prepare test data");
        LastCallsTestData lastCalls = new LastCallsTestData();

        step("Login the system");
        login();

        step("Goto Last Calls -> Incoming numbers");
        basePage.getTabLastCalls().click();
        lastCallsPage.getTabIncoming().click();

        step("Fill in search parameters");
        lastCallsPage.getCheckboxAllNumbers().click();
        lastCallsPage.getInputFromDate().setValue(lastCalls.getFromDate());
        lastCallsPage.getInputToDate().setValue(lastCalls.getToDate());

        step("Click Search and verify found data");
        lastCallsPage.getButtonSearch().click();
        waitUntilAlertDisappear();

    }

    @Description("Verify if user can get last calls by OUTGOING numbers")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "lastCallsPageTests"})
    public void VerifyIfUserCanGetLastCallsByOutgoingNumbers(){
        step("Prepare test data");
        LastCallsTestData lastCalls = new LastCallsTestData();

        step("Login the system");
        login();

        step("Goto Last Calls -> Incoming numbers");
        basePage.getTabLastCalls().click();
        lastCallsPage.getTabOutgoing().click();

        step("Fill in search parameters");
        lastCallsPage.getCheckboxAllNumbers().click();
        lastCallsPage.getInputFromDate().setValue(lastCalls.getFromDate());
        lastCallsPage.getInputToDate().setValue(lastCalls.getToDate());

        step("Click Search and verify found data");
        lastCallsPage.getButtonSearch().click();
        waitUntilAlertDisappear();

    }

    @Description("Verify if user can get last calls by OUTGOING BY MONTH numbers")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "lastCallsPageTests"})
    public void VerifyIfUserCanGetLastCallsByOutgoingByMonthNumbers(){
        step("Prepare test data");
        LastCallsTestData lastCalls = new LastCallsTestData();

        step("Login the system");
        login();

        step("Goto Last Calls -> Incoming numbers");
        basePage.getTabLastCalls().click();
        lastCallsPage.getTabOutgoingByMonth().click();

        step("Fill in search parameters");
        lastCallsPage.getCheckboxAllNumbers().click();
        lastCallsPage.getInputMonth().setValue(lastCalls.getMonthDate());

        step("Click Search and verify found data");
        lastCallsPage.getButtonSearch().click();
        waitUntilAlertDisappear();

    }


}
