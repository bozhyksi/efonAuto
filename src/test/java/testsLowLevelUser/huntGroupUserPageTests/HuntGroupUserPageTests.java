package testsLowLevelUser.huntGroupUserPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import flow.PublicEnums;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.queuesPageTest.queueTestData.Queue;
import testsLowLevelUser.testData.AutotestUserData;

import java.util.ArrayList;

import static api.baseApiMethods.FileManagementApi.deleteAnnouncementApi;
import static api.baseApiMethods.FileManagementApi.uploadAnnouncementApi;
import static api.baseApiMethods.HuntGroupApi.*;
import static api.baseApiMethods.QueueApi.createQueueApi;
import static api.baseApiMethods.QueueApi.deleteQueueApi;
import static flow.PublicEnums.HuntGroupTimerGroup.FULL_DAYS;
import static flow.PublicEnums.HuntGroupTimerGroup.TIME;
import static io.qameta.allure.Allure.step;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.*;
import static pages.basePage.BasePage.MenuTabsBasePage.HUNT_GROUPS;
import static testsLowLevelUser.testData.AutotestUserData.autotestUserDisplayName;
import static testsLowLevelUser.testData.AutotestUserData.autotestUserId;

@Listeners(CustomListeners.class)

public class HuntGroupUserPageTests extends BaseTestMethods {

    //<editor-fold desc="arrayLists">
    ArrayList<HuntGroup> huntGroupsList = new ArrayList<>();
    ArrayList<FileManagementTestData> filesList = new ArrayList<>();
    ArrayList<Queue> queuesList = new ArrayList<>();
    //</editor-fold>

    @Description("Check if low-level user can edit HuntGroup")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupUserPageTests"})
    public void editHuntGroupTest(){
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        createHuntGroupWithAuthorizedUserApi(huntGroup,autotestUserId,autotestUserDisplayName);

        loginAsLowLevelUser()
                .goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .clickEditHuntGroup(huntGroup)
                .clickEditButtonForEditHuntGroupSection()
                .selectLanguage(huntGroup.changeLanguage())
                .saveChanges()
                .clickEditHuntGroup(huntGroup)
                .clickEditButtonForEditHuntGroupSection()
                .getDropdownLanguage().getSelectedValue().contains(huntGroup.getHuntGroupLanguage());

        deleteHuntGroupApi(huntGroup);
    }

    @Description("Check if low-level user can configure Voicemail section on HuntGroup")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupUserPageTests"})
    public void configureVoicemailSectionTest(){
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        createHuntGroupWithAuthorizedUserApi(huntGroup,autotestUserId,autotestUserDisplayName);

        loginAsLowLevelUser()
                .goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .clickEditHuntGroup(huntGroup)
                .clickEditVoicemail()
                    .enterSalutation(huntGroup.getSalutation())
                    .activateSendByEmailCheckBox()
                    .enterPIN(huntGroup.getPinCode())
                    .enterEmail(huntGroup.getVoicemailEmail())
                    .saveVoicemail()
                .saveChanges()
                .clickEditHuntGroup(huntGroup)
                .clickEditVoicemail()
                .validateVoicemail(huntGroup);

        deleteHuntGroupApi(huntGroup);
    }

    @Description("Check if low-level user can configure \"If end devices not available\" section on HuntGroup")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupUserPageTests"})
    public void endDevicesNotAvailableSectionTest(){
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        createHuntGroupWithAuthorizedUserApi(huntGroup,autotestUserId,autotestUserDisplayName);

        loginAsLowLevelUser()
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

    @Description("Check if low-level user can configure \"Full Days\" section on HuntGroup")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupUserPageTests"})
    public void configureFullDaysTest(){
        HuntGroup huntGroup = new HuntGroup(new FileManagementTestData(), new Queue());
        filesList.add(huntGroup.getAnnouncement());
        huntGroupsList.add(huntGroup);
        queuesList.add(huntGroup.getQueue());

        createHuntGroupWithAuthorizedUserApi(huntGroup, autotestUserId, autotestUserDisplayName);
        createQueueApi(huntGroup.getQueue());
        uploadAnnouncementApi(huntGroup.getAnnouncement());

        loginAsLowLevelUser()
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
        deleteQueueApi(huntGroup.getQueue());
        deleteAnnouncementApi(huntGroup.getAnnouncement());
    }

    @Description("Check if low-level user can configure \"Standart Timers\" section on HuntGroup")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupUserPageTests"})
    public void configureStandartTimersTest(){
        HuntGroup huntGroup = new HuntGroup(new FileManagementTestData(), new Queue());
        filesList.add(huntGroup.getAnnouncement());
        huntGroupsList.add(huntGroup);
        queuesList.add(huntGroup.getQueue());

        createHuntGroupWithAuthorizedUserApi(huntGroup, autotestUserId, autotestUserDisplayName);
        createQueueApi(huntGroup.getQueue());
        uploadAnnouncementApi(huntGroup.getAnnouncement());

        loginAsLowLevelUser()
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
        deleteQueueApi(huntGroup.getQueue());
        deleteAnnouncementApi(huntGroup.getAnnouncement());
    }

    @Description("Check if low-level user can configure \"Further Time groups\" section on HuntGroup")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupUserPageTests"})
    public void configureFurtherTimeGroupsTest(){
        HuntGroup huntGroup = new HuntGroup(new FileManagementTestData(), new Queue());
        filesList.add(huntGroup.getAnnouncement());
        huntGroupsList.add(huntGroup);
        queuesList.add(huntGroup.getQueue());

        createHuntGroupWithAuthorizedUserApi(huntGroup, autotestUserId, autotestUserDisplayName);
        createQueueApi(huntGroup.getQueue());
        uploadAnnouncementApi(huntGroup.getAnnouncement());

        loginAsLowLevelUser()
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
        deleteQueueApi(huntGroup.getQueue());
        deleteAnnouncementApi(huntGroup.getAnnouncement());
    }

    @Description("Check if user has access to HuntGroup where he was assigned as authorised user")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupUserPageTests"})
    public void accessToAssignedHuntGroupTest(){
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        createHuntGroupWithAuthorizedUserApi(huntGroup,autotestUserId,autotestUserDisplayName);
        loginAsLowLevelUser()
                .goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .verifyIfHuntGroupNameExists(huntGroup.getHuntGroupName());
        deleteHuntGroupApi(huntGroup);
    }

    @Description("Check if user has NO access to HuntGroup where he was NOT assigned as authorised user")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupUserPageTests"})
    public void noAccessToUnAssignedHuntGroupTest(){
        HuntGroup huntGroup = new HuntGroup();
        HuntGroup huntGroup2 = new HuntGroup();
        huntGroupsList.add(huntGroup);
        huntGroupsList.add(huntGroup2);

        //Here I'am creating two hunt groups. One is related to the user one is not.
        //this is because - if user has no at least one related huntGroup - HUNT_Group tab is unavailable in the menu.
        createHuntGroupApi(huntGroup);
        createHuntGroupWithAuthorizedUserApi(huntGroup2,autotestUserId,autotestUserDisplayName);

        loginAsLowLevelUser()
                .goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .verifyIfHuntGroupNameNotExist(huntGroup.getHuntGroupName());

        deleteHuntGroupApi(huntGroup, huntGroup2);
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        huntGroupCleanUp(huntGroupsList);
        queueCleanUp(queuesList);
        announcementCleanUp(filesList);
    }
}
