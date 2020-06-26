package testsLowLevelUser.testData;

import flow.BaseTestMethods;

import java.sql.ResultSet;
import java.sql.SQLException;

import static core.configuration.preparations.eFonApp.dataBaseWorker;

public abstract class AutotestUserData{

    public final static String autotestUserPhone = "00451245789908";
    public final static String autotestUserFullName = "AutoTestUser AutoTestUser";
    public final static String autotestUserFirstName = "AutoTestUser";
    public final static String autotestUserLastName = "AutoTestUser";
    public final static String autotestUserEndDevname = getAutoTestUserEndDeviceNameFromDB();
    public final static String autotestUserId = "906645";
    public final static String autotestUserDisplayName = "AutoTestUser AutoTestUser";
    public final static String autotestUserContactID = "1110126";
    public final static String autotestUserAccountID = "792888";


    private static String getAutoTestUserEndDeviceNameFromDB(){
        String query = "SELECT name FROM webadmin_20170426.account where account_id=792888";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(query);
        String endDevName ="";
        while (true) try {
            if (!resultSet.next()) break;
            endDevName = resultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return endDevName;
    }
}
