package api.baseApiMethods;

import io.qameta.allure.Step;

import static api.data.endPoints.EndPoints.deleteAbbreviatedNumberDelete;
import static api.data.endPoints.EndPoints.postCreateAbbreviatedNumber;
import static api.data.preparation.Preparation.login;

public class AbbreviatedNumbersApi {

    @Step("Create single short number via API")
    public static void createAbbreviatedNumberApi(String number){
        login()
                .post(postCreateAbbreviatedNumber, number);
    }

    @Step("Delete single short number via API")
    public static void deleteAbbreviatedNumberApi(String id){
        login()
                .delete(deleteAbbreviatedNumberDelete, id);
    }

}
