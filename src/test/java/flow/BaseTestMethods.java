package flow;

import core.configuration.preparations.eFonApp;

import java.util.Random;

public class BaseTestMethods extends eFonApp {

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

    public void login() {
        loginPage.fillInLogin(getLogin());
        loginPage.fillInPassword(getPassword());
        loginPage.getButtonLogin().click();
    }
}
