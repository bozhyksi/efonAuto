package pages.userPage.userPagePopup.configureUser;

public class VoicemailTabConfigUserPopup extends ConfigureUserBasePopup {
    private String dropdownSelectNumberXpath = "//voicemail-settings//h3[text()=\"Select number\"]/following-sibling::select";
    private String buttonVoicemailRetrievalXpath = "//voicemail-settings//h2[contains(text(),\"Voicemail retrieval/delivery\")]/following-sibling::div//i[contains(@class,\"fa-cog\")]";
    private String inputPinCodeXpath = "//voicemail-settings//input[@formcontrolname=\"voicemailPin\"]";
    private String inputEmailForVoiceXpath = "//voicemail-settings//input[@formcontrolname=\"voicemailEmail\"]";
    private String inputSalutationXpath = "//voicemail-settings//input[@formcontrolname=\"voicemailAnrede\"]";
    private String checkboxYesXpath = "//voicemail-settings//span[text()=\"Yes\"]/preceding-sibling::input[@formcontrolname=\"voicemailDelete\"]";
    private String buttonSaveXpath = "//voicemail-settings//button[text()=\"Save\"]";
    private String buttonCancelXpath = "//voicemail-settings//button[text()=\"Cancel\"]";




}
