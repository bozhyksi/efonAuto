package api.tests;

import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;
import tests.userPageTests.userPageTestData.User;

import static api.baseApiMethods.AbbreviatedNumbersApi.createAbbreviatedNumberApi;
import static api.baseApiMethods.UserApi.createUsersApi;
import static api.baseApiMethods.UserApi.setItemsPerPageApi;
import static flow.PublicEnums.ItemsPerPage._All;


public class test2 {
    public static void main(String[] args) {
        User user1 = new User(new AbbreviatedDialling(AbbreviatedDialling.Type.SINGLE));
        User user2 = new User(new AbbreviatedDialling(AbbreviatedDialling.Type.SINGLE));
        User user3 = new User(new AbbreviatedDialling(AbbreviatedDialling.Type.SINGLE));

        createAbbreviatedNumberApi(user1.getShortNum());
        createAbbreviatedNumberApi(user2.getShortNum());
        createAbbreviatedNumberApi(user3.getShortNum());
        createUsersApi(user1,user2,user3);

    }
}
