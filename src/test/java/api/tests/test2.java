package api.tests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static api.data.endPoints.EndPoints.*;
import static api.data.preparation.Preparation.login;

public class test2 {

    @Test
    public void asd(){
        String user = "{\n" +
                "\"userNaming\":\n" +
                "\t{\n" +
                "\t\"salutation\":\n" +
                "\t\t{\n" +
                "\t\t\"id\":10,\n" +
                "\t\t\"messageKey\":\"salutation.male\"\n" +
                "\t\t},\n" +
                "\t\t\"firstName\":\"qwe122\",\n" +
                "\t\t\"lastName\":\"qwe122\",\n" +
                "\t\t\"loginEmail\":\"qwer2233@asd.aa\",\n" +
                "\t\t\"useDifferentContactEmail\":null\n" +
                "\t},\n" +
                "\"userAllocation\":\n" +
                "\t{\n" +
                "\t\t\"useVoicemailEmail\":null,\n" +
                "\t\t\"number\":\"00451245789900\",\n" +
                "\t\t\"accounts\":[],\n" +
                "\t\t\"busyOnBusy\":false,\n" +
                "\t\t\"internalNumbers\":[],\n" +
                "\t\t\"currentBlockSet\":7300,\n" +
                "\t\t\"smsEnabled\":false,\n" +
                "\t\t\"faxEnabled\":false,\n" +
                "\t\t\"faxNumber\":null,\n" +
                "\t\t\"localHeaderInfo\":null,\n" +
                "\t\t\"callRecording\":\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"activateCallRecording\":false,\n" +
                "\t\t\t\t\"callRecordingDirection\":\"ALL\"\n" +
                "\t\t\t}\n" +
                "\t}\n" +
                "}";

        login()
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(user)
                .post(postCreateUser)
                .then()
                .log()
                .status();

    }

    @Test
    public void deleteUser(){
        login()
                .delete(deleteDeleteUser,"907467")
                .then()
                .log()
                .status();

    }




}
