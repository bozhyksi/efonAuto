package pages.queuesPage;

import com.codeborne.selenide.SelenideElement;

public class ConfigureQueueTab extends QueuesBasePage {
    //<editor-fold desc="Locators">
    private String dropdownAbbrevDialingXpath = "//select[@formcontrolname=\"abbreviatedDial\"]";
    private String buttonEditAbbrevDialXpath = "//abbreviated-dialling-queue//i[@id=\"abbreviatedDiallingConfig\"]";
    private String buttonSaveAbbrevDialXpath = "//abbreviated-dialling-queue//button[text()='Save']";
    private String buttonCancelAbbrevDialXpath = "//abbreviated-dialling-queue//button[text()='Cancel']";
    private String buttonCreateNewQueueXpath = "//a[text()=\"Create new\"]";
    private String buttonDeleteQueueByNameXpath = "//table//td[text()=\"%s\"][1]/ancestor::tr//a[@id=\"deleteQueue\"]";
    private String buttonEditQueueByNameXpath = "//table//td[text()=\"%s\"][1]/ancestor::tr//a[@id=\"editQueue\"]";
    private String fieldQueueNameByTextXpath = "//table//td[text()=\"%s\"][1]";
    private String fieldQueueManagerByTextXpath = "//table//td[text()=\"%s\"][4]";
    private String buttonEditAgentsQueueXpath = "//table//td[text()=\"%s\"][1]/ancestor::tr//a[@id=\"editAgentsQueue\"]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getButtonDeleteQueueByName(String name) {
        return field(String.format(buttonDeleteQueueByNameXpath,name));
    }

    public SelenideElement getDropdownAbbrevDialing() {
        return field(dropdownAbbrevDialingXpath);
    }

    public SelenideElement getButtonEditAbbrevDial() {
        return field(buttonEditAbbrevDialXpath);
    }

    public SelenideElement getButtonSaveAbbrevDial() {
        return field(buttonSaveAbbrevDialXpath);
    }

    public SelenideElement getButtonCancelAbbrevDial() {
        return field(buttonCancelAbbrevDialXpath);
    }

    public SelenideElement getButtonCreateNewQueue() {
        return field(buttonCreateNewQueueXpath);
    }

    public SelenideElement getButtonEditQueueByName() {
        return field(buttonEditQueueByNameXpath);
    }

    public SelenideElement getFieldQueueNameByText(String txt) {
        return field(String.format(fieldQueueNameByTextXpath,txt));
    }

    public SelenideElement getFieldQueueManagerByText(String txt) {
        return field(String.format(fieldQueueManagerByTextXpath,txt));
    }

    public SelenideElement getButtonEditAgentsQueue() {
        return field(buttonEditAgentsQueueXpath);
    }
    //</editor-fold>
}
