package api.tests;

import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.queuesPageTest.queueTestData.Queue;

import static api.baseApiMethods.HuntGroupApi.createHuntGroupWithAuthorizedUserApi;
import static api.baseApiMethods.IVRApi.createIvrApi;
import static api.baseApiMethods.IVRApi.deleteIvrApi;
import static api.baseApiMethods.QueueApi.createQueueWithMangerReporterApi;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.*;
import static testsLowLevelUser.testData.AutotestUserData.autotestUserContactID;

public class test2 {
    public static void main(String[] args) {
        Queue queue = new Queue();
        createQueueWithMangerReporterApi(queue,autotestUserContactID,autotestUserContactID);
    }
}
