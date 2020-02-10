package tests.abbreviatedDialPageTest;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDiallingTestData;
import tests.userPageTests.userPageTestData.User;

import java.util.HashMap;

import static io.qameta.allure.Allure.step;

@Listeners(CustomListeners.class)

public class AbbreviatedDialPageTest extends BaseTestMethods {

    @Description("Check if user can add single Short Number on \"Manage abbreviated numbers\" page")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "abbreviatedDialPageTest"})
    public void CheckIfUserCanAddOneShortNumberOnManageAbbreviatedNumbersPage(){
        step("Prepare test data");
        String singleShortNumber = getRandomNumber(201,299);

        step("Login the system");
        login();

        step("Go to Abbreviated dialling -> Manage abbreviated numbers page and add single short number");
        basePage.getTabAbbreviatedDialling().click();
        abbrevDialBasePage.getTabManageAbbreviatedNumbers().click();
        manageAbbrevNumbersPage.addSingleAbbrevNumber(singleShortNumber);

        step("Goto Abbreviated numbers tab and check if short number was added");
        abbrevDialBasePage.getTabAbbreviatedNumbers().click();
        abbreviatedNumbers.checkIfAbbrevNumberExistsInList(singleShortNumber);

        step("Clear test data - delete added short number");
        abbreviatedNumbers.deleteSingleAbbrevNumber(singleShortNumber);
        confirmationPopup.getYesButton().click();

        step("Verify if Abbreviated number was deleted");
        abbreviatedNumbers.checkIfAbbrevNumberDoesNotExistInList(singleShortNumber);
    }

    @Description("Check if user can add Short Number in range on \"Manage abbreviated numbers\" page")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "abbreviatedDialPageTest"})
    public void CheckIfUserCanAddShortNumberInRangeOnManageAbbreviatedNumbersPage(){
        step("Prepare test data");
        AbbreviatedDiallingTestData shortNums = new AbbreviatedDiallingTestData(201,210);

        step("Login the system");
        login();

        step("Go to Abbreviated dialling -> Manage abbreviated numbers page and create short numbers from range");
        basePage.getTabAbbreviatedDialling().click();
        deleteAllAbbrevNumbers();
        abbrevDialBasePage.getTabManageAbbreviatedNumbers().click();
        manageAbbrevNumbersPage.addRangeAbbrevNumber(shortNums.getFromNumber(), shortNums.getUntilNumber());
        waitUntilAlertDisappear();

        step("Goto Abbreviated dialling ->Abbreviated numbers and check if all short numbers were created ");
        abbrevDialBasePage.getTabAbbreviatedNumbers().click();
        abbreviatedNumbers.checkIfAbbrevNumberRangeCreated(shortNums);

        step("Delete all test data - delete all short numbers");
        deleteAllAbbrevNumbers();
        abbreviatedNumbers.getListNo().shouldHave(CollectionCondition.size(1)).shouldHave(CollectionCondition.texts("No Items"));
    }

    @Description("Check if user can Edit Abbreviated number and Assign it to Internal user")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "abbreviatedDialPageTest"})
    public void CheckIfUserCanEditAbbreviatedNumberAndAssignItToInternalUser(){
        User user;

            step("Prepare test data");
            String singleShortNumber = getRandomNumber(201,299);
            user = new User();
        try {
            step("Login the system");
            login();

            step("Create user");
            createUser(user);

            step("Add single Abbreviated Number");
            addSingleAbbrevNumber(singleShortNumber);

            step("Click on Edit button and open Assign abbreviated dialling popup");
            abbreviatedNumbers.editSingleAbbrevNumber(singleShortNumber);

            step("Assign short number to user");
            popupAssignAbbrevDial.getRadioInternalUser().click();
            popupAssignAbbrevDial.getDropdrownSelectUser().selectOptionContainingText(user.getLastName());
            popupAssignAbbrevDial.getCheckboxForwardAsExternal().click();
            popupAssignAbbrevDial.getButtonSave().click();

            step("Check if short dial was assign to the user and user's info is showed in the grid");
            abbreviatedNumbers.getListCompany().filterBy(Condition.text(user.getLastName())).shouldHave(CollectionCondition.sizeGreaterThan(0));
            abbreviatedNumbers.getListCompany().filterBy(Condition.text(user.getFirstName())).shouldHave(CollectionCondition.sizeGreaterThan(0));
        } finally {
            step("Clear test data - delete user");
            deleteUser(user);
            deleteAllAbbrevNumbers();
        }
    }

    @Description("Check if user can Edit Abbreviated number and Assign it to External user")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "abbreviatedDialPageTest"})
    public void CheckIfUserCanEditAbbreviatedNumberAndAssignItToExternalUser(){
        step("Prepare test data");
        HashMap<String,String> dat = new HashMap<String, String>(){{
            put("shortNumber",getRandomNumber(201,299));
            put("extPhoneNum", getRandomPhone());
            put("lastName", getRandomString(5));
            put("firstName", getRandomString(5));
            put("company", getRandomString(10));
        }};

        try {
            step("Login the system");
            login();

            step("Add single Abbreviated Number");
            addSingleAbbrevNumber(dat.get("shortNumber"));
            waitUntilAlertDisappear();

            step("Click on Edit button and open Assign abbreviated dialling popup");
            abbreviatedNumbers.editSingleAbbrevNumber(dat.get("shortNumber"));

            step("Select External number radio");
            popupAssignAbbrevDial.getRadioExternalNumber().click();

            step("Fill in External number phone");
            popupAssignAbbrevDial.getInputExternalNumber().setValue(dat.get("extPhoneNum"));

            step("Fill in Last name");
            popupAssignAbbrevDial.getInputLastName().setValue(dat.get("lastName"));

            step("Fill in First name");
            popupAssignAbbrevDial.getInputFirstName().setValue(dat.get("firstName"));

            step("Fill in Company and save all changes");
            popupAssignAbbrevDial.getInputCompany().setValue(dat.get("company"));
            popupAssignAbbrevDial.getButtonSave().click();
            refreshPage();//temporary overcome because of bug

            step("Check if External user info is displayed in the Abbreviated dialling grid");
            abbreviatedNumbers.checkIfExternalUserInfoIsDisplayedInTheAbbreviatedDiallingGrid(dat);

            step("Edit short dial, make it unUsed and Delete");
            abbreviatedNumbers.editSingleAbbrevNumber(dat.get("shortNumber"));
            popupAssignAbbrevDial.getRadioUnused().click();
            popupAssignAbbrevDial.getButtonSave().click();
            refreshPage();//temporary overcome because of bug
            deleteSingleAbbrevNumber(dat.get("shortNumber"));
        } finally {
            step("Delete test data - delete all short numbers");
            deleteAllAbbrevNumbers();
        }
    }
}
