package lowLevelUserPages.callForwardingLowLevelUserPage;

import com.codeborne.selenide.Condition;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;
import pages.callForwardingPage.CallForwardingPage;

import static io.qameta.allure.Allure.step;

public class CallForwardingUserPage extends BasePageLowLevelUser {

    public void configureAfterSection(CallForwardingPage callForwardingPage, String delay, String forwardToPhone){
        step("Activate \"After\" checkbox");
        if (!callForwardingPage.getCheckboxAfter().isSelected()) callForwardingPage.getCheckboxAfter().click();

        step("Fill in delay");
        callForwardingPage.getInputDelay().setValue(delay);

        step("Select value from \"ForwardTo\" dropdown");
        callForwardingPage.getDropdownAfterForwardTo().selectOptionContainingText("Phone");

        step("Fill in phone field");
        callForwardingPage.getInputAfterPhone().setValue(forwardToPhone);

        step("Save changes");
        callForwardingPage.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();

        step("Verify if all entered data was saved");
        callForwardingPage.getInputDelay().shouldHave(Condition.value(delay));
        callForwardingPage.getDropdownAfterForwardTo().getSelectedOption().shouldHave(Condition.text("Phone"));
        callForwardingPage.getInputAfterPhone().shouldHave(Condition.value(forwardToPhone));
    }

    public void configureIfBusySection(CallForwardingPage callForwardingPage, String forwardToPhone){
        step("Activate \"If Busy\" checkbox");
        if (!callForwardingPage.getCheckboxIfbusy().isSelected())callForwardingPage.getCheckboxIfbusy().click();

        step("Select value from \"ForwardTo\" dropdown");
        callForwardingPage.getDropdownIfbusyForwardTo().selectOption("Phone");

        step("Fill in phone field");
        callForwardingPage.getInputIfbusyPhone().setValue(forwardToPhone);

        step("Save changes");
        callForwardingPage.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();

        step("Verify if all entered data was saved");
        callForwardingPage.getDropdownIfbusyForwardTo().getSelectedOption().shouldHave(Condition.text("Phone"));
        callForwardingPage.getInputIfbusyPhone().shouldHave(Condition.value(forwardToPhone));
    }

    public void configureIfEndDeviceUnavailableSection(CallForwardingPage callForwardingPage, String forwardToPhone){
        step("Activate \"If end device unavailable (not registered)\" checkbox");
        if (!callForwardingPage.getCheckboxDeviceUnavailable().isSelected())callForwardingPage.getCheckboxDeviceUnavailable().click();

        step("Select value from \"ForwardTo\" dropdown");
        callForwardingPage.getDropdownIfbusyForwardTo().selectOption("Phone");

        step("Fill in phone field");
        callForwardingPage.getInputDevicePhone().setValue(forwardToPhone);

        step("Save changes");
        callForwardingPage.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();

        step("Verify if all entered data was saved");
        callForwardingPage.getDropdownDeviceForwardTo().getSelectedOption().shouldHave(Condition.text("Phone"));
        callForwardingPage.getInputDevicePhone().shouldHave(Condition.value(forwardToPhone));
    }

    public void configureSuppressedNumbersSection(CallForwardingPage callForwardingPage, String forwardToPhone){
        step("Activate \"Calls with suppressed numbers\" checkbox");
        if (!callForwardingPage.getCheckboxSuppressedNumbers().isSelected())callForwardingPage.getCheckboxSuppressedNumbers().click();

        step("Select value from \"ForwardTo\" dropdown");
        callForwardingPage.getDropdownSupprNumForwardTo().selectOptionByValue("VOICEMAIL");

        step("Save changes");
        callForwardingPage.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();

        step("Verify if all entered data was saved");
        callForwardingPage.getDropdownSupprNumForwardTo().getSelectedOption().shouldHave(Condition.value("VOICEMAIL"));
    }

}
