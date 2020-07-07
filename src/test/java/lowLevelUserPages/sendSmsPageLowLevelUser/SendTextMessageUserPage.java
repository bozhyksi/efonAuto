package lowLevelUserPages.sendSmsPageLowLevelUser;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lowLevelUserPages.sendSmsPageLowLevelUser.sendSmsUserPagePopup.SmsConfirmationPopup;

public class SendTextMessageUserPage extends SendSmsBaseUserPage {
    //<editor-fold desc="locators">
    private String dropdownSenderNameXpath = "//select[@id=\"senderNumber\"]";
    private String inputRecipientNumberXpath = "//input[@formcontrolname=\"phoneNumbersInput\"]";
    private String buttonAddRecipientXpath = "//i[contains(@class,\"fa-plus-circle\")]/parent::a";
    private String fieldSelectedItemXpath = "//span[@class=\"selected-item\"]";
    private String inputSmsTextAreaXpath = "//textarea[@id=\"message\"]";
    private String buttonSendXpath = "//button[text()=\"Send\"]";
    private String dropdownAddressBookXpath = "//label[text()=\"Select numbers from AddressBook\"]//following-sibling::div/select";
    private String fieldValidationMessageRecipientNumberXpath = "//input[@formcontrolname=\"phoneNumbersInput\"]//following-sibling::span[contains(text(),\"Input doesn't match pattern\")]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getFieldValidationMessageRecipientNumber() {
        return field(fieldValidationMessageRecipientNumberXpath);
    }

    public SelenideElement getDropdownAddressBook() {
        return field(dropdownAddressBookXpath);
    }

    public SelenideElement getDropdownSenderName() {
        return field(dropdownSenderNameXpath);
    }

    public SelenideElement getInputRecipientNumber() {
        return field(inputRecipientNumberXpath);
    }

    public SelenideElement getButtonAddRecipient() {
        return field(buttonAddRecipientXpath);
    }

    public SelenideElement getFieldSelectedItem() {
        return field(fieldSelectedItemXpath);
    }

    public SelenideElement getInputSmsTextArea() {
        return field(inputSmsTextAreaXpath);
    }

    public SelenideElement getButtonSend() {
        return field(buttonSendXpath);
    }
    //</editor-fold>

    @Step("Validate Recipient number input")
    public SendTextMessageUserPage validateRecipientNumberInput(String number){
        getInputRecipientNumber().clear();
        getInputRecipientNumber().setValue(number);
        getInputRecipientNumber().pressTab();
        waitUntilAlertDisappear();
        getFieldValidationMessageRecipientNumber().should(Condition.exist,Condition.appear,Condition.visible);
        return this;
    }

    @Step("Add recipient")
    public SendTextMessageUserPage addRecipient(String recipientName){
        getInputRecipientNumber().setValue(recipientName);
        waitUntilAlertDisappear();
        getButtonAddRecipient().click();
        return this;
    }

    @Step("Fill in SMS text")
    public SendTextMessageUserPage enterSmsText(String smsText){
        getInputSmsTextArea().setValue(smsText);
        return this;
    }

    @Step("Click Send SMS")
    public SmsConfirmationPopup clickSend(){
        getButtonSend().click();
        waitUntilAlertDisappear();
        return new SmsConfirmationPopup();
    }

    @Step("Add recipient from address book")
    public SendTextMessageUserPage selectNumberFromAddressBook(String num){
        getDropdownAddressBook().selectOptionByValue(num);
        return this;
    }

    @Step("Select sender")
    public SendTextMessageUserPage selectSenderNumber(String senderNumber){
        getDropdownSenderName().selectOptionByValue(senderNumber);
        return this;
    }




}
