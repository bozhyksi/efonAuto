package tests.callPickUpPageTests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;

public class callPickUpPageTests extends BaseTestMethods {
    @Description("Check if user can create new Call Pick Up group")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "callPickUpPageTests"})
    public void CheckIfUserCanCreateNewCallPickUpGroup(){
        step("Prepare test data");
        String groupName = getRandomString(10);
        String abbrevNum = getRandomNumber(201,299);
        try {
            step("Log in the system");
            login();

            step("Create new single Abbreviated Number");
            addSingleAbbrevNumber(abbrevNum);

            step("Goto call PickUps tab and create new Call Pickup group");
            basePage.getTabCallPickUps().click();
            callPickUpPage.getButtonNewGroup().click();
            groupCallPickupPopup.getInputName().setValue(groupName);
            groupCallPickupPopup.selectAbbrenNumber(abbrevNum);
            groupCallPickupPopup.getDropdownSelectAccounts().selectOption(1);
            groupCallPickupPopup.getButtonSave().click();

            step("Verify if Call PickUp group was created");
            callPickUpPage.getListName().filterBy(Condition.text(groupName)).shouldHave(CollectionCondition.size(1));
            callPickUpPage.getListAbbrevDial().filterBy(Condition.text(abbrevNum)).shouldHave(CollectionCondition.size(1));

            step("Delete CallPickUp group");
            callPickUpPage.deletePickUpGroup(groupName);
            confirmationPopup.getYesButton().click();

            step("Verify if Call PickUp group was Deleted");
            callPickUpPage.getListName().filterBy(Condition.text(groupName)).shouldHave(CollectionCondition.size(0));
            callPickUpPage.getListAbbrevDial().filterBy(Condition.text(abbrevNum)).shouldHave(CollectionCondition.size(0));
        } finally {
            step("Delete created Abbreviated number");
            refreshPage();
            deleteSingleAbbrevNumber(abbrevNum);
        }
    }
}
