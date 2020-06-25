package testsLowLevelUser.huntGroupUserPageTests;

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

import java.util.ArrayList;

import static api.baseApiMethods.HuntGroupApi.*;
import static io.qameta.allure.Allure.step;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.*;
import static pages.basePage.BasePage.MenuTabsBasePage.HUNT_GROUPS;

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

    //bug EPRO-1023
    @Description("Check if low-level user can configure \"Full Days\" section on HuntGroup")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupUserPageTests"},enabled = false)
    public void CheckIfUserCanConfigureFullDays(){
        step("Prepare test data");
        HuntGroup huntGroup = new HuntGroup();
        Queue queue = new Queue();
        FileManagementTestData announcement = new FileManagementTestData();

        filesList.add(announcement);
        huntGroupsList.add(huntGroup);
        queuesList.add(queue);

        step("Log in as customer admin and create Huntgruop, Queue and upload announcement for low-level user");
        login();
        createHuntGroup(huntGroup, "AutoTestUser AutoTestUser");
        createQueueOnlyRequiredFields(queue);
        uploadAnnouncementFile(announcement);
        logOut();

        step("Log in as low-level user and goto HuntGroups page");
        loginAsLowLevelUser();
        basePageLowLevelUser.goToMenuTab(HUNT_GROUPS);

        step("Edit hunt group and configure Full Days section");
        //huntGroupUserPage.editHuntGroup(huntGroup);
        editHuntGroupLowLevelUserPopup.configureFullDays(huntGroup,queue,announcement);
        editHuntGroupLowLevelUserPopup.verifyFullDayConfiguration(huntGroup);

        step("Login as admin and delete test data");
        login();
        deleteHuntGroup(huntGroup.getHuntGroupName());
        deleteQueue(queue.getName());
        deleteAnnouncementFile(announcement.getFileName());
    }

    //bug EPRO-1023
    @Description("Check if low-level user can configure \"Standart Timers\" section on HuntGroup")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupUserPageTests"},enabled = false)
    public void CheckIfUserCanConfigureStandartTimers(){
        step("Prepare test data");
        HuntGroup huntGroup = new HuntGroup();
        Queue queue = new Queue();
        FileManagementTestData announcement = new FileManagementTestData();

        filesList.add(announcement);
        huntGroupsList.add(huntGroup);
        queuesList.add(queue);

        step("Log in as customer admin and create Huntgruop, Queue and upload announcement for low-level user");
        login();
        createHuntGroup(huntGroup, "AutoTestUser AutoTestUser");
        createQueueOnlyRequiredFields(queue);
        uploadAnnouncementFile(announcement);
        logOut();

        step("Log in as low-level user and goto HuntGroups page");
        loginAsLowLevelUser();
        basePageLowLevelUser.goToMenuTab(HUNT_GROUPS);

        step("Edit hunt group and configure Full Days section");
        //huntGroupUserPage.editHuntGroup(huntGroup);
        editHuntGroupLowLevelUserPopup.configureStandartTimers(announcement,queue);

        step("Verify made changes");
        //huntGroupUserPage.editHuntGroup(huntGroup);
        editHuntGroupLowLevelUserPopup.verifyStandartTimers();
        logOut();

        step("Clear test data");
        login();
        deleteHuntGroup(huntGroup.getHuntGroupName());
        deleteQueue(queue.getName());
        deleteAnnouncementFile(announcement.getFileName());
    }

    //bug EPRO-1023
    @Description("Check if low-level user can configure \"Further Time groups\" section on HuntGroup")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupUserPageTests"}, enabled =  false)
    public void CheckIfUserCanConfigureFurtherTimeGroups(){
        step("Prepare test data");
        HuntGroup huntGroup = new HuntGroup();
        Queue queue = new Queue();
        FileManagementTestData announcement = new FileManagementTestData();

        filesList.add(announcement);
        huntGroupsList.add(huntGroup);
        queuesList.add(queue);

        step("Log in as customer admin and create Huntgruop, Queue and upload announcement for low-level user");
        login();
        createHuntGroup(huntGroup, "AutoTestUser AutoTestUser");
        createQueueOnlyRequiredFields(queue);
        uploadAnnouncementFile(announcement);
        logOut();

        step("Log in as low-level user and goto HuntGroups page");
        loginAsLowLevelUser();
        basePageLowLevelUser.goToMenuTab(HUNT_GROUPS);

        step("Edit hunt group and configure  \"Further Time groups\" section");
        //huntGroupUserPage.editHuntGroup(huntGroup);
        editHuntGroupLowLevelUserPopup.configureFurtherTimers(huntGroup,queue,announcement);

        step("Log out the system");
        logOut();

        step("Log in as vpbx admin and clear test data");
        login();
        deleteHuntGroup(huntGroup.getHuntGroupName());
        deleteQueue(queue.getName());
        deleteAnnouncementFile(announcement.getFileName());
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
        huntGroupsList.add(huntGroup);

        createHuntGroupApi(huntGroup);
        loginAsLowLevelUser()
                .goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .verifyIfHuntGroupNameNotExist(huntGroup.getHuntGroupName());
        deleteHuntGroupApi(huntGroup);
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        huntGroupCleanUp(huntGroupsList);
        queueCleanUp(queuesList);
        announcementCleanUp(filesList);
    }
}
