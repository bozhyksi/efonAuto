package pages.userPage.userPagePopup.configureUser;

import com.codeborne.selenide.SelenideElement;

public class FaxTabConfigUserPopup extends ConfigureUserBasePopup {
    private String dropdownMyNumbersXpath = "//portal-tabs//h3/following-sibling::select";
    private String buttonEditFaxXpath = "//edit-user//i[contains(@class,\"fa-cog\")]/parent::a";
    private String inputEmailXpath = "//portal-tabs//input[@formcontrolname=\"fax2emailEmail\"]";
    private String checkboxTiffAndPdf = "//form//span[contains(text(),\"TIFF and PDF\")]/preceding-sibling::input";

    public SelenideElement getDropdownMyNumbers() {
        return field(dropdownMyNumbersXpath);
    }

    public SelenideElement getButtonEditFax() {
        return field(buttonEditFaxXpath);
    }

    public SelenideElement getInputEmail() {
        return field(inputEmailXpath);
    }

    public SelenideElement getCheckboxTiffAndPdf() {
        return field(checkboxTiffAndPdf);
    }
}
