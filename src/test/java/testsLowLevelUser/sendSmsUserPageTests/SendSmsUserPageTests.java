package testsLowLevelUser.sendSmsUserPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testsLowLevelUser.sendSmsUserPageTests.sendSmsTestData.AddressBookTestData;
import testsLowLevelUser.sendSmsUserPageTests.sendSmsTestData.AuthorizedNumberTestData;
import testsLowLevelUser.sendSmsUserPageTests.sendSmsTestData.SendSmsTestData;

import java.util.ArrayList;

import static api.baseLowLevelUserApi.SendSmsApi.*;
import static io.qameta.allure.Allure.step;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.MenuTabsLowLevelUser.*;

@Listeners(CustomListeners.class)

public class SendSmsUserPageTests extends BaseTestMethods {
    ArrayList<AddressBookTestData> addressBookList = new ArrayList<>();
    ArrayList<AuthorizedNumberTestData> authorizedNumbersList = new ArrayList<>();

    @Description("Check if low-level user can Send SMS")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "sendSmsUserPageTests"})
    public void sendSmsTest(){
        SendSmsTestData sms = new SendSmsTestData();

        loginAsLowLevelUser()
                .goToMenuTab(SEND_SMS);
        sendTextMessageUserPage
                .addRecipient(sms.getRecipientNumber())
                .enterSmsText(sms.getSmsText())
                .clickSend()
                    .verifyIfSmsSent();
    }

    @Description("Check if user can create/delete SMS address book")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "sendSmsUserPageTests"})
    public void createDeleteSmsAddressBookTest(){
        AddressBookTestData addressBook = new AddressBookTestData();
        addressBookList.add(addressBook);

        loginAsLowLevelUser()
            .goToMenuTab(SEND_SMS)
            .goToMenuTab(ADDRESS_BOOK);
        addressBookUserPage
                .clickAdd()
                .enterAddressBookRequiredFields(addressBook)
                .save()
                .verifyAddressBookEntryExists(addressBook)
                .deleteAddressBookEntry(addressBook)
                .verifyAddressBookEntryNotExists(addressBook);
    }

    @Description("Check if user can EDIT sms address book entry")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "sendSmsUserPageTests"})
    public void editSmsAddressBookEntryTest(){
        AddressBookTestData addressBook = new AddressBookTestData();
        AddressBookTestData addressBook2 = new AddressBookTestData();
        addressBookList.add(addressBook);
        addressBookList.add(addressBook2);

        createAddressBookEntryApi(addressBook);
        loginAsLowLevelUser()
                .goToMenuTab(SEND_SMS)
                .goToMenuTab(ADDRESS_BOOK);
        addressBookUserPage
                .clickEdit(addressBook)
                .clearFields()
                .enterAddressBookRequiredFields(addressBook2)
                .save()
                .verifyAddressBookEntryExists(addressBook2);
        deleteAddressBookEntryApi(addressBook,addressBook2);
    }

    @Description("Check if user can send sms using address book entries as recipients")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "sendSmsUserPageTests"})
    public void sendSmsUsingAddressBookEntriesAsRecipientsTest(){
        SendSmsTestData sms = new SendSmsTestData();
        AddressBookTestData addressBook = new AddressBookTestData();
        addressBookList.add(addressBook);

        createAddressBookEntryApi(addressBook);
        loginAsLowLevelUser()
                .goToMenuTab(SEND_SMS)
                .goToMenuTab(SEND_TEXT_MESSAGE);
        sendTextMessageUserPage
                .selectNumberFromAddressBook(addressBook.getMobileNumber())
                .enterSmsText(sms.getSmsText())
                .clickSend()
                .verifyIfSmsSent();
        deleteAddressBookEntryApi(addressBook);
    }

    @Description("Check validation of the Recipient number input")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "sendSmsUserPageTests"})
    public void validationOfTheRecipientNumberInputTest(){

        loginAsLowLevelUser()
                .goToMenuTab(SEND_SMS)
                .goToMenuTab(SEND_TEXT_MESSAGE);
        sendTextMessageUserPage
                .validateRecipientNumberInput("123")
                .validateRecipientNumberInput("0441234567")
                .validateRecipientNumberInput("07622225558987")
                .validateRecipientNumberInput("00000000")
                .validateRecipientNumberInput("asd0000")
                .validateRecipientNumberInput("076a123456")
                .validateRecipientNumberInput("          ");
    }

    @Description("Verify if user can add/delete authorized sender number")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "sendSmsUserPageTests"})
    public void addDeleteAuthorizedSenderNumberTest(){
        AuthorizedNumberTestData authorizedNumber = new AuthorizedNumberTestData();
        authorizedNumbersList.add(authorizedNumber);

        loginAsLowLevelUser()
                .goToMenuTab(SEND_SMS)
                .goToMenuTab(MANAGE_SENDER_NUMBERS_AND_NAMES);
        manageSenderNumbersUserPage
                .clickAdd()
                .enterMobileNumber(authorizedNumber.getNumber())
                .save()
                .verifyNonAuthorizedNumberExists(authorizedNumber.getNumber())
                .clickEdit(authorizedNumber.getNumber())
                .enterAuthorizationCode(authorizedNumber.getAuthorizationCode())
                .save()
                .verifyAuthorizedNumberExists(authorizedNumber.getNumber())
                .deleteSenderNumber(authorizedNumber.getNumber());
    }

    @Description("Verify \"Create new code\" button, and check if user can generate new code and use it")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "sendSmsUserPageTests"})
    public void generateNewCodeTest(){
        AuthorizedNumberTestData authorizedNumber = new AuthorizedNumberTestData();
        authorizedNumbersList.add(authorizedNumber);

        createAuthorizedNumberApi(authorizedNumber);
        loginAsLowLevelUser()
                .goToMenuTab(SEND_SMS)
                .goToMenuTab(MANAGE_SENDER_NUMBERS_AND_NAMES);
        manageSenderNumbersUserPage
                .clickCreateNewCode(authorizedNumber.getNumber())
                .clickEdit(authorizedNumber.getNumber())
                .enterAuthorizationCode(authorizedNumber.getAuthorizationCode())
                .save()
                .verifyAuthorizedNumberExists(authorizedNumber.getNumber());
        deleteAuthorizedNumberApi(authorizedNumber);
    }

    @Description("Verify if authorized senders can be used for SMS send")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "sendSmsUserPageTests"})
    public void sendSmsUsingAuthorizedSenderTest(){
        SendSmsTestData sms = new SendSmsTestData();
        AuthorizedNumberTestData authorizedNumber = new AuthorizedNumberTestData();
        authorizedNumbersList.add(authorizedNumber);

        createAuthorizedNumberApi(authorizedNumber);
        enterAuthorizationCodeApi(authorizedNumber);
        loginAsLowLevelUser()
                .goToMenuTab(SEND_SMS)
                .goToMenuTab(SEND_TEXT_MESSAGE);
        sendTextMessageUserPage
                .selectSenderNumber(authorizedNumber.getNumber())
                .enterSmsText(sms.getSmsText())
                .addRecipient(sms.getRecipientNumber())
                .clickSend()
                .verifyIfSmsSent();
        deleteAuthorizedNumberApi(authorizedNumber);
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        cleanUpAuthNums(authorizedNumbersList);
        addressBookCleanUp(addressBookList);
    }
}
