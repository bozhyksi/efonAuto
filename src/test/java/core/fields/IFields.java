package core.fields;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public interface IFields {

    SelenideElement field(String locator);

    ElementsCollection fields(String locator);

    SelenideElement field(String locator, int index);

    SelenideElement getChild(String parent, int id, String child);

    SelenideElement getChild(String parent, String child);

    ElementsCollection getChildrenCollection(String parent, int id, String child);

    SelenideElement getChildByParentName(String parentList, String childList, String parentName);

    SelenideElement getChildren(String parent, int id, String child, int childId);

}
