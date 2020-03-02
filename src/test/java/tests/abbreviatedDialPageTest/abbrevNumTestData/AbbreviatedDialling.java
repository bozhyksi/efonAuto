package tests.abbreviatedDialPageTest.abbrevNumTestData;

import flow.BaseTestMethods;

import java.util.ArrayList;

public class AbbreviatedDialling extends BaseTestMethods {
    private ArrayList<String> shortNumbers = new ArrayList<>();
    private String singleShortNum;
    private String fromNumber;
    private String untilNumber;
    private String extPhoneNum;
    private String lastName;
    private String firstName;
    private String company;

    public AbbreviatedDialling(int start, int end){
        this.fromNumber = String.valueOf(start);
        this.untilNumber = String.valueOf(end);
        this.extPhoneNum = getRandomPhone();
        this.lastName = getRandomString(15);
        this.firstName = getRandomString(15);
        this.company = getRandomString(15);

        for (int i = start; i <= end; i++) {
            this.shortNumbers.add(String.valueOf(i));
        }
    }

    public AbbreviatedDialling(String singleShortNum){
        this.singleShortNum = singleShortNum;
        this.extPhoneNum = getRandomPhone();
        this.lastName = getRandomString(15);
        this.firstName = getRandomString(15);
        this.company = getRandomString(15);
    }

    public String getSingleShortNum() {
        return singleShortNum;
    }

    public ArrayList<String> getShortNumbersArray() {
        return shortNumbers;
    }

    public String getFromNumber() {
        return fromNumber;
    }

    public String getUntilNumber() {
        return untilNumber;
    }

    public String getCompany() {
        return company;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getExtPhoneNum() {
        return extPhoneNum;
    }
}
