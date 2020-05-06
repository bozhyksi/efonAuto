package pages.basePage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import core.fields.Fields;
import core.workers.menuNavigator.MenuNavigator;
import core.workers.menuNavigator.IMenuNavigator;
import pages.IVRpage.IVRpage;
import pages.abbreviatedDialling.AbbreviatedDiallingBasePage;
import pages.callForwardingPage.CallForwardingPage;
import pages.callPickUpPage.CallPickUpPage;
import pages.conferenceCallsPage.ConferenceCallsPage;
import pages.contactDataPage.ContactDataPage;
import pages.endDevicesPage.EndDevicesPage;
import pages.faxPage.FaxPage;
import pages.fileManagementPage.FileManagementBasePage;
import pages.huntGroupPage.HuntGroupPage;
import pages.lastCallsPage.LastCallsPage;
import pages.numbersPage.NumbersPage;
import pages.phonebookPage.PhonebookPage;
import pages.provisioningPage.ProvisioningBasePage;
import pages.queuesPage.QueuesBasePage;
import pages.recordedCallPage.RecordedCallsBasePage;
import pages.subscriptionsPage.SubscriptionsPage;
import pages.userPage.UserPage;

import java.util.concurrent.atomic.AtomicInteger;

public class BasePage extends Fields{

    public static AtomicInteger index = new AtomicInteger(1);

    public enum MenuTabsBasePage{
        USER,
        NUMBERS,
        SUBSCRIPTIONS,
        LAST_CALLS,
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
    private final String formAdministrationXpath = "//main-menu//*[@id=\"navbar-collapse\"]";
    private final String tabUserXpath = "//a[@id=\"menu-11\"]";
    private final String tabNumbersXpath = "//a[@id=\"menu-12\"]";
    private final String tabSubscriptionsXpath = "//a[@id=\"menu-14\"]";
    private final String tabLastCallsXpath = "//a[@id=\"menu-15\"]";
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
    private final String tabManageAbbreviatedNumbersXpath = "//a[@href=\"/portal/internal-numbers/manage\"]";
    private final String tabAnnouncementDisplayXpath = "//a[contains(@href,\"file-management/announcement-display\")]";
    private final String tabMusicOnHoldXpath = "//a[contains(@href,\"/file-management/music-on-hold\")]";
    private final String tabConfigureQueuesXpath = "//a[@href=\"/portal/call-queues/overview\"]";
    private final String tabStatusQueuesXpath = "//a[@href=\"/portal/call-queues/status\"]";
    private final String tabRecordingsXpath = "//a[@href=\"/portal/call-queues/records\"]";
    private final String tabReportXpath = "//a[@href=\"/portal/call-queues/reports\"]";
    private final String tabRecCallsOverviewXpath = "//a[contains(@href,\"recorded-calls/overview\")]";
    private final String tabRecCallsConfigurationsXpath = "//a[contains(@href,\"recorded-calls/configuration\")]";
    private final String tabAbbreviatedNumberXpath = "//a[@href=\"/portal/internal-numbers/overview\"]";
    private final String tabProvisioningXpath = "//a[@id=\"menu-13\"]";

    //Save button
    private String buttonSaveXpath = "//button[text()=\"Save\"]";


    //</editor-fold>

    //<editor-fold desc="//-- BasePage get/set methods --//">


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
            case RECORDED_CALLs_OVERVIEW: return getTabRecCallsOverview();
            case RECORDED_CALLs_CONFIGURATIONS: return getTabRecCallsConfigurations();
            case ABBREVIATED_NUMBERS: return getTabAbbreviatedNumber();
            default: return getTabUser();
        }
    }

    public BasePage goToMenuTab(MenuTabsBasePage tabName){
        getMenuTab(tabName).click();
        waitUntilAlertDisappear();
        return this;
    }

    public <T extends BasePage> T gotoMenuTab(MenuNavigator.MainMenuTabs tabName) {
        switch (tabName){
            case USER:{
                getTabUser().click();
                waitUntilAlertDisappear();
                return (T) new UserPage();
            }
            case NUMBERS:{
                getTabNumbers().click();
                waitUntilAlertDisappear();
                return (T) new NumbersPage();
            }
            case PROVISIONING: {
                getTabProvisioning().click();
                waitUntilAlertDisappear();
                return (T) new ProvisioningBasePage();
            }
            case SUBSCRIPTIONS:{
                getTabSubscriptions().click();
                waitUntilAlertDisappear();
                return (T) new SubscriptionsPage();
            }
            case LAST_CALLS:{
                getTabLastCalls().click();
                waitUntilAlertDisappear();
                return (T) new LastCallsPage();
            }
            case FAX:{
                getTabFax().click();
                waitUntilAlertDisappear();
                return (T) new FaxPage();
            }
            case IVR:{
                getTabIVRs().click();
                waitUntilAlertDisappear();
                return (T) new IVRpage();
            }
            case ABBREVIATED_DIALING:{
                getTabAbbreviatedDialling().click();
                waitUntilAlertDisappear();
                return (T) new AbbreviatedDiallingBasePage();
            }
            case CALL_PICKUP:{
                getTabCallPickUps().click();
                waitUntilAlertDisappear();
                return (T) new CallPickUpPage();
            }
            case FILE_MANAGEMENT:{
                getTabFileManagement().click();
                waitUntilAlertDisappear();
                return (T) new FileManagementBasePage();
            }
            case CALL_FORWARDING:{
                getTabCallForwarding().click();
                waitUntilAlertDisappear();
                return (T) new CallForwardingPage();
            }
            case HUNT_GROUPS:{
                getTabHuntGroups().click();
                waitUntilAlertDisappear();
                return (T) new HuntGroupPage();
            }
            case CONFERENCE_CALLS:{
                getTabConferenceCalls().click();
                waitUntilAlertDisappear();
                return (T) new ConferenceCallsPage();
            }
            case QUEUES:{
                getTabQueues().click();
                waitUntilAlertDisappear();
                return (T) new QueuesBasePage();
            }
            case END_DEVICES:{
                getTabEndDevices().click();
                waitUntilAlertDisappear();
                return (T) new EndDevicesPage();
            }
            case RECORDED_CALLS:{
                getTabRecordedCalls().click();
                waitUntilAlertDisappear();
                return (T) new RecordedCallsBasePage();
            }
            case PHONEBOOK:{
                getTabPhonebook().click();
                waitUntilAlertDisappear();
                return (T) new PhonebookPage();
            }
            case CONTACT_DATA:{
                getTabContactData().click();
                waitUntilAlertDisappear();
                return (T) new ContactDataPage();
            }
            default:{
                return null;
            }
        }
    }
}
