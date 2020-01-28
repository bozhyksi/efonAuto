package core.configuration.preparations;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

import java.time.LocalDateTime;
import java.util.HashMap;

import static com.codeborne.selenide.Configuration.baseUrl;

import static core.configuration.preparations.PropertyReader.copyPropertiesFile;
import static core.configuration.preparations.PropertyReader.setPropertyToFile;

public class ReportPreparations {

    public static void setAllureEnvProperties(){
        String sourceFilePath = "src/test/resources/allure/environment.properties";
        String destFilePath = "target/allure-results/environment.properties";

        HashMap<String, String> properties = new HashMap<>();
        properties.put("Browser",Configuration.browser);
        properties.put("Test environment: ", baseUrl);
        properties.put("Test execution date: ", LocalDateTime.now().toString());
        properties.put("WebDriver: ", WebDriverRunner.getWebDriver().toString());

        setPropertyToFile(sourceFilePath,properties);
        copyPropertiesFile(sourceFilePath,destFilePath);
    }


}
