package testsLowLevelUser.huntGroupUserPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseEnums;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.queuesPageTest.queueTestData.Queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.HUNT_GROUPS;

@Listeners(CustomListeners.class)

public class HuntGroupUserPageTests extends BaseTestMethods {
    ArrayList<HuntGroup> huntGroupArrayList = new ArrayList<>();
    ArrayList<FileManagementTestData> filesList = new ArrayList<>();
    ArrayList<Queue> queueArrayList = new ArrayList<>();

    private String huntGroupName = "AutoTestHuntGroup";
    private String huntGroupNumber = "044225787864";

    @Description("Check if low-level user can edit HuntGroup")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupUserPageTests"})
    public void CheckIfLowLevelUserCanEditHuntGroup(){
        HuntGroup huntGroup = new HuntGroup(huntGroupName, huntGroupNumber);

        step("Log in the system as Low-Level User");
        loginAsLowLevelUser();

        step("Goto Hunt Group tab");
        basePageLowLevelUser.goToMenuTab(HUNT_GROUPS);

        step("Open hunt group edit popup");
        huntGroupUserPage.editHuntGroup(huntGroup);

        step("Change language");
        createHuntGroupPopup.changeHuntGroupLanguage(huntGroup.getHuntGroupLanguage());

        step("Save changes");
        createHuntGroupPopup.getButtonSave().click();
        waitUntilAlertDisappear();

        step("Verify made changes");
        huntGroupUserPage.verifyDisplayedHuntGroupDataWasNotChanged(huntGroup);
        huntGroupUserPage.editHuntGroup(huntGroup);
        createHuntGroupPopup.getButtonSubmitEditHuntGroup().click();
        createHuntGroupPopup.getDropdownLanguage().getSelectedValue().contains(huntGroup.getHuntGroupLanguage());
    }

    @Description("Check if low-level user can configure Voicemail section on HuntGroup")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupUserPageTests"})
    public void CheckIfLowLevelUserCanConfigureVoicemailSectionOnHuntgroup(){
        step("Test data preparation - create hunt group obj");
        HuntGroup huntGroup = new HuntGroup();
        huntGroupArrayList.add(huntGroup);

        step("Log in as customer admin and create Huntgruop for low-level user");
        login();
        createHuntGroup(huntGroup, "AutoTestUser AutoTestUser");
        logOut();

        step("Log in as low-level user and goto HuntGroups page");
        loginAsLowLevelUser();
        basePageLowLevelUser.goToMenuTab(HUNT_GROUPS);

        step("Edit hunt group and configure Voicemail section");
        huntGroupUserPage.editHuntGroup(huntGroup);
        editHuntGroupLowLevelUserPopup.cofigureVoicemailSection(huntGroup);

        step("Check if configuration was saved");
        refreshPage();
        huntGroupUserPage.editHuntGroup(huntGroup);
        editHuntGroupLowLevelUserPopup.verifyVoicemailSection(huntGroup);
        logOut();

        step("Login as admin and delete test data");
        login();
        deleteHuntGroup(huntGroup.getHuntGroupName());
    }

    @Description("Check if low-level user can configure \"If end devices not available\" section on HuntGroup")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupUserPageTests"})
    public void CheckIfUserCanConfigureIfEndDevicesNotAvailableSection(){
        step("Test data preparation - create hunt group obj");
        HuntGroup huntGroup = new HuntGroup();
        huntGroupArrayList.add(huntGroup);

        step("Log in as customer admin and create Huntgruop for low-level user");
        login();
        createHuntGroup(huntGroup, "AutoTestUser AutoTestUser");
        logOut();

        step("Log in as low-level user and goto HuntGroups page");
        loginAsLowLevelUser();
        basePageLowLevelUser.goToMenuTab(HUNT_GROUPS);

        step("Edit hunt group and configure Voicemail section");
        huntGroupUserPage.editHuntGroup(huntGroup);
        editHuntGroupLowLevelUserPopup.configureEndDeviceNotAvailableSection(huntGroup);

        step("Check if configuration was saved");
        refreshPage();
        huntGroupUserPage.editHuntGroup(huntGroup);
        editHuntGroupLowLevelUserPopup.verifyEndDeviceNotAvailableSection(huntGroup);
        logOut();

        step("Login as admin and delete test data");
        login();
        deleteHuntGroup(huntGroup.getHuntGroupName());
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
        huntGroupArrayList.add(huntGroup);
        queueArrayList.add(queue);

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
        huntGroupUserPage.editHuntGroup(huntGroup);
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
        huntGroupArrayList.add(huntGroup);
        queueArrayList.add(queue);

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
        huntGroupUserPage.editHuntGroup(huntGroup);
        editHuntGroupLowLevelUserPopup.configureStandartTimers(announcement,queue);

        step("Verify made changes");
        huntGroupUserPage.editHuntGroup(huntGroup);
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
        huntGroupArrayList.add(huntGroup);
        queueArrayList.add(queue);

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
        huntGroupUserPage.editHuntGroup(huntGroup);
        editHuntGroupLowLevelUserPopup.configureFurtherTimers(huntGroup,queue,announcement);

        step("Log out the system");
        logOut();

        step("Log in as vpbx admin and clear test data");
        login();
        deleteHuntGroup(huntGroup.getHuntGroupName());
        deleteQueue(queue.getName());
        deleteAnnouncementFile(announcement.getFileName());
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        startBrowser();
        login();
        huntGroupCleanUp(huntGroupArrayList);
        queueCleanUp(queueArrayList);
        announcementCleanUp(filesList);
        closeBrowser();
    }
}
