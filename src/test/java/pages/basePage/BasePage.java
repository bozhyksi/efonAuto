package pages.basePage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import core.fields.Fields;

import java.util.concurrent.atomic.AtomicInteger;

public class BasePage extends Fields {

    public AtomicInteger index = new AtomicInteger(1);

    public enum MenuTabsBasePage{
        USER,
        NUMBERS,
        SUBSCRIPTIONS,
        LAST_CALLS,
        FAX,
        IVRs,
        ABBREVIATED_DIALING,
        CALL_PICKUPs,
        FILE_MANAGEMENT,
        CALL_FORWARDING,
        HUNT_GROUPS,
        CONFERENCE_CALLS,
        QUEUES,
        END_DEVICES,
        RECORDED_CALLs,
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

    //Administration panel
    private String formAdministrationXpath = "//main-menu//*[@id=\"navbar-collapse\"]";
    private String tabUserXpath = "//a[@id=\"menu-11\"]";
    private String tabNumbersXpath = "//a[@id=\"menu-12\"]";
    private String tabSubscriptionsXpath = "//a[@id=\"menu-14\"]";
    private String tabLastCallsXpath = "//a[@id=\"menu-15\"]";
    private String tabFaxXpath = "//a[@id=\"menu-16\"]";
    private String tabIVRsXpath = "//a[@id=\"menu-17\"]";
    private String tabAbbreviatedNumbersXpath = "//a[@id=\"menu-18\"]";
    private String tabCallPickUpsXpath = "//a[@id=\"menu-19\"]";
    private String tabFileManagementXpath = "//a[@id=\"menu-20\"]";
    private String tabCallForwardingXpath = "//a[@id=\"menu-21\"]";
    private String tabHuntGroupsXpath = "//a[@id=\"menu-24\"]";
    private String tabConferenceCallsXpath = "//a[@id=\"menu-25\"]";
    private String tabQueuesXpath = "//a[@id=\"menu-26\"]";
    private String tabEndDevicesXpath = "//a[@id=\"menu-29\"]";
    private String tabRecordedCallsXpath = "//a[@id=\"menu-31\"]";
    private String tabContactDataXpath = "//a[@id=\"menu-33\"]";
    private String tabPhonebookXpath = "//a[@id=\"menu-32\"]";
    private String tabManageAbbreviatedNumbersXpath = "//a[@href=\"/portal/internal-numbers/manage\"]";
    private String tabAnnouncementDisplayXpath = "//a[contains(@href,\"file-management/announcement-display\")]";
    private String tabMusicOnHoldXpath = "//a[contains(@href,\"/file-management/music-on-hold\")]";
    private String tabConfigureQueuesXpath = "//a[@href=\"/portal/call-queues/overview\"]";
    private String tabStatusQueuesXpath = "//a[@href=\"/portal/call-queues/status\"]";
    private String tabRecordingsXpath = "//a[@href=\"/portal/call-queues/records\"]";
    private String tabReportXpath = "//a[@href=\"/portal/call-queues/reports\"]";

    //Save button
    private String buttonSaveXpath = "//button[text()=\"Save\"]";


    //</editor-fold>

    //<editor-fold desc="//-- BasePage get/set methods --//">


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

    private SelenideElement getMenuTab(MenuTabsBasePage tabName){
        switch (tabName){
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
            default: return getTabUser();
        }
    }

    public BasePage goToMenuTab(MenuTabsBasePage tabName){
        getMenuTab(tabName).click();
        waitUntilAlertDisappear();
        return this;
    }

}
