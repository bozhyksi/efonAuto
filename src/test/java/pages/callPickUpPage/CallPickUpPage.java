package pages.callPickUpPage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;

public class CallPickUpPage extends BasePage {
    private String buttonNewGroupXpath = "//a[text()=\"New Group\"]";
    private String listNameXpath = "//table[@role=\"grid\"]//td[1]";
    private String listAbbrevDialXpath = "//table[@role=\"grid\"]//td[2]";
    private String listButtonEditXpath =  "//table[@role=\"grid\"]//td[3]//a[1]";
    private String listButtonDeleteXpath =  "//table[@role=\"grid\"]//td[3]//a[2]";

    public SelenideElement getButtonNewGroup() {
        return field(buttonNewGroupXpath);
    }

    public ElementsCollection getListName() {
        return fields(listNameXpath);
    }

    public ElementsCollection getListAbbrevDial() {
        return fields(listAbbrevDialXpath);
    }

    public ElementsCollection getListButtonEdit() {
        return fields(listButtonEditXpath);
    }

    public ElementsCollection getListButtonDelete() {
        return fields(listButtonDeleteXpath);
    }

    public void deletePickUpGroup(String groupName){
        getChildByParentName(getListName(), getListButtonDelete(), groupName).click();
    }
}
