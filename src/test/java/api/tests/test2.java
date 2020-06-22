package api.tests;

import io.restassured.http.ContentType;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonValue;

import static api.baseApiMethods.IVRApi.createIvrApi;
import static api.baseApiMethods.IVRApi.deleteIvrApi;
import static api.data.endPoints.EndPoints.putUpdateShortNum;
import static api.data.preparation.Preparation.login;

public class test2 {
    public static void main(String[] args) {

        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        String kkj = factory.createObjectBuilder()
                .add("number", 11)
                .add("customerId", JsonValue.NULL)
                .add("type", 0)
                .add("extNumber", JsonValue.NULL)
                .add("extLastName", JsonValue.NULL)
                .add("extFirstName",JsonValue.NULL)
                .add("extCompany", JsonValue.NULL)
                .add("internalRedirect",JsonValue.NULL)
                .build().toString();

        login()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(kkj)
                .put(putUpdateShortNum)
                .then().log().all();

    }



}
