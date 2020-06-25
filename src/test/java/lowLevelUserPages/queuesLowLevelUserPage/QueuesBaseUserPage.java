package lowLevelUserPages.queuesLowLevelUserPage;

import com.codeborne.selenide.SelenideElement;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import static testsLowLevelUser.testData.AutotestUserData.autotestUserFullName;

public abstract class QueuesBaseUserPage extends BasePageLowLevelUser {

    protected QueuesBaseUserPage verifyIfDropDownDoesNotContainQueue(SelenideElement dropdown, String queueName){
        dropdown.click();
        Select select = new Select(dropdown);
        waitUntilAlertDisappear();
        Assert.assertEquals(select.getOptions().contains(queueName), false,
                "\nSearch dropdown should not contain queue: \""+queueName+"\"\n" +
                        "User \""+ autotestUserFullName +"\" is not a manager of queue: \""+queueName+"\"\n");
        return this;
    }

}
