package tests.temp;

import flow.BaseTestMethods;
import org.testng.annotations.Test;
import tests.queuesPageTest.queueTestData.Queue;

import java.util.concurrent.atomic.AtomicInteger;

public class TempTest extends BaseTestMethods {
    @Test
    public void sss(){
        login();
        Queue queue = new Queue();
        createQueue(queue);
    }

}
