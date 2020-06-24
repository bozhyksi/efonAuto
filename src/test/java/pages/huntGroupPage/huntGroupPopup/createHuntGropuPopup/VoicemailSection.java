package pages.huntGroupPage.huntGroupPopup.createHuntGropuPopup;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;

import static com.codeborne.selenide.Condition.value;

public class VoicemailSection extends CreateHuntGroupPopup {

    //<editor-fold desc="locators">
    private String inputPinXpath = "//input[@formcontrolname=\"pin\"]";
    private String inputEmailXpath = "//input[@formcontrolname=\"email\"]";
    private String inputSalutationXpath = "//input[@formcontrolname=\"salutation\"]";
    private String checkboxOnlySendByEmailXpath = "//input[@formcontrolname=\"onlySendByEmail\"]";
    private String buttonSubmitVoicemailXpath = "//h2[text()=\"Voicemail settings\"]/a";
    //</editor-fold>

    @Step("Enter PIN code")
    public VoicemailSection enterPIN(String pin){
        field(inputPinXpath).setValue(pin);
        return this;
    }

    @Step("Activate \"Only send voice messages by email and do not save in the voice mailbox\"")
    public VoicemailSection activateSendByEmailCheckBox(){
        field(checkboxOnlySendByEmailXpath).click();
        return this;
    }

    @Step("Enter Email")
    public VoicemailSection enterEmail(String email){
        field(inputEmailXpath).setValue(email);
        return this;
    }

    @Step("Enter salutation for e-mails with voice messages")
    public VoicemailSection enterSalutation(String salutation){
        field(inputSalutationXpath).setValue(salutation);
        return this;
    }

    @Step("Save")
    public CreateHuntGroupPopup saveVoicemail(){
        field(buttonSubmitVoicemailXpath).click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Validate Voicemail configurations")
    public VoicemailSection validateVoicemail(HuntGroup huntGroup){
        field(inputPinXpath).shouldHave(value(huntGroup.getPinCode()));
        field(inputEmailXpath).shouldHave(value(huntGroup.getVoicemailEmail()));
        field(inputSalutationXpath).shouldHave(value(huntGroup.getSalutation()));
        return this;
    }


}
