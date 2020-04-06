package testsLowLevelUser.sendSmsUserPageTests.sendSmsTestData;

import flow.BaseTestMethods;

public class AddressBookTestData extends BaseTestMethods {
    private String mobileNumber;
    private String firstName;
    private String lastName;
    private String company;

    public AddressBookTestData(){
        this.mobileNumber = getRandomPhone("074",7);
        this.firstName = "FistName_"+getRandomString(10);
        this.lastName = "LastName_"+getRandomString(10);
        this.company = "Company_"+getRandomString(10);
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

    public String getMobileNumber() {
        return mobileNumber;
    }
}
