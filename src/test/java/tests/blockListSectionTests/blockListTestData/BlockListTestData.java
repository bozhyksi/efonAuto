package tests.blockListSectionTests.blockListTestData;

import flow.BaseTestMethods;

public class BlockListTestData extends BaseTestMethods {
    private String blockedNumber;
    private String comment;

    public BlockListTestData(){
        this.blockedNumber = getRandomPhone("00",10);
        this.comment = getRandomString(15);
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
}
