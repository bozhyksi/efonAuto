package lowLevelUserPages.sendSmsPageLowLevelUser.sendSmsUserPagePopup;

import com.codeborne.selenide.SelenideElement;
import core.workers.dbWorker.DataBaseWorker;
import lowLevelUserPages.sendSmsPageLowLevelUser.ManageSenderNumbersAndNamesUserPage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivateAuthorisationCodePopup extends ManageSenderNumbersAndNamesUserPage {
    private String inputAuthorizationCodeXpath = "//input[@formcontrolname=\"code\"]";

    @Override
    public SelenideElement getButtonSave() {
        return super.getButtonSave();
    }

    public SelenideElement getInputAuthorizationCode() {
        return field(inputAuthorizationCodeXpath);
    }

    public String getAuthorizationCode(String number) {
        String code = null;
        String query = String.format("SELECT authorization_code " +
                "FROM webadmin_20170426.sms_authorized_number " +
                "where customer_fk=906645 and sender_number= %s ", number);

        DataBaseWorker dataBaseWorker = new DataBaseWorker();

        ResultSet rs = dataBaseWorker.execSqlQuery(query);
        while (true){
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                code = rs.getString("authorization_code");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return code;
    }

    public void enterAuthorizationCode(String number){
        getInputAuthorizationCode().setValue(getAuthorizationCode(number));
    }


}
