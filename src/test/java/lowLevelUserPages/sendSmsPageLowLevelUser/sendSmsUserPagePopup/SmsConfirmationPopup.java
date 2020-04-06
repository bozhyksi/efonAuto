package lowLevelUserPages.sendSmsPageLowLevelUser.sendSmsUserPagePopup;

import com.codeborne.selenide.SelenideElement;
import lowLevelUserPages.sendSmsPageLowLevelUser.SendTextMessageUserPage;

public class SmsConfirmationPopup extends SendTextMessageUserPage {
    private String titleXpath = "//div[@id=\"systemModal\"]//div[contains(text(),\"were sent successfully\")]";
    private String buttonCloseXpath = "//div[@id=\"systemModal\"]//button[text()=\"Close\"]";

    public SelenideElement getTitle() {
        return field(titleXpath);
    }

    public SelenideElement getButtonClose() {
        return field(buttonCloseXpath);
    }
}
