package e2e.simple_process.processManager.steps;

import helpers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class SP_step4 {

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
    private String allowReassigment = "//div[contains(@id,'--allowReassigmentSUP-handle')]";
    private String allowInsCancellation = "//div[contains(@id,'--allowInsCancellationSUP-handle')]";
    private String arrowPerformerProfile = "//span[contains(@id,'--selectPerfProfileProperty-arrow') and not(contains(@aria-hidden,'true'))]";
    private String saveModelerSp = "--btnSaveModelerSP-img";


    public SP_step4(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.basicControl = new BasicControl(driver);
        this.elementSVG = new ElementSVG(driver);
        this.selectListItem = new SelectListItem(driver);
        this.action = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
        this.asserts = new Asserts(driver);
    }

    public void step4Process() throws InterruptedException {
        //Ingreso al paso 4
        driver.findElement(By.xpath(step4)).click();
        ChargePopPup.PopPupDetail(driver,wait);
        FormsControl.controlTitle(driver,"Configurar Ejecutantes","Set Up Performers");

        //Editamos el paso 4
        basicControl.btnEdit(editModelerSP);

        //Obtenemos el xmlview
        String xmlview = basicControl.getXmlview();

        //Definimos los rect
        String mainRect = "#"+xmlview+"--js-canvas-sp > div > div > svg > g > g > g > g.djs-element.djs-shape.highlight-gray > g";
        //Definimos los user Task
        String userTask1 = "#"+xmlview+"--js-canvas-sp > div > div > svg > g > g > g > g.djs-children > g:nth-child(9) > g > rect.djs-hit.djs-hit-all";
        String userTask2 = "#"+xmlview+"--js-canvas-sp > div > div > svg > g > g > g > g.djs-children > g:nth-child(13) > g > rect.djs-hit.djs-hit-all";

        List<String> userTasks = Arrays.asList(userTask1,userTask2);
        //Definimos Performer Profile
        String date = LocalDate.now().toString(); //Fecha Actual
        String namePP = "Performer Selenium" + " " + date ;

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
