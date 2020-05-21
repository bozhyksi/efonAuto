package tests.provisioningPageTests;

import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.provisioningPageTests.provisioningTestData.ProvisioningTestData;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.*;

@Listeners(CustomListeners.class)

public class ProvisioningPageTests extends BaseTestMethods {
    ArrayList<User> userArrayList = new ArrayList<>();

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

    @Description("Check if vpbx admin can upload Configuration template")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void CheckIfVpbxAdminCanUploadConfigurationTemplate(){
        User user = new User(getAutoProvisionedEndDeviceFromDB());
        ProvisioningTestData.ConfigurationTemplate configTemplate = new ProvisioningTestData.ConfigurationTemplate();
        userArrayList.add(user);

        login();
        userPage
                .createUser(user);
        basePage
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_END_DEVICES);
        provisioningEndDevicesPage
                .clickEditButton(user.getFirstName())
                .uploadCustomConfigTemplate(configTemplate.getFilePath())
                .saveChanges()
                .clickEditButton(user.getFirstName())
                .getButtonDownloadConfigtemplate().should(exist);
        refreshPage();
        deleteUser(user);
    }

    @Description("Check if vpbx admin can download Configuration template  --  cant resolve This file can harm...")
    @Test(/*retryAnalyzer = RetryAnalyzer.class, */groups = {"regression", "provisioningPageTests"}, enabled = false)
    public void checkIfAdminCanDownloadConfigurationTemplate(){
        User user = new User(getAutoProvisionedEndDeviceFromDB());
        ProvisioningTestData.ConfigurationTemplate configTemplate = new ProvisioningTestData.ConfigurationTemplate();
        userArrayList.add(user);

        login();
        userPage
                .createUser(user);
        basePage
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_END_DEVICES);
        provisioningEndDevicesPage
                .clickEditButton(user.getFirstName())
                .uploadCustomConfigTemplate(configTemplate.getFilePath())
                .saveChanges()
                .clickEditButton(user.getFirstName())
                .downloadCurrentConfigurationTemplate()
                .verifyIfConfigFileWaDownloaded()
                .deleteDownloadedFile();
        refreshPage();
        deleteUser(user);
    }

    @Description("Check if vpbx admin can select firmware template from drop-down")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void CheckIfVpbxAdminCanSelectFirmware(){
        User user = new User(getAutoProvisionedEndDeviceFromDB());
        userArrayList.add(user);

        login();
        userPage
                .createUser(user);
        basePage
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_END_DEVICES);
        provisioningEndDevicesPage
                .clickEditButton(user.getFirstName())
                .selectFirmware()
                .saveChanges();
        refreshPage();
        deleteUser(user);
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        startBrowser();
        login();
        userCleanUp(userArrayList);
        closeBrowser();
    }
}
