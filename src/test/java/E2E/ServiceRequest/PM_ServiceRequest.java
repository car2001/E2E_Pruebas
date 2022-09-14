package E2E.ServiceRequest;

import Applications.DataEntityManager.DEM_Entity;
import Applications.ProcessManager.PM_Hierarchies;
import Applications.ProcessManager.PM_Process;
import Helpers.AccessBranch;
import Helpers.BasicControl;
import Helpers.ChargePopPup;
import Helpers.DynamicScroll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.IOException;
import java.time.Duration;

public class PM_ServiceRequest {
    private WebDriver driver;
    private BasicControl basicControl;
    private PM_Hierarchies hierarchies;
    private PM_Process process;
    private AccessBranch accessBranch;
    private DynamicScroll searchScrollElement;
    private ServiceRequest_Step1 step1;
    private ServiceRequest_Step2 step2;
    private ServiceRequest_Step3 step3;

    public PM_ServiceRequest(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.hierarchies = new PM_Hierarchies(driver);
        this.process = new PM_Process(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.accessBranch= new AccessBranch(driver);
        this.step1 = new ServiceRequest_Step1(driver);
        this.step2 = new ServiceRequest_Step2(driver);
        this.step3 = new ServiceRequest_Step3(driver);
    }

    public void createPM_ServiceRequest() throws InterruptedException, AWTException, IOException {
        hierarchies.crearHierarchies("Gestión Soporte Selenium");
        process.crearProceso("Gestión Soporte Selenium","Service Request Selenium","INS-SR","SLA-SR");
    }

    public void createSteps_ServiceRequest() throws IOException, InterruptedException, AWTException {
        searchProcess("Service Request Selenium");
        basicControl.openWizard();
        Thread.sleep(5000);
        //step1.step1Process();
        //step2.step2Process();
        step3.step3Process();
    }


    private void searchProcess(String nameProcess){
        int xpos = searchScrollElement.elementSearch("Process Hierarchies");
        if (xpos != -1){
            accessBranch.clickBranches(xpos);
            xpos = searchScrollElement.elementSearch("Gestión Soporte Selenium");
            if(xpos != -1){
                accessBranch.clickBranches(xpos);
                xpos = searchScrollElement.elementSearch("Processes");
                if(xpos != -1){
                    accessBranch.clickBranches(xpos);
                    xpos = searchScrollElement.elementSearch(nameProcess);
                    if(xpos != -1){
                        WebElement process = driver.findElement(By.xpath("//span[text()='"+nameProcess+"']"));
                        process.click();
                        ChargePopPup.PopPupGeneral(driver,new WebDriverWait(driver, Duration.ofSeconds(100)));
                        //Ingreso al proceso
                    }else{
                        System.out.println("No hay proceso buscado");
                    }
                }else{
                    System.out.println("No hay Processes");
                }
            }else{
                System.out.println("No hay jerarquia");
            }
        }else{
            System.out.println("No hay Process Hierarchies");
        }
    }



}
