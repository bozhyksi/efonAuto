package flow;

import java.util.Random;

public class PublicEnums {

    public enum LanguageValues{

        ES("es"),
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

}
