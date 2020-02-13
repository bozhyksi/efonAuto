package tests.сonferenceCallsPageTests.ConferenceCallTestData;

import flow.BaseTestMethods;

public class Conference extends BaseTestMethods {
    private enum Language{
        de_ch,
        de,
        en,
        it,
        fr;
    }

    private String name;
    private String conferenceNumber;
    private String pin;
    private String language;

    public Conference(){
        this.name = getRandomString(10);
        this.pin = getRandomNumber(4);
        this.language = Language.en.toString();
    }

    public String getName() {
        return name;
    }

    public String getConferenceNumber() {
        return conferenceNumber;
    }

    public String getPin() {
        return pin;
    }

    public String getLanguage() {
        return language;
    }

    public void setConferenceNumber(String conferenceNumber) {
        this.conferenceNumber = conferenceNumber.replaceAll("\\s", "");
    }
}
