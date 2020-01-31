package tests.temp;

import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import org.testng.annotations.Test;
import tests.phonebookPageTests.phonebookPageTestData.Phonebook;

public class TempTest extends BaseTestMethods {
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Test1(){
        Phonebook phonebook = new Phonebook(20);
        phonebook.createExcelPhonebookFile();
    }
}
