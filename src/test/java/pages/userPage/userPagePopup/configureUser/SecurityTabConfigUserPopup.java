package pages.userPage.userPagePopup.configureUser;

import flow.PublicEnums;
import io.qameta.allure.Step;

public class SecurityTabConfigUserPopup extends ConfigureUserBasePopup {

    @Step("Click edit ActiveRoles")
    public SecurityTabConfigUserPopup clickEditActiveRoles(){
        field("//label[text()=\"Active roles\"]/..//a").click();
        return this;
    }

    @Step("Select Role")
    public SecurityTabConfigUserPopup selectRole(PublicEnums.Roles role){
        field("//label[text()=\"Possible Roles\"]/..//select")
                .selectOptionByValue(role.getRole());
        return this;
    }

    @Step ("Save")
    public SecurityTabConfigUserPopup save(){
        super.getButtonSave().click();
        waitUntilAlertDisappear();
        return this;
    }
}
