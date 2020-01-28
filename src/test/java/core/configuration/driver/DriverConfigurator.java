package core.configuration.driver;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverConfigurator {

    public WebDriver driver = null;
    private final int maxTimeWait = 15000;

    private WebDriver createChromeDriver() {
        ChromeDriverManager.chromedriver().setup();
        return driver = new ChromeDriver(chromeOptions());
    }

    public WebDriver getDriver(){
        if (driver == null){
            return createChromeDriver();
        }
        return driver;
    }

    private ChromeOptions chromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        Configuration.timeout = maxTimeWait;
        return options;
    }
}
