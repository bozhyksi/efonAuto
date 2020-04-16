package testsLowLevelUser.sendSmsUserPageTests;

import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testsLowLevelUser.sendSmsUserPageTests.sendSmsTestData.AddressBookTestData;
import testsLowLevelUser.sendSmsUserPageTests.sendSmsTestData.SendSmsTestData;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.exist;
import static io.qameta.allure.Allure.step;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.MenuTabsLowLevelUser.MANAGE_SENDER_NUMBERS_AND_NAMES;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.MenuTabsLowLevelUser.SEND_SMS;

@Listeners(CustomListeners.class)

public class SendSmsUserPageTests extends BaseTestMethods {
    ArrayList<AddressBookTestData> addressBookList = new ArrayList<>();
    ArrayList<String> senderNumbersList = new ArrayList<>();

    @Description("Check if low-level user can Send SMS")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "sendSmsUserPageTests"})
    public void CheckIfLowLevelUserCanSendSms(){
        step("Prepare test data");
        SendSmsTestData sms = new SendSmsTestData();

        step("Login as low-level user");
        loginAsLowLevelUser();

        step("Go to Send SMS page");
        basePageLowLevelUser.getTabSendSms().click();
        sendSmsBaseUserPage.getTabSendTextMessage().click();

        step("Add recipient");
        sendTextMessageUserPage.getInputRecipientNumber().setValue(sms.getRecipientNumber());
        waitUntilAlertDisappear();
        sendTextMessageUserPage.getButtonAddRecipient().click();

        step("Fill in SMS text");
        sendTextMessageUserPage.getInputSmsTextArea().setValue(sms.getSmsText());

        step("Click Send SMS");
        sendTextMessageUserPage.getButtonSend().click();
        waitUntilAlertDisappear();

        step("Check if SMS were sent and \"Confirmation popup appears\"");
        smsConfirmationPopup.getTitle().shouldBe(Condition.visible,Condition.appear);
        smsConfirmationPopup.getButtonClose().click();
    }

    @Description("Check if user can create SMS address book")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "sendSmsUserPageTests"})
    public void CheckIfUserCanCreateSmsAddressBook(){
        step("Prepare test data");
        AddressBookTestData addressBook = new AddressBookTestData();
        addressBookList.add(addressBook);

        step("Login as low-level user");
        loginAsLowLevelUser();

        step("Go to Send SMS page");
        basePageLowLevelUser.getTabSendSms().click();
        sendSmsBaseUserPage.getTabAddressBook().click();

        step("Click Add button");
        addressBookUserPage.getButtonAdd().click();

        step("Fill in all Address Book required fields");
        createSmsAddressPopup.fillInAllAddressBookRequiredFields(addressBook);

        step("Save all data");
        createSmsAddressPopup.getButtonSave().click();
        waitUntilAlertDisappear();

        step("Validate if entry was created");
        addressBookUserPage.validateIfEntryWasCreated(addressBook);

        step("Clear test date - delete test address book entry");
        deleteAddressBook(addressBook.getMobileNumber());
    }

    @Description("Check if user can EDIT sms address book entry")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "sendSmsUserPageTests"})
    public void CheckIfUserCanEditSmsAddressBookEntry(){
        step("Prepare test data");
        AddressBookTestData addressBook = new AddressBookTestData();
        AddressBookTestData addressBook2 = new AddressBookTestData();
        addressBookList.add(addressBook);
        addressBookList.add(addressBook2);

        step("Login as low-level user");
        loginAsLowLevelUser();

        step("Create address book entry");
        createAddressBookEntry(addressBook);

        step("Click edit button on address book entry");
        addressBookUserPage.getButtonEditByText(addressBook.getMobileNumber()).click();
        waitUntilAlertDisappear();

        step("Enter new values");
        createSmsAddressPopup.clearFields();
        createSmsAddressPopup.fillInAllAddressBookRequiredFields(addressBook2);
        createSmsAddressPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();

        step("Validate if data was changed");
        addressBookUserPage.validateIfEntryWasCreated(addressBook2);

        step("Clear test date - delete test address book entry");
        deleteAddressBook(addressBook2.getMobileNumber());
    }

    @Description("Check if user can send sms using address book entries as recipients")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "sendSmsUserPageTests"})
    public void CheckIfUserCanSendSmsUsingAddressBookEntriesAsRecipients(){
        step("Prepare test data");
        SendSmsTestData sms = new SendSmsTestData();
        AddressBookTestData addressBook = new AddressBookTestData();
        addressBookList.add(addressBook);

        step("Login as low-level user");
        loginAsLowLevelUser();

        step("Create address book entry");
        createAddressBookEntry(addressBook);

        step("Go to Send SMS page");
        basePageLowLevelUser.getTabSendSms().click();
        sendSmsBaseUserPage.getTabSendTextMessage().click();

        step("Add recipient from address book");
        sendTextMessageUserPage.getDropdownAddressBook().selectOptionByValue(addressBook.getMobileNumber());

        step("Fill in SMS text");
        sendTextMessageUserPage.getInputSmsTextArea().setValue(sms.getSmsText());

        step("Click Send SMS");
        sendTextMessageUserPage.getButtonSend().click();
        waitUntilAlertDisappear();

        step("Check if SMS were sent and \"Confirmation popup appears\"");
        smsConfirmationPopup.getTitle().shouldBe(Condition.visible,Condition.appear);
        smsConfirmationPopup.getButtonClose().click();

        step("Clear test date - delete test address book entry");
        deleteAddressBook(addressBook.getMobileNumber());
    }

    @Description("Check validation of the Recipient number input")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "sendSmsUserPageTests"})
    public void CheckValidationOfTheRecipientNumberInput(){
        step("Login as low-level user");
        loginAsLowLevelUser();

        step("Go to Send SMS page");
        basePageLowLevelUser.getTabSendSms().click();
        sendSmsBaseUserPage.getTabSendTextMessage().click();

        step("Check validation of the Recipient number input");
        sendTextMessageUserPage.checkValidationMessage("123");
        sendTextMessageUserPage.checkValidationMessage("0441234567");
        sendTextMessageUserPage.checkValidationMessage("07622225558987");
        sendTextMessageUserPage.checkValidationMessage("00000000");
        sendTextMessageUserPage.checkValidationMessage("asd0000");
        sendTextMessageUserPage.checkValidationMessage("076a123456");
        sendTextMessageUserPage.checkValidationMessage("          ");
    }

    @Description("Verify if user can add/delete authorized sender number")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "sendSmsUserPageTests"})
    public void VerifyIfUserCanAddAuthorizedSenderNumber(){
        step("Prepare test data");
        String senderNumber = getRandomPhone("076",7);
        senderNumbersList.add(senderNumber);

        step("Login as low-level user");
        loginAsLowLevelUser();

        step("Goto MANAGE SENDER NUMBERS AND NAMES");
        basePageLowLevelUser.goToMenuTab(SEND_SMS).goToMenuTab(MANAGE_SENDER_NUMBERS_AND_NAMES);

        step("Click Add, open popup, set new sender number");
        manageSenderNumbersUserPage.getButtonAdd().click();
        waitUntilAlertDisappear();
        newSenderNumberPopup.getInputMobileNumber().setValue(senderNumber);
        newSenderNumberPopup.getButtonSave().click();
        waitUntilAlertDisappear();

        step("Verify if number was added as NON Authorized");
        manageSenderNumbersUserPage.verifyIfNumberAddedAsNonAuthorized(senderNumber);

        step("Open edit popup and enter authorization code");
        manageSenderNumbersUserPage.getButtonEditByText(senderNumber).click();
        waitUntilAlertDisappear();
        activateAuthorisationCodePopup.enterAuthorizationCode(senderNumber);
        activateAuthorisationCodePopup.getButtonSave().click();
        waitUntilAlertDisappear();

        step("Verify if number was added as Authorized");
        manageSenderNumbersUserPage.verifyIfNumberAddedAsAuthorized(senderNumber);

        step("Delete test sender number");
        manageSenderNumbersUserPage.getButtonDeleteByText(senderNumber).click();
        waitUntilAlertDisappear();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        refreshPage();
        manageSenderNumbersUserPage.getFiledMobileNumberByText(senderNumber).shouldNot(exist);
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        startBrowser();
        loginAsLowLevelUser();
        addressBookCleanUp(addressBookList);
        closeBrowser();
    }

}
