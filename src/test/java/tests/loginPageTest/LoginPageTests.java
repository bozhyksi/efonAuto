package tests.loginPageTest;

import flow.BaseTestMethods;
import org.testng.annotations.Test;

public class LoginPageTests extends BaseTestMethods {
    @Test
    public static void loginTest(){
        System.out.println("test");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
