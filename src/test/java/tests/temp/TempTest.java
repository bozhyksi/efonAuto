package tests.temp;

import flow.BaseTestMethods;
import org.testng.annotations.Test;
import tests.phonebookPageTests.phonebookPageTestData.Phonebook;

public class TempTest extends BaseTestMethods {
    @Test
    public void Test1(){
        Phonebook phonebook = new Phonebook(5);
        excelFileWorker.writeExcelFile(phonebook.getArr());
    }
}
