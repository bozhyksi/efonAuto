package tests.huntGroupPageTest;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.queuesPageTest.queueTestData.Queue;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static api.baseApiMethods.FileManagementApi.deleteAnnouncementApi;
import static api.baseApiMethods.FileManagementApi.uploadAnnouncementApi;
import static api.baseApiMethods.HuntGroupApi.createHuntGroupApi;
import static api.baseApiMethods.HuntGroupApi.deleteHuntGroupApi;
import static api.baseApiMethods.QueueApi.createQueueApi;
import static api.baseApiMethods.QueueApi.deleteQueueApi;
import static api.baseApiMethods.UserApi.createUsersApi;
import static api.baseApiMethods.UserApi.deleteUsersApi;
import static com.codeborne.selenide.Condition.selected;
import static flow.PublicEnums.HuntGroupTimerGroup.FULL_DAYS;
import static flow.PublicEnums.HuntGroupTimerGroup.TIME;
import static pages.basePage.BasePage.MenuTabsBasePage.HUNT_GROUPS;

@Listeners(CustomListeners.class)

public class HuntGroupsPageTests extends BaseTestMethods {

    //<editor-fold desc="ArrayLists">
    ArrayList<HuntGroup> huntGroupsList = new ArrayList<>();
    ArrayList<User> usersList = new ArrayList<>();
    ArrayList<FileManagementTestData> filesList = new ArrayList<>();
    ArrayList<Queue> queueArrayList = new ArrayList<>();
    //</editor-fold>

    @Description("Verify if user can create/delete Hunt Group")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "huntGroupsPageTests"})
    public void createDeleteHuntGroupTest() {
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        login()
                .goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .clickCreateNewHuntGroup()
                .selectNumber(huntGroup.getHuntGroupNumber())
                .setName(huntGroup.getHuntGroupName())
                .setDisplayName(huntGroup.getHuntGroupDisplayName())
                .saveChanges()
                .verifyIfHuntGroupNameExists(huntGroup.getHuntGroupName())
                .deleteHuntGroup(huntGroup);
    }

    @Description("Verify if user can edit hunt group and configure Voicemail")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void configureVoicemailSectionTest() {

        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        createHuntGroupApi(huntGroup);

        login()
                .goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .clickEditHuntGroup(huntGroup)
                .clickEditVoicemail()
                .enterPIN(huntGroup.getPinCode())
                .enterEmail(huntGroup.getVoicemailEmail())
                .enterSalutation(huntGroup.getSalutation())
                .activateSendByEmailCheckBox()
                .saveVoicemail()
                .saveChanges()
                .clickEditHuntGroup(huntGroup)
                .clickEditVoicemail()
                .validateVoicemail(huntGroup);

        deleteHuntGroupApi(huntGroup);
    }

    @Description("Verify if user can edit hunt group and configure \"If end devices not available (not registered)\" section")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void configureEndDevicesNotAvailableSectionTest() {
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        createHuntGroupApi(huntGroup);

        login()
                .goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .clickEditHuntGroup(huntGroup)
                .clickEditEndDevNotAvailable()
                .selectRelevantEndDevice()
                .enterBackupNumber(huntGroup.getBackUpNumber())
                .submit()
                .saveChanges()
                .clickEditHuntGroup(huntGroup)
                .clickEditEndDevNotAvailable()
                .verifyRelevantAccountConfiguration(huntGroup);

        deleteHuntGroupApi(huntGroup);
    }

    @Description("Verify if user can edit hunt group and configure \"Standard Timers\"")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void configureStandardTimersTest() {
        HuntGroup huntGroup = new HuntGroup(new FileManagementTestData(), new Queue());
        huntGroupsList.add(huntGroup);
        filesList.add(huntGroup.getAnnouncement());
        queueArrayList.add(huntGroup.getQueue());

        createHuntGroupApi(huntGroup);
        createQueueApi(huntGroup.getQueue());
        uploadAnnouncementApi(huntGroup.getAnnouncement());

        login()
                .goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .clickEditHuntGroup(huntGroup)
                .clickEditTimers()
                .configureStandartTimer(huntGroup)
                .submit()
                .saveChanges()
                .clickEditHuntGroup(huntGroup)
                .clickEditTimers()
                .verifyStandartTimersConfiguration();

        deleteHuntGroupApi(huntGroup);
        deleteAnnouncementApi(huntGroup.getAnnouncement());
        deleteQueueApi(huntGroup.getQueue());
    }

    @Description("Verify if user can configure \"Full days groups\"")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void configureFullDaysGroupsTest() {
        HuntGroup huntGroup = new HuntGroup(new FileManagementTestData(), new Queue());
        huntGroupsList.add(huntGroup);
        filesList.add(huntGroup.getAnnouncement());
        queueArrayList.add(huntGroup.getQueue());

        createHuntGroupApi(huntGroup);
        createQueueApi(huntGroup.getQueue());
        uploadAnnouncementApi(huntGroup.getAnnouncement());

        login()
                .goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .clickEditHuntGroup(huntGroup)
                .selectTimerGroups(FULL_DAYS)
                .clickAddFullDays()
                    .enterFullDaysName(huntGroup.getFullDayName())
                    .enterDates(huntGroup.getFullDayDate())
                    .configureSteps(huntGroup)
                    .saveFullDays()
                .saveChanges()
                .clickEditHuntGroup(huntGroup)
                .clickEditFullDays()
                .verifyFullDaysConfig(huntGroup);

        deleteHuntGroupApi(huntGroup);
        deleteAnnouncementApi(huntGroup.getAnnouncement());
        deleteQueueApi(huntGroup.getQueue());
    }

    @Description("Verify if user can configure \"Further Time groups\"")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void configureFurtherTimeGroupsTest(){
        HuntGroup huntGroup = new HuntGroup(new FileManagementTestData(), new Queue());
        huntGroupsList.add(huntGroup);
        filesList.add(huntGroup.getAnnouncement());
        queueArrayList.add(huntGroup.getQueue());

        createHuntGroupApi(huntGroup);
        createQueueApi(huntGroup.getQueue());
        uploadAnnouncementApi(huntGroup.getAnnouncement());

        login()
                .goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .clickEditHuntGroup(huntGroup)
                .selectTimerGroups(TIME)
                .clickAddTimers()
                    .enterTimeName(huntGroup.getFurtherTimeName())
                    .enterTimers(huntGroup)
                    .configureSteps(huntGroup)
                    .saveTimers()
                .saveChanges()
                .clickEditHuntGroup(huntGroup)
                .clickEditFurtherTimers()
                .verifyTimersConfig(huntGroup);

        deleteHuntGroupApi(huntGroup);
        deleteAnnouncementApi(huntGroup.getAnnouncement());
        deleteQueueApi(huntGroup.getQueue());
    }

    @Description("Verify if user is able to configure \"Calls recording\" for HuntGroup")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void configureCallsRecordingTest(){
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        createHuntGroupApi(huntGroup);

        login()
                .goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .clickEditHuntGroup(huntGroup)
                .activateCallRecordings()
                .saveChanges()
                .verifyIfCallRecordingWasActivated(huntGroup);

        deleteHuntGroupApi(huntGroup);
    }

    @Description("Verify if it is possible to add authorized users")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void addAuthorizedUserTest(){
        HuntGroup huntGroup = new HuntGroup(new User());
        huntGroupsList.add(huntGroup);
        usersList.add(huntGroup.getAuthorisedUser());

        createHuntGroupApi(huntGroup);
        createUsersApi(huntGroup.getAuthorisedUser());

        login()
                .goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .clickEditHuntGroup(huntGroup)
                .selectAuthorizedUser(huntGroup.getAuthorisedUser())
                .saveChanges()
                .clickEditHuntGroup(huntGroup)
                .validateSelectedAuthorizedUsers(huntGroup.getAuthorisedUser());

        deleteHuntGroupApi(huntGroup);
        deleteUsersApi(huntGroup.getAuthorisedUser());
    }

    @Description("Verify if after changing HuntGroup name - changed data is shown in the grid")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void verifyIfUpdatedNameShownInGrid(){
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        createHuntGroupApi(huntGroup);

        login()
                .goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .clickEditHuntGroup(huntGroup)
                .clickEditButtonForEditHuntGroupSection()
                .setName(huntGroup.changeHuntGroupName())
                .saveChanges()
                .verifyIfHuntGroupNameExists(huntGroup.getHuntGroupName());

        deleteHuntGroupApi(huntGroup);
    }

    @Description("Verify if after changing HuntGroup Display Name - changed data is shown in the grid")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void verifyIfUpdatedDisplayNameShownInGrid(){
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        createHuntGroupApi(huntGroup);

        login()
                .goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .clickEditHuntGroup(huntGroup)
                .clickEditButtonForEditHuntGroupSection()
                .setDisplayName(huntGroup.changeDisplayName())
                .saveChanges()
                .verifyIfHuntGroupDisplayNameExists(huntGroup.getHuntGroupDisplayName());

        deleteHuntGroupApi(huntGroup);
    }

    @Description("Verify if after changing HuntGroup phonenumber - is shown in the grid")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void verifyIfHuntGroupPhoneShownIngridAfterChanging(){
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        createHuntGroupApi(huntGroup);

        login()
                .goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .clickEditHuntGroup(huntGroup)
                .clickEditButtonForEditHuntGroupSection()
                .selectNumber(huntGroup.changeNumber())
                .saveChanges()
                .verifyIfHuntGroupNumberExists(huntGroup.getHuntGroupNumber());

        deleteHuntGroupApi(huntGroup);
    }

    @Description("Verify if after deleting, HuntGroup phonenumber - is not shown in the BlockList dropdown")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void verifyIfDeletedHuntgroupNumbersNotShownInBlocklistDropDown(){
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        createHuntGroupApi(huntGroup);
        login()
                .goToMenuTab(HUNT_GROUPS);
        blockListSections
                .checkIfDropdownContainsNumber(huntGroup.getHuntGroupNumber());
        huntGroupPage
                .deleteHuntGroup(huntGroup);
        blockListSections
                .checkIfDropdownNotContainsNumber(huntGroup.getHuntGroupNumber());
    }

    @Description("Verify if user can edit hunt group and activate Busy on Busy")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void editHuntGroupAndActivateBusyOnBusyTest(){
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        createHuntGroupApi(huntGroup);
        login()
                .goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .clickEditHuntGroup(huntGroup)
                    .activateBusyOnBusy()
                    .saveChanges()
                .clickEditHuntGroup(huntGroup)
                    .getBusyOnBusy().shouldBe(selected);
        deleteHuntGroupApi(huntGroup);
    }

    @Description("Verify if user can create hunt group and activate Busy on Busy")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void createHuntGroupAndActivateBusyOnBusyTest(){
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        login()
                .goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .clickCreateNewHuntGroup()
                    .selectNumber(huntGroup.getHuntGroupNumber())
                    .setDisplayName(huntGroup.getHuntGroupDisplayName())
                    .setName(huntGroup.getHuntGroupName())
                    .activateBusyOnBusy()
                .saveChanges()
                .clickEditHuntGroup(huntGroup)
                .getBusyOnBusy().shouldBe(selected);
        deleteHuntGroupApi(huntGroup);
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp() {
        huntGroupCleanUp(huntGroupsList);
        userCleanUp(usersList);
        queueCleanUp(queueArrayList);
        announcementCleanUp(filesList);
    }
}
