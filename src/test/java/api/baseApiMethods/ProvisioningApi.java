package api.baseApiMethods;

import io.restassured.http.ContentType;
import tests.provisioningPageTests.provisioningTestData.PhoneModelTestData;

import javax.json.*;

import static api.data.endPoints.EndPoints.putPhoneModelSettingUpdate;
import static api.data.preparation.Preparation.login;

public class ProvisioningApi {

    public static void deactivatePhoneModelFunctionsApi(PhoneModelTestData phoneModel){
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        JsonArray funcArray = null;
        String json = null;

        switch (phoneModel.getPhoneModel()){
            case "panasonic KX-UT670NE":
                for (int i=1; i<25; i++){
                    funcArray = arrayBuilder
                            .add(factory.createObjectBuilder()
                                    .add("keyNumber", i)
                                    .add("isFixed",false)
                                    .add("type", JsonValue.NULL)
                                    .add("destinationNumber", JsonValue.NULL)
                                    .add("displayName", JsonValue.NULL)
                            ).build();
                }
                json = factory.createObjectBuilder()
                        .add("isWebAuthCustom",false)
                        .add("functionKeys",funcArray)
                        .build().toString();
                break;
            case "snom D765":
                for (int i=0; i<16; i++){
                    funcArray = arrayBuilder
                            .add(factory.createObjectBuilder()
                                    .add("keyNumber", i)
                                    .add("isFixed",false)
                                    .add("type", JsonValue.NULL)
                                    .add("destinationNumber", JsonValue.NULL)
                                    .add("displayName", JsonValue.NULL)
                            ).build();
                }
                json = factory.createObjectBuilder()
                        .add("isWebAuthCustom",false)
                        .add("languageCode","")
                        .add("webLanguageCode","")
                        .add("functionKeys",funcArray)
                        .build().toString();
                break;
            case "Gigaset DE900 IP PRO":
                for (int i=0; i<15; i++){
                    funcArray = arrayBuilder
                            .add(factory.createObjectBuilder()
                                    .add("keyNumber", i)
                                    .add("isFixed",false)
                                    .add("type", JsonValue.NULL)
                                    .add("destinationNumber", JsonValue.NULL)
                                    .add("displayName", JsonValue.NULL)
                            ).build();
                }
                json = factory.createObjectBuilder()
                        .add("isWebAuthCustom",false)
                        .add("languageCode","")
                        .add("webLanguageCode","")
                        .add("functionKeys",funcArray)
                        .build().toString();
                break;
            case "Alcatel Temporis IP301G":
                for (int i=1; i<25; i++){
                    funcArray = arrayBuilder
                            .add(factory.createObjectBuilder()
                                    .add("keyNumber", i)
                                    .add("isFixed",false)
                                    .add("type", JsonValue.NULL)
                                    .add("destinationNumber", JsonValue.NULL)
                                    .add("displayName", JsonValue.NULL)
                            ).build();
                }
                json = factory.createObjectBuilder()
                        .add("isWebAuthCustom",false)
                        .add("languageCode","")
                        .add("webLanguageCode","")
                        .add("functionKeys",funcArray)
                        .build().toString();
                break;
            case "aastra 6869i":
                for (int i=1; i<45; i++){
                    funcArray = arrayBuilder
                            .add(factory.createObjectBuilder()
                                    .add("keyNumber", i)
                                    .add("isFixed",false)
                                    .add("type", JsonValue.NULL)
                                    .add("destinationNumber", JsonValue.NULL)
                                    .add("displayName", JsonValue.NULL)
                            ).build();
                }
                json = factory.createObjectBuilder()
                        .add("isWebAuthCustom",false)
                        .add("languageCode","")
                        .add("webLanguageCode","")
                        .add("functionKeys",funcArray)
                        .build().toString();
                break;
        }

        login()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json)
                .put(putPhoneModelSettingUpdate, phoneModel.getId());
    }
}
