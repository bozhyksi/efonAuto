package api.tests;

import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;
import tests.userPageTests.userPageTestData.User;
import testsLowLevelUser.sendSmsUserPageTests.sendSmsTestData.AddressBookTestData;

import static api.baseApiMethods.AbbreviatedNumbersApi.createAbbreviatedNumberApi;
import static api.baseApiMethods.SendSmsApi.createAddressBookEntryApi;
import static api.baseApiMethods.SendSmsApi.deleteAddressBookEntryApi;
import static api.baseApiMethods.UserApi.createUsersApi;
import static tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling.Type.SINGLE;


public class test2 {
    public static void main(String[] args) {
        User user = new User();


        createUsersApi(user);

    }
}
