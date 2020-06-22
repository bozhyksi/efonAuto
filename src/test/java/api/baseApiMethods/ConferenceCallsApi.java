package api.baseApiMethods;

import io.restassured.http.ContentType;
import tests.сonferenceCallsPageTests.ConferenceCallTestData.ConferenceCallTestData;

import static api.data.endPoints.EndPoints.deleteConfCallDelete;
import static api.data.endPoints.EndPoints.postConfCallCreate;
import static api.data.preparation.Preparation.login;

public class ConferenceCallsApi {

    public static void createConferenceCallApi(String json){
        login()
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json)
                .post(postConfCallCreate);
    }

    public static void createConferenceCallApi(ConferenceCallTestData ... conferenceCalls){
        for (ConferenceCallTestData conferenceCall: conferenceCalls) {
            login()
                    .given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(conferenceCall.getJson())
                    .post(postConfCallCreate);
        }
    }

    public static void deleteConferenceCallApi(String id){
        login()
                .delete(deleteConfCallDelete, id);
    }

    public static void deleteConferenceCallApi(ConferenceCallTestData ... conferenceCalls){
        for (ConferenceCallTestData conferenceCall: conferenceCalls) {
            login()
                    .delete(deleteConfCallDelete, conferenceCall.getId());
        }
    }

}
