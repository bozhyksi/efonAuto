package api.tests;

import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.userPageTests.userPageTestData.User;

import static api.baseApiMethods.AbbreviatedNumbersApi.createAbbreviatedNumberApi;
import static api.baseApiMethods.HuntGroupApi.createHuntGroupApi;
import static api.baseApiMethods.HuntGroupApi.createHuntGroupWithAuthorizedUserApi;
import static api.baseApiMethods.UserApi.*;
import static flow.PublicEnums.ItemsPerPage._All;


public class test2 {
    public static void main(String[] args) {
        HuntGroup huntGroup = new HuntGroup(new User(), new AbbreviatedDialling(AbbreviatedDialling.Type.SINGLE));
        createUsersApi(huntGroup.getAuthorisedUser());
        createAbbreviatedNumberApi(huntGroup.getShotNum());
        createHuntGroupWithAuthorizedUserApi(huntGroup);
    }
}
