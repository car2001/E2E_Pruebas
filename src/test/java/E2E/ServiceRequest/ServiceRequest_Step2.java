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
    private String saveDataModel = "//span[contains(@id,'--btnSaveModelData-img')]";
    private int xpos = 0;
    private String input = "//div[contains(@id,'tblAttributes-"+xpos+"-content') and not(contains(@id,'idDisplayNameGridModel'))]//input[@class='sapMInputBaseInner']";

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

        List<String> listName = listDataModelName();
        List<String> listDataType = listDataModelDataType();

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

    private static List<String> listDataModelName(){
        List<String> listName = new ArrayList<String>();
        listName.add("NoUsersolicitante");
        listName.add("NOUSARRequestDate");
        listName.add("RequestDatail");
        listName.add("NousarRequestCategory");
        listName.add("Impact");
        listName.add("Priority");
        listName.add("Reason");
        listName.add("NousarAsignee");
        listName.add("EstimatedTime");
        listName.add("NO_USAR_ApprovalRequiered");
        listName.add("NousarApprover");
        listName.add("SolutionDetail");
        listName.add("NO_USAR_ApproveRequest");
        listName.add("NousarExecutor");
        listName.add("Asignado");
        listName.add("Aprobador");
        listName.add("Ejecutor");
        listName.add("Solicitante");
        listName.add("Category");
        listName.add("Decision");
        listName.add("ObservationComment");
        listName.add("Project");
        listName.add("Requestor");
        listName.add("Cutomer");
        listName.add("RequestSummary");
        listName.add("ExternalReference");
        listName.add("DueDate");
        listName.add("MoreExecutors?");
        listName.add("EstimatedTime2");
        listName.add("Executor2");
        listName.add("TaskDetail2");
        listName.add("MoreExecutors2?");
        listName.add("EstimatedTime3");
        listName.add("Executor3");
        listName.add("TaskDetail3");
        listName.add("ApprovalComments");
        listName.add("ApproveRequest");
        listName.add("ApprovalRequiered");
        listName.add("TotalEstimated");
        listName.add("Category");
        listName.add("ReasonSR");
        listName.add("RequestClosureAuthorization");
        return listName;
    }

    private static List<String> listDataModelDataType(){
        List<String> listDataType = new ArrayList<String>();
        listDataType.add("ForeignKey");
        listDataType.add("DateTime");
        listDataType.add("String");
        listDataType.add("ForeignKey");
        listDataType.add("ForeignKey");
        listDataType.add("ForeignKey");
        listDataType.add("ForeignKey");
        listDataType.add("ForeignKey");
        listDataType.add("BigInteger");
        listDataType.add("Boolean");
        listDataType.add("ForeignKey");
        listDataType.add("String");
        listDataType.add("Boolean");
        listDataType.add("ForeignKey");
        listDataType.add("ForeignKey");
        listDataType.add("ForeignKey");
        listDataType.add("ForeignKey");
        listDataType.add("ForeignKey");
        listDataType.add("ForeignKey");
        listDataType.add("ForeignKey");
        listDataType.add("String");
        listDataType.add("ForeignKey");
        listDataType.add("ForeignKey");
        listDataType.add("ForeignKey");
        listDataType.add("String");
        listDataType.add("String");
        listDataType.add("Date");
        listDataType.add("Boolean");
        listDataType.add("Integer");
        listDataType.add("ForeignKey");
        listDataType.add("String");
        listDataType.add("Boolean");
        listDataType.add("Integer");
        listDataType.add("ForeignKey");
        listDataType.add("String");
        listDataType.add("String");
        listDataType.add("ForeignKey");
        listDataType.add("ForeignKey");
        listDataType.add("Integer");
        listDataType.add("ForeignKey");
        listDataType.add("ForeignKey");
        listDataType.add("ForeignKey");
        return listDataType;
    }

    private static List<String> listDataModelEntityType(){
        List<String> listEntityType = new ArrayList<String>();
        listEntityType.add("Local Environment Entity");

        listEntityType.add("Cross Environment Entity");
        listEntityType.add("Cross Environment Entity");
        listEntityType.add("Cross Environment Entity");
        listEntityType.add("Cross Environment Entity");
        listEntityType.add("Local Environment Entity");

        listEntityType.add("Local Environment Entity");

        listEntityType.add("Local Environment Entity");
        listEntityType.add("System");
        listEntityType.add("System");
        listEntityType.add("System");
        listEntityType.add("System");
        listEntityType.add("Cross Environment Entity");
        listEntityType.add("Cross Environment Entity");

        listEntityType.add("Cross Environment Entity");
        listEntityType.add("Local Environment Entity");
        listEntityType.add("Local Environment Entity");

        listEntityType.add("System");
        listEntityType.add("System");

        listEntityType.add("Cross Environment Entity");
        listEntityType.add("Cross Environment Entity");

        listEntityType.add("Local Environment Entity");
        listEntityType.add("Local Environment Entity");
        listEntityType.add("Cross Environment Entity");
        return listEntityType;
    }

    private static List<String> listDataModelEntity(){
        List<String> listEntity = new ArrayList<String>();
        listEntity.add("Employee Selenium");

        listEntity.add("Category Selenium");
        listEntity.add("Impact Selenium");
        listEntity.add("Priority Selenium");
        listEntity.add("RequestReason Selenium");
        listEntity.add("Employee Selenium");

        listEntity.add("Employee Selenium");

        listEntity.add("Employee Selenium");
        listEntity.add("User");
        listEntity.add("User");
        listEntity.add("User");
        listEntity.add("User");
        listEntity.add("RequestCategory Selenium");
        listEntity.add("Decision2 Selenium");

        listEntity.add("Project Selenium");
        listEntity.add("CustomerContact Selenium");
        listEntity.add("Customer Selenium");

        listEntity.add("User");
        listEntity.add("User");

        listEntity.add("Decision2 Selenium");
        listEntity.add("Decision2 Selenium");

        listEntity.add("Categoria SR Selenium");
        listEntity.add("Motivo de Solicitud SR Selenium");
        listEntity.add("Request Closure Authorization Selenium");
        return listEntity;
    }


    public static void main(String[] args) {
        List<String> listName = listDataModelName();
        List<String> listDataType = listDataModelDataType();

        List<String> listEntiryType = listDataModelEntityType();
        List<String> listEntiTy = listDataModelEntity();

        System.out.println(listName.size());
        System.out.println(listDataType.size());
        System.out.println(listEntiryType.size());
        System.out.println(listEntiTy.size());
    }
}
