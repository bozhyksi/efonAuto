package tests.blockListSectionTests.blockListTestData;

import flow.BaseTestMethods;

public class BlockListTestData extends BaseTestMethods {
    //<editor-fold desc="properties">
    private String blockedNumber;
    private String permittedNumber;
    private String comment;
    //</editor-fold>

    public BlockListTestData(){
        this.blockedNumber = getRandomPhone("00",10);
        this.permittedNumber = getRandomPhone("00",10);
        this.comment = getRandomString(15);
    }

    //<editor-fold desc="get\set">
    public String getPermittedNumber() {
        return permittedNumber;
    }

    public String getComment() {
        return comment;
    }

    public String getBlockedNumber() {
        return blockedNumber;
    }

    public String changeBlockedNumber(){
        return this.blockedNumber = getRandomPhone("00",10);
    }
    //</editor-fold>
}
