package pages.userPage.userPagePopup.configureUser;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import tests.userPageTests.userPageTestData.User;

import static com.codeborne.selenide.Condition.selected;
import static io.qameta.allure.Allure.step;

public class FaxTabConfigUserPopup extends ConfigureUserBasePopup {
    //<editor-fold desc="locators">
    private String dropdownMyNumbersXpath = "//portal-tabs//h3/following-sibling::select";
    private String buttonEditFaxXpath = "//edit-user//i[contains(@class,\"fa-cog\")]/parent::a";
    private String inputEmailXpath = "//portal-tabs//input[@formcontrolname=\"fax2emailEmail\"]";
    private String checkboxTiffAndPdf = "//input[@formcontrolname=\"fax2emailFormat\"][1]";
    private String checkboxTiff = "//input[@formcontrolname=\"fax2emailFormat\"][2]";
    private String checkboxPdf = "//input[@formcontrolname=\"fax2emailFormat\"][3]";
    //</editor-fold>

    //<editor-fold desc="get\set">


    public SelenideElement getCheckboxPdf() {
        return field(checkboxPdf);
    }

    public SelenideElement getCheckboxTiff() {
        return field(checkboxTiff);
    }

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
    //</editor-fold>

    @Step("Select user number from \"Select number\" drop-down")
    public FaxTabConfigUserPopup selectNumber(String num){
        getDropdownMyNumbers().selectOptionContainingText(num);
        return this;
    }

    @Step("Click edit")
    public FaxTabConfigUserPopup clickEdit(){
        getButtonEditFax().click();
        return this;
    }

    @Step("Fill in Email input")
    public FaxTabConfigUserPopup enterEmail(String email){
        getInputEmail().setValue(email);
        return this;
    }

    @Step("Select TIFF and PDF")
    public FaxTabConfigUserPopup selectFaxReceiveFormat(String format){
        switch (format){
            case "TIFF_and_PDF":
                getCheckboxTiffAndPdf().click();
                break;
            case "PDF":
                getCheckboxPdf().click();
                break;
            case "TIFF":
                getCheckboxTiff().click();
                break;
        }
        return this;
    }

    @Step("Save")
    public FaxTabConfigUserPopup save(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Verify fax settings")
    public FaxTabConfigUserPopup verifyFaxSettings(User user){
        getDropdownMyNumbers().selectOptionContainingText(user.getPhoneNumber());
        getButtonEditFax().click();
        getCheckboxTiffAndPdf().shouldBe(Condition.selected);
        getInputEmail().shouldHave(Condition.value(user.getFaxEmail()));
        return this;
    }





}
