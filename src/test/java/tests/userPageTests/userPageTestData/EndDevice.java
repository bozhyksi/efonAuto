package tests.userPageTests.userPageTestData;

import flow.BaseTestMethods;

import java.util.Random;

public class EndDevice extends BaseTestMethods  {

    public enum Codecs{
        _10("10"),
        _20("20"),
        _30("30"),
        _40("40"),
        _31("31");

        private String value;

        Codecs(String codecValue){
            this.value = codecValue;
        }

        private static Codecs getRandomVal(){
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
         public static String getCodec(){
            return getRandomVal().value;
         }
    }

    public enum Language{
        de,
        fr,
        en,
        it;

        public static String getRandomVal(){
            Random random = new Random();
            return values()[random.nextInt(values().length)].toString();
        }
    }

    private String endDevName;
    private String endDevUserId;
    private String endDevPass;
    private String endDevCodec;
    private String endDevPhoneLanguage;
    private String endDevDisplayName;
    private String endDevOutgoingNumber;
    private String endDevCallPickups;
    private String endDevLocation;
    private boolean endDevSuppressedYES;
    private boolean endDevSuppressedNO;

    public EndDevice() {
        endDevName = "EndDevice" + getRandomString(10);
        endDevUserId = getRandomNumber(10);
        endDevPass = getRandomPassword();
        endDevCodec = Codecs.getCodec();
        endDevPhoneLanguage = Language.getRandomVal();
        endDevDisplayName = getRandomString(15);
        endDevLocation = "8306";
    }

    public String getEndDevCallPickups() {
        return endDevCallPickups;
    }

    public String getEndDevCodec() {
        return endDevCodec;
    }

    public String getEndDevDisplayName() {
        return endDevDisplayName;
    }

    public String getEndDevLocation() {
        return endDevLocation;
    }

    public String getEndDevName() {
        return endDevName;
    }

    public String getEndDevOutgoingNumber() {
        return endDevOutgoingNumber;
    }

    public String getEndDevPass() {
        return endDevPass;
    }

    public String getEndDevPhoneLanguage() {
        return endDevPhoneLanguage;
    }

    public boolean getEndDevSuppressedYES() {
        return endDevSuppressedYES;
    }

    public String getEndDevUserId() {
        return endDevUserId;
    }

    public void setEndDevOutgoingNumber(String endDevOutgoingNumber) {
        this.endDevOutgoingNumber = endDevOutgoingNumber;
    }

    public void setEndDevCallPickups(String endDevCallPickups) {
        this.endDevCallPickups = endDevCallPickups;
    }

    public void setEndDevCodec(String endDevCodec) {
        this.endDevCodec = endDevCodec;
    }

    public void setEndDevSuppressedYES(boolean endDevSuppressedYES) {
        this.endDevSuppressedYES = endDevSuppressedYES;
    }

    public boolean getEndDevSuppressedNO(){
        return this.endDevSuppressedNO;
    }

    public void setEndDevSuppressedNO(boolean endDevSuppressedNO) {
        this.endDevSuppressedNO = endDevSuppressedNO;
    }
}
