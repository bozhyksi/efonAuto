package pages.basePage.basePopup;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import core.fields.Fields;

public class BasePopup extends Fields {

    //<editor-fold desc="locators">
    private String buttonSaveXpath = "//button[text()=\"Save\"]";
    private String buttonCancelXpath = "//button[text()=\"Cancel\"]";
    private String isLoadingSpinnerXpath = "//ng2-loading-spinner";
    private String alertDialogXpath = "//div[@role=\"alertdialog\"]";
    //</editor-fold>


    //<editor-fold desc="get\set">
    public SelenideElement getButtonCancel() {
        return field(buttonCancelXpath);
    }

    public SelenideElement getButtonSave() {
        return field(buttonSaveXpath);
    }

    public SelenideElement getIsLoadingSpinner() {
        return field(isLoadingSpinnerXpath);
    }

    public SelenideElement getAlertDialog() {
        return field(alertDialogXpath);
    }
    //</editor-fold>


    public void waitUntilAlertDisappear() {
        getIsLoadingSpinner().waitUntil(Condition.disappear, 10000);
        getIsLoadingSpinner().shouldNotBe(Condition.visible);
        getAlertDialog().waitUntil(Condition.disappear, 10000);
        getAlertDialog().shouldNotBe(Condition.visible);
        Selenide.sleep(500);
    }

    public void refreshPage() {
        Selenide.refresh();
        waitUntilAlertDisappear();
    }

}
