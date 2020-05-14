package tests.userPageTests.userPageTestData;

import flow.BaseTestMethods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public enum Proxy{
        _1("pros.e-fon.ch"),
        _2("pro.e-fon.ch"),
        _3("adapt.e-fon.ch"),
        _4("sip12.e-fon.ch");

        private String proxy;

        Proxy(String proxy){
            this.proxy = proxy;
        }

        public static String getRandomProxy(){
            Random random = new Random();
            return Proxy.values()[random.nextInt(values().length)].proxy;
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
    private String endDevProxy;
    private boolean endDevSuppressedYES;
    private boolean endDevSuppressedNO;
    private boolean endDevSuppressed;
    private String randomEndDeviceForEdit;

    public EndDevice() {
        endDevName = "EndDevice" + getRandomString(10);
        endDevUserId = getRandomNumber(10);
        endDevPass = getRandomPassword();
        endDevCodec = Codecs.getCodec();
        endDevPhoneLanguage = Language.getRandomVal();
        endDevDisplayName = getRandomString(15);
        endDevLocation = "8306";
        endDevProxy = Proxy.getRandomProxy();
        endDevOutgoingNumber = getRandomOutgoingNumberFromDB();
        endDevSuppressed = getRandomBoolean();
        randomEndDeviceForEdit = getRandomEndDeviceForEditFromDB();
    }

    public String getRandomEndDeviceForEdit() {
        return randomEndDeviceForEdit;
    }

    public boolean getEndDevSuppressed(){
        return endDevSuppressed;
    }

    public String getEndDevProxy() {
        return endDevProxy;
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

    private String getRandomOutgoingNumberFromDB(){
        String query = "SELECT number FROM webadmin_20170426.phonenumber where customer_fk = 906144";
        ArrayList<String> outgoingNumbers = new ArrayList<>();

        ResultSet res = dataBaseWorker.execSqlQuery(query);
        while (true){
            try {
                if (!res.next()) break;
                outgoingNumbers.add(res.getString(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return outgoingNumbers.get(new Random().nextInt(outgoingNumbers.size()));
    }

    private String getRandomEndDeviceForEditFromDB(){
        String query = "SELECT name FROM webadmin_20170426.account where customer_fk=906144 and name like \"%EndDevice%\"";
        ArrayList<String> endDeviceList = new ArrayList<>();
        ResultSet res = dataBaseWorker.execSqlQuery(query);
        while (true){
            try {
                if (!res.next()) break;
                endDeviceList.add(res.getString(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return endDeviceList.get(new Random().nextInt(endDeviceList.size()));
    }


}
