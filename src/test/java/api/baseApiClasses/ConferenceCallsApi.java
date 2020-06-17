package api.baseApiClasses;

import io.restassured.http.ContentType;

import static api.data.endPoints.EndPoints.deleteConfCallDelete;
import static api.data.endPoints.EndPoints.postConfCallCreate;
import static api.data.preparation.Preparation.login;

public class ConferenceCallsApi {

    public static void createConferenceCallApi(String json){
        login()
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json)
                .post(postConfCallCreate);
    }

    public static void deleteConferenceCallApi(String id){
        login()
                .delete(deleteConfCallDelete, id);
    }

}
