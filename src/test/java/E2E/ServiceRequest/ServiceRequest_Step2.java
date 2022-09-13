package E2E.ServiceRequest;

import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectListItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private Asserts asserts;
    private SelectListItem selectListItem;
    private String step2 = "//span[contains(@id,'-count') and @class='sapMITBCount' and text()='2']";
    private String editDataModel = "--btnEditModelData-img";
    private String addAtributte = "//button[@title='Agregar' or @title='Add']";
    private String saveDataModel = "--btnSaveModelData-img";
    private int xpos;
    private String xmlview;
    private String input;
    private String arrowDataType;
    private String arrowTypeEntity;
    private String arrowEntity;



    public ServiceRequest_Step2(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        this.basicControl = new BasicControl(driver);
        this.selectListItem = new SelectListItem(driver);
        this.asserts = new Asserts(driver);
    }

    public void step2Process() throws InterruptedException {
        //Ingresamos al paso 2
        driver.findElement(By.xpath(step2)).click();
        FormsControl.controlTitle(driver,"Definir el Modelo de Datos de Proceso","Define Process Data Model");
        //Editamos al paso 2
        basicControl.btnEdit(editDataModel);
        //Añadimos los atributos al modelo
        WebElement btnAddAtributte = driver.findElement(By.xpath(addAtributte));

        List<String> listName = listDataModelName();
        List<String> listDataType = listDataModelDataType();

        xmlview = basicControl.getXmlview();

        for (int i = 0; i <= listName.size()-1; i++){
            btnAddAtributte.click();
            xpos = i;
            input = "//div[contains(@id,'tblAttributes-"+xpos+"-content') and not(contains(@id,'idDisplayNameGridModel'))]//input[@class='sapMInputBaseInner']";
            arrowDataType = "//span[@id='"+xmlview+"--idDataType-"+xmlview+"--tblAttributes-"+xpos+"-arrow']";

            WebElement inputName = driver.findElement(By.xpath(input));
            inputName.click();
            inputName.sendKeys(listName.get(i));
            WebElement arrowType = driver.findElement(By.xpath(arrowDataType));
            arrowType.click();
            selectListItem.SelectItemLi(listDataType.get(i));
        }

        List<String> listEntityType = listDataModelEntityType();
        List<String> listEntity = listDataModelEntity();

        arrowTypeEntity = "//span[contains(@id,'"+xmlview+"--idDataEntityType-"+xmlview+"--tblAttributes-') and @class='sapMSltArrow']";
        arrowEntity = "//span[contains(@id,'"+xmlview+"--idDataAttributeEntityType-"+xmlview+"--tblAttributes') and @class='sapUiIcon sapUiIconMirrorInRTL sapUiIconPointer sapMInputBaseIcon']";
        List<WebElement> typeEntity = driver.findElements(By.xpath(arrowTypeEntity));
        List<WebElement> entity = driver.findElements(By.xpath(arrowEntity));

        for (int j=0; j<= listEntityType.size()-1; j++ ){
            typeEntity.get(j).click();
            selectListItem.SelectItemLi(listEntityType.get(j));

            entity.get(j).click();
            selectListItem.SelectItemDiv(listEntity.get(j));
        }


        basicControl.btnSave(saveDataModel);
        asserts.assertSaveModelData();
    }

    private  List<String> listDataModelName(){
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
        listName.add("Tabla Selenium");
        return listName;
    }

    private  List<String> listDataModelDataType(){
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
        listDataType.add("ForeignKey");
        return listDataType;
    }

    private  List<String> listDataModelEntityType(){
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
        listEntityType.add("Transactional");
        return listEntityType;
    }

    private  List<String> listDataModelEntity(){
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
        listEntity.add("Tabla Selenium");
        return listEntity;
    }



}
