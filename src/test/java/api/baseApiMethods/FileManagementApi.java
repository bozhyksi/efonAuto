package api.baseApiMethods;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import org.testng.Assert;
import pages.fileManagementPage.FileManagementBasePage;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.userPageTests.userPageTestData.User;

import java.io.File;

import static api.data.endPoints.EndPoints.*;
import static api.data.preparation.Preparation.login;

public class FileManagementApi {

    @Step("Upload announcement via API")
    public static void uploadAnnouncementApi(FileManagementTestData file) {
        boolean success = login().
                given()
                .contentType("multipart/form-data")
                .accept(ContentType.JSON)
                .multiPart("multipartFile", new File(file.getFilePath()), "audio/wav")
                .formParam("displayName", file.getFileName())
                .when()
                .post(postAnnouncementUpload)
                .then()
                .extract()
                .body()
                .path("success");
        Assert.assertTrue(success,"\n\n Announcement upload via API failed! Success - false \n\n");
    }

    @Step("Upload announcement for proper user via API")
    public static void uploadAnnouncementForUserApi(User user, FileManagementTestData file) {
        boolean success = login().
                given()
                .contentType("multipart/form-data")
                .accept(ContentType.JSON)
                .multiPart("multipartFile", new File(file.getFilePath()), "audio/wav")
                .formParam("displayName", file.getFileName())
                .when()
                .post(postUploadAnnouncementForUser, user.getId())
                .then()
                .extract()
                .body()
                .path("success");
        Assert.assertTrue(success,"\n\n Announcement upload via API failed! Success - false \n\n");
    }

    @Step("Delete announcement via API")
    public static void deleteAnnouncementApi(String id) {
        login()
                .delete(deleteAnnouncementDelete, id);
    }

    @Step("Delete announcement via API")
    public static void deleteAnnouncementApi(FileManagementTestData ... fileList) {
        for (FileManagementTestData announcement:fileList) {
            if (!announcement.getId().equals("")) {
                login()
                        .delete(deleteAnnouncementDelete, announcement.getId());
            }
        }
    }

    @Step("Upload MOH via API")
    public static void uploadMohApi(FileManagementTestData... mohFiles) {
        for (FileManagementTestData moh : mohFiles) {
            login().
                    given()
                    .contentType("multipart/form-data")
                    .accept(ContentType.JSON)
                    .multiPart("multipartFile", new File(moh.getFilePath()), "audio/wav")
                    .formParam("displayName", moh.getFileName())
                    .when()
                    .post(postMohUpload);
        }
    }

    @Step("Delete MOH via API")
    public static void deleteMohApi(FileManagementTestData... mohFiles) {
        for (FileManagementTestData moh : mohFiles) {
            login()
                    .delete(deleteMohDelete, moh.getMohId());
        }
    }

}
