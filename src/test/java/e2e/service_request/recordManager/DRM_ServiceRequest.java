package e2e.service_request.recordManager;

import applications.dataRecordManager.DRM_EntityRecord;
import helpers.AccessBranch;
import helpers.BasicControl;
import helpers.DynamicScroll;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;

public class DRM_ServiceRequest {
    private WebDriver driver;
    private AccessBranch accessBranch;
    private DynamicScroll searchScrollElement;
    private DRM_EntityRecord entityRecord;

    public DRM_ServiceRequest(WebDriver driver){
        this.driver = driver;
        this.accessBranch = new AccessBranch(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.entityRecord = new DRM_EntityRecord(driver);
    }

    String date = LocalDate.now().toString();

    public void createDRM_ServiceRequest() throws InterruptedException {
        //Cross
        entityRecord.crearDataRecordGlobal("Category " + date,listDataCategorySelenium(),"Cross Environment Entity");
        entityRecord.crearDataRecord("Priority " + date,listDataPrioritySelenium());
        entityRecord.crearDataRecord("Decision2 " + date,listDataDecision2Selenium());
        entityRecord.crearDataRecord("Project " + date,listDataProjectSelenium());
        entityRecord.crearDataRecord("Request Closure Authorization " + date,listDataRequestClosureSelenium());
        entityRecord.crearDataRecord("RequestCategory " + date,listDataRequestCategorySelenium());
        entityRecord.crearDataRecord("Impact " + date,listDataRequestImpactSelenium());
        entityRecord.crearDataRecord("RequestReason " + date,listDataRequestReasonSelenium());

        entityRecord.scrollTop();
        accessBranch.clickBranches(searchScrollElement.elementSearch("Cross Environment Entity"));
        accessBranch.clickBranches(searchScrollElement.elementSearch("Global"));
        //Local
        entityRecord.crearDataRecordGlobal("CustomerContact " + date,listDataRequestCustomerContactSelenium(),"Local Environment Entity");
        entityRecord.crearDataRecord("Customer " + date,listDataRequestCustomerSelenium());
        entityRecord.crearDataRecord("Categoria SR " + date,listDataRequestCategoriaSR());
        entityRecord.crearDataRecord("Motivo de Solicitud SR " + date,listDataRequestMotivoSolicitudSR());
        entityRecord.scrollTop();
        accessBranch.clickBranches(searchScrollElement.elementSearch("Local Environment Entity"));
        accessBranch.clickBranches(searchScrollElement.elementSearch("Global"));
        //Process



    }

    private String[][] listDataCategorySelenium(){
        int filas = 10;
        int columnas = 1;
        String[][] attribute = new String[filas][columnas];
        attribute[0][0] = "Actualización de Procesos";
        attribute[1][0] = "Actualización Documentaria";
        attribute[2][0] = "Capacitación";
        attribute[3][0] = "Compras";
        attribute[4][0] = "Configuraciones";
        attribute[5][0] = "Creación de Scrips de Pruebas";
        attribute[6][0] = "Diseño Grafico";
        attribute[7][0] = "Documentación";
        attribute[8][0] = "Entrenamientos";
        attribute[9][0] = "Entrevista";
        return attribute;
    }

    private String[][] listDataPrioritySelenium(){
        int filas = 5;
        int columnas = 1;
        String[][] attribute = new String[filas][columnas];
        attribute[0][0] = "Low";
        attribute[1][0] = "Medium";
        attribute[2][0] = "High";
        attribute[3][0] = "Critical";
        attribute[4][0] = "Showstoper";
        return attribute;
    }

    private String[][] listDataDecision2Selenium(){
        int filas = 2;
        int columnas = 2;
        String[][] attribute = new String[filas][columnas];
        attribute[0][0] = "DS";
        attribute[0][1] = "Sí";
        attribute[1][0] = "DN";
        attribute[1][1] = "No";
        return attribute;
    }

    private String[][] listDataProjectSelenium(){
        int filas = 4;
        int columnas = 2;
        String[][] attribute = new String[filas][columnas];
        attribute[0][0] = "AUSA-Soporte";
        attribute[0][1] = "AUSA-Soporte";

        attribute[1][0] = "iDO-Actualización de Página Web";
        attribute[1][1] = "iDO-Actualización de Página Web";

        attribute[2][0] = "iDO-Automatización de Procesos";
        attribute[2][1] = "iDO-Automatización de Procesos";

        attribute[3][0] = "iDO-Gestión Administrativa";
        attribute[3][1] = "iDO-Gestión Administrativa";
        return attribute;
    }

    private String[][] listDataRequestClosureSelenium(){
        int filas = 4;
        int columnas = 2;
        String[][] attribute = new String[filas][columnas];
        attribute[0][0] = "RCA01";
        attribute[0][1] = "SI";

        attribute[1][0] = "RCA02";
        attribute[1][1] = "No";

        attribute[2][0] = "RCA03";
        attribute[2][1] = "No es necesario";

        attribute[3][0] = "RCA04";
        attribute[3][1] = "No respondio";
        return attribute;
    }

    private String[][] listDataRequestEmployeeSelenium(){
        int filas = 1;
        int columnas = 9;
        String[][] attribute = new String[filas][columnas];
        attribute[0][0] = "01";
        attribute[0][1] = "Carlos Pingo";
        attribute[0][2] = "cpingo";
        attribute[0][3] = "cr7@gmail.com";
        attribute[0][4] = "933457248";
        attribute[0][5] = "Av.Perú";
        attribute[0][6] = "Wedox";
        attribute[0][7] = "QA";
        attribute[0][8] = "tester";
        return attribute;
    }

    private String[][] listDataRequestCustomerContactSelenium(){
        int filas = 3;
        int columnas = 2;
        String[][] attribute = new String[filas][columnas];
        attribute[0][0] = "Juan Perez";
        attribute[0][1] = "juan.perez@abc.com";

        attribute[1][0] = "Ashley Valeriano";
        attribute[1][1] = "avaleriano@def.com";

        attribute[2][0] = "Ana Arana";
        attribute[2][1] = "ana.arana@ghi.com";

        return attribute;
    }

    private String[][] listDataRequestCustomerSelenium(){
        int filas = 2;
        int columnas = 6;
        String[][] attribute = new String[filas][columnas];
        attribute[0][0] = "1234567890";
        attribute[0][1] = "ABC S.A.C";
        attribute[0][2] = "Cydsa Comp";
        attribute[0][3] = "Juan Antonio";
        attribute[0][4] = "-";
        attribute[0][5] = "abc@abc.com";

        attribute[1][0] = "0987654321";
        attribute[1][1] = "DEF E.I.R.L";
        attribute[1][2] = "Merza El";
        attribute[1][3] = "Maria Luz";
        attribute[1][4] = "-";
        attribute[1][5] = "def@def.com";
        return attribute;
    }

    private String[][] listDataRequestCategorySelenium(){
        int filas = 8;
        int columnas = 1;
        String[][] attribute = new String[filas][columnas];
        attribute[0][0] = "Hardware";
        attribute[1][0] = "Solicitud de Acceso";
        attribute[2][0] = "Solicitud de Información";
        attribute[3][0] = "Software";
        attribute[4][0] = "Configuración";
        attribute[5][0] = "Parche";
        attribute[6][0] = "Automatización de Pruebas";
        attribute[7][0] = "Cambios en el Proeso";
        return attribute;
    }

    private String[][] listDataRequestImpactSelenium(){
        int filas = 4;
        int columnas = 1;
        String[][] attribute = new String[filas][columnas];
        attribute[0][0] = "Empresa";
        attribute[1][0] = "Area/Departamento";
        attribute[2][0] = "Multiples Usuarios";
        attribute[3][0] = "Usuario";
        return attribute;
    }

    private String[][] listDataRequestReasonSelenium(){
        int filas = 4;
        int columnas = 1;
        String[][] attribute = new String[filas][columnas];
        attribute[0][0] = "Conversión";
        attribute[1][0] = "Mantenimiento";
        attribute[2][0] = "Nuevo";
        attribute[3][0] = "Resolución de Problemas";
        return attribute;
    }

    private String[][] listDataRequestCategoriaSR(){
        int filas = 3;
        int columnas = 2;
        String[][] attribute = new String[filas][columnas];
        attribute[0][0] = "Categoria1";
        attribute[0][1] = "";
        attribute[1][0] = "Categoria2";
        attribute[1][1] = "";
        attribute[2][0] = "Categoria3";
        attribute[2][1] = "";
        return attribute;
    }

    private String[][] listDataRequestMotivoSolicitudSR(){
        int filas = 3;
        int columnas = 2;
        String[][] attribute = new String[filas][columnas];
        attribute[0][0] = "Motivo1";
        attribute[0][1] = "";
        attribute[1][0] = "Motivo2";
        attribute[1][1] = "";
        attribute[2][0] = "Motivo3";
        attribute[2][1] = "";
        return attribute;
    }
}
