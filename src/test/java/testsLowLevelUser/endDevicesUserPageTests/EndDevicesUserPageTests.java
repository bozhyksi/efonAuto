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
import static api.baseApiMethods.UserApi.*;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.value;
import static flow.PublicEnums.OutgoingNumberType.INTERNAL;
import static flow.PublicEnums.OutgoingNumberType.INTERNATIONAL;
import static pages.basePage.BasePage.MenuTabsBasePage.END_DEVICES;
import static testsLowLevelUser.testData.AutotestUserData.autotestUserEndDevname;

@Listeners(CustomListeners.class)

public class EndDevicesUserPageTests extends BaseTestMethods {
    ArrayList<User> userList = new ArrayList<>();

    @Description("Check if low-level user can edit his own End Devices")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "endDevicesUserPageTests"})
    public void editOwnEndDevicesTest(){
        User user = new User(new EndDevice(INTERNAL));
        userList.add(user);

        createUsersApi(user);
        login(user.getLoginEmail(),user.getLoginPassword())
                .goToMenuTab(END_DEVICES);
        endDevicesPage
                .clickEditEndDevice()
                .selectLanguage(user.getEndDeviceData().getEndDevPhoneLanguage())
                .selectOutgoingNumber(user.getEndDeviceData().getEndDevOutgoingNumber())
                .setSuppressed(user.getEndDeviceData().getEndDevSuppressed())
                .setLocation(user.getEndDeviceData().getEndDevLocation())
                .saveChanges()
                .clickEditEndDevice()
                .getInputLocation().shouldHave(value(user.getEndDeviceData().getEndDevLocation()));
        deleteUsersApi(user);
    }

    @Description("Check if all customer numbers are available as End Device outgoing number")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "endDevicesUserPageTests"})
    public void verifyOutgoingNumbersListTest(){
        User user = new User();
        userList.add(user);
        ArrayList<String> customerNumbersList = getCustomerNumbersApi();
        ArrayList<String> outgoingNumbersList;

        createUsersApi(user);
        login(user.getLoginEmail(),user.getLoginPassword())
                .goToMenuTab(END_DEVICES);
        outgoingNumbersList = endDevicesPage
                .clickEditEndDevice()
                .getOutgoingDropdownItems();
        endDevicesPage
                .verifyOutgoingNumbersList(customerNumbersList,outgoingNumbersList);
        deleteUsersApi(user);
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
