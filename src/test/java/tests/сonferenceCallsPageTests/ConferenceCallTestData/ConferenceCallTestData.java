package tests.сonferenceCallsPageTests.ConferenceCallTestData;

import flow.BaseTestMethods;
import flow.PublicEnums;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConferenceCallTestData extends BaseTestMethods {

    private String name;
    private String conferenceNumber;
    private String pin;
    private String language;

    public ConferenceCallTestData(){
        this.name = getRandomString(10);
        this.pin = getRandomNumber(4);
        this.language = PublicEnums.LanguageValues.getRandLangVal();
        this.conferenceNumber = getRandomCustomerFreePhoneNumberFromDB();
    }

    public ConferenceCallTestData(String conferenceNumber){
        this.name = getRandomString(10);
        this.pin = getRandomNumber(4);
        this.language = PublicEnums.LanguageValues.getRandLangVal();
        this.conferenceNumber = conferenceNumber;
    }

    //<editor-fold desc="get\set">
    public String getName() {
        return name;
    }

    public String getConferenceNumber() {
        return conferenceNumber;
    }

    public String getPin() {
        return pin;
    }

    public String getLanguage() {
        return language;
    }

    public void setConferenceNumber(String conferenceNumber) {
        this.conferenceNumber = conferenceNumber.replaceAll("\\s", "");
    }
    //</editor-fold>

    public String changeName(){
        return this.name = getRandomString(15);
    }

    public String changePIN(){
        return this.pin = getRandomNumber(4);
    }

    public String changeNumber(){
        return this.conferenceNumber = getRandomCustomerFreePhoneNumberFromDB();
    }

    public String changeLanguage(){
        return this.language = PublicEnums.LanguageValues.getRandLangVal();
    }

    public String getJson(){
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        return factory.createObjectBuilder()
                .add("name", getName())
                .add("numberId", getPhoneNumberId(getConferenceNumber()))
                .add("pin", getPin())
                .add("language", getLanguage())
                .build().toString();
    }

    public String getId(){
        String query = "SELECT * FROM webadmin_20170426.conference_call where display_name = \"%s\"; ";
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
