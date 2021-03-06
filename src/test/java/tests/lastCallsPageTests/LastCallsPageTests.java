package tests.lastCallsPageTests;

import com.codeborne.selenide.Condition;
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

    @Description("Verify if user can search MISSED last calls")
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

        step("Verify search results");
        missedCallsTab.getFieldDestinationNumber(lastCalls.getDesctinationNumber()).should(Condition.exist);
        alertPopup.getAlertDialog().shouldNotBe(Condition.appear,Condition.visible);
    }

    @Description("Verify if user can search INCOMING last calls")
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

        step("Verify search results");
        incomingCallsTab.getFieldDestinationNumber(lastCalls.getDesctinationNumber()).should(Condition.exist);
        alertPopup.getAlertDialog().shouldNotBe(Condition.appear,Condition.visible);
    }

    @Description("Verify if user can search OUTGOING last calls")
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

        step("Verify search results");
        outgoingCallsTab.getFieldDestinationNumber(lastCalls.getDesctinationNumber()).should(Condition.exist);
        alertPopup.getAlertDialog().shouldNotBe(Condition.appear,Condition.visible);

    }

}
