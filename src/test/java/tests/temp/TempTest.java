package tests.temp;

import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import org.testng.annotations.Test;

public class TempTest extends BaseTestMethods {
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void Test1(){
        System.out.println(System.getProperty("user.dir"));
    }
}
