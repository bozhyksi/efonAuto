package tests.basePageTests.basePageTestData;

import flow.BaseTestMethods;

public class BasePageTestsData extends BaseTestMethods {
    public enum MainMenu{
        user("User"),
        numbers("Numbers"),
        subscriptions("Subscriptions"),
        lastCalls("Calls"),
        fax("Faxes"),
        IVRs("IVR overview"),
        abbreviatedDialling("Abbreviated dialling"),
        callPickups("Groups for call pick-up"),
        fileManagement("File management"),
        callForwarding("Forwarding"),
        huntGroups("Hunt Groups"),
        conferenceCalls("Conference calls"),
        queues("Queues"),
        endDevices("End devices"),
        recordedCalls("Recorded calls"),
        phonebook("Phonebook"),
        contactData("Contact settings");

        private String tabName;

        MainMenu (String tabName){
            this.tabName = tabName;
        }

        public String getTabName() {
            return tabName;
        }
    }
}
