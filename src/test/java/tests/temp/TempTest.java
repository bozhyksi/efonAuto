package tests.temp;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import flow.BaseTestMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import tests.queuesPageTest.queueTestData.Queue;

import java.awt.*;
import java.awt.event.InputEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import static com.codeborne.selenide.Condition.exist;
import static io.qameta.allure.Allure.step;
import static pages.basePage.BasePage.MenuTabsBasePage.QUEUES;

public class TempTest extends BaseTestMethods {

    @Test
    public void testttt() throws Exception {

        step("Log in the system");
        login();

        step("Open Queue for agents popup");
        basePage.goToMenuTab(QUEUES);
        configureQueueTab.getButtonEditAgentsQueueByText("1111").click();
        waitUntilAlertDisappear();

        step("Move agent");
        WebElement element = queueForAgentsPopup.getFieldNotSelectedAgentByName("Auto");
        WebElement element2 = queueForAgentsPopup.getFieldNotSelectedAgentByName("asdd");
        WebElement dragTo = queueForAgentsPopup.getSectionSelected();

        dragAndDropElement(element, dragTo);
        dragAndDropElement(element2, dragTo);

        queueForAgentsPopup.getButtonSave().click();
        waitUntilAlertDisappear();
        refreshPage();

        configureQueueTab.getButtonEditAgentsQueueByText("1111").click();
        waitUntilAlertDisappear();
        queueForAgentsPopup.getFieldSelectedAgentByName("asd").should(exist);
        queueForAgentsPopup.getFieldSelectedAgentByName("Auto").should(exist);
        System.out.println("");
    }

    public static void dragAndDropElement(WebElement element, WebElement dragToArea) throws Exception {
        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);
        Selenide.sleep(1000);

        // Get size of elements
        Dimension elementSize = element.getSize();
        Dimension dragToAreaSize = dragToArea.getSize();

        Point dragToAreaLocation = dragToArea.getLocation();
        Point elementLocation = element.getLocation();

        //Calculate the centers of draggable element and drop section
        int elementCenterX = elementLocation.x + elementSize.width/2;
        int elementCenterY = elementLocation.y + elementSize.height/2 + 115;

        int dragToAreaCenterX = dragToAreaLocation.x + dragToAreaSize.width/2;
        int dragToAreaCenterY = dragToAreaLocation.y + dragToAreaSize.height/2 + 115;

        //Make Mouse coordinate centre of element
        //elementLocation.x += elementSize.width/2;
        //elementLocation.y += elementSize.height/2 + 115;

        //robot.mouseMove(elementLocation.x, elementLocation.y);
        //dragToAreaLocation.x += dragToAreaSize.width/2;
        //dragToAreaLocation.y += dragToAreaSize.height/2;

        //Move mouse to drag element
        robot.mouseMove(elementCenterX, elementCenterY);

        //Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);

        //Drag events require more than one movement to register
        //Just appearing at destination doesn't work so move halfway first
        robot.mouseMove(((dragToAreaLocation.x - elementLocation.x) / 2) + elementLocation.x , ((dragToAreaLocation.y - elementLocation.y) / 2) + elementLocation.y);

        //Move to final position
        robot.mouseMove(dragToAreaCenterX, dragToAreaCenterY);
        //Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        System.out.println();
    }

}
