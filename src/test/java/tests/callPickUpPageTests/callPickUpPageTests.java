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

import static api.baseApiMethods.AbbreviatedNumbersApi.createAbbreviatedNumberApi;
import static api.baseApiMethods.AbbreviatedNumbersApi.deleteAbbreviatedNumberApi;
import static api.baseApiMethods.CallPickUpsApi.createCallPickupApi;
import static api.baseApiMethods.CallPickUpsApi.deleteCallPickupApi;
import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.CALL_PICKUPs;
import static tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling.Type.SINGLE;

public class callPickUpPageTests extends BaseTestMethods {
    ArrayList<CallPickUp> callPickUpsList = new ArrayList<>();

    @Description("Check if user can create new Call Pick Up group")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "callPickUpPageTests"})
    public void createDeleteCallPickUpGroupTest() {

        CallPickUp callPickUp = new CallPickUp( new AbbreviatedDialling(SINGLE));
        callPickUpsList.add(callPickUp);

        createAbbreviatedNumberApi(callPickUp.getShortNumberObj().getSingleShortNum());
        login()
                .goToMenuTab(CALL_PICKUPs);
        callPickUpPage
                .clickCreateNewGroup()
                .configureGroupForCallPickup(callPickUp)
                .editCallPickUp(callPickUp)
                .verifyGroupForCallPickupConfiguration(callPickUp)
                .deleteCallPickUp(callPickUp)
                .verifyIfCallPickUpDoesNotExist(callPickUp);
        deleteAbbreviatedNumberApi(callPickUp.getShortNumberObj().getId());
    }

    @Description("Check if user can EDIT and change the configuration of Call Pick Up group")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "callPickUpPageTests"})
    public void editCallPickUpGroupTest() {

        CallPickUp callPickUp = new CallPickUp(new AbbreviatedDialling(SINGLE));
        CallPickUp callPickUpUpdated = new CallPickUp(new AbbreviatedDialling(SINGLE));
        callPickUpsList.add(callPickUpUpdated);
        callPickUpsList.add(callPickUp);

        createCallPickupApi(callPickUp);
        createAbbreviatedNumberApi(callPickUpUpdated.getShortNumberObj().getSingleShortNum());

        login()
                .goToMenuTab(CALL_PICKUPs);
        callPickUpPage
                .editCallPickUp(callPickUp)
                .configureGroupForCallPickup(callPickUpUpdated)
                .editCallPickUp(callPickUpUpdated)
                .verifyGroupForCallPickupConfiguration(callPickUpUpdated)
                .deleteCallPickUp(callPickUpUpdated)
                .verifyIfCallPickUpDoesNotExist(callPickUpUpdated);

        deleteCallPickupApi(callPickUp);
        deleteAbbreviatedNumberApi(callPickUpUpdated.getShortNumberObj().getId());
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        callPickUpCleanUp(callPickUpsList);
    }
}
