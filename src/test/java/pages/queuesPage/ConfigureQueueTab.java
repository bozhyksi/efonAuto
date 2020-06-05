package pages.queuesPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;
import pages.queuesPage.queuePagePopups.CreateNewQueuePopup;
import pages.queuesPage.queuePagePopups.QueueForAgentsPopup;
import tests.queuesPageTest.queueTestData.Queue;

import static com.codeborne.selenide.Condition.exist;
import static core.configuration.preparations.eFonApp.confirmationPopup;
import static core.configuration.preparations.eFonApp.excelFileWorker;
import static pages.basePage.BasePage.MenuTabsBasePage.CONFIGURE_QUEUES;
import static pages.basePage.BasePage.MenuTabsBasePage.QUEUES;

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
    private String fieldByTextXpath = "//table//td[contains(text(),\"%s\")]";
    private String buttonEditAgentsQueueByTextXpath = "//table//td[text()=\"%s\"][1]/ancestor::tr//a[@id=\"editAgentsQueue\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getFieldReporterByText(String text) {
        return field(String.format(fieldByTextXpath,text));
    }

    public SelenideElement getFieldManagerByText(String text) {
        return field(String.format(fieldByTextXpath,text));
    }

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

    @Step("Click Edit Queue button")
    public CreateNewQueuePopup clickEditQueueButton(Queue queue){
        getButtonEditQueueByName(queue.getName()).click();
        waitUntilAlertDisappear();
        return new CreateNewQueuePopup();
    }

    @Step("Create new Queue(only required fields)")
    public ConfigureQueueTab createQueue(Queue queue){
        goToMenuTab(QUEUES)
                .goToMenuTab(CONFIGURE_QUEUES);
        clickCreateNewQueue()
                .setQueueName(queue.getName())
                .setSubscription()
                .saveChanges()
                .verifyIfQueueExistsInTheList(queue.getName());
        return this;
    }

    @Step("Open Queue agent popup")
    public QueueForAgentsPopup openQueueAgentPopup(Queue queue){
        getButtonEditAgentsQueueByText(queue.getName()).click();
        waitUntilAlertDisappear();
        return new QueueForAgentsPopup();
    }

    @Step("Open Queue agent popup")
    public QueueForAgentsPopup openQueueAgentPopup(String queue){
        getButtonEditAgentsQueueByText(queue).click();
        waitUntilAlertDisappear();
        return new QueueForAgentsPopup();
    }

    @Step("Delete Queue")
    public ConfigureQueueTab deleteQueue(Queue ... queues){
        goToMenuTab(QUEUES)
                .goToMenuTab(CONFIGURE_QUEUES);
        for (Queue queue : queues) {
            getButtonDeleteQueueByName(queue.getName()).click();
            waitUntilAlertDisappear();
            confirmationPopup.getYesButton().click();
            waitUntilAlertDisappear();
            verifyIfQueueNotExistInList(queue.getName());
        }
        return this;
    }

    @Step("Verify Queue manager\reporter")
    public ConfigureQueueTab verifyQueueManagerReporterExist(String manager){
        boolean exist = getFieldManagerByText(manager).exists();
        Assert.assertTrue(exist,"User "+manager+" is not current queue manager");
        return this;
    }

}
