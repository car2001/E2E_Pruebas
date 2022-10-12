package e2e.simple_process.processManager.steps;

import helpers.Asserts;
import helpers.BasicControl;
import helpers.FormsControl;
import helpers.SelectListItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SP_step2 {

    private WebDriver driver;
    private WebDriverWait wait;
    private BasicControl basicControl;
    private Asserts asserts;
    private Actions action;
    private SelectListItem selectListItem;
    private String step2 = "//span[contains(@id,'-count') and @class='sapMITBCount' and text()='2']";
    private String editDataModel = "--btnEditModelData-img";
    private String addAtributte = "//button[@title='Agregar' or @title='Add']";
    private String saveDataModel = "--btnSaveModelData-img";


    public SP_step2(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        this.basicControl = new BasicControl(driver);
        this.selectListItem = new SelectListItem(driver);
        this.asserts = new Asserts(driver);
        this.action = new Actions(driver);
    }

    public void step2Process() throws InterruptedException {
        //Ingresamos al paso 2
        driver.findElement(By.xpath(step2)).click();
        FormsControl.controlTitle(driver,"Definir el Modelo de Datos de Proceso","Define Process Data Model");
        //Editamos al paso 2
        basicControl.btnEdit(editDataModel);
        //Añadimos los atributos al modelo
        WebElement btnAddAtributte = driver.findElement(By.xpath(addAtributte));
        action.doubleClick(btnAddAtributte).perform();
        btnAddAtributte.click();

        List<WebElement> listform = driver.findElements(By.className("sapMInputBaseInner"));
        listform.get(2).click();
        listform.get(2).sendKeys("Solicitud");
        listform.get(4).click();
        listform.get(4).sendKeys("¿Acepta?");
        listform.get(6).click();
        listform.get(6).sendKeys("Usuario");

        List<WebElement> cboForm = driver.findElements(By.className("sapMSltArrow"));
        cboForm.get(0).click();
        selectListItem.SelectItemLi("String");
        Thread.sleep(500);
        cboForm.get(1).click();
        selectListItem.SelectItemLi("Boolean");
        Thread.sleep(500);
        cboForm.get(2).click();
        selectListItem.SelectItemLi("String");

        basicControl.btnSave(saveDataModel);
        asserts.assertSaveModelData();
    }

}
