package testsLowLevelUser.faxUserPageTests;

import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.basePage.BasePage;
import tests.userPageTests.userPageTestData.User;
import testsLowLevelUser.faxUserPageTests.faxUserPageTestData.Fax2EmailSettingsTestData;
import testsLowLevelUser.faxUserPageTests.faxUserPageTestData.SendFaxTestData;

import java.util.ArrayList;

import static io.qameta.allure.Allure.step;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.MenuTabsLowLevelUser.FAX_ARRIVED;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.autotestUserName;

import static lowLevelUserPages.faxPageLowLevelUser.FaxesBaseUserPage.FaxesBaseUserPageTabs.FAX_SETTINGS;
import static pages.basePage.BasePage.MenuTabsBasePage.FAX;
import static pages.userPage.userPagePopup.configureUser.ConfigureUserBasePopup.Tabs.ALLOCATIONS;


@Listeners(CustomListeners.class)

public class FaxUserPageTests extends BaseTestMethods {
    ArrayList<User> usersList = new ArrayList<>();

    @Description("Check if low-level user can Send Fax")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "faxUserPageTests"})
    public void CheckIfLowLevelUserCanSendFax(){
        step("Prepare test data");
        SendFaxTestData fax = new SendFaxTestData();

        step("Login as low-level user");
        loginAsLowLevelUser();

        step("Go to Send Fax page");
        basePageLowLevelUser.getTabFax().click();
        faxesBaseUserPage.getTabFaxSend().click();
        waitUntilAlertDisappear();

        step("Select Outgoing number");
        sendFaxUserPage.getDropdownOutgoingNumber().selectOptionByValue(fax.getOutgoingNumberValue());
        fax.setOutgoingNumber(sendFaxUserPage.getDropdownOutgoingNumber().getSelectedText());

        step("Fill in Destination number");
        sendFaxUserPage.getInputDestinationNumber().setValue(fax.getDestinationNumber());

        step("Upload sample file");
        sendFaxUserPage.uploadFaxFile(fax.getFaxFilePath());
        waitUntilAlertDisappear();

        step("Click Send button");
        sendFaxUserPage.getButtonSend().shouldBe(Condition.enabled).click();
        refreshPage();

        step("Validate if fax appeared in the grid as sent");
        sendFaxUserPage.getFieldRecipientByNumber(fax.getDestinationNumber()).shouldBe(Condition.visible, Condition.exist);
        sendFaxUserPage.getSenderByText(fax.getOutgoingNumber()).shouldBe(Condition.visible, Condition.exist);
    }

    @Description("Check validation of the Destination number input")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "faxUserPageTests"})
    public void CheckValidationOfTheDestinationNumberInput(){

        step("Login as low-level user");
        loginAsLowLevelUser();

        step("Go to Send Fax page");
        basePageLowLevelUser.getTabFax().click();
        faxesBaseUserPage.getTabFaxSend().click();
        waitUntilAlertDisappear();

        step("Validate Destination number");
        sendFaxUserPage.validateDestinationNumber("   ","0123456", "04412378","asd","0481234aa","+380441234567", "+380677851465");
    }

    @Description("Check if user can configure Fax2Email settings")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "faxUserPageTests"})
    public void CheckIfUserCanConfigureFax2emailSettings(){
        step("Prepare test data");
        Fax2EmailSettingsTestData fax2EmailSettings = new Fax2EmailSettingsTestData();

        step("Login as low-level user");
        loginAsLowLevelUser();

        step("Goto Faxes -> Fax Settings");
        basePageLowLevelUser.goToMenuTab(BasePage.MenuTabsBasePage.FAX);
        faxesBaseUserPage.goToMenuTab(FAX_SETTINGS);

        step("Configure Fax2Email");
        faxSettingUserPage.configureFax2Email(fax2EmailSettings);
        refreshPage();

        step("Validate if changes were saved");
        faxSettingUserPage.validateFax2EmailSettings(fax2EmailSettings);

    }

    @Description("Check if selected number in Fax2Email is available in \"Select number\" drop-down on Fax tab")
    public void CheckIfSelectedNumberInFax2emailIsAvailableInSelectNumberDropdownOnFaxTab(){
        step("Prepare test data");
        User user = new User();
        usersList.add(user);

        login();
        createUser(user);
        refreshPage();
        userPage
                .openEditUserPopup(autotestUserName)
                .goToTab(ALLOCATIONS);
        allocationTabConfigUserPopup
                .selectNumberAssignFax2EmailAccess(user.getPhoneNumber())
                .saveChanges()
                .closeEditUserPopup();
        logOut();

        loginAsLowLevelUser();
        basePageLowLevelUser
                .goToMenuTab(FAX)
                .goToMenuTab(FAX_ARRIVED);
        faxArrivedUserPage
                .selectNumberFromSearchDropdown(user.getPhoneNumber());
        logOut();

        login();
        deleteUser(user);
        logOut();

        loginAsLowLevelUser();
        basePageLowLevelUser
                .goToMenuTab(FAX)
                .goToMenuTab(FAX_ARRIVED);
        faxArrivedUserPage
                .validateNumberSearchDropDownItems();
    }

    @Description("Check if \"Select number\" drop-down on Fax tab contains only user related numbers")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "faxUserPageTests"})
    public void checkifSelectNumberDropDownOnFaxTabContainsOnlyUserRelatedNumbers(){
        loginAsLowLevelUser();
        basePageLowLevelUser
                .goToMenuTab(FAX)
                .goToMenuTab(FAX_ARRIVED);
        faxArrivedUserPage
                .validateNumberSearchDropDownItems();
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        startBrowser();
        login();
        userCleanUp(usersList);
        closeBrowser();
    }

}
