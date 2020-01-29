package pages.basePage.basePopup;

import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;

public class ConfirmationPopup extends BasePage {
    private String titleConfirmationPopupXpath = "//div[@class=\"modal-content\"]//h3";
    private String buttonDoNotSaveXpath = "//div[@class=\"modal-content\"]//button[text()='Do not save']";
    private String buttonContinueWorkingXpath = "//div[@class=\"modal-content\"]//button[text()='Continue working']";

    public SelenideElement getButtonContinueWorking() {
        return field(buttonContinueWorkingXpath);
    }

    public SelenideElement getButtonDoNotSave() {
        return field(buttonDoNotSaveXpath);
    }

    public SelenideElement getTitleConfirmationPopup() {
        return field(titleConfirmationPopupXpath);
    }
}
