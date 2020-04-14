package tests.phonebookPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.phonebookPageTests.phonebookPageTestData.Phonebook;
import tests.phonebookPageTests.phonebookPageTestData.PhonebookRowMapper;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;

@Listeners(CustomListeners.class)

public class PhonebookPageTests extends BaseTestMethods {
    ArrayList<Phonebook> phonebookList = new ArrayList<>();

    @Description("Verify if user is able to upload xlsx phonebook")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "phonebookPageTests"})
    public void VerifyIfUserIsAbleToUploadXlsxPhonebook(){
        step("Preparing test data. Create phonebook.xlsx file with 10 entries");
        int numberOfPhones = 10;
        Phonebook phonebook = new Phonebook(numberOfPhones);
        phonebookList.add(phonebook);

        phonebook.createExcelPhonebookFile();
        try {
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
        } finally {
            step("Delete test excel file");
            //excelFileWorker.deleteFile(phonebook.getfileName());
        }

    }

    @Description("Verify if user is able to restore phonebook from dataBase")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "phonebookPageTests"})
    public void VerifyIfUserIsAbleToRestorePhonebookFromDatabase(){
        int numberOfEntries = 10;
        Phonebook phonebook = new Phonebook(numberOfEntries);
        phonebookList.add(phonebook);

        try {
            step("Login the test env");
            login();

            step("Prepare phonebook file and upload it");
            uploadPhoneBook(phonebook);

            step("Delete uploaded phonebook");
            deletePhonebook();

            step("Restore phonebook from dataBase and verify iff all numbers were restored");
            phonebookPage.getButtonRestore().click();
            confirmationPopup.getYesButton().click();
            phonebookPage.validateUploadedNumbers(numberOfEntries);
            step("Delete restored phonebook");
            deletePhonebook();

        } finally {
            excelFileWorker.deleteFile(phonebook.getfileName());
        }
    }

    @Description("Verify if user is able to download phonebook")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "phonebookPageTests"})
    public void VerifyIfUserIsAbleToDownloadPhonebook(){
        try {
            step("Prepare test data");
            int numberOfEnriesInFile = 10;
            Phonebook phonebook = new Phonebook(numberOfEnriesInFile);
            phonebookList.add(phonebook);
            List<Phonebook> phonebooks;

            step("Log in the system as VPBX admin");
            login();

            step("Goto Phonebook tab Upload new phonebook file");
            uploadPhoneBook(phonebook);

            step("Download phonebook");
            phonebookPage.downloadPhonebook();

            step("Read downloaded excel file, and verify data");
            phonebooks = excelFileWorker.readExcelFile("phonebook.xls",new PhonebookRowMapper(), new Phonebook());
            Assert.assertEquals(phonebooks.size()-1, numberOfEnriesInFile);

            step("Delete test data");
            deletePhonebook();

        } finally {
            step("Delete test data - delete downloaded .xlsx file");
            excelFileWorker.deleteFile("phonebook.xls");
        }
    }

    @Description("Verify if user is able to download example file")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "phonebookPageTests"}) // bug
    public void VerifyIfUserIsAbleToDownloadExample(){
        step("Log in the system as VPBX admin and goto Phonebook tab");
        login();
        basePage.getTabPhonebook().click();
        phonebookPage.validatePageTitle("Phonebook");

        step("Click Download Example button");
        phonebookPage.downloadExample();

        step("Check if example file was downloaded");
        try {
            Assert.assertTrue(excelFileWorker.checkIfFileExists("excelimport_phonebook_example.xls"), "Example file not found");
        } finally {
            step("Delete example file");
            excelFileWorker.deleteFile("excelimport_phonebook_example.xls");
        }
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        phoneBookExcelCleanUp(phonebookList);
    }
}
