package lowLevelUserPages.voicemailLowLevelUserpage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class VoicemailUserPage extends VoicemailBaseUserPage {

    //<editor-fold desc="locators">
    private final String dropdownSelectNumberXpath = "//h3/../following-sibling::select";
    private final String fieldByTextXpath = "//td[text()=\"%s\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getDropdownSelectNumber() {
        return field(dropdownSelectNumberXpath);
    }

    public SelenideElement getFieldNoItems() {
        return field(String.format(fieldByTextXpath,"No Items"));
    }
    //</editor-fold>

    @Step("Check if Select number dropdown contains only user number")
    public VoicemailUserPage verifySelectNumberDropdownItems(){
        super.validateDropDownItems(getDropdownSelectNumber());
        return this;
    }

}
