package pages.queuesPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import tests.queuesPageTest.queueTestData.Queue;
import tests.userPageTests.userPageTestData.User;

import static com.codeborne.selenide.Condition.exist;
import static io.qameta.allure.Allure.step;


public class RecordingsQueueTab extends QueuesBasePage {
    //<editor-fold desc="locators">
    private String dropdownQueueDisplayNameXpath = "//label[text()=\"Queue display name\"]/..//select";
    private String inputCallerXpath = "//label[text()=\"Caller\"]/following-sibling::div[1]/input";
    private String dropdownAgentXpath = "//label[text()=\"Agent\"]/..//select";
    private String inputFromXpath = "//label[text()=\"From\"]/following-sibling::div[1]/input";
    private String inputToXpath = "//label[text()=\"To\"]/following-sibling::div[1]/input";
    private String buttonSearchXpath = "//button[text()=\"Search\"]";
    private String fieldByNameXpath = "//td[contains(text(),\"%s\")]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getDropdownQueueDisplayName() {
        return field(dropdownQueueDisplayNameXpath);
    }

    public SelenideElement getInputCaller() {
        return field(inputCallerXpath);
    }

    public SelenideElement getDropdownAgent() {
        return field(dropdownAgentXpath);
    }

    public SelenideElement getInputFrom() {
        return field(inputFromXpath);
    }

    public SelenideElement getInputTo() {
        return field(inputToXpath);
    }

    public SelenideElement getButtonSearch() {
        return field(buttonSearchXpath);
    }

    public SelenideElement getFieldByName(String text) {
        return field(String.format(fieldByNameXpath, text));
    }
    //</editor-fold>

    @Step("Select created queue in the dropdown")
    public RecordingsQueueTab selectQueue(Queue queue){
        getDropdownQueueDisplayName().selectOptionContainingText(queue.getName());
        return this;
    }

    @Step("Select agent")
    public RecordingsQueueTab selectAgent(User user){
        getDropdownAgent().selectOptionContainingText(user.getLastName());
        return this;
    }

    @Step("Enter data")
    public RecordingsQueueTab enterDate(String fromDate, String toDate){
        getInputFrom().setValue(fromDate).pressTab();
        getInputTo().setValue(toDate).pressTab();
        return this;
    }

    @Step("Click search")
    public RecordingsQueueTab clickSearch(){
        getButtonSearch().click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Verify search results")
    public RecordingsQueueTab verifySearchResults(){
        getFieldByName("No Items").should(exist);
        return this;
    }

}
