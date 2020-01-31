package tests.phonebookPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.phonebookPageTests.phonebookPageTestData.Phonebook;

import static io.qameta.allure.Allure.step;

@Listeners(CustomListeners.class)

public class PhonebookPageTests extends BaseTestMethods {

    @Description("Verify if user is able to upload xlsx phonebook")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "phonebookPageTests"})
    public void VerifyIfUserIsAbleToUploadXlsxPhonebook(){
        step("Preparing test data. Create phonebook.xlsx file with 20 entries");
        int numberOfPhones = 10;
        Phonebook phonebook = new Phonebook(numberOfPhones);
        phonebook.createExcelPhonebookFile();

        step("Log in the system as VPBX admin and goto Phonebook tab");
        login();
        basePage.getTabPhonebook().click();
        phonebookPage.validatePageTitle("Phonebook");

        step("Upload Phonebook file");
        phonebookPage.uploadFile(phonebook.getfileName());

        step("Verify if all numbers from file were uploaded");
        phonebookPage.validateUploadedNumbers(numberOfPhones);

        step("Clear test environment - delete uploaded Phonebook");
        phonebookPage.getButtonDeletePhoneBook().click();
        confirmationPopup.getYesButton().click();
        phonebookPage.checkIfPhonebookWasDeleted();
    }

    @Description("Verify if user is able to restore phonebook from dataBase")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "phonebookPageTests"})
    public void VerifyIfUserIsAbleToRestorePhonebookFromDatabase(){
        int numberOfEntries = 10;

        step("Login the test env");
        login();

        step("Prepare phonebook file and upload it");
        uploadPhoneBook(numberOfEntries);

        step("Delete uploaded phonebook");
        deletePhonebook();

        step("Restore phonebook from dataBase and verify iff all numbers were restored");
        phonebookPage.getButtonRestore().click();
        confirmationPopup.getYesButton().click();
        phonebookPage.validateUploadedNumbers(numberOfEntries);

        step("Delete restored phonebook");
        deletePhonebook();
    }
}
