package lowLevelUserPages.faxPageLowLevelUser;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import testsLowLevelUser.faxUserPageTests.faxUserPageTestData.SendFaxTestData;

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

    @Step("Upload sample file")
    public SendFaxUserPage uploadFaxFile(String filePath){
        getInputFileTobeSent().uploadFile(new File(filePath));
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Validate destination number")
    public SendFaxUserPage validateDestinationNumber(String ... inputs){
        for (String input : inputs) {
            getInputDestinationNumber().clear();
            getInputDestinationNumber().setValue(input);
            getInputDestinationNumber().pressTab();
            getFieldInputRequiredDestNumber().should(Condition.appear,Condition.visible,Condition.exist);
        }
        return this;
    }

    @Step("Verify if all customer numbers are available in Outgoing number dropdown")
    public SendFaxUserPage verifyIfAllCustomerNumbersAreAvailableInOutgoingNumberDropdown(){
        int customerNumbersQuantity = super.getAllCustomerNumbersFromDB().size();
        int outgoingNumbersQuantity = new Select(getDropdownOutgoingNumber()).getOptions().size();
        Assert.assertEquals(outgoingNumbersQuantity,customerNumbersQuantity+2,
                "Customer has "+customerNumbersQuantity+" phone numbers but "+outgoingNumbersQuantity+" are available!");
        return this;
    }

    @Step("Select Outgoing number")
    public SendFaxUserPage selectOutGoingNumber(String outGoingNum){
        getDropdownOutgoingNumber().selectOptionContainingText(outGoingNum);
        return this;
    }

    @Step("Fill in Destination number")
    public SendFaxUserPage enterDestinationNumber(String destNum){
        getInputDestinationNumber().setValue(destNum);
        return this;
    }

    @Step("Click Send button")
    public SendFaxUserPage clickSend(){
        getButtonSend().shouldBe(Condition.enabled).click();
        return this;
    }

    @Step("Validate if fax appeared in the grid as sent")
    public SendFaxUserPage verifyIfFaxSent(SendFaxTestData fax){
        getFieldRecipientByNumber(fax.getDestinationNumber()).shouldBe(Condition.visible, Condition.exist);
        getSenderByText(fax.getOutgoingNumber()).shouldBe(Condition.visible, Condition.exist);
        return this;
    }

}
