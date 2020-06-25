package testsLowLevelUser.endDevicesUserPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.basePage.BasePage;
import tests.userPageTests.userPageTestData.EndDevice;

import java.util.ArrayList;

import static io.qameta.allure.Allure.step;

import static pages.basePage.BasePage.MenuTabsBasePage.END_DEVICES;
import static testsLowLevelUser.testData.AutotestUserData.autotestUserEndDevname;

@Listeners(CustomListeners.class)

public class EndDevicesUserPageTests extends BaseTestMethods {

    //EPRO-1103
    @Description("Check if low-level user can edit his own End Devices")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "endDevicesUserPageTests"},enabled = false)
    public void editOwnEndDevicesTest(){
        EndDevice endDevice = new EndDevice();

        loginAsLowLevelUser();
        basePageLowLevelUser
                .goToMenuTab(END_DEVICES);
        endDevicesUserPage
                .configureEndDevice(autotestUserEndDevname)
                .selectPhoneLanguage(endDevice.getEndDevPhoneLanguage())
                .selectOutgoingNumber(endDevice.getEndDevOutgoingNumber())
                .selectSuppressedOption(endDevice.getEndDevSuppressed())
                .enterLocation(endDevice.getEndDevLocation())
                .saveChanges()
                .configureEndDevice(autotestUserEndDevname)
                .validateEndDeviceConfiguration(endDevice);
    }

    @Description("Check if all customer numbers are available as End Device outgoing number")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "endDevicesUserPageTests"})
    public void CheckIfAllCustomerNumbersAreAvailableAsOutgoingNumbers(){
        step("Prepare test data");
        ArrayList<String> customerNumbersList;
        ArrayList<String> outgoingNumbersList;

        step("Log in as admin and get customer numbers list");
        customerNumbersList = getListOfAllCustomerNumbers();

        step("Log in as low-level user");
        loginAsLowLevelUser();

        step("Goto end devices and verify if all customer numbers exist in outgoing dropdown");
        basePageLowLevelUser.goToMenuTab(END_DEVICES);
        endDevicesUserPage.getButtonEditEndDeviceByName(autotestUserEndDevname).click();
        waitUntilAlertDisappear();
        outgoingNumbersList = endDevicesUserPage.getOutgoingDropdownItems();
        endDevicesUserPage.verifyIfAllCustomerNumbersAreAvailableAsOutgoing(customerNumbersList,outgoingNumbersList);
    }


}
