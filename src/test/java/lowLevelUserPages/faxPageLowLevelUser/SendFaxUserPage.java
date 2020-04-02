package lowLevelUserPages.faxPageLowLevelUser;

import com.codeborne.selenide.SelenideElement;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;

public class SendFaxUserPage extends FaxesBaseUserPage {
    //<editor-fold desc="locators">
    private String dropdownOutgoingNumberXpath = "//select[@formcontrolname=\"outgoingNumber\"]";
    private String inputDestinationNumberXpath = "//input[@formcontrolname=\"destinationNumber\"]";
    private String inputFileTobeSentXapth = "//label[text()=\"File to be sent\"]//following-sibling::div//input";
    private String buttonSendXpath = "//button[text()=\"Send\"]";
    private String fieldRecipientByNumberXpath = "//tbody//td[contains(text(),\"%s\")]";
    private String fieldCreatedDateXpathXpath = "//tbody//td[4]";
    //</editor-fold>

    //<editor-fold desc="get\set">
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
    //</editor-fold>
}
