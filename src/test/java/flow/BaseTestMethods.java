package flow;

import com.codeborne.selenide.Selenide;
import core.configuration.preparations.eFonApp;
import tests.userPageTests.userPageTestData.User;

import java.util.Random;

public class BaseTestMethods extends eFonApp {

    public void refreshPage(){
        Selenide.refresh();
    }

    public String getRandomEmail() {
        return "L"+getRandomString(8)+"@email.com";
    }

    public String getRandomPassword(){
        return "L"+getRandomString(5)+"123!!!+++";
    }

    public String getRandomString(int length){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }
        return String.valueOf(sb);
    }

    public String getRandomNumber(int length){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) (random.nextInt(8) + '1'));
        }
        return String.valueOf(sb);
    }

    public String getRandomNumber(int min, int max){
        Random r = new Random();
        return String.valueOf(r.nextInt((max - min) + 1) + min);
    }

    public String getRandomPhone(String prefix){
        if (prefix.equals("+")) return prefix + getRandomNumber(11);
        else return "00"+getRandomNumber(11);
    }

    public String getRandomPhone(){
        return "00"+getRandomNumber(11);
    }

    public void login() {
        loginPage.fillInLogin(getLogin());
        loginPage.fillInPassword(getPassword());
        loginPage.getButtonLogin().click();
    }

    public void createUser(User user){
        login();
        basePage.getTabUser().click();
        userPage.getPageTitle().getText().equals("User");
        userPage.getButtonCreateNewUser().click();
        createUserPopup.getPopupTitle().getText().equals("Create user");
        createUserPopup.selectTitle(user.getTitle());
        createUserPopup.fillFirstName(user.getFirstName());
        createUserPopup.fillLastName(user.getLastName());
        createUserPopup.fillLoginEmail(user.getLoginEmail());
        user.setPhoneNumber(createUserPopup.selectNumber());
        createUserPopup.selectEndDevices();
        createUserPopup.getButtonSave().click();
        userPage.checkIfUserExistsInTheList(user);
    }

    public void deleteUser(User user){
        userPage.deleteUserButtonClick(user.getFullName());
        confirmationPopup.getYesButton().click();
        userPage.checkIfUserDeleted(user);
    }
}
