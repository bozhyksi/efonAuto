package pages.provisioningPage.provisioningPopups;

import pages.huntGroupPage.huntGroupPopup.AddFurtherTimePopup;
import pages.provisioningPage.ProvisioningEndDevicesPage;
import pages.queuesPage.queuePagePopups.QueueForAgentsPopup;
import tests.userPageTests.userPageTestData.User;

import static com.codeborne.selenide.Condition.exist;
import static core.workers.javaScriptExecutor.JavaScriptExecutor.executeDragDropScript;

public class AdditionalEndDevicesPopup extends ProvisioningEndDevicesPage {

    private QueueForAgentsPopup queueForAgentsPopup = new QueueForAgentsPopup();

    public AdditionalEndDevicesPopup addAdditionalEndDevice(User parentUser, User additionalUserEndDevice){
        getButtonPlusByText(parentUser.getFirstName()).click();
        waitUntilAlertDisappear();
        executeDragDropScript(queueForAgentsPopup.getFieldNotSelectedAgentByName(additionalUserEndDevice.getEndDevices()),
                                queueForAgentsPopup.getSectionSelected());
        queueForAgentsPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        return this;
    }

    public AdditionalEndDevicesPopup validateAdditionalEndDevice(User parentUser,User additionalUserEndDevice){
        getButtonPlusByText(parentUser.getFirstName()).click();
        waitUntilAlertDisappear();
        queueForAgentsPopup.getFieldSelectedAgentByName(additionalUserEndDevice.getEndDevices()).should(exist);
        waitUntilAlertDisappear();
        queueForAgentsPopup.getButtonCancel().click();
        return this;
    }

}
