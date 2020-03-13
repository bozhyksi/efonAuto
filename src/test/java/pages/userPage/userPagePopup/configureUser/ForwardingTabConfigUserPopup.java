package pages.userPage.userPagePopup.configureUser;

import com.codeborne.selenide.SelenideElement;

public class ForwardingTabConfigUserPopup extends ConfigureUserBasePopup {
    private String dropdownMyNumbersXpath = "//portal-tabs//h3/following-sibling::select";
    private String checkboxAfterXpath = "//form//h2/following-sibling::section//input[@formcontrolname=\"isEnabled\"]";
    private String inputForwardToPhoneXpath = "//form//input[@formcontrolname=\"number\"]";

    public SelenideElement getDropdownMyNumbers() {
        return field(dropdownMyNumbersXpath);
    }

    public SelenideElement getCheckboxAfter() {
        return field(checkboxAfterXpath);
    }

    public SelenideElement getInputForwardToPhone() {
        return field(inputForwardToPhoneXpath);
    }
}
