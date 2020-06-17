package tests.IVRpageTests.IVRtestData;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactoryBuilder;
import flow.BaseTestMethods;
import flow.PublicEnums;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonValue;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IVRtestData extends BaseTestMethods {

    public enum IvrActions{
        RINGRUF,
        PHONE_DIRECT,
        PHONE_INTERNAL,
        PHONE_EXTERNAL,
        IVR,
        VM_UNAVAILABLE,
        VM_NO_ANNOUNCE,
        VM_PERSONAL,
        HANGUP,
        RESTART,
        PLAY_HANGUP,
        VM_BUSY,
        PLAY_RESTART,
        CALL_CENTER_QUEUE
    }

    public enum EventNumber{
        Event_1("1"),
        Event_2("2"),
        Event_3("3"),
        Event_4("4"),
        Event_5("5"),
        Event_6("6"),
        Event_7("7"),
        Event_8("8"),
        Event_9("9"),
        Event_0("0"),
        Event_star("*"),
        Event_diez("#"),
        Event_abbrevNumber("Abbreviated number");

        private String event;

        EventNumber(String event){
            this.event = event;
        }

        public String getEvent() {
            return event;
        }
    }

    //<editor-fold desc="properties">
    private String ivrName;
    private String ivrDisplName;
    private String ivrLanguage;
    private String ivrNumber;
    private String ivrAnnounce;
    private String parameter;
    private String eventNumber;
    private boolean active;
    private String action;
    private String parameterExtTelNumber;
    private String parameterHuntGroup;
    private String parameterPhoneDirect;
    private String parameterPhoneInternal;
    private String parameterIVR;
    private String parameterPlayAndHangUp;
    private String parameterQueue;
    private String parameterMaxThroughputs;
    private String parameterMaxWaitingTime;
    private String pinCode;
    private FileManagementTestData announcement;
    //</editor-fold>

    public IVRtestData(){
        this.parameterMaxThroughputs = getRandomNumber(1,9);
        this.parameterMaxWaitingTime = getRandomNumber(10,19);
        this.ivrName = getRandomString(10);
        this.ivrDisplName = getRandomString(10);
        this.ivrLanguage = PublicEnums.LanguageValues.getRandLangVal();
        this.active = getRandomBoolean();
        this.parameterExtTelNumber = getRandomPhone();
        this.ivrNumber = getRandomCustomerFreePhoneNumberFromDB();
        this.pinCode = getRandomNumber(1111,9999);
    }

    public IVRtestData(FileManagementTestData announcement){
        this.parameterMaxThroughputs = getRandomNumber(1,9);
        this.parameterMaxWaitingTime = getRandomNumber(10,19);
        this.ivrName = getRandomString(10);
        this.ivrDisplName = getRandomString(10);
        this.ivrLanguage = PublicEnums.LanguageValues.getRandLangVal();
        this.active = getRandomBoolean();
        this.parameterExtTelNumber = getRandomPhone();
        this.ivrNumber = getRandomCustomerFreePhoneNumberFromDB();
        this.pinCode = getRandomNumber(1111,9999);
        this.announcement = announcement;
    }

    public IVRtestData(String num){
        this.parameterMaxThroughputs = getRandomNumber(1,9);
        this.parameterMaxWaitingTime = getRandomNumber(10,19);
        this.ivrName = getRandomString(10);
        this.ivrDisplName = getRandomString(10);
        this.ivrLanguage = PublicEnums.LanguageValues.getRandLangVal();
        this.active = getRandomBoolean();
        this.parameterExtTelNumber = getRandomPhone();
        this.ivrNumber = num;
        this.pinCode = getRandomNumber(1111,9999);
    }

    //<editor-fold desc="get\set">


    public FileManagementTestData getAnnouncement() {
        return announcement;
    }

    public String getParameterMaxWaitingTime() {
        return parameterMaxWaitingTime;
    }

    public String getParameterMaxThroughputs() {
        return parameterMaxThroughputs;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setEventNumber(String eventNumber) {
        this.eventNumber = eventNumber;
    }

    public void setParameterQueue(String parameterQueue) {
        this.parameterQueue = parameterQueue;
    }

    public String getParameterQueue() {
        return parameterQueue;
    }

    public void setParameterPlayAndHangUp(String parameterPlayAndHangUp) {
        this.parameterPlayAndHangUp = parameterPlayAndHangUp;
    }

    public String getParameterPlayAndHangUp() {
        return parameterPlayAndHangUp;
    }

    public String getParameterIVR() {
        return parameterIVR;
    }

    public void setParameterIVR(String parameterIVR) {
        this.parameterIVR = parameterIVR;
    }

    public void setParameterPhoneInternal(String parameterPhoneInternal) {
        this.parameterPhoneInternal = parameterPhoneInternal;
    }

    public String getParameterPhoneInternal() {
        return parameterPhoneInternal;
    }

    public void setParameterPhoneDirect(String parameterPhoneDirect) {
        this.parameterPhoneDirect = parameterPhoneDirect;
    }

    public String getParameterPhoneDirect() {
        return parameterPhoneDirect;
    }

    public void setParameterHuntGroup(String parameterHuntGroup) {
        this.parameterHuntGroup = parameterHuntGroup;
    }

    public String getParameterHuntGroup() {
        return parameterHuntGroup;
    }

    public String getParameterExtTelNumber() {
        return parameterExtTelNumber;
    }

    public void setAction(IvrActions action) {
        this.action = action.toString();
    }

    public String getAction() {
        return action;
    }

    public boolean getActiveFlag(){
        return this.active;
    }

    public String getEventNumber() {
        return eventNumber;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return this.parameter;
    }

    public String getIvrName() {
        return ivrName;
    }

    public String getIvrAnnounce() {
        return ivrAnnounce;
    }

    public String getIvrDisplName() {
        return ivrDisplName;
    }

    public String getIvrLanguage() {
        return ivrLanguage;
    }

    public String getIvrNumber() {
        return ivrNumber;
    }

    public void setIvrNumber(String ivrNumber) {
        this.ivrNumber = ivrNumber.replaceAll("\\s","");
    }

    public void setIvrAnnounce(String ivrAnnounce) {
        this.ivrAnnounce = ivrAnnounce.replaceAll("\\s","");
    }
    //</editor-fold>

    public String getJson(){
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        return factory.createObjectBuilder()
                .add("logicalName", getIvrName())
                .add("displayName", getIvrDisplName())
                .add("language", getIvrLanguage())
                .add("number", getPhoneNumberId(getIvrNumber()))
                .add("announcement", announcement.getId())
                .add("ivrActions", factory.createArrayBuilder()
                        .add(factory.createObjectBuilder()
                                .add("active",false)
                                .add("ivrActionType","HANGUP")
                                .add("event",1)
                                .add("parameter", factory.createObjectBuilder()
                                        .add("id", JsonValue.NULL)
                                )
                        )
                        .add(factory.createObjectBuilder()
                                .add("active",false)
                                .add("ivrActionType","HANGUP")
                                .add("event",2)
                                .add("parameter", factory.createObjectBuilder()
                                        .add("id", JsonValue.NULL)
                                )
                        )
                        .add(factory.createObjectBuilder()
                                .add("active",false)
                                .add("ivrActionType","HANGUP")
                                .add("event",3)
                                .add("parameter", factory.createObjectBuilder()
                                        .add("id", JsonValue.NULL)
                                )
                        )
                        .add(factory.createObjectBuilder()
                                .add("active",false)
                                .add("ivrActionType","HANGUP")
                                .add("event",4)
                                .add("parameter", factory.createObjectBuilder()
                                        .add("id", JsonValue.NULL)
                                )
                        )
                        .add(factory.createObjectBuilder()
                                .add("active",false)
                                .add("ivrActionType","HANGUP")
                                .add("event",5)
                                .add("parameter", factory.createObjectBuilder()
                                        .add("id", JsonValue.NULL)
                                )
                        )
                        .add(factory.createObjectBuilder()
                                .add("active",false)
                                .add("ivrActionType","HANGUP")
                                .add("event",6)
                                .add("parameter", factory.createObjectBuilder()
                                        .add("id", JsonValue.NULL)
                                )
                        )
                        .add(factory.createObjectBuilder()
                                .add("active",false)
                                .add("ivrActionType","HANGUP")
                                .add("event",7)
                                .add("parameter", factory.createObjectBuilder()
                                        .add("id", JsonValue.NULL)
                                )
                        )
                        .add(factory.createObjectBuilder()
                                .add("active",false)
                                .add("ivrActionType","HANGUP")
                                .add("event",8)
                                .add("parameter", factory.createObjectBuilder()
                                        .add("id", JsonValue.NULL)
                                )
                        )
                        .add(factory.createObjectBuilder()
                                .add("active",false)
                                .add("ivrActionType","HANGUP")
                                .add("event",9)
                                .add("parameter", factory.createObjectBuilder()
                                        .add("id", JsonValue.NULL)
                                )
                        )
                        .add(factory.createObjectBuilder()
                                .add("active",false)
                                .add("ivrActionType","HANGUP")
                                .add("event",0)
                                .add("parameter", factory.createObjectBuilder()
                                        .add("id", JsonValue.NULL)
                                )
                        )
                        .add(factory.createObjectBuilder()
                                .add("active",false)
                                .add("ivrActionType","HANGUP")
                                .add("event","*")
                                .add("parameter", factory.createObjectBuilder()
                                        .add("id", JsonValue.NULL)
                                )
                        )
                        .add(factory.createObjectBuilder()
                                .add("active",false)
                                .add("ivrActionType","HANGUP")
                                .add("event","#")
                                .add("parameter", factory.createObjectBuilder()
                                        .add("id", JsonValue.NULL)
                                )
                        )
                        .add(factory.createObjectBuilder()
                                .add("active",false)
                                .add("ivrActionType","HANGUP")
                                .add("event","_INTERNALNB_")
                                .add("parameter", factory.createObjectBuilder()
                                        .add("id", JsonValue.NULL)
                                )
                        )
                        .add(factory.createObjectBuilder()
                                .add("active",false)
                                .add("ivrActionType","HANGUP")
                                .add("event","i")
                                .add("parameter", factory.createObjectBuilder()
                                        .add("id", JsonValue.NULL)
                                )
                        )
                        .add(factory.createObjectBuilder()
                                .add("active",false)
                                .add("ivrActionType","HANGUP")
                                .add("event","t")
                                .add("parameter", factory.createObjectBuilder()
                                        .add("id", JsonValue.NULL)
                                )
                        )
                        .add(factory.createObjectBuilder()
                                .add("active",false)
                                .add("ivrActionType","HANGUP")
                                .add("event","d")
                                .add("parameter", factory.createObjectBuilder()
                                        .add("id", JsonValue.NULL)
                                )
                        )
                )
                .add("maxThroughputs", 1)
                .add("maxWaitTime", 3)
                .add("pin", "")
                .add("voiceMailEmail", "")
                .add("voiceMailTitle", "")
                .add("deleteVoiceMail", false)
                .add("hasCallsRecording", factory.createObjectBuilder()
                        .add("activateCallRecording", false)
                )
                .add("isNumberPublic", true)
                .build().toString();
    }

    public String getId(){
        String query = "SELECT ivr_id FROM webadmin_20170426.ivr where logical_name = \"%s\"";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(String.format(query,getIvrName()));
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
