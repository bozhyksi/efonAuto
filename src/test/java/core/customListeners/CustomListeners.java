package core.customListeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static core.configuration.preparations.ReportPreparations.setAllureEnvProperties;
import static core.configuration.screen.ScreenShotMaker.*;

public class CustomListeners implements ITestListener {
    private final String ANSI_RED    = "\u001B[31m";
    private final String ANSI_BOLD   = "\u001B[1m";
    private final String ANSI_GREEN   = "\u001b[32m";
    private final String ANSI_RESET  = "\u001B[0m";

    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println(ANSI_GREEN+ANSI_BOLD+"\n\nTest '"+iTestResult.getName()+"' successfully PASSED!\n\n"+ANSI_RESET);
    }

    public void onTestFailure(ITestResult iTestResult) {
        String fileName = iTestResult.getName()+iTestResult.getEndMillis();
        attachment(fileName);
        System.out.println(ANSI_RED+ANSI_BOLD+"\n\nTest '"+iTestResult.getName()+"' FAILED!\n\n"+ANSI_RESET);
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
