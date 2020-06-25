package flow;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import core.configuration.preparations.eFonApp;
import io.qameta.allure.Step;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;
import pages.basePage.BasePage;
import pages.userPage.userPagePopup.configureUser.ConfigureUserBasePopup;
import tests.IVRpageTests.IVRtestData.BlockListTestData;
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
import testsLowLevelUser.sendSmsUserPageTests.sendSmsTestData.SendSmsTestData;

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
import static api.baseApiMethods.UserApi.deleteUserApi;
import static api.baseApiMethods.UserApi.deleteUsersApi;
import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.Allure.step;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.MenuTabsLowLevelUser.*;
import static pages.basePage.BasePage.ItemsPerPage._All;
import static pages.basePage.BasePage.MenuTabsBasePage.*;

public class BaseTestMethods extends eFonApp {

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

    @Step("Create new user")
    public void createUser(User user) {
        basePage.getTabUser().click();
        userPage.getPageTitle().getText().equals("User");
        userPage.getButtonCreateNewUser().click();
        createUserPopup.getInputFirstName().waitUntil(Condition.appears, 10000);
        createUserPopup.getPopupTitle().getText().equals("Create user");
        createUserPopup.selectTitle(user.getTitle());
        createUserPopup.fillFirstName(user.getFirstName());
        createUserPopup.fillLastName(user.getLastName());
        createUserPopup.fillLoginEmail(user.getLoginEmail());
        createUserPopup.selectNumber(user.getPhoneNumber());
        createUserPopup.selectEndDevices();
        user.setEndDevices(createUserPopup.getSelectedEndDeviceName());
        createUserPopup.fillInDiffContactEmail(user.getUseDiffContactEmail());
        createUserPopup.fillInVoiceEmail(user.getVoiceEmail());
        createUserPopup.getCheckboxBusyOnBusy().click();
        user.setPermittedDestinationNumbers(createUserPopup.selectPermittedDestinationNumbers());
        createUserPopup.getCheckboxSmsEnabled().click();
        user.setCallsRecordingDirection(createUserPopup.activateCallRecordings());
        //createUserPopup.activateFaxDispatch(user.getInputLocalHeaderInfo());
        createUserPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        userPage.checkIfUserExistsInTheList(user);
    }

    @Step("Delete the user")
    public void deleteUser(User user) {
        basePage.getTabUser().click();
        waitUntilAlertDisappear();
        userPage.deleteUserButtonClick(user.getFullName());
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        userPage.checkIfUserDeleted(user);
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

    public void createHuntGroup(HuntGroup huntGroup, String authorisedUser){
        basePage.goToMenuTab(HUNT_GROUPS);
        huntGroupPage.getButtonCreateNewHuntGroup().click();
        waitUntilAlertDisappear();
        createHuntGroupPopup.getInputName().setValue(huntGroup.getHuntGroupName());
        createHuntGroupPopup.getInputDisplName().setValue(huntGroup.getHuntGroupDisplayName());
        createHuntGroupPopup.selectRandomNumber();
        huntGroup.setHuntGroupNumber(createHuntGroupPopup.getDropdownNumber().getSelectedText());
        createHuntGroupPopup.getDropdownLanguage().selectOptionByValue(huntGroup.getHuntGroupLanguage());
        createHuntGroupPopup.getDropdownAuthUsers().selectOptionContainingText(authorisedUser);
        huntGroup.setHuntGroupAuthorizedUser(authorisedUser);
        createHuntGroupPopup.getButtonSubmitEditHuntGroup().click();
        createHuntGroupPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        huntGroupPage.getfieldNameByText(huntGroup.getHuntGroupName()).should(Condition.exist);
    }

    @Step("Delete hunt group")
    public void deleteHuntGroup(String name){
        basePage.goToMenuTab(HUNT_GROUPS);
        waitUntilAlertDisappear();
        huntGroupPage.getButtonDeleteByName(name).click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        refreshPage();
        huntGroupPage.getfieldNameByText(name).shouldNot(Condition.exist);
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

    public void createQueueOnlyRequiredFields(Queue queue){
        basePage.getTabQueues().click();
        waitUntilAlertDisappear();
        queuesBasePage.getTabConfigureQueues().click();
        waitUntilAlertDisappear();
        configureQueueTab.getButtonCreateNewQueue().click();
        createNewQueuePopup.getInputQueueName().setValue(queue.getName());
        createNewQueuePopup.selectRandomSubscriptionForQueue();
        queue.setSubscription(createNewQueuePopup.getDropdownSubscription().getSelectedText());
        createNewQueuePopup.getButtonSave().shouldBe(Condition.enabled).click();
        waitUntilAlertDisappear();
        refreshPage();
        configureQueueTab.getFieldQueueNameByText(queue.getName()).should(Condition.exist);
    }

    public void deleteQueue(String queueName){
        basePage.goToMenuTab(QUEUES).goToMenuTab(CONFIGURE_QUEUES);
        configureQueueTab.getButtonDeleteQueueByName(queueName).click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        configureQueueTab.getFieldQueueNameByText(queueName).shouldNot(Condition.exist);
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

    @Step("Clean up abbreviated numbers - delete test data")
    public void abbrevNumsCleanUp(ArrayList<AbbreviatedDialling> abbrevDialList){
        for (AbbreviatedDialling abbreviatedDial : abbrevDialList) {
            deleteAbbreviatedNumberApi(abbreviatedDial);
        }
    }

    public void uploadAnnouncementFile(FileManagementTestData file){
        basePage.goToMenuTab(FILE_MANAGEMENT).goToMenuTab(ANNOUNCEMENT_DISPLAY);
        announcementDisplayPage.getButtonUploadFile().click();
        announcementDisplayPage.uploadFile(file.getFilePath());
        waitUntilAlertDisappear();
        announcementDisplayPage.getInputName().setValue(file.getFileName());
        announcementDisplayPage.getButtonSave().click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        refreshPage(); // temp fix because of the bug
        announcementDisplayPage.getFieldNameByText(file.getFileName()).should(Condition.exist);
    }

    public void deleteAnnouncementFile(String fileName){
        basePage.goToMenuTab(FILE_MANAGEMENT).goToMenuTab(ANNOUNCEMENT_DISPLAY);
        announcementDisplayPage.getButtonDeleteByName(fileName).click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        refreshPage();
        announcementDisplayPage.getFieldNameByText(fileName).shouldNot(Condition.exist);
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

    public void deleteAddressBook(String addressBookPhone){
        basePageLowLevelUser.getTabSendSms().click();
        sendSmsBaseUserPage.getTabAddressBook().click();
        addressBookUserPage.getButtonDeleteByText(addressBookPhone).click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        refreshPage();
        addressBookUserPage.getFieldMobileNumberByText(addressBookPhone).shouldNot(Condition.exist);
    }

    public void addressBookCleanUp(List<AddressBookTestData> addressBookList){
        try {
            basePageLowLevelUser.getTabSendSms().click();
            sendSmsBaseUserPage.getTabAddressBook().click();
            for (AddressBookTestData entry: addressBookList) {
                if (configureQueueTab.getFieldQueueNameByText(entry.getMobileNumber()).exists()){
                    deleteAddressBook(entry.getMobileNumber());
                }
            }
        } catch (Throwable e) {
            System.out.println("addressBookCleanUp failed");
            e.printStackTrace();
        }
    }

    public void createAddressBookEntry(AddressBookTestData addressBook){

        basePageLowLevelUser.getTabSendSms().click();
        sendSmsBaseUserPage.getTabAddressBook().click();
        step("Click Add button");
        addressBookUserPage.getButtonAdd().click();

        step("Fill in all Address Book required fields");
        createSmsAddressPopup.fillInAllAddressBookRequiredFields(addressBook);

        step("Save all data");
        createSmsAddressPopup.getButtonSave().click();
        waitUntilAlertDisappear();

        step("Validate if entry was created");
        addressBookUserPage.validateIfEntryWasCreated(addressBook);
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

    public void addAgentToQueue(String queue, User user){
        basePage.goToMenuTab(QUEUES).goToMenuTab(CONFIGURE_QUEUES);
        configureQueueTab.openQueueAgentPopup(queue);
        queueForAgentsPopup.addAgentToQueue(queue, user);
        queueForAgentsPopup.validateAddedAgents(queue,user);
    }

    public void createNonAthorizedSmsSenderNumber(String senderNumber){
        basePageLowLevelUser.goToMenuTab(SEND_SMS).goToMenuTab(MANAGE_SENDER_NUMBERS_AND_NAMES);
        manageSenderNumbersUserPage.getButtonAdd().click();
        waitUntilAlertDisappear();
        newSenderNumberPopup.getInputMobileNumber().setValue(senderNumber);
        newSenderNumberPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        manageSenderNumbersUserPage.verifyIfNumberAddedAsNonAuthorized(senderNumber);
    }

    public void createAthorizedSmsSenderNumber(String senderNumber){
        basePageLowLevelUser.goToMenuTab(SEND_SMS).goToMenuTab(MANAGE_SENDER_NUMBERS_AND_NAMES);
        manageSenderNumbersUserPage.getButtonAdd().click();
        waitUntilAlertDisappear();
        newSenderNumberPopup.getInputMobileNumber().setValue(senderNumber);
        newSenderNumberPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        manageSenderNumbersUserPage.verifyIfNumberAddedAsNonAuthorized(senderNumber);
        manageSenderNumbersUserPage.getButtonEditByText(senderNumber).click();
        waitUntilAlertDisappear();
        activateAuthorisationCodePopup.enterAuthorizationCode(senderNumber);
        activateAuthorisationCodePopup.getButtonSave().click();
        waitUntilAlertDisappear();
        manageSenderNumbersUserPage.verifyIfNumberAddedAsAuthorized(senderNumber);
    }

    public void authorizeSmsSenderNumber(String senderNumber){
        manageSenderNumbersUserPage.getButtonEditByText(senderNumber).click();
        waitUntilAlertDisappear();
        activateAuthorisationCodePopup.enterAuthorizationCode(senderNumber);
        activateAuthorisationCodePopup.getButtonSave().click();
        waitUntilAlertDisappear();
        manageSenderNumbersUserPage.verifyIfNumberAddedAsAuthorized(senderNumber);
    }

    public void deleteSmsSenderNumber(String senderNumber){
        basePageLowLevelUser.goToMenuTab(SEND_SMS).goToMenuTab(MANAGE_SENDER_NUMBERS_AND_NAMES);
        manageSenderNumbersUserPage.getButtonDeleteByText(senderNumber).click();
        waitUntilAlertDisappear();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        refreshPage();
        manageSenderNumbersUserPage.getFiledMobileNumberByText(senderNumber).shouldNot(exist);
    }

    public void sendSms(SendSmsTestData sms){
        step("Go to Send SMS page");
        basePageLowLevelUser.goToMenuTab(SEND_SMS);

        step("Select sender");
        sendTextMessageUserPage.getDropdownSenderName().selectOptionByValue(sms.getSenderNumber());

        step("Add recipient");
        sendTextMessageUserPage.getInputRecipientNumber().setValue(sms.getRecipientNumber());
        waitUntilAlertDisappear();
        sendTextMessageUserPage.getButtonAddRecipient().click();

        step("Fill in SMS text");
        sendTextMessageUserPage.getInputSmsTextArea().setValue(sms.getSmsText());

        step("Click Send SMS");
        sendTextMessageUserPage.getButtonSend().click();
        waitUntilAlertDisappear();

        step("Check if SMS were sent and \"Confirmation popup appears\"");
        smsConfirmationPopup.getTitle().shouldBe(Condition.visible,Condition.appear);
        smsConfirmationPopup.getButtonClose().click();
    }

    public ArrayList<String> getListOfAllCustomerNumbers(){
        ArrayList<String> customerNumbersList;
        login();
        basePage.goToMenuTab(NUMBERS);
        basePage.getDropdownItemsPerPage().selectOptionContainingText("All");
        waitUntilAlertDisappear();
        customerNumbersList = numbersPage.getListOfNumbers();
        logOut();
        waitUntilAlertDisappear();
        return customerNumbersList;
    }

    public void uploadAnnouncementForUserOnEditPopup(User user, FileManagementTestData announcFile){
        userPage.openEditUserPopup(user);
        configureUserBasePopup.goToTab(ConfigureUserBasePopup.Tabs.ANNOUNCEMENTS);
        announcementsTabConfigUserPopup.getButtonUploadFile().click();
        announcementsTabConfigUserPopup.uploadAnnouncementFile(announcFile.getFilePath());
        waitUntilAlertDisappear();
        announcementsTabConfigUserPopup.getInputName().setValue(announcFile.getFileName());
        announcementsTabConfigUserPopup.getButtonSave().click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        announcementsTabConfigUserPopup.getButtonClose().click();
    }

    public void cleanUpConfCalls(ArrayList<ConferenceCallTestData> confCallsList){
        for (ConferenceCallTestData entry : confCallsList) {
            deleteConferenceCallApi(entry.getId());
        }
    }

}
