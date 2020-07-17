package tests.huntGroupPageTest;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.queuesPageTest.queueTestData.Queue;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static api.baseApiMethods.AbbreviatedNumbersApi.createAbbreviatedNumberApi;
import static api.baseApiMethods.AbbreviatedNumbersApi.deleteAbbreviatedNumberApi;
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
import static tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling.Type.SINGLE;

@Listeners(CustomListeners.class)

public class HuntGroupsPageTests extends BaseTestMethods {

    //<editor-fold desc="ArrayLists">
    ArrayList<HuntGroup> huntGroupsList = new ArrayList<>();
    ArrayList<User> usersList = new ArrayList<>();
    ArrayList<FileManagementTestData> filesList = new ArrayList<>();
    ArrayList<Queue> queueArrayList = new ArrayList<>();
    ArrayList<AbbreviatedDialling> shortNumsList = new ArrayList<>();
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
                .enterName(huntGroup.getHuntGroupName())
                .enterDisplayName(huntGroup.getHuntGroupDisplayName())
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
        HuntGroup huntGroup = new HuntGroup();
        User user = new User();
        huntGroupsList.add(huntGroup);
        usersList.add(user);

        createUsersApi(user);
        createHuntGroupApi(huntGroup);
        login()
                .goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .clickEditHuntGroup(huntGroup)
                .selectAuthorizedUser(user)
                .saveChanges()
                .clickEditHuntGroup(huntGroup)
                .validateSelectedAuthorizedUsers(user);
        deleteHuntGroupApi(huntGroup);
        deleteUsersApi(user);
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
                .enterName(huntGroup.changeHuntGroupName())
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
                .enterDisplayName(huntGroup.changeDisplayName())
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

    //EPRO-1156
    @Description("Verify if after deleting, HuntGroup phonenumber - is not shown in the BlockList dropdown")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"}, enabled = false)
    public void verifyIfDeletedHuntgroupNumbersNotShownInBlocklistDropDown(){
        HuntGroup huntGroup = new HuntGroup();
        HuntGroup huntGroup2 = new HuntGroup();
        huntGroupsList.add(huntGroup);
        huntGroupsList.add(huntGroup2);

        createHuntGroupApi(huntGroup,huntGroup2);
        login()
                .goToMenuTab(HUNT_GROUPS);
        blockListSections
                .checkIfDropdownContainsNumber(huntGroup.getHuntGroupNumber())
                .checkIfDropdownContainsNumber(huntGroup2.getHuntGroupNumber());
        huntGroupPage
                .deleteHuntGroup(huntGroup);
        blockListSections
                .checkIfDropdownNotContainsNumber(huntGroup.getHuntGroupNumber())
                .checkIfDropdownContainsNumber(huntGroup2.getHuntGroupNumber());
        deleteHuntGroupApi(huntGroup2);
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
                    .enterDisplayName(huntGroup.getHuntGroupDisplayName())
                    .enterName(huntGroup.getHuntGroupName())
                    .activateBusyOnBusy()
                .saveChanges()
                .clickEditHuntGroup(huntGroup)
                .getBusyOnBusy().shouldBe(selected);
        deleteHuntGroupApi(huntGroup);
    }

    @Description("Verify if user can create hunt group and configure dropdown \"Display following number, when forwarding\"")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void dropdownSelectShowNumberTest(){
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        createHuntGroupApi(huntGroup);
        login()
                .goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .clickEditHuntGroup(huntGroup)
                .selectShowNumber("Original number")
                .saveChanges()
                .clickEditHuntGroup(huntGroup)
                .getDropdownShowNumber().getSelectedText().contains("Original number");
        refreshPage();
        huntGroupPage
                .clickEditHuntGroup(huntGroup)
                .selectShowNumber("Fixed network number")
                .saveChanges()
                .clickEditHuntGroup(huntGroup)
                .getDropdownShowNumber().getSelectedText().contains("Fixed network number");
        deleteHuntGroupApi(huntGroup);
    }

    @Description("Verify if system delete HuntGroups after editing")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void deleteHuntGroupAfterEditingTest(){
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        createHuntGroupApi(huntGroup);
        login()
                .goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .clickEditHuntGroup(huntGroup)
                    .clickEditButtonForEditHuntGroupSection()
                    .selectNumber(huntGroup.changeNumber())
                    .enterName(huntGroup.changeHuntGroupName())
                    .enterDisplayName(huntGroup.changeDisplayName())
                .saveChanges()
                .deleteHuntGroup(huntGroup)
                .verifyIfHuntGroupNameNotExist(huntGroup.getHuntGroupName());
    }

    @Description("Verify if user can configure \"Full days groups with Time Ranges\"")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void configureFullDaysWithTimesTest() {
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
                .enterDates(huntGroup.getFullDayWithTimeRanges())
                .configureSteps(huntGroup)
                .saveFullDays()
                .saveChanges()
                .clickEditHuntGroup(huntGroup)
                .clickEditFullDays()
                .verifyFullDaysWithTimeRangesConfig(huntGroup);

        deleteHuntGroupApi(huntGroup);
        deleteAnnouncementApi(huntGroup.getAnnouncement());
        deleteQueueApi(huntGroup.getQueue());
    }

    @Description("Verify if user can create HungGroup using shortNumber as phone number")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void createHuntGroupWithShortNumberTest(){
        HuntGroup huntGroup = new HuntGroup(new  AbbreviatedDialling(SINGLE));
        huntGroupsList.add(huntGroup);
        shortNumsList.add(huntGroup.getShotNum());

        createAbbreviatedNumberApi(huntGroup.getShotNum());
        login()
                .goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .clickCreateNewHuntGroup()
                .enterDisplayName(huntGroup.getHuntGroupDisplayName())
                .enterName(huntGroup.getHuntGroupName())
                .selectNumber(huntGroup.getShotNum().getSingleShortNum())
                .saveChanges()
                .verifyIfHuntGroupNumberExists(huntGroup.getShotNum().getSingleShortNum())
                .verifyIfHuntGroupNameExists(huntGroup.getHuntGroupName());
        deleteHuntGroupApi(huntGroup);
        deleteAbbreviatedNumberApi(huntGroup.getShotNum());
    }


    @AfterClass(alwaysRun = true)
    private void cleanUp() {
        huntGroupCleanUp(huntGroupsList);
        userCleanUp(usersList);
        queueCleanUp(queueArrayList);
        announcementCleanUp(filesList);
        abbrevNumsCleanUp(shortNumsList);
    }
}
