package api.tests;

import tests.IVRpageTests.IVRtestData.IVRtestData;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.queuesPageTest.queueTestData.Queue;
import tests.сonferenceCallsPageTests.ConferenceCallTestData.ConferenceCallTestData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static api.baseApiClasses.ConferenceCallsApi.createConferenceCallApi;
import static api.baseApiClasses.ConferenceCallsApi.deleteConferenceCallApi;
import static api.baseApiClasses.FileManagementApi.deleteAnnouncementApi;
import static api.baseApiClasses.FileManagementApi.uploadAnnouncementApi;
import static api.baseApiClasses.IVRApi.createIvrApi;
import static api.baseApiClasses.IVRApi.deleteIvrApi;
import static api.baseApiClasses.QueueApi.createQueueApi;
import static api.baseApiClasses.QueueApi.deleteQueueApi;
import static core.configuration.preparations.eFonApp.dataBaseWorker;

public class test2 {
    public static void main(String[] args) {

        ConferenceCallTestData conferenceCall = new ConferenceCallTestData();
        createConferenceCallApi(conferenceCall.getJson());
        deleteConferenceCallApi(conferenceCall.getId());

    }

}
