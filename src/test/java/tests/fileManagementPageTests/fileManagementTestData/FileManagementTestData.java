package tests.fileManagementPageTests.fileManagementTestData;

import flow.BaseTestMethods;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FileManagementTestData extends BaseTestMethods {

    //<editor-fold desc="properties">
    private String destinationPath = "/var/spool/asterisk/voicemail/default/00451245789908/";
    private String sourcePath = "testData\\announcement\\new.wav";

    private String filePath = "testData\\announcement\\2_test_mono_8000Hz_16bit.wav";
    private String fileName;
    //</editor-fold>

    public FileManagementTestData(){
        this.fileName = getRandomString(15);
    }

    public FileManagementTestData(String filePath){
        this.fileName = getRandomString(15);
        this.filePath = filePath;
    }

    //<editor-fold desc="get\set">
    public String getSourcePath() {
        return sourcePath;
    }

    public String getDestinationPath() {
        return destinationPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }
    //</editor-fold>

    public String rename(){
        return this.fileName = getRandomString(15);
    }

    public String getId(){
        String query = "SELECT * FROM webadmin_20170426.announcement where display_name = \"%s\"";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(String.format(query,getFileName()));
        while (true){
            try {
                if (!resultSet.next()) break;
                return resultSet.getString(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public String getMohId(){
        String query = "SELECT * FROM webadmin_20170426.moh where display_name = \"%s\";";
        ResultSet resultSet = dataBaseWorker.execSqlQuery(String.format(query,getFileName()));
        while (true){
            try {
                if (!resultSet.next()) break;
                return resultSet.getString(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
