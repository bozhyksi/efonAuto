package testsLowLevelUser.callForwardingUserPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static api.baseApiMethods.UserApi.createUsersApi;
import static api.baseApiMethods.UserApi.deleteUsersApi;
import static pages.basePage.BasePage.MenuTabsBasePage.CALL_FORWARDING;

@Listeners(CustomListeners.class)

public class CallForwardingUserPageTests extends BaseTestMethods {
    ArrayList<User> userList = new ArrayList<>();

    @Description("Verify if user can configure After section in call forwarding")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "callForwardingUserPageTests"})
    public void configureAfterSectionInCallForwardingTest(){
        User user = new User();
        userList.add(user);

        createUsersApi(user);
        login(user.getLoginEmail(),user.getLoginPassword())
                .goToMenuTab(CALL_FORWARDING);
        callForwardingPage
                .selectNumber(user.getPhoneNumber())
                .activateAfter()
                .selectForwardTo("Phone")
                .enterDelay(user.getAfterDelay())
                .enterPhone(user.getForwardToPhone())
                .saveChanges()
                .refreshPage();
        callForwardingPage
                .verifyAfterSection(user);
        deleteUsersApi(user);
    }

    @Description("Verify if user can configure \"If busy\" section in call forwarding")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "callForwardingPage"})
    public void configureIfBusyCallForwardingTest(){
        User user = new User();
        userList.add(user);

        createUsersApi(user);
        login(user.getLoginEmail(),user.getLoginPassword())
            .goToMenuTab(CALL_FORWARDING);
        callForwardingPage
                .selectNumber(user.getPhoneNumber())
                .activateIfBusy()
                .selectForwardTo("Phone")
                .enterPhone(user.getForwardToPhone())
                .saveChanges()
                .refreshPage();
        callForwardingPage
                .verifyIfBusySection(user);
        deleteUsersApi(user);
    }

    @Description("Verify if user can configure \"If end device unavailable (not registered)\" section in call forwarding")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "callForwardingPage"})
    public void configureIfEndDeviceUnavailableTest(){
        User user = new User();
        userList.add(user);

        createUsersApi(user);
        login(user.getLoginEmail(),user.getLoginPassword())
                .goToMenuTab(CALL_FORWARDING);
        callForwardingPage
                .selectNumber(user.getPhoneNumber())
                .activateEndDevice()
                .selectForwardTo("Phone")
                .enterPhone(user.getForwardToPhone())
                .saveChanges()
                .refreshPage();
        callForwardingPage
                .verifyEndDevSection(user);
        deleteUsersApi(user);
    }

    @Description("Verify if user can configure \"Calls with suppressed numbers\" section in call forwarding")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "callForwardingPage"})
    public void configureCallsWithSuppressedNumbersTest(){
        User user = new User();
        userList.add(user);

        createUsersApi(user);
        login(user.getLoginEmail(),user.getLoginPassword())
                .goToMenuTab(CALL_FORWARDING);
        callForwardingPage
                .selectNumber(user.getPhoneNumber())
                .activateManualStatus()
                .enterAbsent(user.getManualStatusSubj())
                .selectForwardTo("Phone")
                .enterPhone(user.getForwardToPhone())
                .enterManualStatusFromDate(user.getManualStatusDateFrom())
                .enterManualStatusToDate(user.getManualStatusDateTo())
                .saveChanges()
                .refreshPage();
        callForwardingPage
                .verifyManualStatus(user);
        deleteUsersApi(user);
    }

    @Description("Verify if \"My numbers\" dropdown contains only user related number")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "callForwardingPage"})
    public void myNumbersDropdownContainsOnlyUserRelatedNumberTest(){
        User user = new User();
        userList.add(user);

        createUsersApi(user);
        login(user.getLoginEmail(),user.getLoginPassword())
                .goToMenuTab(CALL_FORWARDING);
        validateDropDownItems(callForwardingPage.getDropdownMyNumbers());
        deleteUsersApi(user);
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        userCleanUp(userList);
    }
}
