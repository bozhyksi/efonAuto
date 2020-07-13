package flow;

import java.util.Random;

public class PublicEnums {

    //general
    public enum LanguageValues{

        IT("it"),
        DE("de"),
        FR("fr"),
        EN("en");

        private String val;

        LanguageValues(String val){
            this.val=val;
        }

        public static String getRandLangVal(){
            return values()[new Random().nextInt(LanguageValues.values().length)].getVal();
        }

        private String getVal() {
            return val;
        }
    }
    public enum DragDropSection {
        SECTION_SELECTED,
        SECTION_NOT_SELECTED
    }
    public enum State{
        ACTIVATED,
        DEACTIVATED;
    }
    public enum ItemsPerPage{
        _10("10"),
        _25("25"),
        _50("50"),
        _100("100"),
        _All("-1");

        private String items;

        ItemsPerPage(String items){
            this.items = items;
        }

        public String getItems() {
            return items;
        }
    }

    //IVRs
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
        CALL_CENTER_QUEUE;

        public static IvrActions getRandomIvrActionValue(){
            return IvrActions.values()[new Random().nextInt(IvrActions.values().length)];
        }
    }
    public enum IvrEvents{
        _1("1"),
        _2("2"),
        _3("3"),
        _4("4"),
        _5("5"),
        _6("6"),
        _7("7"),
        _8("8"),
        _9("9"),
        _0("0"),
        _star("*"),
        _dies("#"),
        ABBREVIATED_NUMBER("Abbreviated number"),
        INVALID_CHOICE("Invalid choice"),
        TIME_EXPIRED("Time expired"),
        MAX_PASSES_REACHED("Max. passes reached");

        private String val;

        IvrEvents(String val){
            this.val = val;
        }

        public String getVal() {
            return val;
        }

        public String getRandomValue(){
            return IvrEvents.values()[new Random().nextInt(IvrEvents.values().length)].getVal();
        }
    }

    //Provisioning
    public enum PhoneModelFunctions{

        NOT_SELECTED("Not selected"),
        PHONE_NUMBER("Phone number"),
        FORWARDING("Forwarding");

        private String func;

        PhoneModelFunctions(String func){
            this.func = func;
        }

        public String getFunc(){
            return this.func;
        }

        public static String getRandFunc(){
            return values()[new Random().nextInt(PhoneModelFunctions.values().length)].getFunc();
        }

    }

    //Hunt Groups
    public enum HuntGroupTimerGroup{
        FULL_DAYS("Full days"),
        TIME("Time");

        private String type;

        HuntGroupTimerGroup(String type){
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
    public enum HuntGroupStepActions {
        NumberEndDevice("0"),
        VoicemailUnavailable("1"),
        Announcements("2"),
        VoicemailBusy("3"),
        VoicemailNoAnnouncement("4"),
        Queue("5");

        private String value;

        HuntGroupStepActions(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    //Users
    public enum PermittedDestNums{
        _1000("1000"),
        _4000("4000"),
        _7000("7000"),
        _7300("7300");
        //_7310("7310"),
        //_7340("7340");

        private String val;

        PermittedDestNums(String val){
            this.val = val;
        }

        public static String getRandVal(){
            return PermittedDestNums.values()[new Random().nextInt((PermittedDestNums.values().length))].val;
        }
    }
    public enum CallsRecordingDirection{
        ALL,
        IN,
        OUT;

        public static String getRandVal(){
            return CallsRecordingDirection.values()[new Random().nextInt((CallsRecordingDirection.values().length))].toString();
        }

    }
    public enum Roles{
        VPBX_ADMIN("ROLE_SUPERUSER"),
        RECORDED_CALLS_MANAGER("ROLE_RECORDED_CALLS_MANAGER");

        private String role;

        Roles(String role){
            this.role = role;
        }

        public String getRole() {
            return role;
        }
    }

    //Fax
    public enum FaxReceiveFormat{
        TIFF_and_PDF,
        PDF,
        TIFF;

        public static String getRandom(){
            return FaxReceiveFormat.values()[new Random().nextInt(FaxReceiveFormat.values().length)].toString();
        }

    }



}
