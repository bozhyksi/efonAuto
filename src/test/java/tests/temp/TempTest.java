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
import static pages.basePage.BasePage.MenuTabsBasePage.USER;
import static pages.userPage.userPagePopup.configureUser.ConfigureUserBasePopup.Tabs.ANNOUNCEMENTS;

public class TempTest extends BaseTestMethods {

    @Test
    public void sss() {
        login()
                .goToMenuTab(USER);
        userPage
                .clickEditUser("tthrjniylnrifzg")
                .goToTab(ANNOUNCEMENTS);

    }

}
