package pages.huntGroupPage.huntGroupPopup;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.userPage.userPagePopup.CreateUserPopup;

public class AddFullDaysPopup extends CreateUserPopup {
    //<editor-fold desc="Locators">
    private String buttonAddNewStepXpath = "//div[@id=\"systemModal\"]//button[text()=\"+ Add new step\"]";
    private String buttonCancelXpath = "//div[@id=\"systemModal\"]//button[text()=\"Cancel\"]";
    private String inputFullDayXpath = "//div[@id=\"systemModal\"]//input[@formcontrolname=\"timerQueueName\"]";
    private String inputDatesXpath = "//div[@id=\"systemModal\"]//input[@formcontrolname=\"dates\"]";
    private String dropdownListAccountSelectXpath = "//div[@id=\"systemModal\"]//select[@id=\"accountSelect\"]";
    private String dropdownListNumbersXpath = "//div[@id=\"systemModal\"]//input[@id=\"numberSelection\"]";
    private String buttonListDeleteLevelXpath = "//div[@id=\"systemModal\"]//a[@id=\"deleteQueueAction\"]";
    private String buttonCloseXpath = "//div[@id=\"systemModal\"]//button[@aria-label=\"Close\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getButtonAddNewStep() {
        return field(buttonAddNewStepXpath);
    }

    public SelenideElement getButtonCancel() {
        return field(buttonCancelXpath);
    }

    public SelenideElement getInputFullDay() {
        return field(inputFullDayXpath);
    }

    public SelenideElement getInputDates() {
        return field(inputDatesXpath);
    }

    public ElementsCollection getDropdownListAccountSelect() {
        return fields(dropdownListAccountSelectXpath);
    }

    public ElementsCollection getDropdownListNumbers() {
        return fields(dropdownListNumbersXpath);
    }

    public ElementsCollection getButtonListDeleteLevel() {
        return fields(buttonListDeleteLevelXpath);
    }

    public SelenideElement getButtonClose() {
        return field(buttonCloseXpath);
    }
    //</editor-fold>
}
