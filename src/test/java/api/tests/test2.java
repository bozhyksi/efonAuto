package api.tests;

import api.data.endPoints.EndPoints;
import io.restassured.http.ContentType;

import java.util.ArrayList;

import static api.data.preparation.Preparation.login;

public class test2 {
    public static void main(String[] args) {
        ArrayList<String> nums = login()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(EndPoints.getCustomerNumbers)
                .then()
                .extract()
                .path("result.number");
    }


}
