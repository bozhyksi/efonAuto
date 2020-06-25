package api.tests;

import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;

import static api.baseApiMethods.HuntGroupApi.createHuntGroupWithAuthorizedUserApi;
import static api.baseApiMethods.IVRApi.createIvrApi;
import static api.baseApiMethods.IVRApi.deleteIvrApi;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.autotestUserDisplayName;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.autotestUserId;

public class test2 {
    public static void main(String[] args) {
        HuntGroup huntGroup = new HuntGroup();
        createHuntGroupWithAuthorizedUserApi(huntGroup,autotestUserId,autotestUserDisplayName);


    }
}
