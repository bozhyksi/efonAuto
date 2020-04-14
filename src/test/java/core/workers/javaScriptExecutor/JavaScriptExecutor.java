package core.workers.javaScriptExecutor;

import com.codeborne.selenide.WebDriverRunner;
import core.configuration.driver.DriverConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static core.workers.javaScriptExecutor.ScriptsJS.getDragDropScript;

public class JavaScriptExecutor{
    private static JavascriptExecutor executor;

    public static void executeDragDropScript(WebElement element, WebElement dragToArea){
        executor = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        executor.executeScript(getDragDropScript(),element, dragToArea);
    }

}
