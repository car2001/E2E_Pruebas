package e2e.service_request.proccesManager.steps;

import helpers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class SR_step4 {
    private WebDriver driver;
    private WebDriverWait wait;
    private BasicControl basicControl;
    private JavascriptExecutor js;
    private Asserts asserts;
    private ElementSVG elementSVG;
    private SelectListItem selectListItem;
    private Actions action;
    private String step4 = "//span[contains(@id,'-count') and @class='sapMITBCount' and text()='4']";
    private String editModelerSP = "--btnEditModelerSP-img";
    private String zoomOut = "//span[contains(@id,'--btnZoomOutSP-img')]";
    private String allowReassigment = "//div[contains(@id,'--allowReassigmentSUP-handle')]";
    private String allowInsCancellation = "//div[contains(@id,'--allowInsCancellationSUP-handle')]";
    private String arrowPerformerProfile = "//span[contains(@id,'--selectPerfProfileProperty-arrow') and not(contains(@aria-hidden,'true'))]";
    private String saveModelerSp = "--btnSaveModelerSP-img";
    private ChargePopPup chargePopPup;

    public SR_step4(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.basicControl = new BasicControl(driver);
        this.elementSVG = new ElementSVG(driver);
        this.selectListItem = new SelectListItem(driver);
        this.action = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
        this.asserts = new Asserts(driver);
        this.chargePopPup = new ChargePopPup(driver);
    }


    public void step4Process(String namePP) throws InterruptedException {
        //Ingreso al paso 4
        driver.findElement(By.xpath(step4)).click();
        chargePopPup.PopPupDetail();
        FormsControl.controlTitle(driver,"Configurar Ejecutantes","Set Up Performers");

        //Editamos el paso 4
        basicControl.btnEdit(editModelerSP);
        //Reducimos el tamaño del modelo
        WebElement btnzoomOut = driver.findElement(By.xpath(zoomOut));
        action.click(btnzoomOut).click().click().perform();

        //Obtenemos el xmlview
        String xmlview = basicControl.getXmlview();

        //Definimos los user Task
        String userTask1 = "#"+xmlview+"--js-canvas-sp > div > div > svg > g > g > g > g.djs-children > g:nth-child(30) > g > rect.djs-hit.djs-hit-all";
        String userTask2 = "#"+xmlview+"--js-canvas-sp > div > div > svg > g > g > g > g.djs-children > g:nth-child(35) > g > rect.djs-hit.djs-hit-all";
        String userTask3 = "#"+xmlview+"--js-canvas-sp > div > div > svg > g > g > g > g.djs-children > g:nth-child(46) > g > rect.djs-hit.djs-hit-all";
        String userTask4 = "#"+xmlview+"--js-canvas-sp > div > div > svg > g > g > g > g.djs-children > g:nth-child(47) > g > rect.djs-hit.djs-hit-all";
        String userTask5 = "#"+xmlview+"--js-canvas-sp > div > div > svg > g > g > g > g.djs-children > g:nth-child(51) > g > rect.djs-hit.djs-hit-all";
        String userTask6 = "#"+xmlview+"--js-canvas-sp > div > div > svg > g > g > g > g.djs-children > g:nth-child(54) > g > rect.djs-hit.djs-hit-all";

        List<String> userTasks = Arrays.asList(userTask1,userTask2,userTask3,userTask4,userTask5,userTask6);

        for(String userTask : userTasks){
            elementSVG.clickSVGElementCenter(userTask);
            Thread.sleep(1000);
            driver.findElement(By.xpath(allowReassigment)).click();
            driver.findElement(By.xpath(allowInsCancellation)).click();
            driver.findElement(By.xpath(arrowPerformerProfile)).click();
            selectListItem.SelectItemDiv(namePP);
            WebElement btnAdd = driver.findElement(By.xpath("//span[contains(@id,'--addItem-img')]"));
            js.executeScript("arguments[0].scrollIntoView();",btnAdd);
            btnAdd.click();

            WebElement verticalbar = driver.findElement(By.xpath("//div[@title='Ajustar el tamaño entre el panel 1 y el panel 2' or @title='Resize between pane 1 and pane 2']"));// este el original
            action.doubleClick(verticalbar).build().perform();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//span[contains(@id,'-performerTypeSelect') and @class='sapMSltArrow']")).click();
            driver.findElement(By.xpath("//li[text()='Role' and contains(@id,'--performerTypeSelect-')]")).click();
            driver.findElement(By.xpath("//span[contains(@id,'--performerValueSelect-') and @class='sapMSltArrow']")).click();
            driver.findElement(By.xpath("//li[text()='Superadmin' and contains(@id,'--performerValueSelect-')]")).click();
            action.doubleClick(verticalbar).build().perform();
            Thread.sleep(1000);
            js.executeScript("let y = document.getElementById('"+xmlview+"--detail-cont');y.scroll(0,0)");
            driver.findElement(By.xpath("//button[@title='Rechazar' or @title = 'Decline']")).click();
            Thread.sleep(1000);
        }

        basicControl.btnSave(saveModelerSp);
        asserts.assertSave();

    }




}
