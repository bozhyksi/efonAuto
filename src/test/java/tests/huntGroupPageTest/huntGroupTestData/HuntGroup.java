package tests.huntGroupPageTest.huntGroupTestData;

import flow.BaseTestMethods;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.queuesPageTest.queueTestData.Queue;
import tests.userPageTests.userPageTestData.User;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonValue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HuntGroup extends BaseTestMethods {

    //<editor-fold desc="properties">

    private FileManagementTestData announcement;
    private Queue queue;
    private User authorisedUser;

    //Edit hunt group section
    private String huntGroupNumber;
    private String huntGroupLanguage;
    private String huntGroupAuthorizedUser;
    private String huntGroupName;
    private String huntGroupDisplayName;
    private ArrayList<String> huntGroupAuthorizedUsers;

    //Voicemail settings
    private String pinCode;
    private String voicemailEmail;
    private String salutation;

    //If end devices not available (not registered) section
    private String relevantAccount;
    private String backUpNumber;

    //Full days
    private String fullDayName;
    private String fullDayDate;
    private String fullDayPhoneNumber;

    //Further time
    private String furtherTimeName = getRandomString(15);
    private String furtherTimeMonday = "08:00-11:15;13:30-15:00;";
    private String furtherTimeTuesday = "08:00-13:00;14:00-18:00;";
    private String furtherTimeWednesday = "08:00-13:00;14:00-18:00;";
    private String furtherTimeThursday = "08:00-11:15;13:30-15:00;";
    private String furtherTimeFriday = "08:00-13:00;14:00-18:00;";
    private String furtherTimeSaturday = "08:00-13:00;14:00-18:00;";
    private String furtherTimeSunday = "08:00-13:00;14:00-18:00;";
    //</editor-fold>

    public HuntGroup(){
        //Edit hunt group section
        this.huntGroupLanguage = "en";
        this.huntGroupName = getRandomString(10);
        this.huntGroupDisplayName = "Display_"+this.huntGroupName;
        this.huntGroupNumber = getRandomCustomerFreePhoneNumberFromDB();

        //Voicemail settings
        this.pinCode = getRandomNumber(1111,9999);
        this.voicemailEmail = getRandomEmail();
        this.salutation = "Dear Ms";

        //If end devices not available (not registered) section
        this.backUpNumber = getRandomPhone();

        //Full days
        this.fullDayName = getRandomString(10);
        this.fullDayDate = "1.8; 24.12; 31.09; 08.12";
        fullDayPhoneNumber = getRandomPhone();
    }

    public HuntGroup(User authorisedUser){
        this.authorisedUser= authorisedUser;

        //Edit hunt group section
        this.huntGroupLanguage = "en";
        this.huntGroupName = getRandomString(10);
        this.huntGroupDisplayName = "Display_"+this.huntGroupName;
        this.huntGroupNumber = getRandomCustomerFreePhoneNumberFromDB();

        //Voicemail settings
        this.pinCode = getRandomNumber(1111,9999);
        this.voicemailEmail = getRandomEmail();
        this.salutation = "Dear Ms";

        //If end devices not available (not registered) section
        this.backUpNumber = getRandomPhone();

        //Full days
        this.fullDayName = getRandomString(10);
        this.fullDayDate = "1.8; 24.12; 31.09; 08.12";
        fullDayPhoneNumber = getRandomPhone();
    }

    public HuntGroup(String huntGroupName, String huntGroupNumber){
        //Edit hunt group section
        this.huntGroupLanguage = "en";
        this.huntGroupName = huntGroupName;
        this.huntGroupDisplayName = huntGroupName;
        this.huntGroupNumber = huntGroupNumber;

        //Voicemail settings
        this.pinCode = getRandomNumber(1111,9999);
        this.voicemailEmail = getRandomEmail();
        this.salutation = "Dear Ms";

        //If end devices not available (not registered) section
        this.backUpNumber = getRandomPhone();

        //Full days
        this.fullDayName = getRandomString(10);
        this.fullDayDate = "1.8; 24.12; 31.09; 08.12";
        fullDayPhoneNumber = getRandomPhone();
    }

    public HuntGroup(FileManagementTestData announcement, Queue queue){
        this.queue = queue;
        this.announcement = announcement;

        //Edit hunt group section
        this.huntGroupLanguage = "en";
        this.huntGroupName = getRandomString(10);
        this.huntGroupDisplayName = "Display_"+this.huntGroupName;
        this.huntGroupNumber = getRandomCustomerFreePhoneNumberFromDB();

        //Voicemail settings
        this.pinCode = getRandomNumber(1111,9999);
        this.voicemailEmail = getRandomEmail();
        this.salutation = "Dear Ms";

        //If end devices not available (not registered) section
        this.backUpNumber = getRandomPhone();

        //Full days
        this.fullDayName = getRandomString(10);
        this.fullDayDate = "1.8; 24.12; 31.09; 08.12";
        fullDayPhoneNumber = getRandomPhone();
    }

    //<editor-fold desc="get\set">


    public User getAuthorisedUser() {
        return authorisedUser;
    }

    public FileManagementTestData getAnnouncement() {
        return announcement;
    }

    public Queue getQueue() {
        return queue;
    }

    public ArrayList<String> getHuntGroupAuthorizedUsers() {
        return huntGroupAuthorizedUsers;
    }

    public String getFurtherTimeSunday() {
        return furtherTimeSunday;
    }

    public String getFurtherTimeSaturday() {
        return furtherTimeSaturday;
    }

    public String getFurtherTimeFriday() {
        return furtherTimeFriday;
    }

    public String getFurtherTimeThursday() {
        return furtherTimeThursday;
    }

    public String getFurtherTimeWednesday() {
        return furtherTimeWednesday;
    }

    public String getFurtherTimeTuesday() {
        return furtherTimeTuesday;
    }

    public String getFurtherTimeMonday() {
        return furtherTimeMonday;
    }

    public String getFurtherTimeName() {
        return furtherTimeName;
    }

    public String getFullDayPhoneNumber() {
        return fullDayPhoneNumber;
    }

    public String getFullDayDate() {
        return fullDayDate;
    }

    public String getFullDayName() {
        return fullDayName;
    }

    public String getHuntGroupDisplayName() {
        return huntGroupDisplayName;
    }

    public String getHuntGroupLanguage() {
        return huntGroupLanguage;
    }

    public String getHuntGroupName() {
        return huntGroupName;
    }

    public String getHuntGroupNumber() {
        return huntGroupNumber;
    }

    public String getHuntGroupAuthorizedUser() {
        return huntGroupAuthorizedUser;
    }

    public void setHuntGroupNumber(String huntGroupNumber) {
        this.huntGroupNumber = huntGroupNumber.replaceAll("\\s","");
    }

    public String getSalutation() {
        return salutation;
    }

    public String getVoicemailEmail() {
        return voicemailEmail;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getBackUpNumber() {
        return backUpNumber;
    }

    public void setRelevantAccount(String relevantAccount) {
        this.relevantAccount = relevantAccount;
    }

    public String getRelevantAccount() {
        return relevantAccount;
    }

    public void setHuntGroupAuthorizedUser(String huntGroupAuthorizedUser) {
        this.huntGroupAuthorizedUser = huntGroupAuthorizedUser;
    }
    //</editor-fold>

    public String changeHuntGroupName(){
        return this.huntGroupName = "ChangedName "+getRandomString(15);
    }

    public String changeDisplayName(){
        return this.huntGroupDisplayName = "ChangedDisplayName "+getRandomString(15);
    }

    public String changeNumber(){
        return this.huntGroupNumber = getRandomCustomerFreePhoneNumberFromDB();
    }

    public String getJson(){
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        return factory.createObjectBuilder()
                .add("huntGroupNumber", factory.createObjectBuilder()
                        .add("phoneNumberId", getPhoneNumberId())
                        .add("number", getHuntGroupNumber())
                )
                .add("huntGroupName",getHuntGroupName())
                .add("huntGroupDisplayName",getHuntGroupDisplayName())
                .add("huntGroupLanguage",getHuntGroupLanguage())
                .add("grantedUsers",factory.createArrayBuilder())
                .add("voicemailSettings",factory.createObjectBuilder()
                        .add("email","")
                        .add("pin","")
                        .add("salutation", "")
                        .add("onlySendByEmail", false)
                )
                .add("huntGroupBackUpRouting",factory.createObjectBuilder()
                        .add("backUpAccount", JsonValue.NULL)
                        .add("backUpType", 0)
                        .add("backUpNumber", JsonValue.NULL)
                )
                .add("huntGroupTimerQueues", factory.createArrayBuilder()
                        .add(factory.createObjectBuilder()
                                        .add("huntGroupQueueId", JsonValue.NULL)
                                        .add("timerQueueName","Default")
                                        .add("position", 999)
                                        .add("timerQueueType", "standard")
                                        .add("huntGroupQueueActions", factory.createArrayBuilder())
                                        .add("huntGroupQueueActionsUpdate", false)
                                        .add("viewIndex",0))
                )
                .add("hasCallsRecording", factory.createObjectBuilder()
                        .add("activateCallRecording", false)
                ).build().toString();
    }

    public String getId(){
        String query ="SELECT ringruf_id FROM webadmin_20170426.ringruf where logical_name = \"%s\"";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(String.format(query,getHuntGroupName()));
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

    public String getPhoneNumberId(){
        String query ="SELECT phonenumber_id FROM webadmin_20170426.phonenumber where number = \"%s\" and owner_fk = 906144";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(String.format(query,getHuntGroupNumber()));
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

