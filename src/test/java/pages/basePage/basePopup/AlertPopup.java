package pages.basePage.basePopup;

import com.codeborne.selenide.SelenideElement;

public class AlertPopup extends BasePopup {
    private String alertDialogXpath = "//div[@role=\"alertdialog\"]";

    public SelenideElement getAlertDialog() {
        return field(alertDialogXpath);
    }
}
