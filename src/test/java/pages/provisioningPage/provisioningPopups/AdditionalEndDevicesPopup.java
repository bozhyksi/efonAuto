package pages.provisioningPage.provisioningPopups;

import com.codeborne.selenide.SelenideElement;
import flow.PublicEnums;
import io.qameta.allure.Step;
import pages.basePage.basePopup.BasePopup;
import pages.provisioningPage.ProvisioningEndDevicesPage;

import static com.codeborne.selenide.Condition.exist;
import static core.workers.javaScriptExecutor.JavaScriptExecutor.executeDragDropScript;

public class AdditionalEndDevicesPopup extends BasePopup {

    //<editor-fold desc="locators">
    private final String notSelectedAccountByText = "//h2[text()=\"Not Selected\"]/..//div[text()[contains(.,\"%s\")]]";
    private final String selectedAccountByText = "//h2[text()=\"Selected\"]/..//div[text()[contains(.,\"%s\")]]";
    private final String sectionSelected = "//h2[text()=\"Selected\"]/..//section";
    private final String sectionNotSelected = "//h2[text()=\"Not Selected\"]/..//section";
    //</editor-fold>

    //<editor-fold desc="get\set">


    public SelenideElement getSectionSelected() {
        return field(sectionSelected);
    }

    public SelenideElement getSectionNotSelected() {
        return field(sectionNotSelected);
    }

    public SelenideElement getSelectedAccountByText(String text) {
        return field(String.format(selectedAccountByText,text));
    }

    public SelenideElement getNotSelectedAccountByText(String text) {
        return field(String.format(notSelectedAccountByText,text));
    }
    //</editor-fold>

    @Step("Create additional end device")
    public AdditionalEndDevicesPopup dragDrop(PublicEnums.DragDropSection section , String account){
        switch (section){
            case SECTION_SELECTED:
                executeDragDropScript(getNotSelectedAccountByText(account),getSectionSelected());
                break;
            case SECTION_NOT_SELECTED:
                executeDragDropScript(getSelectedAccountByText(account),getSectionNotSelected());
                break;
        }
        return this;
    }

    @Step("Save changes")
    public ProvisioningEndDevicesPage saveChanges(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return new ProvisioningEndDevicesPage();
    }

    @Step("Validate additional end device")
    public AdditionalEndDevicesPopup validateAdditionalEndDevice(String account){
        getSelectedAccountByText(account).should(exist);
        waitUntilAlertDisappear();
        return this;
    }

}
