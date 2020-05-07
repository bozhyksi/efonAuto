package tests.provisioningPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.provisioningPageTests.provisioningTestData.ProvisioningTestData;

import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.PROVISIONING;
import static pages.basePage.BasePage.MenuTabsBasePage.PROVISIONING_MANAGER;

@Listeners(CustomListeners.class)

public class ProvisioningPageTests extends BaseTestMethods {

    @Description("Check if user can configure and ACTIVATE Provisioning Manager")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "provisioningPageTests"})
    public void CheckIfUserCanConfigureAndActivateProvisioningManager(){

        step("Prepare test data");
        ProvisioningTestData.ProvisioningManagerData provisioningManagerData = new ProvisioningTestData.ProvisioningManagerData();

        step("LogIn the system and navigate to Provisioning manager page");
        login();
        basePage.goToMenuTab(PROVISIONING).goToMenuTab(PROVISIONING_MANAGER);

        step("Configure and activate Provisioning manager");
        provisioningManagerPage
                .activateProvisioningManager(provisioningManagerData)
                .verifyProvisioningManagerConfiguration(provisioningManagerData)
                .deactivateProvisioningManager();

        step("Deactivate manager and clear test data");

    }

    @Description("Check if user can RE-ENABLE Provisioning Manager")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void CheckIfUserCanReEnableProvisioningManager(){
        step("Prepare test data");
        ProvisioningTestData.ProvisioningManagerData provisioningManagerData = new ProvisioningTestData.ProvisioningManagerData();
        ProvisioningTestData.ProvisioningManagerData provisioningManagerData2 = new ProvisioningTestData.ProvisioningManagerData();

        step("LogIn the system and navigate to Provisioning manager page");
        login();
        basePage.goToMenuTab(PROVISIONING).goToMenuTab(PROVISIONING_MANAGER);

        step("Configure and activate Provisioning manager");
        provisioningManagerPage
                .activateProvisioningManager(provisioningManagerData)
                .verifyProvisioningManagerConfiguration(provisioningManagerData);

        step("Re-Enable Provisioning manager");
        provisioningManagerPage
                .reEnableProvisioningManager(provisioningManagerData2)
                .verifyProvisioningManagerConfiguration(provisioningManagerData2)
                .deactivateProvisioningManager();

        step("Deactivate manager and clear test data");

    }

    @Description("Check if user can DEACTIVATE Provisioning Manager")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void CheckIfUserCanDeactivateProvisioningManager(){

        step("Prepare test data");
        ProvisioningTestData.ProvisioningManagerData provisioningManagerData = new ProvisioningTestData.ProvisioningManagerData();

        step("LogIn the system and navigate to Provisioning manager page");
        login();
        basePage.goToMenuTab(PROVISIONING).goToMenuTab(PROVISIONING_MANAGER);

        step("Configure and activate Provisioning manager");
        provisioningManagerPage
                .activateProvisioningManager(provisioningManagerData)
                .verifyProvisioningManagerConfiguration(provisioningManagerData);

        step("Deactivate Provisioning manager");
        provisioningManagerPage.deactivateProvisioningManager();

        step("Re-enable and DEACTIVATE Provisioning manager");
        provisioningManagerPage
                .activateProvisioningManager(provisioningManagerData)
                .reEnableProvisioningManager(provisioningManagerData)
                .verifyProvisioningManagerConfiguration(provisioningManagerData)
                .reEnableAndDeactivateProvisioningManager(provisioningManagerData);
    }

}
