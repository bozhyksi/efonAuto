package tests.faxPageTests;

import com.codeborne.selenide.Condition;
import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static io.qameta.allure.Allure.step;

@Listeners(CustomListeners.class)

public class FaxPageTests extends BaseTestMethods {
    ArrayList<User> userArrayList = new ArrayList<>();

    @Description("Verify if user can Fax for newly created user")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "faxPageTests"})
    public void VerifyIfUserCanСonfigureFaxForNewlyCreatedUser() {
        step("Prepare test data - create test user object");
        User user = new User();
        userArrayList.add(user);

        step("Login");
        login();

        step("Create new user");
        createUser(user);

        step("Goto Fax page");
        basePage.getTabFax().click();

        step("Select phone number for fax");
        faxPage.getDropdownSelectNumber().selectOption(0);

        step("Click configure button");
        faxPage.getEditButton().click();

        step("Fill in fax email");
        faxPage.getInputEmail().setValue(getRandomEmail());

        step("Select fax format - pdf only");
        faxPage.getRadioPdfOnly().click();

        step("Save Changes and validate");
        faxPage.getButtonSave().click();
        alertPopup.getAlertDialog().should(Condition.appears);

        step("Delete test data - delete test user");
        refreshPage();
        deleteUser(user);
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        startBrowser();
        login();
        userCleanUp(userArrayList);
        closeBrowser();
    }
}
