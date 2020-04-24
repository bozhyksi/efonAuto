package pages.numbersPage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;

import java.util.ArrayList;

import static pages.basePage.BasePage.MenuTabsBasePage.NUMBERS;

public class NumbersPage extends BasePage {
    public String listNumbersXpath = "//a[contains(@href,\"callto:/\")]";

    public ElementsCollection getListNumbers() {
        return fields(listNumbersXpath);
    }

    public ArrayList<String> getListOfNumbers(){
        ArrayList<String> numberList = new ArrayList<>();
        for (SelenideElement elem: getListNumbers()) {
                numberList.add(elem.getText());
        }
        return numberList;
    }
}
