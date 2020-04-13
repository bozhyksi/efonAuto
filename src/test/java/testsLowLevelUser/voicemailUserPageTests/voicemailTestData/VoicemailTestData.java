package testsLowLevelUser.voicemailUserPageTests.voicemailTestData;

import flow.BaseTestMethods;

public class VoicemailTestData extends BaseTestMethods {
    private String voicemailPinCode = getRandomNumber(4);
    private String voicemailEmail = getRandomEmail();
    private String voicemailSalutation = getRandomString(15);

    public String getVoicemailEmail() {
        return voicemailEmail;
    }

    public String getVoicemailPinCode() {
        return voicemailPinCode;
    }

    public String getVoicemailSalutation() {
        return voicemailSalutation;
    }
}
