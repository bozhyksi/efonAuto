package tests.сonferenceCallsPageTests;

import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.сonferenceCallsPageTests.ConferenceCallTestData.ConferenceCallTestData;

import java.util.ArrayList;

import static io.qameta.allure.Allure.step;

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

    @Description("Verify if user can configure Calls with suppressed numbers")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","conferenceCallsPage"})
    public void VerifyIfUserCanConfigureCallsWithSuppressedNumbers(){

        ConferenceCallTestData confCall = new ConferenceCallTestData();
        confCallsList.add(confCall);

        login();
        conferenceCallsPage
                .createConfCall(confCall)
                .configSuppressedNumber(confCall.getConferenceNumber())
                .saveChanges()
                .refreshPage();
        conferenceCallsPage
                .validateSuppressedNumber(confCall.getConferenceNumber())
                .deleteConfCall(confCall);
    }

    @Description("Verify if changed conference call name displayed in the grid")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","conferenceCallsPage"})
    public void verifyIfChangedNameDisplayed(){
        ConferenceCallTestData confCall = new ConferenceCallTestData();
        confCallsList.add(confCall);

        login();
        conferenceCallsPage
                .createConfCall(confCall)
                .clickEdit(confCall)
                .enterName(confCall.changeName())
                .saveChanges()
                .verifyConfCallExists(confCall.getName())
                .deleteConfCall(confCall);
    }

    @Description("Verify if changed conference call Number displayed in the grid")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","conferenceCallsPage"})
    public void verifyIfChangedNumberDisplayed(){
        ConferenceCallTestData confCall = new ConferenceCallTestData();
        confCallsList.add(confCall);

        login();
        conferenceCallsPage
                .createConfCall(confCall)
                .clickEdit(confCall)
                .selectNumber(confCall.changeNumber())
                .saveChanges()
                .verifyConfCallExists(confCall.getConferenceNumber())
                .deleteConfCall(confCall);
    }

    @Description("Verify if changed conference call PIN displayed in the grid")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","conferenceCallsPage"})
    public void verifyIfChangedPinDisplayed(){
        ConferenceCallTestData confCall = new ConferenceCallTestData();
        confCallsList.add(confCall);

        login();
        conferenceCallsPage
                .createConfCall(confCall)
                .clickEdit(confCall)
                .enterPIN(confCall.changePIN())
                .saveChanges()
                .verifyConfCallExists(confCall.getPin())
                .deleteConfCall(confCall);
    }

    @Description("Verify if changed conference call Language displayed in the grid")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","conferenceCallsPage"})
    public void verifyIfChangedLanguageDisplayed(){
        ConferenceCallTestData confCall = new ConferenceCallTestData();
        confCallsList.add(confCall);

        login();
        conferenceCallsPage
                .createConfCall(confCall)
                .clickEdit(confCall)
                .selectLanguage(confCall.changeLanguage())
                .saveChanges()
                .verifyConfCallExists(confCall.getLanguage())
                .deleteConfCall(confCall);
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        startBrowser();
        login();
        cleanUpConfCalls(confCallsList);
        closeBrowser();
    }
}
