package tests.callPickUpPageTests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.basePage.BasePage;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;
import tests.callPickUpPageTests.CallPickUpTestData.CallPickUp;

import java.util.ArrayList;

import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.CALL_PICKUPs;
import static tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling.Type.SINGLE;

public class callPickUpPageTests extends BaseTestMethods {
    ArrayList<AbbreviatedDialling> shortNumbersList = new ArrayList<>();
    ArrayList<CallPickUp> callPickUpsList = new ArrayList<>();

    @Description("Check if user can create new Call Pick Up group")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "callPickUpPageTests"})
    public void CheckIfUserCanCreateNewCallPickUpGroup() {

        step("Prepare test data");
        AbbreviatedDialling shortNumber = new AbbreviatedDialling(SINGLE);
        CallPickUp callPickUp = new CallPickUp(shortNumber);
        shortNumbersList.add(shortNumber);
        callPickUpsList.add(callPickUp);

        login();
        addSingleAbbreviatedNumber(shortNumber);

        basePage
                .goToMenuTab(CALL_PICKUPs);
        callPickUpPage
                .clickCreateNewGroup()
                .configureGroupForCallPickup(callPickUp)
                .editCallPickUp(callPickUp)
                .verifyGroupForCallPickupConfiguration(callPickUp)
                .deleteCallPickUp(callPickUp)
                .verifyIfCallPickUpDoesNotExist(callPickUp);

        deleteSingleAbbreviatedNumber(shortNumber);
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        startBrowser();
        login();
        abbrevNumsCleanUp(shortNumbersList);
        closeBrowser();
    }
}
