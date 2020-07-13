package api.baseApiMethods;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import org.testng.Assert;
import tests.queuesPageTest.queueTestData.Queue;

import static api.data.endPoints.EndPoints.*;
import static api.data.preparation.Preparation.login;

public class QueueApi {

    @Step("Create Queue via API")
    public static void createQueueApi(Queue queue){
        boolean success = login()
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(queue.getJson())
                .post(postCreateQueue)
                .then()
                .extract()
                .body()
                .path("success");
        Assert.assertTrue(success,"\n\n Queue creation via API failed! Success - false \n\n");;
    }

    @Step("Create Queue with manager and reporter via API")
    public static void createQueueWithMangerReporterApi(Queue queue, String managerContactID, String reporterContactID){
        login()
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(queue.getJson(managerContactID, reporterContactID))
                .post(postCreateQueue);
    }

    @Step("Delete Queue via API")
    public static void deleteQueueApi(String id){
        login()
                .delete(deleteDeleteQueue,id);
    }

    @Step("Delete Queue via API")
    public static void deleteQueueApi(Queue ... queues){
        for (Queue queue:queues) {
            if (!queue.getId().equals("")){
                login()
                        .delete(deleteDeleteQueue,queue.getId());
            }
        }
    }

    @Step("Delete Queue with manager and reporter via API")
    public static void deleteQueueWithManageReporterApi(Queue queue){
        login()
                .delete(deleteDeleteQueue,queue.getId());

        UserApi.deleteUserApi(queue.getManager().getId());
        UserApi.deleteUserApi(queue.getReporter().getId());
    }

    @Step("Add agent to Queue via API")
    public static void addQueueAgentApi(Queue queue){
        login()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(queue.getAddAgentJson())
                .post(postAddQueueAgent,queue.getId());
    }

    @Step("Add agent to Queue via API")
    public static void addQueueAgentApi(Queue queue, String agentAccountID){
        login()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(queue.getAddAgentJson(agentAccountID))
                .post(postAddQueueAgent,queue.getId());
    }
}
