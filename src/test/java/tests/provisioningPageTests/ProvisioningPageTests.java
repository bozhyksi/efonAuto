package tests.provisioningPageTests;

import com.codeborne.selenide.Selenide;
import core.customListeners.CustomListeners;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static core.workers.menuNavigator.MenuNavigator.MainMenuTabs.PROVISIONING;
import static core.workers.menuNavigator.MenuNavigator.ProvisioningSubTabs.PROVISIONING_MANAGER;

@Listeners(CustomListeners.class)

public class ProvisioningPageTests extends BaseTestMethods {

    @Description("decssss")
    @Test(/*retryAnalyzer = RetryAnalyzer.class, */groups = {"regression", "smoke", "provisioningPageTests"})
    public void ttt(){
        login();
        basePage.gotoMenuTab(PROVISIONING).gotoSubMenuTab(PROVISIONING_MANAGER);
        Selenide.sleep(5000);

    }

}
