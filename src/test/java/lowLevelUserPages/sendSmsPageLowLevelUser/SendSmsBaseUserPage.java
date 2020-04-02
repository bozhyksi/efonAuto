package lowLevelUserPages.sendSmsPageLowLevelUser;

import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;

public class SendSmsBaseUserPage extends BasePageLowLevelUser {
    private String tabSendTextMessageXpath = "//a[contains(@href,\"/sms/sms-send\")]";
    private String tabAddressBookXpath = "//a[contains(@href,\"/sms/sms-addressbook\")]";

}
