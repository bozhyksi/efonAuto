package pages.queuesPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.queuesPage.queuePagePopups.CreateNewQueuePopup;
import tests.queuesPageTest.queueTestData.Queue;

import static com.codeborne.selenide.Condition.exist;

public class ConfigureQueueTab extends QueuesBasePage {
    //<editor-fold desc="Locators">
    private String dropdownAbbrevDialingXpath = "//select[@formcontrolname=\"abbreviatedDial\"]";
    private String buttonEditAbbrevDialXpath = "//abbreviated-dialling-queue//i[@id=\"abbreviatedDiallingConfig\"]";
    private String buttonSaveAbbrevDialXpath = "//abbreviated-dialling-queue//button[text()='Save']";
    private String buttonCancelAbbrevDialXpath = "//abbreviated-dialling-queue//button[text()='Cancel']";
    private String buttonCreateNewQueueXpath = "//a[text()=\"Create new\"]";
    private String buttonDeleteQueueByNameXpath = "//table//td[text()=\"%s\"][1]/ancestor::tr//a[@id=\"deleteQueue\"]";
    private String buttonEditQueueByNameXpath = "//table//td[text()=\"%s\"][1]/ancestor::tr//a[@id=\"editQueue\"]";
    private String fieldQueueNameByTextXpath = "//table//td[text()=\"%s\"][1]";
    private String fieldQueueManagerByTextXpath = "//table//td[text()=\"%s\"][4]";
    private String buttonEditAgentsQueueByTextXpath = "//table//td[text()=\"%s\"][1]/ancestor::tr//a[@id=\"editAgentsQueue\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getButtonDeleteQueueByName(String name) {
        return field(String.format(buttonDeleteQueueByNameXpath,name));
    }

    public SelenideElement getDropdownAbbrevDialing() {
        return field(dropdownAbbrevDialingXpath);
    }

    public SelenideElement getButtonEditAbbrevDial() {
        return field(buttonEditAbbrevDialXpath);
    }

    public SelenideElement getButtonSaveAbbrevDial() {
        return field(buttonSaveAbbrevDialXpath);
    }

    public SelenideElement getButtonCancelAbbrevDial() {
        return field(buttonCancelAbbrevDialXpath);
    }

    public SelenideElement getButtonCreateNewQueue() {
        return field(buttonCreateNewQueueXpath);
    }

    public SelenideElement getButtonEditQueueByName(String name) {
        return field(String.format(buttonEditQueueByNameXpath, name));
    }

    public SelenideElement getFieldQueueNameByText(String txt) {
        return field(String.format(fieldQueueNameByTextXpath,txt));
    }

    public SelenideElement getFieldQueueManagerByText(String txt) {
        return field(String.format(fieldQueueManagerByTextXpath,txt));
    }

    public SelenideElement getButtonEditAgentsQueueByText(String name) {
        return field(String.format(buttonEditAgentsQueueByTextXpath,name));
    }
    //</editor-fold>

    @Step("Click create new Queue")
    public CreateNewQueuePopup clickCreateNewQueue(){
        getButtonCreateNewQueue().click();
        waitUntilAlertDisappear();
        return new CreateNewQueuePopup();
    }

    @Step("Verify if Queue exists in the list")
    public ConfigureQueueTab verifyIfQueueExistsInTheList(String queueName){
        getFieldQueueNameByText(queueName).should(exist);
        return this;
    }

    @Step("Verify if Queue does NOT exists in the list")
    public ConfigureQueueTab verifyIfQueueNotExistInList(String queueName){
        getFieldQueueNameByText(queueName).shouldNot(exist);
        return this;
    }

    @Step("Click Edit Queue button")
    public CreateNewQueuePopup clickEditQueueButton(String queueName){
        getButtonEditQueueByName(queueName).click();
        waitUntilAlertDisappear();
        return new CreateNewQueuePopup();
    }

}
