package pages.loginPage;

import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;

public class LoginPage extends BasePage {

    private String inputLoginFieldXpath = "//*[@id='j_username']";
    private String inputPasswordFieldXpath = "//*[@id='j_password']";
    private String buttonLoginXpath = "//button[contains(text(),'Login')]";
    private String linkCreatePasswordXpath = "//a[contains(text(),'Create password')]";
    private String imgUpcBusinessXpath = "//a[@href=\"http://www.e-fon.ch/\"]";

    public SelenideElement getButtonLogin() {
        return field(buttonLoginXpath);
    }

    public SelenideElement getInputLoginField() {
        return field(inputLoginFieldXpath);
    }

    public SelenideElement getInputPasswordField() {
        return field(inputPasswordFieldXpath);
    }

    public void fillInLogin(String login){
        field(inputLoginFieldXpath).setValue(login);
    }

    public void fillInPassword(String password){
        field(inputPasswordFieldXpath).setValue(password);
    }

    public void clickLoginButton(){
        field(buttonLoginXpath).click();
    }

    public void clickUpcBusinessImg(){
        field(imgUpcBusinessXpath).click();
    }

}
