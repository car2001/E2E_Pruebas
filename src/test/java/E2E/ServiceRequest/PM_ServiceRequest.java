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
    PM_Hierarchies hierarchies;
    PM_Process process;
    AccessBranch accessBranch;
    DynamicScroll searchScrollElement;
    ServiceRequest_Step1 step1;

    public PM_ServiceRequest(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.hierarchies = new PM_Hierarchies(driver);
        this.process = new PM_Process(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.accessBranch= new AccessBranch(driver);
        this.step1 = new ServiceRequest_Step1(driver);
    }

    public void createPM_ServiceRequest() throws InterruptedException, AWTException, IOException {
        hierarchies.crearHierarchies("Gestión Soporte Selenium");
        process.crearProceso("Gestión Soporte Selenium","Service Request Selenium","INS-SR","SLA-SR");
    }

    public void createSteps_ServiceRequest() throws IOException, InterruptedException {
        searchProcess("Service Request Selenium");
    }

    private void clickProcess() throws IOException, InterruptedException {
        WebElement process = driver.findElement(By.xpath("//span[text()='Service Request Selenium']"));
        process.click();
        ChargePopPup.PopPupGeneral(driver,new WebDriverWait(driver, Duration.ofSeconds(100)));
        basicControl.openWizard();
        step1.step1Process();
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
                        WebElement process = driver.findElement(By.xpath());
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
