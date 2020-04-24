package lowLevelUserPages.endDevicesLowLevelUserPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.endDevicesPage.EndDevicesPage;
import pages.userPage.userPagePopup.configureUser.EndDeviceTabConfigUserPopup;
import tests.userPageTests.userPageTestData.EndDevice;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.selected;

public class EndDevicesUserPage extends BasePageLowLevelUser {
    //<editor-fold desc="locators">
    private String buttonEditEndDeviceByNameXpath = "//td[contains(text(),\"%s\")]//..//i[contains(@class,\"fa-cog\")]//..";
    private String checkboxSuppressedYesXpath = "//span[text()=\"Yes\"]//..//input[@formcontrolname=\"suppressed\"]";
    private String checkboxSuppressedNoXpath = "//span[text()=\"No\"]//..//input[@formcontrolname=\"suppressed\"]";
    private String dropdownPhoneLanguageXpath = "//select[@formcontrolname=\"language\"]";
    private String dropdownOutgoingNumberXpath = "//select[@formcontrolname=\"outgoingNumber\"]";
    private String buttonSaveXpath = "//button[text()=\"Save\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getDropdownOutgoingNumber() {
        return field(dropdownOutgoingNumberXpath);
    }

    public SelenideElement getDropdownPhoneLanguage() {
        return field(dropdownPhoneLanguageXpath);
    }

    public SelenideElement getButtonEditEndDeviceByName(String name) {
        return field(String.format(buttonEditEndDeviceByNameXpath, name));
    }

    public SelenideElement getCheckboxSuppressedYes() {
        return field(checkboxSuppressedYesXpath);
    }

    public SelenideElement getCheckboxSuppressedNo() {
        return field(checkboxSuppressedNoXpath);
    }


    //</editor-fold>

    public void openEditEndDevicePopup(String name){
        getButtonEditEndDeviceByName(name).click();
        waitUntilAlertDisappear();
    }

    public void configureUserEndDevice(EndDeviceTabConfigUserPopup endDevicesPage, EndDevice endDevice){
        getDropdownPhoneLanguage().selectOptionByValue(endDevice.getEndDevPhoneLanguage());
        getDropdownOutgoingNumber().selectOption(getRandomNumber(1,20));
        endDevice.setEndDevOutgoingNumber(getDropdownOutgoingNumber().getSelectedValue());
        //endDevicesPage.getInputLocationEndDev().setValue(endDevice.getEndDevLocation());
        if (getCheckboxSuppressedYes().isSelected()) {
            getCheckboxSuppressedNo().click();
            endDevice.setEndDevSuppressedNO(true);
        }
        else{
            getCheckboxSuppressedYes().click();
            endDevice.setEndDevSuppressedYES(true);
        }
        getButtonSave().click();
        waitUntilAlertDisappear();
    }

    public void validateEndDeviceData(EndDeviceTabConfigUserPopup endDevicesPage, EndDevice endDevice){
        getDropdownPhoneLanguage().getSelectedValue().contains(endDevice.getEndDevPhoneLanguage());
        getDropdownOutgoingNumber().getSelectedValue().contains(endDevice.getEndDevOutgoingNumber());
        if (endDevice.getEndDevSuppressedYES()) getCheckboxSuppressedYes().shouldBe(selected);
        if (endDevice.getEndDevSuppressedNO()) getCheckboxSuppressedNo().shouldBe(selected);
        refreshPage();
    }

    public ArrayList<String> getOutgoingDropdownItems(){
        waitUntilAlertDisappear();
        ArrayList<String> dropdownItemsList = new ArrayList<>();
        Select obj = new Select(getDropdownOutgoingNumber());
        List<WebElement> itemsList = obj.getOptions();
        for (WebElement elem: itemsList) {
            if (!elem.getText().contains("Not Selected"))dropdownItemsList.add(elem.getText().replaceAll("\\s",""));
        }
        return dropdownItemsList;
    }

    public void verifyIfAllCustomerNumbersAreAvailableAsOutgoing(ArrayList<String> customerNumbersList,
                                                                 ArrayList<String> outgoingNumbersList){
        Assert.assertEquals(customerNumbersList.size(),outgoingNumbersList.size(),"Size of numbers lists is not equal " +
                "customer numbers: "+customerNumbersList.size()+"; outgoing numbers: "+outgoingNumbersList.size());

        for (String customerNumber: customerNumbersList) {
            Assert.assertTrue(outgoingNumbersList.contains(customerNumber), customerNumber+" - does not exist in outgoing numbers list");
        }

    }


}
