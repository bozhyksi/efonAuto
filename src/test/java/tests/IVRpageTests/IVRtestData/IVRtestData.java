package tests.IVRpageTests.IVRtestData;

import flow.BaseTestMethods;
import flow.PublicEnums;

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

    private enum Language{
        de_ch,
        de,
        en,
        it,
        fr;
    }

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


    public IVRtestData(){
        this.ivrName = getRandomString(10);
        this.ivrDisplName = getRandomString(10);
        this.ivrLanguage = PublicEnums.LanguageValues.getRandLangVal();
        this.active = getRandomBoolean();
        this.parameterExtTelNumber = getRandomPhone();
        this.ivrNumber = getRandomCustomerFreePhoneNumberFromDB();
    }

    //<editor-fold desc="get\set">


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
}
