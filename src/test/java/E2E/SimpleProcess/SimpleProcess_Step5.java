package E2E.SimpleProcess;

import Helpers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class SimpleProcess_Step5 {

    private WebDriver driver;
    private WebDriverWait wait;
    private BasicControl basicControl;
    private SelectListItem selectListItem;
    private ElementSVG elementSVG;
    private Asserts asserts;
    private String step5 = "//span[contains(@id,'-count') and @class='sapMITBCount' and text()='5']";
    private String editModelerIC = "--btnEditModelerIC-img";
    private String allowDueDate = "//div[contains(@id,'--allowDueDateChangeIC-handle')]";
    private String arrowSLA = "//span[contains(@id,'--selectSLAProperty-arrow') ] [not(contains(@aria-hidden,'true'))]";

    public SimpleProcess_Step5(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.basicControl = new BasicControl(driver);
        this.elementSVG = new ElementSVG(driver);
        this.asserts = new Asserts(driver);
        this.selectListItem = new SelectListItem(driver);
    }

    public void step5Process() throws InterruptedException {
        //Ingreso al paso 5
        driver.findElement(By.xpath(step5)).click();
        ChargePopPup.PopPupDetail(driver,wait);
        FormsControl.controlTitle(driver,"Integrar Código","Integrate Code");

        //Editamos el paso 5
        basicControl.btnEdit(editModelerIC);

        //Obtenemos el xmlview
        String xmlview = basicControl.getXmlview();

        //Definimos los rect
        String mainRect ="#"+xmlview+"--js-canvas-ic > div > div > svg > g > g > g > g.djs-element.djs-shape.highlight-gray > g";

        //Definimos los user Task
        String userTask1 = "#"+xmlview+"--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(9) > g > rect.djs-hit.djs-hit-all";
        String userTask2 = "#"+xmlview+"--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(13) > g > rect.djs-hit.djs-hit-all";

        List<String> userTasks = Arrays.asList(userTask1,userTask2);

        //Definimos las reglas
        String reglaSi = "#"+xmlview+"--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(4) > g > polyline";
        String reglaNo = "#"+xmlview+"--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(2) > g > polyline";

        String date = LocalDate.now().toString(); //Fecha Actual
        String nameSLA = "SLA Selenium" + " " + date ;

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


        elementSVG.clickSVGElementCenter(reglaSi);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(@id,'--btnAddRE-img')]")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        driver.findElement(By.xpath("//input[contains(@id,'--ruleName-inner')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--ruleName-inner')]")).sendKeys("Regla SI");
        driver.findElement(By.xpath("//input[contains(@id,'--ruleDisplayName-inner')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--ruleDisplayName-inner')]")).sendKeys("Regla SI");
        driver.findElement(By.xpath("//button[(@aria-label='Agregar' or @aria-label='Add') and @class='sapMBtnBase sapMBtn sapMBarChild']")).click();
        List<WebElement> arrow = driver.findElements(By.cssSelector(".sapMSltArrow"));
        arrow.get(0).click();
        selectListItem.SelectItemLi("Data Model Attribute");
        driver.findElements(By.xpath("//span[(@aria-label='Mostrar ayuda para entradas' or @aria-label='Show Value Help') and contains(@id,'-vhi') ]")).get(0).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        driver.findElement(By.xpath("//span[contains(@id,'--treeDM-rows-row0') and (@title='Expandir nodos' or @title='Expand Node')]")).click();
        driver.findElement(By.xpath("//span[text()='¿Acepta? : Boolean']")).click();
        Thread.sleep(800);
        arrow = driver.findElements(By.cssSelector(".sapMSltArrow"));
        arrow.get(1).click();
        Thread.sleep(800);
        selectListItem.SelectItemLi("Is True");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//bdi[text()='Guardar' or text()='Save']")).click();
        ChargePopPup.PopPupGeneral(driver,wait);

        //Creamos regla NO
        elementSVG.clickSVGElementRule(reglaNo,-40,0);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(@id,'--btnAddRE-img')]")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        driver.findElement(By.xpath("//input[contains(@id,'--ruleName-inner')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--ruleName-inner')]")).sendKeys("Regla NO");
        driver.findElement(By.xpath("//input[contains(@id,'--ruleDisplayName-inner')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--ruleDisplayName-inner')]")).sendKeys("Regla NO");
        driver.findElement(By.xpath("//button[(@aria-label='Agregar' or @aria-label='Add') and @class='sapMBtnBase sapMBtn sapMBarChild']")).click();
        arrow = driver.findElements(By.cssSelector(".sapMSltArrow"));
        arrow.get(0).click();
        selectListItem.SelectItemLi("Data Model Attribute");
        driver.findElements(By.xpath("//span[(@aria-label='Mostrar ayuda para entradas' or @aria-label='Show Value Help') and contains(@id,'-vhi') ]")).get(0).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        driver.findElement(By.xpath("//span[contains(@id,'--treeDM-rows-row0') and (@title='Expandir nodos' or @title='Expand Node')]")).click();
        driver.findElement(By.xpath("//span[text()='¿Acepta? : Boolean']")).click();
        arrow = driver.findElements(By.cssSelector(".sapMSltArrow"));
        arrow.get(1).click();
        Thread.sleep(800);
        selectListItem.SelectItemLi("Is False");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//bdi[text()='Guardar' or text()='Save']")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        driver.findElement(By.xpath("//button[@title='Rechazar' or @title = 'Decline']")).click();


        basicControl.btnSave("--btnSaveModelerIC-img");
        //Guardamos cambios
        asserts.assertSave();

    }

}
