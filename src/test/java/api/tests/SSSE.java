package api.tests;


import com.codeborne.selenide.Selenide;
import flow.BaseTestMethods;
import org.testng.annotations.Test;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.userPageTests.userPageTestData.User;

import static api.baseApiMethods.FileManagementApi.uploadAnnouncementApi;
import static api.baseApiMethods.UserApi.createUsersApi;
import static api.baseApiMethods.UserApi.deleteUsersApi;
import static api.baseLowLevelUserApi.FileManagementApi.uploadAnnouncementLowLevelUserApi;

public class SSSE extends BaseTestMethods {

    @Test
    public void asd(){
        User user = new User();

        createUsersApi(user);
        login(user.getLoginEmail(), user.getLoginPassword());
        Selenide.sleep(5000);
        logOut();
        deleteUsersApi(user);
    }

}