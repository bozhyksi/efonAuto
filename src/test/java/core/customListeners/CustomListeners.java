package core.customListeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static core.configuration.preparations.ReportPreparations.setAllureEnvProperties;
import static core.configuration.screen.ScreenShotMaker.*;

public class CustomListeners implements ITestListener {

    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("\n\nTest '"+iTestResult.getName()+"' successfully PASSED!\n\n");
    }

    public void onTestFailure(ITestResult iTestResult) {
        String fileName = iTestResult.getName()+iTestResult.getEndMillis();
        attachment(fileName);
        System.out.println("\n\nTest '"+iTestResult.getName()+"' FAILED!\n\n");
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {
        setAllureEnvProperties();
    }
}
