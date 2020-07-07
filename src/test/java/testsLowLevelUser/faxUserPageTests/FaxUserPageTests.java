package testsLowLevelUser.faxUserPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.userPageTests.userPageTestData.User;
import testsLowLevelUser.faxUserPageTests.faxUserPageTestData.Fax2EmailSettingsTestData;
import testsLowLevelUser.faxUserPageTests.faxUserPageTestData.SendFaxTestData;

import java.util.ArrayList;

import static api.baseApiMethods.UserApi.createUsersApi;
import static api.baseApiMethods.UserApi.deleteUsersApi;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.MenuTabsLowLevelUser.FAX_ARRIVED;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.MenuTabsLowLevelUser.SEND_FAX;
import static pages.basePage.BasePage.MenuTabsBasePage.FAX;
import static pages.basePage.BasePage.MenuTabsBasePage.USER;
import static pages.userPage.userPagePopup.configureUser.ConfigureUserBasePopup.Tabs.ALLOCATIONS;
import static testsLowLevelUser.testData.AutotestUserData.autotestUserFullName;


@Listeners(CustomListeners.class)

public class FaxUserPageTests extends BaseTestMethods {
    ArrayList<User> usersList = new ArrayList<>();

    @Description("Check if low-level user can Send Fax")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "faxUserPageTests"})
    public void sendFaxTest(){
        SendFaxTestData fax = new SendFaxTestData();

        loginAsLowLevelUser()
            .goToMenuTab(FAX)
            .goToMenuTab(SEND_FAX);
        sendFaxUserPage
                .selectOutGoingNumber(fax.getOutgoingNumber())
                .enterDestinationNumber(fax.getDestinationNumber())
                .uploadFaxFile(fax.getFaxFilePath())
                .clickSend()
                .refreshPage(); // overcome due to bug EPRO-1127
        sendFaxUserPage
                .verifyIfFaxSent(fax);
    }

    @Description("Check validation of the Destination number input")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "faxUserPageTests"})
    public void validateDestinationNumberTest(){
        loginAsLowLevelUser()
            .goToMenuTab(FAX)
            .goToMenuTab(SEND_FAX);
        sendFaxUserPage
                .validateDestinationNumber(
                        "   "
                        ,"0123456",
                        "04412378",
                        "asd",
                        "0481234aa",
                        "+380441234567",
                        "+380677851465");
    }

    @Description("Check if user can configure Fax2Email settings")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "faxUserPageTests"})
    public void configureFax2emailSettingsTest(){
        Fax2EmailSettingsTestData fax2EmailSettings = new Fax2EmailSettingsTestData();

        loginAsLowLevelUser()
            .goToMenuTab(FAX)
            .goToMenuTab(BasePageLowLevelUser.MenuTabsLowLevelUser.FAX_SETTINGS);
        faxPage
                .selectNumber(fax2EmailSettings.getNumber())
                .clickEditFax2Email()
                .enterEmail(fax2EmailSettings.getEmail())
                .selectFaxReceiveFormat(fax2EmailSettings.getFaxMessageFormat())
                .saveChanges()
                .clickEditFax2Email()
                .validateFaxSettings(fax2EmailSettings);
    }

    @Description("Check if \"Outgoing number\" drop-down on SendFax tab contains all customer numbers")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "faxUserPageTests"})
    public void sendFaxOutgoingNumberContainsAllCustomerNumbersTest(){

        loginAsLowLevelUser()
                .goToMenuTab(FAX)
                .goToMenuTab(SEND_FAX);
        sendFaxUserPage
                .verifyIfAllCustomerNumbersAreAvailableInOutgoingNumberDropdown();
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        userCleanUp(usersList);
    }

}
