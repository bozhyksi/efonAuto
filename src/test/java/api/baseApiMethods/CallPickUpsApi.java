package api.baseApiMethods;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import tests.callPickUpPageTests.CallPickUpTestData.CallPickUp;

import static api.baseApiMethods.AbbreviatedNumbersApi.createAbbreviatedNumberApi;
import static api.baseApiMethods.AbbreviatedNumbersApi.deleteAbbreviatedNumberApi;
import static api.data.endPoints.EndPoints.deleteCreateCallPickUp;
import static api.data.endPoints.EndPoints.postCreateCallPickUp;
import static api.data.preparation.Preparation.login;

public class CallPickUpsApi {

    @Step("Create Call PickUp via API")
    public static void createCallPickupApi(CallPickUp callPickUp){

        createAbbreviatedNumberApi(callPickUp.getShortNumberObj().getSingleShortNum());

        login()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(callPickUp.getJson())
                .post(postCreateCallPickUp);

    }

    @Step("Delete Call PickUp via API")
    public static void deleteCallPickupApi(CallPickUp callPickUp){

        login()
                .delete(deleteCreateCallPickUp, callPickUp.getId());

        deleteAbbreviatedNumberApi(callPickUp.getShortNumberObj().getId());

    }
}
