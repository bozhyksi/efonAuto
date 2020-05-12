package tests.endDevicesPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.basePage.BasePage;
import tests.endDevicesPageTests.endDevicesTestData.EndDevicesTestData;

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
}
