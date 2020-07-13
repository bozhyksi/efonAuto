package pages.blockListSection.blockListSectionPopup;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.IVRpage.blockListSection.BlocklistPopup;
import pages.basePage.basePopup.BasePopup;
import pages.blockListSection.BlockListSection;

import static com.codeborne.selenide.Condition.exist;
import static core.configuration.preparations.eFonApp.confirmationPopup;

public class BlockedNumbersPopup extends BasePopup {

    public class EditNumber{

        //<editor-fold desc="locators">
        private final String inputBlockedNumber = "//input[@id=\"blockedNumber\"]";
        private final String inputBlockedNumberComment = "//input[@id=\"blockedNumberComment\"]";
        private final String buttonSaveXpath = "//block-numbers-list//button[contains(text(),\"Save\")]";
        private final String buttonCancelXpath = "//block-numbers-list//button[contains(text(),\"Cancel\")]";
        //</editor-fold>

        //<editor-fold desc="get\set">
        public SelenideElement getInputBlockedNumberComment() {
            return field(inputBlockedNumberComment);
        }

        public SelenideElement getInputBlockedNumber() {
            return field(inputBlockedNumber);
        }

        public SelenideElement getButtonCancel() {
            return field(buttonCancelXpath);
        }

        public SelenideElement getButtonSave() {
            return field(buttonSaveXpath);
        }
        //</editor-fold>

        @Step("Edit number")
        public EditNumber editNumber(String number){
            getInputBlockedNumber().setValue(number);
            return this;
        }

        @Step("Edit comment")
        public EditNumber enterComment(String comment){
            getInputBlockedNumberComment().setValue(comment);
            return this;
        }

        @Step("Save changes")
        public BlockedNumbersPopup saveChanges(){
            getButtonSave().click();
            waitUntilAlertDisappear();
            return new BlockedNumbersPopup();
        }

    }

    public class AddNumber{

        //<editor-fold desc="locators">
        private final String inputBlockedNumber = "//input[@id=\"blockedNumber\" or @id=\"permittedNumber\"]";
        private final String inputBlockedNumberComment = "//input[@id=\"blockedNumberComment\" or @id=\"permittedNumberComment\" ]";
        private final String checkboxEntireCustomer = "//input[@id=\"blockedNumberForEntireCustomer\"]";
        private final String buttonSaveXpath = "//*[@id=\"systemModal\"]//button[text()=\"Save\"]";
        private final String buttonCancelXpath = "//*[@id=\"systemModal\"]//button[text()=\"Cancel\"]";
        //</editor-fold>

        //<editor-fold desc="get\set">

        public SelenideElement getButtonSave() {
            return field(buttonSaveXpath);
        }

        public SelenideElement getCheckboxEntireCustomer() {
            return field(checkboxEntireCustomer);
        }
        public SelenideElement getInputBlockedNumber() {
            return field(inputBlockedNumber);
        }
        public SelenideElement getInputBlockedNumberComment() {
            return field(inputBlockedNumberComment);
        }
        //</editor-fold>

        @Step("Enter number")
        public AddNumber enterNumber(String number){
            getInputBlockedNumber().setValue(number);
            waitUntilAlertDisappear();
            return this;
        }

        @Step("Enter comment")
        public AddNumber enterComment(String comment){
            getInputBlockedNumberComment().setValue(comment);
            waitUntilAlertDisappear();
            return this;
        }

        @Step("Activate entire customer")
        public AddNumber activateEntireCustomer(){
            getCheckboxEntireCustomer().click();
            return this;
        }

        @Step("Save number")
        public BlockedNumbersPopup saveNumber(){
            getButtonSave().click();
            waitUntilAlertDisappear();
            return new BlockedNumbersPopup();
        }
    }

    //<editor-fold desc="locators">
    private final String buttonAddXpath = "//*[@id=\"systemModal\"]//button[text()=\"Add\"]";
    private final String buttonDeleteXpath = "//*[@id=\"systemModal\"]//button[text()=\"Delete\"]";
    private final String buttonCloseXpath = "//*[@id=\"systemModal\"]//button[text()=\"Close\"]";


    private final String checkboxSelectNumber = "//td[contains(text(),\"%s\")]/..//input[@type=\"checkbox\"]";
    private final String buttonEditByText = "//*[@id=\"systemModal\"]//td[contains(text(),\"%s\")]/..//i[contains(@class,\"fa-cog\")]/..";
    private final String buttonDeleteByText = "//*[@id=\"systemModal\"]//td[contains(text(),\"%s\")]/..//i[contains(@class,\"fa-trash \")]/..";
    private final String fieldByText = "//*[@id=\"systemModal\"]//td[contains(text(),\"%s\")]";

    //</editor-fold>


    //<editor-fold desc="get\set">

    public SelenideElement getCheckboxSelectNumber(String text) {
        return field(String.format(checkboxSelectNumber,text));
    }

    public SelenideElement getButtonClose() {
        return field(buttonCloseXpath);
    }

    public SelenideElement getButtonAdd() {
        return field(buttonAddXpath);
    }

    public SelenideElement getButtonDelete() {
        return field(buttonDeleteXpath);
    }



    public SelenideElement getButtonEditByText(String text) {
        return field(String.format(buttonEditByText,text));
    }

    public SelenideElement getButtonDeleteByText(String text) {
        return field(String.format(buttonDeleteByText,text));
    }

    public SelenideElement getFieldNumberByText(String text) {
        return field(String.format(fieldByText,text));
    }

    public SelenideElement getFieldCommentByText(String text) {
        return field(String.format(fieldByText,text));
    }
    //</editor-fold>

    @Step("Click ADD button")
    public AddNumber clickAdd(){
        getButtonAdd().click();
        return new AddNumber();
    }

    @Step("Click Edit number")
    public EditNumber clickEditNumber(String number){
        getButtonEditByText(number).click();
        waitUntilAlertDisappear();
        return new EditNumber();
    }

    @Step("Delete number")
    public BlockedNumbersPopup deleteNumber(String num){
        getButtonDeleteByText(num).click();
        waitUntilAlertDisappear();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Close popup")
    public BlockListSection clickClose(){
        getButtonClose().click();
        waitUntilAlertDisappear();
        return new BlockListSection();
    }

    @Step("Bulk delete the number")
    public BlockedNumbersPopup bulkDeleteNumber(String num){
        getCheckboxSelectNumber(num).click();
        getButtonDelete().click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        return this;
    }

    @Step("Verify if number Not exists")
    public BlockedNumbersPopup verifyNumberNotExist(String num){
        getFieldNumberByText(num).shouldNot(exist);
        return this;
    }

    @Step("Verify if number exists")
    public BlockedNumbersPopup verifyNumberExists(String num){
        getFieldNumberByText(num).should(exist);
        return this;
    }

    @Step("Add number")
    public BlockedNumbersPopup addNumber(String num){
        clickAdd()
                .enterNumber(num)
                .saveNumber();
        return this;
    }

}
