package core.configuration.screen;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Attachment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.codeborne.selenide.Selenide.screenshot;

public class ScreenShotMaker {

    public static void attachment(String fileName){
        makeScreenShot(fileName);
        try {
            getBytes(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void makeScreenShot(String fileName){
       Configuration.reportsFolder = "target/test-result/reports";
        screenshot(fileName);
    }

    @Attachment(value = "attachment")
    private static byte[] getBytes(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get(Configuration.reportsFolder, resourceName+".png"));
    }


}
