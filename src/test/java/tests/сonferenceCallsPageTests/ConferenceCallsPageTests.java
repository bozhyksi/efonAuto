package tests.сonferenceCallsPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.сonferenceCallsPageTests.ConferenceCallTestData.ConferenceCallTestData;


import java.util.ArrayList;

import static api.baseApiMethods.ConferenceCallsApi.createConferenceCallApi;
import static api.baseApiMethods.ConferenceCallsApi.deleteConferenceCallApi;
import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.CONFERENCE_CALLS;

@Listeners(CustomListeners.class)

public class ConferenceCallsPageTests extends BaseTestMethods {
    ArrayList<ConferenceCallTestData> confCallsList = new ArrayList<>();

    @Description("Verify if user can create new ConferenceCallTestData Call")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "conferenceCallsPage"})
    public void VerifyIfUserCreateNewConferenceCall(){

        ConferenceCallTestData confCall = new ConferenceCallTestData();
        confCallsList.add(confCall);

        login();
        conferenceCallsPage
                .createConfCall(confCall)
                .verifyConfCallExists(confCall.getName())
                .deleteConfCall(confCall);
    }

    @Description("Verify if changed conference call name displayed in the grid")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","conferenceCallsPage"})
    public void verifyIfChangedNameDisplayed(){
        ConferenceCallTestData confCall = new ConferenceCallTestData();
        confCallsList.add(confCall);

        createConferenceCallApi(confCall.getJson());
        login()
                .goToMenuTab(CONFERENCE_CALLS);
        conferenceCallsPage
                .clickEdit(confCall)
                .enterName(confCall.changeName())
                .saveChanges()
                .verifyConfCallExists(confCall.getName());
        deleteConferenceCallApi(confCall.getId());
    }

    @Description("Verify if changed conference call Number displayed in the grid")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","conferenceCallsPage"})
    public void verifyIfChangedNumberDisplayed(){
        ConferenceCallTestData confCall = new ConferenceCallTestData();
        confCallsList.add(confCall);

        createConferenceCallApi(confCall.getJson());
        login()
                .goToMenuTab(CONFERENCE_CALLS);
        conferenceCallsPage
                .clickEdit(confCall)
                .selectNumber(confCall.changeNumber())
                .saveChanges()
                .verifyConfCallExists(confCall.getConferenceNumber());
        deleteConferenceCallApi(confCall.getId());
    }

    @Description("Verify if changed conference call PIN displayed in the grid")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","conferenceCallsPage"})
    public void verifyIfChangedPinDisplayed(){
        ConferenceCallTestData confCall = new ConferenceCallTestData();
        confCallsList.add(confCall);

        createConferenceCallApi(confCall.getJson());
        login()
                .goToMenuTab(CONFERENCE_CALLS);
        conferenceCallsPage
                .clickEdit(confCall)
                .enterPIN(confCall.changePIN())
                .saveChanges()
                .verifyConfCallExists(confCall.getPin());
        deleteConferenceCallApi(confCall.getId());
    }

    @Description("Verify if changed conference call Language displayed in the grid")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","conferenceCallsPage"})
    public void verifyIfChangedLanguageDisplayed(){
        ConferenceCallTestData confCall = new ConferenceCallTestData();
        confCallsList.add(confCall);

        createConferenceCallApi(confCall.getJson());
        login()
                .goToMenuTab(CONFERENCE_CALLS);
        conferenceCallsPage
                .clickEdit(confCall)
                .selectLanguage(confCall.changeLanguage())
                .saveChanges()
                .verifyConfCallLanguage(confCall.getLanguage());
        deleteConferenceCallApi(confCall.getId());
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        cleanUpConfCalls(confCallsList);
    }
}
