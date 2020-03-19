package tests.userPageTests.userPageTestData;

import flow.BaseTestMethods;
import tests.queuesPageTest.queueTestData.Queue;

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

        public static String getRandomVal(){
            Random random = new Random();
            return values()[random.nextInt(values().length)].toString();
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
    private String endDevSuppressed;

    public EndDevice() {
        endDevName = "EndDevice" + getRandomString(10);
        endDevUserId = getRandomNumber(10);
        endDevPass = getRandomPassword();
        endDevCodec = Codecs.getRandomVal();
        endDevPhoneLanguage = Language.getRandomVal();
        endDevDisplayName = getRandomString(15);
        endDevLocation = getRandomNumber(4);
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

    public String getEndDevSuppressed() {
        return endDevSuppressed;
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

    public void setEndDevSuppressed(String endDevSuppressed) {
        this.endDevSuppressed = endDevSuppressed;
    }
}
