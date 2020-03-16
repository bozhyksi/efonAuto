package tests.userPageTests.userPageTestData;

import flow.BaseTestMethods;

public class User extends BaseTestMethods {
    private String title = "Mr";
    private String firstName = getRandomString(5);
    private String lastName = getRandomString(5);
    private String loginEmail = getRandomEmail();
    private String UseDiffContactEmail = getRandomEmail();
    private String VoiceEmail = getRandomEmail();
    private String phoneNumber;
    private String endDevices;
    private String permittedDestinationNumbers;
    private String callsRecordingDirection;
    private String inputLocalHeaderInfo = getRandomString(10);
    private String forwardDelay;
    private String forwardToPhone = getRandomPhone();
    private String manualStatusSubj = getRandomString(20);
    private String manualStatusDataFrom = getDate("DAY",1);
    private String manualStatusDataTo = getDate("YEAR", 1);;
    private String afterDelay = getRandomNumber(2);

    //<editor-fold desc="get\set">


    public String getAfterDelay() {
        return afterDelay;
    }

    public String getManualStatusDataTo() {
        return manualStatusDataTo;
    }

    public String getManualStatusDataFrom() {
        return manualStatusDataFrom;
    }

    public String getManualStatusSubj() {
        return manualStatusSubj;
    }

    public String getForwardToPhone() {
        return forwardToPhone;
    }

    public String getForwardDelay() {
        return forwardDelay;
    }

    public void setForwardDelay(String forwardDelay) {
        this.forwardDelay = forwardDelay;
    }

    public String getInputLocalHeaderInfo() {
        return inputLocalHeaderInfo;
    }

    public void setCallsRecordingDirection(String callsRecordingDirection) {
        this.callsRecordingDirection = callsRecordingDirection;
    }

    public String getCallsRecordingDirection() {
        return callsRecordingDirection;
    }

    public void setPermittedDestinationNumbers(String permittedDestinationNumbers) {
        this.permittedDestinationNumbers = permittedDestinationNumbers;
    }

    public String getPermittedDestinationNumbers() {
        return permittedDestinationNumbers;
    }

    public void setEndDevices(String endDevices) {
        this.endDevices = endDevices;
    }

    public String getEndDevices() {
        return endDevices;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public String getTitle() {
        return title;
    }

    public String getUseDiffContactEmail() {
        return UseDiffContactEmail;
    }

    public String getVoiceEmail() {
        return VoiceEmail;
    }

    public String getFullName(){
        return lastName+" "+firstName;
    }
    //</editor-fold>
}
