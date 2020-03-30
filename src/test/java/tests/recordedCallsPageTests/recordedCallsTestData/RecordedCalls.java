package tests.recordedCallsPageTests.recordedCallsTestData;

import flow.BaseTestMethods;

public class RecordedCalls extends BaseTestMethods {

    private String host = "192.122.0.12";
    private String port = getRandomNumber(4);
    private String user = getRandomString(15);
    private String pass = getRandomPassword();
    private String path = "\\eFonAuto\\proj_EFON\\efonAuto";
    private String untilDate = getDate();
    private String fromDate = "2015-08-01 00:00";
    private String searchData = "";

    private String sourceNumber1 = "044225787888";
    private String sourceNumber2 = "00451245789900";


    public String getSourceNumber1() {
        return sourceNumber1;
    }

    public String getSourceNumber2() {
        return sourceNumber2;
    }

    public String getSearchData() {
        return searchData;
    }

    public String getUntilDate() {
        return untilDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getHost() {
        return host;
    }

    public String getPass() {
        return pass;
    }

    public String getPath() {
        return path;
    }

    public String getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }
}
