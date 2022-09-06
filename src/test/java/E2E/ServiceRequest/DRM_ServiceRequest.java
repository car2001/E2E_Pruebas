package E2E.ServiceRequest;

import Applications.DataEntityManager.DEM_Entity;
import Applications.DataRecordManager.DRM_EntityRecord;
import Helpers.AccessBranch;
import Helpers.BasicControl;
import Helpers.DynamicScroll;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class DRM_ServiceRequest {
    private WebDriver driver;
    private BasicControl basicControl;
    private JavascriptExecutor js ;
    AccessBranch accessBranch;
    DynamicScroll searchScrollElement;
    DRM_EntityRecord entityRecord;

    public DRM_ServiceRequest(WebDriver driver){
        this.driver = driver;
        this.accessBranch = new AccessBranch(driver);
        this.js = (JavascriptExecutor)driver;
        this.searchScrollElement = new DynamicScroll(driver);
        this.basicControl = new BasicControl(driver);
        this.entityRecord = new DRM_EntityRecord(driver);
    }

    public void createDRM_ServiceRequest() throws InterruptedException {
        List<String> attributeList = new ArrayList<String>();
        entityRecord.crearDataRecordGlobal("Decisión2", listDataDecision2 (),"Cross Environment Entity");
        //entityRecord.crearDataRecord("aaa",attributeList);
        //entityRecord.crearDataRecord("Decisión2",attributeList);
        entityRecord.scrollTop();
        accessBranch.clickBranches(searchScrollElement.elementSearch("Cross Environment Entity"));
        accessBranch.clickBranches(searchScrollElement.elementSearch("Global"));
        //entityRecord.crearDataRecordGlobal("Customer", attributeList,"Local Environment Entity");
        entityRecord.scrollTop();
        accessBranch.clickBranches(searchScrollElement.elementSearch("Global"));
        //entityRecord.crearDataRecordProcess("Gestión de Programas IDO", "Gestión Eventos Terminated","Continuar?",attributeList,"Cross Environment Entity");
    }

    public String[][] listDataDecision2 (){
        int filas = 2;
        int columnas = 2;
        String[][] attribute = new String[filas][columnas];
        attribute[0][0] = "1";
        attribute[0][1] = "HOLA1";

        attribute[1][0] = "2";
        attribute[1][1] = "HOLA2";
        return attribute;
    }

    public static void main(String[] args) {
        int filas = 2;
        int columnas = 4;
        String[][] attribute = new String[filas][columnas];
        attribute[0][0] = "1";
        attribute[0][1] = "HOLA1";
        attribute[0][2] = "HOLA2";
        attribute[0][3] = "HOLA3";

        attribute[1][0] = "2";
        attribute[1][1] = "Carlos1";
        attribute[1][2] = "Carlos2";
        attribute[1][3] = "Carlos3";


        for(int i =0 ; i < filas; i++){
            for (int j =0 ; j < columnas; j++){
                System.out.println(attribute[i][j]);
            }
        }

        System.out.println(attribute.length);
        System.out.println(attribute[0].length);

    }
}
