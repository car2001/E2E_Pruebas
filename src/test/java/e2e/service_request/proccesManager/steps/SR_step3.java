package e2e.service_request.proccesManager.steps;

import forms.ProcessManager.FormsActivityForm;
import helpers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class SR_step3 {
    private WebDriver driver;
    private WebDriverWait wait;
    private BasicControl basicControl;
    private Actions action;
    private ElementSVG elementSVG;
    private FormsActivityForm formsAF;
    private String step3 = "//span[contains(@id,'-count') and @class='sapMITBCount' and text()='3']";
    private String editModelerFB = "--btnEditModelerFB-inner";
    private String addACTF = "//span[contains(@id,'--btnAddACTF-img')]";
    private String zoomOut = "//span[contains(@id,'--btnZoomOutFB-img')]";

    public SR_step3(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        this.basicControl = new BasicControl(driver);
        this.action = new Actions(driver);
        this.elementSVG = new ElementSVG(driver);
        this.formsAF = new FormsActivityForm(driver);
    }


    public void step3Process()  throws InterruptedException, AWTException {

        //Ingreso al paso 3
        driver.findElement(By.xpath(step3)).click();
        ChargePopPup.PopPupDetail(driver,wait);
        FormsControl.controlTitle(driver,"Asignar Formularios","Assign Forms");
        //Editamos el paso 3
        basicControl.btnEdit(editModelerFB);
        //Reducimos el tamaño del modelo
        WebElement btnzoomOut = driver.findElement(By.xpath(zoomOut));
        action.click(btnzoomOut).click().click().perform();

        //Ingresamos a la actividad 1
        String xmlview = basicControl.getXmlview();

        //Definimos los rect
        String mainRect = "#"+xmlview+"--js-canvas-fb > div > div > svg > g > g > g > g.djs-element.djs-shape.highlight-gray > rect.djs-hit.djs-hit-all";
        //Definimos los user Task
        String userTask1 = "#"+xmlview+"--js-canvas-fb > div > div > svg > g > g > g > g.djs-children > g:nth-child(30) > g > rect.djs-hit.djs-hit-all";
        String userTask2 = "#"+xmlview+"--js-canvas-fb > div > div > svg > g > g > g > g.djs-children > g:nth-child(35) > g > rect.djs-hit.djs-hit-all";
        String userTask3 = "#"+xmlview+"--js-canvas-fb > div > div > svg > g > g > g > g.djs-children > g:nth-child(46) > g > rect.djs-hit.djs-hit-all";
        String userTask4 = "#"+xmlview+"--js-canvas-fb > div > div > svg > g > g > g > g.djs-children > g:nth-child(47) > g > rect.djs-hit.djs-hit-all";
        String userTask5 = "#"+xmlview+"--js-canvas-fb > div > div > svg > g > g > g > g.djs-children > g:nth-child(51) > g > rect.djs-hit.djs-hit-all";
        String userTask6 = "#"+xmlview+"--js-canvas-fb > div > div > svg > g > g > g > g.djs-children > g:nth-child(54) > g > rect.djs-hit.djs-hit-all";

        List<String> userTasks = Arrays.asList(userTask2,userTask3,userTask4,userTask5,userTask6);

        //Definimos send Task
        String sendTask1 = "#"+xmlview+"--js-canvas-fb > div > div > svg > g > g > g > g.djs-children > g:nth-child(38) > g > rect.djs-hit.djs-hit-all";
        String sendTask2 = "#"+xmlview+"--js-canvas-fb > div > div > svg > g > g > g > g.djs-children > g:nth-child(55) > g > rect.djs-hit.djs-hit-all";

        List<String> sendTasks = Arrays.asList(sendTask1,sendTask2);


        //Click en el primer userTask1
        elementSVG.clickSVGElementCenter(userTask1);
        //Click para crear el Activity Form
        driver.findElement(By.xpath(addACTF)).click();
        formsAF.createNewActivityForm("AF-SERVICE REQUEST");
        ChargePopPup.PopPupMain(driver,wait);
        formsAF.panelActivityForm();

        //Ingresamos a todos los User Task

        for ( String userTask : userTasks ){
            elementSVG.clickSVGElementCenter(userTask);
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[contains(@id,'--cbActivityForm-inner')]")).click();
            driver.findElement(By.xpath("//input[contains(@id,'--cbActivityForm-inner')]")).sendKeys("AF-SERVICE REQUEST");
            driver.findElement(By.xpath("//input[contains(@id,'--cbActivityForm-inner')]")).sendKeys(Keys.TAB);
            driver.findElement(By.xpath("//button[@title='Rechazar' or @title = 'Decline' ]")).click();

            Thread.sleep(1000);
        }

        //Ingresamos a los Send Task

/*
        //Ingresamos a la actividad 3
        String task3 = "#__xmlview4--js-canvas-fb > div > div > svg > g > g > g > g.djs-children > g:nth-child(16) > g > rect.djs-outline";
        elementSVG.clickSVGElementCenter(task3);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(@id,'--btnEmailDefinition-inner')]")).click();
        WebElement popEmailDefinition = driver.findElement(By.xpath("//span[text()='Editor de correo electrónico' or text()='Email Editor']"));
        wait.until(ExpectedConditions.visibilityOf(popEmailDefinition));
        driver.findElement(By.xpath("//input[contains(@id,'--miTO-inner')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--miTO-inner')]")).sendKeys("role: Superadmin");
        driver.findElement(By.xpath("//input[contains(@id,'--miTO-inner')]")).sendKeys(Keys.TAB);
        driver.findElement(By.xpath("//input[contains(@id,'--txtSubject-inner')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--txtSubject-inner')]")).sendKeys("Proceso Selenium iniciooo");
        driver.findElement(By.xpath("//div[@aria-label='Rich Text Editor, main']")).click();
        driver.findElement(By.xpath("//div[@aria-label='Rich Text Editor, main']")).sendKeys("Proceso Selenium");
        driver.findElement(By.xpath("//span[contains(@id,'--btnSaveEE-content')]")).click();
        driver.findElement(By.xpath("//button[@title='Rechazar' or @title = 'Decline']")).click();
        driver.findElement(By.xpath("//span[contains(@id,'--btnSaveModelerFB-inner')]")).click();*/
        //asserts.assertSave();
    }

}
