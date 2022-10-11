package E2E.SimpleProcess;

import Forms.ProcessManager.FormsActivityForm;
import Helpers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;

public class SimpleProcess_Step3 {

    private WebDriver driver;
    private WebDriverWait wait;
    private BasicControl basicControl;
    private Actions action;
    private Asserts asserts;
    private ElementSVG elementSVG;
    private FormsActivityForm formsAF;
    private String step3 = "//span[contains(@id,'-count') and @class='sapMITBCount' and text()='3']";
    private String editModelerFB = "--btnEditModelerFB-inner";
    private String addACTF = "//span[contains(@id,'--btnAddACTF-img')]";


    public SimpleProcess_Step3(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        this.basicControl = new BasicControl(driver);
        this.action = new Actions(driver);
        this.elementSVG = new ElementSVG(driver);
        this.formsAF = new FormsActivityForm(driver);
        this.asserts = new Asserts(driver);
    }


    public void step3Process()  throws InterruptedException, AWTException {
        //Ingreso al paso 3
        driver.findElement(By.xpath(step3)).click();
        ChargePopPup.PopPupDetail(driver,wait);
        FormsControl.controlTitle(driver,"Asignar Formularios","Assign Forms");
        //Editamos el paso 3
        basicControl.btnEdit(editModelerFB);
        //Obtenemos el xmlview
        String xmlview = basicControl.getXmlview();

        //Definimos los rect
        String mainRect = "#"+xmlview+"--js-canvas-fb > div > div > svg > g";
        //Definimos los user Task
        String usertask1 = "#"+xmlview+"--js-canvas-fb > div > div > svg > g > g > g > g.djs-children > g:nth-child(9) > g > rect.djs-hit.djs-hit-all";
        String usertask2 = "#"+xmlview+"--js-canvas-fb > div > div > svg > g > g > g > g.djs-children > g:nth-child(13) > g > rect.djs-hit.djs-hit-all";

        //Definimos los send Task
        String sendtask = "#"+xmlview+"--js-canvas-fb > div > div > svg > g > g > g > g.djs-children > g:nth-child(16) > g > rect.djs-outline";

        //Ingresamos a la actividad 1
        elementSVG.clickSVGElementCenter(usertask1);
        //Click para crear el Activity Form
        driver.findElement(By.xpath(addACTF)).click();
        formsAF.createNewActivityForm("AF-SELENIUM");
        ChargePopPup.PopPupMain(driver,wait);
        formsAF.panelActivityForm(3);

        //Ingresamos a la actividad 2

        elementSVG.clickSVGElementCenter(usertask2);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[contains(@id,'--cbActivityForm-inner')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--cbActivityForm-inner')]")).sendKeys("AF-SELENIUM");
        driver.findElement(By.xpath("//input[contains(@id,'--cbActivityForm-inner')]")).sendKeys(Keys.TAB);
        driver.findElement(By.xpath("//button[@title='Rechazar' or @title = 'Decline']")).click();
        Thread.sleep(1000);


        //Ingresamos al sendTask

        elementSVG.clickSVGElementCenter(sendtask);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(@id,'--btnEmailDefinition-inner')]")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        WebElement popEmailDefinition = driver.findElement(By.xpath("//span[text()='Editor de Correo Electrónico' or text()='Email Editor']"));
        wait.until(ExpectedConditions.visibilityOf(popEmailDefinition));
        driver.findElement(By.xpath("//input[contains(@id,'--miTO-inner')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--miTO-inner')]")).sendKeys("role: Superadmin");
        driver.findElement(By.xpath("//input[contains(@id,'--miTO-inner')]")).sendKeys(Keys.TAB);
        driver.findElement(By.xpath("//input[contains(@id,'--txtSubject-inner')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--txtSubject-inner')]")).sendKeys("Proceso Selenium iniciooo");
        driver.findElement(By.xpath("//div[@aria-label='Rich Text Editor, main']")).click();
        driver.findElement(By.xpath("//div[@aria-label='Rich Text Editor, main']")).sendKeys("Proceso Selenium");
        driver.findElement(By.xpath("//span[contains(@id,'--btnSaveEE-content')]")).click();
        driver.findElement(By.xpath("//button[@title='Rechazar' or @title = 'Decline' ]")).click();

        basicControl.btnSave("--btnSaveModelerFB-inner");
        asserts.assertSave();
    }


}
