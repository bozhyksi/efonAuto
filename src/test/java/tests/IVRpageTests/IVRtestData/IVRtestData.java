package tests.IVRpageTests.IVRtestData;

import flow.BaseTestMethods;

public class IVRtestData extends BaseTestMethods {
    private enum Language{
        de_ch,
        de,
        en,
        it,
        fr;
    }
    private String ivrName;
    private String ivrDisplName;
    private String ivrLanguage;
    private String ivrNumber;
    private String ivrAnnounce;

    public IVRtestData(){
        this.ivrName = getRandomString(10);
        this.ivrDisplName = getRandomString(10);
        this.ivrLanguage = Language.en.toString();
    }

    public String getIvrName() {
        return ivrName;
    }

    public String getIvrAnnounce() {
        return ivrAnnounce;
    }

    public String getIvrDisplName() {
        return ivrDisplName;
    }

    public String getIvrLanguage() {
        return ivrLanguage;
    }

    public String getIvrNumber() {
        return ivrNumber;
    }

    public void setIvrNumber(String ivrNumber) {
        this.ivrNumber = ivrNumber;
    }

    public void setIvrAnnounce(String ivrAnnounce) {
        this.ivrAnnounce = ivrAnnounce;
    }
}
