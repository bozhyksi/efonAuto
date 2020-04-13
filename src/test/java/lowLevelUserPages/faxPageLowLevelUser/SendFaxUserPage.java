package lowLevelUserPages.faxPageLowLevelUser;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;

import java.io.File;

public class SendFaxUserPage extends FaxesBaseUserPage {
    //<editor-fold desc="locators">
    private String dropdownOutgoingNumberXpath = "//select[@formcontrolname=\"outgoingNumber\"]";
    private String inputDestinationNumberXpath = "//input[@formcontrolname=\"destinationNumber\"]";
    private String inputFileTobeSentXapth = "//label[text()=\"File to be sent\"]//following-sibling::div//input";
    private String buttonSendXpath = "//button[text()=\"Send\"]";
    private String fieldRecipientByNumberXpath = "//tbody//td[contains(text(),\"%s\")]";
    private String fieldCreatedDateXpathXpath = "//tbody//td[4]";
    private String fieldInputRequiredDestNumberXpath = "//input[@formcontrolname=\"destinationNumber\"]//following-sibling::span[text()=\"Input required\" or text()=\"Input doesn't match pattern\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">

    public SelenideElement getFieldInputRequiredDestNumber() {
        return field(fieldInputRequiredDestNumberXpath);
    }

    public SelenideElement getDropdownOutgoingNumber() {
        return field(dropdownOutgoingNumberXpath);
    }

    public SelenideElement getInputDestinationNumber() {
        return field(inputDestinationNumberXpath);
    }

    public SelenideElement getInputFileTobeSent() {
        return field(inputFileTobeSentXapth);
    }

    public SelenideElement getButtonSend() {
        return field(buttonSendXpath);
    }

    public SelenideElement getFieldRecipientByNumber(String number) {
        return field(String.format(fieldRecipientByNumberXpath, number));
    }

    public SelenideElement getFieldCreatedDate() {
        return field(fieldCreatedDateXpathXpath);
    }

    public SelenideElement getSenderByText(String text){
        return field(String.format(fieldRecipientByNumberXpath,text));
    }
    //</editor-fold>

    public void uploadFaxFile(String filePath){
        getInputFileTobeSent().uploadFile(new File(filePath));
        waitUntilAlertDisappear();
    }

    public void validateDestinationNumber(String ... inputs){
        for (String input : inputs) {
            getInputDestinationNumber().clear();
            getInputDestinationNumber().setValue(input);
            getInputDestinationNumber().pressTab();
            getFieldInputRequiredDestNumber().should(Condition.appear,Condition.visible,Condition.exist);
        }

    }
}
