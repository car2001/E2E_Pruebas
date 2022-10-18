package e2e.service_request.entityManager;

import applications.dataEntityManager.DEM_Entity;
import helpers.AccessBranch;
import helpers.BasicControl;
import helpers.DynamicScroll;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class DEM_ServiceRequest {
    private WebDriver driver;
    private BasicControl basicControl;
    private JavascriptExecutor js ;
    AccessBranch accessBranch;
    DynamicScroll searchScrollElement;
    DEM_Entity entity;

    public DEM_ServiceRequest(WebDriver driver){
        this.driver = driver;
        this.accessBranch = new AccessBranch(driver);
        this.js = (JavascriptExecutor)driver;
        this.searchScrollElement = new DynamicScroll(driver);
        this.basicControl = new BasicControl(driver);
        this.entity = new DEM_Entity(driver);
    }

    String date = LocalDate.now().toString(); //Fecha Actual


    public void createDEM_ServiceRequest() throws InterruptedException {
        //entidades Locales
        entity.crearDataEntityGlobal("CustomerContact " + date,listAttributeCustomerContact(),"Local Environment Entity");
        entity.crearDataEntity("Customer " + date,listAttributeCustomer(),"Local Environment Entity");
        //entidades cross
        entity.crearDataEntity("Category " + date,listAttributeCategory(),"Cross Environment Entity");
        entity.crearDataEntity("Priority " + date,listAttributePriority(),"Cross Environment Entity");
        entity.crearDataEntity("Decision2 " + date,listAttributeDecision2(),"Cross Environment Entity");
        entity.crearDataEntity("Project " + date,listAttributeProject(),"Cross Environment Entity");
        entity.crearDataEntity("Request Closure Authorization " + date,listAttributeRequestClosure(),"Cross Environment Entity");
        //transactional
        entity.crearDataEntity("Tabla " + date,listAttributeTablaSelenium(),"Transactional");
        //Cross Process
        entity.crearDataEntity("RequestCategory " + date,listAttributeRequestCategory(),"Cross Environment Entity");
        entity.crearDataEntity("Impact " + date,listAttributeImpact(),"Cross Environment Entity");
        entity.crearDataEntity("RequestReason " + date,listAttributeRequestReason(),"Cross Environment Entity");
        //Local Process
        entity.crearDataEntity("Categoria SR " + date,listAttributeCategoriaSR(),"Local Environment Entity");
        entity.crearDataEntity("Motivo de Solicitud SR " + date,listAttributeMotivoSolicitud(),"Local Environment Entity");
    }


    private Map<String,String> listAttributeEmployee(){
        Map<String,String> attributeList =  new LinkedHashMap<String,String>();
        attributeList.put("ID","String");
        attributeList.put("FullName","String");
        attributeList.put("Username","String");
        attributeList.put("ContactEmail","String");
        attributeList.put("Cellphone","Integer");
        attributeList.put("Address","String");
        attributeList.put("Boss","String");
        attributeList.put("OrganizationUnit","String");
        attributeList.put("Position","String");
        return attributeList;
    }

    private Map<String,String> listAttributeCategory(){
        Map<String,String> attributeList =  new LinkedHashMap<String,String>();
        attributeList.put("CategoryName","String");
        return attributeList;
    }

    private Map<String,String> listAttributePriority(){
        Map<String,String> attributeList =  new LinkedHashMap<String,String>();
        attributeList.put("PriorityName","String");
        return attributeList;
    }

    private Map<String,String> listAttributeDecision2(){
        Map<String,String> attributeList =  new LinkedHashMap<String,String>();
        attributeList.put("IDDecision","String");
        attributeList.put("DecisionDescription","String");
        return attributeList;
    }

    private Map<String,String> listAttributeProject(){
        Map<String,String> attributeList =  new LinkedHashMap<String,String>();
        attributeList.put("ProjectName","String");
        attributeList.put("Description","String");
        return attributeList;
    }

    private Map<String,String> listAttributeCustomerContact(){
        Map<String,String> attributeList =  new LinkedHashMap<String,String>();
        attributeList.put("FullName","String");
        attributeList.put("EmailContact","String");
        return attributeList;
    }

    private Map<String,String> listAttributeCustomer(){
        Map<String,String> attributeList =  new LinkedHashMap<String,String>();
        attributeList.put("RUC","String");
        attributeList.put("RazonSocial","String");
        attributeList.put("NombreComercial","String");
        attributeList.put("FullNameContact","String");
        attributeList.put("NOUSAR","String");
        attributeList.put("EmailContact","String");
        return attributeList;
    }

    private Map<String,String> listAttributeRequestClosure(){
        Map<String,String> attributeList =  new LinkedHashMap<String,String>();
        attributeList.put("ID","String");
        attributeList.put("RequestClosure Authorization","String");
        return attributeList;
    }

    private Map<String,String> listAttributeRequestCategory(){
        Map<String,String> attributeList =  new LinkedHashMap<String,String>();
        attributeList.put("RequestCategoryName","String");
        return attributeList;
    }

    private Map<String,String> listAttributeImpact(){
        Map<String,String> attributeList =  new LinkedHashMap<String,String>();
        attributeList.put("ImpactName","String");
        return attributeList;
    }

    private Map<String,String> listAttributeRequestReason(){
        Map<String,String> attributeList =  new LinkedHashMap<String,String>();
        attributeList.put("RequestReasonName","String");
        return attributeList;
    }

    private Map<String,String> listAttributeCategoriaSR(){
        Map<String,String> attributeList =  new LinkedHashMap<String,String>();
        attributeList.put("CategoryNameSR","String");
        attributeList.put("IDCategory","String");
        return attributeList;
    }

    private Map<String,String> listAttributeMotivoSolicitud(){
        Map<String,String> attributeList =  new LinkedHashMap<String,String>();
        attributeList.put("MotivoSolicitudSR","String");
        attributeList.put("IDMotivoSolicitudSR","String");
        return attributeList;
    }

    private Map<String,String> listAttributeTablaSelenium(){
        Map<String,String> attributeList =  new LinkedHashMap<String,String>();
        attributeList.put("ID","String");
        attributeList.put("Descripción","String");
        return attributeList;
    }
}

