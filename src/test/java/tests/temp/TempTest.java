package tests.temp;

import flow.BaseTestMethods;
import org.testng.annotations.Test;
import tests.queuesPageTest.queueTestData.Queue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class TempTest extends BaseTestMethods {
    //@Test
    public static void main(String[] args) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:");
        Date currentDate = new Date();
        String date = formatter.format(currentDate);
        System.out.println(date);

    }

}
