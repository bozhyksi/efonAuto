package lowLevelUserPages.huntGroupLowLevelUserPage;

import com.codeborne.selenide.Condition;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;
import pages.huntGroupPage.HuntGroupPage;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;

import static com.codeborne.selenide.Condition.exist;

public class HuntGroupUserPage extends BasePageLowLevelUser {
    private HuntGroupPage huntGroupPage = new HuntGroupPage();

    public void editHuntGroup(HuntGroup huntGroup){
        huntGroupPage.editHuntGroup(huntGroup.getHuntGroupName());
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

}
