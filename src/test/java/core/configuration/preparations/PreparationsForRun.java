package core.configuration.preparations;

import com.codeborne.selenide.WebDriverRunner;
import core.configuration.driver.DriverConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static core.configuration.preparations.PropertyReader.readProperty;

public class PreparationsForRun extends DriverConfigurator {

    public static String getLogin(){
        return readProperty("login", "core.properties");
    }

    public static String getPassword(){
        return readProperty("password", "core.properties");
    }

    public static String getBaseURL(){
        baseUrl = readProperty("baseUrl", "core.properties");
        return baseUrl;
    }

    public static String getLowLevelUserLogin(){
        return readProperty("lowLevelUserLogin", "core.properties");
    }

    public static String getLowLevelUserPassword(){
        return readProperty("lowLevelUserPassword", "core.properties");
    }

    @BeforeMethod(alwaysRun = true)
    public void startBrowser(){
        WebDriverRunner.setWebDriver(getDriver());
        open(getBaseURL());
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        WebDriverRunner.getWebDriver().quit();
        driver = null;
    }
}
