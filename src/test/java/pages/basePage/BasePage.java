package pages.basePage;

import com.codeborne.selenide.SelenideElement;
import core.fields.Fields;

public class BasePage extends Fields {

    //<editor-fold desc="//-- BasePage Locators --//">
    //Error alert
    private String alertErrorMsgXpath = "//div[@class=\"toast-top-right toast-container\"]";

    private String buttonLogoutXpath = "//span[text()='Logout']/parent::button";
    private String imgUpcBusinessXpath = "//a[@href=\"http://www.e-fon.ch\"]";
    private String linkFeedbackXpath = "//span[text()='Feedback']/parent::a";
    private String dropdownHelpXpath = "//*[@id=\"helpLabel\"]";
    private String dropdownHelpItemKnowledgeDatabaseXpath = "//*[@class=\"dropdown open\"]//a[text()='Knowledge database']";
    private String dropdownHelpItemSupportRequestXpath = "//*[@class=\"dropdown open\"]//a[text()='Support request']";
    private String linkUserNameXpath = "//div[@class=\"col-xs-7\"]//a[@href=\"/portal/contact-data\"]";

    //Administration panel
    private String formAdministrationXpath = "//main-menu//*[@id=\"navbar-collapse\"]";
    private String tabUserXpath = "//a[@href=\"/portal/user\"]";
    private String tabNumbersXpath = "//a[@href=\"/portal/numbers\"]";
    private String tabSubscriptionsXpath = "//a[@href=\"/portal/abos\"]";
    private String tabLastCallsXpath = "//a[@href=\"/portal/last-calls\"]";
    private String tabFaxXpath = "//a[@href=\"/portal/fax\"]";
    private String tabIVRsXpath = "//a[@href=\"/portal/ivrs\"";
    private String tabAbbreviatedNumbersXpath = "//a[@href=\"/portal/internal-numbers\"]";
    private String tabCallPickUpsXpath = "//a[@href=\"/portal/call-intercept-groups\"]";
    private String tabFileManagementXpath = "//a[@href=\"/portal/file-management\"]";
    private String tabCallForwardingXpath = "href=\"/portal/call-forwarding\"";
    private String tabHuntGroupsXpath = "//a[@href=\"/portal/hunt-groups\"]";
    private String tabConferenceCallsXpath = "//a[@href=\"/portal/conference-calls\"]";
    private String tabQueuesXpath = "//a[@href=\"/portal/call-queues\"]";
    private String tabEndDevicesXpath = "//a[@href=\"/portal/end-devices\"]";
    private String tabRecordedCallsXpath = "//a[@href=\"/portal/recorded-calls\"]";
    private String tabContactDataXpath = "//a[@href=\"/portal/contact-data\"][text()='Contact data']";
    //</editor-fold>

    //<editor-fold desc="//-- BasePage get/set methods --//">

    public SelenideElement getAlertErrorMsg() {
        return field(alertErrorMsgXpath);
    }

    public SelenideElement getTabContactData() {
        return field(tabContactDataXpath);
    }

    public SelenideElement getTabRecordedCalls() {
        return field(tabRecordedCallsXpath);
    }

    public SelenideElement getTabEndDevices() {
        return field(tabEndDevicesXpath);
    }

    public SelenideElement getTabQueues() {
        return field(tabQueuesXpath);
    }

    public SelenideElement getTabConferenceCalls() {
        return field(tabConferenceCallsXpath);
    }

    public SelenideElement getTabHuntGroups() {
        return field(tabHuntGroupsXpath);
    }

    public SelenideElement getTabCallForwarding() {
        return field(tabCallForwardingXpath);
    }

    public SelenideElement getTabCallPickUps() {
        return field(tabCallPickUpsXpath);
    }

    public SelenideElement getTabFileManagement() {
        return field(tabFileManagementXpath);
    }

    public SelenideElement getTabAbbreviatedNumbers() {
        return field(tabAbbreviatedNumbersXpath);
    }

    public SelenideElement getTabIVRs() {
        return field(tabIVRsXpath);
    }

    public SelenideElement getTabFax() {
        return field(tabFaxXpath);
    }

    public SelenideElement getTabLastCalls() {
        return field(tabLastCallsXpath);
    }

    public SelenideElement getTabSubscriptions() {
        return field(tabSubscriptionsXpath);
    }

    public SelenideElement getTabNumbers() {
        return field(tabNumbersXpath);
    }

    public SelenideElement getTabUser() {
        return field(tabUserXpath);
    }

    public SelenideElement formAdministration() {
        return field(formAdministrationXpath);
    }

    public SelenideElement getButtonLogout() {
        return field(buttonLogoutXpath);
    }
    //</editor-fold>

}
