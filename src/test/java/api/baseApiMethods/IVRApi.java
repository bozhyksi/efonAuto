package api.baseApiMethods;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import tests.IVRpageTests.IVRtestData.IVRtestData;

import static api.baseApiMethods.FileManagementApi.deleteAnnouncementApi;
import static api.baseApiMethods.FileManagementApi.uploadAnnouncementApi;
import static api.data.endPoints.EndPoints.*;
import static api.data.preparation.Preparation.login;

public class IVRApi {

    @Step("Create IVR via API")
    public static void createIvrApi(String json){
        login()
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json)
                .post(postIvrCreate);
    }

    @Step("Create IVR via API")
    public static void createIvrApi(IVRtestData ivr){

        uploadAnnouncementApi(ivr.getAnnouncement());

        login()
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(ivr.getJson())
                .post(postIvrCreate);

    }

    @Step("Delete IVR via API")
    public static void deleteIvrApi(String id){
        login()
                .delete(deleteIvrDelete,id);
    }

    @Step("Delete IVR via API")
    public static void deleteIvrApi(IVRtestData ivr){

        login()
                .delete(deleteIvrDelete,ivr.getId());

        deleteAnnouncementApi(ivr.getAnnouncement().getId());

    }

}
