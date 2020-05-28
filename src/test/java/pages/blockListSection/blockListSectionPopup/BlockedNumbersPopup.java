package pages.blockListSection.blockListSectionPopup;

import pages.basePage.BasePage;
import pages.basePage.basePopup.BasePopup;

public class BlockedNumbersPopup extends BasePopup {

    private final String buttonAddXpath = "//block-numbers-list//button[text()=\"Add\"]";
    private final String buttonDeleteXpath = "//block-numbers-list//button[text()=\"Delete\"]";
    private final String buttonEditByText = "//block-numbers-list//td[contains(text(),\"%s\")]/..//i[contains(@class,\"fa-cog\")]/..";
    private final String buttonDeleteByText = "//block-numbers-list//td[contains(text(),\"%s\")]/..//i[contains(@class,\"fa-trash \")]/..";
    private final String fieldByText = "//block-numbers-list//td[contains(text(),\"%s\")]";
    private final String buttonSaveXpath = "//block-numbers-list//button[contains(text(),\"Save\")]";
    private final String buttonCancelXpath = "//block-numbers-list//button[contains(text(),\"Cancel\")]";


}
