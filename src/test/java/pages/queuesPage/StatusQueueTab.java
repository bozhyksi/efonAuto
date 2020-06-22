package pages.queuesPage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.queuesPage.queuePagePopups.PenaltyPopup;
import tests.queuesPageTest.queueTestData.Queue;
import tests.userPageTests.userPageTestData.User;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;

public class StatusQueueTab extends QueuesBasePage {
    public enum ChangeState{
        Pause("Pause"),
        Login("Login"),
        Logout("Logout"),
        Wait("Wait"),
        End_Wait("End wait"),
        End_Pause("End pause");

        private String status;

        ChangeState (String status){
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }

    //<editor-fold desc="Locators">
    private String dropdownSearchXpath = "//h3[text()=\"Search\"]//following-sibling::div//select";
    private String inputSearchXpath = "//h3[text()=\"Search\"]//following-sibling::div//input";
    private String listAgentNameXpath = "(//table//tr/td)[1]";
    private String fieldPenaltyByNameXpath = "//td/div[contains(text(),\"%s\")]";
    private String fieldStatusByNameXpath = "//td[1][contains(text(),\"%s\")]/following-sibling::td[2]";
    private String buttonChangeStateByNameXpath = "//td[1][contains(text(),\"%s\")]/following-sibling::td[3]//a[@id=\"changeStateStatus\"][contains(text(),\"%s\")]";
    private String fieldByNameXpath = "//td[contains(text(),\"%s\")]";
    private String buttonEditByNameXpath = "//td[contains(text(),\"%s\")]/..//a[@id=\"changePenaltyStatus\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">

    public SelenideElement getFieldAgentByText(String text) {
        return field(String.format(fieldByNameXpath,text));
    }

    public SelenideElement getButtonChangeStateByName(String name, String state) {
        return field(String.format(buttonChangeStateByNameXpath, name, state));
    }

    public SelenideElement getFieldStatusByName(String name) {
        return field(String.format(fieldStatusByNameXpath, name));
    }

    public SelenideElement getFieldPenaltyByText(String name) {
        return field(String.format(fieldPenaltyByNameXpath,name));
    }

    public SelenideElement getButtonEditByName(String name) {
        return field(String.format(buttonEditByNameXpath, name));
    }

    public ElementsCollection getListAgentName() {
        return fields(listAgentNameXpath);
    }

    public SelenideElement getInputSearch() {
        return field(inputSearchXpath);
    }

    public SelenideElement getDropdownSearch() {
        return field(dropdownSearchXpath);
    }
    //</editor-fold>

    @Step("Change and verify agent statuses")
    public StatusQueueTab changeStatus(String agentName, ChangeState state){
        getButtonChangeStateByName(agentName,state.getStatus()).click();
        waitUntilAlertDisappear();
        getFieldStatusByName(agentName).shouldHave(text(getCurrentAgentStatus(state)));
        return this;
    }

    private String getCurrentAgentStatus(ChangeState state){
        switch (state){
            case Login: return "Online";
            case Wait: return "Wait";
            case Pause: return "Paused";
            case End_Wait:return "Online";
            case Logout: return "Offline";
            case End_Pause:return "Online";
            default:return "";
        }
    }

    @Step("Select Queue from drop-down")
    public StatusQueueTab selectQueue(Queue queue){
        getDropdownSearch().selectOptionContainingText(queue.getName());
        return this;
    }

    @Step("Click edit penalty. Open Penalty popup")
    public PenaltyPopup clickEditPenalty(User user){
        getButtonEditByName(user.getFirstName()).click();
        waitUntilAlertDisappear();
        return new PenaltyPopup();
    }

    @Step("Verify penalty value")
    public StatusQueueTab verifyPenalty(String penalty){
        getFieldPenaltyByText(penalty).should(exist);
        return this;
    }
}
