package tests.fileManagementPageTests.fileManagementTestData;

import flow.BaseTestMethods;

public class FileManagementTestData extends BaseTestMethods {
    private String filePath = "testData\\announcement\\2_test_mono_8000Hz_16bit.wav";
    private String fileName;

    public FileManagementTestData(){
        this.fileName = getRandomString(15);
    }

    public FileManagementTestData(String filePath){
        this.fileName = getRandomString(15);
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String rename(){
        return this.fileName = getRandomString(15);
    }
}
