package api.baseLowLevelUserApi;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import org.testng.Assert;
import testsLowLevelUser.sendSmsUserPageTests.sendSmsTestData.AddressBookTestData;
import testsLowLevelUser.sendSmsUserPageTests.sendSmsTestData.AuthorizedNumberTestData;

import static api.data.endPoints.EndPoints.*;
import static api.data.preparation.Preparation.loginAsLowLevelUser;

public class SendSmsApi {

    @Step("Create AddressBook entry via API")
    public static void createAddressBookEntryApi(AddressBookTestData ... addressBookEntries){
        for (AddressBookTestData addressBook:addressBookEntries) {
            boolean success = loginAsLowLevelUser()
                    .given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(addressBook.getJson())
                    .post(postCreateAddressBookEntry)
                    .then()
                    .extract()
                    .path("success");
            Assert.assertTrue(success,"\n\n AddressBook creation via API failed! Success - false \n\n");
        }
    }

    @Step("Delete AddressBook entry via API")
    public static void deleteAddressBookEntryApi(AddressBookTestData ... addressBookEntries){
        for (AddressBookTestData addressBook : addressBookEntries) {
            if(!addressBook.getID().equals("")){
                boolean success = loginAsLowLevelUser()
                        .given()
                        .delete(deleteAddressBookEntryDelete,addressBook.getID())
                        .then()
                        .extract()
                        .path("success");
                Assert.assertTrue(success,"\n\n AddressBook deletion via API failed! Success - false \n\n");
            }
        }
    }

    @Step("Add authorized number via API")
    public static void createAuthorizedNumberApi(AuthorizedNumberTestData authorizedNumber){
        boolean success = loginAsLowLevelUser()
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(authorizedNumber.getJsonCreateNewAuthNum())
                .put(putAddAuthorizedNumber)
                .then().extract().path("success");
        Assert.assertTrue(success,"\n\n AuthorizedNumber creation via API failed! Success - false \n\n");
    }

    @Step("Delete authorized number via API")
    public static void deleteAuthorizedNumberApi(AuthorizedNumberTestData authorizedNumber){
        if (!authorizedNumber.getID().equals("")){
            boolean success = loginAsLowLevelUser()
                    .given()
                    .delete(deleteAddAuthorizedNumberDelete,authorizedNumber.getID())
                    .then().extract().path("success");
            Assert.assertTrue(success,"\n\n AuthorizedNumber deletion via API failed! Success - false \n\n");
        }
    }

    @Step("Enter authorization code via API")
    public static void enterAuthorizationCodeApi(AuthorizedNumberTestData authorizedNumber){
        boolean success = loginAsLowLevelUser()
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(authorizedNumber.getJsonEnterAuthCode())
                .post(postEnterAuthorizationCode)
                .then().extract().path("success");
        Assert.assertTrue(success,"\n\n AuthCode enter via API failed! Success - false \n\n");
    }

}
