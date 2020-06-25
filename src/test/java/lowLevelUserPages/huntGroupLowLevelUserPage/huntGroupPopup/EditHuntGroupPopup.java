package lowLevelUserPages.huntGroupLowLevelUserPage.huntGroupPopup;

import com.codeborne.selenide.Condition;
import lowLevelUserPages.huntGroupLowLevelUserPage.HuntGroupUserPage;
import pages.huntGroupPage.huntGroupPopup.AddFullDaysPopup;
import pages.huntGroupPage.huntGroupPopup.AddFurtherTimePopup;
import pages.huntGroupPage.huntGroupPopup.createHuntGropuPopup.CreateHuntGroupPopup;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.queuesPageTest.queueTestData.Queue;

import static com.codeborne.selenide.Condition.*;

public class EditHuntGroupPopup extends HuntGroupUserPage {
    private CreateHuntGroupPopup createHuntGroupPopup = new CreateHuntGroupPopup();
    private AddFullDaysPopup addFullDaysPopup = new AddFullDaysPopup();

    public void configureFullDays(HuntGroup huntGroup, Queue queue, FileManagementTestData announcement) {
        createHuntGroupPopup.getButtonAdd().click();
        addFullDaysPopup.getInputFullDay().setValue(huntGroup.getFullDayName());
        addFullDaysPopup.getInputDates().setValue(huntGroup.getFullDayDate());
        //addFullDaysPopup.configureLevel("12", NumberEndDevice, huntGroup.getFullDayPhoneNumber());
        //addFullDaysPopup.configureLevel("26", VoicemailBusy);
        //addFullDaysPopup.configureLevel("44", Announcements, announcement);
        //addFullDaysPopup.configureLevel("15", Queue, queue);
        //addFullDaysPopup.getButtonSave().click();
        createHuntGroupPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();
    }

    public void verifyFullDayConfiguration(HuntGroup huntGroup){
        createHuntGroupPopup.getButtonEditFullDay().shouldBe(visible, Condition.enabled).click();
        createHuntGroupPopup.getInputFullDayName().shouldHave(Condition.value(huntGroup.getFullDayName()));
        createHuntGroupPopup.getInputFullDayDate().shouldHave(Condition.value(huntGroup.getFullDayDate()));
        refreshPage();
    }

    public void configureStandartTimers(FileManagementTestData announcement, Queue queue){
        //createHuntGroupPopup.configureStandartTimers(announcement, queue);
    }

    public void verifyStandartTimers(){
        //createHuntGroupPopup.verifyStandartTimersConfiguration();
    }

    public void configureFurtherTimers(HuntGroup huntGroup,Queue queue, FileManagementTestData announcement){
        createHuntGroupPopup.getDropdownTimerGroup().selectOptionContainingText("Time");
        createHuntGroupPopup.getButtonAdd().click();
        waitUntilAlertDisappear();
        //addFurtherTimePopup.fillInTimers(huntGroup);
        //addFurtherTimePopup.configureLevel("12", NumberEndDevice, huntGroup.getFullDayPhoneNumber());
        //addFurtherTimePopup.configureLevel("26", VoicemailBusy);
        //addFurtherTimePopup.configureLevel("15", Queue, queue);
        //addFurtherTimePopup.configureLevel("44", Announcements, announcement);
        //addFurtherTimePopup.getButtonSave().click();
        waitUntilAlertDisappear();
        createHuntGroupPopup.getButtonSave().click();
        waitUntilAlertDisappear();
    }
}
