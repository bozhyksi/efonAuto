package testsLowLevelUser.faxUserPageTests.faxUserPageTestData;

import flow.BaseTestMethods;

public class SendFaxTestData extends BaseTestMethods {
    //<editor-fold desc="properties">
    private String outgoingNumberValue;
    private String outgoingNumber;
    private String destinationNumber;
    private String faxFilePath;
    //</editor-fold>

    public SendFaxTestData(){
        this.outgoingNumberValue = getRandomNumber(1,30)+": Object";
        this.outgoingNumber = getRandomCustomerFreePhoneNumberFromDB();
        this.destinationNumber = getRandomPhone("044",7);
        this.faxFilePath = "testData\\faxSendFile\\sample.pdf";
    }

    //<editor-fold desc="get\set">
    public String getFaxFilePath() {
        return faxFilePath;
    }

    public String getDestinationNumber() {
        return destinationNumber;
    }

    public void setOutgoingNumber(String outgoingNumber) {
        this.outgoingNumber = outgoingNumber;
    }

    public String getOutgoingNumber() {
        return outgoingNumber;
    }

    public String getOutgoingNumberValue() {
        return outgoingNumberValue;
    }
    //</editor-fold>

}

