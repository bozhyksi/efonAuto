package pages.numbersPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;
import tests.IVRpageTests.IVRtestData.IVRtestData;
import tests.huntGroupPageTest.huntGroupTestData.HuntGroup;
import tests.userPageTests.userPageTestData.User;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;

public class NumbersPage extends BasePage {
    //<editor-fold desc="locators">
    private String listNumbersXpath = "//a[contains(@href,\"callto:/\")]";
    private String fieldByTextXpath = "//td[contains(text(),\"%s\")]";
    private String inputSearchXpath = "//h3[text()=\"Search\"]/../input";
    private String fieldNumberByTextXpath = "//a[contains(@href,\"%s\")]";
    //</editor-fold>

    //<editor-fold desc="get\set">


    public SelenideElement getFieldNumberByText(String text) {
        return field(String.format(fieldNumberByTextXpath,text));
    }

    public SelenideElement getInputSearch() {
        return field(inputSearchXpath);
    }

    public SelenideElement getFieldByText(String text) {
        return field(String.format(fieldByTextXpath,text));
    }

    public ElementsCollection getListNumbers() {
        return fields(listNumbersXpath);
    }
    //</editor-fold>

    public ArrayList<String> getListOfNumbers(){
        ArrayList<String> numberList = new ArrayList<>();
        for (SelenideElement elem: getListNumbers()) {
                numberList.add(elem.getText());
        }
        return numberList;
    }

    public void useSearchToFindNumber(String number){
        getInputSearch().setValue(number);
        waitUntilAlertDisappear();
        getFieldNumberByText(number).should(exist, visible);
    }

    public void verifyIfNumberInfoShowed(IVRtestData ivr){
        useSearchToFindNumber(ivr.getIvrNumber());
        getFieldByText("IVR").should(exist);
    }

    public void verifyIfNumberInfoShowed(HuntGroup huntGroup){
        useSearchToFindNumber(huntGroup.getHuntGroupNumber());
        getFieldByText("Hunt group").should(exist);
    }

    public void verifyIfNumberInfoShowed(User user){
        useSearchToFindNumber(user.getPhoneNumber());
        getFieldByText(user.getFirstName()).should(exist);
        getFieldByText(getUserAccountDetail(user.getEndDevices())).should(exist);
    }

    private String getUserAccountDetail(String account){
        int start = account.indexOf('(')+1;
        int end = account.indexOf(')');
        return account.substring(start, end);
    }
}
