package flow;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import core.configuration.preparations.eFonApp;
import tests.IVRpageTests.IVRtestData.IVRtestData;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDialling;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.phonebookPageTests.phonebookPageTestData.Phonebook;
import tests.userPageTests.userPageTestData.User;
import tests.сonferenceCallsPageTests.ConferenceCallTestData.Conference;

import java.util.List;
import java.util.Random;

public class BaseTestMethods extends eFonApp {

    public void waitUntilAlertDisappear() {
        basePage.getIsLoadingSpinner().waitUntil(Condition.disappear, 10000);
        basePage.getIsLoadingSpinner().shouldNotBe(Condition.visible);
        alertPopup.getAlertDialog().waitUntil(Condition.disappear, 10000);
        alertPopup.getAlertDialog().shouldNotBe(Condition.visible);
        Selenide.sleep(1000);
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

    public String getRandomPhone() {
        return "00" + getRandomNumber(11);
    }

    public void login() {
        loginPage.fillInLogin(getLogin());
        loginPage.fillInPassword(getPassword());
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
        createUserPopup.fillInDiffContactEmail(user.getUseDiffContactEmail());
        createUserPopup.fillInVoiceEmail(user.getVoiceEmail());
        createUserPopup.getCheckboxBusyOnBusy().click();
        user.setPermittedDestinationNumbers(createUserPopup.selectPermittedDestinationNumbers());
        createUserPopup.getCheckboxSmsEnabled().click();
        createUserPopup.getCheckboxRoleFinance().click();
        user.setCallsRecordingDirection(createUserPopup.activateCallRecordings());
        //createUserPopup.activateFaxDispatch(user.getInputLocalHeaderInfo());
        createUserPopup.getButtonSave().click();
        userPage.checkIfUserExistsInTheList(user);
    }

    public void deleteUser(User user) {
        basePage.getTabUser().click();
        userPage.deleteUserButtonClick(user.getFullName());
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        userPage.checkIfUserDeleted(user);
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
        basePage.getTabAbbreviatedDialling().click();
        abbrevDialBasePage.getTabManageAbbreviatedNumbers().click();
        manageAbbrevNumbersPage.addSingleAbbrevNumber(abbrevNum);
        waitUntilAlertDisappear();
        abbrevDialBasePage.getTabAbbreviatedNumbers().click();
        abbreviatedNumbers.checkIfAbbrevNumberExistsInList(abbrevNum);
    }

    public void deleteSingleAbbrevNumber(String abbrevNum) {
        basePage.getTabAbbreviatedDialling().click();
        abbrevDialBasePage.getTabAbbreviatedNumbers().click();
        basePage.getDropdownItemsPerPage().selectOptionContainingText("All");
        abbreviatedNumbers.deleteSingleAbbrevNumber(abbrevNum);
        confirmationPopup.getYesButton().click();
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
        popupAssignAbbrevDial.getRadioUnused().click();
        popupAssignAbbrevDial.getButtonSave().click();
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

    public void createIVR(IVRtestData ivr) {
        basePage.getTabIVRs().click();
        ivrPage.getButtonNewIvr().click();
        ivrPagePopup.getInputName().setValue(ivr.getIvrName());
        ivrPagePopup.getInputDisplayName().setValue(ivr.getIvrDisplName());
        ivrPagePopup.getDropdownLanguage().selectOptionByValue(ivr.getIvrLanguage());
        ivrPagePopup.getDropdownSelectIvrNumber().selectOption(1);
        ivr.setIvrNumber(ivrPagePopup.getDropdownSelectIvrNumber().getSelectedText());
        ivrPagePopup.getDropdownSelectAnnounc().selectOption(1);
        ivr.setIvrAnnounce(ivrPagePopup.getDropdownSelectAnnounc().getSelectedText());
        ivrPagePopup.getButtonSave().click();
        waitUntilAlertDisappear();
        ivrPage.getListName().filterBy(Condition.text(ivr.getIvrName())).shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

    public void deleteIVR(String name) {
        basePage.getTabIVRs().click();
        ivrPage.getButtonDeleteIvrByName(name).click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        ivrPage.getListName().filterBy(Condition.text(name)).shouldHave(CollectionCondition.size(0));
    }

    public void createHuntGroup(HuntGroup huntGroup){
        basePage.getTabHuntGroups().click();
        huntGroupPage.getButtonCreateNewHuntGroup().click();
        waitUntilAlertDisappear();
        createHuntGroupPopup.getInputName().setValue(huntGroup.getHuntGroupName());
        createHuntGroupPopup.getInputDisplName().setValue(huntGroup.getHuntGroupDisplayName());
        createHuntGroupPopup.getDropdownNumber().selectOption(1);
        createHuntGroupPopup.getDropdownLanguage().selectOptionByValue(huntGroup.getHuntGroupLanguage());
        createHuntGroupPopup.getButtonSubmitEditHuntGroup().click();
        createHuntGroupPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        huntGroupPage.getListNames().filterBy(Condition.text(huntGroup.getHuntGroupName())).shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

    public void deleteHuntGroup(String name){
        basePage.getTabHuntGroups().click();
        huntGroupPage.getButtonDeleteByName(name).click();
        confirmationPopup.getYesButton().click();
        waitUntilAlertDisappear();
        huntGroupPage.getListNames().filterBy(Condition.text(name)).shouldHave(CollectionCondition.size(0));
    }

    public void ivrCleanUp(List<IVRtestData> ivrList){
        basePage.getTabIVRs().click();
        waitUntilAlertDisappear();
        for (IVRtestData ivr: ivrList) {
            if (ivrPage.getListName().filterBy(Condition.text(ivr.getIvrName())).size()>0){
                deleteIVR(ivr.getIvrName());
            }
        }
    }

    public void huntGroupCleanUp(List<HuntGroup> huntGroupList){
        basePage.getTabHuntGroups().click();
        waitUntilAlertDisappear();
        for (HuntGroup huntGroup: huntGroupList) {
            if (huntGroupPage.getListNames().filterBy(Condition.text(huntGroup.getHuntGroupName())).size()>0){
                deleteHuntGroup(huntGroup.getHuntGroupName());
            }
        }
    }

    public void userCleanUp(List<User> userList){
        basePage.getTabUser().click();
        waitUntilAlertDisappear();
        for (User user: userList) {
            if (userPage.getListUserNames().filterBy(Condition.text(user.getFullName())).size()>0){
                deleteUser(user);
            }
        }
    }
}
