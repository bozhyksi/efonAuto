package api.tests;

import tests.queuesPageTest.queueTestData.Queue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static api.baseApiClasses.QueueApi.createQueueApi;
import static api.baseApiClasses.QueueApi.deleteQueueApi;
import static core.configuration.preparations.eFonApp.dataBaseWorker;

public class test2 {
    public static void main(String[] args) {

        Queue queue = new Queue();
        createQueueApi(queue.getJson());
        deleteQueueApi(queue.getId());
    }

}
