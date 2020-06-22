package api.tests;

import api.baseApiMethods.UserApi;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;
import tests.callPickUpPageTests.CallPickUpTestData.CallPickUp;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.queuesPageTest.queueTestData.Queue;
import tests.userPageTests.userPageTestData.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static api.baseApiMethods.CallPickUpsApi.createCallPickupApi;
import static api.baseApiMethods.CallPickUpsApi.deleteCallPickupApi;
import static api.baseApiMethods.FileManagementApi.deleteMohApi;
import static api.baseApiMethods.FileManagementApi.uploadMohApi;
import static api.baseApiMethods.IVRApi.createIvrApi;
import static api.baseApiMethods.IVRApi.deleteIvrApi;
import static api.baseApiMethods.QueueApi.*;
import static api.baseApiMethods.UserApi.createUsersApi;
import static core.configuration.preparations.eFonApp.dataBaseWorker;
import static tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling.Type.SINGLE;

public class test2 {
    public static void main(String[] args) {

        Queue queue = new Queue(new User());
        createUsersApi(queue.getAgent());
        createQueueApi(queue);
        addQueueAgentApi(queue);

    }


}
