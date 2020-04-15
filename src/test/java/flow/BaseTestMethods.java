package flow;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import core.configuration.preparations.eFonApp;
import tests.IVRpageTests.IVRtestData.BlockListTestData;
import tests.IVRpageTests.IVRtestData.IVRtestData;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;
import tests.fileManagementPageTests.fileManagementTestData.FileManagementTestData;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.phonebookPageTests.phonebookPageTestData.Phonebook;
import tests.queuesPageTest.queueTestData.Queue;
import tests.userPageTests.userPageTestData.User;
import tests.сonferenceCallsPageTests.ConferenceCallTestData.Conference;
import testsLowLevelUser.sendSmsUserPageTests.sendSmsTestData.AddressBookTestData;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exist;
import static io.qameta.allure.Allure.step;
import static lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser.MenuTabsLowLevelUser.VOICEMAIL;
import static lowLevelUserPages.voicemailLowLevelUserpage.VoicemailBaseUserPage.VoicemailTabs.ANNOUNCEMENTS;
import static pages.basePage.BasePage.MenuTabsBasePage.*;

public class BaseTestMethods extends eFonApp {

    public String getDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date currentDate = new Date();
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

    public void login() {
        loginPage.fillInLogin(getLogin());
        loginPage.fillInPassword(getPassword());
        loginPage.getButtonLogin().click();
        waitUntilAlertDisappear();
    }

    public void loginAsLowLevelUser(){
        loginPage.fillInLogin(getLowLevelUserLogin());
        loginPage.fillInPassword(getLowLevelUserPassword());
        loginPage.getButtonLogin().click();
        waitUntilAlertDisappear();
    }

    public void login(String login, String pass) {
        loginPage.fillInLogin(login);
        loginPage.fillInPassword(pass);
        loginPage.getButtonLogin().click();
    }

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
        user.setPhoneNumber(createUserPopup.selectNumber());
        createUserPopup.selectEndDevices();
        user.setEndDevices(createUserPopup.getSelectedEndDeviceName());
        createUserPopup.fillInDiffContactEmail(user.getUseDiffContactEmail());
        createUserPopup.fillInVoiceEmail(user.getVoiceEmail());
        createUserPopup.getCheckboxBusyOnBusy().click();
        user.setPermittedDestinationNumbers(createUserPopup.selectPermittedDestinationNumbers());
        createUserPopup.getCheckboxSmsEnabled().click();
        createUserPopup.getCheckboxRoleFinance().click();
        user.setCallsRecordingDirection(createUserPopup.activateCallRecordings());
        //createUserPopup.activateFaxDispatch(user.getInputLocalHeaderInfo());
        createUserPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        userPage.checkIfUserExistsInTheList(user);
    }

    public void deleteUser(User user) {
        basePage.getTabUser().click();
        waitUntilAlertDisappear();
        userPage.deleteUserButtonClick(user.getFullName());
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        userPage.checkIfUserDeleted(user);
    }

    public void deleteUser(User ... users) {
        basePage.getTabUser().click();
        waitUntilAlertDisappear();
        for (User user: users) {
            userPage.deleteUserButtonClick(user.getFullName());
            confirmationPopup.getYesButton().click();
            waitUntilAlertDisappear();
            userPage.checkIfUserDeleted(user);
        }
    }

    public void deleteAllCustomerUsers() {
        String userName;
        basePage.getTabUser().click();
        while (userPage.getListUserNames().size() > 0) {
            userName = userPage.getListUserNames().get(0).getText();
            if (!userName.contains("No Items")) {
                userPage.deleteUserButtonClick(userName);
                confirmationPopup.getYesButton().click();
                waitUntilAlertDisappear();
            } else break;
        }
        userPage.getListUserNames().shouldHaveSize(1).shouldHave(CollectionCondition.texts("No Items"));
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

    public void addSingleAbbrevNumber(String abbrevNum) {
        basePage.goToMenuTab(ABBREVIATED_DIALING).goToMenuTab(MANAGE_ABBREVIATED_NUMBERS);
        manageAbbrevNumbersPage.addSingleAbbrevNumber(abbrevNum);
        waitUntilAlertDisappear();
        abbrevDialBasePage.getTabAbbreviatedNumbers().click();
        abbreviatedNumbers.checkIfAbbrevNumberExistsInList(abbrevNum);
    }

    public void deleteSingleAbbrevNumber(String abbrevNum) {
        basePage.getTabAbbreviatedDialling().click();
        waitUntilAlertDisappear();
        abbrevDialBasePage.getTabAbbreviatedNumbers().click();
        waitUntilAlertDisappear();
        basePage.getDropdownItemsPerPage().selectOptionContainingText("All");
        if (!abbreviatedNumbers.getButtonDeleteByNum(abbrevNum).exists()) {
            makeAbbrevNumberUnused(abbrevNum);
            refreshPage();
        }
        abbreviatedNumbers.deleteSingleAbbrevNumber(abbrevNum);
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        abbreviatedNumbers.checkIfAbbrevNumberDoesNotExistInList(abbrevNum);
    }

    public void deleteAllAbbrevNumbers() {
        String data;
        basePage.getTabAbbreviatedDialling().click();
        abbrevDialBasePage.getTabAbbreviatedNumbers().click();
        refreshPage();
        basePage.getDropdownItemsPerPage().selectOptionContainingText("All");
        while (abbreviatedNumbers.getListNo().size() > 0) {
            data = abbreviatedNumbers.getListNo().get(0).getText();
            if (data.equals("No Items")) break;
            if (!abbreviatedNumbers.getButtonDeleteByNum(data).exists()) {
                makeAbbrevNumberUnused(data);
                refreshPage();
            }
            deleteSingleAbbrevNumber(data);
        }
    }

    public void createAbbrevNumberRange(AbbreviatedDialling obj) {
        basePage.getTabAbbreviatedDialling().click();
        abbrevDialBasePage.getTabManageAbbreviatedNumbers().click();
        manageAbbrevNumbersPage.addRangeAbbrevNumber(obj.getFromNumber(), obj.getUntilNumber());
        abbrevDialBasePage.getTabAbbreviatedNumbers().click();
        abbreviatedNumbers.checkIfAbbrevNumberRangeCreated(obj);
    }

    public void makeAbbrevNumberUnused(String shortNumber) {
        abbreviatedNumbers.editSingleAbbrevNumber(shortNumber);
        waitUntilAlertDisappear();
        popupAssignAbbrevDial.getRadioUnused().click();
        popupAssignAbbrevDial.getButtonSave().click();
        waitUntilAlertDisappear();
    }

    public void configFaxForNewUser(User user) {
        login();
        createUser(user);
        basePage.getTabFax().click();
        faxPage.getDropdownSelectNumber().selectOption(0);
        faxPage.getEditButton().click();
        faxPage.getInputEmail().setValue(getRandomEmail());
        faxPage.getRadioPdfOnly().click();
        faxPage.getButtonSave().click();
        alertPopup.getAlertDialog().should(Condition.appears);
    }

    public void createCallPickUpGroup(String groupName, String abbrevNum) {
        basePage.getTabCallPickUps().click();
        callPickUpPage.getButtonNewGroup().click();
        groupCallPickupPopup.getInputName().setValue(groupName);
        groupCallPickupPopup.selectAbbrenNumber(abbrevNum);
        groupCallPickupPopup.getDropdownSelectAccounts().selectOption(1);
        groupCallPickupPopup.getButtonSave().click();

        callPickUpPage.getListName().filterBy(Condition.text(groupName)).shouldHave(CollectionCondition.size(1));
        callPickUpPage.getListAbbrevDial().filterBy(Condition.text(abbrevNum)).shouldHave(CollectionCondition.size(1));
    }

    public void deleteCallPickUpGroup(String groupName) {
        callPickUpPage.deletePickUpGroup(groupName);
        confirmationPopup.getYesButton().click();
        callPickUpPage.getListName().filterBy(Condition.text(groupName)).shouldHave(CollectionCondition.size(0));
    }

    public void createConferenceCall(Conference conference) {
        basePage.getTabConferenceCalls().click();
        conferenceCallsPage.getButtonNewConferenceCall().click();
        waitUntilAlertDisappear();
        createNewConfCallPopup.getInputName().setValue(conference.getName());
        createNewConfCallPopup.getDropdownConferenceCallNum().selectOption(1);
        conference.setConferenceNumber(createNewConfCallPopup.getDropdownConferenceCallNum().getSelectedText());
        createNewConfCallPopup.getInputPin().setValue(conference.getPin());
        createNewConfCallPopup.getDropdownLanguage().selectOptionByValue(conference.getLanguage());
        createNewConfCallPopup.getButtonSave().click();
        waitUntilAlertDisappear();
    }

    public void deleteConferenceCall(String confName) {
        basePage.getTabConferenceCalls().click();
        conferenceCallsPage.getButtonDeleteByName(confName).click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        conferenceCallsPage.getListNames().filterBy(Condition.text(confName)).shouldHaveSize(0);
    }

    public void createIVR(IVRtestData ivr, FileManagementTestData file) {
        basePage.goToMenuTab(IVRs);
        ivrPage.getButtonNewIvr().click();
        waitUntilAlertDisappear();
        createNewIvrPopup.getInputName().setValue(ivr.getIvrName());
        createNewIvrPopup.getInputDisplayName().setValue(ivr.getIvrDisplName());
        createNewIvrPopup.getDropdownLanguage().selectOptionByValue(ivr.getIvrLanguage());
        createNewIvrPopup.selectIvrNumber();
        ivr.setIvrNumber(createNewIvrPopup.getDropdownSelectIvrNumber().getSelectedText());
        createNewIvrPopup.getDropdownSelectAnnounc().selectOptionContainingText(file.getFileName());
        ivr.setIvrAnnounce(createNewIvrPopup.getDropdownSelectAnnounc().getSelectedText());
        createNewIvrPopup.getButtonSave().shouldBe(enabled).click();
        waitUntilAlertDisappear();
        ivrPage.getListName().filterBy(Condition.text(ivr.getIvrName())).shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

    public void deleteIVR(String name) {
        basePage.getTabIVRs().click();
        waitUntilAlertDisappear();
        ivrPage.getButtonDeleteIvrByName(name).click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        ivrPage.getListName().filterBy(Condition.text(name)).shouldHave(CollectionCondition.size(0));
    }

    public void createHuntGroup(HuntGroup huntGroup){
        basePage.goToMenuTab(HUNT_GROUPS);
        huntGroupPage.getButtonCreateNewHuntGroup().click();
        waitUntilAlertDisappear();
        createHuntGroupPopup.getInputName().setValue(huntGroup.getHuntGroupName());
        createHuntGroupPopup.getInputDisplName().setValue(huntGroup.getHuntGroupDisplayName());
        createHuntGroupPopup.selectRandomNumber();
        createHuntGroupPopup.getDropdownLanguage().selectOptionByValue(huntGroup.getHuntGroupLanguage());
        createHuntGroupPopup.getButtonSubmitEditHuntGroup().click();
        createHuntGroupPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        huntGroupPage.getfieldNameByText(huntGroup.getHuntGroupName()).should(Condition.exist);
    }

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
        try {
            refreshPage();
            basePage.goToMenuTab(IVRs);
            for (IVRtestData ivr: ivrList) {
                if (ivrPage.getListName().filterBy(Condition.text(ivr.getIvrName())).size()>0){
                    deleteIVR(ivr.getIvrName());
                }
            }
        } catch (Throwable e) {
            System.out.println("ivrCleanUp failed");
            e.printStackTrace();
        }
    }

    public void huntGroupCleanUp(List<HuntGroup> huntGroupList){
        try {
            refreshPage();
            basePage.goToMenuTab(HUNT_GROUPS);
            for (HuntGroup huntGroup: huntGroupList) {
                if (huntGroupPage.getfieldNameByText(huntGroup.getHuntGroupName()).exists()){
                    deleteHuntGroup(huntGroup.getHuntGroupName());
                }
            }
        } catch (Throwable e) {
            System.out.println("huntGroupCleanUp failed");
            e.printStackTrace();
        }
    }

    public void userCleanUp(List<User> userList){
        try {
            refreshPage();
            basePage.goToMenuTab(USER);
            for (User user: userList) {
                if (userPage.getListUserNames().filterBy(Condition.text(user.getFullName())).size()>0){
                    deleteUser(user);
                }
            }
        } catch (Throwable e) {
            System.out.println("userCleanUp failed");
            e.printStackTrace();
        }
    }

    public void createQueue(Queue queue){
        basePage.goToMenuTab(QUEUES).goToMenuTab(CONFIGURE_QUEUES);
        configureQueueTab.getButtonCreateNewQueue().click();
        createNewQueuePopup.getInputQueueName().setValue(queue.getName());
        createNewQueuePopup.selectRandomSubscriptionForQueue();
        queue.setSubscription(createNewQueuePopup.getDropdownSubscription().getSelectedText());
        createNewQueuePopup.getDropdownMaxWaintingTime().selectOptionByValue(queue.getMaxWaitTime());
        createNewQueuePopup.getDropdownPriority().selectOptionContainingText(queue.getPriority());
        createNewQueuePopup.getDropdownWaitingMusic().selectOption(1);
        queue.setWaitingMusic(createNewQueuePopup.getDropdownWaitingMusic().getSelectedText());
        createNewQueuePopup.getDropdownFileNameAnnounc().selectOption(1);
        queue.setFilenameAnnouncement(createNewQueuePopup.getDropdownFileNameAnnounc().getSelectedText());
        createNewQueuePopup.getDropdownAnnounFreq().selectOptionByValue(queue.getAnnouncementFrequency());
        createNewQueuePopup.getDropdownRulesForFindAgent().selectOptionContainingText(queue.getRuleForFindingAgent());
        createNewQueuePopup.getDropdownTimeOutForCall().selectOptionByValue(queue.getTimeoutForCalling());
        createNewQueuePopup.getDropdownRetry().selectOptionByValue(queue.getWaitingTimeBeforeNextAttempt());
        createNewQueuePopup.getDropdownWrapUpTime().selectOptionByValue(queue.getWaitingTimeBeforeNextCall());
        createNewQueuePopup.getDropdownRecordCalls().selectOptionContainingText(queue.getRecordCalls());
        createNewQueuePopup.getButtonSave().shouldBe(Condition.enabled).click();
        waitUntilAlertDisappear();
        refreshPage();
        configureQueueTab.getFieldQueueNameByText(queue.getName()).should(Condition.exist);
    }

    public void createQueue(Queue queue, AbbreviatedDialling shortNum){
        basePage.goToMenuTab(QUEUES).goToMenuTab(CONFIGURE_QUEUES);
        configureQueueTab.getButtonCreateNewQueue().click();
        createNewQueuePopup.getInputQueueName().setValue(queue.getName());
        createNewQueuePopup.selectRandomSubscriptionForQueue();
        queue.setSubscription(createNewQueuePopup.getDropdownSubscription().getSelectedText());
        createNewQueuePopup.getDropdownLoginLogout().selectOptionContainingText(shortNum.getSingleShortNum());
        createNewQueuePopup.getDropdownMaxWaintingTime().selectOptionByValue(queue.getMaxWaitTime());
        createNewQueuePopup.getDropdownPriority().selectOptionContainingText(queue.getPriority());
        createNewQueuePopup.getDropdownWaitingMusic().selectOption(1);
        queue.setWaitingMusic(createNewQueuePopup.getDropdownWaitingMusic().getSelectedText());
        createNewQueuePopup.getDropdownFileNameAnnounc().selectOption(1);
        queue.setFilenameAnnouncement(createNewQueuePopup.getDropdownFileNameAnnounc().getSelectedText());
        createNewQueuePopup.getDropdownAnnounFreq().selectOptionByValue(queue.getAnnouncementFrequency());
        createNewQueuePopup.getDropdownRulesForFindAgent().selectOptionContainingText(queue.getRuleForFindingAgent());
        createNewQueuePopup.getDropdownTimeOutForCall().selectOptionByValue(queue.getTimeoutForCalling());
        createNewQueuePopup.getDropdownRetry().selectOptionByValue(queue.getWaitingTimeBeforeNextAttempt());
        createNewQueuePopup.getDropdownWrapUpTime().selectOptionByValue(queue.getWaitingTimeBeforeNextCall());
        createNewQueuePopup.getDropdownRecordCalls().selectOptionContainingText(queue.getRecordCalls());
        createNewQueuePopup.getButtonSave().shouldBe(Condition.enabled).click();
        waitUntilAlertDisappear();
        refreshPage();
        configureQueueTab.getFieldQueueNameByText(queue.getName()).should(Condition.exist);
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
        try {
            refreshPage();
            if (!basePage.getPageTitle().equals("Queues")){
                basePage.goToMenuTab(QUEUES).goToMenuTab(CONFIGURE_QUEUES);
            }
            for (Queue queue: queueList) {
                if (configureQueueTab.getFieldQueueNameByText(queue.getName()).exists()){
                    deleteQueue(queue.getName());
                }
            }
        } catch (Throwable e) {
            System.out.println("queueCleanUp failed");
            e.printStackTrace();
        }
    }

    public void uploadMusicOnHoldFile(FileManagementTestData file){
        basePage.getTabFileManagement().click();
        fileManagementBasePage.getTabMusicOnHold().click();
        musicOnHoldPage.uploadFile(file.getFilePath());
        musicOnHoldPage.getInputName().setValue(file.getFileName());
        musicOnHoldPage.getButtonSave().click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        musicOnHoldPage.getFieldNameByText(file.getFileName()).should(Condition.exist);
    }

    public void deleteMusicOnHoldFile(String MOHfileName){
        musicOnHoldPage.getButtonDeleteByName(MOHfileName).click();
        confirmationPopup.getYesButton().click();
        refreshPage();
        waitUntilAlertDisappear();
        musicOnHoldPage.getFieldNameByText(MOHfileName).shouldNot(Condition.exist);
    }

    public void mohCleanUp(List<FileManagementTestData> filesList){
        try {
            basePage.getTabFileManagement().click();
            waitUntilAlertDisappear();
            fileManagementBasePage.getTabMusicOnHold().click();
            waitUntilAlertDisappear();
            for (FileManagementTestData file: filesList) {
                if (musicOnHoldPage.getFieldNameByText(file.getFileName()).exists()){
                    deleteMusicOnHoldFile(file.getFileName());
                }
            }
        } catch (Throwable e) {
            System.out.println("mohCleanUp failed");
            e.printStackTrace();
        }
    }

    public void abbrevNumsCleanUp(ArrayList<AbbreviatedDialling> abbrevDialList){
        try {
            basePage.getTabAbbreviatedDialling().click();
            waitUntilAlertDisappear();
            abbrevDialBasePage.getTabAbbreviatedNumbers().click();
            waitUntilAlertDisappear();
            for (AbbreviatedDialling num: abbrevDialList) {
                if (abbreviatedNumbers.getFieldNumberByText(num.getSingleShortNum()).exists()){
                    deleteSingleAbbrevNumber(num.getSingleShortNum());
                }
            }
        } catch (Exception e) {
            System.out.println("abbrevNumsCleanUp failed");
            e.printStackTrace();
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
        basePageLowLevelUser.goToMenuTab(VOICEMAIL);
        voicemailBaseUserPage.goToMenuTab(ANNOUNCEMENTS);
        announcementsUserPage.getButtonDeleteByName(announcement.getFileName()).click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        announcementsUserPage.getFieldNameByText(announcement.getFileName()).shouldNot(exist);
    }

    public void announcementCleanUp(List<FileManagementTestData> filesList){
        try {
            refreshPage();
            basePage.goToMenuTab(FILE_MANAGEMENT).goToMenuTab(ANNOUNCEMENT_DISPLAY);
            for (FileManagementTestData file: filesList) {
                if (announcementDisplayPage.getFieldNameByText(file.getFileName()).exists()){
                    deleteAnnouncementFile(file.getFileName());
                }
            }
        } catch (Throwable e) {
            System.out.println("announcementCleanUp failed");
            e.printStackTrace();
        }
    }

    public void lowLevelUserAnnouncementCleanUp(List<FileManagementTestData> filesList){
        try {
            basePageLowLevelUser.goToMenuTab(VOICEMAIL);
            waitUntilAlertDisappear();
            voicemailBaseUserPage.getTabAnnouncements().click();
            waitUntilAlertDisappear();
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
        basePageLowLevelUser.goToMenuTab(VOICEMAIL);
        voicemailBaseUserPage.goToMenuTab(ANNOUNCEMENTS);
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

    public void addAgentToQueue(Queue queue, User user){
        basePage.goToMenuTab(QUEUES).goToMenuTab(CONFIGURE_QUEUES);
        queueForAgentsPopup.addAgentToQueue(queue, user);
        queueForAgentsPopup.validateAddedAgents(queue,user);
    }

    public void configureBlockList(IVRtestData ivr, BlockListTestData blockList){
        step("Configure Block list section - select number");
        blockListSection.getDropdownNumbers().selectOptionContainingText(ivr.getIvrNumber());

        step("Activate \"Block incoming calls\" and select \"Forward to\" option");
        blockListSection.getCheckboxBlockIncomingCalls().click();
        blockListSection.getDropdownForwardTo().selectOptionByValue("VOICEMAIL");

        step("Activate option \"Calls with suppressed numbers\"");
        blockListSection.getCheckboxCallsSuppressedNumbers().click();

        step("Activate option \"Use blocklist\"");
        blockListSection.getCheckboxUseBlocklist().click();
        blockListSection.getDropdownBlocklistType().selectOptionByValue("BLOCKED_NUMBERS");

        step("Add numbers to Block list");
        blockListSection.getButtonEditBlocklist().click();
        waitUntilAlertDisappear();
        blocklistPopup.addNumberToBlockList(blockList);
    }

}
