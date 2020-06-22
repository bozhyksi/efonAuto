package api.baseApiMethods;

import io.qameta.allure.Step;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;

import static api.data.endPoints.EndPoints.deleteAbbreviatedNumberDelete;
import static api.data.endPoints.EndPoints.postCreateAbbreviatedNumber;
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
        login()
                .delete(deleteAbbreviatedNumberDelete, abbreviatedDial.getId());
    }

}
