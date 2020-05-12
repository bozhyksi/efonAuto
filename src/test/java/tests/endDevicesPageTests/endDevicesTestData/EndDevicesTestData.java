package tests.endDevicesPageTests.endDevicesTestData;

import flow.BaseTestMethods;

public class EndDevicesTestData extends BaseTestMethods {

    public static class EndDevice{
        private final String autoProvisionedEndDeviceName = "Account 906144a10";

        public String getAutoProvisionedEndDeviceName() {
            return autoProvisionedEndDeviceName;
        }
    }

    public static class AutoProvisionedInfo {

        //<editor-fold desc="properties">
        private final String phoneModel = "aastra 6867i";
        private final String firmwareVersion = "4.2.0.181-SIP";
        private final String MAC = "00085D4C3333";
        private final String ipAddress= "244.5.5.7";
        private final String lastBootIp = "244.5.5.8";
        private final String dateOfFirstProvisioning = "02.04.2020 13:51";
        private final String dateOfLastProvisioning = "02.04.2020 13:51";
        //</editor-fold>

        //<editor-fold desc="get\set">
        public String getIpAddress() {
            return ipAddress;
        }

        public String getDateOfFirstProvisioning() {
            return dateOfFirstProvisioning;
        }

        public String getDateOfLastProvisioning() {
            return dateOfLastProvisioning;
        }

        public String getFirmwareVersion() {
            return firmwareVersion;
        }

        public String getLastBootIp() {
            return lastBootIp;
        }

        public String getMAC() {
            return MAC;
        }

        public String getPhoneModel() {
            return phoneModel;
        }
        //</editor-fold>

    }


}
