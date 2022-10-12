package e2e.service_request.proccesManager.steps;

import forms.ProcessManager.FormsRule;
import helpers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.css.Rect;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class SR_step5 {

    private WebDriver driver;
    private WebDriverWait wait;
    private BasicControl basicControl;
    private Actions action;
    private SelectListItem selectListItem;
    private ElementSVG elementSVG;
    private Asserts asserts;
    private FormsRule formsRule;
    private String step5 = "//span[contains(@id,'-count') and @class='sapMITBCount' and text()='5']";
    private String editModelerIC = "--btnEditModelerIC-img";
    private String zoomOut = "//span[contains(@id,'--btnZoomOutIC-img')]";
    private String allowDueDate = "//div[contains(@id,'--allowDueDateChangeIC-handle')]";
    private String arrowSLA = "//span[contains(@id,'--selectSLAProperty-arrow') ] [not(contains(@aria-hidden,'true'))]";
    private String addRule = "//span[contains(@id,'--btnAddRE-img')]";

    public SR_step5(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.basicControl = new BasicControl(driver);
        this.elementSVG = new ElementSVG(driver);
        this.asserts = new Asserts(driver);
        this.action = new Actions(driver);
        this.selectListItem = new SelectListItem(driver);
        this.formsRule = new FormsRule(driver);
    }

    public void step5Process(String nameSLA) throws InterruptedException {
        //Ingreso al paso 5
        driver.findElement(By.xpath(step5)).click();
        ChargePopPup.PopPupDetail(driver,wait);
        FormsControl.controlTitle(driver,"Integrar Código","Integrate Code");

        //Editamos el paso 5
        basicControl.btnEdit(editModelerIC);
        Thread.sleep(500);
        //Reducimos el tamaño del modelo
        WebElement btnzoomOut = driver.findElement(By.xpath(zoomOut));
        action.click(btnzoomOut).click().click().perform();

        //Obtenemos el xmlview
        String xmlview = basicControl.getXmlview();

        //Definimos el main rect
        String mainRect = "#"+xmlview+"--js-canvas-ic > div > div > svg > g > g > g > g.djs-element.djs-shape.highlight-gray > rect.djs-hit.djs-hit-all";
        //Definimos los user Task
        String userTask1  = "#"+xmlview+"--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(30) > g > rect.djs-hit.djs-hit-all";
        String userTask2  = "#"+xmlview+"--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(35) > g > rect.djs-hit.djs-hit-all";
        String userTask3  = "#"+xmlview+"--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(46) > g > rect.djs-hit.djs-hit-all";
        String userTask4  = "#"+xmlview+"--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(47) > g > rect.djs-hit.djs-hit-all";
        String userTask5  = "#"+xmlview+"--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(51) > g > rect.djs-hit.djs-hit-all";
        String userTask6  = "#"+xmlview+"--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(54) > g > rect.djs-hit.djs-hit-all";

        List<String> userTasks = Arrays.asList(userTask1,userTask2,userTask3,userTask4,userTask5,userTask6);

        //Definimos las reglas
        String siRequiereAprobacion = "#"+xmlview+"--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(6) > g > rect";
        String noRequiereAprobacion = "#"+xmlview+"--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(4) > g > rect";

        String siAprueba = "#"+xmlview+"--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(8) > g > rect";
        String noAprueba = "#"+xmlview+"--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(13) > g > rect";

        String siRequiereEjecutor2 = "#"+xmlview+"--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(19) > g > rect";
        String noRequiereEjecutor2 = "#"+xmlview+"--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(20) > g > rect";

        String siRequiereEjecutor3 = "#"+xmlview+"--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(24) > g > rect";
        String noRequiereEjecutor3 = "#"+xmlview+"--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(25) > g > rect";

        String siObservaciones = "#"+xmlview+"--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(9) > g > rect";
        String noObservaciones = "#"+xmlview+"--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(28) > g > rect";

        //Asignamos los valores al UserTask

        for(String userTask : userTasks){
            elementSVG.clickSVGElementCenter(userTask);
            Thread.sleep(1000);
            driver.findElement(By.xpath(allowDueDate)).click();
            driver.findElement(By.xpath(arrowSLA)).click();
            selectListItem.SelectItemDiv(nameSLA);
            driver.findElement(By.xpath("//input[contains(@id,'--numberOfDays-inner')]")).click();
            driver.findElement(By.xpath("//input[contains(@id,'--numberOfDays-inner')]")).sendKeys("5");
            driver.findElement(By.xpath("//button[@title='Rechazar' or @title = 'Decline']")).click();
            Thread.sleep(1000);
        }

        // Asignamos los valores de las reglas
        elementSVG.clickSVGElementCenter(siRequiereAprobacion);
        Thread.sleep(1000);
        driver.findElement(By.xpath(addRule)).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        formsRule.createRule("siRequiereAprobación","",);




        //elementSVG.clickSVGElementRule(noRequiereAprobacion,200,-20);
        //elementSVG.clickSVGElementRule(siAprueba,49,0);
        //elementSVG.clickSVGElementRule(noAprueba,-7,0);
        //elementSVG.clickSVGElementRule(siRequiereEjecutor2,10,0);
        // elementSVG.clickSVGElementRule(noRequiereEjecutor2,54,0);
        //elementSVG.clickSVGElementRule(siRequiereEjecutor3,-7,0);
        //elementSVG.clickSVGElementRule(noRequiereEjecutor3,54,0);
        //elementSVG.clickSVGElementRule(siObservaciones,153,0);
        //elementSVG.clickSVGElementRule(noObservaciones,10,0);

    }

    public static void main(String[] args) {
        /*WebElement task = driver.findElement(By.cssSelector(noObservaciones));
        Rectangle task1 = task.getRect();
        task1.getHeight();
        task1.getWidth();

        System.out.println(task1.getHeight() + " " + task1.getWidth());*/
    }

}
