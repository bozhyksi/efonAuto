package api.baseApiMethods;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import tests.userPageTests.userPageTestData.User;

import static api.data.endPoints.EndPoints.*;
import static api.data.preparation.Preparation.login;

public class UserApi {

    @Step("Create user via API")
    public static void createUserApi(String userJsonData){
        boolean success = login()
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userJsonData)
                .post(postCreateUser)
                .then()
                .extract()
                .body()
                .path("success");
        Assert.assertTrue(success,"\n\n User creation via API failed! Success - false \n\n");
    }

    @Step("Create user via API")
    public static void createUsersApi(User... users){
        for (User user : users) {
            boolean success = login()
                    .given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(user.getJson())
                    .post(postCreateUser)
                    .then()
                    .extract()
                    .body()
                    .path("success");
            Assert.assertTrue(success,"\n\n User creation via API failed! Success - false \n\n");
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

    public static void setInterfaceLangEnglishApi(String login, String pass){
        boolean success = login(login,pass)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("")
                .put(putInterfaceLanguageEn)
                .then().extract().path("success");
        Assert.assertTrue(success,"\n\n Interface language was not set to EN via API. Success - false! \n\n");
    }

}
