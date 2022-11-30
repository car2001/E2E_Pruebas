package home_page;


import helpers.AccessBranch;
import helpers.BasicControl;
import helpers.ChargePopPup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginApplications {
    private WebDriverWait wait;
    private WebDriver driver;
    private AccessBranch accessBranch;
    private BasicControl basicControl;
    private JavascriptExecutor js;
    private ChargePopPup chargePopPup;

    public LoginApplications(WebDriver driver){
        this.driver = driver;
        this.accessBranch = new AccessBranch(driver);
        this.basicControl = new BasicControl(driver);
        this.js = (JavascriptExecutor) driver;
        this.chargePopPup = new ChargePopPup(driver);
    }

    public void loginOSM(){
        basicControl.btnApplication("Organizational Structure Manager");
        accessBranch.clickBranches(0);
        accessBranch.clickBranches(1);
    }

    public void loginCM(){
        basicControl.btnApplication("Configuration Manager");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("navListItem-navList-0-a")));
        int tam = js.executeScript("let tam = document.getElementById('sap-ui-blocklayer-popup'); return(tam.clientHeight)").hashCode();
        if(tam != 0){
            chargePopPup.PopPupGeneral();
        }
        driver.findElement(By.xpath("//div[@title='Reusable Component']")).click();
        driver.findElement(By.xpath("//div[@title='Setting']")).click();
    }

    public  void loginRM(String componente){
        basicControl.btnApplication("Release Manager");
        chargePopPup.PopPupGeneral();
        int tam = js.executeScript("let tam = document.getElementById('sap-ui-blocklayer-popup'); return(tam.clientHeight)").hashCode();
        if(tam != 0){
            chargePopPup.PopPupGeneral();
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

    public void loginPM() throws InterruptedException {
        basicControl.btnApplication("Process Manager");
        //Esperamos las cargas
        String xmlview = basicControl.getXmlview();
        chargePopPup.PopPupGeneral();
        int tam = js.executeScript("let tam = document.getElementById('sap-ui-blocklayer-popup'); return(tam.clientHeight)").hashCode();
        if(tam != 0){
            chargePopPup.PopPupGeneral();
        }
    }

    public void loginColl(String proceso){
        basicControl.btnApplication("Collaboration Workspace");
        chargePopPup.PopPupGeneral();
        driver.findElement(By.id("navListItem-navList-2")).click();
        chargePopPup.PopPupGeneral();
        driver.findElement(By.xpath("//div[contains(@aria-label,'"+proceso+"')]")).click();
        driver.findElement(By.xpath("//bdi[text()='Sí' or text()='Yes']")).click();
        chargePopPup.PopPupGeneral();
        int tam = js.executeScript("let tam = document.getElementById('sap-ui-blocklayer-popup'); return(tam.clientHeight)").hashCode();
        if(tam != 0){
            chargePopPup.PopPupGeneral();
        }
    }

    public void loginDataEntity(){
        basicControl.btnApplication("Data Entity Manager");
        chargePopPup.PopPupGeneral();
    }

    public  void loginDataRecord(){
        basicControl.btnApplication("Data Record Manager");
    }

}
