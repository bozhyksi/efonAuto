package tests.endDevicesPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.basePage.BasePage;
import tests.endDevicesPageTests.endDevicesTestData.EndDevicesTestData;
import tests.userPageTests.userPageTestData.EndDevice;

import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.END_DEVICES;

@Listeners(CustomListeners.class)

public class EndDevicesPageTests extends BaseTestMethods {

    @Description("Check if system shows additional info tooltip for autoprovisioned end devices.")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "endDevicesPageTests"})
    public void CheckIfSystemShowsAdditionalInfoTooltipForAutoProvisionedEndDevices(){
        step("Prepare test data");
        EndDevicesTestData.AutoProvisionedInfo autoProvisionedInfo = new EndDevicesTestData.AutoProvisionedInfo();

        step("Log in the system and goto End-Devices tab");
        login();
        basePage.goToMenuTab(END_DEVICES);

        step("Open auto-provisioned info tool tip and verify shown data");
        endDevicesPage
                .openAutoProvisionedInfoToolTip()
                .verifyAutoProvisioningToolTipInfo(autoProvisionedInfo);
    }

    @Description("Check if vpbx admin can edit end-device")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "endDevicesPageTests"})
    public void CheckIfVpbxAdminCanEditEndDevice(){
        step("Prepare test data");
        EndDevice endDevice = new EndDevice();

        login();

        basePage
                .goToMenuTab(END_DEVICES);

        endDevicesPage
                .configureEndDevice(endDevice.getRandomEndDeviceForEdit())
                .setName(endDevice.getEndDevName())
                .setUserId(endDevice.getEndDevUserId())
                .setPassword(endDevice.getEndDevPass())
                .selectProxyServer(endDevice.getEndDevProxy())
                .selectCodec(endDevice.getEndDevCodec())
                .selectLanguage(endDevice.getEndDevPhoneLanguage())
                .setDisplayName(endDevice.getEndDevDisplayName())
                .selectOutgoingNumber(endDevice.getEndDevOutgoingNumber())
                //.setLocation(endDevice.getEndDevLocation())
                .setSuppressed(endDevice.getEndDevSuppressed())
                .saveChanges()
                .refreshPage();

        endDevicesPage
                .configureEndDevice(endDevice.getEndDevName())
                .verifyEndDeviceConfiguration(endDevice);
    }
}
