package testsLowLevelUser.faxUserPageTests.faxUserPageTestData;

import flow.BaseTestMethods;

import java.util.Random;

public class Fax2EmailSettingsTestData extends BaseTestMethods {

    public enum FaxMessageFormat{
        TIFF_Only("TIFF only"),
        PDF_Only("PDF only"),
        TIFF_and_PDF("TIFF and PDF");

        private String format;

        FaxMessageFormat(String format){
            this.format = format;
        }

        private static FaxMessageFormat rand(){
            Random rand = new Random();
            return values()[rand.nextInt(values().length)];
        }

        public static String getRandomFormat(){
            return rand().format;
        }
    }

    private String number = "00451245789906";
    private String email = getRandomEmail();
    private String faxMessageFormat = FaxMessageFormat.getRandomFormat();

    public String getEmail() {
        return email;
    }

    public String getFaxMessageFormat() {
        return faxMessageFormat;
    }

    public String getNumber() {
        return number;
    }
}
