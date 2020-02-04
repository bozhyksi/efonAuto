package core.configuration.driver;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

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
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
        options.addArguments("--start-maximized");
        options.addArguments("--test-type");
        options.addArguments("--disable-notifications");
        options.addArguments("--auto-open-devtools-for-tabs");
        options.setExperimentalOption("prefs", chromePrefs);
        Configuration.timeout = maxTimeWait;
        return options;
    }
}
