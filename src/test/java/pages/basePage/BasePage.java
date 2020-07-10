package pages.basePage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import core.fields.Fields;
import flow.BaseTestMethods;
import io.qameta.allure.Step;
import pages.basePage.basePopup.BasePopup;
import pages.loginPage.LoginPage;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class BasePage extends Fields{

    public static AtomicInteger index = new AtomicInteger(1);

    public enum MenuTabsBasePage{
        USER,
        NUMBERS,
        SUBSCRIPTIONS,
        PROVISIONING,
        PROVISIONING_END_DEVICES,
        PROVISIONING_PHONE_MODELS,
        PROVISIONING_MANAGER,
        LAST_CALLS,
        LAST_CALLS_MISSED,
        LAST_CALLS_INCOMING,
        LAST_CALLS_OUTGOING,
        FAX,
        IVRs,
        ABBREVIATED_DIALING,
        ABBREVIATED_NUMBERS,
        CALL_PICKUPs,
        FILE_MANAGEMENT,
        CALL_FORWARDING,
        HUNT_GROUPS,
        CONFERENCE_CALLS,
        QUEUES,
        END_DEVICES,
        RECORDED_CALLs,
        RECORDED_CALLs_CONFIGURATIONS,
        RECORDED_CALLs_OVERVIEW,
        PHONEBOOK,
        CONTACT_DATA,
        MANAGE_ABBREVIATED_NUMBERS,
        ANNOUNCEMENT_DISPLAY,
        MUSIC_ON_HOLD,
        CONFIGURE_QUEUES,
        STATUS_QUEUES,
        RECORDINGS_QUEUES,
        REPORT_QUEUES,
    }

    public enum ItemsPerPage{
        _10("10"),
        _25("25"),
        _50("50"),
        _100("100"),
        _All("-1");

        private String items;

        ItemsPerPage(String items){
            this.items = items;
        }

        public String getItems() {
            return items;
        }
    }

    //<editor-fold desc="//-- BasePage Locators --//">
    private String pageTitleXpath = "//h1";
    private String alertDialogXpath = "//div[@role=\"alertdialog\"]";

    //Error alert
    private String alertErrorMsgXpath = "//div[@class=\"toast-top-right toast-container\"]";

    private String buttonLogoutXpath = "//span[text()='Logout']/parent::button";
    private String imgUpcBusinessXpath = "//a[@href=\"http://www.e-fon.ch\"]";
    private String linkFeedbackXpath = "//span[text()='Feedback']/parent::a";
    private String dropdownHelpXpath = "//*[@id=\"helpLabel\"]";
    private String dropdownHelpItemKnowledgeDatabaseXpath = "//*[@class=\"dropdown open\"]//a[text()='Knowledge database']";
    private String dropdownHelpItemSupportRequestXpath = "//*[@class=\"dropdown open\"]//a[text()='Support request']";
    private String linkUserNameXpath = "//div[@class=\"col-xs-7\"]//a[@href=\"/portal/contact-data\"]";
    private String dropdownItemsPerPageXpath = "//span[text()=\"Items per page\"]/following-sibling::select";
    private String isLoadingSpinnerXpath = "//ng2-loading-spinner";

    //<editor-fold desc="ADMIN MENU">
    private final String formAdministrationXpath = "//main-menu//*[@id=\"navbar-collapse\"]";
    private final String tabUserXpath = "//a[@id=\"menu-11\"]";
    private final String tabNumbersXpath = "//a[@id=\"menu-12\"]";
    private final String tabSubscriptionsXpath = "//a[@id=\"menu-14\"]";
    private final String tabLastCallsXpath = "//a[@id=\"menu-15\"]";
    private final String tabLastCallsMissedXpath = "//a[contains(@href,\"/last-calls/missed\")]";
    private final String tabLastCallsIncomingXpath = "//a[contains(@href,\"last-calls/incoming\")]";
    private final String tabLastCallsOutgoingXpath = "//a[contains(@href,\"last-calls/outgoing\")]";
    private final String tabFaxXpath = "//a[@id=\"menu-16\"]";
    private final String tabIVRsXpath = "//a[@id=\"menu-17\"]";
    private final String tabAbbreviatedNumbersXpath = "//a[@id=\"menu-18\"]";
    private final String tabCallPickUpsXpath = "//a[@id=\"menu-19\"]";
    private final String tabFileManagementXpath = "//a[@id=\"menu-20\"]";
    private final String tabCallForwardingXpath = "//a[@id=\"menu-21\"]";
    private final String tabHuntGroupsXpath = "//a[@id=\"menu-24\"]";
    private final String tabConferenceCallsXpath = "//a[@id=\"menu-25\"]";
    private final String tabQueuesXpath = "//a[@id=\"menu-26\"]";
    private final String tabEndDevicesXpath = "//a[@id=\"menu-29\"]";
    private final String tabRecordedCallsXpath = "//a[@id=\"menu-31\"]";
    private final String tabContactDataXpath = "//a[@id=\"menu-33\"]";
    private final String tabPhonebookXpath = "//a[@id=\"menu-32\"]";
    private final String tabManageAbbreviatedNumbersXpath = "//a[contains(@href,\"/internal-numbers/manage\")]";
    private final String tabAnnouncementDisplayXpath = "//a[contains(@href,\"file-management/announcement-display\")]";
    private final String tabMusicOnHoldXpath = "//a[contains(@href,\"/file-management/music-on-hold\")]";
    private final String tabConfigureQueuesXpath = "//a[contains(@href,\"/call-queues/overview\")]";
    private final String tabStatusQueuesXpath = "//a[contains(@href,\"/call-queues/status\")]";
    private final String tabRecordingsXpath = "//a[contains(@href,\"/call-queues/records\")]";
    private final String tabReportXpath = "//a[contains(@href,\"/call-queues/reports\")]";
    private final String tabRecCallsOverviewXpath = "//a[contains(@href,\"recorded-calls/overview\")]";
    private final String tabRecCallsConfigurationsXpath = "//a[contains(@href,\"recorded-calls/configuration\")]";
    private final String tabAbbreviatedNumberXpath = "//a[contains(@href,\"/internal-numbers/overview\")]";
    private final String tabProvisioningXpath = "//a[@id=\"menu-13\"]";
    private final String tabProvisioningEndDevicesXpath = "//a[contains(@href,\"/provisioning/end-devices\") or contains(@href,\"/provisioning/(end-devices)\")]";
    private final String tabProvisioningPhoneModelsXpath = "//a[contains(@href,\"/provisioning/phone-models\") or contains(@href,\"/provisioning/(phone-models)\")]";
    private final String tabProvisioningManagerXpath = "//a[contains(@href,\"/provisioning-manager\")]";
    //</editor-fold>

    //Save button
    private String buttonSaveXpath = "//button[text()=\"Save\"]";


    //</editor-fold>

    //<editor-fold desc="//-- BasePage get/set methods --//">


    public SelenideElement getTabLastCallsOutgoing() {
        return field(tabLastCallsOutgoingXpath);
    }

    public SelenideElement getTabLastCallsIncoming() {
        return field(tabLastCallsIncomingXpath);
    }

    public SelenideElement getTabLastCallsMissed() {
        return field(tabLastCallsMissedXpath);
    }

    public SelenideElement getTabProvisioningEndDevices() {
        return field(tabProvisioningEndDevicesXpath);
    }

    public SelenideElement getTabProvisioningPhoneModels() {
        return field(tabProvisioningPhoneModelsXpath);
    }

    public SelenideElement getTabProvisioningManager() {
        return field(tabProvisioningManagerXpath);
    }

    public SelenideElement getTabProvisioning() {
        return field(tabProvisioningXpath);
    }

    public SelenideElement getTabAbbreviatedNumber() {
        return field(tabAbbreviatedNumberXpath);
    }

    public SelenideElement getTabRecCallsOverview() {
        return field(tabRecCallsOverviewXpath);
    }

    public SelenideElement getTabRecCallsConfigurations() {
        return field(tabRecCallsConfigurationsXpath);
    }

    public SelenideElement getLinkFeedback() {
        return field(linkFeedbackXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getTabConfigureQueues() {
        return field(tabConfigureQueuesXpath);
    }

    public SelenideElement getTabStatusQueues() {
        return field(tabStatusQueuesXpath);
    }

    public SelenideElement getTabReport() {
        return field(tabReportXpath);
    }

    public SelenideElement getTabRecordings() {
        return field(tabRecordingsXpath);
    }

    public SelenideElement getTabAnnouncementDisplay() {
        return field(tabAnnouncementDisplayXpath);
    }

    public SelenideElement getTabMusicOnHold() {
        return field(tabMusicOnHoldXpath);
    }

    public SelenideElement getTabManageAbbreviatedNumbers() {
        return field(tabManageAbbreviatedNumbersXpath);
    }

    public SelenideElement getAlertDialog() {
        return field(alertDialogXpath);
    }

    public SelenideElement getPageTitle() {
        return field(pageTitleXpath);
    }

    public SelenideElement getIsLoadingSpinner() {
        return field(isLoadingSpinnerXpath);
    }

    public SelenideElement getDropdownItemsPerPage() {
        return field(dropdownItemsPerPageXpath);
    }

    public SelenideElement getTabPhonebook() {
        return field(tabPhonebookXpath);
    }

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

    public SelenideElement getTabAbbreviatedDialling() {
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

    public void waitUntilAlertDisappear() {
        Selenide.sleep(200);
        getIsLoadingSpinner().waitUntil(Condition.disappear, 10000);
        getIsLoadingSpinner().shouldNotBe(Condition.visible);
        getAlertDialog().waitUntil(Condition.disappear, 10000);
        getAlertDialog().shouldNotBe(Condition.visible);
        Selenide.sleep(500);
    }

    public void refreshPage() {
        Selenide.refresh();
        waitUntilAlertDisappear();
    }

    public BasePage goToMenuTab(MenuTabsBasePage tabName){
        getMenuTab(tabName).click();
        waitUntilAlertDisappear();
        return this;
    }

    private SelenideElement getMenuTab(MenuTabsBasePage tabName){
        switch (tabName){
            case LAST_CALLS_MISSED:return getTabLastCallsMissed();
            case LAST_CALLS_INCOMING:return getTabLastCallsIncoming();
            case LAST_CALLS_OUTGOING:return getTabLastCallsOutgoing();
            case FAX: return getTabFax();
            case IVRs: return getTabIVRs();
            case USER: return getTabUser();
            case QUEUES: return getTabQueues();
            case NUMBERS: return getTabNumbers();
            case PHONEBOOK: return getTabPhonebook();
            case LAST_CALLS: return getTabLastCalls();
            case END_DEVICES: return getTabEndDevices();
            case HUNT_GROUPS: return  getTabHuntGroups();
            case CALL_PICKUPs: return getTabCallPickUps();
            case CONTACT_DATA: return getTabContactData();
            case SUBSCRIPTIONS: return getTabSubscriptions();
            case RECORDED_CALLs: return getTabRecordedCalls();
            case CALL_FORWARDING: return getTabCallForwarding();
            case FILE_MANAGEMENT: return getTabFileManagement();
            case CONFERENCE_CALLS: return getTabConferenceCalls();
            case ABBREVIATED_DIALING: return getTabAbbreviatedDialling();
            case MANAGE_ABBREVIATED_NUMBERS: return getTabManageAbbreviatedNumbers();
            case ANNOUNCEMENT_DISPLAY: return getTabAnnouncementDisplay();
            case MUSIC_ON_HOLD: return getTabMusicOnHold();
            case REPORT_QUEUES: return getTabReport();
            case STATUS_QUEUES:return getTabStatusQueues();
            case CONFIGURE_QUEUES:return getTabConfigureQueues();
            case RECORDINGS_QUEUES:return getTabRecordings();
            case RECORDED_CALLs_OVERVIEW: return getTabRecCallsOverview();
            case RECORDED_CALLs_CONFIGURATIONS: return getTabRecCallsConfigurations();
            case ABBREVIATED_NUMBERS: return getTabAbbreviatedNumber();
            case PROVISIONING: return getTabProvisioning();
            case PROVISIONING_MANAGER: return getTabProvisioningManager();
            case PROVISIONING_END_DEVICES: return getTabProvisioningEndDevices();
            case PROVISIONING_PHONE_MODELS: return getTabProvisioningPhoneModels();
            default: return getTabUser();
        }
    }

    public BasePage setItemsPerPage(ItemsPerPage value){
        getDropdownItemsPerPage().selectOptionByValue(value.getItems());
        return this;
    }

    @Step("LogOut")
    public LoginPage logOut(){
        getButtonLogout().click();
        waitUntilAlertDisappear();
        return new LoginPage();
    }

}
