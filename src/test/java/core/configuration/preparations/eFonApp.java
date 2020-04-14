package core.configuration.preparations;

import core.fields.Fields;
import core.workers.dbWorker.DataBaseWorker;
import core.workers.excelWorker.ExcelFileWorker;
import core.workers.javaScriptExecutor.JavaScriptExecutor;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;
import lowLevelUserPages.callForwardingLowLevelUserPage.CallForwardingUserPage;
import lowLevelUserPages.dashboardPageLowLevelUser.DashboardUserPage;
import lowLevelUserPages.endDevicesLowLevelUserPage.EndDevicesUserPage;
import lowLevelUserPages.faxPageLowLevelUser.FaxSettingUserPage;
import lowLevelUserPages.faxPageLowLevelUser.FaxesBaseUserPage;
import lowLevelUserPages.faxPageLowLevelUser.SendFaxUserPage;
import lowLevelUserPages.sendSmsPageLowLevelUser.AddressBookUserPage;
import lowLevelUserPages.sendSmsPageLowLevelUser.SendSmsBaseUserPage;
import lowLevelUserPages.sendSmsPageLowLevelUser.SendTextMessageUserPage;
import lowLevelUserPages.sendSmsPageLowLevelUser.sendSmsUserPagePopup.CreateSmsAddressPopup;
import lowLevelUserPages.sendSmsPageLowLevelUser.sendSmsUserPagePopup.SmsConfirmationPopup;
import lowLevelUserPages.voicemailLowLevelUserpage.AnnouncementsUserPage;
import lowLevelUserPages.voicemailLowLevelUserpage.VoicemailBaseUserPage;
import lowLevelUserPages.voicemailLowLevelUserpage.VoicemailSettingUserPage;
import lowLevelUserPages.voicemailLowLevelUserpage.voicemailUserPagePopups.EditAnnouncementPopupUserPage;
import pages.IVRpage.IVRpage;
import pages.IVRpage.IVRpagePopup.IVRpagePopup;
import pages.abbreviatedDialling.AbbreviatedDiallingBasePage;
import pages.abbreviatedDialling.AbbreviatedNumbers;
import pages.abbreviatedDialling.ManageAbbreviatedNumbers;
import pages.abbreviatedDialling.abbreviatedDiallingPopup.AssignAbbreviatedDialling;
import pages.basePage.BasePage;
import pages.basePage.basePopup.AlertPopup;
import pages.basePage.basePopup.ConfirmationPopup;
import pages.callForwardingPage.CallForwardingPage;
import pages.callPickUpPage.CallPickUpPage;
import pages.callPickUpPage.callPickUpPopup.GroupCallPickupPopup;
import pages.conferenceCallsPage.ConferenceCallsPage;
import pages.conferenceCallsPage.conferenceCallsPagePopup.CreateNewConferenceCallPopup;
import pages.contactDataPage.ContactDataPage;
import pages.endDevicesPage.EndDevicesPage;
import pages.faxPage.FaxPage;
import pages.fileManagementPage.AnnouncementDisplayPage;
import pages.fileManagementPage.FileManagementBasePage;
import pages.fileManagementPage.MusicOnHoldPage;
import pages.fileManagementPage.fileManagementPopups.EditFileManagementPopup;
import pages.huntGroupPage.HuntGroupPage;
import pages.huntGroupPage.huntGroupPopup.AddFullDaysPopup;
import pages.huntGroupPage.huntGroupPopup.AddFurtherTimePopup;
import pages.huntGroupPage.huntGroupPopup.CreateHuntGroupPopup;
import pages.lastCallsPage.IncomingCallsTab;
import pages.lastCallsPage.LastCallsPage;
import pages.lastCallsPage.MissedCallsTab;
import pages.lastCallsPage.OutgoingCallsTab;
import pages.loginPage.LoginPage;
import pages.numbersPage.NumbersPage;
import pages.phonebookPage.PhonebookPage;
import pages.queuesPage.ConfigureQueueTab;
import pages.queuesPage.QueuesBasePage;
import pages.queuesPage.queuePagePopups.CreateNewQueuePopup;
import pages.queuesPage.queuePagePopups.QueueForAgentsPopup;
import pages.recordedCallPage.RecordedCallConfigurationPage;
import pages.recordedCallPage.RecordedCallOverviewPage;
import pages.recordedCallPage.RecordedCallsPage;
import pages.subscriptionsPage.SubscriptionsPage;
import pages.userPage.UserPage;
import pages.userPage.userPagePopup.CreateUserPopup;
import pages.userPage.userPagePopup.configureUser.*;

public class eFonApp extends PreparationsForRun{

    public Fields fields = new Fields();
    public BasePage basePage = new BasePage();
    public DataBaseWorker dataBaseWorker = new DataBaseWorker();
    public ExcelFileWorker excelFileWorker = new ExcelFileWorker();
    public JavaScriptExecutor javaScriptExecutor = new JavaScriptExecutor();

    public ConfirmationPopup confirmationPopup = new ConfirmationPopup();
    public AlertPopup alertPopup = new AlertPopup();
    public LoginPage loginPage = new LoginPage();
    public UserPage userPage = new UserPage();
    public CreateUserPopup createUserPopup = new CreateUserPopup();
    public ConfigureUserBasePopup configureUserBasePopup = new ConfigureUserBasePopup();
    public NameTabConfigUserPopup nameTabConfigUserPopup = new NameTabConfigUserPopup();
    public PhonebookPage phonebookPage = new PhonebookPage();
    public AbbreviatedDiallingBasePage abbrevDialBasePage = new AbbreviatedDiallingBasePage();
    public AssignAbbreviatedDialling popupAssignAbbrevDial = new AssignAbbreviatedDialling();
    public ManageAbbreviatedNumbers manageAbbrevNumbersPage = new ManageAbbreviatedNumbers();
    public AbbreviatedNumbers abbreviatedNumbers = new AbbreviatedNumbers();
    public FaxPage faxPage = new FaxPage();
    public CallPickUpPage callPickUpPage = new CallPickUpPage();
    public GroupCallPickupPopup groupCallPickupPopup = new GroupCallPickupPopup();
    public CallForwardingPage callForwardingPage = new CallForwardingPage();
    public ConferenceCallsPage conferenceCallsPage = new ConferenceCallsPage();
    public CreateNewConferenceCallPopup createNewConfCallPopup = new CreateNewConferenceCallPopup();
    public IVRpage ivrPage = new IVRpage();
    public IVRpagePopup createNewIvrPopup = new IVRpagePopup();
    public HuntGroupPage huntGroupPage = new HuntGroupPage();
    public AddFullDaysPopup addFullDaysPopup = new AddFullDaysPopup();
    public CreateHuntGroupPopup createHuntGroupPopup = new CreateHuntGroupPopup();
    public QueuesBasePage queuesBasePage = new QueuesBasePage();
    public ConfigureQueueTab configureQueueTab = new ConfigureQueueTab();
    public CreateNewQueuePopup createNewQueuePopup = new CreateNewQueuePopup();
    public NumbersPage numbersPage = new NumbersPage();
    public SubscriptionsPage subscriptionsPage = new SubscriptionsPage();
    public LastCallsPage lastCallsPage = new LastCallsPage();
    public FileManagementBasePage fileManagementBasePage = new FileManagementBasePage();
    public EndDevicesPage endDevicesPage = new EndDevicesPage();
    public RecordedCallsPage recordedCallsPage = new RecordedCallsPage();
    public ContactDataPage contactDataPage = new ContactDataPage();
    public MusicOnHoldPage musicOnHoldPage = new MusicOnHoldPage();
    public EditFileManagementPopup editFileManagPopup = new EditFileManagementPopup();
    public AnnouncementDisplayPage announcementDisplayPage = new AnnouncementDisplayPage();
    public QueueForAgentsPopup queueForAgentsPopup = new QueueForAgentsPopup();
    public AllocationTabConfigUserPopup allocationTabConfigUserPopup = new AllocationTabConfigUserPopup();
    public ForwardingTabConfigUserPopup forwardingTabConfigUserPopup = new ForwardingTabConfigUserPopup();
    public VoicemailTabConfigUserPopup voicemailTabConfigUserPopup = new VoicemailTabConfigUserPopup();
    public AnnouncementsTabConfigUserPopup announcementsTabConfigUserPopup = new AnnouncementsTabConfigUserPopup();
    public FaxTabConfigUserPopup faxTabConfigUserPopup = new FaxTabConfigUserPopup();
    public EndDeviceTabConfigUserPopup endDeviceTabConfigUserPopup = new EndDeviceTabConfigUserPopup();
    public RecordedCallOverviewPage recordedCallOverviewPage = new RecordedCallOverviewPage();
    public RecordedCallConfigurationPage recordedCallConfigPage = new RecordedCallConfigurationPage();
    public AddFurtherTimePopup addFurtherTimePopup = new AddFurtherTimePopup();
    public IncomingCallsTab incomingCallsTab = new IncomingCallsTab();
    public MissedCallsTab missedCallsTab = new MissedCallsTab();
    public OutgoingCallsTab outgoingCallsTab = new OutgoingCallsTab();
    public BasePageLowLevelUser basePageLowLevelUser = new BasePageLowLevelUser();
    public SendSmsBaseUserPage sendSmsBaseUserPage = new SendSmsBaseUserPage();
    public SendTextMessageUserPage sendTextMessageUserPage = new SendTextMessageUserPage();
    public AddressBookUserPage addressBookUserPage = new AddressBookUserPage();
    public SmsConfirmationPopup smsConfirmationPopup = new SmsConfirmationPopup();
    public CreateSmsAddressPopup createSmsAddressPopup = new CreateSmsAddressPopup();
    public FaxesBaseUserPage faxesBaseUserPage = new FaxesBaseUserPage();
    public SendFaxUserPage sendFaxUserPage = new SendFaxUserPage();
    public FaxSettingUserPage faxSettingUserPage = new FaxSettingUserPage();
    public DashboardUserPage dashboardUserPage = new DashboardUserPage();
    public VoicemailBaseUserPage voicemailBaseUserPage = new VoicemailBaseUserPage();
    public VoicemailSettingUserPage voicemailSettingUserPage = new VoicemailSettingUserPage();
    public AnnouncementsUserPage announcementsUserPage = new AnnouncementsUserPage();
    public EditAnnouncementPopupUserPage editAnnouncementPopup = new EditAnnouncementPopupUserPage();
    public CallForwardingUserPage callForwardingUserPage = new CallForwardingUserPage();
    public EndDevicesUserPage endDevicesUserPage = new EndDevicesUserPage();
}
