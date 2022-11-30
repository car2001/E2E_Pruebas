package applications.processManager;

import forms.ProcessManager.FormsProcess;
import helpers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;
import java.util.List;

public class PM_Process {

    private WebDriver driver;

    private BasicControl basicControl;
    private Actions action;
    private AccessBranch accessBranch;
    private DynamicScroll searchScrollElement;
    private Asserts asserts;
    private JavascriptExecutor js;
    private ElementSVG elementSVG;
    private WebDriverWait wait;
    private  FormsProcess formsProcess;
    private ChargePopPup chargePopPup;
    private String component = "Processes";


    public PM_Process(WebDriver driver) {
        this.driver = driver;
        this.asserts = new Asserts(driver);
        this.action = new Actions(driver);
        this.accessBranch = new AccessBranch(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        this.js = (JavascriptExecutor) driver;
        this.basicControl = new BasicControl(driver);
        this.formsProcess = new FormsProcess(driver);
        this.chargePopPup = new ChargePopPup(driver);
    }

    @Test
    public void crearProceso(String nameLevel, String nameProcess, String INS, String SLA) throws InterruptedException, AWTException {
        int xpos = searchScrollElement.elementSearch(nameLevel);
        System.out.println(xpos);
        if (xpos != -1) {
            accessBranch.clickBranches(xpos);
            xpos = searchScrollElement.elementSearch(component);
            if (xpos != -1) {
                WebElement process = driver.findElement(By.xpath("//span[text()='" + component + "']"));
                action.contextClick(process).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New Process' or text()='Nuevo Proceso']")).click();
                Thread.sleep(1000);
                formsProcess.createProcess(nameProcess, INS, SLA);
                chargePopPup.PopPupMain();
                asserts.assertSave();
                //Realizamos los pasos
                //stepsProcess();
            } else {
                Assert.assertEquals("No se encontro la jerarquia", "NO");

            }
        }
    }



}
