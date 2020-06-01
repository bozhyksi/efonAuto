package flow;

import java.util.Random;

public class PublicEnums {

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

    public enum dragDropSection{
        SECTION_SELECTED,
        SECTION_NOT_SELECTED
    }

}
