package pages.huntGroupPage.huntGroupPopup.createHuntGropuPopup;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.userPageTests.userPageTestData.EndDevice;

import static com.codeborne.selenide.Condition.value;

public class EndDevicesNotAvailableSection extends CreateHuntGroupPopup {

    //<editor-fold desc="locators">
    private final String dropdownRelevantAccountXpath = "//select[@formcontrolname=\"backUpAccount\"]";
    private final String dropdownBackUpTypeXpath = "//select[@formcontrolname=\"backUpType\"]";
    private final String inputBackUpNumberXpath = "//input[@formcontrolname=\"backUpNumber\"]";
    private final String buttonSubmitRelevantAccountXpath = "//h2[text()=\"If end devices not available (not registered)\"]/a";
    //</editor-fold>

    @Step("Select Relevant account")
    public EndDevicesNotAvailableSection selectRelevantEndDevice(){
        field(dropdownRelevantAccountXpath).selectOption(3);
        return this;
    }

    @Step("Enter back up number")
    public EndDevicesNotAvailableSection enterBackupNumber(String num){
        field(dropdownBackUpTypeXpath).selectOptionContainingText("this number");
        field(inputBackUpNumberXpath).setValue(num);
        return this;
    }

    @Step("Verify selected Relevant account")
    public EndDevicesNotAvailableSection verifyRelevantAccountConfiguration(HuntGroup huntGroup){
        field(inputBackUpNumberXpath).shouldHave(value(huntGroup.getBackUpNumber()));
        return this;
    }

    @Step("Submit changes")
    public CreateHuntGroupPopup submit(){
        field(buttonSubmitRelevantAccountXpath).click();
        waitUntilAlertDisappear();
        return this;
    }

}
