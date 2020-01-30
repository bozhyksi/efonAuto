package tests.userPageTests;

import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import tests.userPageTests.userPageTestData.User;

import static io.qameta.allure.Allure.step;

public class UserPageTests extends BaseTestMethods {

    @Description("Check if VPBX admin is able to create users")
    @Test(groups = {"regression", "smoke", "userPageTests","ttt"})
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
        user.setPhoneNumber(createUserPopup.selectNumber());
        createUserPopup.selectEndDevices();

        step("Save all made changes");
        createUserPopup.getButtonSave().click();

        step("Verify if user was created with correct data");
        userPage.checkIfUserExistsInTheList(user);

        step("Clear test data, delete created user");
        deleteUser(user);
    }

    @Description("Check if VPBX admin is able to DELETE users")
    @Test(groups = {"regression", "smoke", "userPageTests"})
    public void CheckifVpbxAdminisAbletoDeleteUsers(){
        step("Preparing test data object - User");
        User user = new User();

        step("Create new user");
        createUser(user);

        step("Delete created user");
        userPage.deleteUserButtonClick(user.getFullName());

        step("Confirm deleting");
        confirmationPopup.getYesButton().click();

        step("Check if user was deleted");
        userPage.checkIfUserDeleted(user);
    }

}
