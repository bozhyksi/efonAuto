package tests.loginPageTest;

import core.configuration.customListeners.CustomListeners;
import flow.BaseTestMethods;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)

public class LoginPageTests extends BaseTestMethods {
    @Test
    public static void loginTest(){
        Assert.assertTrue(false);
        System.out.println("test");
    }
}
