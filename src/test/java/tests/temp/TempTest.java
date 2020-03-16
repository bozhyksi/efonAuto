package tests.temp;

import flow.BaseTestMethods;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TempTest extends BaseTestMethods {
    public static void main(String[] args) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date data = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.DATE, 10);

        data = calendar.getTime();

        String date = formatter.format(data);
        System.out.println(date);
    }
}
