package core.fields;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Fields implements IFields {

    private String locator = null;

    public SelenideElement field(String locator) {
        return $(By.xpath(locator));
    }

    public ElementsCollection fields(String locator) {
        return $$(By.xpath(locator));
    }

    public SelenideElement field(String locator, int index) {
        return $$(By.xpath(locator)).get(index);
    }

    public SelenideElement getChild(String parent, int id, String child) {
        locator = parent + "[" + id + "]" + child;
        return $(By.xpath(locator));
    }

    public SelenideElement getChild(String parent, String child) {
        locator = parent + child;
        return $(By.xpath(locator));
    }

    public ElementsCollection getChildrenCollection(String parent, int id, String child) {
        locator = parent + "[" + id + "]" + child;
        return $$(By.xpath(locator));
    }

    public SelenideElement getChildByParentName(String parentList, String childList, String parentName) {
        ElementsCollection list1 = fields(parentList);
        ElementsCollection list2 = fields(childList);

        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).text().equals(parentName)) {
                return list2.get(i);
            }
        }
        return null;
    }

    public SelenideElement getChildByParentName(ElementsCollection parentList, ElementsCollection childList, String parentName) {
        parentList.shouldHave(CollectionCondition.sizeGreaterThan(0));
        childList.shouldHave(CollectionCondition.sizeGreaterThan(0));

        for (int i = 0; i < parentList.size(); i++) {
            if (parentList.get(i).text().contains(parentName)) {
                return childList.get(i).waitUntil(Condition.visible,15000).waitUntil(Condition.appears,15000);
            }
        }
        try {
            throw new NullPointerException();
        } catch (NullPointerException e) {
            System.out.println("===No element found===");
            e.printStackTrace();
            return null;
        }
    }

    public SelenideElement getChildren(String parent, int id, String child, int childId) {
        locator = parent + "[" + id + "]" + child;
        return $$(By.xpath(locator)).get(childId);
    }

    public int getRandomDropDownOption(String xpath){
        Select obj = new Select(WebDriverRunner.getWebDriver().findElement(By.xpath(xpath)));
        int size = obj.getOptions().size();
        Random random = new Random();
        return random.nextInt(size);
    }

    public void getRandomDropDownOption(SelenideElement dd){
        Select obj = new Select(dd);
        int size = obj.getOptions().size();
        //Random random = new Random();
        //dd.selectOption(random.nextInt(size));
        dd.selectOption(getRandomNumber(1,size-1));
    }

    public int getRandomNumber(int min, int max) {
        Random r = new Random();
        int res = r.nextInt((max - min) + 1) + min;
        return res;
    }
}
