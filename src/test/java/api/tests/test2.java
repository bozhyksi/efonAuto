package api.tests;

import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;


import static api.baseLowLevelUserApi.FileManagementApi.deleteAnnouncementLowLevelUserApi;
import static api.baseLowLevelUserApi.FileManagementApi.uploadAnnouncementLowLevelUserApi;
import static api.data.endPoints.EndPoints.deleteAnnouncementLowLevelUser;
import static api.data.preparation.Preparation.loginAsLowLevelUser;


public class test2 {
    public static void main(String[] args) {
        FileManagementTestData announcement = new FileManagementTestData();

        loginAsLowLevelUser()
                .delete(deleteAnnouncementLowLevelUser, "277237").then().log().all();

    }
}
