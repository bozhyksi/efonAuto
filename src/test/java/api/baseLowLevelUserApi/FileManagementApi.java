package api.baseLowLevelUserApi;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import org.testng.Assert;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;

import java.io.File;

import static api.data.endPoints.EndPoints.*;
import static api.data.preparation.Preparation.login;
import static api.data.preparation.Preparation.loginAsLowLevelUser;

public class FileManagementApi {
    @Step("Upload announcement via LowLevelApi")
    public static void uploadAnnouncementLowLevelUserApi(FileManagementTestData file) {
        boolean success = loginAsLowLevelUser().
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

    @Step("Delete announcement via LowLevelApi")
    public static void deleteAnnouncementLowLevelUserApi(FileManagementTestData ... fileList) {
        for (FileManagementTestData announcement:fileList) {
            if (!announcement.getId().equals("")) {
                boolean success = loginAsLowLevelUser()
                        .delete(deleteAnnouncementLowLevelUser, announcement.getId())
                        .then()
                        .extract().path("success");
                Assert.assertTrue(success,"\n\n Announcement deletion via API failed! Success - false \n\n");
            }
        }
    }
}
