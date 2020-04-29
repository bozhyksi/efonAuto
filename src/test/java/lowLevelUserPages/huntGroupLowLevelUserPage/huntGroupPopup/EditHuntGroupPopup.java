package lowLevelUserPages.huntGroupLowLevelUserPage.huntGroupPopup;

import com.codeborne.selenide.Condition;
import lowLevelUserPages.huntGroupLowLevelUserPage.HuntGroupUserPage;
import pages.huntGroupPage.huntGroupPopup.AddFullDaysPopup;
import pages.huntGroupPage.huntGroupPopup.CreateHuntGroupPopup;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.queuesPageTest.queueTestData.Queue;

import static com.codeborne.selenide.Condition.*;
import static pages.huntGroupPage.huntGroupPopup.CreateHuntGroupPopup.QueueActions.*;

public class EditHuntGroupPopup extends HuntGroupUserPage {
    private CreateHuntGroupPopup createHuntGroupPopup = new CreateHuntGroupPopup();
    private AddFullDaysPopup addFullDaysPopup = new AddFullDaysPopup();

    public void cofigureVoicemailSection(HuntGroup huntGroup) {
        createHuntGroupPopup.getButtonSubmitVoicemail().click();
        createHuntGroupPopup.getInputPin().setValue(huntGroup.getPinCode());
        createHuntGroupPopup.getInputEmail().setValue(huntGroup.getVoicemailEmail());
        createHuntGroupPopup.getInputSalutation().setValue(huntGroup.getSalutation());
        createHuntGroupPopup.getButtonSubmitVoicemail().click();
        createHuntGroupPopup.getButtonSave().click();
        waitUntilAlertDisappear();
    }

    public void verifyVoicemailSection(HuntGroup huntGroup) {
        createHuntGroupPopup.getButtonSubmitVoicemail().click();
        createHuntGroupPopup.getInputPin().shouldHave(value(huntGroup.getPinCode()));
        createHuntGroupPopup.getInputEmail().shouldHave(value(huntGroup.getVoicemailEmail()));
        createHuntGroupPopup.getInputSalutation().shouldHave(value(huntGroup.getSalutation()));
        refreshPage();
    }

    public void configureEndDeviceNotAvailableSection(HuntGroup huntGroup) {
        createHuntGroupPopup.getButtonSubmitRelevantAccount().click();
        createHuntGroupPopup.getDropdownRelevantAccount().selectOption(1);
        huntGroup.setRelevantAccount(createHuntGroupPopup.getDropdownRelevantAccount().getSelectedText());
        createHuntGroupPopup.getDropdownBackUpType().selectOptionByValue("3");
        createHuntGroupPopup.getInputBackUpNumber().setValue(huntGroup.getBackUpNumber());
        createHuntGroupPopup.getButtonSubmitRelevantAccount().click();
        createHuntGroupPopup.getButtonSave().click();
        waitUntilAlertDisappear();
    }

    public void verifyEndDeviceNotAvailableSection(HuntGroup huntGroup){
        createHuntGroupPopup.getButtonSubmitRelevantAccount().click();
        createHuntGroupPopup.getDropdownRelevantAccount().getSelectedOption().shouldHave(text(huntGroup.getRelevantAccount()));
        createHuntGroupPopup.getInputBackUpNumber().shouldHave(value(huntGroup.getBackUpNumber()));
        refreshPage();
    }

    public void configureFullDays(HuntGroup huntGroup, Queue queue, FileManagementTestData announcement) {
        createHuntGroupPopup.getButtonAdd().click();
        addFullDaysPopup.getInputFullDay().setValue(huntGroup.getFullDayName());
        addFullDaysPopup.getInputDates().setValue(huntGroup.getFullDayDate());
        addFullDaysPopup.configureLevel("12", NumberEndDevice, huntGroup.getFullDayPhoneNumber());
        addFullDaysPopup.configureLevel("26", VoicemailBusy);
        addFullDaysPopup.configureLevel("44", Announcements, announcement);
        addFullDaysPopup.configureLevel("15", Queue, queue);
        addFullDaysPopup.getButtonSave().click();
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
}
