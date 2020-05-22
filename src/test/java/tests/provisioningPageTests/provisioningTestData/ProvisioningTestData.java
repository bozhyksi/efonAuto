package tests.provisioningPageTests.provisioningTestData;

import flow.BaseTestMethods;
import flow.PublicEnums;

import java.util.Random;

public class ProvisioningTestData extends BaseTestMethods {

    public static class ProvisioningManagerData extends ProvisioningTestData{

        private enum SetProvisioningForXMinutes {
            _10("10"),
            _20("20"),
            _30("30"),
            _60("60"),
            _120("120"),
            _180("180");

            private String value;

            SetProvisioningForXMinutes (String value){
                this.value = value;
            }

            public static String getRandomEnumValue(){
                return values()[new Random().nextInt(values().length)].getValue();
            }

            public String getValue() {
                return value;
            }
        }

        private String pinCode;
        private String setProvisioningForXMinutes;
        private String ipAddress;

        public ProvisioningManagerData(){
            this.pinCode = getRandomNumber(1111,9999);
            this.setProvisioningForXMinutes = SetProvisioningForXMinutes.getRandomEnumValue();
            this.ipAddress = getRandomIpAddress()+";"+getRandomIpAddress()+";"+getRandomIpAddress();
        }

        public String getIpAddress() {
            return ipAddress;
        }

        public String getPinCode() {
            return pinCode;
        }

        public String getSetProvisioningForXMinutes() {
            return setProvisioningForXMinutes;
        }
    }

    public static class ConfigurationTemplate extends ProvisioningTestData{
        private String filePath = "testData\\provisioning\\aastra_37i_V2.0_6867.cfg";

        public String getFilePath() {
            return filePath;
        }
    }

    public static class ProvisioningSettings extends ProvisioningTestData{

        private String languageValue;
        private String webLanguageVal;
        private String webUser;
        private String webPassword;
        private String destNum;
        private String dispName;

        public ProvisioningSettings(){
            this.languageValue = PublicEnums.LanguageValues.getRandLangVal();
            this.webLanguageVal = PublicEnums.LanguageValues.getRandLangVal();
            this.webUser = getRandomString(10);
            this.webPassword = getRandomPassword();
            this.destNum = getRandomPhone();
            this.dispName = getRandomString(15);
        }

        public String getDestNum() {
            return destNum;
        }

        public String getDispName() {
            return dispName;
        }

        public String getLanguageValue() {
            return languageValue;
        }

        public String getWebLanguageVal() {
            return webLanguageVal;
        }

        public String getWebPassword() {
            return webPassword;
        }

        public String getWebUser() {
            return webUser;
        }
    }
}
