package core.configuration.preparations;

import core.fields.Fields;
import core.workers.dbWorker.DataBaseWorker;
import core.workers.excelWorker.ExcelFileWorker;
import core.workers.javaScriptExecutor.JavaScriptExecutor;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;
import lowLevelUserPages.dashboardPageLowLevelUser.DashboardUserPage;
import lowLevelUserPages.faxPageLowLevelUser.FaxArrivedUserPage;
import lowLevelUserPages.faxPageLowLevelUser.SendFaxUserPage;
import lowLevelUserPages.lastCallsLowLevelUserPage.IncomingLastCallsUserPage;
import lowLevelUserPages.lastCallsLowLevelUserPage.MissedLastCallsUserPage;
import lowLevelUserPages.lastCallsLowLevelUserPage.OutgoingLastCallsUserPage;
import lowLevelUserPages.sendSmsPageLowLevelUser.AddressBookUserPage;
import lowLevelUserPages.sendSmsPageLowLevelUser.ManageSenderNumbersAndNamesUserPage;
import lowLevelUserPages.sendSmsPageLowLevelUser.SendTextMessageUserPage;
import lowLevelUserPages.voicemailLowLevelUserpage.AnnouncementsUserPage;
import lowLevelUserPages.voicemailLowLevelUserpage.VoicemailSettingUserPage;
import lowLevelUserPages.voicemailLowLevelUserpage.VoicemailUserPage;
import pages.IVRpage.IVRpage;
import pages.abbreviatedDialling.AbbreviatedDiallingBasePage;
import pages.abbreviatedDialling.AbbreviatedNumbers;
import pages.abbreviatedDialling.ManageAbbreviatedNumbers;
import pages.basePage.BasePage;
import pages.basePage.basePopup.AlertPopup;
import pages.basePage.basePopup.ConfirmationPopup;
import pages.callForwardingPage.CallForwardingPage;
import pages.callPickUpPage.CallPickUpPage;
import pages.conferenceCallsPage.ConferenceCallsPage;
import pages.contactDataPage.ContactDataPage;
import pages.endDevicesPage.EndDevicesPage;
import pages.faxPage.FaxPage;
import pages.fileManagementPage.AnnouncementDisplayPage;
import pages.fileManagementPage.FileManagementBasePage;
import pages.fileManagementPage.MusicOnHoldPage;
import pages.huntGroupPage.HuntGroupPage;
import pages.lastCallsPage.IncomingCallsTab;
import pages.lastCallsPage.LastCallsPage;
import pages.lastCallsPage.MissedCallsTab;
import pages.lastCallsPage.OutgoingCallsTab;
import pages.loginPage.LoginPage;
import pages.numbersPage.NumbersPage;
import pages.phonebookPage.PhonebookPage;
import pages.provisioningPage.ProvisioningBasePage;
import pages.provisioningPage.ProvisioningEndDevicesPage;
import pages.provisioningPage.ProvisioningManagerPage;
import pages.provisioningPage.ProvisioningPhoneModelsPage;
import pages.queuesPage.*;
import pages.queuesPage.queuePagePopups.QueueForAgentsPopup;
import pages.recordedCallPage.RecordedCallConfigurationPage;
import pages.recordedCallPage.RecordedCallOverviewPage;
import pages.recordedCallPage.RecordedCallsBasePage;
import pages.subscriptionsPage.SubscriptionsPage;
import pages.userPage.UserPage;
import pages.userPage.userPagePopup.configureUser.*;

public class eFonApp extends PreparationsForRun {

    public Fields fields = new Fields();
    public BasePage basePage = new BasePage();
    public static DataBaseWorker dataBaseWorker = new DataBaseWorker();
    public static ExcelFileWorker excelFileWorker = new ExcelFileWorker();
    public JavaScriptExecutor javaScriptExecutor = new JavaScriptExecutor();

    public static ProvisioningPhoneModelsPage provisioningPhoneModelsPage = new ProvisioningPhoneModelsPage();
    public static ConfirmationPopup confirmationPopup = new ConfirmationPopup();
    public static CallForwardingPage callForwardingPage = new CallForwardingPage();
    public static ConferenceCallsPage conferenceCallsPage = new ConferenceCallsPage();
    public static IVRpage ivrPage = new IVRpage();
    public static HuntGroupPage huntGroupPage = new HuntGroupPage();
    public static AnnouncementDisplayPage announcementDisplayPage = new AnnouncementDisplayPage();
    public static QueueForAgentsPopup queueForAgentsPopup = new QueueForAgentsPopup();
    public static StatusQueueTab statusQueuePage = new StatusQueueTab();
    public static RecordingsQueueTab recordingsQueuePage = new RecordingsQueueTab();
    public static ReportsQueueTab reportsQueuePage = new ReportsQueueTab();
    public static ProvisioningBasePage provisioningBasePage = new ProvisioningBasePage();
    public static ProvisioningManagerPage provisioningManagerPage = new ProvisioningManagerPage();
    public static FaxArrivedUserPage faxArrivedUserPage = new FaxArrivedUserPage();
    public static MissedLastCallsUserPage missedLastCallsUserPage = new MissedLastCallsUserPage();
    public static IncomingLastCallsUserPage incomingLastCallsUserPage = new IncomingLastCallsUserPage();
    public static OutgoingLastCallsUserPage outgoingLastCallsUserPage = new OutgoingLastCallsUserPage();
    public static VoicemailUserPage voicemailUserPage = new VoicemailUserPage();
    public static ProvisioningEndDevicesPage provisioningEndDevicesPage = new ProvisioningEndDevicesPage();
    public static pages.blockListSection.BlockListSection blockListSections = new pages.blockListSection.BlockListSection();
    public AlertPopup alertPopup = new AlertPopup();
    public LoginPage loginPage = new LoginPage();
    public UserPage userPage = new UserPage();
    public NameTabConfigUserPopup nameTabConfigUserPopup = new NameTabConfigUserPopup();
    public PhonebookPage phonebookPage = new PhonebookPage();
    public AbbreviatedDiallingBasePage abbrevDialBasePage = new AbbreviatedDiallingBasePage();
    public ManageAbbreviatedNumbers manageAbbrevNumbersPage = new ManageAbbreviatedNumbers();
    public AbbreviatedNumbers abbreviatedNumbersPage = new AbbreviatedNumbers();
    public FaxPage faxPage = new FaxPage();
    public CallPickUpPage callPickUpPage = new CallPickUpPage();
    public QueuesBasePage queuesBasePage = new QueuesBasePage();
    public ConfigureQueueTab configureQueueTab = new ConfigureQueueTab();
    public NumbersPage numbersPage = new NumbersPage();
    public SubscriptionsPage subscriptionsPage = new SubscriptionsPage();
    public LastCallsPage lastCallsPage = new LastCallsPage();
    public FileManagementBasePage fileManagementBasePage = new FileManagementBasePage();
    public EndDevicesPage endDevicesPage = new EndDevicesPage();
    public RecordedCallsBasePage recordedCallsPage = new RecordedCallsBasePage();
    public ContactDataPage contactDataPage = new ContactDataPage();
    public MusicOnHoldPage musicOnHoldPage = new MusicOnHoldPage();
    public AllocationTabConfigUserPopup allocationTabConfigUserPopup = new AllocationTabConfigUserPopup();
    public ForwardingTabConfigUserPopup forwardingTabConfigUserPopup = new ForwardingTabConfigUserPopup();
    public VoicemailTabConfigUserPopup voicemailTabConfigUserPopup = new VoicemailTabConfigUserPopup();
    public AnnouncementsTabConfigUserPopup announcementsTabConfigUserPopup = new AnnouncementsTabConfigUserPopup();
    public FaxTabConfigUserPopup faxTabConfigUserPopup = new FaxTabConfigUserPopup();
    public EndDeviceTabConfigUserPopup endDeviceTabConfigUserPopup = new EndDeviceTabConfigUserPopup();
    public RecordedCallOverviewPage recordedCallOverviewPage = new RecordedCallOverviewPage();
    public RecordedCallConfigurationPage recordedCallConfigPage = new RecordedCallConfigurationPage();
    public IncomingCallsTab incomingCallsTab = new IncomingCallsTab();
    public MissedCallsTab missedCallsTab = new MissedCallsTab();
    public OutgoingCallsTab outgoingCallsTab = new OutgoingCallsTab();
    public BasePageLowLevelUser basePageLowLevelUser = new BasePageLowLevelUser();
    public SendTextMessageUserPage sendTextMessageUserPage = new SendTextMessageUserPage();
    public AddressBookUserPage addressBookUserPage = new AddressBookUserPage();
    public SendFaxUserPage sendFaxUserPage = new SendFaxUserPage();
    public DashboardUserPage dashboardUserPage = new DashboardUserPage();
    public VoicemailSettingUserPage voicemailSettingUserPage = new VoicemailSettingUserPage();
    public AnnouncementsUserPage announcementsUserPage = new AnnouncementsUserPage();
    public ManageSenderNumbersAndNamesUserPage manageSenderNumbersUserPage = new ManageSenderNumbersAndNamesUserPage();


}
