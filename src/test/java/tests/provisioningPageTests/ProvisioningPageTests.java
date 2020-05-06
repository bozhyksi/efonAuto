package tests.provisioningPageTests;

import com.codeborne.selenide.Selenide;
import core.customListeners.CustomListeners;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)

public class ProvisioningPageTests extends BaseTestMethods {

    @Description("decssss")
    @Test(/*retryAnalyzer = RetryAnalyzer.class, */groups = {"regression", "smoke", "provisioningPageTests"})
    public void ttt(){

        login();

        Selenide.sleep(5000);

    }

}
