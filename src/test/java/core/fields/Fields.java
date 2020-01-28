package core.fields;

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

    public SelenideElement getChildren(String parent, int id, String child) {
        return null;
    }

    public SelenideElement getChildren(String parent, int id, String child, int childId) {
        locator = parent + "[" + id + "]" + child;
        return $$(By.xpath(locator)).get(childId);
    }
}
