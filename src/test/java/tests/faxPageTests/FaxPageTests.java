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

import static api.baseApiMethods.UserApi.createUserApi;
import static api.baseApiMethods.UserApi.deleteUserApi;
import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.FAX;

@Listeners(CustomListeners.class)

public class FaxPageTests extends BaseTestMethods {
    ArrayList<User> userArrayList = new ArrayList<>();

    @Description("Verify if user can configure Fax for newly created user")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "faxPageTests"})
    public void fax2EmailConfigurationsTest() {
        String fax2email = getRandomEmail();
        User user = new User();
        userArrayList.add(user);

        createUserApi(user.getJson());
        login()
                .goToMenuTab(FAX);
        faxPage
                .selectNumber(user.getPhoneNumber())
                .clickEditFax2Email()
                .enterEmail(fax2email)
                .selectPdfOnly()
                .saveChanges()
                .clickEditFax2Email()
                .validateSavedData(fax2email);
        deleteUserApi(user.getId());
    }

    @AfterClass(alwaysRun = true)
    private void cleanUp(){
        userCleanUp(userArrayList);
    }
}
