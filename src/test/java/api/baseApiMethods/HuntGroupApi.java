package api.baseApiMethods;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;

import static api.data.endPoints.EndPoints.deleteHuntGroup;
import static api.data.endPoints.EndPoints.postHuntGroupCreate;
import static api.data.preparation.Preparation.login;

public class HuntGroupApi {

    @Step("Create Hunt Group via API")
    public static void createHuntGroupApi(String jsonHuntGroup){
        login().
                given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonHuntGroup)
                .post(postHuntGroupCreate);
    }

    @Step("Create Hunt Group via API")
    public static void createHuntGroupApi(HuntGroup ... huntGroups){
        for (HuntGroup huntGroup: huntGroups) {
            login().
                    given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(huntGroup.getJson())
                    .post(postHuntGroupCreate);
        }
    }

    @Step("Delete Hunt Group via API")
    public static void deleteHuntGroupApi(String huntGroupsId){
        login()
                .delete(deleteHuntGroup,huntGroupsId);
    }

    @Step("Delete Hunt Group via API")
    public static void deleteHuntGroupApi(HuntGroup ... huntGroups){
        for (HuntGroup huntGroup: huntGroups) {
            if (!huntGroup.getId().equals("")) {
                login()
                        .delete(deleteHuntGroup, huntGroup.getId());
            }
        }
    }

}
