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

import static api.baseApiMethods.ProvisioningApi.deactivatePhoneModelFunctionsApi;
import static api.baseApiMethods.UserApi.*;
import static com.codeborne.selenide.Condition.exist;
import static flow.PublicEnums.DragDropSection.SECTION_NOT_SELECTED;
import static flow.PublicEnums.DragDropSection.SECTION_SELECTED;
import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.*;

@Listeners(CustomListeners.class)

public class ProvisioningPageTests extends BaseTestMethods {
    ArrayList<User> userArrayList = new ArrayList<>();

    @Description("Check if user can configure and ACTIVATE Provisioning Manager")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "provisioningPageTests"})
    public void activateProvisioningManagerTest(){

        ProvisioningTestData.ProvisioningManagerData provisioningManagerData = new ProvisioningTestData.ProvisioningManagerData();

        login()
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_MANAGER);
        provisioningManagerPage
                .activateProvisioningManager(provisioningManagerData)
                .verifyProvisioningManagerConfiguration(provisioningManagerData)
                .deactivateProvisioningManager();
    }

    @Description("Check if user can RE-ENABLE Provisioning Manager")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void reEnableProvisioningManagerTest(){

        ProvisioningTestData.ProvisioningManagerData provisioningManagerData = new ProvisioningTestData.ProvisioningManagerData();
        ProvisioningTestData.ProvisioningManagerData provisioningManagerData2 = new ProvisioningTestData.ProvisioningManagerData();

        login()
                .goToMenuTab(PROVISIONING).goToMenuTab(PROVISIONING_MANAGER);
        provisioningManagerPage
                .activateProvisioningManager(provisioningManagerData)
                .verifyProvisioningManagerConfiguration(provisioningManagerData)
                .reEnableProvisioningManager(provisioningManagerData2)
                .verifyProvisioningManagerConfiguration(provisioningManagerData2)
                .deactivateProvisioningManager();
    }

    @Description("Check if user can DEACTIVATE Provisioning Manager")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void deactivateProvisioningManagerTest(){

        ProvisioningTestData.ProvisioningManagerData provisioningManagerData = new ProvisioningTestData.ProvisioningManagerData();

        login()
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_MANAGER);
        provisioningManagerPage
                .activateProvisioningManager(provisioningManagerData)
                .verifyProvisioningManagerConfiguration(provisioningManagerData)
                .deactivateProvisioningManager()
                .activateProvisioningManager(provisioningManagerData)
                .reEnableProvisioningManager(provisioningManagerData)
                .verifyProvisioningManagerConfiguration(provisioningManagerData)
                .reEnableAndDeactivateProvisioningManager(provisioningManagerData);
    }

    @Description("Check if vpbx admin can upload Configuration template")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void uploadConfigurationTemplateTest(){
        User user = new User(getAutoProvisionedEndDeviceFromDB());
        ProvisioningTestData.ConfigurationTemplate configTemplate = new ProvisioningTestData.ConfigurationTemplate();
        userArrayList.add(user);

        createUsersApi(user);

        login()
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

        deleteUsersApi(user);
    }

    @Description("Check if vpbx admin can download Configuration template  --  cant resolve This file can harm...")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"}, enabled = false)
    public void checkIfAdminCanDownloadConfigurationTemplate(){
        User user = new User(getAutoProvisionedEndDeviceFromDB());
        ProvisioningTestData.ConfigurationTemplate configTemplate = new ProvisioningTestData.ConfigurationTemplate();
        userArrayList.add(user);

        createUsersApi(user);

        login()
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

        deleteUsersApi(user);
    }

    @Description("Check if vpbx admin can select firmware template from drop-down")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void selectFirmwareTest(){
        User user = new User(getAutoProvisionedEndDeviceFromDB());
        userArrayList.add(user);

        createUsersApi(user);

        login()
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_END_DEVICES);
        provisioningEndDevicesPage
                .clickEditButton(user.getFirstName())
                .gotoProvisioningConfigurationTab()
                .selectFirmware()
                .saveChanges();
        refreshPage();

        deleteUsersApi(user);
    }

    @Description("Check if provisioning settings popup shows end device and user name")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void checkIfProvisioningSettingsPopupShownEndDeviceAndUserName(){

        User user = new User(getAutoProvisionedEndDeviceFromDB());
        userArrayList.add(user);

        createUsersApi(user);

        login()
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_END_DEVICES);
        provisioningEndDevicesPage
                .clickEditButton(user.getFirstName())
                .checkEndDeviceAndUserName(user.getEndDevices(),user.getFirstName());

        deleteUsersApi(user);
    }

    @Description("Check if user can Select/Deselect for provisioning end device")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void selectForProvisioningTest(){

        User user = new User();
        userArrayList.add(user);

        createUsersApi(user);

        login()
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_END_DEVICES);
        provisioningEndDevicesPage
                .selectForProvisioning(user.getFirstName())
                .deSelectForProvisioning(user.getFirstName());

        deleteUsersApi(user);
    }

    @Description("Check if user can Set/ReSet as manually provisioned end device")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void setAsManuallyProvisionedTest(){

        User user = new User();
        userArrayList.add(user);

        createUsersApi(user);

        login()
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_END_DEVICES);
        provisioningEndDevicesPage
                .setManuallyProvisioned(user.getFirstName())
                .resetManuallyProvisioned(user.getFirstName());

        deleteUsersApi(user);
    }

    @Description("Check if user can Select/Deselect for MAC end device")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void selectForMacTest(){
        String macAddress = getRandomMAC();
        User user = new User();
        userArrayList.add(user);

        createUsersApi(user);

        login()
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_END_DEVICES);
        provisioningEndDevicesPage
                .selectForMacProvisioning(user.getFirstName(),macAddress)
                .deSelectForProvisioning(user.getFirstName());

        deleteUsersApi(user);
    }

    @Description("Check if user can change IP address of end device")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void changeIpAddressTest(){
        String ipAddress = getRandomIpAddress();
        User user = new User(getAutoProvisionedEndDeviceFromDB());
        userArrayList.add(user);

        createUsersApi(user);

        login()
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_END_DEVICES);
        provisioningEndDevicesPage
                .changeIpAddress(user.getFirstName(),ipAddress);

        deleteUsersApi(user);
    }

    @Description("Check if user can open Phone Model provisioning settings popup")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void openPhoneModelSettingsPopupTest(){

        PhoneModelTestData phoneModel = new PhoneModelTestData();

        login()
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_PHONE_MODELS);
        provisioningPhoneModelsPage
                .clickEditPhoneModel(phoneModel.getPhoneModel())
                .gotoProvisioningSettingsTab()
                .verifyPhoneModelName(phoneModel.getPhoneModel());
    }

    @Description("Check if user can configure PANASONIC KX-UT670NE functions")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void configFunctionsForPanasonicTest(){
        PhoneModelTestData phoneModel = new PhoneModelTestData("panasonic KX-UT670NE");

        login();
        basePage
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_PHONE_MODELS);
        provisioningPhoneModelsPage
                .clickEditPhoneModel(phoneModel.getPhoneModel())
                .gotoProvisioningSettingsTab()
                .configAllFunctions(phoneModel)
                .savePhoneModelChanges()
                .clickEditPhoneModel(phoneModel.getPhoneModel())
                .gotoProvisioningSettingsTab()
                .validateFunctions(phoneModel);

        deactivatePhoneModelFunctionsApi(phoneModel);
    }

    @Description("Check if user can configure SNOM D765 functions")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void configFunctionsForSnomTest(){
        PhoneModelTestData phoneModel = new PhoneModelTestData("snom D765");

        login()
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_PHONE_MODELS);
        provisioningPhoneModelsPage
                .clickEditPhoneModel(phoneModel.getPhoneModel())
                .gotoProvisioningSettingsTab()
                .configAllFunctions(phoneModel)
                .savePhoneModelChanges()
                .clickEditPhoneModel(phoneModel.getPhoneModel())
                .gotoProvisioningSettingsTab()
                .validateFunctions(phoneModel);

        deactivatePhoneModelFunctionsApi(phoneModel);
    }

    @Description("Check if user can configure Gigaset D765 functions")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void configFunctionsForGigasetTest(){
        PhoneModelTestData phoneModel = new PhoneModelTestData("Gigaset DE900 IP PRO");

        login()
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_PHONE_MODELS);
        provisioningPhoneModelsPage
                .clickEditPhoneModel(phoneModel.getPhoneModel())
                .gotoProvisioningSettingsTab()
                .configAllFunctions(phoneModel)
                .savePhoneModelChanges()
                .clickEditPhoneModel(phoneModel.getPhoneModel())
                .gotoProvisioningSettingsTab()
                .validateFunctions(phoneModel);

        deactivatePhoneModelFunctionsApi(phoneModel);
    }

    @Description("Check if user can configure Alcatel Temporis IP301G functions")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void configFunctionsForAlcatelTest(){
        PhoneModelTestData phoneModel = new PhoneModelTestData("Alcatel Temporis IP301G");

        login()
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_PHONE_MODELS);
        provisioningPhoneModelsPage
                .clickEditPhoneModel(phoneModel.getPhoneModel())
                .gotoProvisioningSettingsTab()
                .configAllFunctions(phoneModel)
                .savePhoneModelChanges()
                .clickEditPhoneModel(phoneModel.getPhoneModel())
                .gotoProvisioningSettingsTab()
                .validateFunctions(phoneModel);

        deactivatePhoneModelFunctionsApi(phoneModel);
    }

    @Description("Check if user can configure AASTRA 6869i functions")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void configFunctionsForAastraTest(){
        PhoneModelTestData phoneModel = new PhoneModelTestData("aastra 6869i");

        login()
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_PHONE_MODELS);
        provisioningPhoneModelsPage
                .clickEditPhoneModel(phoneModel.getPhoneModel())
                .gotoProvisioningSettingsTab()
                .configAllFunctions(phoneModel)
                .savePhoneModelChanges()
                .clickEditPhoneModel(phoneModel.getPhoneModel())
                .gotoProvisioningSettingsTab()
                .validateFunctions(phoneModel);

        deactivatePhoneModelFunctionsApi(phoneModel);
    }

    @Description("Check if user can create Additional end devices")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "provisioningPageTests"})
    public void additionalEndDevicesTest(){
        User user1 = new User(getSelectedForProvEndDeviceFromDB());
        User user2 = new User(getNotProvisionedEndDeviceFromDB());
        userArrayList.add(user2);
        userArrayList.add(user1);

        createUsersApi(user1,user2);

        login()
                .goToMenuTab(PROVISIONING)
                .goToMenuTab(PROVISIONING_END_DEVICES);
        provisioningEndDevicesPage
                .clickPlus(user1.getFirstName())
                .dragDrop(SECTION_SELECTED, user2.getFirstName())
                .saveChanges()
                .clickPlus(user1.getFirstName())
                .dragDrop(SECTION_NOT_SELECTED, user2.getFirstName())
                .saveChanges()
                .refreshPage();

        deleteUsersApi(user1,user2);
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        userCleanUp(userArrayList);
    }
}
