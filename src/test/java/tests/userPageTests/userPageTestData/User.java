package tests.userPageTests.userPageTestData;

import flow.BaseTestMethods;

public class User extends BaseTestMethods {
    private String title = "Mr";
    private String firstName = "AutoTest_"+getRandomString(5);
    private String lastName = "AutoTest_"+getRandomString(5);
    private String loginEmail = getRandomEmail();
    private String UseDiffContactEmail = getRandomEmail();
    private String VoiceEmail = getRandomEmail();
    private String phoneNumber;
    private String endDevices;

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
}
