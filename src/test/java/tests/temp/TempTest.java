package tests.temp;

import com.codeborne.selenide.Selenide;
import flow.BaseTestMethods;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.testng.annotations.Test;
import tests.userPageTests.userPageTestData.User;

import java.awt.*;
import java.awt.event.InputEvent;

import static com.codeborne.selenide.Condition.exist;
import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.QUEUES;

public class TempTest extends BaseTestMethods {

    @Test
    public void sss() {

            String query = String.format("SELECT * FROM webadmin_20170426.sms_authorized_number where sender_number=%s","0741234567");
        System.out.println(getEntityIdFromDB(query));

    }

}
