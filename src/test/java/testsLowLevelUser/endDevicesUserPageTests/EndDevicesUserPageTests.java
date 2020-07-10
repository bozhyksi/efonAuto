package testsLowLevelUser.endDevicesUserPageTests;

import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.userPageTests.userPageTestData.EndDevice;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static api.baseApiMethods.NumbersApi.getCustomerNumbersApi;
import static api.baseApiMethods.UserApi.createUsersApi;
import static api.baseApiMethods.UserApi.deleteUsersApi;
import static com.codeborne.selenide.Condition.value;
import static pages.basePage.BasePage.MenuTabsBasePage.END_DEVICES;
import static testsLowLevelUser.testData.AutotestUserData.autotestUserEndDevname;

@Listeners(CustomListeners.class)

public class EndDevicesUserPageTests extends BaseTestMethods {

    //EPRO-1103
    @Description("Check if low-level user can edit his own End Devices")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "endDevicesUserPageTests"},enabled = false)
    public void editOwnEndDevicesTest(){
        EndDevice endDevice = new EndDevice();

        loginAsLowLevelUser()
                .goToMenuTab(END_DEVICES);
        endDevicesPage
                .configureEndDevice(autotestUserEndDevname)
                .selectLanguage(endDevice.getEndDevPhoneLanguage())
                .selectOutgoingNumber(endDevice.getEndDevOutgoingNumber())
                .setSuppressed(endDevice.getEndDevSuppressed())
                .setLocation(endDevice.getEndDevLocation())
                .saveChanges()
                .configureEndDevice(autotestUserEndDevname)
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
                .configureEndDevice(autotestUserEndDevname)
                .getOutgoingDropdownItems();
        endDevicesPage
                .verifyOutgoingNumbersList(customerNumbersList,outgoingNumbersList);
    }

    @Description("Check if user can change outgoing number and set new Location zip code")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "endDevicesUserPageTests"})
    public void changeLocationZipCodeTest(){
        User user = new User(new EndDevice());

        createUsersApi(user);
        login(user.getLoginEmail(),user.getLoginPassword())
                .goToMenuTab(END_DEVICES);
        endDevicesPage
                .configureEndDevice(user.getEndDevices())
                .selectOutgoingNumber(user.getEndDeviceData().changeOutgoingNumber())
                .verifyLocationFieldEmpty()
                .setLocation(user.getEndDeviceData().changeLocation())
                .saveChanges()
                .configureEndDevice(user.getEndDevices())
                .getInputLocation().shouldHave(value(user.getEndDeviceData().getEndDevLocation()));
        deleteUsersApi(user);
    }


}
