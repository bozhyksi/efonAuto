package tests.blockListSectionTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.blockListSectionTests.blockListTestData.BlockListTestData;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;

import java.util.ArrayList;

@Listeners(CustomListeners.class)

public class BlockListTests extends BaseTestMethods {

    ArrayList<HuntGroup> huntGroupsList = new ArrayList<>();

    //bug 974
    @Description("Check Block incoming calls configurations")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"}, enabled = false)
    public void configBlockIncomingCallsTest (){
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        login();
        huntGroupPage
                .createHuntGroup(huntGroup);
        blockListSections
                .selectNumber(huntGroup.getHuntGroupNumber())
                .clickBlockIncomingCalls()
                .selectForwardTo("Voicemail")
                .saveChanges()
                .refreshPage();
        blockListSections
                .selectNumber(huntGroup.getHuntGroupNumber())
                .verifyBlockIncomingCallsConfig();
        huntGroupPage
                .deleteHuntGroup(huntGroup);

    }

    @Description("Check Calls with suppressed numbers configurations")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void configCallsWithSuppressedNumbersTest (){
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        login();
        huntGroupPage
                .createHuntGroup(huntGroup);
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
        huntGroupPage
                .deleteHuntGroup(huntGroup);

    }

    @Description("Check Use blocklist configurations")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void configUseBlocklistTest (){
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        login();
        huntGroupPage
                .createHuntGroup(huntGroup);
        blockListSections
                .selectNumber(huntGroup.getHuntGroupNumber())
                .activateUseBlocklist()
                .saveChanges()
                .refreshPage();
        blockListSections
                .selectNumber(huntGroup.getHuntGroupNumber())
                .verifyUseBlocklistConfigs();
        huntGroupPage
                .deleteHuntGroup(huntGroup);

    }

    //EPRO-1078
    @Description("Check Add/Delete blocked number functionality")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"},enabled = false)
    public void addDeleteBlockedNumberTest (){

        BlockListTestData blockList = new BlockListTestData();
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        login();
        huntGroupPage
                .createHuntGroup(huntGroup);
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
                .verifyBlockedNumberNotExists(blockList.getBlockedNumber());
        huntGroupPage
                .deleteHuntGroup(huntGroup);
    }

    @Description("Check Edit blocked number functionality")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void editBlockedNumberTest (){

        BlockListTestData blockList = new BlockListTestData();
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        login();
        huntGroupPage
                .createHuntGroup(huntGroup);
        blockListSections
                .selectNumber(huntGroup.getHuntGroupNumber())
                .activateUseBlocklist()
                .clickEdit()
                .addBlocklistNumber(blockList.getBlockedNumber())
                .clickClose()
                .clickEdit()
                .clickEditNumber(blockList.getBlockedNumber())
                    .editNumber(blockList.changeBlockedNumber())
                    .saveChanges()
                .clickClose()
                .clickEdit()
                .verifyBlockedNumberExists(blockList.getBlockedNumber())
                .refreshPage();
        huntGroupPage
                .deleteHuntGroup(huntGroup);
    }

    @Description("Check delete blocked number functionality")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupsPageTests"})
    public void deleteBlockedNumberTest (){

        BlockListTestData blockList = new BlockListTestData();
        HuntGroup huntGroup = new HuntGroup();
        huntGroupsList.add(huntGroup);

        login();
        huntGroupPage
                .createHuntGroup(huntGroup);
        blockListSections
                .selectNumber(huntGroup.getHuntGroupNumber())
                .activateUseBlocklist()
                .clickEdit()
                .addBlocklistNumber(blockList.getBlockedNumber())
                .clickClose()
                .clickEdit()
                .deleteNumber(blockList.getBlockedNumber())
                .clickClose()
                .clickEdit()
                .verifyBlockedNumberNotExists(blockList.getBlockedNumber())
                .refreshPage();
        huntGroupPage
                .deleteHuntGroup(huntGroup);
    }

    //bug EPRO-1075
    @Description("Check after configuring blocklist and deleting HuntGruops phone number can be re-used")
    @Test(/*retryAnalyzer = RetryAnalyzer.class, */groups = {"regression", "huntGroupsPageTests"}, enabled = false)
    public void phoneNumberReUseHuntGroupTest(){
        HuntGroup huntGroup = new HuntGroup();
        HuntGroup huntGroup2 = new HuntGroup(getRandomString(15), huntGroup.getHuntGroupNumber());
        huntGroupsList.add(huntGroup);
        huntGroupsList.add(huntGroup2);

        login();
        huntGroupPage
                .createHuntGroup(huntGroup);
        blockListSections
                .selectNumber(huntGroup.getHuntGroupNumber())
                .activateUseBlocklist()
                .saveChanges()
                .refreshPage();
        huntGroupPage
                .deleteHuntGroup(huntGroup)
                .createHuntGroup(huntGroup2)
                .deleteHuntGroup(huntGroup2);
    }



    @AfterClass(alwaysRun = true)
    private void cleanUp() {
        startBrowser();
        login();
        huntGroupCleanUp(huntGroupsList);
        closeBrowser();
    }

}
