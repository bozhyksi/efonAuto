package lowLevelUserPages.sendSmsPageLowLevelUser;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import testsLowLevelUser.sendSmsUserPageTests.sendSmsTestData.AddressBookTestData;

public class AddressBookUserPage extends SendSmsBaseUserPage {
    //<editor-fold desc="locators">
    private String buttonAddXpath = "//a[text()=\"Add\"]";
    private String fieldMobileNumberByTextXpath = "//tbody//td[contains(text(),\"%s\")]";
    private String buttonEditByTextXpath = "//tbody//td[contains(text(),\"%s\")]//parent::tr//i[contains(@class,\"fa-cog\")]";
    private String buttonDeleteByTextXpath = "//tbody//td[contains(text(),\"%s\")]//parent::tr//i[contains(@class,\"fa-trash\")]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getButtonAdd() {
        return field(buttonAddXpath);
    }

    public SelenideElement getButtonEditByText(String text) {
        return field(String.format(buttonEditByTextXpath, text));
    }

    public SelenideElement getButtonDeleteByText(String text) {
        return field(String.format(buttonDeleteByTextXpath, text));
    }

    public SelenideElement getFieldMobileNumberByText(String text){
        return field(String.format(fieldMobileNumberByTextXpath,text));
    }

    public SelenideElement getFieldFirstNameByText(String text){
        return field(String.format(fieldMobileNumberByTextXpath,text));
    }

    public SelenideElement getFieldLastNameByText(String text){
        return field(String.format(fieldMobileNumberByTextXpath,text));
    }

    public SelenideElement getFieldCompanyByText(String text){
        return field(String.format(fieldMobileNumberByTextXpath,text));
    }
    //</editor-fold>

    public void validateIfEntryWasCreated(AddressBookTestData addressBook){
        getFieldMobileNumberByText(addressBook.getMobileNumber()).shouldBe(Condition.visible,Condition.exist);
        getFieldFirstNameByText(addressBook.getFirstName()).shouldBe(Condition.visible,Condition.exist);
        getFieldLastNameByText(addressBook.getLastName()).shouldBe(Condition.visible,Condition.exist);
        getFieldCompanyByText(addressBook.getCompany()).shouldBe(Condition.visible,Condition.exist);
    }
}
