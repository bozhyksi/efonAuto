package api.baseApiMethods;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import tests.userPageTests.userPageTestData.User;

import static api.data.endPoints.EndPoints.deleteDeleteUser;
import static api.data.endPoints.EndPoints.postCreateUser;
import static api.data.preparation.Preparation.login;

public class UserApi {

    @Step("Create user via API")
    public static void createUserApi(String userJsonData){
        login()
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userJsonData)
                .post(postCreateUser);
    }

    @Step("Create user via API")
    public static void createUsersApi(User... users){
        for (User user : users) {
            login()
                    .given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(user.getJson())
                    .post(postCreateUser);
        }
    }

    @Step("Delete user via API")
    public static void deleteUserApi(String userId){
        login()
                .delete(deleteDeleteUser, userId);
    }

    @Step("Delete user via API")
    public static void deleteUsersApi(User ... users){
        for (User user : users) {
            if (!user.getId().equals("")) {
                login()
                        .delete(deleteDeleteUser, user.getId());
            }
        }
    }

}
