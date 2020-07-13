package tests.blockListSectionTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.IVRpageTests.IVRtestData.IVRtestData;
import tests.blockListSectionTests.blockListTestData.BlockListTestData;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.сonferenceCallsPageTests.ConferenceCallTestData.ConferenceCallTestData;

import java.util.ArrayList;

import static api.baseApiMethods.ConferenceCallsApi.createConferenceCallApi;
import static api.baseApiMethods.ConferenceCallsApi.deleteConferenceCallApi;
import static api.baseApiMethods.HuntGroupApi.createHuntGroupApi;
import static api.baseApiMethods.HuntGroupApi.deleteHuntGroupApi;
import static api.baseApiMethods.IVRApi.createIvrApi;
import static api.baseApiMethods.IVRApi.deleteIvrApi;
import static pages.basePage.BasePage.MenuTabsBasePage.*;

@Listeners(CustomListeners.class)

public class BlockListTests extends BaseTestMethods {

    //<editor-fold desc="Lists">
    ArrayList<HuntGroup> huntGroupsList = new ArrayList<>();
    ArrayList<IVRtestData> ivrList = new ArrayList<>();
    ArrayList<FileManagementTestData> announcementList = new ArrayList<>();
    ArrayList<ConferenceCallTestData> confCallList = new ArrayList<>();
    //</editor-fold>

    //bug 974
    @Description("Check Block incoming calls configurations")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"},enabled = false)
    public void configBlockIncomingCallsTest (){
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        createHuntGroupApi(huntGroup);
        login()
                .goToMenuTab(HUNT_GROUPS);
        blockListSections
                .selectNumber(huntGroup.getHuntGroupNumber())
                .clickBlockIncomingCalls()
                .selectForwardTo("Voicemail")
                .saveChanges()
                .refreshPage();
        blockListSections
                .selectNumber(huntGroup.getHuntGroupNumber())
                .verifyBlockIncomingCallsConfig();
        deleteHuntGroupApi(huntGroup);
    }

    @Description("Check Calls with suppressed numbers configurations")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void configCallsWithSuppressedNumbersTest (){
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        createHuntGroupApi(huntGroup);

        login()
                .goToMenuTab(HUNT_GROUPS);
        blockListSections
                .selectNumber(huntGroup.getHuntGroupNumber())
                .clickBlockIncomingCalls()
                .selectForwardTo("Voicemail")
                .clickCallsWithSuppressedNumbers()
                .saveChanges()
                .refreshPage();
        blockListSections
                .selectNumber(huntGroup.getHuntGroupNumber())
                .verifyCallsWithSuppressedNumbers();

        deleteHuntGroupApi(huntGroup);
    }

    //974
    @Description("Check Use blocklist configurations")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"},enabled = false)
    public void configUseBlocklistTest (){
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        createHuntGroupApi(huntGroup);

        login()
                .goToMenuTab(HUNT_GROUPS);
        blockListSections
                .selectNumber(huntGroup.getHuntGroupNumber())
                .activateUseBlocklist()
                .saveChanges()
                .refreshPage();
        blockListSections
                .selectNumber(huntGroup.getHuntGroupNumber())
                .verifyUseBlocklistConfigs();

        deleteHuntGroupApi(huntGroup);
    }

    @Description("Check Add/Delete blocked number functionality")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void addDeleteBlockedNumberTest (){

        BlockListTestData blockList = new BlockListTestData();
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        createHuntGroupApi(huntGroup);

        login()
                .goToMenuTab(HUNT_GROUPS);
        blockListSections
                .selectNumber(huntGroup.getHuntGroupNumber())
                .activateUseBlocklist()
                .clickEdit()
                    .clickAdd()
                    .enterNumber(blockList.getBlockedNumber())
                    .enterComment(blockList.getComment())
                    .saveNumber()
                .clickClose()
                .clickEdit()
                .bulkDeleteNumber(blockList.getBlockedNumber())
                .verifyNumberNotExist(blockList.getBlockedNumber());

        deleteHuntGroupApi(huntGroup);
    }

    @Description("Check Edit blocked number functionality")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void editBlockedNumberTest (){

        BlockListTestData blockList = new BlockListTestData();
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        createHuntGroupApi(huntGroup);

        login()
                .goToMenuTab(HUNT_GROUPS);
        blockListSections
                .selectNumber(huntGroup.getHuntGroupNumber())
                .activateUseBlocklist()
                .clickEdit()
                .addNumber(blockList.getBlockedNumber())
                .clickClose()
                .clickEdit()
                .clickEditNumber(blockList.getBlockedNumber())
                    .editNumber(blockList.changeBlockedNumber())
                    .saveChanges()
                .clickClose()
                .clickEdit()
                .verifyNumberExists(blockList.getBlockedNumber());

        deleteHuntGroupApi(huntGroup);
    }

    @Description("Check delete blocked number functionality")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void deleteBlockedNumberTest (){

        BlockListTestData blockList = new BlockListTestData();
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        createHuntGroupApi(huntGroup);

        login()
                .goToMenuTab(HUNT_GROUPS);
        blockListSections
                .selectNumber(huntGroup.getHuntGroupNumber())
                .activateUseBlocklist()
                .clickEdit()
                .addNumber(blockList.getBlockedNumber())
                .clickClose()
                .clickEdit()
                .deleteNumber(blockList.getBlockedNumber())
                .clickClose()
                .clickEdit()
                .verifyNumberNotExist(blockList.getBlockedNumber());

        deleteHuntGroupApi(huntGroup);
    }

    @Description("Check after configuring blocklist and deleting HuntGruops phone number can be re-used")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void phoneNumberReUseHuntGroupTest(){
        HuntGroup huntGroup = new HuntGroup();
        HuntGroup huntGroup2 = new HuntGroup(getRandomString(15), huntGroup.getHuntGroupNumber());
        huntGroupsList.add(huntGroup);
        huntGroupsList.add(huntGroup2);

        createHuntGroupApi(huntGroup);

        login()
                .goToMenuTab(HUNT_GROUPS);
        blockListSections
                .selectNumber(huntGroup.getHuntGroupNumber())
                .activateUseBlocklist()
                .saveChanges();
        huntGroupPage
                .deleteHuntGroup(huntGroup)
                .createHuntGroup(huntGroup2);

        deleteHuntGroupApi(huntGroup,huntGroup2);
    }

    @Description("Check if after configuring blocklist and deleting IVRs phone number can be re-used")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void phoneNumberReUseIVRTest(){

        IVRtestData ivr = new IVRtestData(new FileManagementTestData());
        IVRtestData ivr2 = new IVRtestData(ivr.getIvrNumber(), ivr.getAnnouncement());
        ivrList.add(ivr);
        ivrList.add(ivr2);
        announcementList.add(ivr.getAnnouncement());

        createIvrApi(ivr);

        login()
                .goToMenuTab(IVRs);
        blockListSections
                .selectNumber(ivr.getIvrNumber())
                .activateUseBlocklist();
        ivrPage
                .deleteIvr(ivr)
                .createIvr(ivr2,ivr2.getAnnouncement());

        deleteIvrApi(ivr,ivr2);
    }

    //bug EPRO-1128
    @Description("Check if after creating and deleting Conference Call phone number can be re-used")
    @Test(/*retryAnalyzer = RetryAnalyzer.class,*/ groups = {"regression", "huntGroupsPageTests"},enabled = false)
    public void phoneNumberReUseConferenceCallTest(){
        ConferenceCallTestData confCall = new ConferenceCallTestData();
        ConferenceCallTestData confCall2 = new ConferenceCallTestData(confCall.getConferenceNumber());
        confCallList.add(confCall);
        confCallList.add(confCall2);

        createConferenceCallApi(confCall);

        login()
                .goToMenuTab(CONFERENCE_CALLS);
        blockListSections
                .selectNumber(confCall.getConferenceNumber())
                .activateUseBlocklist()
                .saveChanges();
        conferenceCallsPage
                .deleteConfCall(confCall)
                .createConfCall(confCall2);

        deleteConferenceCallApi(confCall,confCall2);
    }

    @Description("Check if user can configure \"Permitted numbers\"")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void configPermittedNumbers(){
        BlockListTestData blockListData = new BlockListTestData();
        IVRtestData ivr = new IVRtestData(new FileManagementTestData());
        ivrList.add(ivr);

        createIvrApi(ivr);
        login()
                .goToMenuTab(IVRs);
        blockListSections
                .selectNumber(ivr.getIvrNumber())
                .clickBlockIncomingCalls()
                .selectForwardTo("Voicemail")
                .activateUseBlocklist()
                .selectBlockListType("Permitted numbers")
                .clickEdit()
                    .clickAdd()
                    .enterNumber(blockListData.getPermittedNumber())
                    .enterComment(blockListData.getComment())
                    .saveNumber()
                    .clickClose()
                .verifyIfNumberShown(blockListData.getPermittedNumber())
                .clickEdit()
                    .deleteNumber(blockListData.getPermittedNumber())
                    .clickClose()
                .clickEdit()
                    .verifyNumberNotExist(blockListData.getPermittedNumber());
        deleteIvrApi(ivr);
    }


    @AfterClass(alwaysRun = true)
    private void cleanUp() {
        ivrCleanUp(ivrList);
        huntGroupCleanUp(huntGroupsList);
        announcementCleanUp(announcementList);
        cleanUpConfCalls(confCallList);
    }

}
