package api.baseApiMethods;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;
import tests.userPageTests.userPageTestData.User;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonValue;

import static api.data.endPoints.EndPoints.*;
import static api.data.preparation.Preparation.login;

public class AbbreviatedNumbersApi {

    @Step("Create single short number via API")
    public static void createAbbreviatedNumberApi(String number){
        login()
                .post(postCreateAbbreviatedNumber, number);
    }

    @Step("Create single short number via API")
    public static void createAbbreviatedNumberApi(AbbreviatedDialling abbreviatedDial){
        login()
                .post(postCreateAbbreviatedNumber, abbreviatedDial.getSingleShortNum());
    }

    @Step("Delete single short number via API")
    public static void deleteAbbreviatedNumberApi(String id){
        login()
                .delete(deleteAbbreviatedNumberDelete, id);
    }

    @Step("Delete single short number via API")
    public static void deleteAbbreviatedNumberApi(AbbreviatedDialling abbreviatedDial){

        unusedAbbrevNumApi(abbreviatedDial);
        login()
                .delete(deleteAbbreviatedNumberDelete, abbreviatedDial.getId());
    }

    @Step("Make abbreviated number unused")
    public static void unusedAbbrevNumApi(AbbreviatedDialling shortNum) {

        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        String json = factory.createObjectBuilder()
                .add("number", shortNum.getSingleShortNum())
                .add("customerId", JsonValue.NULL)
                .add("type", 0)
                .add("extNumber", JsonValue.NULL)
                .add("extLastName", JsonValue.NULL)
                .add("extFirstName", JsonValue.NULL)
                .add("extCompany", JsonValue.NULL)
                .add("internalRedirect", JsonValue.NULL)
                .build().toString();
        login()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json)
                .put(putUpdateShortNum);
    }

    @Step("Assign abbreviated number to internal user")
    public static void assignAbbrevNumInternUserApi(AbbreviatedDialling shortNum, User user){
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        String json = factory.createObjectBuilder()
                .add("number", shortNum.getSingleShortNum())
                .add("customerId", user.getId())
                .add("type", 1)
                .add("extNumber", JsonValue.NULL)
                .add("extLastName", JsonValue.NULL)
                .add("extFirstName",JsonValue.NULL)
                .add("extCompany", JsonValue.NULL)
                .add("internalRedirect",JsonValue.NULL)
                .build().toString();
        login()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json)
                .put(putUpdateShortNum);
    }

}
