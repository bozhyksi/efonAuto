package lowLevelUserPages.queuesLowLevelUserPage;

import com.codeborne.selenide.SelenideElement;
import lowLevelUserPages.basePageLowLevelUser.BasePageLowLevelUser;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import static core.configuration.preparations.eFonApp.statusQueuePage;

public abstract class QueuesBaseUserPage extends BasePageLowLevelUser {

    protected QueuesBaseUserPage verifyIfDropDownDoesNotContainQueue(SelenideElement dropdown, String queueName){
        dropdown.click();
        Select select = new Select(dropdown);
        waitUntilAlertDisappear();
        Assert.assertEquals(select.getOptions().contains(queueName), false,
                "\nSearch dropdown should not contain queue: \""+queueName+"\"\n" +
                        "User \""+autotestUserName+"\" is not a manager of queue: \""+queueName+"\"\n");
        return this;
    }

}
