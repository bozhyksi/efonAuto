package tests.lastCallsPageTests.lastCallsTestData;

import flow.BaseTestMethods;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LastCallsTestData extends BaseTestMethods {
    private String fromDate = "2015-04-21 12:58";
    private String toDate = getDate();
    private String monthDate = getCurrentMonth();
    private String desctinationNumber = "0218567";

    public String getDesctinationNumber() {
        return desctinationNumber;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getMonthDate() {
        return monthDate;
    }

    public String getToDate() {
        return toDate;
    }

    private String getCurrentMonth(){
        SimpleDateFormat formatter = new SimpleDateFormat("MM.yyyy");
        Date currentDate = new Date();
        String date = formatter.format(currentDate);
        return date;
    }

}
