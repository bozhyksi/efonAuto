package pages.basePage;

import com.codeborne.selenide.Condition;
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
    private String linkUserXpath = "//a[@href=\"/portal/user\"]";
    private String linkNumbersXpath = "//a[@href=\"/portal/numbers\"]";
    private String linkSubscriptionsXpath = "//a[@href=\"/portal/abos\"]";
    private String linkLastCallsXpath = "//a[@href=\"/portal/last-calls\"]";
    private String linkFaxXpath = "//a[@href=\"/portal/fax\"]";
    private String linkIVRsXpath = "//a[@href=\"/portal/ivrs\"";
    private String linkAbbreviatedNumbersXpath = "//a[@href=\"/portal/internal-numbers\"]";
    private String linkCallPickUpsXpath = "//a[@href=\"/portal/call-intercept-groups\"]";
    private String linkFileManagementXpath = "//a[@href=\"/portal/file-management\"]";
    private String linkCallForwardingXpath = "href=\"/portal/call-forwarding\"";
    private String linkHuntGroupsXpath = "//a[@href=\"/portal/hunt-groups\"]";
    private String linkConferenceCallsXpath = "//a[@href=\"/portal/conference-calls\"]";
    private String linkQueuesXpath = "//a[@href=\"/portal/call-queues\"]";
    private String linkEndDevicesXpath = "//a[@href=\"/portal/end-devices\"]";
    private String linkRecordedCallsXpath = "//a[@href=\"/portal/recorded-calls\"]";
    private String linkContactDataXpath = "//a[@href=\"/portal/contact-data\"][text()='Contact data']";
    //</editor-fold>

    //<editor-fold desc="//-- BasePage get/set methods --//">

    public SelenideElement getAlertErrorMsg() {
        return field(alertErrorMsgXpath);
    }

    public SelenideElement getLinkContactData() {
        return field(linkContactDataXpath);
    }

    public SelenideElement getLinkRecordedCalls() {
        return field(linkRecordedCallsXpath);
    }

    public SelenideElement getLinkEndDevices() {
        return field(linkEndDevicesXpath);
    }

    public SelenideElement getLinkQueues() {
        return field(linkQueuesXpath);
    }

    public SelenideElement getLinkConferenceCalls() {
        return field(linkConferenceCallsXpath);
    }

    public SelenideElement getLinkHuntGroups() {
        return field(linkHuntGroupsXpath);
    }

    public SelenideElement getLinkCallForwarding() {
        return field(linkCallForwardingXpath);
    }

    public SelenideElement getLinkCallPickUps() {
        return field(linkCallPickUpsXpath);
    }

    public SelenideElement getLinkFileManagement() {
        return field(linkFileManagementXpath);
    }

    public SelenideElement getLinkAbbreviatedNumbers() {
        return field(linkAbbreviatedNumbersXpath);
    }

    public SelenideElement getLinkIVRs() {
        return field(linkIVRsXpath);
    }

    public SelenideElement getLinkFax() {
        return field(linkFaxXpath);
    }

    public SelenideElement getLinkLastCalls() {
        return field(linkLastCallsXpath);
    }

    public SelenideElement getLinkSubscriptions() {
        return field(linkSubscriptionsXpath);
    }

    public SelenideElement getLinkNumbers() {
        return field(linkNumbersXpath);
    }

    public SelenideElement getLinkUser() {
        return field(linkUserXpath);
    }

    public SelenideElement formAdministration() {
        return field(formAdministrationXpath);
    }

    public SelenideElement getButtonLogout() {
        return field(buttonLogoutXpath);
    }
    //</editor-fold>

    public void clickButtonLogout() {
        field(buttonLogoutXpath).click();
    }

    public void clickImgUpcBusiness() {
        field(imgUpcBusinessXpath).click();
    }

    public void clickLinkFeedback() {
        field(linkFeedbackXpath).click();
    }

    public void clickDropdownHelp() {
        field(dropdownHelpXpath).click();
        getAlertErrorMsg().shouldNotBe(Condition.visible);
    }

    public void clickDropdownHelpItemKnowledgeDatabase() {
        clickDropdownHelp();
        field(dropdownHelpItemKnowledgeDatabaseXpath).click();
    }

    public void clickDropdownHelpItemSupportRequest() {
        clickDropdownHelp();
        field(dropdownHelpItemSupportRequestXpath).click();
    }

    public void clickLinkUserName() {
        field(linkUserNameXpath).click();
    }

}
