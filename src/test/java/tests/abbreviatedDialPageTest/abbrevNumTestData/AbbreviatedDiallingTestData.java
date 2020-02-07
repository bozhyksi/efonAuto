package tests.abbreviatedDialPageTest.abbrevNumTestData;

import flow.BaseTestMethods;

import java.util.ArrayList;

public class AbbreviatedDiallingTestData extends BaseTestMethods {
    private ArrayList<String> shortNumbers = new ArrayList<>();

    public AbbreviatedDiallingTestData(int start, int end){
        for (int i = start; i <= end; i++) {
            this.shortNumbers.add(String.valueOf(i));
        }
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
