package pages.fileManagementPage;

import com.codeborne.selenide.SelenideElement;

public class MusicOnHoldPage extends FileManagementBasePage {
    //<editor-fold desc="Locators">
    private String pageTitleXpath = "//music-on-hold//h1";
    private String fieldNameByTextXpath = "(//music-on-hold//table)[2]//td[2]//div[text()[contains(.,\"%s\")]]";
    private String buttonEditByNameXpath = "(//music-on-hold//table)[2]//td[2]//div[text()[contains(.,\"%s\")]]/parent::td/following-sibling::td/i[2]";
    private String buttonDeleteByNameXpath = "(//music-on-hold//table)[2]//td[2]//div[text()[contains(.,\"%s\")]]/parent::td/following-sibling::td/i[3]";
    //</editor-fold>

    //<editor-fold desc="get\set">
    public SelenideElement getPageTitle() {
        return field(pageTitleXpath);
    }

    public SelenideElement getFieldNameByText(String name) {
        return field(String.format(fieldNameByTextXpath,name));
    }

    public SelenideElement getButtonEditByName(String name) {
        return field(String.format(buttonEditByNameXpath,name));
    }

    public SelenideElement getButtonDeleteByName(String name) {
        return field(String.format(buttonDeleteByNameXpath, name));
    }
    //</editor-fold>
}
