package lowLevelUserPages.sendSmsPageLowLevelUser.sendSmsUserPagePopup;

import com.codeborne.selenide.SelenideElement;
import lowLevelUserPages.sendSmsPageLowLevelUser.ManageSenderNumbersAndNamesUserPage;

public class NewSenderNumberPopup extends ManageSenderNumbersAndNamesUserPage {
    private String inputMobileNumberXpath = "//input[@formcontrolname=\"mobileNumber\"]";

    public SelenideElement getInputMobileNumber() {
        return field(inputMobileNumberXpath);
    }

    @Override
    public SelenideElement getButtonSave() {
        return super.getButtonSave();
    }
}
