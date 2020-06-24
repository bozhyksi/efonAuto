package lowLevelUserPages.huntGroupLowLevelUserPage;

import io.qameta.allure.Step;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;

import static com.codeborne.selenide.Condition.exist;
import static core.configuration.preparations.eFonApp.huntGroupPage;
import static pages.basePage.BasePage.MenuTabsBasePage.HUNT_GROUPS;

public class HuntGroupUserPage extends BasePageLowLevelUser {

    public void editHuntGroup(HuntGroup huntGroup){
        huntGroupPage.clickEditHuntGroup(huntGroup.getHuntGroupName());
    }

    public void verifyIfCallRecordingActive(HuntGroup huntGroup){
        huntGroupPage.verifyIfCallRecordingWasActivated(huntGroup);
    }

    public void verifyDisplayedHuntGroupDataWasNotChanged(HuntGroup huntGroup){
        huntGroupPage.getfieldNameByText(huntGroup.getHuntGroupName()).should(exist);
        huntGroupPage.getFieldDisplayNameByText(huntGroup.getHuntGroupDisplayName()).should(exist);
        huntGroupPage.getFieldNumberByText(huntGroup.getHuntGroupNumber()).should(exist);
        huntGroupPage.verifyIfCallRecordingWasActivated(huntGroup);
    }

    @Step("Verify if HuntGroup exists in the list")
    public HuntGroupUserPage verifyIfHuntGroupIsAvailable(String huntGroupName){
        goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .getfieldNameByText(huntGroupName).should(exist);
        return this;
    }

    @Step("Verify if HuntGroup does not exists in the list")
    public HuntGroupUserPage verifyIfHuntGroupIsNOTAvailable(String huntGroupName){
        goToMenuTab(HUNT_GROUPS);
        huntGroupPage
                .getfieldNameByText(huntGroupName).shouldNot(exist);
        return this;
    }

}
