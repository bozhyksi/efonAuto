package lowLevelUserPages.sendSmsPageLowLevelUser;

import com.codeborne.selenide.SelenideElement;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;

public class SendSmsBaseUserPage extends BasePageLowLevelUser {
    private String tabSendTextMessageXpath = "//a[contains(@href,\"/sms/sms-send\")]";
    private String tabAddressBookXpath = "//a[contains(@href,\"/sms/sms-addressbook\")]";

    public SelenideElement getTabSendTextMessage() {
        return field(tabSendTextMessageXpath);
    }

    public SelenideElement getTabAddressBook() {
        return field(tabAddressBookXpath);
    }
}
