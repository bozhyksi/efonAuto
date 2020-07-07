package lowLevelUserPages.sendSmsPageLowLevelUser.sendSmsUserPagePopup;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lowLevelUserPages.sendSmsPageLowLevelUser.SendTextMessageUserPage;

public class SmsConfirmationPopup extends SendTextMessageUserPage {
    //<editor-fold desc="locators">
    private String titleXpath = "//div[@id=\"systemModal\"]//div[contains(text(),\"were sent successfully\")]";
    private String buttonCloseXpath = "//div[@id=\"systemModal\"]//button[text()=\"Close\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getTitle() {
        return field(titleXpath);
    }

    public SelenideElement getButtonClose() {
        return field(buttonCloseXpath);
    }
    //</editor-fold>

    @Step("Check if SMS were sent and \"Confirmation popup appears\"")
    public SendTextMessageUserPage verifyIfSmsSent() {
        getTitle().shouldBe(Condition.visible, Condition.appear);
        getButtonClose().click();
        return this;
    }
}
