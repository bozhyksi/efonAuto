package tests.callForwardingPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static api.baseApiMethods.UserApi.*;
import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.CALL_FORWARDING;

@Listeners(CustomListeners.class)

public class CallForwardingPageTest extends BaseTestMethods {
    ArrayList<User> usersList = new ArrayList<>();

    @Description("Verify if user can configure After section in call forwarding")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "callForwardingPage"})
    public void configureAfterSectionTest(){
        User user = new User();
        usersList.add(user);

        createUserApi(user.getJson());
        login()
                .goToMenuTab(CALL_FORWARDING);
        callForwardingPage
                .selectNumber(user.getPhoneNumber())
                .activateAfter()
                .enterDelay(user.getAfterDelay())
                .selectForwardTo("Phone")
                .enterPhone(user.getForwardToPhone())
                .saveChanges()
                .refreshPage();
        callForwardingPage
                .verifyAfterSection(user);
        deleteUserApi(user.getId());
    }

    @Description("Verify if user can configure \"If busy\" section in call forwarding")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "callForwardingPage"})
    public void configureIfBusySectionTest(){
        User user = new User();
        usersList.add(user);

        createUsersApi(user);
        login()
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
    public void configureEndDeviceSectionTest(){
        User user = new User();
        usersList.add(user);

        createUserApi(user.getJson());
        login()
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
       deleteUserApi(user.getId());
    }

    @Description("Verify if user can configure \"Calls with suppressed numbers\" section in call forwarding")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "callForwardingPage"})
    public void configureCallsWithSuppressedNumbersTest(){
        User user = new User();
        usersList.add(user);

        createUserApi(user.getJson());
        login()
                .goToMenuTab(CALL_FORWARDING);
        callForwardingPage
                .selectNumber(user.getPhoneNumber())
                .activateSuppressedNumbers()
                .selectForwardTo("Voicemail")
                .saveChanges()
                .refreshPage();
        callForwardingPage
                .selectNumber(user.getPhoneNumber())
                .getDropdownDeviceForwardTo().getSelectedText().equals("Voicemail");
        deleteUserApi(user.getId());
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        userCleanUp(usersList);
    }
}
