package core.configuration.screen;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.ScreenShooter;

import static com.codeborne.selenide.Selenide.screenshot;

public class ScreenShotMaker {

    public static void makeScreenShot(String fileName){
        Configuration.reportsFolder = "test-result/reports";
        screenshot(fileName);
    }
}
