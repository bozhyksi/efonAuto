package testsLowLevelUser.callForwardingUserPageTests;

import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.basePage.BasePage;
import tests.userPageTests.userPageTestData.User;

import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.CALL_FORWARDING;

@Listeners(CustomListeners.class)

public class CallForwardingUserPageTests extends BaseTestMethods {

    @Description("Verify if user can configure After section in call forwarding")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "callForwardingUserPageTests"})
    public void VerifyIfUserCanConfigureAfterSectionInCallForwarding(){
        step("Prepare test data");
        String delay = getRandomNumber(2);
        String forwardToPhone = getRandomPhone();

        step("Log in the system");
        loginAsLowLevelUser();

        step("Goto forwarding tab");
        basePageLowLevelUser.goToMenuTab(CALL_FORWARDING);

        step("Configure After section");
        callForwardingUserPage.configureAfterSection(delay, forwardToPhone);

        step("Clear test data");
        lowLevelUserCallForwardingCleanUp();
    }

    @Description("Verify if user can configure \"If busy\" section in call forwarding")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "callForwardingPage"})
    public void VerifyIfUserCanConfigureIfBusySectionInCallForwarding(){
        step("Prepare test data");
        String forwardToPhone = getRandomPhone();

        step("Log in the system");
        loginAsLowLevelUser();

        step("Goto forwarding tab");
        basePageLowLevelUser.goToMenuTab(CALL_FORWARDING);

        step("Confidure if Busy");
        callForwardingUserPage.configureIfBusySection(forwardToPhone);

        step("Clear test data");
        lowLevelUserCallForwardingCleanUp();
    }

    @Description("Verify if user can configure \"If end device unavailable (not registered)\" section in call forwarding")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "callForwardingPage"})
    public void VerifyIfUserCanConfigureIfEndDeviceUnavailableSectionInCallForwarding(){
        step("Prepare test data");
        String delay = getRandomNumber(2);
        String forwardToPhone = getRandomPhone();

        step("Log in the system");
        loginAsLowLevelUser();

        step("Goto forwarding tab");
        basePageLowLevelUser.goToMenuTab(CALL_FORWARDING);

        step("Configure end device section");
        callForwardingUserPage.configureIfEndDeviceUnavailableSection(forwardToPhone);

        step("Clear test data");
        lowLevelUserCallForwardingCleanUp();
    }

    @Description("Verify if user can configure \"Calls with suppressed numbers\" section in call forwarding")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "callForwardingPage"})
    public void VerifyIfUserCanConfigureCallsWithSuppressedNumbersSectionInCallForwarding(){
        step("Prepare test data");
        String delay = getRandomNumber(2);
        String forwardToPhone = getRandomPhone();

        step("Log in the system");
        loginAsLowLevelUser();

        step("Goto forwarding tab");
        basePageLowLevelUser.goToMenuTab(CALL_FORWARDING);

        step("Configure Suppressed section");
        callForwardingUserPage.configureSuppressedNumbersSection(forwardToPhone);

        step("Clear test data");
        lowLevelUserCallForwardingCleanUp();
    }

    @Description("Verify if \"My numbers\" dropdown contains only user related number")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "callForwardingPage"})
    public void VerifyIfMyNumbersDropdownContainsOnlyUserRelatedNumber(){

        loginAsLowLevelUser();
        basePageLowLevelUser
                .goToMenuTab(CALL_FORWARDING);
        callForwardingUserPage
                .verifyMyNumbersDropDownItems();

    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        startBrowser();
        loginAsLowLevelUser();
        lowLevelUserCallForwardingCleanUp();
        closeBrowser();
    }
}
