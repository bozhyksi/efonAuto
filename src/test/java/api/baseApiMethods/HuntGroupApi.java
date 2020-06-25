package api.baseApiMethods;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonValue;

import static api.data.endPoints.EndPoints.*;
import static api.data.preparation.Preparation.login;

public class HuntGroupApi {

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
    public static void deleteHuntGroupApi(HuntGroup ... huntGroups){
        for (HuntGroup huntGroup: huntGroups) {
            if (!huntGroup.getId().equals("")) {
                login()
                        .delete(deleteHuntGroup, huntGroup.getId());
            }
        }
    }

    @Step("Create hunt group with authorized user")
    public static void createHuntGroupWithAuthorizedUserApi(HuntGroup huntGroup, String userId, String userDisplayName){
        //<editor-fold desc="json">
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        String json =  factory.createObjectBuilder()
                .add("huntGroupNumber", factory.createObjectBuilder()
                        .add("phoneNumberId", huntGroup.getPhoneNumberId())
                        .add("number", huntGroup.getHuntGroupNumber())
                )
                .add("huntGroupName",huntGroup.getHuntGroupName())
                .add("huntGroupDisplayName",huntGroup.getHuntGroupDisplayName())
                .add("huntGroupLanguage",huntGroup.getHuntGroupLanguage())
                .add("grantedUsers",factory.createArrayBuilder()
                        .add(factory.createObjectBuilder()
                                .add("id", Integer.parseInt(userId))
                                .add("displayName", userDisplayName)
                        )
                )
                .add("voicemailSettings",factory.createObjectBuilder()
                        .add("email","")
                        .add("pin","")
                        .add("salutation", "")
                        .add("onlySendByEmail", false)
                )
                .add("huntGroupBackUpRouting",factory.createObjectBuilder()
                        .add("backUpAccount", JsonValue.NULL)
                        .add("backUpType", 0)
                        .add("backUpNumber", JsonValue.NULL)
                )
                .add("huntGroupTimerQueues", factory.createArrayBuilder()
                        .add(factory.createObjectBuilder()
                                .add("huntGroupQueueId", JsonValue.NULL)
                                .add("timerQueueName","Default")
                                .add("position", 999)
                                .add("timerQueueType", "standard")
                                .add("huntGroupQueueActions", factory.createArrayBuilder())
                                .add("huntGroupQueueActionsUpdate", false)
                                .add("viewIndex",0))
                )
                .add("hasCallsRecording", factory.createObjectBuilder()
                        .add("activateCallRecording", true)
                ).build().toString();
        //</editor-fold>

        login().
                given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json)
                .post(postHuntGroupCreate);
    }
}
