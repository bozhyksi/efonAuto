package tests.callPickUpPageTests.CallPickUpTestData;

import flow.BaseTestMethods;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonValue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CallPickUp extends BaseTestMethods {

    //<editor-fold desc="properties">
    private String name;
    private String abbreviatedDialling;
    private String accountForCallPickup;
    private AbbreviatedDialling shortNumberObj;
    //</editor-fold>

    public CallPickUp(AbbreviatedDialling shortNumber){
        this.shortNumberObj = shortNumber;
        this.name = getRandomString(15);
        this.abbreviatedDialling = shortNumber.getSingleShortNum();
        this.accountForCallPickup = getRandomEndDeviceForEditFromDB();
    }

    //<editor-fold desc="get\set">

    public String getName() {
        return name;
    }

    public String getAbbreviatedDialling() {
        return abbreviatedDialling;
    }

    public String getAccountForCallPickup() {
        return accountForCallPickup;
    }

    public AbbreviatedDialling getShortNumberObj() {
        return shortNumberObj;
    }

    //</editor-fold>

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

    public String getJson(){
        Map <String,String> accountData = getAccountData();
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        return factory.createObjectBuilder()
                .add("name",getName())
                .add("internalNumberId", shortNumberObj.getId())
                .add("selectedAccounts", factory.createArrayBuilder()
                        .add(factory.createObjectBuilder()
                                .add("accountId", Integer.parseInt(accountData.get("accountId")))
                                .add("customerDisplayName",accountData.get("customerDisplayName"))
                                .add("accountName", accountData.get("accountName"))
                                .add("accountDisplayName", accountData.get("accountDisplayName"))
                                .add("callInterceptName", JsonValue.NULL)
                                .add("callInterceptId", JsonValue.NULL))
                        )
                .build().toString();
    }

    private Map<String,String> getAccountData(){
        Map<String,String> accountData = new HashMap<>();
        String query = "SELECT account_id, c.display_name, a.name, a.display_name\n" +
                "FROM webadmin_20170426.account a\n" +
                "join customer c on a.customer_fk = c.customer_id\n" +
                "where a.name = \"%s\"";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(String.format(query,getAccountForCallPickup()));

        while (true){
            try {
                if (!resultSet.next()) break;
                accountData.put("accountId",resultSet.getString(1));
                accountData.put("customerDisplayName",resultSet.getString(2));
                accountData.put("accountName",resultSet.getString(3));
                accountData.put("accountDisplayName",resultSet.getString(4));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return accountData;
    }

    public String getId(){
        String query = "SELECT call_intercept_group_id FROM webadmin_20170426.call_intercept_group where call_intercept_name = \"%s\"";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(String.format(query,getName()));
        while (true){
            try {
                if (!resultSet.next()) break;
                return resultSet.getString(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
