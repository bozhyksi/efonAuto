package tests.phonebookPageTests.phonebookPageTestData;

import flow.BaseTestMethods;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Phonebook extends BaseTestMethods {
    private static int shortDialmin = 290;
    private static int shortDialmax = 299;
    private static int shortDialCounter = shortDialmin;

    private Phonebook[] arr;
    private String number;
    private String name;
    private String shortDial;
    private String path = Paths.get("").toAbsolutePath().toString()+"\\tempTestData\\phonebook.xlsx";

    public Phonebook(int entryNumber) {
        this.arr = new Phonebook[entryNumber];
        for (int i = 0; i < entryNumber; i++) {
            arr[i] = new Phonebook();
        }
    }

    public Phonebook(){
        this.name = getRandomString(5)+" " + getRandomString(10);
        if(Integer.parseInt(getRandomNumber(5))%2 == 0) {
            this.number = getRandomPhone("00");
        }else {
            this.number = getRandomPhone("+");
        };
        if (shortDialCounter <= shortDialmax) {
            this.shortDial = String.valueOf(shortDialCounter);
            shortDialCounter++;
        }else {
            shortDialCounter = shortDialmin;
            this.shortDial = String.valueOf(shortDialCounter);
            shortDialCounter++;
        }
    }

    public String getName() {
        return name;
    }

    public String getShortDial() {
        return shortDial;
    }

    public String getNumber() {
        return number;
    }

    public Phonebook[] getArr() {
        return arr;
    }

    public List getList(){
        return Arrays.asList(arr);
    }

    public String getfileName() {
        return path;
    }

    public void createExcelPhonebookFile(){
        excelFileWriter.writeExcelFile(getfileName(), getList(),new PhonebookRowMapper());
    }

    @Override
    public String toString() {
        return "Phonebook{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", shortDial='" + shortDial + '\'' +
                '}';
    }
}
