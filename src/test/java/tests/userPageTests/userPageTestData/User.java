package tests.userPageTests.userPageTestData;


import flow.BaseTestMethods;
import flow.PublicEnums;


import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonValue;
import java.sql.ResultSet;
import java.sql.SQLException;


public class User extends BaseTestMethods {
    //<editor-fold desc="properties">
    private String title = "Mr";
    private String firstName = getRandomString(15);
    private String lastName = getRandomString(15);
    private String loginEmail = getRandomEmail();
    private String UseDiffContactEmail = getRandomEmail();
    private String VoiceEmail = getRandomEmail();
    private String phoneNumber;
    private String endDevices;
    private String permittedDestinationNumbers = PublicEnums.PermittedDestNums.getRandVal();
    private String callsRecordingDirection = PublicEnums.CallsRecordingDirection.getRandVal();
    private String inputLocalHeaderInfo = getRandomString(10);
    private String forwardDelay;
    private String forwardToPhone = getRandomPhone();
    private String manualStatusSubj = getRandomString(20);
    private String manualStatusDataFrom = getDate("DAY",1);
    private String manualStatusDataTo = getDate("YEAR", 1);;
    private String afterDelay = getRandomNumber(2);
    private String voicemailPinCode = getRandomNumber(4);
    private String voicemailEmail = getRandomEmail();
    private String voicemailSalutation = getRandomString(15);
    private String faxEmail = getRandomEmail();
    private String differentContactEmail = getRandomEmail();
    private String penalty = getRandomNumber(100,999);
    private String faxReceiveFormat = PublicEnums.FaxReceiveFormat.getRandom();
    //</editor-fold>

    //<editor-fold desc="get\set">


    public String getFaxReceiveFormat() {
        return faxReceiveFormat;
    }

    public String getDifferentContactEmail() {
        return differentContactEmail;
    }

    public String getPenalty() {
        return penalty;
    }

    public String getFaxEmail() {
        return faxEmail;
    }

    public String getVoicemailSalutation() {
        return voicemailSalutation;
    }

    public String getVoicemailEmail() {
        return voicemailEmail;
    }

    public String getVoicemailPinCode() {
        return voicemailPinCode;
    }

    public String getAfterDelay() {
        return afterDelay;
    }

    public String getManualStatusDataTo() {
        return manualStatusDataTo;
    }

    public String getManualStatusDataFrom() {
        return manualStatusDataFrom;
    }

    public String getManualStatusSubj() {
        return manualStatusSubj;
    }

    public String getForwardToPhone() {
        return forwardToPhone;
    }

    public String getForwardDelay() {
        return forwardDelay;
    }

    public void setForwardDelay(String forwardDelay) {
        this.forwardDelay = forwardDelay;
    }

    public String getInputLocalHeaderInfo() {
        return inputLocalHeaderInfo;
    }

    public void setCallsRecordingDirection(String callsRecordingDirection) {
        this.callsRecordingDirection = callsRecordingDirection;
    }

    public String getCallsRecordingDirection() {
        return callsRecordingDirection;
    }

    public void setPermittedDestinationNumbers(String permittedDestinationNumbers) {
        this.permittedDestinationNumbers = permittedDestinationNumbers;
    }

    public String getPermittedDestinationNumbers() {
        return permittedDestinationNumbers;
    }

    public void setEndDevices(String endDevices) {
        this.endDevices = endDevices.substring(0,endDevices.length()-2);
    }

    public String getEndDevices() {
        return endDevices;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public String getTitle() {
        return title;
    }

    public String getUseDiffContactEmail() {
        return UseDiffContactEmail;
    }

    public String getVoiceEmail() {
        return VoiceEmail;
    }

    public String getFullName(){
        return lastName+" "+firstName;
    }
    //</editor-fold>

    public User(){
        this.phoneNumber = getRandomCustomerFreePhoneNumberFromDB();
        this.endDevices = getRandomCustomerFreeEndDeviceFromDB();
    }

    public User(String autoProvisionedEndDevice){
        this.phoneNumber = getRandomCustomerFreePhoneNumberFromDB();
        this.endDevices = autoProvisionedEndDevice;
    }

    public String getJson(){
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        return factory.createObjectBuilder()
                .add("userNaming", factory.createObjectBuilder()
                        .add("salutation", factory.createObjectBuilder()
                                .add("id",10)
                                .add("messageKey","salutation.male")
                        )
                        .add("firstName", getFirstName())
                        .add("lastName", getLastName())
                        .add("loginEmail", getLoginEmail())
                        .add("useDifferentContactEmail", JsonValue.TRUE)
                        .add("differentContactEmail",getDifferentContactEmail())
                )
                .add("userAllocation", factory.createObjectBuilder()
                        .add("useVoicemailEmail", JsonValue.TRUE)
                        .add("voicemailEmail", getVoicemailEmail())
                        .add("number", getPhoneNumber())
                        .add("accounts", factory.createArrayBuilder()
                                .add(getEndDevId())
                        )
                        .add("busyOnBusy",JsonValue.TRUE)
                        .add("internalNumbers", factory.createArrayBuilder())
                        .add("currentBlockSet",Integer.parseInt(getPermittedDestinationNumbers()))
                        .add("smsEnabled",JsonValue.FALSE)
                        .add("faxEnabled",JsonValue.FALSE)
                        .add("callRecording", factory.createObjectBuilder()
                                .add("activateCallRecording",JsonValue.TRUE)
                                .add("callRecordingDirection",getCallsRecordingDirection())
                        )
                ).build().toString();
    }

    public String getId(){
        String query ="SELECT customer_id FROM webadmin_20170426.customer where display_name = \"%s\"";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(String.format(query,getFullName()));
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

    private String getEndDevId(){
        String query ="SELECT account_id " +
                "FROM webadmin_20170426.account " +
                "where owner_fk = 906144 and name like \"%s\"";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(String.format(query,getEndDevices()));
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

    public String getAccountId(){
        String query = "SELECT account_id \n" +
                "FROM webadmin_20170426.account a\n" +
                "join customer c on c.customer_id = a.customer_fk\n" +
                "where c.display_name = \"%s\";";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(String.format(query,getFullName()));
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
