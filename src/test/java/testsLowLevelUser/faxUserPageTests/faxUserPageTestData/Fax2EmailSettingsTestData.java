package testsLowLevelUser.faxUserPageTests.faxUserPageTestData;

import flow.BaseTestMethods;
import flow.PublicEnums;

import java.util.Random;

public class Fax2EmailSettingsTestData extends BaseTestMethods {

    //<editor-fold desc="properties">
    private String number;
    private String email;
    private String faxMessageFormat;
    //</editor-fold>

    public Fax2EmailSettingsTestData(){
        this.number = "00451245789908";
        this.email = getRandomEmail();
        this.faxMessageFormat = PublicEnums.FaxReceiveFormat.getRandom();

    }

    //<editor-fold desc="get\set">
    public String getEmail() {
        return email;
    }

    public String getFaxMessageFormat() {
        return faxMessageFormat;
    }

    public String getNumber() {
        return number;
    }
    //</editor-fold>
}
