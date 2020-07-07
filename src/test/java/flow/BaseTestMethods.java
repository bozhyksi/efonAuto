package flow;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import core.configuration.preparations.eFonApp;
import io.qameta.allure.Step;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;
import pages.basePage.BasePage;
import tests.IVRpageTests.IVRtestData.IVRtestData;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;
import tests.callPickUpPageTests.CallPickUpTestData.CallPickUp;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.phonebookPageTests.phonebookPageTestData.Phonebook;
import tests.queuesPageTest.queueTestData.Queue;
import tests.userPageTests.userPageTestData.User;
import tests.сonferenceCallsPageTests.ConferenceCallTestData.ConferenceCallTestData;
import testsLowLevelUser.sendSmsUserPageTests.sendSmsTestData.AddressBookTestData;
import testsLowLevelUser.sendSmsUserPageTests.sendSmsTestData.AuthorizedNumberTestData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import static api.baseApiMethods.AbbreviatedNumbersApi.deleteAbbreviatedNumberApi;
import static api.baseApiMethods.CallPickUpsApi.deleteCallPickupApi;
import static api.baseApiMethods.ConferenceCallsApi.deleteConferenceCallApi;
import static api.baseApiMethods.FileManagementApi.deleteAnnouncementApi;
import static api.baseApiMethods.FileManagementApi.deleteMohApi;
import static api.baseApiMethods.HuntGroupApi.deleteHuntGroupApi;
import static api.baseApiMethods.IVRApi.deleteIvrApi;
import static api.baseApiMethods.QueueApi.deleteQueueApi;
import static api.baseApiMethods.SendSmsApi.deleteAddressBookEntryApi;
import static api.baseApiMethods.SendSmsApi.deleteAuthorizedNumberApi;
import static api.baseApiMethods.UserApi.deleteUsersApi;
import static com.codeborne.selenide.Condition.*;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.MenuTabsLowLevelUser.ANNOUNCEMENTS;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.MenuTabsLowLevelUser.VOICEMAIL;
import static pages.basePage.BasePage.MenuTabsBasePage.CALL_FORWARDING;

public class BaseTestMethods extends eFonApp {

    public String getEntityIdFromDB(String query){
        ResultSet resultSet = dataBaseWorker.execSqlQuery(query);
        while (true){
            try {
                if (!resultSet.next()) break;
                return resultSet.getString(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public String getPhoneNumberId(String phoneNumber){
        String query ="SELECT phonenumber_id FROM webadmin_20170426.phonenumber where number= \"%s\"";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(String.format(query,phoneNumber));
        while (true){
            try {
                if (!resultSet.next()) break;
                return resultSet.getString(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public String getRandomPhoneModelFromDB(){
        String query ="SELECT pm.phone_model " +
                "FROM webadmin_20170426.firmware f " +
                "join webadmin_20170426.phone_model pm on pm.phone_model_id = f.phone_model_fk " +
                "where f.function_keys_quantity > 15 " +
                "and pm.phone_model != \"Yealink T42S\" " +
                "group by pm.phone_model";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(query);
        ArrayList<String> numbers = new ArrayList<>();
        while (true){
            try {
                if (!resultSet.next()) break;
                numbers.add(resultSet.getString(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return numbers.get(new Random().nextInt(numbers.size()));
    }

    public String getRandomMAC(){
        return "00085D"+getRandomNumber(000000,999999);
    }

    public String getAutoProvisionedEndDeviceFromDB(){
        String query = "select name " +
                "from account " +
                "where is_automatically_provisioned = 1 " +
                "and  is_selected_for_provisioning = 0 " +
                "and owner_fk = 906144 " +
                "and customer_fk = 906144";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(query);
        ArrayList<String> autoProvisionedEndDevicesList = new ArrayList<>();
        while (true){
            try {
                if (!resultSet.next()) break;
                autoProvisionedEndDevicesList.add(resultSet.getString(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return autoProvisionedEndDevicesList.get(new Random().nextInt(autoProvisionedEndDevicesList.size()));
    }

    public String getSelectedForProvEndDeviceFromDB(){
        String query = "SELECT name " +
                "FROM webadmin_20170426.account " +
                "where owner_fk = 906144 " +
                "and customer_fk=906144 " +
                "and (is_automatically_provisioned = 0 and is_selected_for_provisioning = 1)";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(query);
        ArrayList<String> autoProvisionedEndDevicesList = new ArrayList<>();
        while (true){
            try {
                if (!resultSet.next()) break;
                autoProvisionedEndDevicesList.add(resultSet.getString(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return autoProvisionedEndDevicesList.get(new Random().nextInt(autoProvisionedEndDevicesList.size()));
    }

    public String getNotProvisionedEndDeviceFromDB(){
        String query = "SELECT name " +
                        "FROM webadmin_20170426.account " +
                        "where owner_fk = 906144 " +
                        "and customer_fk=906144 " +
                        "and (is_automatically_provisioned = 0 and is_selected_for_provisioning = 0)";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(query);
        ArrayList<String> autoProvisionedEndDevicesList = new ArrayList<>();
        while (true){
            try {
                if (!resultSet.next()) break;
                autoProvisionedEndDevicesList.add(resultSet.getString(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return autoProvisionedEndDevicesList.get(new Random().nextInt(autoProvisionedEndDevicesList.size()));
    }

    public String getRandomCustomerFreePhoneNumberFromDB(){
        String query =
                "select pn.number\n" +
                "from webadmin_20170426.phonenumber as pn \n" +
                "left join webadmin_20170426.feature f on pn.phonenumber_id = f.phonenumber_fk\n" +
                "where pn.owner_fk = 906144\n" +
                "and pn.manual_extensions = 0\n" +
                "and pn.write_extensions = 1\n" +
                "and f.feature_id is null";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(query);
        ArrayList<String> numbers = new ArrayList<>();
        while (true){
            try {
                if (!resultSet.next()) break;
                numbers.add(resultSet.getString(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return numbers.get(new Random().nextInt(numbers.size()));
    }

    public String getRandomCustomerFreeEndDeviceFromDB(){
        String query = "SELECT name FROM webadmin_20170426.account where owner_fk = 906144 and customer_fk = 906144";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(query);
        ArrayList<String> numbers = new ArrayList<>();
        while (true){
            try {
                if (!resultSet.next()) break;
                numbers.add(resultSet.getString(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return numbers.get(new Random().nextInt(numbers.size()));
    }

    public boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }

    public String getRandomIpAddress(){
        return "192.168." + getRandomNumber(0,254)+"."+getRandomNumber(0,254);
    }

    public String getDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date currentDate = new Date();
        String date = formatter.format(currentDate);
        return date;
    }

    public String getDate(String format){
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date currentDate = new Date();
        String date = formatter.format(currentDate);
        return date;
    }

    public String getDate(String format, String timeValue, int amount){
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        switch (timeValue){
            case "DAY":
                calendar.add(Calendar.DATE, amount);
                calendar.add(Calendar.HOUR,amount);
                break;
            case "MONTH":
                calendar.add(Calendar.MONTH, amount);
                calendar.add(Calendar.HOUR,amount);
                break;
            case "YEAR":
                calendar.add(Calendar.YEAR, amount);
                calendar.add(Calendar.HOUR,amount);
                break;
            default:
                break;
        }
        currentDate = calendar.getTime();
        String date = formatter.format(currentDate);
        return date;
    }

    public String getDate(String timeValue, int amount){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        switch (timeValue){
            case "DAY":
                calendar.add(Calendar.DATE, amount);
                calendar.add(Calendar.HOUR,amount);
                break;
            case "MONTH":
                calendar.add(Calendar.MONTH, amount);
                calendar.add(Calendar.HOUR,amount);
                break;
            case "YEAR":
                calendar.add(Calendar.YEAR, amount);
                calendar.add(Calendar.HOUR,amount);
                break;
            default:
                break;
        }
        currentDate = calendar.getTime();
        String date = formatter.format(currentDate);
        return date;
    }

    public void waitUntilAlertDisappear() {
        basePage.getIsLoadingSpinner().waitUntil(Condition.disappear, 10000);
        basePage.getIsLoadingSpinner().shouldNotBe(Condition.visible);
        alertPopup.getAlertDialog().waitUntil(Condition.disappear, 10000);
        alertPopup.getAlertDialog().shouldNotBe(Condition.visible);
        Selenide.sleep(500);
    }

    public void refreshPage() {
        Selenide.refresh();
        waitUntilAlertDisappear();
    }

    public String getRandomEmail() {
        return "L" + getRandomString(8) + "@email.com";
    }

    public String getRandomPassword() {
        return "L" + getRandomString(5) + "123!!!+++";
    }

    public String getRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }
        return String.valueOf(sb);
    }

    public String getRandomNumber(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) (random.nextInt(8) + '1'));
        }
        return String.valueOf(sb);
    }

    public String getRandomNumber(int min, int max) {
        Random r = new Random();
        return String.valueOf(r.nextInt((max - min) + 1) + min);
    }

    public String getRandomPhone(String prefix) {
        if (prefix.equals("+")) return prefix + getRandomNumber(11);
        else return "00" + getRandomNumber(11);
    }

    public String getRandomPhone(String prefix, int length) {
        return prefix + getRandomNumber(length);
    }

    public String getRandomPhone() {
        return "00" + getRandomNumber(11);
    }

    @Step("Log out the system")
    public void logOut(){
        basePage.getButtonLogout().click();
        waitUntilAlertDisappear();
        loginPage.getButtonLogin().shouldBe(visible,appear,appears);
    }

    @Step("Log in as VPBX admin")
    public BasePage login() {
        loginPage.fillInLogin(getLogin());
        loginPage.fillInPassword(getPassword());
        loginPage.getButtonLogin().click();
        waitUntilAlertDisappear();
        return new BasePage();
    }

    @Step("Log in as Low-Level user")
    public BasePageLowLevelUser loginAsLowLevelUser(){
        loginPage.fillInLogin(getLowLevelUserLogin());
        loginPage.fillInPassword(getLowLevelUserPassword());
        loginPage.getButtonLogin().click();
        waitUntilAlertDisappear();
        return new BasePageLowLevelUser();
    }

    public void login(String login, String pass) {
        loginPage.fillInLogin(login);
        loginPage.fillInPassword(pass);
        loginPage.getButtonLogin().click();
    }

    public void uploadPhoneBook(Phonebook phonebook) {
        phonebook.createExcelPhonebookFile();
        basePage.getTabPhonebook().click();
        phonebookPage.validatePageTitle("Phonebook");
        phonebookPage.uploadFile(phonebook.getfileName());
        phonebookPage.validateUploadedNumbers(phonebook.getArr().length);
        excelFileWorker.deleteFile(phonebook.getfileName());
    }

    public void deletePhonebook() {
        refreshPage();
        if (!(phonebookPage.getListNumbers().filterBy(Condition.text("No Items")).size() == 1)) {
            basePage.getTabPhonebook().click();
            phonebookPage.getButtonDeletePhoneBook().click();
            confirmationPopup.getYesButton().click();
            phonebookPage.checkIfPhonebookWasDeleted();
        }
    }

    public void callPickUpCleanUp(ArrayList<CallPickUp> callPickUpsList){
        for (CallPickUp callPickUp : callPickUpsList) {
            deleteCallPickupApi(callPickUp);
        }
    }

    public void ivrCleanUp(List<IVRtestData> ivrList){
        for (IVRtestData ivr : ivrList) {
            deleteIvrApi(ivr);
        }
    }

    public void huntGroupCleanUp(List<HuntGroup> huntGroupList){
        for (HuntGroup huntGroup:huntGroupList) {
            deleteHuntGroupApi(huntGroup);
        }
    }

    public void userCleanUp(List<User> userList){
        for (User user : userList) {
            deleteUsersApi(user);
        }
    }

    public void queueCleanUp(List<Queue> queueList){
        for (Queue queue : queueList) {
            deleteQueueApi(queue);
        }
    }

    public void mohCleanUp(List<FileManagementTestData> filesList){
        for (FileManagementTestData moh : filesList) {
            deleteMohApi(moh);
        }
    }

    public void abbrevNumsCleanUp(ArrayList<AbbreviatedDialling> abbrevDialList){
        for (AbbreviatedDialling abbreviatedDial : abbrevDialList) {
            deleteAbbreviatedNumberApi(abbreviatedDial);
        }
    }

    public void deleteAnnouncementLowLevelUser(FileManagementTestData announcement){
        basePageLowLevelUser
                .goToMenuTab(VOICEMAIL)
                .goToMenuTab(BasePageLowLevelUser.MenuTabsLowLevelUser.ANNOUNCEMENTS);
        announcementsUserPage.getButtonDeleteByName(announcement.getFileName()).click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        announcementsUserPage.getFieldNameByText(announcement.getFileName()).shouldNot(exist);
    }

    public void announcementCleanUp(List<FileManagementTestData> filesList){
        for (FileManagementTestData announcement: filesList) {
            deleteAnnouncementApi(announcement);
        }
    }

    public void lowLevelUserAnnouncementCleanUp(List<FileManagementTestData> filesList){
        try {
            basePageLowLevelUser
                    .goToMenuTab(VOICEMAIL)
                    .goToMenuTab(ANNOUNCEMENTS);
            for (FileManagementTestData file: filesList) {
                if (announcementsUserPage.getFieldNameByText(file.getFileName()).exists()){
                    deleteAnnouncementLowLevelUser(file);
                }
            }
        } catch (Throwable e) {
            System.out.println("Low-LevelUser Announcement CleanUp failed");
            e.printStackTrace();
        }
    }

    public void lowLevelUserUploadAnnouncement(FileManagementTestData announcement){
        basePageLowLevelUser
                .goToMenuTab(VOICEMAIL)
                .goToMenuTab(ANNOUNCEMENTS);
        announcementsUserPage.uploadAnnouncementFile(announcement);
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        announcementsUserPage.validateIfAnnouncementExcists(announcement);
    }

    public void phoneBookExcelCleanUp(List<Phonebook> phonebookList){
        for (Phonebook phonebook: phonebookList) {
            if (excelFileWorker.checkIfFileExists(phonebook.getfileName())){
                excelFileWorker.deleteFile(phonebook.getfileName());
            }
        }
    }

    public void addressBookCleanUp(List<AddressBookTestData> addressBookList){
        for (AddressBookTestData addressBook:addressBookList) {
            deleteAddressBookEntryApi(addressBook);
        }
    }

    public void lowLevelUserCallForwardingCleanUp(){
        basePageLowLevelUser.goToMenuTab(CALL_FORWARDING);
        if (callForwardingPage.getCheckboxAfter().isSelected())callForwardingPage.getCheckboxAfter().click();
        if (callForwardingPage.getCheckboxIfbusy().isSelected())callForwardingPage.getCheckboxIfbusy().click();
        if (callForwardingPage.getCheckboxDeviceUnavailable().isSelected())callForwardingPage.getCheckboxDeviceUnavailable().click();
        if (callForwardingPage.getCheckboxSuppressedNumbers().isSelected())callForwardingPage.getCheckboxSuppressedNumbers().click();
        callForwardingPage.getButtonSave().click();
        waitUntilAlertDisappear();
    }

    public void cleanUpConfCalls(ArrayList<ConferenceCallTestData> confCallsList){
        for (ConferenceCallTestData entry : confCallsList) {
            deleteConferenceCallApi(entry.getId());
        }
    }

    public void cleanUpAuthNums(ArrayList<AuthorizedNumberTestData> authorizedNumberList){
        for (AuthorizedNumberTestData authNumber:authorizedNumberList) {
            deleteAuthorizedNumberApi(authNumber);
        }
    }

}
