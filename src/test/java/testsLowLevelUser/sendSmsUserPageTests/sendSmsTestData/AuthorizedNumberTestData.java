package testsLowLevelUser.sendSmsUserPageTests.sendSmsTestData;

import core.workers.dbWorker.DataBaseWorker;
import flow.BaseTestMethods;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonValue;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorizedNumberTestData extends BaseTestMethods {

    //<editor-fold desc="properties">
    private String number;
    private String authorizationCode;
    //</editor-fold>

    public AuthorizedNumberTestData (){
        this.number = getRandomPhone("074",7);
    }

    public String getNumber() {
        return number;
    }

    public String getAuthorizationCode() {
        String code = null;
        String query = String.format("SELECT authorization_code " +
                "FROM webadmin_20170426.sms_authorized_number " +
                "where customer_fk=906645 and sender_number= %s ", this.number);

        DataBaseWorker dataBaseWorker = new DataBaseWorker();

        ResultSet rs = dataBaseWorker.execSqlQuery(query);
        while (true){
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                code = rs.getString("authorization_code");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return code;
    }

    public String getJsonCreateNewAuthNum(){
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        return factory.createObjectBuilder()
                .add("mobileNumber", getNumber())
                .add("id", JsonValue.NULL)
                .build()
                .toString();
    }

    public String getJsonEnterAuthCode(){
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        return factory.createObjectBuilder()
                .add("code", getAuthorizationCode())
                .add("id",getID())
                .build()
                .toString();
    }

    public String getID(){
        String query = String.format("SELECT sms_authorized_number_id" +
                                    " FROM webadmin_20170426.sms_authorized_number " +
                                    "where sender_number=%s",getNumber());
        return getEntityIdFromDB(query);
    }

}
