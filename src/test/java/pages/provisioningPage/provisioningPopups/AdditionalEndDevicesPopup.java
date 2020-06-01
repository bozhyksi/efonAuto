package pages.provisioningPage.provisioningPopups;

import io.qameta.allure.Step;
import pages.basePage.basePopup.BasePopup;
import pages.provisioningPage.ProvisioningEndDevicesPage;
import tests.userPageTests.userPageTestData.User;
import static core.configuration.preparations.eFonApp.queueForAgentsPopup;

import static com.codeborne.selenide.Condition.exist;
import static core.workers.javaScriptExecutor.JavaScriptExecutor.executeDragDropScript;

public class AdditionalEndDevicesPopup extends BasePopup {

    @Step("Create additional end device")
    public AdditionalEndDevicesPopup dragDrop(String additionalUserEndDevice){
        executeDragDropScript(queueForAgentsPopup.getFieldNotSelectedAgentByName(additionalUserEndDevice),
                                queueForAgentsPopup.getSectionSelected());
        return this;
    }

    @Step("Save changes")
    public ProvisioningEndDevicesPage saveChanges(){
        getButtonSave().click();
        waitUntilAlertDisappear();
        return new ProvisioningEndDevicesPage();
    }

    @Step("Validate additional end device")
    public ProvisioningEndDevicesPage validateAdditionalEndDevice(String additionalUserEndDevice){
        queueForAgentsPopup.getFieldSelectedAgentByName(additionalUserEndDevice).should(exist);
        waitUntilAlertDisappear();
        queueForAgentsPopup.getButtonCancel().click();
        return new ProvisioningEndDevicesPage();
    }

}
