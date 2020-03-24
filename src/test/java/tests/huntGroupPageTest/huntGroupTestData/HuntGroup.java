package tests.huntGroupPageTest.huntGroupTestData;

import flow.BaseTestMethods;

public class HuntGroup extends BaseTestMethods {
    public enum TimerLevels{
        NumberEndDevice ("0"),
        VoicemailUnavailable("1"),
        Announcements("2"),
        VoicemailBusy("3"),
        VoicemailNoAnnouncement("4"),
        Queue("5");
        private String level;

        TimerLevels(String level){
            this.level = level;
        }
        public String getLevel() {
            return level;
        }
    }

    //Edit hunt group section
    private String huntGroupNumber;
    private String huntGroupLanguage;
    private String huntGroupAuthorizedUsers;
    private String huntGroupName;
    private String huntGroupDisplayName;

    //Voicemail settings
    private String pinCode;
    private String voicemailEmail;
    private String salutation;

    //If end devices not available (not registered) section
    private String relevantAccount;
    private String backUpNumber;

    //Full days
    private String fullDayName;
    private String fullDayDate;
    private String fullDayPhoneNumber;

    public HuntGroup(){
        //Edit hunt group section
        this.huntGroupLanguage = "en";
        this.huntGroupName = getRandomString(10);
        this.huntGroupDisplayName = "Display_"+this.huntGroupName;

        //Voicemail settings
        this.pinCode = getRandomNumber(1111,9999);
        this.voicemailEmail = getRandomEmail();
        this.salutation = "Dear Ms";

        //If end devices not available (not registered) section
        this.backUpNumber = getRandomPhone();

        //Full days
        this.fullDayName = getRandomString(10);
        this.fullDayDate = "1.8; 24.12; 31.09; 08.12";
        fullDayPhoneNumber = getRandomPhone();
    }

    public String getFullDayPhoneNumber() {
        return fullDayPhoneNumber;
    }

    public String getFullDayDate() {
        return fullDayDate;
    }

    public String getFullDayName() {
        return fullDayName;
    }

    public String getHuntGroupDisplayName() {
        return huntGroupDisplayName;
    }

    public String getHuntGroupLanguage() {
        return huntGroupLanguage;
    }

    public String getHuntGroupName() {
        return huntGroupName;
    }

    public String getHuntGroupNumber() {
        return huntGroupNumber;
    }

    public String getHuntGroupAuthorizedUsers() {
        return huntGroupAuthorizedUsers;
    }

    public void setHuntGroupNumber(String huntGroupNumber) {
        this.huntGroupNumber = huntGroupNumber.replaceAll("\\s","");
    }

    public String getSalutation() {
        return salutation;
    }

    public String getVoicemailEmail() {
        return voicemailEmail;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getBackUpNumber() {
        return backUpNumber;
    }

    public void setRelevantAccount(String relevantAccount) {
        this.relevantAccount = relevantAccount;
    }

    public String getRelevantAccount() {
        return relevantAccount;
    }

    public void setHuntGroupAuthorizedUsers(String huntGroupAuthorizedUsers) {
        this.huntGroupAuthorizedUsers = huntGroupAuthorizedUsers;
    }
}

