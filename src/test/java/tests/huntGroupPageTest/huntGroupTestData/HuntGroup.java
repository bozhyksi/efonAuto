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

    //<editor-fold desc="properties">
    //Edit hunt group section
    private String huntGroupNumber;
    private String huntGroupLanguage;
    private String huntGroupAuthorizedUser;
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

    //Further time
    private String furtherTimeName = getRandomString(15);
    private String furtherTimeMonday = "08:00-11:15;13:30-15:00;";
    private String furtherTimeTuesday = "08:00-13:00;14:00-18:00;";
    private String furtherTimeWednesday = "08:00-13:00;14:00-18:00;";
    private String furtherTimeThursday = "08:00-11:15;13:30-15:00;";
    private String furtherTimeFriday = "08:00-13:00;14:00-18:00;";
    private String furtherTimeSaturday = "08:00-13:00;14:00-18:00;";
    private String furtherTimeSunday = "08:00-13:00;14:00-18:00;";
    //</editor-fold>

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

    public HuntGroup(String huntGroupName, String huntGroupNumber){
        //Edit hunt group section
        this.huntGroupLanguage = "en";
        this.huntGroupName = huntGroupName;
        this.huntGroupDisplayName = huntGroupName;
        this.huntGroupNumber = huntGroupNumber;

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

    //<editor-fold desc="get\set">


    public String getFurtherTimeSunday() {
        return furtherTimeSunday;
    }

    public String getFurtherTimeSaturday() {
        return furtherTimeSaturday;
    }

    public String getFurtherTimeFriday() {
        return furtherTimeFriday;
    }

    public String getFurtherTimeThursday() {
        return furtherTimeThursday;
    }

    public String getFurtherTimeWednesday() {
        return furtherTimeWednesday;
    }

    public String getFurtherTimeTuesday() {
        return furtherTimeTuesday;
    }

    public String getFurtherTimeMonday() {
        return furtherTimeMonday;
    }

    public String getFurtherTimeName() {
        return furtherTimeName;
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

    public String getHuntGroupAuthorizedUser() {
        return huntGroupAuthorizedUser;
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

    public void setHuntGroupAuthorizedUser(String huntGroupAuthorizedUser) {
        this.huntGroupAuthorizedUser = huntGroupAuthorizedUser;
    }
    //</editor-fold>
}

