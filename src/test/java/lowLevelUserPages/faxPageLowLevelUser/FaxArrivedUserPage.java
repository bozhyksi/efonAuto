package lowLevelUserPages.faxPageLowLevelUser;

import io.qameta.allure.Step;

public class FaxArrivedUserPage extends FaxesBaseUserPage{

    public FaxArrivedUserPage selectNumberFromSearchDropdown(String number){
        super.selectNumberFromSearchDropdown(number);
        return this;
    }

    public FaxArrivedUserPage validateNumberSearchDropDownItems(){
        super.validateNumberSearchDropDownItems();
        return this;
    }
}
