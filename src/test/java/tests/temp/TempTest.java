package tests.temp;

import core.workers.DataBaseWorker;
import flow.BaseTestMethods;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TempTest extends BaseTestMethods {
    @Test
    public void Test1(){
        DataBaseWorker dataBaseWorker = new DataBaseWorker();
        String query = "SELECT abo_id \n" +
                       "FROM webadmin_20170426.abo \n" +
                       "where customer_fk = 906062;";

        ResultSet res = dataBaseWorker.execSqlQuery(query);

        try{
            while (res.next()){
                int i = res.getInt("abo_id");
                System.out.println(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
