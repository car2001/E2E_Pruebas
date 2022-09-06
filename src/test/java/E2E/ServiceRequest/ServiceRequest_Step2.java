package E2E.ServiceRequest;

import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceRequest_Step2 {

    private WebDriver driver;
    private WebDriverWait wait;
    private BasicControl basicControl;
    private Actions action;
    private Asserts asserts;
    private String step2 = "//span[contains(@id,'-count') and @class='sapMITBCount' and text()='2']";
    private String editDataModel = "--btnEditModelData-img";
    private String addAtributte = "//button[@title='Añadir' or @title='Add']";

    public ServiceRequest_Step2(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        this.basicControl = new BasicControl(driver);
        this.action = new Actions(driver);
        this.asserts = new Asserts(driver);
    }

    public void step2Process() throws InterruptedException {
        //Ingresamos al paso 2
        driver.findElement(By.xpath(step2)).click();
        FormsControl.controlTitle(driver,"Definir el modelo de datos de proceso","Define Process Data Model");
        //Editamos al paso 2
        basicControl.btnEdit(editDataModel);
        //Añadimos los atributos al modelo
        List<String> nameAtributte = new ArrayList<String>();
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
        driver.findElements(By.cssSelector(".sapMSelectListItemWithIcon.sapMSelectListItemBase.sapMSelectListItem.sapMSelectListItemBaseHoverable")).get(10).click();
        cboForm.get(1).click();
        driver.findElements(By.cssSelector(".sapMSelectListItemWithIcon.sapMSelectListItemBase.sapMSelectListItem.sapMSelectListItemBaseHoverable")).get(15).click();
        cboForm.get(2).click();
        driver.findElements(By.cssSelector(".sapMSelectListItemWithIcon.sapMSelectListItemBase.sapMSelectListItem.sapMSelectListItemBaseHoverable")).get(38).click();
        driver.findElement(By.id("__xmlview4--btnSaveModelData-img")).click();
        asserts.assertSaveModelData();
    }



}
