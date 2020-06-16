package api.baseApiMethods;

import io.restassured.http.ContentType;

import static api.data.endPoints.EndPoints.deleteHuntGroup;
import static api.data.endPoints.EndPoints.postHuntGroupCreate;
import static api.data.preparation.Preparation.login;

public class HuntGroupApi {

    public static void createHuntGroupApi(String jsonHuntGroup){
        login().
                given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonHuntGroup)
                .post(postHuntGroupCreate);
    }

    public static void deleteHuntGroupApi(String huntGroupsId){
        login()
                .delete(deleteHuntGroup,huntGroupsId);
    }
}
