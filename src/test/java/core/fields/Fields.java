package core.fields;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

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
                return childList.get(i);
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
}
