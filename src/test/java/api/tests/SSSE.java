package api.tests;


import org.testng.annotations.Test;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;

import static api.baseApiClasses.FileManagementApi.deleteAnnouncementApi;
import static api.baseApiClasses.FileManagementApi.uploadAnnouncementApi;

public class SSSE {

    @Test
    public void asd(){
        FileManagementTestData announcement = new FileManagementTestData();

        uploadAnnouncementApi(announcement);

        //deleteAnnouncementApi(announcement.getId());

    }

}