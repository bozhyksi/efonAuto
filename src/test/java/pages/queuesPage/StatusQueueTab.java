package pages.queuesPage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class StatusQueueTab extends QueuesBasePage {
    //<editor-fold desc="Locators">
    public enum State{
        Pause,
        Login,
        Logout,
        Wait;
    }

    private String dropdownSearchXpath = "//h3[text()=\"Search\"]//following-sibling::div//select";
    private String inputSearchXpath = "//h3[text()=\"Search\"]//following-sibling::div//input";
    private String listAgentNameXpath = "(//table//tr/td)[1]";
    private String buttonPenaltyByNameXpath = "//td[1][contains(text(),\"%s\")]/following-sibling::td/div/a[id=\"changePenaltyStatus\"]";
    private String fieldPenaltyByNameXpath = "//td[1][contains(text(),\"%s\")]/following-sibling::td/div[1]";
    private String fieldStatusByNameXpath = "//td[1][contains(text(),\"%s\")]/following-sibling::td[2]";
    private String buttonChangeStateByNameXpath = "//td[1][contains(text(),\"%s\")]/following-sibling::td[3]//a[@id=\"changeStateStatus\"][contains(text(),\"%s\")]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getButtonChangeStateByName(String name, String state) {
        return field(String.format(buttonChangeStateByNameXpath, name, state));
    }

    public SelenideElement getFieldStatusByName(String name) {
        return field(String.format(fieldStatusByNameXpath, name));
    }

    public SelenideElement getFieldPenaltyBy(String name) {
        return field(String.format(fieldPenaltyByNameXpath,name));
    }

    public SelenideElement getButtonPenaltyByName(String name) {
        return field(String.format(buttonPenaltyByNameXpath, name));
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

    public void changeStatus(String agentName, State state){
        getButtonChangeStateByName(agentName,state.toString()).click();
    }
}
