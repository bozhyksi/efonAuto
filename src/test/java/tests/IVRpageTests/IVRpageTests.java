package tests.IVRpageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import flow.PublicEnums;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.IVRpageTests.IVRtestData.IVRtestData;
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
import static api.baseApiMethods.IVRApi.createIvrApi;
import static api.baseApiMethods.IVRApi.deleteIvrApi;
import static api.baseApiMethods.QueueApi.*;
import static api.baseApiMethods.UserApi.*;
import static com.codeborne.selenide.Condition.*;
import static flow.PublicEnums.IvrActions.CALL_CENTER_QUEUE;
import static flow.PublicEnums.IvrActions.PHONE_EXTERNAL;
import static flow.PublicEnums.State.ACTIVATED;
import static flow.PublicEnums.State.DEACTIVATED;
import static pages.basePage.BasePage.MenuTabsBasePage.IVRs;
import static tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling.Type.SINGLE;

@Listeners(CustomListeners.class)

public class IVRpageTests extends BaseTestMethods {

    //<editor-fold desc="arrayLists">
    private ArrayList<IVRtestData> ivrList = new ArrayList<>();
    private ArrayList<FileManagementTestData> filesList = new ArrayList<>();
    private ArrayList<HuntGroup> huntGroupsList = new ArrayList<>();
    private ArrayList<Queue> queueList = new ArrayList<>();
    private ArrayList<User> usersList = new ArrayList<>();
    private ArrayList<AbbreviatedDialling> shortNumList = new ArrayList<>();
    //</editor-fold>

    @Description("Verify if user can create new IVR")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "IVRpageTests"})
    public void createNewIvrTest() {

        IVRtestData ivr = new IVRtestData(new FileManagementTestData());
        ivrList.add(ivr);
        filesList.add(ivr.getAnnouncement());

        uploadAnnouncementApi(ivr.getAnnouncement());
        login();
        ivrPage
                .createIvr(ivr)
                .verifyIfIvrExists(ivr.getIvrName())
                .deleteIvr(ivr);
        deleteAnnouncementApi(ivr.getAnnouncement().getId());
    }

    @Description("Verify if user can EDIT new IVR")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "IVRpageTests"})
    public void editNewIvrTest() {

        IVRtestData ivr = new IVRtestData(new FileManagementTestData());
        ivrList.add(ivr);
        filesList.add(ivr.getAnnouncement());

        createIvrApi(ivr);
        login()
                .goToMenuTab(IVRs);
        ivrPage
                .clickEditIvr(ivr)
                .enterDisplayName(ivr.changeDisplayName())
                .saveChanges()
                .clickEditIvr(ivr)
                .getInputDisplayName().shouldHave(value(ivr.getIvrDisplName()));
        refreshPage();
        deleteIvrApi(ivr);
    }

    @Description("Verify if user can configure \"Call to external subscriber\" ivr action")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"})
    public void callExternalNumberActionTest() {

        IVRtestData ivr = new IVRtestData(new FileManagementTestData());
        ivrList.add(ivr);
        filesList.add(ivr.getAnnouncement());

        createIvrApi(ivr);

        login()
            .goToMenuTab(IVRs);
        ivrPage
                .clickEditIvr(ivr)
                .configureExternalDirectAction(ivr.getParameterExtTelNumber())
                .saveChanges()
                .clickEditIvr(ivr)
                .verifyIvrAction(PHONE_EXTERNAL,ivr);

        deleteIvrApi(ivr);
    }

    @Description("Verify if user can configure \"Hunt Group\" ivr action")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"})
    public void HuntGroupIvrActionTest() {

        IVRtestData ivr = new IVRtestData(new FileManagementTestData(), new HuntGroup());

        ivrList.add(ivr);
        filesList.add(ivr.getAnnouncement());
        huntGroupsList.add(ivr.getHuntGroup());

        createIvrApi(ivr);
        createHuntGroupApi(ivr.getHuntGroup());

        login()
                .goToMenuTab(IVRs);
        ivrPage
                .clickEditIvr(ivr)
                .configureHuntGroupAction(ivr.getHuntGroup())
                .saveChanges()
                .clickEditIvr(ivr)
                .verifyIvrAction(PublicEnums.IvrActions.RINGRUF, ivr);

        deleteIvrApi(ivr);
        deleteHuntGroupApi(ivr.getHuntGroup());
    }

    @Description("Verify if HungGroup with shortNumber available for IVR actions")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"})
    public void useHuntGroupWithShortNumInIvrActionTest(){
        IVRtestData ivr = new IVRtestData(new FileManagementTestData(), new HuntGroup(new AbbreviatedDialling(SINGLE)));

        ivrList.add(ivr);
        filesList.add(ivr.getAnnouncement());
        huntGroupsList.add(ivr.getHuntGroup());
        shortNumList.add(ivr.getHuntGroup().getShotNum());

        createAbbreviatedNumberApi(ivr.getHuntGroup().getShotNum());
        createIvrApi(ivr);
        createHuntGroupApi(ivr.getHuntGroup());
        login()
                .goToMenuTab(IVRs);
        ivrPage
                .clickEditIvr(ivr)
                .configureHuntGroupAction(ivr.getHuntGroup())
                .saveChanges()
                .clickEditIvr(ivr)
                .verifyIvrAction(PublicEnums.IvrActions.RINGRUF, ivr);
        deleteIvrApi(ivr);
        deleteHuntGroupApi(ivr.getHuntGroup());
        deleteAbbreviatedNumberApi(ivr.getHuntGroup().getShotNum());
    }

    @Description("Verify if user can configure \"Queues\" ivr action")
    @Test(/*retryAnalyzer = RetryAnalyzer.class,*/ groups = {"regression", "IVRpageTests"})
    public void configureQueuesIvrActionTest() {

        Queue queue = new Queue(new User(), new User(),new User(),new AbbreviatedDialling(SINGLE));
        IVRtestData ivr = new IVRtestData(new FileManagementTestData(),queue);
        usersList.add(queue.getManager());
        usersList.add(queue.getReporter());
        usersList.add(queue.getAgent());
        queueList.add(queue);
        filesList.add(ivr.getAnnouncement());
        shortNumList.add(queue.getShortNum());
        ivrList.add(ivr);

        createUsersApi(queue.getReporter(),queue.getManager(), queue.getAgent());
        createAbbreviatedNumberApi(queue.getShortNum());
        createIvrApi(ivr);
        createQueueApi(queue);
        addQueueAgentApi(queue);

        login()
                .goToMenuTab(IVRs);
        ivrPage
                .clickEditIvr(ivr)
                .configureQueueAction(queue)
                .saveChanges()
                .clickEditIvr(ivr)
                .verifyIvrAction(CALL_CENTER_QUEUE, ivr);

        deleteIvrApi(ivr);
        deleteQueueApi(queue);
        deleteAbbreviatedNumberApi(queue.getShortNum());
        deleteUsersApi(queue.getReporter(),queue.getManager(),queue.getAgent());
    }

    @Description("Verify if user can configure \"Call to direct number\" ivr action")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"})
    public void configureCallToDirectNumberIvrActionTest() {

        IVRtestData ivr = new IVRtestData(new FileManagementTestData(), new User());
        ivrList.add(ivr);
        filesList.add(ivr.getAnnouncement());
        usersList.add(ivr.getUser());

        createIvrApi(ivr);
        createUsersApi(ivr.getUser());

        login()
                .goToMenuTab(IVRs);
        ivrPage
                .clickEditIvr(ivr)
                .configurePhoneDirectAction(ivr.getUser())
                .saveChanges()
                .clickEditIvr(ivr)
                .verifyIvrAction(PublicEnums.IvrActions.PHONE_DIRECT,ivr);

        deleteIvrApi(ivr);
        deleteUsersApi(ivr.getUser());
    }

    @Description("Verify if user can configure \"Call to abbrev number\" ivr action")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"})
    public void configureCallToAbbreviatedNumberActionTest(){
        User user = new User(new AbbreviatedDialling(SINGLE));
        IVRtestData ivr = new IVRtestData(new FileManagementTestData(), user.getShortNum());

        filesList.add(ivr.getAnnouncement());
        ivrList.add(ivr);
        usersList.add(user);
        shortNumList.add(user.getShortNum());

        createAbbreviatedNumberApi(user.getShortNum());
        createUsersApi(user);
        createIvrApi(ivr);
        login()
                .goToMenuTab(IVRs);
        ivrPage
                .clickEditIvr(ivr)
                .configureAbbreviatedNumberAction(user.getShortNum())
                .saveChanges()
                .clickEditIvr(ivr)
                .verifyIvrAction(PublicEnums.IvrActions.PHONE_INTERNAL,ivr);
        deleteIvrApi(ivr);
        deleteAnnouncementApi(ivr.getAnnouncement());
        deleteUsersApi(user);
        deleteAbbreviatedNumberApi(user.getShortNum());
    }

    @Description("Verify if user can configure \"Voicemail: no announcement\" ivr action")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"})
    public void configureVoicemailNoAnnouncementIvrActionTest() {

        IVRtestData ivr = new IVRtestData(new FileManagementTestData());
        ivrList.add(ivr);
        filesList.add(ivr.getAnnouncement());

        createIvrApi(ivr);

        login()
                .goToMenuTab(IVRs);
        ivrPage
                .clickEditIvr(ivr)
                .configureVoiceMailNoAnnouncementAction()
                .saveChanges()
                .clickEditIvr(ivr)
                .verifyIvrAction(PublicEnums.IvrActions.VM_NO_ANNOUNCE,ivr);

        deleteIvrApi(ivr);
    }

    @Description("Verify if user can configure \"Play file and hang up\" ivr action")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"})
    public void configurePlayFileAndHangUpIvrActionTest() {

        IVRtestData ivr = new IVRtestData(new FileManagementTestData());
        ivrList.add(ivr);
        filesList.add(ivr.getAnnouncement());

        createIvrApi(ivr);

        login()
                .goToMenuTab(IVRs);
        ivrPage
                .clickEditIvr(ivr)
                .configurePlayAndHangUpAction(ivr.getAnnouncement())
                .saveChanges()
                .clickEditIvr(ivr)
                .verifyIvrAction(PublicEnums.IvrActions.PLAY_HANGUP,ivr);

        deleteIvrApi(ivr);
    }

    @Description("Verify if user can create/delete IVRs")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"})
    public void createDeleteIvrTest(){

        IVRtestData ivr = new IVRtestData(new FileManagementTestData());
        filesList.add(ivr.getAnnouncement());
        ivrList.add(ivr);

        uploadAnnouncementApi(ivr.getAnnouncement());

        login()
                .goToMenuTab(IVRs);
        ivrPage
                .clickNewIvr()
                .enterIvrName(ivr.getIvrName())
                .enterDisplayName(ivr.getIvrDisplName())
                .selectLanguage(ivr.getIvrLanguage())
                .selectNumber(ivr.getIvrNumber())
                .selectAnnouncement(ivr.getAnnouncement().getFileName())
                .saveChanges()
                .verifyIfIvrExists(ivr.getIvrName())
                .deleteIvr(ivr);

        deleteAnnouncementApi(ivr.getAnnouncement());
    }

    @Description("Verify if user can configure IVRs actions")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"})
    public void configureIvrActionsTest(){

        IVRtestData ivr = new IVRtestData(new FileManagementTestData());
        HuntGroup huntGroup = new HuntGroup();
        User user = new User(new AbbreviatedDialling(SINGLE));

        filesList.add(ivr.getAnnouncement());
        ivrList.add(ivr);
        usersList.add(user);
        huntGroupsList.add(huntGroup);
        shortNumList.add(user.getShortNum());

        createAbbreviatedNumberApi(user.getShortNum());
        createHuntGroupApi(huntGroup);
        createUsersApi(user);
        createIvrApi(ivr);

        login()
                .goToMenuTab(IVRs);
        ivrPage
                .clickEditIvr(ivr)
                .configureHuntGroupAction(huntGroup)
                .configureAbbreviatedNumberAction(user.getShortNum())
                .configurePhoneDirectAction(user)
                .configureExternalDirectAction(ivr.getParameterExtTelNumber())
                .configureVoiceMailUnavailableAction()
                .configureVoiceMailNoAnnouncementAction()
                .configureVoiceMailOfSubscriberAction(user)
                .configureMaxPassesReachedAction()
                .configureInvalidChoiceAction()
                .configureTimeExpiredAction()
                .configurePlayAndHangUpAction(ivr.getAnnouncement())
                .configurePlayAndRestartAction(ivr.getAnnouncement())
                .configureRestartMenuAction()
                .saveChanges();

        deleteIvrApi(ivr);
        deleteAnnouncementApi(ivr.getAnnouncement());
        deleteHuntGroupApi(huntGroup);
        deleteUsersApi(user);
        deleteAbbreviatedNumberApi(user.getShortNum());
    }

    @Description("Verify if user can activate/deactivate CAll Recordings")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"})
    public void activateCallRecordingTest(){
        IVRtestData ivr = new IVRtestData(new FileManagementTestData());
        filesList.add(ivr.getAnnouncement());
        ivrList.add(ivr);

        createIvrApi(ivr);
        login()
                .goToMenuTab(IVRs);
        ivrPage
                .clickEditIvr(ivr)
                .activateCallRecordings()
                .saveChanges()
                .verifyCallRecording(ivr,ACTIVATED)
                .clickEditIvr(ivr)
                .deactivateCallRecordings()
                .saveChanges()
                .verifyCallRecording(ivr,DEACTIVATED);
        deleteIvrApi(ivr);
    }

    @Description("Verify if user can configure VoiceMail section")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"})
    public void configVoiceMailSectionTest(){
        IVRtestData ivr = new IVRtestData(new FileManagementTestData());
        ivrList.add(ivr);

        createIvrApi(ivr);
        login()
                .goToMenuTab(IVRs);
        ivrPage
                .clickEditIvr(ivr)
                .enterPIN(ivr.getPinCode())
                .enterVoiceMailEmail(ivr.getVoicemailEmail())
                .enterSalutation(ivr.getSalutation())
                .activateDeleteVoiceMailOption()
                .saveChanges()
                .clickEditIvr(ivr)
                .verifyVoiceMailSettings(ivr);
        deleteIvrApi(ivr);
    }

    @Description("Verify if user can use short number as IVR phone number")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"})
    public void useShortNumAsIvrPhoneNumber(){
        IVRtestData ivr = new IVRtestData(new FileManagementTestData(), new AbbreviatedDialling(SINGLE));
        ivrList.add(ivr);
        shortNumList.add(ivr.getShortNum());
        filesList.add(ivr.getAnnouncement());

        uploadAnnouncementApi(ivr.getAnnouncement());
        createAbbreviatedNumberApi(ivr.getShortNum());
        login()
                .goToMenuTab(IVRs);
        ivrPage
                .clickNewIvr()
                .enterIvrName(ivr.getIvrName())
                .enterDisplayName(ivr.getIvrDisplName())
                .selectNumber(ivr.getShortNum().getSingleShortNum())
                .selectAnnouncement(ivr.getAnnouncement().getFileName())
                .saveChanges();
        deleteIvrApi(ivr);
        deleteAbbreviatedNumberApi(ivr.getShortNum());
    }

    //EPRO-1145
    @Description("Verify if used short number as IVR phone number are not available in blocklist dropdown")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "IVRpageTests"}, enabled = false)
    public void shortNumbersNotAvailableInBlockLIstDropdownTest(){
        IVRtestData ivr = new IVRtestData(new FileManagementTestData(), new AbbreviatedDialling(SINGLE));
        ivrList.add(ivr);
        shortNumList.add(ivr.getShortNum());
        filesList.add(ivr.getAnnouncement());

        createIvrApi(ivr);
        createAbbreviatedNumberApi(ivr.getShortNum());
        login()
                .goToMenuTab(IVRs);
        ivrPage
                .clickEditIvr(ivr)
                .selectNumber(ivr.getShortNum().getSingleShortNum())
                .saveChanges()
                .refreshPage();
        blockListSections
                .checkIfDropdownNotContainsNumber(ivr.getShortNum().getSingleShortNum());
        deleteIvrApi(ivr);
        deleteAbbreviatedNumberApi(ivr.getShortNum());
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp() {
        ivrCleanUp(ivrList);
        huntGroupCleanUp(huntGroupsList);
        queueCleanUp(queueList);
        userCleanUp(usersList);
        announcementCleanUp(filesList);
        abbrevNumsCleanUp(shortNumList);
    }
}
