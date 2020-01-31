package core.retryAnalyzer;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int counter = 1;
    private int maxAttempts = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (counter < maxAttempts){
            counter++;
            return true;
        }
        return false;
    }
}
