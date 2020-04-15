package tests.IVRpageTests.IVRtestData;

import flow.BaseTestMethods;

import java.util.ArrayList;

public class BlockListTestData extends BaseTestMethods {
    private String number;
    private String comment;
    ArrayList<BlockListTestData> blockListArray = new ArrayList<>();

    public BlockListTestData(int numberOfEntriesToCreate){
        for (int i = 0; i < numberOfEntriesToCreate; i++) {
            blockListArray.add(new BlockListTestData());
        }
    }

    public BlockListTestData(){
        this.number = getRandomPhone("00", 11);
        this.comment = getRandomString(15);
        blockListArray.add(this);
    }

    public String getNumber() {
        return number;
    }

    public String getComment() {
        return comment;
    }

    public ArrayList<BlockListTestData> getBlockListArray() {
        return blockListArray;
    }
}
