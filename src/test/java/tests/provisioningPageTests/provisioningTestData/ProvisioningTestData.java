package tests.provisioningPageTests.provisioningTestData;

import flow.BaseTestMethods;

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
}
