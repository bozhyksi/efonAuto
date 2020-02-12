package tests.temp;

import flow.BaseTestMethods;
import org.testng.annotations.Test;
import tests.userPageTests.userPageTestData.User;

public class TempTest extends BaseTestMethods {
    @Test(groups = {"ttt"})
    public void Test1(){
        login();
        createUser(new User());
    }
}
