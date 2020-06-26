package api.baseApiMethods;

import api.data.endPoints.EndPoints;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;

import javax.json.Json;
import java.util.ArrayList;

import static api.data.endPoints.EndPoints.getCustomerNumbers;
import static api.data.preparation.Preparation.login;

public class NumbersApi {

    @Step("Get customer's number list")
    public static ArrayList<String> getCustomerNumbersApi(){
        return login()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(EndPoints.getCustomerNumbers)
                .then()
                .extract()
                .path("result.number");
    }

}


