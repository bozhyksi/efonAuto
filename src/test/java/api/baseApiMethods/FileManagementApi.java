package api.baseApiMethods;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;

import java.io.File;

import static api.data.endPoints.EndPoints.*;
import static api.data.preparation.Preparation.login;

public class FileManagementApi {

    @Step("Upload announcement via API")
    public static void uploadAnnouncementApi(FileManagementTestData file){
        login().
                given()
                .contentType("multipart/form-data")
                .accept(ContentType.JSON)
                .multiPart("multipartFile", new File(file.getFilePath()), "audio/wav")
                .formParam("displayName", file.getFileName())
                .when()
                .post(postAnnouncementUpload);
    }

    @Step("Delete announcement via API")
    public static void deleteAnnouncementApi (String id){
        login()
                .delete(deleteAnnouncementDelete,id);
    }

    @Step("Upload MOH via API")
    public static void uploadMohApi(FileManagementTestData moh){
        login().
                given()
                .contentType("multipart/form-data")
                .accept(ContentType.JSON)
                .multiPart("multipartFile", new File(moh.getFilePath()), "audio/wav")
                .formParam("displayName", moh.getFileName())
                .when()
                .post(postMohUpload);
    }

    @Step("Delete MOH via API")
    public static void deleteMohApi (String id){
        login()
                .delete(deleteMohDelete,id);
    }

}
