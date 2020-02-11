package flow;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import core.configuration.preparations.eFonApp;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDiallingTestData;
import tests.phonebookPageTests.phonebookPageTestData.Phonebook;
import tests.userPageTests.userPageTestData.User;

import java.util.Random;

public class BaseTestMethods extends eFonApp {

    public void waitUntilAlertDisappear(){
        alertPopup.getAlertDialog().waitUntil(Condition.disappear,10000);
        alertPopup.getAlertDialog().shouldNotBe(Condition.visible);
        Selenide.sleep(1000);
    }

    public void refreshPage(){
        Selenide.refresh();
    }

    public String getRandomEmail() {
        return "L"+getRandomString(8)+"@email.com";
    }

    public String getRandomPassword(){
        return "L"+getRandomString(5)+"123!!!+++";
    }

    public String getRandomString(int length){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }
        return String.valueOf(sb);
    }

    public String getRandomNumber(int length){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) (random.nextInt(8) + '1'));
        }
        return String.valueOf(sb);
    }

    public String getRandomNumber(int min, int max){
        Random r = new Random();
        return String.valueOf(r.nextInt((max - min) + 1) + min);
    }

    public String getRandomPhone(String prefix){
        if (prefix.equals("+")) return prefix + getRandomNumber(11);
        else return "00"+getRandomNumber(11);
    }

    public String getRandomPhone(){
        return "00"+getRandomNumber(11);
    }

    public void login() {
        loginPage.fillInLogin(getLogin());
        loginPage.fillInPassword(getPassword());
        loginPage.getButtonLogin().click();
    }

    public void createUser(User user){
        basePage.getTabUser().click();
        userPage.getPageTitle().getText().equals("User");
        userPage.getButtonCreateNewUser().click();
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

    public void deleteUser(User user){
        basePage.getTabUser().click();
        userPage.deleteUserButtonClick(user.getFullName());
        confirmationPopup.getYesButton().click();
        userPage.checkIfUserDeleted(user);
    }

    public void uploadPhoneBook(int numberOfEntriesInFile){
        Phonebook phonebook = new Phonebook(numberOfEntriesInFile);
        phonebook.createExcelPhonebookFile();
        basePage.getTabPhonebook().click();
        phonebookPage.validatePageTitle("Phonebook");
        phonebookPage.uploadFile(phonebook.getfileName());
        phonebookPage.validateUploadedNumbers(numberOfEntriesInFile);
        excelFileWorker.deleteFile(phonebook.getfileName());;
    }

    public void deletePhonebook(){
        basePage.getTabPhonebook().click();
        waitUntilAlertDisappear();
        phonebookPage.getButtonDeletePhoneBook().click();
        confirmationPopup.getYesButton().click();
        phonebookPage.checkIfPhonebookWasDeleted();
    }

    public void addSingleAbbrevNumber(String abbrevNum){
        basePage.getTabAbbreviatedDialling().click();
        abbrevDialBasePage.getTabManageAbbreviatedNumbers().click();
        manageAbbrevNumbersPage.addSingleAbbrevNumber(abbrevNum);
        waitUntilAlertDisappear();
        abbrevDialBasePage.getTabAbbreviatedNumbers().click();
        abbreviatedNumbers.checkIfAbbrevNumberExistsInList(abbrevNum);
    }

    public void deleteSingleAbbrevNumber(String abbrevNum){
        basePage.getTabAbbreviatedDialling().click();
        abbrevDialBasePage.getTabAbbreviatedNumbers().click();
        basePage.getDropdownItemsPerPage().selectOptionContainingText("All");
        abbreviatedNumbers.deleteSingleAbbrevNumber(abbrevNum);
        confirmationPopup.getYesButton().click();
        abbreviatedNumbers.checkIfAbbrevNumberDoesNotExistInList(abbrevNum);
    }

    public void deleteAllAbbrevNumbers(){
        String data;
        basePage.getTabAbbreviatedDialling().click();
        abbrevDialBasePage.getTabAbbreviatedNumbers().click();
        basePage.getDropdownItemsPerPage().selectOptionContainingText("All");
        while (abbreviatedNumbers.getListNo().size() > 0) {
            data = abbreviatedNumbers.getListNo().get(0).getText();
            if (data.equals("No Items")) break;
            if (!abbreviatedNumbers.getButtonDeleteByNum(data).exists()){
                makeAbbrevNumberUnused(data);
                basePage.getTabUser().click();//temporary overcome because of bug
            }
            deleteSingleAbbrevNumber(data);
        }
    }

    public void createAbbrevNumberRange(AbbreviatedDiallingTestData obj){
        basePage.getTabAbbreviatedDialling().click();
        abbrevDialBasePage.getTabManageAbbreviatedNumbers().click();
        manageAbbrevNumbersPage.addRangeAbbrevNumber(obj.getFromNumber(), obj.getUntilNumber());
        abbrevDialBasePage.getTabAbbreviatedNumbers().click();
        abbreviatedNumbers.checkIfAbbrevNumberRangeCreated(obj);
    }

    public void makeAbbrevNumberUnused(String shortNumber){
        abbreviatedNumbers.editSingleAbbrevNumber(shortNumber);
        popupAssignAbbrevDial.getRadioUnused().click();
        popupAssignAbbrevDial.getButtonSave().click();
    }

    public void configFaxForNewUser(User user){
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

    public void createCallPickUpGroup(String groupName, String abbrevNum){
        basePage.getTabCallPickUps().click();
        callPickUpPage.getButtonNewGroup().click();
        groupCallPickupPopup.getInputName().setValue(groupName);
        groupCallPickupPopup.selectAbbrenNumber(abbrevNum);
        groupCallPickupPopup.getDropdownSelectAccounts().selectOption(1);
        groupCallPickupPopup.getButtonSave().click();

        callPickUpPage.getListName().filterBy(Condition.text(groupName)).shouldHave(CollectionCondition.size(1));
        callPickUpPage.getListAbbrevDial().filterBy(Condition.text(abbrevNum)).shouldHave(CollectionCondition.size(1));
    }

    public void deleteCallPickUpGroup(String groupName){
        callPickUpPage.deletePickUpGroup(groupName);
        confirmationPopup.getYesButton().click();
        callPickUpPage.getListName().filterBy(Condition.text(groupName)).shouldHave(CollectionCondition.size(0));
    }
}
