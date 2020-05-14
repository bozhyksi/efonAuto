package tests.callPickUpPageTests.CallPickUpTestData;

import flow.BaseTestMethods;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class CallPickUp extends BaseTestMethods {

    private String name;
    private String abbreviatedDialling;
    private String accountForCallPickup;

    public CallPickUp(AbbreviatedDialling shortNumber){
        this.name = getRandomString(15);
        this.abbreviatedDialling = shortNumber.getSingleShortNum();
        this.accountForCallPickup = getRandomEndDeviceForEditFromDB();
    }

    private String getRandomEndDeviceForEditFromDB(){
        String query = "SELECT name FROM webadmin_20170426.account where customer_fk=906144";
        ArrayList<String> endDeviceList = new ArrayList<>();
        ResultSet res = dataBaseWorker.execSqlQuery(query);
        while (true){
            try {
                if (!res.next()) break;
                endDeviceList.add(res.getString(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return endDeviceList.get(new Random().nextInt(endDeviceList.size()));
    }

    public String getName() {
        return name;
    }

    public String getAbbreviatedDialling() {
        return abbreviatedDialling;
    }

    public String getAccountForCallPickup() {
        return accountForCallPickup;
    }
}
