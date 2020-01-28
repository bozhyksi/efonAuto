package core.configuration.customListeners;

import com.codeborne.selenide.WebDriverRunner;
import core.configuration.driver.DriverConfigurator;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.Configuration;

import java.io.File;
import java.io.IOException;

import static core.configuration.preparations.PropertyReader.copyPropertiesFile;
import static core.configuration.preparations.ReportPreparations.setAllureEnvProperties;
import static core.configuration.screen.ScreenShotMaker.*;

public class CustomListeners implements ITestListener {
    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test "+iTestResult.getName()+"successfully PASSED!");
    }

    public void onTestFailure(ITestResult iTestResult) {
        String fileName = iTestResult.getName()+iTestResult.getEndMillis();
        attachment(fileName);
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
