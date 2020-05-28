package tests.huntGroupPageTest;

import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static pages.basePage.BasePage.MenuTabsBasePage.HUNT_GROUPS;
import static pages.huntGroupPage.huntGroupPopup.CreateHuntGroupPopup.QueueActions.Announcements;
import static pages.huntGroupPage.huntGroupPopup.CreateHuntGroupPopup.QueueActions.Queue;

import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.queuesPageTest.queueTestData.Queue;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static io.qameta.allure.Allure.step;
import static pages.huntGroupPage.huntGroupPopup.CreateHuntGroupPopup.QueueActions.NumberEndDevice;
import static pages.huntGroupPage.huntGroupPopup.CreateHuntGroupPopup.QueueActions.VoicemailBusy;

@Listeners(CustomListeners.class)

public class HuntGroupsPageTests extends BaseTestMethods {
    ArrayList<HuntGroup> huntGroupsList = new ArrayList<>();
    ArrayList<User> usersList = new ArrayList<>();
    ArrayList<FileManagementTestData> filesList = new ArrayList<>();
    ArrayList<Queue> queueArrayList = new ArrayList<>();

    @Description("Verify if user can create/delete Hunt Group")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "huntGroupsPageTests"})
    public void VerifyIfUserCanCreateHuntGroup() {
        step("Prepare test data");
        HuntGroup huntGroup = new HuntGroup();
        User user = new User();
        huntGroupsList.add(huntGroup);
        usersList.add(user);

        step("Login the system");
        login();

        step("Create new user");
        createUser(user);

        step("Goto Hunt Group tab");
        basePage.getTabHuntGroups().click();

        step("Click \"New hunt group\" button");
        huntGroupPage.getButtonCreateNewHuntGroup().click();
        waitUntilAlertDisappear();

        step("Fill in new hunt group name");
        createHuntGroupPopup.getInputName().setValue(huntGroup.getHuntGroupName());

        step("Select authorized user");
        createHuntGroupPopup.getDropdownAuthUsers().selectOptionContainingText(user.getFirstName());
        huntGroup.setHuntGroupAuthorizedUser(user.getFullName());

        step("Fill in new hunt group Display Name");
        createHuntGroupPopup.getInputDisplName().setValue(huntGroup.getHuntGroupDisplayName());

        step("Select Hunt Group number");
        createHuntGroupPopup.getDropdownNumber().selectOption(1);

        step("Select hunt group Language");
        createHuntGroupPopup.getDropdownLanguage().selectOptionByValue(huntGroup.getHuntGroupLanguage());
        createHuntGroupPopup.getButtonSubmitEditHuntGroup().click();

        step("Save new Hunt Group");
        createHuntGroupPopup.getButtonSave().click();
        waitUntilAlertDisappear();

        step("Verify if new Hunt Group appeared in the grid");
        huntGroupPage.getfieldNameByText(huntGroup.getHuntGroupName()).should().exists();

        step("Delete created Hunt Group");
        huntGroupPage.getButtonDeleteByName(huntGroup.getHuntGroupName()).click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();

        step("Check if Hunt Group was deleted");
        huntGroupPage.getfieldNameByText(huntGroup.getHuntGroupName()).shouldNot().exists();

        step("Delete created user");
        deleteUser(user);
    }

    @Description("Verify if user can edit hunt group and configure Voicemail")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void VerifyIfUserCanEditHuntGroupAndConfigureVoicemail() {
        step("Prepare test data");
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        step("Login the system");
        login();

        step("Create Hunt Group");
        createHuntGroup(huntGroup);

        step("Open created Hunt group for edit");
        huntGroupPage.getButtonEditByName(huntGroup.getHuntGroupName()).click();
        waitUntilAlertDisappear();

        step("Click \"Edit Voicemail\" button");
        createHuntGroupPopup.getButtonSubmitVoicemail().click();

        step("Fill in PIN code");
        createHuntGroupPopup.getInputPin().setValue(huntGroup.getPinCode());

        step("Fill in email");
        createHuntGroupPopup.getInputEmail().setValue(huntGroup.getVoicemailEmail());

        step("Fill in Salutation for e-mails with voice messages");
        createHuntGroupPopup.getInputSalutation().setValue(huntGroup.getSalutation());

        step("Click Submit button");
        createHuntGroupPopup.getButtonSubmitVoicemail().click();

        step("Save all made changes");
        createHuntGroupPopup.getButtonSave().click();
        waitUntilAlertDisappear();

        step("Delete test data");
        deleteHuntGroup(huntGroup.getHuntGroupName());
    }

    @Description("Verify if user can edit hunt group and configure \"If end devices not available (not registered)\" section")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void VerifyIfUserCanEditHuntGroupAndConfigureIfEndDevicesNotAvailableSection() {
        step("Prepare test data");
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        step("Login the system");
        login();

        step("Create Hunt Group");
        createHuntGroup(huntGroup);

        step("Open created Hunt group for edit");
        huntGroupPage.editHuntGroup(huntGroup);

        step("Click \"Edit If end devices not available (not registered)\" button");
        createHuntGroupPopup.getButtonSubmitRelevantAccount().click();

        step("Select relevant account");
        createHuntGroupPopup.getDropdownRelevantAccount().selectOption(1);
        huntGroup.setRelevantAccount(createHuntGroupPopup.getDropdownRelevantAccount().getSelectedText());

        step("Select BackUp type - this number and fill in the number");
        createHuntGroupPopup.getDropdownBackUpType().selectOptionByValue("3");
        createHuntGroupPopup.getInputBackUpNumber().setValue(huntGroup.getBackUpNumber());
        createHuntGroupPopup.getButtonSubmitRelevantAccount().click();

        step("Save all entered data");
        createHuntGroupPopup.getButtonSave().click();
        waitUntilAlertDisappear();

        step("Verify if Relevant account was saved correctly");
        huntGroupPage.editHuntGroup(huntGroup);
        createHuntGroupPopup.getButtonSubmitRelevantAccount().click();
        Assert.assertEquals(huntGroup.getRelevantAccount(), createHuntGroupPopup.getDropdownRelevantAccount().getSelectedText());
        createHuntGroupPopup.getButtonClose().click();

        step("Delete test data");
        deleteHuntGroup(huntGroup.getHuntGroupName());
    }

    @Description("Verify if user can edit hunt group and configure \"Standard Timers\"")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void VerifyIfUserCanEditHuntGroupAndConfigureStandardTimers() {
        step("Prepare test data");
        HuntGroup huntGroup = new HuntGroup();
        FileManagementTestData announcement = new FileManagementTestData();
        Queue queue = new Queue();

        huntGroupsList.add(huntGroup);
        filesList.add(announcement);
        queueArrayList.add(queue);

        step("Login the system");
        login();

        step("Upload announcement announcement");
        uploadAnnouncementFile(announcement);

        step("Create Hunt Group");
        createHuntGroup(huntGroup);

        step("Create Queue");
        createQueueOnlyRequiredFields(queue);

        step("Open created Hunt group for edit");
        basePage.goToMenuTab(HUNT_GROUPS);
        huntGroupPage.editHuntGroup(huntGroup);

        step("Configure Standart Timers");
        createHuntGroupPopup.configureStandartTimers(announcement,queue);

        step("Verify Standart Timers configuration");
        huntGroupPage.editHuntGroup(huntGroup);
        createHuntGroupPopup.verifyStandartTimersConfiguration();
        refreshPage();

        step("Delete test data");
        deleteHuntGroup(huntGroup.getHuntGroupName());
        deleteQueue(queue.getName());
        deleteAnnouncementFile(announcement.getFileName());
    }

    @Description("Verify if user can configure \"Full days groups\"")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void VerifyIfUserCanConfigureFullDaysGroups() {
        step("Prepare test data");
        HuntGroup huntGroup = new HuntGroup();
        Queue queue = new Queue();
        FileManagementTestData announcement = new FileManagementTestData();

        filesList.add(announcement);
        huntGroupsList.add(huntGroup);
        queueArrayList.add(queue);

        step("Login the system");
        login();

        step("");
        uploadAnnouncementFile(announcement);

        step("Create Queue");
        //createQueue(queue);
        createQueueOnlyRequiredFields(queue);

        step("Create Hunt Group");
        createHuntGroup(huntGroup);

        step("Open created Hunt group for edit");
        huntGroupPage.getButtonEditByName(huntGroup.getHuntGroupName()).click();
        waitUntilAlertDisappear();

        step("Select full days in \"Huntgroup timer group\" drop-down");
        createHuntGroupPopup.getButtonAdd().click();

        step("Fill in Full Days name");
        addFullDaysPopup.getInputFullDay().setValue(huntGroup.getFullDayName());

        step("Fill in dates");
        addFullDaysPopup.getInputDates().setValue(huntGroup.getFullDayDate());

        step("Configure 1 Level: Immediately for NumberEndDevice");
        addFullDaysPopup.configureLevel("12", NumberEndDevice, huntGroup.getFullDayPhoneNumber());

        step("Configure 2 Level for VoiceMail busy");
        addFullDaysPopup.configureLevel("26", VoicemailBusy);

        step("Configure 3 Level for Queues");
        addFullDaysPopup.configureLevel("15", Queue, queue);

        step("Configure 4 Level for Announcements");
        addFullDaysPopup.configureLevel("44", Announcements, announcement);

        step("Save Full Days configuration");
        addFullDaysPopup.getButtonSave().click();
        createHuntGroupPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();

        step("Check if full days were saved");
        huntGroupPage.getButtonEditByName(huntGroup.getHuntGroupName()).click();
        waitUntilAlertDisappear();
        createHuntGroupPopup.getButtonEditFullDay().shouldBe(visible,Condition.enabled).click();
        createHuntGroupPopup.getInputFullDayName().shouldHave(Condition.value(huntGroup.getFullDayName()));
        createHuntGroupPopup.getInputFullDayDate().shouldHave(Condition.value(huntGroup.getFullDayDate()));
        refreshPage();

        step("Delete test data");
        deleteHuntGroup(huntGroup.getHuntGroupName());
        deleteAnnouncementFile(announcement.getFileName());
        deleteQueue(queue.getName());
    }

    @Description("Verify if user can configure \"Further Time groups\"")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void VerifyIfUserCanConfigureFurtherTimeGroups(){
        step("Prepare test data");
        HuntGroup huntGroup = new HuntGroup();
        Queue queue = new Queue();
        FileManagementTestData announcement = new FileManagementTestData();

        filesList.add(announcement);
        huntGroupsList.add(huntGroup);
        queueArrayList.add(queue);

        step("Login the system");
        login();

        step("");
        uploadAnnouncementFile(announcement);

        step("Create Queue");
        //createQueue(queue);
        createQueueOnlyRequiredFields(queue);

        step("Create Hunt Group");
        createHuntGroup(huntGroup);

        step("Open created Hunt group for edit");
        huntGroupPage.getButtonEditByName(huntGroup.getHuntGroupName()).click();
        waitUntilAlertDisappear();

        step("Select Time in Huntgroup timer group dropdown and click Add button");
        createHuntGroupPopup.getDropdownTimerGroup().selectOptionContainingText("Time");
        createHuntGroupPopup.getButtonAdd().click();
        waitUntilAlertDisappear();

        step("Fill in timers");
        addFurtherTimePopup.fillInTimers(huntGroup);

        step("Configure 1 Level: Immediately for NumberEndDevice");
        addFurtherTimePopup.configureLevel("12", NumberEndDevice, huntGroup.getFullDayPhoneNumber());

        step("Configure 2 Level for VoiceMail busy");
        addFurtherTimePopup.configureLevel("26", VoicemailBusy);

        step("Configure 3 Level for Queues");
        addFurtherTimePopup.configureLevel("15", Queue, queue);

        step("Configure 4 Level for Announcements");
        addFurtherTimePopup.configureLevel("44", Announcements, announcement);

        step("Save all changes");
        addFurtherTimePopup.getButtonSave().click();
        waitUntilAlertDisappear();
        createHuntGroupPopup.getButtonSave().click();
        refreshPage();

        step("Edit created HuntGroup");
        huntGroupPage.getButtonEditByName(huntGroup.getHuntGroupName()).click();
        waitUntilAlertDisappear();

        step("Check if Timers were saved");
        createHuntGroupPopup.getButtonEditFurtherTime().click();
        createHuntGroupPopup.checkIfFurtherTimersSaved(huntGroup);
        refreshPage();

        step("Delete test data");
        deleteHuntGroup(huntGroup.getHuntGroupName());
        deleteAnnouncementFile(announcement.getFileName());
        deleteQueue(queue.getName());
    }

    @Description("Verify if user is able to configure \"Calls recording\" for HuntGroup")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void VerifyIfUserIsAbleToConfigureCallsRecording(){
        step("Prepare test data");
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        step("Login the system");
        login();

        step("Create Hunt Group");
        createHuntGroup(huntGroup);

        step("Edit created hunt Group and activate \"Calls recording\"");
        huntGroupPage.openEditPopup(huntGroup);
        createHuntGroupPopup.activateCallRecordings();

        step("Verify if \"Calls recording\" was activated");
        huntGroupPage.verifyIfCallRecordingWasActivated(huntGroup);

        step("Delete test data");
        deleteHuntGroup(huntGroup.getHuntGroupName());
    }

    @Description("Verify if user is able to RE-Configure HuntGroup authorized users")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void VerifyIfUserIsAbleToReconfigureHuntgroupEditSection(){
        User user1 = new User();
        User user2 = new User();
        HuntGroup huntGroup = new HuntGroup();

        huntGroupsList.add(huntGroup);
        usersList.add(user1);
        usersList.add(user2);

        login();
        userPage
                .createUser(user1,user2);
        basePage
                .goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .createHuntGroup(huntGroup)
                .addAuthorizedUserToHuntGroup(huntGroup,user1);
        huntGroupPage
                .editHuntGroup(huntGroup)
                .unassignAuthorizedUser(user1)
                .selectAuthorizedUser(user2)
                .saveChanges()
                .editHuntGroup(huntGroup)
                .validateSelectedAuthorizedUsers(user2)
                .refreshPage();

        deleteUser(user1,user2);
        deleteHuntGroup(huntGroup.getHuntGroupName());
    }

    @Description("Verify if after changing HuntGroup name - changed data is shown in the grid")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void verifyIfUpdatedNameShownInGrid(){
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        login();
        huntGroupPage
                .createHuntGroup(huntGroup)
                .editHuntGroup(huntGroup)
                .clickEditButtonForEditHuntGroupSection()
                .setName(huntGroup.changeHuntGroupName())
                .saveChanges()
                .verifyIfHuntGroupNameExists(huntGroup.getHuntGroupName())
                .deleteHuntGroup(huntGroup);

    }

    //- BUG 975
    @Description("Verify if after changing HuntGroup Display Name - changed data is shown in the grid - BUG 975")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"}, enabled = false)
    public void verifyIfUpdatedDisplayNameShownInGrid(){
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        login();
        huntGroupPage
                .createHuntGroup(huntGroup)
                .editHuntGroup(huntGroup)
                .clickEditButtonForEditHuntGroupSection()
                .setDisplayName(huntGroup.changeDisplayName())
                .saveChanges()
                .verifyIfHuntGroupDisplayNameExists(huntGroup.getHuntGroupDisplayName())
                .deleteHuntGroup(huntGroup);
    }

    //- BUG 975
    @Description("Verify if after changing HuntGroup phonenumber - is shown in the grid- BUG 975")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"}, enabled = false)
    public void VerifyIfHuntGroupPhoneShownIngridAfterChanging(){
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        login();
        huntGroupPage
                .createHuntGroup(huntGroup)
                .editHuntGroup(huntGroup)
                .clickEditButtonForEditHuntGroupSection()
                .selectNumber(huntGroup.changeNumber())
                .saveChanges()
                .verifyIfHuntGroupNumberExists(huntGroup.getHuntGroupNumber())
                .deleteHuntGroup(huntGroup);
    }

    //- BUG 975
    @Description("Verify if after deleting, HuntGroup phonenumber - is not shown in the BlockList dropdown- BUG 975")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"}, enabled = false)
    public void verifyIfDeletedHuntgroupNumbersNotShownInBlocklistDropDown(){
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        login();
        huntGroupPage
                .createHuntGroup(huntGroup);
        blockListSections
                .dropdownContainsNumber(huntGroup.getHuntGroupNumber());
        huntGroupPage
                .deleteHuntGroup(huntGroup.getHuntGroupName());
        blockListSections
                .dropdownNotContainsNumber(huntGroup.getHuntGroupNumber());
    }


    @AfterClass(alwaysRun = true)
    private void cleanUp() {
        startBrowser();
        login();
        huntGroupCleanUp(huntGroupsList);
        userCleanUp(usersList);
        queueCleanUp(queueArrayList);
        announcementCleanUp(filesList);
        closeBrowser();
    }
}
