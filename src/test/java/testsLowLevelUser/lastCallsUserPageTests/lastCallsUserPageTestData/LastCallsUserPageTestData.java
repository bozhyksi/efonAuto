package testsLowLevelUser.lastCallsUserPageTests.lastCallsUserPageTestData;

import flow.BaseTestMethods;

import static testsLowLevelUser.testData.AutotestUserData.autotestUserPhone;


public class LastCallsUserPageTestData extends BaseTestMethods {

    private String phoneNumber;
    private String fromDate;
    private String untilDate;

    public LastCallsUserPageTestData(){
        this.phoneNumber = autotestUserPhone;
        this.untilDate = getDate("yyyy-MM-dd HH:mm");
        this.fromDate = getDate("yyyy-MM-dd HH:mm","DAY",-2);
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getUntilDate() {
        return untilDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
