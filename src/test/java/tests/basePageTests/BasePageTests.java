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

@Listeners(CustomListeners.class)

public class BasePageTests extends BaseTestMethods {

    @Description("Verify if user can open each main menu Tab")
    @Test(retryAnalyzer = RetryAnalyzer.class, groups = {"regression", "smoke", "BasePageTests"})
    public void VerifyIfUserCanOpenEachMainMenuTab(){

        step("Log in the system");
        login();

        step("Check if user can open \"User\" tab");
        basePage.getTabUser().click();
        Assert.assertEquals(userPage.getPageTitle().text(), MainMenu.user.getTabName());

        step("Check if user can open \"Numbers\" tab");
        basePage.getTabNumbers().click();
        Assert.assertEquals(numbersPage.getPageTitle().text(), MainMenu.numbers.getTabName());

        step("Check if user can open \"Subscriptions\" tab");
        basePage.getTabSubscriptions().click();
        Assert.assertEquals(subscriptionsPage.getPageTitle().text(), MainMenu.subscriptions.getTabName());

        step("Check if user can open \"Last Calls\" tab");
        basePage.getTabLastCalls().click();
        Assert.assertEquals(lastCallsPage.getPageTitle().text(), MainMenu.lastCalls.getTabName());

        step("Check if user can open \"Fax\" tab");
        basePage.getTabFax().click();
        Assert.assertEquals(faxPage.getPageTitle().text(), MainMenu.fax.getTabName());

        step("Check if user can open \"IVRs\" tab");
        basePage.getTabIVRs().click();
        Assert.assertEquals(ivrPage.getPageTitle().text(), MainMenu.IVRs.getTabName());

        step("Check if user can open \"Abbreviated Dialling\" tab");
        basePage.getTabAbbreviatedDialling().click();
        Assert.assertEquals(abbrevDialBasePage.getPageTitle().text(), MainMenu.abbreviatedDialling.getTabName());


        step("Check if user can open \"Groups for call pick-up\" tab");
        basePage.getTabCallPickUps().click();
        Assert.assertEquals(callPickUpPage.getPageTitle().text(), MainMenu.callPickups.getTabName());

        step("Check if user can open \"File management\" tab");
        basePage.getTabFileManagement().click();
        Assert.assertEquals(fileManagementPage.getPageTitle().text(), MainMenu.fileManagement.getTabName());

        step("Check if user can open \"Forwarding\" tab");
        basePage.getTabCallForwarding().click();
        Assert.assertEquals(callForwardingPage.getPageTitle().text(), MainMenu.callForwarding.getTabName());


        step("Check if user can open \"Hunt Groups\" tab");
        basePage.getTabHuntGroups().click();
        Assert.assertEquals(huntGroupPage.getPageTitle().text(), MainMenu.huntGroups.getTabName());

        step("Check if user can open \"Conference calls\" tab");
        basePage.getTabConferenceCalls().click();
        Assert.assertEquals(conferenceCallsPage.getPageTitle().text(), MainMenu.conferenceCalls.getTabName());

        step("Check if user can open \"Queues\" tab");
        basePage.getTabQueues().click();
        Assert.assertEquals(queuesBasePage.getPageTitle().text(), MainMenu.queues.getTabName());

        step("Check if user can open \"End devices\" tab");
        basePage.getTabEndDevices().click();
        Assert.assertEquals(endDevicesPage.getPageTitle().text(), MainMenu.endDevices.getTabName());

        step("Check if user can open \"Recorded calls\" tab");
        basePage.getTabRecordedCalls().click();
        Assert.assertEquals(recordedCallsPage.getPageTitle().text(), MainMenu.recordedCalls.getTabName());

        step("Check if user can open \"Phonebook\" tab");
        basePage.getTabPhonebook().click();
        Assert.assertEquals(phonebookPage.getPageTitle().text(), MainMenu.phonebook.getTabName());

        step("Check if user can open \"Contact Data\" tab");
        basePage.getTabContactData().click();
        Assert.assertEquals(contactDataPage.getPageTitle().text(), MainMenu.contactData.getTabName());
    }
}
