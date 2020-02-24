package pages.basePage.basePopup;

import com.codeborne.selenide.SelenideElement;

public class ConfirmationPopup extends BasePopup {

    private String titleConfirmationPopupXpath = "//div[@class=\"modal-content\"]//h3";
    private String buttonDoNotSaveXpath = "//div[@class=\"modal-content\"]//button[text()='Do not save' or text()='Yes']";
    private String buttonContinueWorkingXpath = "//div[@class=\"modal-content\"]//button[text()='Continue working' or text()='No']";

    public SelenideElement getButtonContinueWorking() {
        return field(buttonContinueWorkingXpath);
    }

    public SelenideElement getButtonDoNotSave() {
        return field(buttonDoNotSaveXpath);
    }

    public SelenideElement getTitleConfirmationPopup() {
        return field(titleConfirmationPopupXpath);
    }

    public SelenideElement getYesButton(){
        return field(buttonDoNotSaveXpath);
    }

    public SelenideElement getNoButton(){
       return field(buttonContinueWorkingXpath);
    }
}
