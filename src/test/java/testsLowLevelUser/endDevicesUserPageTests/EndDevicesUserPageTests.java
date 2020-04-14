package testsLowLevelUser.endDevicesUserPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.basePage.BasePage;
import tests.userPageTests.userPageTestData.EndDevice;

import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.END_DEVICES;

@Listeners(CustomListeners.class)

public class EndDevicesUserPageTests extends BaseTestMethods {

    @Description("Check if low-level user can edit his own End Devices")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "endDevicesUserPageTests"}, invocationCount = 2)
    public void CheckIfLowlevelUserCanEditHisOwnEndDevices(){
        step("Prepare test data");
        EndDevice endDevice = new EndDevice();

        step("Login as low-level user");
        loginAsLowLevelUser();

        step("Goto end devices tab");
        basePageLowLevelUser.goToMenuTab(END_DEVICES);

        step("Edit available EndDevice");
        endDevicesUserPage.openEditEndDevicePopup("Account 906144a10");
        endDevicesUserPage.configureUserEndDevice(endDeviceTabConfigUserPopup, endDevice);

        step("validate saved data");
        endDevicesUserPage.openEditEndDevicePopup("Account 906144a10");
        endDevicesUserPage.validateEndDeviceData(endDeviceTabConfigUserPopup, endDevice);
    }

}
