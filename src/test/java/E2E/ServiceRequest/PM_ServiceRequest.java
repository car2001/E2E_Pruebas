package E2E.ServiceRequest;

import Applications.DataEntityManager.DEM_Entity;
import Applications.ProcessManager.PM_Hierarchies;
import Applications.ProcessManager.PM_Process;
import Helpers.BasicControl;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.IOException;

public class PM_ServiceRequest {
    private WebDriver driver;
    private BasicControl basicControl;
    PM_Hierarchies hierarchies;
    PM_Process process;
    DEM_Entity entity;
    ServiceRequest_Step1 step1;

    public PM_ServiceRequest(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.hierarchies = new PM_Hierarchies(driver);
        this.process = new PM_Process(driver);
        this.step1 = new ServiceRequest_Step1(driver);
    }

    public void createPM_ServiceRequest() throws InterruptedException, AWTException, IOException {
        hierarchies.crearHierarchies("Gestión Soporte");
        process.crearProceso("Gestión Soporte","Service Request Selenium","INS-SR","SLA-SR");
        //basicControl.openWizard();
        //step1.step1Process();
    }





}
