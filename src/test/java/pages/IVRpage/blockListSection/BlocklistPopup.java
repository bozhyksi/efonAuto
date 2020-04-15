package pages.IVRpage.blockListSection;

import com.codeborne.selenide.SelenideElement;

public class BlocklistPopup extends BlockListSection {
    //<editor-fold desc="locators">
    private String buttonAddXpath = "//block-numbers-list//button[text()=\"Add\"]";
    private String buttonDeleteXpath = "//block-numbers-list//button[text()=\"Delete\"]";
    private String inputAddNewPhoneXpath = "(//blocked-number-new//input)[1]";
    private String inputAddNewCommentXpath = "(//blocked-number-new//input)[2]";
    private String checkboxEntireCustomerXpath = "//blocked-number-new//input[@type=\"checkbox\"]";
    private String buttonSaveXpath = "//blocked-number-new//button[text()=\"Save\"]";
    private String buttonCloseXpath = "//button[text()=\"Close\"]";
    private String fieldByNameXpath = "//block-numbers-list//td[contains(text(),\"%s\")]";
    private String buttonEditByNameXpath = "//block-numbers-list//td[contains(text(),\"%s\")]/..//i[contains(@class,\"fa-cog\")]/..";
    private String buttonDeleteByNameXpath = "//block-numbers-list//td[contains(text(),\"%s\")]/..//i[contains(@class,\"fa-trash\")]/..";
    //</editor-fold>


    //<editor-fold desc="get\set">
    public SelenideElement getButtonAdd() {
        return field(buttonAddXpath);
    }

    public SelenideElement getButtonDelete() {
        return field(buttonDeleteXpath);
    }

    public SelenideElement getInputAddNewPhone() {
        return field(inputAddNewPhoneXpath);
    }

    public SelenideElement getInputAddNewComment() {
        return field(inputAddNewCommentXpath);
    }

    public SelenideElement getCheckboxEntireCustomer() {
        return field(checkboxEntireCustomerXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getButtonClose() {
        return field(buttonCloseXpath);
    }

    public SelenideElement getFieldNameByText(String text) {
        return field(String.format(fieldByNameXpath, text));
    }

    public SelenideElement getFieldCommentByText(String text) {
        return field(String.format(fieldByNameXpath, text));
    }

    public SelenideElement getFieldEntireCustomerByText(String text) {
        return field(String.format(fieldByNameXpath, text));
    }

    public SelenideElement getButtonEditByName() {
        return field(buttonEditByNameXpath);
    }

    public SelenideElement getButtonDeleteByName() {
        return field(buttonDeleteByNameXpath);
    }
    //</editor-fold>
}
