package lowLevelUserPages.dashboardPageLowLevelUser;

import com.codeborne.selenide.SelenideElement;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;

import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.MenuTabsLowLevelUser.DASHBOARD;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.PageTitles.SEND_SMS;

public class DashboardUserPage extends BasePageLowLevelUser {
    public enum WidgetButtons{
        SHOW_ALL_CALLS("Show all calls"),
        SHOW_ALL_FAXES("Show all faxes"),
        ALTER_CALL_FORWARDING("Alter call forwarding"),
        SHOW_ALL_VOICEMAIL_MESSAGES("Show all voicemail messages"),
        SHOW_QUEUE_STATUS("Show queue status"),
        SEND_TEXT_MESSAGE("Send text message"),
        MY_END_DEVICES("My end devices"),
        ADDRESS_BOOK("Address book");


        private String buttonName;

        WidgetButtons(String buttonName){
            this.buttonName = buttonName;
        }

        public String getButtonName() {
            return buttonName;
        }
    }

    private String widgetButtonByName = "//widget//button[text()=\"%s\"] | //widget//a[text()=\"%s\"]";

    public SelenideElement getWidgetButtonByName(WidgetButtons button) {
        return field(String.format(widgetButtonByName, button.getButtonName(), button.getButtonName()));
    }

    public void checkWidgetButton(WidgetButtons button, PageTitles expectedPageTitle){
        getWidgetButtonByName(button).click();
        waitUntilAlertDisappear();
        validatePageTitle(expectedPageTitle);
        goToMenuTab(DASHBOARD);
        if(expectedPageTitle == SEND_SMS) goToMenuTab(DASHBOARD);
        waitUntilAlertDisappear();
    }

}
