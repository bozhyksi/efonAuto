package api.baseApiMethods;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;

import java.io.File;

import static api.data.endPoints.EndPoints.deleteAnnouncementDelete;
import static api.data.endPoints.EndPoints.postAnnouncementUpload;
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

}
