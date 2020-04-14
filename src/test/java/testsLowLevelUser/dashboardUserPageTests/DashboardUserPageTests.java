package testsLowLevelUser.dashboardUserPageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;
import lowLevelUserPages.dashboardPageLowLevelUser.DashboardUserPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.PageTitles.*;
import static lowLevelUserPages.dashboardPageLowLevelUser.DashboardUserPage.WidgetButtons.*;

@Listeners(CustomListeners.class)

public class DashboardUserPageTests extends BaseTestMethods {

    @Description("Check widget buttons on Dashboard")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "dashboardUserPageTests"})
    public void CheckWidgetButtonsOnDashboard(){
        step("Login as low-level user");
        loginAsLowLevelUser();

        step("Check Last calls widget");
        dashboardUserPage.checkWidgetButton(SHOW_ALL_CALLS, CALLS);

        step("Check Fax Arrived widget");
        dashboardUserPage.checkWidgetButton(SHOW_ALL_FAXES, FAXES);

        step("Check Call Forwarding widget");
        dashboardUserPage.checkWidgetButton(ALTER_CALL_FORWARDING, FORWARDING);

        step("Check Voicemail widget");
        dashboardUserPage.checkWidgetButton(SHOW_ALL_VOICEMAIL_MESSAGES, VOICEMAIL);

        step("Check Queues widget");
        dashboardUserPage.checkWidgetButton(SHOW_QUEUE_STATUS, QUEUES);

        step("Check Send text message widget");
        dashboardUserPage.checkWidgetButton(SEND_TEXT_MESSAGE, SEND_SMS);
        dashboardUserPage.checkWidgetButton(ADDRESS_BOOK, SEND_SMS);

        step("Check End devices widget");
        dashboardUserPage.checkWidgetButton(MY_END_DEVICES, END_DEVICES);
    }

}
