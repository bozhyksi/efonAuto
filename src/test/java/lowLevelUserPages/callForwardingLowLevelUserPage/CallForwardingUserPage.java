package lowLevelUserPages.callForwardingLowLevelUserPage;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;
import pages.callForwardingPage.CallForwardingPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static core.configuration.preparations.eFonApp.callForwardingPage;
import static io.qameta.allure.Allure.step;

public class CallForwardingUserPage extends BasePageLowLevelUser {

    public void configureAfterSection(String delay, String forwardToPhone){
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
        callForwardingPage.getInputDelay().shouldHave(value(delay));
        callForwardingPage.getDropdownAfterForwardTo().getSelectedOption().shouldHave(text("Phone"));
        callForwardingPage.getInputAfterPhone().shouldHave(value(forwardToPhone));
    }

    public void configureIfBusySection(String forwardToPhone){
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
        callForwardingPage.getDropdownIfbusyForwardTo().getSelectedOption().shouldHave(text("Phone"));
        callForwardingPage.getInputIfbusyPhone().shouldHave(value(forwardToPhone));
    }

    @Step("Configure \"If end device unavailable (not registered)\" section")
    public void configureIfEndDeviceUnavailableSection(String forwardToPhone){
        if (!callForwardingPage.getCheckboxDeviceUnavailable().isSelected())
            callForwardingPage.getCheckboxDeviceUnavailable().click();
        callForwardingPage.getDropdownIfbusyForwardTo().selectOption("Phone");
        callForwardingPage.getInputDevicePhone().setValue(forwardToPhone);
        callForwardingPage.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();
        callForwardingPage.getDropdownDeviceForwardTo().getSelectedOption().shouldHave(text("Phone"));
        callForwardingPage.getInputDevicePhone().shouldHave(value(forwardToPhone));
    }

    public void configureSuppressedNumbersSection( String forwardToPhone){
        step("Activate \"Calls with suppressed numbers\" checkbox");
        if (!callForwardingPage.getCheckboxSuppressedNumbers().isSelected())callForwardingPage.getCheckboxSuppressedNumbers().click();

        step("Select value from \"ForwardTo\" dropdown");
        callForwardingPage.getDropdownSupprNumForwardTo().selectOptionByValue("VOICEMAIL");

        step("Save changes");
        callForwardingPage.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();

        step("Verify if all entered data was saved");
        callForwardingPage.getDropdownSupprNumForwardTo().getSelectedOption().shouldHave(value("VOICEMAIL"));
    }

    @Step("Check if Select number dropdown contains only user number")
    public CallForwardingUserPage verifyMyNumbersDropDownItems(){
        super.validateDropDownItems(callForwardingPage.getDropdownMyNumbers());
        return this;
    }

}
