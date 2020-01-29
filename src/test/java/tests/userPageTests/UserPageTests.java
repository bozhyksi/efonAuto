package tests.userPageTests;

import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import tests.userPageTests.userPageTestData.User;

import static io.qameta.allure.Allure.step;

public class UserPageTests extends BaseTestMethods {

    @Description("Check if VPBX admin is able to create users")
    @Test(groups = {"regression", "smoke", "userPageTests"})
    public void CheckIfVpbxAdminIsAbleToCreateUsers(){
        step("Preparing test data object - User");
        User user = new User();

        step("Log in the system as VPBX admin and goto Users tab");
        login();
        basePage.getTabUser().click();

        step("Verify if correct page was loaded");
        userPage.getPageTitle().getText().equals("User");

        step("Click \"Create New User\"");
        userPage.getButtonCreateNewUser().click();

        step("Verify if correct popup opened");
        createUserPopup.getPopupTitle().getText().equals("Create user");

        step("Fill in all mandatory fields - Title, First/Last name, Login, Number, End-device");
        createUserPopup.selectTitle(user.getTitle());
        createUserPopup.fillFirstName(user.getFirstName());
        createUserPopup.fillLastName(user.getLastName());
        createUserPopup.fillLoginEmail(user.getLoginEmail());
        createUserPopup.selectNumber();
        createUserPopup.selectEndDevices();

        step("Save all made changes");
        createUserPopup.getButtonSave().click();
    }
}
