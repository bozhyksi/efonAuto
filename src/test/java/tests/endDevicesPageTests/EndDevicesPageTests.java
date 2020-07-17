package tests.endDevicesPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.endDevicesPageTests.endDevicesTestData.EndDevicesTestData;
import tests.userPageTests.userPageTestData.EndDevice;

import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.END_DEVICES;

@Listeners(CustomListeners.class)

public class EndDevicesPageTests extends BaseTestMethods {

    @Description("Check if system shows additional info tooltip for autoprovisioned end devices.")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "endDevicesPageTests"})
    public void additionalInfoTooltipTest(){
        EndDevicesTestData.AutoProvisionedInfo autoProvisionedInfo = new EndDevicesTestData.AutoProvisionedInfo();

        login()
                .goToMenuTab(END_DEVICES);
        endDevicesPage
                .openAutoProvisionedInfoToolTip()
                .verifyAutoProvisioningToolTipInfo(autoProvisionedInfo);
    }

    @Description("Check if vpbx admin can edit end-device")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "endDevicesPageTests"})
    public void editEndDeviceTest(){
        EndDevice endDevice = new EndDevice();

        login()
                .goToMenuTab(END_DEVICES);
        endDevicesPage
                .clickEditEndDevice(endDevice.getRandomEndDeviceForEdit())
                .setName(endDevice.getEndDevName())
                .setUserId(endDevice.getEndDevUserId())
                .setPassword(endDevice.getEndDevPass())
                .selectProxyServer(endDevice.getEndDevProxy())
                .selectCodec(endDevice.getEndDevCodec())
                .selectLanguage(endDevice.getEndDevPhoneLanguage())
                .setDisplayName(endDevice.getEndDevDisplayName())
                .selectOutgoingNumber(endDevice.getEndDevOutgoingNumber())
                .setLocation(endDevice.getEndDevLocation())
                .setSuppressed(endDevice.getEndDevSuppressed())
                .saveChanges()
                .refreshPage();
        endDevicesPage
                .clickEditEndDevice(endDevice.getEndDevName())
                .verifyEndDeviceConfiguration(endDevice);
    }
}
