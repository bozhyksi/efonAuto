package testsLowLevelUser.endDevicesUserPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.userPageTests.userPageTestData.EndDevice;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static api.baseApiMethods.NumbersApi.getCustomerNumbersApi;
import static api.baseApiMethods.UserApi.createUsersApi;
import static api.baseApiMethods.UserApi.deleteUsersApi;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.value;
import static flow.PublicEnums.OutgoingNumberType.INTERNAL;
import static flow.PublicEnums.OutgoingNumberType.INTERNATIONAL;
import static pages.basePage.BasePage.MenuTabsBasePage.END_DEVICES;
import static testsLowLevelUser.testData.AutotestUserData.autotestUserEndDevname;

@Listeners(CustomListeners.class)

public class EndDevicesUserPageTests extends BaseTestMethods {
    ArrayList<User> userList = new ArrayList<>();

    //EPRO-1103
    @Description("Check if low-level user can edit his own End Devices")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "endDevicesUserPageTests"},enabled = false)
    public void editOwnEndDevicesTest(){
        EndDevice endDevice = new EndDevice();

        loginAsLowLevelUser()
                .goToMenuTab(END_DEVICES);
        endDevicesPage
                .clickEditEndDevice(autotestUserEndDevname)
                .selectLanguage(endDevice.getEndDevPhoneLanguage())
                .selectOutgoingNumber(endDevice.getEndDevOutgoingNumber())
                .setSuppressed(endDevice.getEndDevSuppressed())
                .setLocation(endDevice.getEndDevLocation())
                .saveChanges()
                .clickEditEndDevice(autotestUserEndDevname)
                .verifyEndDeviceConfiguration(endDevice);
    }

    @Description("Check if all customer numbers are available as End Device outgoing number")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "endDevicesUserPageTests"})
    public void verifyOutgoingNumbersListTest(){
        ArrayList<String> customerNumbersList = getCustomerNumbersApi();
        ArrayList<String> outgoingNumbersList;

        loginAsLowLevelUser()
                .goToMenuTab(END_DEVICES);
        outgoingNumbersList = endDevicesPage
                .clickEditEndDevice(autotestUserEndDevname)
                .getOutgoingDropdownItems();
        endDevicesPage
                .verifyOutgoingNumbersList(customerNumbersList,outgoingNumbersList);
    }

    @Description("Check if user can change INTERNAL outgoing number and set new Location zip code")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "endDevicesUserPageTests"})
    public void changeLocationZipCodeForInternalOutgoingNumbersTest(){
        User user = new User(new EndDevice(INTERNAL));
        userList.add(user);

        createUsersApi(user);
        login(user.getLoginEmail(),user.getLoginPassword())
                .goToMenuTab(END_DEVICES);
        endDevicesPage
                .clickEditEndDevice(user.getEndDevices())
                    .selectOutgoingNumber(user.getEndDeviceData().getEndDevOutgoingNumber())
                    .verifyLocationFieldEmpty()
                    .setLocation(user.getEndDeviceData().changeLocation())
                    .saveChanges()
                .clickEditEndDevice(user.getEndDevices())
                    .verifyLocationFieldValue(user.getEndDeviceData().getEndDevLocation());
        deleteUsersApi(user);
    }

    @Description("Check if user can change INTERNATIONAL outgoing number and set new Location zip code")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "endDevicesUserPageTests"})
    public void changeLocationZipCodeForInternationalOutgoingNumbersTest(){
        User user = new User(new EndDevice(INTERNATIONAL));
        userList.add(user);

        createUsersApi(user);
        login(user.getLoginEmail(),user.getLoginPassword())
                .goToMenuTab(END_DEVICES);
        endDevicesPage
                .clickEditEndDevice(user.getEndDevices())
                .selectOutgoingNumber(user.getEndDeviceData().getEndDevOutgoingNumber())
                .selectCountryCode(user.getEndDeviceData().getCountryCode())
                .saveChanges()
                .clickEditEndDevice(user.getEndDevices())
                .verifyCountryCode(user.getEndDeviceData().getCountryCode())
                .getInputLocation().shouldBe(disabled);
        deleteUsersApi(user);
    }

    @AfterClass(alwaysRun = true)
    private void сleanUp(){
        userCleanUp(userList);
    }
}
