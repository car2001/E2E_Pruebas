package e2e.simple_process.processManager.steps;

import helpers.Asserts;
import helpers.BasicControl;
import helpers.ChargePopPup;
import helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SP_step1 {

    private WebDriver driver;
    private WebDriverWait wait;
    private BasicControl basicControl;
    private Actions action;
    private Asserts asserts;
    private String editModel = "--btnEdit-img";
    private String openFile = "//span[contains(@id,'--btnOpenFile-img')]";
    private String saveModel = "--btnSave-inne";
    private ChargePopPup chargePopPup;

    public SP_step1(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.basicControl = new BasicControl(driver);
        this.action = new Actions(driver);
        this.asserts = new Asserts(driver);
        this.chargePopPup = new ChargePopPup(driver);
    }

    public void step1Process() throws InterruptedException {
        //Ingresamos al paso 1 Model Process
        FormsControl.controlTitle(driver,"Modelar Proceso","Model Process");
        //Editamos el paso 1
        basicControl.btnEdit(editModel);
        //Obtenemos xmlview
        String xmlview = basicControl.getXmlview();
        //Empezamos a crear el diagrama
        WebElement start = driver.findElement(By.cssSelector("#"+xmlview+"--js-canvas > div > div > svg > g > g.layer-base > g > g.djs-children > g:nth-child(1) > g > rect.djs-outline"));
        action.moveToElement(start).click().build().perform();
        driver.findElement(By.xpath("//div[@title='Append User Task']")).click();
        driver.findElement(By.xpath("//div[@title='Append Exclusive Gateway']")).click();
        driver.findElement(By.xpath("//div[@title='Append User Task']")).click();
        driver.findElement(By.xpath("//div[@title='Append EndEvent']")).click();
        WebElement gateWay =  driver.findElement(By.cssSelector("#"+xmlview+"--js-canvas > div > div > svg > g > g.layer-base > g > g.djs-children > g:nth-child(8) > g > rect.djs-hit.djs-hit-all"));
        action.moveToElement(gateWay).click().build().perform();
        driver.findElement(By.xpath("//div[@title='Append User Task']")).click();
        driver.findElement(By.xpath("//div[@title='Append EndEvent']")).click();
        driver.findElement(By.cssSelector("#"+xmlview+"--js-canvas > div > div > svg > g > g.layer-base > g > g.djs-children > g:nth-child(11) > g > rect.djs-hit.djs-hit-all")).click();
        driver.findElement(By.xpath("//div[@title='Change type']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='Send Task']")).click();

        basicControl.btnSave("--btnSave-inner");
        chargePopPup.PopPupGeneral();
        asserts.assertSaveDiagram();
    }

}
