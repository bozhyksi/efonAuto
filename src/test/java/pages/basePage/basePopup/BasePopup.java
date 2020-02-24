package pages.basePage.basePopup;

import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;

public class BasePopup extends BasePage {
    private String buttonSaveXpath = "//button[text()=\"Save\"]";
    private String buttonCancelXpath = "//button[text()=\"Cancel\"]";

    public SelenideElement getButtonCancel() {
        return field(buttonCancelXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }
}
