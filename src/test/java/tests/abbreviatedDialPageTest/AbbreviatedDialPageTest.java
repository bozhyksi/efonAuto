package tests.abbreviatedDialPageTest;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.*;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static io.qameta.allure.Allure.step;

@Listeners(CustomListeners.class)

public class AbbreviatedDialPageTest extends BaseTestMethods {
    ArrayList<User> userArrayList = new ArrayList<>();
    ArrayList<AbbreviatedDialling> abbrevDialList = new ArrayList<>();

    @Description("Check if user can add single Short Number on \"Manage abbreviated numbers\" page")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "abbreviatedDialPageTest"})
    public void CheckIfUserCanAddOneShortNumberOnManageAbbreviatedNumbersPage() {
        step("Prepare test data");
        AbbreviatedDialling abbrevNum = new AbbreviatedDialling(getRandomNumber(250, 299));

        step("Login the system");
        login();

        step("Go to Abbreviated dialling -> Manage abbreviated numbers page and add single short number");
        basePage.getTabAbbreviatedDialling().click();
        abbrevDialBasePage.getTabManageAbbreviatedNumbers().click();
        manageAbbrevNumbersPage.addSingleAbbrevNumber(abbrevNum.getSingleShortNum());
        waitUntilAlertDisappear();

        step("Goto Abbreviated numbers tab and check if short number was added");
        abbrevDialBasePage.getTabAbbreviatedNumbers().click();
        waitUntilAlertDisappear();
        abbreviatedNumbers.checkIfAbbrevNumberExistsInList(abbrevNum.getSingleShortNum());

        step("Clear test data - delete added short number");
        abbreviatedNumbers.deleteSingleAbbrevNumber(abbrevNum.getSingleShortNum());
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();

        step("Verify if Abbreviated number was deleted");
        abbreviatedNumbers.checkIfAbbrevNumberDoesNotExistInList(abbrevNum.getSingleShortNum());
    }

    @Description("Check if user can add Short Number in range on \"Manage abbreviated numbers\" page")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "abbreviatedDialPageTest"})
    public void CheckIfUserCanAddShortNumberInRangeOnManageAbbreviatedNumbersPage() {
        step("Prepare test data");
        AbbreviatedDialling shortNums = new AbbreviatedDialling(201, 205);
        for (String num: shortNums.getShortNumbersArray()) {
            abbrevDialList.add(new AbbreviatedDialling(num));
        }

        step("Login the system");
        login();
        abbrevNumsCleanUp(abbrevDialList);

        step("Go to Abbreviated dialling -> Manage abbreviated numbers page and create short numbers from range");
        basePage.getTabAbbreviatedDialling().click();
        abbrevDialBasePage.getTabManageAbbreviatedNumbers().click();
        manageAbbrevNumbersPage.addRangeAbbrevNumber(shortNums.getFromNumber(), shortNums.getUntilNumber());
        waitUntilAlertDisappear();

        step("Goto Abbreviated dialling ->Abbreviated numbers and check if all short numbers were created ");
        abbrevDialBasePage.getTabAbbreviatedNumbers().click();
        abbreviatedNumbers.checkIfAbbrevNumberRangeCreated(shortNums);

        step("Delete all test data - delete all short numbers");
        abbrevNumsCleanUp(abbrevDialList);
    }

    @Description("Check if user can Edit Abbreviated number and Assign it to Internal user")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "abbreviatedDialPageTest"}, enabled = false) //TEST IGNORED UNTIL BUG FIXED
    public void CheckIfUserCanEditAbbreviatedNumberAndAssignItToInternalUser() {
        step("Prepare test data");
        AbbreviatedDialling shorNum = new AbbreviatedDialling(getRandomNumber(255, 260));
        User user = new User();
        userArrayList.add(user);
        abbrevDialList.add(shorNum);

        step("Login the system");
        login();

        step("Create user");
        createUser(user);

        step("Add single Abbreviated Number");
        abbrevNumsCleanUp(abbrevDialList);
        addSingleAbbrevNumber(shorNum.getSingleShortNum());

        step("Click on Edit button and open Assign abbreviated dialling popup");
        abbreviatedNumbers.editSingleAbbrevNumber(shorNum.getSingleShortNum());

        step("Assign short number to user");
        popupAssignAbbrevDial.getRadioInternalUser().click();
        popupAssignAbbrevDial.getDropdrownSelectUser().selectOptionContainingText(user.getLastName());
        popupAssignAbbrevDial.getCheckboxForwardAsExternal().click();
        popupAssignAbbrevDial.getButtonSave().click();
        refreshPage();

        step("Check if short dial was assign to the user and user's info is showed in the grid");
        abbreviatedNumbers.getListLastName().filterBy(Condition.text(user.getLastName())).shouldHave(CollectionCondition.sizeGreaterThan(0));
        abbreviatedNumbers.getListFirstName().filterBy(Condition.text(user.getFirstName())).shouldHave(CollectionCondition.sizeGreaterThan(0));

        step("Clear test data - delete user");
        abbrevNumsCleanUp(abbrevDialList);
        deleteUser(user);
    }

    @Description("Check if user can Edit Abbreviated number and Assign it to External user")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression","abbreviatedDialPageTest"})
    public void CheckIfUserCanEditAbbreviatedNumberAndAssignItToExternalUser() {
        step("Prepare test data");
        AbbreviatedDialling shortNum = new AbbreviatedDialling(getRandomNumber(245,249));

        step("Login the system");
        login();

        step("Add single Abbreviated Number");
        abbrevNumsCleanUp(abbrevDialList);
        addSingleAbbrevNumber(shortNum.getSingleShortNum());
        waitUntilAlertDisappear();

        step("Click on Edit button and open Assign abbreviated dialling popup");
        abbreviatedNumbers.editSingleAbbrevNumber(shortNum.getSingleShortNum());

        step("Select External number radio");
        popupAssignAbbrevDial.getRadioExternalNumber().click();

        step("Fill in External number phone");
        popupAssignAbbrevDial.getInputExternalNumber().setValue(shortNum.getExtPhoneNum());

        step("Fill in Last name");
        popupAssignAbbrevDial.getInputLastName().setValue(shortNum.getLastName());

        step("Fill in First name");
        popupAssignAbbrevDial.getInputFirstName().setValue(shortNum.getFirstName());

        step("Fill in Company and save all changes");
        popupAssignAbbrevDial.getInputCompany().setValue(shortNum.getCompany());
        popupAssignAbbrevDial.getButtonSave().click();
        refreshPage();//temporary overcome because of bug

        step("Check if External user info is displayed in the Abbreviated dialling grid");
        abbreviatedNumbers.checkIfExternalUserInfoIsDisplayedInTheAbbreviatedDiallingGrid(shortNum);

        step("Edit short dial, make it unUsed and Delete");
        abbreviatedNumbers.editSingleAbbrevNumber(shortNum.getSingleShortNum());
        popupAssignAbbrevDial.getRadioUnused().click();
        popupAssignAbbrevDial.getButtonSave().click();
        refreshPage();//temporary overcome because of bug
        deleteSingleAbbrevNumber(shortNum.getSingleShortNum());

        step("Delete test data - delete all short numbers");
        refreshPage();
        abbrevNumsCleanUp(abbrevDialList);
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        startBrowser();
        login();
        userCleanUp(userArrayList);
        abbrevNumsCleanUp(abbrevDialList);
        closeBrowser();
    }
}
