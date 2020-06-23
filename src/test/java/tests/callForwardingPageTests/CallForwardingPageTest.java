package tests.callForwardingPageTests;

import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.basePage.BasePage;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static api.baseApiMethods.UserApi.createUserApi;
import static api.baseApiMethods.UserApi.deleteUserApi;
import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.CALL_FORWARDING;

@Listeners(CustomListeners.class)

public class CallForwardingPageTest extends BaseTestMethods {
    ArrayList<User> usersList = new ArrayList<>();

    @Description("Verify if user can configure After section in call forwarding")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "callForwardingPage"})
    public void configureAfterSectionTest(){
        String delay = getRandomNumber(2);
        String forwardToPhone = getRandomPhone();
        User user = new User();
        usersList.add(user);

        createUserApi(user.getJson());
        login()
                .goToMenuTab(CALL_FORWARDING);
        callForwardingPage
                .selectNumber(user.getPhoneNumber())
                .activateAfter()
                .enterDelay(delay)
                .selectForwardTo("Phone")
                .enterPhone(forwardToPhone)
                .saveChanges()
                .refreshPage();
        callForwardingPage
                .verifySavedData(user,delay,forwardToPhone);
        deleteUserApi(user.getId());
    }

    @Description("Verify if user can configure \"If busy\" section in call forwarding")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "callForwardingPage"})
    public void configureIfBusySectionTest(){
        String forwardToPhone = getRandomPhone();
        User user = new User();
        usersList.add(user);

        createUserApi(user.getJson());
        login()
                .goToMenuTab(CALL_FORWARDING);
        callForwardingPage
                .selectNumber(user.getPhoneNumber())
                .activateIfBusy()
                .selectForwardTo("Phone")
                .enterPhone(forwardToPhone)
                .saveChanges()
                .refreshPage();
        callForwardingPage
                .verifySavedData(user,forwardToPhone);
        deleteUserApi(user.getId());
    }

    @Description("Verify if user can configure \"If end device unavailable (not registered)\" section in call forwarding")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "callForwardingPage"})
    public void configureEndDeviceSectionTest(){
        String forwardToPhone = getRandomPhone();
        User user = new User();
        usersList.add(user);

        createUserApi(user.getJson());
        login()
                .goToMenuTab(CALL_FORWARDING);
        callForwardingPage
                .selectNumber(user.getPhoneNumber())
                .activateEndDevice()
                .selectForwardTo("Phone")
                .enterPhone(forwardToPhone)
                .saveChanges()
                .refreshPage();
        callForwardingPage
                .verifySavedData(user,forwardToPhone);
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
