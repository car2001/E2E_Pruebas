package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasicControl {
    private WebDriver driver;
    private JavascriptExecutor js;
    private WebDriverWait wait;

    public BasicControl(WebDriver driver){
        this.driver = driver;
        this.js = (JavascriptExecutor)driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    //Logo
    public void logo(){
        driver.findElement(By.xpath("//img[@src='./public/images/buplat_logo_blanco.png']")).click();
        String routePM = "//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Process Manager']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(routePM)));
    }

    public void btnUser(){
        driver.findElement(By.xpath("//span[contains(@id,'--avatarUser') and (@title='User Options' or @title='Opciones de usuario')]")).click();
        //driver.findElement(By.xpath("//bdi[text()='"+option+"']"));
    }

    //Añadir
    public void btnAdd(){
        driver.findElement(By.xpath("//div[contains(@id,'__xmlview') and @class='sapUiView sapUiXMLView sapMNavItem']//span//span[contains(@id,'--add-img')]")).click();
    }

    //Editar
    public void btnEdit() throws InterruptedException {
        String xpathedit = "//div[contains(@id,'__xmlview') and @class='sapUiView sapUiXMLView sapMNavItem']//span//span[contains(@id,'--edit-img')]";
        driver.findElement(By.xpath(xpathedit)).click();
        FormsControl.controlLook(driver,xpathedit,js);
    }

    public void btnEdit(String edit) throws InterruptedException {
        String xpathedit = "//div[contains(@id,'__xmlview') and @class='sapUiView sapUiXMLView sapMNavItem']//span//span[contains(@id,'"+edit+"')]";
        driver.findElement(By.xpath(xpathedit)).click();
        FormsControl.controlLook(driver,xpathedit,js);
    }

    //Guardar
    public void btnSave(){
        driver.findElement(By.xpath("//div[contains(@id,'__xmlview') and @class='sapUiView sapUiXMLView sapMNavItem']//span//span[contains(@id,'--save-img')]")).click();
    }

    public void btnSave(String save){
        driver.findElement(By.xpath("//div[contains(@id,'__xmlview') and @class='sapUiView sapUiXMLView sapMNavItem']//span//span[contains(@id,'"+save+"')]")).click();
    }

    //Cancelar
    public void btnCancel() {
        driver.findElement(By.xpath("//div[contains(@id,'__xmlview') and @class='sapUiView sapUiXMLView sapMNavItem']//span//span[contains(@id,'--cancel-img')]")).click();
    }

    //Dependencias
    public void btnDependecies(){
        driver.findElement(By.xpath("//div[contains(@id,'__xmlview') and @class='sapUiView sapUiXMLView sapMNavItem']//span//span[contains(@id,'--viewDependencies-img')]")).click();
    }

    //Nueva Versión
    public void btnNewVersion(String version) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        String newVersion = "//div[contains(@id,'__xmlview') and @class='sapUiView sapUiXMLView sapMNavItem']//span//span[contains(@id,'--newVersion-img')]";
        driver.findElement(By.xpath(newVersion)).click();
        FormsControl.controlLook(driver,newVersion,js); // contolamos el look
        //Esperamos el pop up de version
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@id,'--newVersionDialog-scrollCont')]"))));
        if(version.equals("mayor")){
            driver.findElement(By.xpath("//bdi[contains(@id,'--majorVersionRB-label-bdi') or text()='Major Version']")).click();
        }else{
            driver.findElement(By.xpath("//bdi[contains(@id,'--minorVersionRB-label-bdi') or text()='Minor Version']")).click();
        }
        driver.findElement(By.xpath("//bdi[contains(@id,'-BDI-content') and ( text()='Crear versión' or text()='Create Version')]")).click();

    }

    //Historia de Versión
    public void btnVersionHistory(int xpos, String version){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.findElement(By.xpath("//div[contains(@id,'__xmlview') and @class='sapUiView sapUiXMLView sapMNavItem']//span//span[contains(@id,'--versionHistory')]")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//tbody[contains(@id,'--versionsTable-tblBody')]"))));
        List<WebElement> btn_restore = driver.findElements(By.xpath("//button[@title='Restaurar' or @title='Restore']"));
        btn_restore.get(xpos).click();
        //Esperamos el pop up de version
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@id,'--newVersionDialog-scrollCont')]"))));
        if(version.equals("mayor")){
            driver.findElement(By.xpath("//bdi[contains(@id,'--majorVersionRB-label-bdi') or text()='Major Version']")).click();
        }else{
            driver.findElement(By.xpath("//bdi[contains(@id,'--minorVersionRB-label-bdi') or text()='Minor Version']")).click();
        }
        driver.findElement(By.xpath("//bdi[contains(@id,'-BDI-content') and ( text()='Crear versión' or text()='Create Version')]")).click();
    }

    //Reclamar
    public void claim(){
        driver.findElement(By.xpath("//span[contains(@id,'--btnClain-img')]")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
    }

    //Enviar
    public void submit(){
        driver.findElement(By.xpath("//bdi[text() = 'Enviar' and contains(@id,'--btnSubmit-BDI-content')]")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
    }

    public void btn_MsgStrigMessage(){
        driver.findElement(By.xpath("//button[@title='Cerrar' or @title='Close']")).click();
    }

    //More - CM
    public void btn_More(String componente){
        driver.findElement(By.xpath("//span[text()='"+componente+"']")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        String mas = "//span[(text()='Más' or text()='More') and @class='sapMSLITitle']";
        try {
            WebElement more = driver.findElement(By.xpath(mas));
            Boolean moreDisplayed = more.isDisplayed();
            while (moreDisplayed){
                more.click();
                moreDisplayed  = driver.findElement(By.xpath(mas)).isDisplayed();
            }
        }catch (Exception error){
            System.out.println("No hay elemento más");
        }
    }
}