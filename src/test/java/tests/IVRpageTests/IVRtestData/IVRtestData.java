package tests.IVRpageTests.IVRtestData;

import flow.BaseTestMethods;

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

    public IVRtestData(){
        this.ivrName = getRandomString(10);
        this.ivrDisplName = getRandomString(10);
        this.ivrLanguage = Language.en.toString();
        this.eventNumber = getRandomNumber(0,9);
        this.active = true;
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
}
