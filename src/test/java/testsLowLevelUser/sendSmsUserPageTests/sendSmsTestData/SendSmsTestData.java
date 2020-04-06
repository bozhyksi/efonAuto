package testsLowLevelUser.sendSmsUserPageTests.sendSmsTestData;

import flow.BaseTestMethods;

public class SendSmsTestData extends BaseTestMethods {
    private String senderNumber;
    private String recipientNumber = getRandomPhone("074",7);
    private String smsText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                             "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                             "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat." +
                             "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                             "Excepteur sint occaecat cupidatat non proident, " +
                             "sunt in culpa qui officia deserunt mollit anim id est laborum.";

    public String getSenderNumber() {
        return senderNumber;
    }

    public void setSenderNumber(String senderNumber) {
        this.senderNumber = senderNumber;
    }

    public String getRecipientNumber() {
        return recipientNumber;
    }

    public String getSmsText() {
        return smsText;
    }
}
