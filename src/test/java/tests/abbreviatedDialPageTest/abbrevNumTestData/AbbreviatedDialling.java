package tests.abbreviatedDialPageTest.abbrevNumTestData;

import flow.BaseTestMethods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AbbreviatedDialling extends BaseTestMethods {
    public enum Type{
        SINGLE,
        RANGE;
    }

    private final int startRange = 11;
    private final int endRange = 98;

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
        this.singleShortNum = getRandomNumber(start,end);

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

    public AbbreviatedDialling(Type type){
        switch (type){
            case SINGLE:{
                this.singleShortNum = generateRandomSingleAbbrevNumber();
                this.extPhoneNum = getRandomPhone();
                this.lastName = getRandomString(15);
                this.firstName = getRandomString(15);
                this.company = getRandomString(15);
                break;
            }
            case RANGE:{
                String [] range = generateRandomAbbrevNumsRange();
                this.fromNumber = range[0];
                this.untilNumber = range [1];
                this.extPhoneNum = getRandomPhone();
                this.lastName = getRandomString(15);
                this.firstName = getRandomString(15);
                this.company = getRandomString(15);

                for (int i = Integer.parseInt(range[0]); i <= Integer.parseInt(range[1]) ; i++) {
                    shortNumbers.add(String.valueOf(i));
                }
            }
        }
    }

    //<editor-fold desc="get\set">
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
    //</editor-fold>

    private String generateRandomSingleAbbrevNumber(){
        String abbrevNum = getRandomNumber(startRange,endRange);
        if (verifyIfSingleAbbrevNumFree(abbrevNum)) {
            return abbrevNum;
        }
        else {
            return generateRandomSingleAbbrevNumber();
        }
    }

    public String[] generateRandomAbbrevNumsRange(){
        String[] range = new String[2];
        range[0] = getRandomNumber(startRange,endRange);
        range[1] = getRandomNumber(Integer.parseInt(range[0])+3, Integer.parseInt(range[0])+5);

        if (verifyIfAbbrevNumsRangeFree(range[0], range[1])){
            return range;
        }else {
            return generateRandomAbbrevNumsRange();
        }
    }

    private boolean verifyIfSingleAbbrevNumFree(String abrevNumber){
        String query =  "SELECT number FROM webadmin_20170426.internal_number where abo_fk = 1212363";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(query);
        ArrayList<String> nums = new ArrayList<>();
        while (true){
            try {
                if (!resultSet.next()) break;
                nums.add(resultSet.getString(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (nums.contains(abrevNumber)) return false;
        return true;
    }

    private boolean verifyIfAbbrevNumsRangeFree(String start, String end){
        String query =  "SELECT number FROM webadmin_20170426.internal_number where abo_fk = 1212363";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(query);
        ArrayList<String> nums = new ArrayList<>();
        while (true){
            try {
                if (!resultSet.next()) break;
                nums.add(resultSet.getString(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        for (int i = Integer.parseInt(start); i <= Integer.parseInt(end ); i++) {
            if (nums.contains(String.valueOf(i))) return  false;
        }
        return true;
    }

    public String getId(){
        String query = "SELECT * FROM webadmin_20170426.internal_number where number=%s and abo_fk=1212363";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(String.format(query,getSingleShortNum()));
        while (true){
            try {
                if (!resultSet.next()) break;
                return resultSet.getString(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

}
