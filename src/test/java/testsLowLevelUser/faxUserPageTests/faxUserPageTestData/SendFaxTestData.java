package testsLowLevelUser.faxUserPageTests.faxUserPageTestData;

import flow.BaseTestMethods;

public class SendFaxTestData extends BaseTestMethods {
    private String outgoingNumberValue = getRandomNumber(1,30)+": Object";
    private String outgoingNumber;
    private String destinationNumber = getRandomPhone("044",7);
    private String faxFilePath = "testData\\faxSendFile\\sample.pdf";


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

}

