package testsLowLevelUser.faxUserPageTests;

import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testsLowLevelUser.faxUserPageTests.faxUserPageTestData.Fax2EmailSettingsTestData;
import testsLowLevelUser.faxUserPageTests.faxUserPageTestData.SendFaxTestData;

import static io.qameta.allure.Allure.step;
import static lowLevelUserPages.faxPageLowLevelUser.FaxesBaseUserPage.FaxesBaseUserPageTabs.FAX_SETTINGS;
import static pages.basePage.BasePage.MenuTabsBasePage.FAX;

@Listeners(CustomListeners.class)

public class FaxUserPageTests extends BaseTestMethods {

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
        sendFaxUserPage.validateDestinationNumber("   ");
        sendFaxUserPage.validateDestinationNumber("0123456");
        sendFaxUserPage.validateDestinationNumber("04412378");
        sendFaxUserPage.validateDestinationNumber("asd");
        sendFaxUserPage.validateDestinationNumber("0481234aa");
        sendFaxUserPage.validateDestinationNumber("+380441234567");
        sendFaxUserPage.validateDestinationNumber("+380677851465");
    }

    @Description("Check if user can configure Fax2Email settings")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "faxUserPageTests"})
    public void CheckIfUserCanConfigureFax2emailSettings(){
        step("Prepare test data");
        Fax2EmailSettingsTestData fax2EmailSettings = new Fax2EmailSettingsTestData();

        step("Login as low-level user");
        loginAsLowLevelUser();

        step("Goto Faxes -> Fax Settings");
        basePageLowLevelUser.goToMenuTab(FAX);
        faxesBaseUserPage.goToMenuTab(FAX_SETTINGS);

        step("Configure Fax2Email");
        faxSettingUserPage.configureFax2Email(fax2EmailSettings);
        refreshPage();

        step("Validate if changes were saved");
        faxSettingUserPage.validateFax2EmailSettings(fax2EmailSettings);

    }


}
