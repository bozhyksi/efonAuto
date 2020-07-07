package testsLowLevelUser.sendSmsUserPageTests.sendSmsTestData;

import com.fasterxml.jackson.core.JsonFactoryBuilder;
import flow.BaseTestMethods;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonValue;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressBookTestData extends BaseTestMethods {

    //<editor-fold desc="properties">
    private String mobileNumber;
    private String firstName;
    private String lastName;
    private String company;
    //</editor-fold>

    public AddressBookTestData(){
        this.mobileNumber = getRandomPhone("074",7);
        this.firstName = "FistName_"+getRandomString(10);
        this.lastName = "LastName_"+getRandomString(10);
        this.company = "Company_"+getRandomString(10);
    }

    //<editor-fold desc="get/set">
    public String getCompany() {
        return company;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
    //</editor-fold>

    public String getJson(){
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        return factory.createObjectBuilder()
                .add("firstName", getFirstName())
                .add("lastName", getLastName())
                .add("company", getCompany())
                .add("mobileNumber", getMobileNumber())
                .add("smsAddressId", JsonValue.NULL)
                .build()
                .toString();
    }

    public String getID(){
        String query = "SELECT sms_address_id FROM webadmin_20170426.sms_address where number = %s";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(String.format(query,Integer.parseInt(getMobileNumber())));
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
