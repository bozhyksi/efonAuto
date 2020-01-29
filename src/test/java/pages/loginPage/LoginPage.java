package pages.loginPage;

import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;

public class LoginPage extends BasePage {

    //<editor-fold desc="//-- LoginPage locators --//">
    private String inputLoginFieldXpath = "//*[@id='j_username']";
    private String inputPasswordFieldXpath = "//*[@id='j_password']";
    private String buttonLoginXpath = "//button[contains(text(),'Login')]";
    private String linkCreatePasswordXpath = "//a[contains(text(),'Create password')]";
    private String imgUpcBusinessXpath = "//a[@href=\"http://www.e-fon.ch/\"]";
    private String messageLoginErrorXpath = "//div[@id=\"login-error\"]";
    //</editor-fold>

    //<editor-fold desc="//-- LoginPage get\set nethods --//">
    public SelenideElement getMessageLoginError() {
        return field(messageLoginErrorXpath);
    }

    public SelenideElement getButtonLogin() {
        return field(buttonLoginXpath);
    }

    public SelenideElement getInputLoginField() {
        return field(inputLoginFieldXpath);
    }

    public SelenideElement getInputPasswordField() {
        return field(inputPasswordFieldXpath);
    }
    //</editor-fold>

    public void fillInLogin(String login){
        getInputLoginField().setValue(login);
    }

    public void fillInPassword(String password){
        getInputPasswordField().setValue(password);
    }

}
