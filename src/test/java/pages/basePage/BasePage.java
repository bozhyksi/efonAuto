package pages.basePage;

import com.codeborne.selenide.SelenideElement;
import core.configuration.fields.Fields;

public class BasePage extends Fields {

    private String buttonLogoutXpath = "//span[text()='Logout']/parent::button";
    private String imgUpcBusinessXpath = "//a[@href=\"http://www.e-fon.ch\"]";
    private String linkFeedbackXpath = "//span[text()='Feedback']/parent::a";
    private String dropdownHelpXpath = "//*[@id=\"helpLabel\"]";
    private String dropdownHelpItemKnowledgeDatabaseXpath = "//*[@class=\"dropdown open\"]//a[text()='Knowledge database']";
    private String dropdownHelpItemSupportRequestXpath = "//*[@class=\"dropdown open\"]//a[text()='Support request']";
    private String linkUserNameXpath = "//div[@class=\"col-xs-7\"]//a[@href=\"/portal/contact-data\"]";
    private String divMainMenu = "//main-menu//*[@id=\"navbar-collapse\"]";

    public SelenideElement getMainMenu() {
        return field(divMainMenu);
    }

    public SelenideElement getButtonLogout() {
        return field(buttonLogoutXpath);
    }

    public void clickButtonLogout(){
        field(buttonLogoutXpath).click();
    }

    public void clickImgUpcBusiness(){
        field(imgUpcBusinessXpath).click();
    }

    public void clickLinkFeedback(){
        field(linkFeedbackXpath).click();
    }

    public void clickDropdownHelp(){
        field(dropdownHelpXpath).click();
    }

    public void clickDropdownHelpItemKnowledgeDatabase(){
        clickDropdownHelp();
        field(dropdownHelpItemKnowledgeDatabaseXpath).click();
    }

    public void clickDropdownHelpItemSupportRequest(){
        clickDropdownHelp();
        field(dropdownHelpItemSupportRequestXpath).click();
    }

    public void clickLinkUserName(){
        field(linkUserNameXpath).click();
    }

}
