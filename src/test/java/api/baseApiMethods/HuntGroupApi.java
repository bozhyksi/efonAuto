package api.baseApiMethods;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import org.testng.Assert;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import static api.data.endPoints.EndPoints.*;
import static api.data.preparation.Preparation.login;

public class HuntGroupApi {

    @Step("Create Hunt Group via API")
    public static void createHuntGroupApi(HuntGroup ... huntGroups){
        for (HuntGroup huntGroup: huntGroups) {
            boolean success = login().
                    given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(huntGroup.getJson())
                    .post(postHuntGroupCreate)
                    .then()
                    .extract()
                    .path("success");
            Assert.assertTrue(success,"\n\n Hunt Group creation via API failed! Success - false \n\n");
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
                        .add("isPublic",JsonValue.TRUE)
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

        boolean success =login().
                given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json)
                .post(postHuntGroupCreate)
                .then()
                .extract()
                .path("success");
        Assert.assertTrue(success,"\n\n Hunt Group creation via API failed! Success - false \n\n");
    }

    @Step("Create hunt group with authorized user")
    public static void createHuntGroupWithAuthorizedUserApi(HuntGroup huntGroup){
        //<editor-fold desc="json">
        JsonObjectBuilder objectNumberJson = Json.createObjectBuilder();
        if (huntGroup.getShotNum() != null){
            objectNumberJson
                    .add("phoneNumberId", huntGroup.getShotNum().getId())
                    .add("number", huntGroup.getShotNum().getSingleShortNum())
                    .add("isPublic",JsonValue.FALSE);
        }else{
            objectNumberJson
                    .add("phoneNumberId", huntGroup.getPhoneNumberId())
                    .add("number", huntGroup.getHuntGroupNumber())
                    .add("isPublic",JsonValue.TRUE);
        }

        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        String json =  factory.createObjectBuilder()
                .add("huntGroupNumber", factory.createObjectBuilder()
                        .addAll(objectNumberJson)
                )
                .add("huntGroupName",huntGroup.getHuntGroupName())
                .add("huntGroupDisplayName",huntGroup.getHuntGroupDisplayName())
                .add("huntGroupLanguage",huntGroup.getHuntGroupLanguage())
                .add("grantedUsers",factory.createArrayBuilder()
                        .add(factory.createObjectBuilder()
                                .add("id", Integer.parseInt(huntGroup.getAuthorisedUser().getId()))
                                .add("displayName", huntGroup.getAuthorisedUser().getDisplayName())
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

        boolean success =login().
                given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json)
                .post(postHuntGroupCreate)
                .then()
                .extract()
                .path("success");
        Assert.assertTrue(success,"\n\n Hunt Group creation via API failed! Success - false \n\n");
    }
}
