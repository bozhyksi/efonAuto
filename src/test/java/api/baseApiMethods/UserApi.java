package api.baseApiMethods;

import io.restassured.http.ContentType;

import static api.data.endPoints.EndPoints.deleteDeleteUser;
import static api.data.endPoints.EndPoints.postCreateUser;
import static api.data.preparation.Preparation.login;

public class UserApi {

    public static void createUserApi(String userJsonData){
        login()
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userJsonData)
                .post(postCreateUser);
    }

    public static void deleteUserApi(String userId){
        login()
                .delete(deleteDeleteUser, userId);
    }

}
