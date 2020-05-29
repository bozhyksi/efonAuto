package tests.provisioningPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.provisioningPageTests.provisioningTestData.PhoneModelTestData;
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
                .gotoProvisioningConfigurationTab()
                .uploadCustomConfigTemplate(configTemplate.getFilePath())
                .saveChanges()
                .clickEditButton(user.getFirstName())
                .gotoProvisioningConfigurationTab()
                .getButtonDownloadConfigtemplate().should(exist);
        refreshPage();
        deleteUser(user);
    }

    @Description("Check if vpbx admin can download Configuration template  --  cant resolve This file can harm...")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"}, enabled = false)
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
                .gotoProvisioningConfigurationTab()
                .uploadCustomConfigTemplate(configTemplate.getFilePath())
                .saveChanges()
                .clickEditButton(user.getFirstName())
                .gotoProvisioningConfigurationTab()
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
                .gotoProvisioningConfigurationTab()
                .selectFirmware()
                .saveChanges();
        refreshPage();
        deleteUser(user);
    }

    @Description("Check if vpbx admin can configure Functions on provisioning settings")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void checkIfVpbxAdminCanConfigureFunctions(){
        User user = new User(getAutoProvisionedEndDeviceFromDB());
        ProvisioningTestData.ProvisioningSettings provisioningSettings = new ProvisioningTestData.ProvisioningSettings();
        userArrayList.add(user);

        login();
        userPage
                .createUser(user);
        basePage
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_END_DEVICES);
        provisioningEndDevicesPage
                .clickEditButton(user.getFirstName())
                .gotoProvisioningSettingsTab()
                .selectLanguage(provisioningSettings.getLanguageValue())
                .selectWebLanguage(provisioningSettings.getWebLanguageVal())
                .configureWebAuthentication(provisioningSettings.getWebUser(),provisioningSettings.getWebPassword())
                .configureFunction(provisioningSettings.getDestNum(), provisioningSettings.getDispName())
                .saveChanges();
        provisioningEndDevicesPage
                .clickEditButton(user.getFirstName())
                .gotoProvisioningSettingsTab()
                .verifyConfiguredFunctions(provisioningSettings.getDestNum(),provisioningSettings.getDispName());

        refreshPage();
        deleteUser(user);

    }

    @Description("Check if provisioning settings popup shows end device and user name")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void checkIfProvisioningSettingsPopupShownEndDeviceAndUserName(){

        User user = new User(getAutoProvisionedEndDeviceFromDB());
        userArrayList.add(user);

        login();
        userPage
                .createUser(user)
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_END_DEVICES);
        provisioningEndDevicesPage
                .clickEditButton(user.getFirstName())
                .checkEndDeviceAndUserName(user.getEndDevices(),user.getFirstName());

        refreshPage();
        deleteUser(user);

    }

    @Description("Check if user can Select/Deselect for provisioning end device")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void selectForProvisioningTest(){

        User user = new User();
        userArrayList.add(user);

        login();
        userPage
                .createUser(user)
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_END_DEVICES);
        provisioningEndDevicesPage
                .selectForProvisioning(user.getFirstName())
                .deSelectForProvisioning(user.getFirstName());
        userPage
                .deleteUser(user);
    }

    @Description("Check if user can Set/ReSet as manually provisioned end device")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void setAsManuallyProvisionedTest(){

        User user = new User();
        userArrayList.add(user);

        login();
        userPage
                .createUser(user)
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_END_DEVICES);
        provisioningEndDevicesPage
                .setManuallyProvisioned(user.getFirstName())
                .resetManuallyProvisioned(user.getFirstName());
        userPage
                .deleteUser(user);
    }

    @Description("Check if user can Select/Deselect for MAC end device")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void selectForMacTest(){
        String macAddress = getRandomMAC();
        User user = new User();
        userArrayList.add(user);

        login();
        userPage
                .createUser(user)
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_END_DEVICES);
        provisioningEndDevicesPage
                .selectForMacProvisioning(user.getFirstName(),macAddress)
                .deSelectForProvisioning(user.getFirstName());
        userPage
                .deleteUser(user);
    }

    @Description("Check if user can change IP address of end device")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void changeIpAddressTest(){
        String ipAddress = getRandomIpAddress();
        User user = new User(getAutoProvisionedEndDeviceFromDB());
        userArrayList.add(user);

        login();
        userPage
                .createUser(user)
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_END_DEVICES);
        provisioningEndDevicesPage
                .changeIpAddress(user.getFirstName(),ipAddress);
        userPage
                .deleteUser(user);
    }

    @Description("Check if user can open Phone Model provisioning settings popup")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void openPhoneModelSettingsPopupTest(){

        PhoneModelTestData phoneModel = new PhoneModelTestData();

        login();
        basePage
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_PHONE_MODELS);
        provisioningPhoneModelsPage
                .clickEditPhoneModel(phoneModel.getPhoneModel())
                .gotoProvisioningSettingsTab()
                .verifyPhoneModelName(phoneModel.getPhoneModel());

    }

    @Description("Check if user can configure fixed function")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void configureFixedFunctionTest(){

        PhoneModelTestData phoneModel = new PhoneModelTestData("Alcatel Temporis IP301G");

        login();
        basePage
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_PHONE_MODELS);
        provisioningPhoneModelsPage
                .clickEditPhoneModel(phoneModel)
                .gotoProvisioningSettingsTab()
                .configureFixedFunctions(phoneModel)
                .savePhoneModelChanges()
                .clickEditPhoneModel(phoneModel)
                .gotoProvisioningSettingsTab()
                .verifyFixedFunctions(phoneModel)
                .unConfigureFixedFunctions()
                .saveChanges();

    }



    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        startBrowser();
        login();
        userCleanUp(userArrayList);
        closeBrowser();
    }
}
