package HomePage;


import Helpers.AccessBranch;
import Helpers.BasicControl;
import Helpers.ChargePopPup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginApplications {
    public static WebDriverWait wait;
    public static AccessBranch accessBranch;
    public static BasicControl basicControl;
    public static JavascriptExecutor js;

    public static void loginOSM(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        js = (JavascriptExecutor) driver;
        accessBranch = new AccessBranch(driver);
        basicControl = new BasicControl(driver);
        basicControl.btnApplication("Organizational Structure Manager");
        accessBranch.clickBranches(0);
        accessBranch.clickBranches(1);
    }

    public static void loginCM(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        js = (JavascriptExecutor) driver;
        basicControl = new BasicControl(driver);
        basicControl.btnApplication("Configuration Manager");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("navListItem-navList-0-a")));
        int tam = js.executeScript("let tam = document.getElementById('sap-ui-blocklayer-popup'); return(tam.clientHeight)").hashCode();
        if(tam != 0){
            ChargePopPup.PopPupGeneral(driver,wait);
        }
        driver.findElement(By.xpath("//div[@title='Reusable Component']")).click();
        driver.findElement(By.xpath("//div[@title='Setting']")).click();
    }

    public static void loginRM(WebDriver driver, String componente){
        wait = new WebDriverWait(driver,Duration.ofSeconds(100));
        js = (JavascriptExecutor) driver;
        accessBranch = new AccessBranch(driver);
        basicControl = new BasicControl(driver);
        basicControl.btnApplication("Release Manager");
        ChargePopPup.PopPupGeneral(driver,wait);
        int tam = js.executeScript("let tam = document.getElementById('sap-ui-blocklayer-popup'); return(tam.clientHeight)").hashCode();
        if(tam != 0){
            ChargePopPup.PopPupGeneral(driver,wait);
        }

        if(componente.equals("Projects")){
            accessBranch.clickBranches(0);
        }else if(componente.equals("Change Containers")){
            accessBranch.clickBranches(1);
        }else if (componente.equals("Deployment Packages")){
            accessBranch.clickBranches(2);
        }else if(componente.equals("Deployment Requests")){
            accessBranch.clickBranches(3);
        }
    }

    public static void loginPM(WebDriver driver) throws InterruptedException {
        wait = new WebDriverWait(driver,Duration.ofSeconds(100));
        js = (JavascriptExecutor) driver;
        accessBranch = new AccessBranch(driver);
        basicControl = new BasicControl(driver);
        basicControl.btnApplication("Process Manager");
        //Esperamos las cargas
        String xmlview = basicControl.getXmlview();
        ChargePopPup.PopPupGeneral(driver,wait);
        int tam = js.executeScript("let tam = document.getElementById('sap-ui-blocklayer-popup'); return(tam.clientHeight)").hashCode();
        if(tam != 0){
            ChargePopPup.PopPupGeneral(driver,wait);
        }
    }

    public static void loginColl(WebDriver driver, String proceso){
        wait = new WebDriverWait(driver,Duration.ofSeconds(100));
        js = (JavascriptExecutor) driver;
        basicControl = new BasicControl(driver);
        basicControl.btnApplication("Collaboration Workspace");
        ChargePopPup.PopPupGeneral(driver,wait);
        driver.findElement(By.id("navListItem-navList-2")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        driver.findElement(By.xpath("//div[contains(@aria-label,'"+proceso+"')]")).click();
        driver.findElement(By.xpath("//bdi[text()='Sí' or text()='Yes']")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        int tam = js.executeScript("let tam = document.getElementById('sap-ui-blocklayer-popup'); return(tam.clientHeight)").hashCode();
        if(tam != 0){
            ChargePopPup.PopPupGeneral(driver,wait);
        }
    }

    public static void loginDataEntity(WebDriver driver){
        wait = new WebDriverWait(driver,Duration.ofSeconds(100));
        basicControl = new BasicControl(driver);
        basicControl.btnApplication("Data Entity Manager");
        ChargePopPup.PopPupGeneral(driver,wait);
        int tam = js.executeScript("let tam = document.getElementById('sap-ui-blocklayer-popup'); return(tam.clientHeight)").hashCode();
        if(tam != 0){
            ChargePopPup.PopPupGeneral(driver,wait);
        }
    }

    public static void loginDataRecord(WebDriver driver){
        wait = new WebDriverWait(driver,Duration.ofSeconds(100));
        basicControl = new BasicControl(driver);
        basicControl.btnApplication("Data Record Manager");
        ChargePopPup.PopPupGeneral(driver,wait);
        int tam = js.executeScript("let tam = document.getElementById('sap-ui-blocklayer-popup'); return(tam.clientHeight)").hashCode();
        if(tam != 0){
            ChargePopPup.PopPupGeneral(driver,wait);
        }
    }

}
