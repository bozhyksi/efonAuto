package api.tests;

import testsLowLevelUser.sendSmsUserPageTests.sendSmsTestData.AddressBookTestData;

import static api.baseApiMethods.SendSmsApi.createAddressBookEntryApi;
import static api.baseApiMethods.SendSmsApi.deleteAddressBookEntryApi;


public class test2 {
    public static void main(String[] args) {
        AddressBookTestData addressBookTestData = new AddressBookTestData();
        createAddressBookEntryApi(addressBookTestData);
        deleteAddressBookEntryApi(addressBookTestData);
        deleteAddressBookEntryApi(addressBookTestData);

    }
}
