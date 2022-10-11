package Applications.ReleaseManager;

import Forms.FormsRM;
import Forms.ReleaseManager.FormsChangeContainer;
import Helpers.*;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class RM_ChangeContainer {

    private WebDriver driver;

    Actions action;
    DynamicScroll searchScrollElement;
    Asserts asserts;
    AccessBranch accessBranch;
    BasicControl basicControl;
    Login login;
    FormsChangeContainer formsChangeContainer;

    String componente = "Change Containers";
    String newChangeContainer = "CC_SELENIUM";
    String DP = "DP_SELENIUM";
    String urlQA = "http://wedox.sytes.net/buplat_QA/";
    String urlPROD = "http://wedox.sytes.net/buplat/";


    public RM_ChangeContainer(WebDriver driver){
        this.driver = driver;
        this.action = new Actions(driver);
        this.asserts = new Asserts(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.accessBranch = new AccessBranch(driver);
        this.basicControl = new BasicControl(driver);
        this.login = new Login(driver);
        this.formsChangeContainer = new FormsChangeContainer(driver);
    }




    public void crearChangeContainerArbol(String changeContainer, String proyecto, String release, String user) throws InterruptedException {
        WebElement btnOpen = driver.findElement(By.xpath("//span[text()='Open']"));
        action.contextClick(btnOpen).perform();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[text()='New Change Container' or text()='Nuevo Contenedor de Cambios']")).click();
        formsChangeContainer.createChangeContainer(changeContainer,proyecto,release,user);
        asserts.assertSave();
        
    }


    @Test
    public void activarChangeContainerTabla(String changeContainer, String proyecto, String release, String user) throws InterruptedException {
        crearChangeContainerArbol(changeContainer,proyecto,release,user);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//span[text()='Open']")).click();
        Thread.sleep(1000);
        WebElement titulo = driver.findElement(By.xpath("//span[text()='Lista de Contenedor de Cambios' or text()='Change Container List']"));
        wait.until(ExpectedConditions.visibilityOf(titulo));
        List<WebElement> buttons = driver.findElements(By.xpath("//span[@class ='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']"));

        String posCC = searchScrollElement.searchElementTable(proyecto,"Open",release,changeContainer);
        if(posCC == " "){
            Assert.assertEquals("No hay el CC","Si hay CC");
        }else{
            driver.findElement(By.xpath("//span[text()='"+posCC+"']")).click();
            action.moveToElement(buttons.get(2)).click().perform();
            Thread.sleep(1500);
            asserts.assertSave();
        }

    }

    @Test
    public void releaseChangeContainer() throws InterruptedException {
        int exist = -1;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        exist = searchScrollElement.elementSearch("Open");
        if (exist != -1){
            accessBranch.clickBranches(exist);
            exist = searchScrollElement.elementSearch(newChangeContainer);
            if (exist != -1){
                WebElement CC = driver.findElement(By.xpath("//span[text()='"+newChangeContainer+"']"));
                action.contextClick(CC).build().perform();
                driver.findElement(By.xpath("//div[normalize-space()='Release']")).click();
                FormsRM.formReleaseCC(driver,accessBranch);
                exist = searchScrollElement.elementSearch("Open");
                if(exist != -1){
                    accessBranch.clickBranches(exist);
                    exist = searchScrollElement.elementSearch(DP);
                    if(exist !=-1){
                        WebElement newDP = driver.findElement(By.xpath("//span[text()='"+DP+"']"));
                        action.contextClick(newDP).build().perform();
                        driver.findElement(By.xpath("//div[normalize-space()='Release']")).click();
                        FormsRM.formReleaseDP(driver,accessBranch);
                        exist = searchScrollElement.elementSearch("Open");
                        if (exist != -1){
                            driver.findElement(By.xpath("//span[text()='Open']")).click();
                            Thread.sleep(1000);
                            WebElement titulo = driver.findElement(By.xpath("//span[text()='Lista de solicitudes de instalación']"));
                            wait.until(ExpectedConditions.visibilityOf(titulo));
                            List<WebElement> buttons = driver.findElements(By.xpath("//span[@class ='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']"));
                            driver.findElement(By.xpath("//span[text()='01']")).click();
                            action.moveToElement(buttons.get(2)).click().perform();
                            Thread.sleep(1000);
                            asserts.assertSave();
                            releaseEnviromentQA(wait);
                            releaseEnviromentPROD(wait);
                        }
                    }

                }
            }else{
                asserts.assertSave();
            }
        }else{
            asserts.assertSave();
        }

    }

    @Test
    public void releaseEnviromentQA(WebDriverWait wait) throws InterruptedException {
        Thread.sleep(1000);
        login.loginPage(urlQA);
        componente = "Deployment Request";
        LoginApplications.loginRM(driver, componente);
        Thread.sleep(1000);
        int exist = -1;
        exist = searchScrollElement.elementSearch("Open");
        if (exist != -1) {
            driver.findElement(By.xpath("//span[text()='Open']")).click();
            ChargePopPup.PopPupMain(driver,wait);
            WebElement titulo = driver.findElement(By.xpath("//span[text()='Lista de solicitudes de instalación']"));
            wait.until(ExpectedConditions.visibilityOf(titulo));
            List<WebElement> buttons = driver.findElements(By.xpath("//span[@class ='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']"));
            driver.findElement(By.xpath("//span[text()='01']")).click();
            action.moveToElement(buttons.get(0)).click().perform();
            ChargePopPup.PopPupMain(driver,wait);
            asserts.assertSave();
            driver.findElement(By.xpath("//span[text()='02']")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//span[text()='01']")).click();
            buttons = driver.findElements(By.xpath("//span[@class ='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']"));
            action.moveToElement(buttons.get(0)).click().perform();
            ChargePopPup.PopPupMain(driver,wait);
            asserts.assertSave();
        }
    }

    @Test
    public void releaseEnviromentPROD(WebDriverWait wait) throws InterruptedException {
        Thread.sleep(1000);
        login.loginPage(urlPROD);
        componente = "Deployment Request";
        LoginApplications.loginRM(driver, componente);
        int exist = -1;
        exist = searchScrollElement.elementSearch("Open");
        if (exist != -1) {
            driver.findElement(By.xpath("//span[text()='Open']")).click();
            Thread.sleep(1000);
            WebElement titulo = driver.findElement(By.xpath("//span[text()='Lista de solicitudes de instalación']"));
            wait.until(ExpectedConditions.visibilityOf(titulo));
            List<WebElement> buttons = driver.findElements(By.xpath("//span[@class ='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']"));
            driver.findElement(By.xpath("//span[text()='01']")).click();
            action.moveToElement(buttons.get(0)).click().perform();
            Thread.sleep(1000);
            asserts.assertSave();
        }
    }

    @AfterMethod
    public void tearDown(){
        if (driver != null){
            //driver.quit();
        }
    }

}


