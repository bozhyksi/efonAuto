package pages.callPickUpPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.basePage.BasePage;
import pages.basePage.basePopup.ConfirmationPopup;
import pages.callPickUpPage.callPickUpPopup.GroupCallPickupPopup;
import tests.callPickUpPageTests.CallPickUpTestData.CallPickUp;

import static com.codeborne.selenide.Condition.exist;

public class CallPickUpPage extends BasePage {
    //<editor-fold desc="locators">
    private final String buttonNewGroupXpath = "//a[text()=\"New Group\"]";
    private final String fieldByTextXpath = "//table//td[contains(text(),\"%s\")]";
    private final String buttonEditByTextXpath= "//table//td[contains(text(),\"%s\")]/..//a[@id=\"editCallPickUp\"]";
    private final String buttonDeleteByTextXpath= "//table//td[contains(text(),\"%s\")]/..//a[@id=\"deleteCallPickUp\"]";
    //</editor-fold>


    //<editor-fold desc="get\set">
    public SelenideElement getButtonNewGroup() {
        return field(buttonNewGroupXpath);
    }

    public SelenideElement getNameByText(String text) {
        return field(String.format(fieldByTextXpath,text));
    }

    public SelenideElement getAbbreviatedDialingByText(String text) {
        return field(String.format(fieldByTextXpath,text));
    }

    public SelenideElement getButtonEditByText(String text) {
        return field(String.format(buttonEditByTextXpath,text));
    }

    public SelenideElement getButtonDeleteByText(String text) {
        return field(String.format(buttonDeleteByTextXpath,text));
    }
    //</editor-fold>

    @Step("Click create new Group for call pick-up")
    public GroupCallPickupPopup clickCreateNewGroup(){
        getButtonNewGroup().click();
        waitUntilAlertDisappear();
        return new GroupCallPickupPopup();
    }

    @Step("Click edit Group for call pick-up")
    public GroupCallPickupPopup editCallPickUp(String callPickUpName){
        getButtonEditByText(callPickUpName).click();
        waitUntilAlertDisappear();
        return new GroupCallPickupPopup();
    }

    @Step("Click edit Group for call pick-up")
    public GroupCallPickupPopup editCallPickUp(CallPickUp callPickUp){
        getButtonEditByText(callPickUp.getName()).click();
        waitUntilAlertDisappear();
        return new GroupCallPickupPopup();
    }

    @Step("Delete call Pickup")
    public CallPickUpPage deleteCallPickUp(CallPickUp callPickUp){
        getButtonDeleteByText(callPickUp.getName()).click();
        waitUntilAlertDisappear();
        new ConfirmationPopup().getYesButton().click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Verify if call pickup does not exist")
    public CallPickUpPage verifyIfCallPickUpDoesNotExist(CallPickUp callPickUp){
        getNameByText(callPickUp.getName()).shouldNot(exist);
        refreshPage();
        getNameByText(callPickUp.getName()).shouldNot(exist);
        return this;
    }

    @Step("Verify if call pickup exists")
    public CallPickUpPage verifyIfCallPickUpExists(CallPickUp callPickUp){
        getNameByText(callPickUp.getName()).should(exist);
        refreshPage();
        getNameByText(callPickUp.getName()).should(exist);
        return this;
    }

}
