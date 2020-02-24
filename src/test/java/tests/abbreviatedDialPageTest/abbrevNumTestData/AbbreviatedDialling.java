package tests.abbreviatedDialPageTest.abbrevNumTestData;

import flow.BaseTestMethods;

import java.util.ArrayList;

public class AbbreviatedDialling extends BaseTestMethods {
    private ArrayList<String> shortNumbers = new ArrayList<>();
    private String singleShortNum;

    public AbbreviatedDialling(int start, int end){
        for (int i = start; i <= end; i++) {
            this.shortNumbers.add(String.valueOf(i));
        }
    }

    public AbbreviatedDialling(String singleShortNum){
        this.singleShortNum = singleShortNum;
    }

    public String getSingleShortNum() {
        return singleShortNum;
    }

    public ArrayList<String> getShortNumbersArray() {
        return shortNumbers;
    }

    public String getFromNumber(){
        return shortNumbers.get(0);
    }

    public String getUntilNumber(){
        return shortNumbers.get(shortNumbers.size()-1);
    }
}
