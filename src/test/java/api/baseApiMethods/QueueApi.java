package api.baseApiMethods;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import tests.queuesPageTest.queueTestData.Queue;

import static api.data.endPoints.EndPoints.*;
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

    @Step("Create Queue via API")
    public static void createQueueApi(Queue queue){
        login()
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(queue.getJson())
                .post(postCreateQueue);
    }

    @Step("Create Queue with manager and reporter via API")
    public static void createQueueWithMangerReporterApi(Queue queue){
        UserApi.createUserApi(queue.getReporter().getJson());
        UserApi.createUserApi(queue.getManager().getJson());

        login()
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(queue.getJson())
                .post(postCreateQueue);
    }

    @Step("Delete Queue via API")
    public static void deleteQueueApi(String id){
        login()
                .delete(deleteDeleteQueue,id);
    }

    @Step("Delete Queue via API")
    public static void deleteQueueApi(Queue queue){
        login()
                .delete(deleteDeleteQueue,queue.getId());
    }

    @Step("Delete Queue with manager and reporter via API")
    public static void deleteQueueWithManageReporterApi(Queue queue){
        login()
                .delete(deleteDeleteQueue,queue.getId());

        UserApi.deleteUserApi(queue.getManager().getId());
        UserApi.deleteUserApi(queue.getReporter().getId());
    }
}
