package flow;

import com.codeborne.selenide.Selenide;
import core.configuration.preparations.eFonApp;
import tests.abbreviatedDialPageTest.abbrevNumTestData.AbbreviatedDiallingTestData;
import tests.phonebookPageTests.phonebookPageTestData.Phonebook;
import tests.userPageTests.userPageTestData.User;

import java.util.Random;

public class BaseTestMethods extends eFonApp {

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
        phonebookPage.getButtonDeletePhoneBook().click();
        confirmationPopup.getYesButton().click();
        phonebookPage.checkIfPhonebookWasDeleted();
    }

    public void addSingleAbbrevNumber(String abbrevNum){
        basePage.getTabAbbreviatedDialling().click();
        abbrevDialBasePage.getTabManageAbbreviatedNumbers().click();
        manageAbbrevNumbersPage.addSingleAbbrevNumber(abbrevNum);
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
}
