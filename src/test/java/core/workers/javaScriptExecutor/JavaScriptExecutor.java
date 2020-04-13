package core.workers.javaScriptExecutor;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static core.workers.javaScriptExecutor.ScriptsJS.getDragDropScript;

public class JavaScriptExecutor {
    private static final JavascriptExecutor executor = (JavascriptExecutor) WebDriverRunner.getWebDriver();

    public static void executeDragDropScript(WebElement element, WebElement dragToArea){
        executor.executeScript(getDragDropScript(),element, dragToArea);
    }

}
