package testsLowLevelUser.lastCallsUserPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testsLowLevelUser.lastCallsUserPageTests.lastCallsUserPageTestData.LastCallsUserPageTestData;

import static pages.basePage.BasePage.ItemsPerPage.*;
import static pages.basePage.BasePage.MenuTabsBasePage.*;

@Listeners(CustomListeners.class)

public class LastCallsUserPageTests extends BaseTestMethods {

    @Description("Check if low-level user can create MISSED last calls report")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "lastCallsUserPageTests"})
    public void CheckIfLowLevelUserCanCreateMissedLastCallsReport(){
        LastCallsUserPageTestData dto = new LastCallsUserPageTestData();

        loginAsLowLevelUser();
        basePageLowLevelUser
                .goToMenuTab(LAST_CALLS)
                .goToMenuTab(LAST_CALLS_MISSED);
        missedLastCallsUserPage
                .selectPhoneNumber(dto.getPhoneNumber())
                .setDateFrom(dto.getFromDate())
                .setDateUntil(dto.getUntilDate())
                .clickSearch()
                .validateResults()
                .setItemsPerPage(_100);
    }

    @Description("Check if phones dropdown include only users related number on MISSED last calls")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "lastCallsUserPageTests"})
    public void CheckIfPhonesDropdownIncludeOnlyUsersRelatedNumberOnMissedLastCalls(){
        loginAsLowLevelUser();
        basePageLowLevelUser
                .goToMenuTab(LAST_CALLS)
                .goToMenuTab(LAST_CALLS_MISSED);
        missedLastCallsUserPage
                .validatePhoneDropDownItems();
    }

    @Description("Check if low-level user can create INCOMING last calls report")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "lastCallsUserPageTests"})
    public void CheckIfLowLevelUserCanCreateIncomingLastCallsReport(){
        LastCallsUserPageTestData dto = new LastCallsUserPageTestData();

        loginAsLowLevelUser();
        basePageLowLevelUser
                .goToMenuTab(LAST_CALLS)
                .goToMenuTab(LAST_CALLS_INCOMING);
        incomingLastCallsUserPage
                .selectPhoneNumber(dto.getPhoneNumber())
                .setDateFrom(dto.getFromDate())
                .setDateUntil(dto.getUntilDate())
                .clickSearch()
                .validateResults()
                .setItemsPerPage(_100);
    }

    @Description("Check if phones dropdown include only users related number on INCOMING last calls")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "lastCallsUserPageTests"})
    public void CheckIfPhonesDropdownIncludeOnlyUsersRelatedNumberOnIncomingLastCalls(){
        loginAsLowLevelUser();
        basePageLowLevelUser
                .goToMenuTab(LAST_CALLS)
                .goToMenuTab(LAST_CALLS_INCOMING);
        incomingLastCallsUserPage
                .validatePhoneDropDownItems();
    }

    @Description("Check if low-level user can create OUTGOING last calls report")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "lastCallsUserPageTests"})
    public void CheckIfLowLevelUserCanCreateOutgoingLastCallsReport(){
        LastCallsUserPageTestData dto = new LastCallsUserPageTestData();

        loginAsLowLevelUser();
        basePageLowLevelUser
                .goToMenuTab(LAST_CALLS)
                .goToMenuTab(LAST_CALLS_OUTGOING);
        outgoingLastCallsUserPage
                .selectPhoneNumber(dto.getPhoneNumber())
                .setDateFrom(dto.getFromDate())
                .setDateUntil(dto.getUntilDate())
                .clickSearch()
                .validateResults()
                .setItemsPerPage(_100);
    }

    @Description("Check if phones dropdown include only users related number on OUTGOING last calls")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "lastCallsUserPageTests"})
    public void CheckIfPhonesDropdownIncludeOnlyUsersRelatedNumberOnOutgoingLastCalls(){
        loginAsLowLevelUser();
        basePageLowLevelUser
                .goToMenuTab(LAST_CALLS)
                .goToMenuTab(LAST_CALLS_OUTGOING);
        outgoingLastCallsUserPage
                .validatePhoneDropDownItems();
    }

    @Description("Check if low-level user can create ALL MISSED calls report")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "lastCallsUserPageTests"})
    public void CheckIfUserCanCreateAllMissedCallsReport(){
        LastCallsUserPageTestData dto = new LastCallsUserPageTestData();

        loginAsLowLevelUser();
        basePageLowLevelUser
                .goToMenuTab(LAST_CALLS)
                .goToMenuTab(LAST_CALLS_MISSED);
        missedLastCallsUserPage
                .activateAllNumbersCheckBox()
                .setDateFrom(dto.getFromDate())
                .setDateUntil(dto.getUntilDate())
                .clickSearch()
                .validateResults()
                .setItemsPerPage(_100);
    }

    @Description("Check if low-level user can create ALL INCOMING calls report")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "lastCallsUserPageTests"})
    public void CheckIfUserCanCreateAllIncomingCallsReport(){
        LastCallsUserPageTestData dto = new LastCallsUserPageTestData();

        loginAsLowLevelUser();
        basePageLowLevelUser
                .goToMenuTab(LAST_CALLS)
                .goToMenuTab(LAST_CALLS_INCOMING);
        incomingLastCallsUserPage
                .activateAllNumbersCheckBox()
                .setDateFrom(dto.getFromDate())
                .setDateUntil(dto.getUntilDate())
                .clickSearch()
                .validateResults()
                .setItemsPerPage(_50);
    }

    @Description("Check if low-level user can create ALL OUTGOING calls report")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "lastCallsUserPageTests"})
    public void CheckIfUserCanCreateAllOutgoingCallsReport(){
        LastCallsUserPageTestData dto = new LastCallsUserPageTestData();

        loginAsLowLevelUser();
        basePageLowLevelUser
                .goToMenuTab(LAST_CALLS)
                .goToMenuTab(LAST_CALLS_OUTGOING);
        outgoingLastCallsUserPage
                .activateAllNumbersCheckBox()
                .setDateFrom(dto.getFromDate())
                .setDateUntil(dto.getUntilDate())
                .clickSearch()
                .validateResults()
                .setItemsPerPage(_25);
    }
}
