package testsLowLevelUser.faxUserPageTests;

import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testsLowLevelUser.faxUserPageTests.faxUserPageTestData.FaxTestData;

import static io.qameta.allure.Allure.step;

@Listeners(CustomListeners.class)

public class FaxUserPageTests extends BaseTestMethods {

    @Description("Check if low-level user can Send Fax")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "faxUserPageTests"})
    public void CheckIfLowLevelUserCanSendFax(){
        step("Prepare test data");
        FaxTestData fax = new FaxTestData();

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
        sendFaxUserPage.validateDestinationNumber("0381234567");
    }
}
