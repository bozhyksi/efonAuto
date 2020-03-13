package pages.userPage.userPagePopup.configureUser;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class NameTabConfigUserPopup extends ConfigureUserBasePopup{
    //<editor-fold desc="//-- NameTabConfigUserPopup Locators --//">
    private String dropdownTitleXpath = "//form//select[@formcontrolname=\"salutation\"]";
    private String inputFirstNameXpath = "//form//input[@formcontrolname=\"firstName\"]";
    private String inputLastNameXpath = "//form//input[@formcontrolname=\"lastName\"]";
    private String inputLoginEmailXpath = "//form//input[@formcontrolname=\"loginEmail\"]";
    private String checkboxUseDiffContactEmailXpath = "//form//input[@formcontrolname=\"useDifferentContactEmail\"]";
    private String inputDiffContactEmailXpath = "//form//input[@formcontrolname=\"differentContactEmail\"]";
    private String buttonSaveXpath = "//form//button[text()='Save']";
    //</editor-fold>

    //<editor-fold desc="//-- NameTabConfigUserPopup get\set methods --//">
    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getDropdownTitle() {
        return field(dropdownTitleXpath);
    }

    public SelenideElement getInputFirstName() {
        return field(inputFirstNameXpath);
    }

    public SelenideElement getInputLastName() {
        return field(inputLastNameXpath);
    }

    public SelenideElement getInputLoginEmail() {
        return field(inputLoginEmailXpath);
    }

    public SelenideElement getCheckboxUseDiffContactEmail() {
        return field(checkboxUseDiffContactEmailXpath);
    }

    public SelenideElement getInputDiffContactEmail() {
        return field(inputDiffContactEmailXpath);
    }
    //</editor-fold>

    public void validateTitle(String expectedText){
        getDropdownTitle().getSelectedText().contains(expectedText);
    }

    public void validateFirstName(String expectedText){
        getInputFirstName().getText().contains(expectedText);
    }

    public void validateLastName(String expectedText){
        getInputLastName().getText().contains(expectedText);
    }

    public void validateLoginEmail(String expectedText){
        getInputLoginEmail().getText().contains(expectedText);
    }

    public void validateDiffContactEmail(String expectedText){
        getCheckboxUseDiffContactEmail().shouldBe(Condition.selected);
        getInputDiffContactEmail().getText().contains(expectedText);
    }
}
