package api.baseApiClasses;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import static api.data.endPoints.EndPoints.deleteDeleteQueue;
import static api.data.endPoints.EndPoints.postCreateQueue;
import static api.data.preparation.Preparation.login;

public class QueueApi {

    @Step("Create Queue via API")
    public static void createQueueApi(String json){
        login()
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json)
                .post(postCreateQueue);
    }

    @Step("Delete Queue via API")
    public static void deleteQueueApi(String id){
        login()
                .delete(deleteDeleteQueue,id);
    }

}
