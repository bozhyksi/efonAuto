package tests.IVRpageTests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.basePage.BasePage;
import tests.IVRpageTests.IVRtestData.BlockListTestData;
import tests.IVRpageTests.IVRtestData.IVRtestData;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.queuesPageTest.queueTestData.Queue;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.selectedText;
import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.IVRs;
import static tests.IVRpageTests.IVRtestData.IVRtestData.EventNumber.Event_1;
import static tests.IVRpageTests.IVRtestData.IVRtestData.EventNumber.Event_2;
import static tests.IVRpageTests.IVRtestData.IVRtestData.IvrActions.*;
import static tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling.Type.SINGLE;

@Listeners(CustomListeners.class)

public class IVRpageTests extends BaseTestMethods {
    private ArrayList<IVRtestData> ivrList = new ArrayList<>();
    private ArrayList<FileManagementTestData> filesList = new ArrayList<>();
    private ArrayList<HuntGroup> huntGroupsList = new ArrayList<>();
    private ArrayList<Queue> queueList = new ArrayList<>();
    private ArrayList<User> usersList = new ArrayList<>();
    private ArrayList<AbbreviatedDialling> shortNumList = new ArrayList<>();

    @Description("Verify if user can create new IVR")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "IVRpageTests"})
    public void VerifyIfUserCanCreateNewIvr() {
        step("Prepare test data - create IVR object");
        IVRtestData ivr = new IVRtestData();
        FileManagementTestData file = new FileManagementTestData();
        ivrList.add(ivr);
        filesList.add(file);

        step("Log in the system");
        login();

        step("Upload announcement");
        uploadAnnouncementFile(file);

        step("Goto IVR page");
        basePage.goToMenuTab(IVRs);

        step("Click \"New IVR\" button");
        ivrPage.getButtonNewIvr().click();

        step("Fill in \"Name\" field");
        createNewIvrPopup.getInputName().setValue(ivr.getIvrName());

        step("Fill in \"Display Nane\" field");
        createNewIvrPopup.getInputDisplayName().setValue(ivr.getIvrDisplName());

        step("Select English language");
        createNewIvrPopup.getDropdownLanguage().selectOptionByValue(ivr.getIvrLanguage());

        step("Select number");
        createNewIvrPopup.getDropdownSelectIvrNumber().selectOption(1);

        step("Select announcement");
        createNewIvrPopup.getDropdownSelectAnnounc().selectOption(1);

        step("Save all changes");
        createNewIvrPopup.getButtonSave().click();
        waitUntilAlertDisappear();

        step("Check if IVR is displayed in the grid");
        ivrPage.getListName().filterBy(Condition.text(ivr.getIvrName())).shouldHave(CollectionCondition.sizeGreaterThan(0));

        step("Delete created IVR");
        ivrPage.getButtonDeleteIvrByName(ivr.getIvrName()).click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();

        step("Check if IVR was deleted in the grid");
        ivrPage.getListName().filterBy(Condition.text(ivr.getIvrName())).shouldHave(CollectionCondition.size(0));

        step("Delete announcement file");
        deleteAnnouncementFile(file.getFileName());
    }

    @Description("Verify if user can EDIT new IVR")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "IVRpageTests"})
    public void VerifyIfUserCanEditNewIvr() {
        step("Prepare test data - create IVR object");
        String displName = getRandomString(10);
        IVRtestData ivr = new IVRtestData();
        FileManagementTestData file = new FileManagementTestData();
        ivrList.add(ivr);
        filesList.add(file);

        step("Log in the system");
        login();

        step("Upload announcement");
        uploadAnnouncementFile(file);

        step("Create new IVR");
        createIVR(ivr, file);

        step("Click edit button");
        ivrPage.getButtonEditIvrByName(ivr.getIvrName()).click();
        waitUntilAlertDisappear();

        step("Display name");
        createNewIvrPopup.getInputDisplayName().setValue(displName);

        step("Save changed data");
        createNewIvrPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();

        step("Check if saved data are displayed correctly in the grid");
        ivrPage.getListDisplayName().filterBy(Condition.text(displName)).shouldHave(CollectionCondition.sizeGreaterThan(0));

        step("Delete created test data");
        deleteIVR(ivr.getIvrName());

        step("Delete announcement file");
        deleteAnnouncementFile(file.getFileName());
    }

    @Description("Verify if user can configure \"Call to external subscriber\" ivr action")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"})
    public void VerifyIfUserCanConfigureCallExternalNumberAction() {
        step("Prepare test data - create IVR object");
        IVRtestData ivr = new IVRtestData();
        FileManagementTestData file = new FileManagementTestData();
        ivrList.add(ivr);
        filesList.add(file);

        step("Log in the system");
        login();

        step("Upload announcement");
        uploadAnnouncementFile(file);

        step("Create IVR");
        createIVR(ivr, file);

        step("Click edit button");
        ivrPage.editIVR(ivr.getIvrName());

        step("Configure Call to external subscriber action");
        ivrPage.configureAction(ivr,Event_1, PHONE_EXTERNAL);

        step("Save changes");
        createNewIvrPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();

        step("Check if all data was saved");
        ivrPage.editIVR(ivr.getIvrName());
        createNewIvrPopup.getCheckboxActiveByEventNumber(ivr.getEventNumber()).shouldBe(selected);
        createNewIvrPopup.getDropdownActionByEventNumber(ivr.getEventNumber()).getSelectedValue().contains(ivr.getAction());
        createNewIvrPopup.getInputParameterByEventNumber(ivr.getEventNumber()).getValue().contains(ivr.getParameterExtTelNumber());
        refreshPage();
        waitUntilAlertDisappear();

        step("CleanUp test data");
        deleteIVR(ivr.getIvrName());
        deleteAnnouncementFile(file.getFileName());
    }

    @Description("Verify if user can configure \"Hunt Group\" ivr action")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"})
    public void VerifyIfUserCanConfigureHuntGroupIvrAction() {
        step("Prepare test data - create IVR object");
        IVRtestData ivr = new IVRtestData();
        FileManagementTestData file = new FileManagementTestData();
        HuntGroup huntGroup = new HuntGroup();

        ivrList.add(ivr);
        filesList.add(file);
        huntGroupsList.add(huntGroup);

        step("Log in the system");
        login();

        step("Upload announcement");
        uploadAnnouncementFile(file);

        step("Create Hunt Group");
        createHuntGroup(huntGroup);

        step("Create IVR");
        createIVR(ivr, file);

        step("Click edit button");
        ivrPage.editIVR(ivr.getIvrName());

        step("Configure HuntGroup action");
        createNewIvrPopup.configureAction(ivr, Event_1, RINGRUF, huntGroup);

        step("Save changes");
        createNewIvrPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();

        step("Check if data was saved");
        ivrPage.editIVR(ivr.getIvrName());
        waitUntilAlertDisappear();
        createNewIvrPopup.getCheckboxActiveByEventNumber(ivr.getEventNumber()).shouldBe(selected);
        createNewIvrPopup.getDropdownActionByEventNumber(ivr.getEventNumber()).getSelectedValue().contains(ivr.getAction());
        createNewIvrPopup.getDropdownParameterByEventNumber(ivr.getEventNumber()).getSelectedText().contains(ivr.getParameterHuntGroup());
        refreshPage();
        waitUntilAlertDisappear();


        step("CleanUp test data");
        deleteIVR(ivr.getIvrName());
        deleteHuntGroup(huntGroup.getHuntGroupName());
        deleteAnnouncementFile(file.getFileName());
    }

    @Description("Verify if user can configure \"Queues\" ivr action")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"})
    public void VerifyIfUserCanConfigureQueuesIvrAction() {
        Queue queue = new Queue();
        FileManagementTestData announcement = new FileManagementTestData();
        AbbreviatedDialling shortNumber = new AbbreviatedDialling(SINGLE);
        IVRtestData ivr = new IVRtestData();
        User user = new User();
        usersList.add(user);
        queueList.add(queue);
        filesList.add(announcement);
        shortNumList.add(shortNumber);
        ivrList.add(ivr);

        login();
        userPage
                .createUser(user);
        manageAbbrevNumbersPage
                .addSingleAbbrevNumber(shortNumber);
        configureQueueTab
                .createQueue(queue)
                .clickEditQueueButton(queue)
                .selectLoginShortNum(shortNumber)
                .saveChanges()
                .openQueueAgentPopup(queue)
                .addAgentToQueue(user);
        announcementDisplayPage
                .uploadAnnouncement(announcement);
        ivrPage
                .createIvr(ivr, announcement)
                .clickEditIvr(ivr)
                .configureQueueAction(queue)
                .saveChanges()
                .deleteIvr(ivr);
        configureQueueTab
                .deleteQueue(queue);
        userPage
                .deleteUser(user);
        abbreviatedNumbers
                .deleteSingleAbbrevNumber(shortNumber);
        announcementDisplayPage
                .deleteAnnouncement(announcement);
    }

    @Description("Verify if user can configure \"Call to direct number\" ivr action")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"})
    public void VerifyIfUserCanConfigureCallToDirectNumberIvrAction() {
        step("Prepare test data - create IVR object");
        IVRtestData ivr = new IVRtestData();
        FileManagementTestData file = new FileManagementTestData();
        User user = new User();

        ivrList.add(ivr);
        filesList.add(file);
        usersList.add(user);


        step("Log in the system");
        login();

        step("Upload announcement");
        uploadAnnouncementFile(file);

        step("Create Hunt Group");
        createUser(user);

        step("Create IVR");
        createIVR(ivr, file);

        step("Click edit button");
        ivrPage.editIVR(ivr.getIvrName());

        step("Configure HuntGroup action");
        createNewIvrPopup.configureAction(ivr, Event_2, PHONE_DIRECT, user);

        step("Save changes");
        createNewIvrPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();

        step("Check if data was saved");
        ivrPage.editIVR(ivr.getIvrName());
        waitUntilAlertDisappear();
        createNewIvrPopup.getCheckboxActiveByEventNumber(ivr.getEventNumber()).shouldBe(selected);
        createNewIvrPopup.getDropdownActionByEventNumber(ivr.getEventNumber()).getSelectedValue().contains(ivr.getAction());
        createNewIvrPopup.getDropdownParameterByEventNumber(ivr.getEventNumber()).getSelectedText().contains(ivr.getParameterPhoneDirect());
        refreshPage();
        waitUntilAlertDisappear();


        step("CleanUp test data");
        deleteIVR(ivr.getIvrName());
        deleteUser(user);
        deleteAnnouncementFile(file.getFileName());
    }

    @Description("Verify if user can configure \"Call to External number\" ivr action")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"})
    public void VerifyIfUserCanConfigureCallToExternalNumberIvrAction() {
        step("Prepare test data - create IVR object");
        IVRtestData ivr = new IVRtestData();
        FileManagementTestData file = new FileManagementTestData();

        ivrList.add(ivr);
        filesList.add(file);

        step("Log in the system");
        login();

        step("Upload announcement");
        uploadAnnouncementFile(file);

        step("Create IVR");
        createIVR(ivr, file);

        step("Click edit button");
        ivrPage.editIVR(ivr.getIvrName());

        step("Configure HuntGroup action");
        createNewIvrPopup.configureAction(ivr, Event_2, PHONE_EXTERNAL);

        step("Save changes");
        createNewIvrPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();

        step("Check if data was saved");
        ivrPage.editIVR(ivr.getIvrName());
        waitUntilAlertDisappear();
        createNewIvrPopup.getCheckboxActiveByEventNumber(ivr.getEventNumber()).shouldBe(selected);
        createNewIvrPopup.getDropdownActionByEventNumber(ivr.getEventNumber()).getSelectedValue().contains(ivr.getAction());
        createNewIvrPopup.getInputParameterByEventNumber(ivr.getEventNumber()).getText().contains(ivr.getParameterExtTelNumber());
        refreshPage();
        waitUntilAlertDisappear();


        step("CleanUp test data");
        deleteIVR(ivr.getIvrName());
        deleteAnnouncementFile(file.getFileName());
    }

    @Description("Verify if user can configure \"Voicemail: no announcement\" ivr action")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"})
    public void VerifyIfUserCanConfigureVoicemailNoAnnouncementIvrAction() {
        step("Prepare test data - create IVR object");
        IVRtestData ivr = new IVRtestData();
        FileManagementTestData file = new FileManagementTestData();

        ivrList.add(ivr);
        filesList.add(file);

        step("Log in the system");
        login();

        step("Upload announcement");
        uploadAnnouncementFile(file);

        step("Create IVR");
        createIVR(ivr, file);

        step("Click edit button");
        ivrPage.editIVR(ivr.getIvrName());

        step("Configure HuntGroup action");
        createNewIvrPopup.configureAction(ivr, Event_2, VM_NO_ANNOUNCE);

        step("Save changes");
        createNewIvrPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();

        step("Check if data was saved");
        ivrPage.editIVR(ivr.getIvrName());
        waitUntilAlertDisappear();
        createNewIvrPopup.getCheckboxActiveByEventNumber(ivr.getEventNumber()).shouldBe(selected);
        createNewIvrPopup.getDropdownActionByEventNumber(ivr.getEventNumber()).getSelectedValue().contains(ivr.getAction());
        refreshPage();
        waitUntilAlertDisappear();

        step("CleanUp test data");
        deleteIVR(ivr.getIvrName());
        deleteAnnouncementFile(file.getFileName());
    }

    @Description("Verify if user can configure \"Play file and hang up\" ivr action")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"})
    public void VerifyIfUserCanConfigurePlayFileAndHangUpIvrAction() {
        step("Prepare test data - create IVR object");
        IVRtestData ivr = new IVRtestData();
        FileManagementTestData file = new FileManagementTestData();

        ivrList.add(ivr);
        filesList.add(file);

        step("Log in the system");
        login();

        step("Upload announcement");
        uploadAnnouncementFile(file);

        step("Create IVR");
        createIVR(ivr, file);

        step("Click edit button");
        ivrPage.editIVR(ivr.getIvrName());

        step("Configure HuntGroup action");
        createNewIvrPopup.configureAction(ivr, Event_2, PLAY_HANGUP, file);

        step("Save changes");
        createNewIvrPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();

        step("Check if data was saved");
        ivrPage.editIVR(ivr.getIvrName());
        waitUntilAlertDisappear();
        createNewIvrPopup.getCheckboxActiveByEventNumber(ivr.getEventNumber()).shouldBe(selected);
        createNewIvrPopup.getDropdownActionByEventNumber(ivr.getEventNumber()).getSelectedValue().contains(ivr.getAction());
        createNewIvrPopup.getDropdownParameterByEventNumber(ivr.getEventNumber()).getSelectedText().contains(ivr.getParameterPlayAndHangUp());
        refreshPage();
        waitUntilAlertDisappear();

        step("CleanUp test data");
        deleteIVR(ivr.getIvrName());
        deleteAnnouncementFile(file.getFileName());
    }

    @Description("Verify if user can configure Block list section on IVR Page")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"})
    public void VerifyIfUserCanConfigureBlockListSectionOnIvrPage(){
        step("Prepare test data - create IVR object");
        IVRtestData ivr = new IVRtestData();
        FileManagementTestData file = new FileManagementTestData();

        ivrList.add(ivr);
        filesList.add(file);

        step("Log in the system");
        login();

        step("Upload announcement");
        uploadAnnouncementFile(file);

        step("Create IVR");
        createIVR(ivr, file);

        step("Configure Block list section - select number");
        blockListSection.getDropdownNumbers().selectOptionContainingText(ivr.getIvrNumber());

        step("Activate \"Block incoming calls\" and select \"Forward to\" option");
        blockListSection.getCheckboxBlockIncomingCalls().click();
        blockListSection.getDropdownForwardTo().selectOptionByValue("VOICEMAIL");

        step("Activate option \"Calls with suppressed numbers\"");
        blockListSection.getCheckboxCallsSuppressedNumbers().click();

        step("Activate option \"Use blocklist\"");
        blockListSection.getCheckboxUseBlocklist().click();
        blockListSection.getDropdownBlocklistType().selectOptionByValue("BLOCKED_NUMBERS");

        step("Save and verify");
        blockListSection.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();

        blockListSection.getDropdownNumbers().selectOptionContainingText(ivr.getIvrNumber());
        blockListSection.getCheckboxBlockIncomingCalls().shouldBe(selected);
        blockListSection.getDropdownForwardTo().getSelectedValue().contains("VOICEMAIL");
        blockListSection.getCheckboxCallsSuppressedNumbers().shouldBe(selected);
        //blockListSection.getCheckboxUseBlocklist().shouldBe(selected);
        //blockListSection.getDropdownBlocklistType().getSelectedValue().contains("BLOCKED_NUMBERS");

        step("CleanUp test data");
        deleteIVR(ivr.getIvrName());
        deleteAnnouncementFile(file.getFileName());

    }

    @Description("Verify if user can add/delete numbers to Block list on IVR Page")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"})
    public void VerifyIfUserCanAddDeleteNumbersToBlockListOnIvrPage(){
        step("Prepare test data - create IVR object");
        IVRtestData ivr = new IVRtestData();
        FileManagementTestData file = new FileManagementTestData();
        BlockListTestData blockList = new BlockListTestData();

        ivrList.add(ivr);
        filesList.add(file);

        step("Log in the system");
        login();

        step("Upload announcement");
        uploadAnnouncementFile(file);

        step("Create IVR");
        createIVR(ivr, file);

        step("Configure block list");
        configureBlockList(ivr, blockList);

        step("Validate block list");
        blockListSection.getButtonEditBlocklist().click();
        waitUntilAlertDisappear();
        blocklistPopup.verifyBlockListNumber(blockList);
        blocklistPopup.getButtonClose().click();
        waitUntilAlertDisappear();

        step("Save");
        blockListSection.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();

        step("Delete number from Block list");
        blockListSection.getDropdownNumbers().selectOptionContainingText(ivr.getIvrNumber());
        blockListSection.getButtonEditBlocklist().click();
        waitUntilAlertDisappear();
        blocklistPopup.deleteNumberFromBlockList(blockList);
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        blocklistPopup.verifyNumberDeletedFromBlockList(blockList);
        refreshPage();

        step("CleanUp test data");
        deleteIVR(ivr.getIvrName());
        deleteAnnouncementFile(file.getFileName());
    }

    @Description("Verify if user can create/delete IVRs")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"})
    public void createDeleteIvrTest(){

        IVRtestData ivr = new IVRtestData();
        FileManagementTestData announcement = new FileManagementTestData();
        filesList.add(announcement);
        ivrList.add(ivr);

        login();
        uploadAnnouncementFile(announcement);
        basePage
                .goToMenuTab(IVRs);
        ivrPage
                .clickNewIvr()
                .enterIvrName(ivr.getIvrName())
                .enterDisplayName(ivr.getIvrDisplName())
                .selectLanguage(ivr.getIvrLanguage())
                .selectNumber(ivr.getIvrNumber())
                .selectAnnouncement(announcement.getFileName())
                .saveChanges()
                .verifyIfIvrExists(ivr.getIvrName())
                .deleteIvr(ivr);
        deleteAnnouncementFile(announcement.getFileName());
    }

    @Description("Verify if user can configure IVRs actions")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"})
    public void configureIvrActionsTest(){

        IVRtestData ivr = new IVRtestData();
        HuntGroup huntGroup = new HuntGroup();
        FileManagementTestData announcement = new FileManagementTestData();
        User user = new User();

        filesList.add(announcement);
        ivrList.add(ivr);
        usersList.add(user);
        huntGroupsList.add(huntGroup);

        login();
        uploadAnnouncementFile(announcement);
        createHuntGroup(huntGroup);

        userPage
                .createUser(user);
        ivrPage
                .createIvr(ivr, announcement);
        ivrPage
                .clickEditIvr(ivr)
                .configureHuntGroupAction(huntGroup)
                .configurePhoneDirectAction(user)
                .configureExternalDirectAction(ivr.getParameterExtTelNumber())
                .configureVoiceMailUnavailableAction()
                .configureVoiceMailNoAnnouncementAction()
                .configureVoiceMailOfSubscriberAction(user)
                .configureMaxPassesReachedAction()
                .configureInvalidChoiceAction()
                .configureTimeExpiredAction()
                .configurePlayAndHangUpAction(announcement)
                .configurePlayAndRestartAction(announcement)
                .configureRestartMenuAction()
                .saveChanges();
        ivrPage
                .deleteIvr(ivr);
        userPage
                .deleteUser(user);
        deleteAnnouncementFile(announcement.getFileName());
        deleteHuntGroup(huntGroup.getHuntGroupName());
    }


    @AfterClass(alwaysRun = true)
    private void cleanUp() {
        startBrowser();
        login();
        ivrCleanUp(ivrList);
        userCleanUp(usersList);
        huntGroupCleanUp(huntGroupsList);
        queueCleanUp(queueList);
        announcementCleanUp(filesList);
        abbrevNumsCleanUp(shortNumList);
        closeBrowser();
    }
}
