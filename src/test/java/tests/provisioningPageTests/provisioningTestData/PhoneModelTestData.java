package tests.provisioningPageTests.provisioningTestData;

import flow.BaseTestMethods;
import flow.PublicEnums;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhoneModelTestData extends BaseTestMethods {

    //<editor-fold desc="properties">
    private String phoneModel;
    private String language;
    private String webLanguage;
    private String webUser;
    private String webPassword;
    private String function;
    private String destinNumber;
    private String displName;
    //</editor-fold>

    public PhoneModelTestData() {

        this.phoneModel = getRandomPhoneModelFromDB();
        this.language = PublicEnums.LanguageValues.getRandLangVal();
        this.webLanguage = PublicEnums.LanguageValues.getRandLangVal();;
        this.webUser = getRandomString(15);
        this.webPassword = getRandomPassword();
        this.function = PublicEnums.PhoneModelFunctions.getRandFunc();
        this.destinNumber = getRandomNumber(10);
        this.displName = getRandomString(15);

    }

    public PhoneModelTestData(String phoneModel) {

        this.phoneModel = phoneModel;
        this.language = PublicEnums.LanguageValues.getRandLangVal();
        this.webLanguage = PublicEnums.LanguageValues.getRandLangVal();;
        this.webUser = getRandomString(15);
        this.webPassword = getRandomPassword();
        this.function = PublicEnums.PhoneModelFunctions.getRandFunc();
        this.destinNumber = getRandomNumber(10);
        this.displName = getRandomString(15);

    }

    //<editor-fold desc="get/set">
    public String getWebUser() {
        return webUser;
    }

    public String getWebPassword() {
        return webPassword;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public String getDestinNumber() {
        return destinNumber;
    }

    public String getDisplName() {
        return displName;
    }

    public String getFunction() {
        return function;
    }

    public String getLanguage() {
        return language;
    }

    public String getWebLanguage() {
        return webLanguage;
    }
    //</editor-fold>

    public String getId(){
        String query ="SELECT phone_model_id\n" +
                "FROM webadmin_20170426.phone_model\n" +
                "where phone_model= \"%s\"";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(String.format(query,getPhoneModel()));
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
