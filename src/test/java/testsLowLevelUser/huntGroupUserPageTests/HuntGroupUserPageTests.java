package testsLowLevelUser.huntGroupUserPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;

import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.HUNT_GROUPS;

@Listeners(CustomListeners.class)

public class HuntGroupUserPageTests extends BaseTestMethods {
    private String huntGroupName = "AutoTestHuntGroup";
    private String huntGroupNumber = "044225787864";

    @Description("Check if low-level user can edit HuntGroup")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "huntGroupUserPageTests"})
    public void CheckIfLowLevelUserCanEditHuntGroup(){
        HuntGroup huntGroup = new HuntGroup(huntGroupName, huntGroupNumber);

        step("Log in the system as Low-Level User");
        loginAsLowLevelUser();

        step("Goto Hunt Group tab");
        basePageLowLevelUser.goToMenuTab(HUNT_GROUPS);

        step("Open hunt group edit popup");
        huntGroupUserPage.editHuntGroup(huntGroup);

        step("Change language");
        createHuntGroupPopup.changeHuntGroupLanguage(huntGroup.getHuntGroupLanguage());

        step("Save changes");
        createHuntGroupPopup.getButtonSave().click();
        waitUntilAlertDisappear();

        step("Verify made changes");
        huntGroupUserPage.verifyDisplayedHuntGroupDataWasNotChanged(huntGroup);
        huntGroupUserPage.editHuntGroup(huntGroup);
        createHuntGroupPopup.getButtonSubmitEditHuntGroup().click();
        createHuntGroupPopup.getDropdownLanguage().getSelectedValue().contains(huntGroup.getHuntGroupLanguage());
    }


}
