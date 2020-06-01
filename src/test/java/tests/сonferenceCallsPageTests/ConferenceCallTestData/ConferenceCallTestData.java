package tests.сonferenceCallsPageTests.ConferenceCallTestData;

import flow.BaseTestMethods;
import flow.PublicEnums;

public class ConferenceCallTestData extends BaseTestMethods {

    private String name;
    private String conferenceNumber;
    private String pin;
    private String language;

    public ConferenceCallTestData(){
        this.name = getRandomString(10);
        this.pin = getRandomNumber(4);
        this.language = PublicEnums.LanguageValues.getRandLangVal();
        this.conferenceNumber = getRandomCustomerFreePhoneNumberFromDB();
    }

    //<editor-fold desc="get\set">
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
    //</editor-fold>

    public String changeName(){
        return this.name = getRandomString(15);
    }

    public String changePIN(){
        return this.pin = getRandomNumber(4);
    }

    public String changeNumber(){
        return this.conferenceNumber = getRandomCustomerFreePhoneNumberFromDB();
    }

    public String changeLanguage(){
        return this.language = PublicEnums.LanguageValues.getRandLangVal();
    }
}
