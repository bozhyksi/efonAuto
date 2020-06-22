package tests.abbreviatedDialPageTest;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static api.baseApiMethods.AbbreviatedNumbersApi.*;
import static api.baseApiMethods.UserApi.*;
import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.*;
import static tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling.Type.RANGE;
import static tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling.Type.SINGLE;

@Listeners(CustomListeners.class)

public class AbbreviatedDialPageTest extends BaseTestMethods {
    ArrayList<User> userArrayList = new ArrayList<>();
    ArrayList<AbbreviatedDialling> abbrevDialList = new ArrayList<>();

    @Description("Check if user can add single Short Number on \"Manage abbreviated numbers\" page")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "abbreviatedDialPageTest"})
    public void addSingleShortNumberTest() {
        AbbreviatedDialling abbrevNum = new AbbreviatedDialling(SINGLE);

        login()
                .goToMenuTab(ABBREVIATED_DIALING)
                .goToMenuTab(MANAGE_ABBREVIATED_NUMBERS);
        manageAbbrevNumbersPage
                .addSingleAbbrevNumber(abbrevNum)
                .goToMenuTab(ABBREVIATED_NUMBERS);
        abbreviatedNumbersPage
                .checkIfSingleAbbrevNumberExistsInList(abbrevNum);

        deleteAbbreviatedNumberApi(abbrevNum);
    }

    @Description("Check if user can add Short Number in range on \"Manage abbreviated numbers\" page")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "abbreviatedDialPageTest"})
    public void addRangeShortNumbersTest() {
        ArrayList<AbbreviatedDialling> temp = new ArrayList<>();
        AbbreviatedDialling shortNums = new AbbreviatedDialling(RANGE);
        for (String num: shortNums.getShortNumbersArray()) {
            temp.add(new AbbreviatedDialling(num));
        }
        abbrevDialList.addAll(temp);

        login()
                .goToMenuTab(ABBREVIATED_DIALING)
                .goToMenuTab(MANAGE_ABBREVIATED_NUMBERS);
        manageAbbrevNumbersPage
                .addRangeAbbrevNumber(shortNums)
                .goToMenuTab(ABBREVIATED_NUMBERS);
        abbreviatedNumbersPage
                .checkIfAbbrevNumberRangeCreated(shortNums);

        for (AbbreviatedDialling entry : temp) {
            deleteAbbreviatedNumberApi(entry);
        }

    }

    @Description("Check if user can Edit Abbreviated number and Assign it to Internal user")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "abbreviatedDialPageTest"})
    public void assignShortNumToInternalUserTest() {
        AbbreviatedDialling shorNum = new AbbreviatedDialling(SINGLE);
        User user = new User();

        userArrayList.add(user);
        abbrevDialList.add(shorNum);

        createAbbreviatedNumberApi(shorNum);
        createUsersApi(user);

        login()
                .goToMenuTab(ABBREVIATED_DIALING)
                .goToMenuTab(ABBREVIATED_NUMBERS);
        abbreviatedNumbersPage
                .clickEditSingleShortNumber(shorNum)
                .setShorNumToInternalUser(user)
                .validateInternalUserShorDial(user);

        deleteUsersApi(user);
        deleteAbbreviatedNumberApi(shorNum);
    }

    @Description("Check if user can Edit Abbreviated number and Assign it to External user")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","abbreviatedDialPageTest"})
    public void assignShortNumToExternalUserTest () {

        AbbreviatedDialling shortNum = new AbbreviatedDialling(SINGLE);
        abbrevDialList.add(shortNum);

        createAbbreviatedNumberApi(shortNum);

        login()
                .goToMenuTab(ABBREVIATED_DIALING)
                .goToMenuTab(ABBREVIATED_NUMBERS);
        abbreviatedNumbersPage
                .clickEditSingleShortNumber(shortNum)
                .setShorNumToExternalUser(shortNum)
                .validateExtNumber(shortNum);

        deleteAbbreviatedNumberApi(shortNum);
    }

    @Description("Check if user add secretary to short number")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","abbreviatedDialPageTest"})
    public void addSecretaryTest(){
        AbbreviatedDialling shortNum = new AbbreviatedDialling(SINGLE);
        User secretary = new User();

        userArrayList.add(secretary);
        abbrevDialList.add(shortNum);

        createUsersApi(secretary);
        createAbbreviatedNumberApi(shortNum);
        assignAbbrevNumInternUserApi(shortNum,secretary);

        login()
                .goToMenuTab(ABBREVIATED_DIALING)
                .goToMenuTab(ABBREVIATED_NUMBERS);
        abbreviatedNumbersPage
                .clickEditSecretary(shortNum)
                .addSecretaryToShortNumber(secretary)
                .clickEditSecretary(shortNum)
                .validateAddedSecretaries(secretary);

        deleteUsersApi(secretary);
        deleteAbbreviatedNumberApi(shortNum);
    }

    @Description("Check if user Activate/Deactivate BLF PickUp option")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","abbreviatedDialPageTest"})
    public void activateDeactivateBlfPickupTest(){

        AbbreviatedDialling shortNum = new AbbreviatedDialling(SINGLE);
        User user = new User();
        userArrayList.add(user);
        abbrevDialList.add(shortNum);

        createAbbreviatedNumberApi(shortNum);
        createUsersApi(user);
        assignAbbrevNumInternUserApi(shortNum,user);

        login()
                .goToMenuTab(ABBREVIATED_DIALING)
                .goToMenuTab(ABBREVIATED_NUMBERS);
        abbreviatedNumbersPage
                .activateBLF(shortNum)
                .verifyBlfActive(shortNum);

        deleteUsersApi(user);
        deleteAbbreviatedNumberApi(shortNum);
    }

    @Description("Check if user can add single Short Number (modified  test)")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "abbreviatedDialPageTest"})
    public void createDeleteSingleNumberTest() {

        AbbreviatedDialling singleShortNum = new AbbreviatedDialling(SINGLE);
        abbrevDialList.add(singleShortNum);

        login()
                .goToMenuTab(ABBREVIATED_DIALING)
                .goToMenuTab(MANAGE_ABBREVIATED_NUMBERS);
        manageAbbrevNumbersPage
                .addSingleAbbrevNumber(singleShortNum)
                .goToMenuTab(ABBREVIATED_NUMBERS);
        abbreviatedNumbersPage
                .checkIfSingleAbbrevNumberExistsInList(singleShortNum)
                .deleteSingleAbbrevNumber(singleShortNum)
                .checkIfSingleAbbrevNumberDoesNotExistInList(singleShortNum);
    }

    @Description("Check if user can add Short Number in range (modified  test)")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "abbreviatedDialPageTest"})
    public void CheckIfUserCanAddShortNumberInRange(){
        step("Prepare test data");
        AbbreviatedDialling rangeShortNum = new AbbreviatedDialling(RANGE);
        for (String shortNumber: rangeShortNum.getShortNumbersArray()) {
            abbrevDialList.add(new AbbreviatedDialling(shortNumber));
        }

        login();
        basePage
                .goToMenuTab(ABBREVIATED_DIALING)
                .goToMenuTab(MANAGE_ABBREVIATED_NUMBERS);

        manageAbbrevNumbersPage
                .addRangeAbbrevNumber(rangeShortNum)
                .goToMenuTab(ABBREVIATED_NUMBERS);

        abbreviatedNumbersPage
                .checkIfAbbrevNumberRangeCreated(rangeShortNum);

        abbrevNumsCleanUp(abbrevDialList);
    }


    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        userCleanUp(userArrayList);
        abbrevNumsCleanUp(abbrevDialList);
    }
}
