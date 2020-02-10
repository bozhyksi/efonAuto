package pages.basePage.basePopup;

import com.codeborne.selenide.SelenideElement;
import pages.basePage.BasePage;

public class AlertPopup extends BasePage {
    private String alertDialogXpath = "//div[@role=\"alertdialog\"]";

    public SelenideElement getAlertDialog() {
        return field(alertDialogXpath);
    }
}
