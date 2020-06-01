package tests.basePageTests;

import core.customListeners.CustomListeners;
import core.retryAnalyzer.RetryAnalyzer;
import flow.BaseTestMethods;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import tests.basePageTests.basePageTestData.BasePageTestsData.MainMenu;

import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.*;

@Listeners(CustomListeners.class)

public class BasePageTests extends BaseTestMethods {

    @Description("Verify if user can open each main menu Tab")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "BasePageTests"})
    public void VerifyIfUserCanOpenEachMainMenuTab() {

        step("Log in the system");
        login();

        step("Check if user can open \"User\" tab");
        basePage.goToMenuTab(USER);
        Assert.assertEquals(userPage.getPageTitle().text(), MainMenu.user.getTabName());

        step("Check if user can open \"Numbers\" tab");
        basePage.goToMenuTab(NUMBERS);
        Assert.assertEquals(numbersPage.getPageTitle().text(), MainMenu.numbers.getTabName());

        step("Check if user can open \"Provisioning\" tab");
        basePage.goToMenuTab(PROVISIONING);
        Assert.assertEquals(provisioningBasePage.getPageTitle().text(), MainMenu.provisioning.getTabName());

        step("Check if user can open \"Subscriptions\" tab");
        basePage.goToMenuTab(SUBSCRIPTIONS);
        Assert.assertEquals(subscriptionsPage.getPageTitle().text(), MainMenu.subscriptions.getTabName());

        step("Check if user can open \"Last Calls\" tab");
        basePage.goToMenuTab(LAST_CALLS);
        Assert.assertEquals(lastCallsPage.getPageTitle().text(), MainMenu.lastCalls.getTabName());

        step("Check if user can open \"Fax\" tab");
        basePage.goToMenuTab(FAX);
        Assert.assertEquals(faxPage.getPageTitle().text(), MainMenu.fax.getTabName());

        step("Check if user can open \"IVRs\" tab");
        basePage.goToMenuTab(IVRs);
        Assert.assertEquals(ivrPage.getPageTitle().text(), MainMenu.IVRs.getTabName());

        step("Check if user can open \"Abbreviated Dialling\" tab");
        basePage.goToMenuTab(ABBREVIATED_DIALING);
        Assert.assertEquals(abbrevDialBasePage.getPageTitle().text(), MainMenu.abbreviatedDialling.getTabName());

        step("Check if user can open \"Groups for call pick-up\" tab");
        basePage.goToMenuTab(CALL_PICKUPs);
        Assert.assertEquals(callPickUpPage.getPageTitle().text(), MainMenu.callPickups.getTabName());

        step("Check if user can open \"File management\" tab");
        basePage.goToMenuTab(FILE_MANAGEMENT);
        Assert.assertEquals(fileManagementBasePage.getPageTitle().text(), MainMenu.fileManagement.getTabName());

        step("Check if user can open \"Forwarding\" tab");
        basePage.goToMenuTab(CALL_FORWARDING);
        Assert.assertEquals(callForwardingPage.getPageTitle().text(), MainMenu.callForwarding.getTabName());


        step("Check if user can open \"Hunt Groups\" tab");
        basePage.goToMenuTab(HUNT_GROUPS);
        Assert.assertEquals(huntGroupPage.getPageTitle().text(), MainMenu.huntGroups.getTabName());

        step("Check if user can open \"ConferenceCallTestData calls\" tab");
        basePage.goToMenuTab(CONFERENCE_CALLS);
        Assert.assertEquals(conferenceCallsPage.getPageTitle().text(), MainMenu.conferenceCalls.getTabName());

        step("Check if user can open \"Queues\" tab");
        basePage.goToMenuTab(QUEUES);
        Assert.assertEquals(queuesBasePage.getPageTitle().text(), MainMenu.queues.getTabName());

        step("Check if user can open \"End devices\" tab");
        basePage.goToMenuTab(END_DEVICES);
        Assert.assertEquals(endDevicesPage.getPageTitle().text(), MainMenu.endDevices.getTabName());

        step("Check if user can open \"Recorded calls\" tab");
        basePage.goToMenuTab(RECORDED_CALLs);
        Assert.assertEquals(recordedCallsPage.getPageTitle().text(), MainMenu.recordedCalls.getTabName());

        step("Check if user can open \"Phonebook\" tab");
        basePage.goToMenuTab(PHONEBOOK);
        Assert.assertEquals(phonebookPage.getPageTitle().text(), MainMenu.phonebook.getTabName());

        step("Check if user can open \"Contact Data\" tab");
        basePage.goToMenuTab(CONTACT_DATA);
        Assert.assertEquals(contactDataPage.getPageTitle().text(), MainMenu.contactData.getTabName());
    }


}
